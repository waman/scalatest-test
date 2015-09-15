package scalatest.tutorial.matcher.custom

import java.time.Month._
import java.time._
import java.time.temporal.TemporalAccessor

import org.scalatest.matchers.Matcher
import org.scalatest.{FlatSpec, Matchers}

class ComposeMatcherSpec extends FlatSpec with Matchers{

  val birthday = MonthDay.of(DECEMBER, 11)
  val birthTime = ZonedDateTime.parse("2015-12-11T00:00+09:00[Asia/Tokyo]")

  val now          = ZonedDateTime.parse("2015-09-01T00:00+09:00[Asia/Tokyo]")
  val dtGreenwich0 = ZonedDateTime.parse("2015-09-01T00:00Z[Greenwich]")
  val dtTokyo0     = ZonedDateTime.parse("2015-09-01T00:00+09:00[Asia/Tokyo]")
  val dtTokyo9     = ZonedDateTime.parse("2015-09-01T09:00+09:00[Asia/Tokyo]")
  val dt0offset9   = OffsetDateTime.parse("2015-09-01T00:00+09:00")

  "method definition" should "マッチャーを定義する" in {
    val beInOphiuchusMonthDayPeriod = be >= MonthDay.of(NOVEMBER, 29) and be < MonthDay.of(DECEMBER, 18)

    birthday should beInOphiuchusMonthDayPeriod
  }

  "f compose g" should "SUTを別オブジェクトへ変換してMatcherを適用するのと等価なMatcherを生成する" in {
    val beInOphiuchusMonthDayPeriod = be >= MonthDay.of(NOVEMBER, 29) and be < MonthDay.of(DECEMBER, 18)

    // for TemporalAccessor
    val beOphiuchus = beInOphiuchusMonthDayPeriod compose { temporal: TemporalAccessor => MonthDay from temporal }

    birthTime should beOphiuchus
    birthday should beOphiuchus
  }

  it should "変数名を短縮して分かりやすくしてみる" in {
    val f = be >= MonthDay.of(NOVEMBER, 29) and be < MonthDay.of(DECEMBER, 18)
      // f: Matcher[MonthDay]
    val g = MonthDay from _  // g: TemporalAccessor => MonthDay
    val h = f compose g  // h: Matcher[TemporalAccessor]

    birthTime should h
    birthTime should (f compose g)
    g(birthTime) should f
  }

  it should "引数がある場合は関数として定義する" in {
    def f(month: Month, day: Int) = be (MonthDay.of(month, day))  // equal ではマズイ
      // f: (month: Month, day: Int)Matcher[MonthDay]
    val g = MonthDay from _  // TemporalAccessor => MonthDay
    def h(month: Month, day: Int) = f(month, day) compose g
    // h: (month: Month, day: Int)Matcher[TemporalAccessor]

    birthTime should h(DECEMBER, 11)
    birthTime should (f(DECEMBER, 11) compose g)
    g(birthTime) should f(DECEMBER, 11)

  }

  "2回のcompose" should "SUTにも期待値にも変換を施した後、検証を行う" in {
    val equiv = be (_: Instant)  // equiv: Instant => Matcher[Instant]
    val toInstant = (_: ZonedDateTime).toInstant  // toInstant: ZonedDateTime => Instant
    val beTheSameInstantAs = (equiv compose toInstant) andThen (_ compose toInstant)
      // beTheSameInstant: ZonedDateTime => Matcher[ZonedDateTime]

    dtGreenwich0 should not be dtTokyo9  // ZonedDateTime としては等しくない
    dtGreenwich0 should beTheSameInstantAs (dtTokyo9)  // Instant としては等しい
    dtGreenwich0 shouldNot beTheSameInstantAs (dtTokyo0)
  }

  it should "変数名を短縮して分かりやすくしてみる" in {
    val f = be (_:Instant)
    val g = (_:ZonedDateTime).toInstant
    val beTheSameInstantAs = (f compose g) andThen (_ compose g)

    dtGreenwich0 should beTheSameInstantAs (dtTokyo9)
    g(dtGreenwich0) should f(g(dtTokyo9))
  }

  it should "どちらがどちらの変換か？" in {
    val f = be (_:Instant)  // f: Instant => Matcher[Instant]
    val g = (_:OffsetDateTime).toInstant  // g: OffsetDateTime => Instant
    val h = (_:ZonedDateTime).toInstant  // h: ZonedDateTime => Instant
    val beTheSameInstantAs = (f compose g) andThen (_ compose h)
      // val beTheSameInstantAs = (f compose g) andThen ((_:Matcher[Instant]) compose h)

    dtTokyo0 should beTheSameInstantAs (dt0offset9)
    dtTokyo0 should ((f compose g) andThen (_ compose h))(dt0offset9)
    h(dtTokyo0) should f(g(dt0offset9))

    // 型を明示して段階を踏んだ構築
    val fg: OffsetDateTime => Matcher[Instant] = f compose g
    val _h: Matcher[Instant] => Matcher[ZonedDateTime] = (_:Matcher[Instant]) compose h
    val beTheSameInstantAs_ : OffsetDateTime => Matcher[ZonedDateTime] = fg andThen _h
    dtTokyo0 should beTheSameInstantAs_ (dt0offset9)

    // compose だけで書くとこんな感じ（関数として定義）
    def beTheSameInstantAs__(odt: OffsetDateTime) = (f compose g)(odt) compose h
    dtTokyo0 should beTheSameInstantAs__ (dt0offset9)
  }
}
