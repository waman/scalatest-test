package scalatest.tutorial.matcher.custom

import java.time.Month._
import java.time._
import java.time.format.DateTimeFormatter
import java.time.temporal.TemporalAccessor

import org.scalatest.matchers._
import org.scalatest.{FlatSpec, Matchers}

class CustomMatcherSpec extends FlatSpec with Matchers{

  val now = ZonedDateTime.parse("2015-09-01T00:00+09:00[Asia/Tokyo]")
  val today = now.toLocalDate
  val date_2016_4_1 = LocalDate.of(2016, APRIL, 1)

  val dateFormat = DateTimeFormatter.ofPattern("M月d日")

  "be date" should "Javaオブジェクトのequalsが定義されていればカスタムマッチャーの必要なし" in{
    today should be (LocalDate.of(2015, 9, 1))
  }

  //***** Matcher *****
  "beAprilFool(LocalDate版)" should "Matcherでエイプリルフールであることを検証する" in {

//    val beAprilFool = new Matcher[LocalDate]{
//
//      override def apply(sut: LocalDate) =
//        MatchResult(
//          sut.getMonth == Month.APRIL && sut.getDayOfMonth == 1,
//          s"""${sut.format(dateFormat)}はエイプリルフールではありません。""",
//          s"""${sut.format(dateFormat)}はエイプリルフールです。"""
//        )
//    }

    val beAprilFool = Matcher { sut: LocalDate =>
      MatchResult(
        sut.getMonth == Month.APRIL && sut.getDayOfMonth == 1,
        s"""${sut.format(dateFormat)}はエイプリルフールではありません。""",
        s"""${sut.format(dateFormat)}はエイプリルフールです。"""
      )
    }

    date_2016_4_1 should beAprilFool
    today should not (beAprilFool)
//    today should beAprilFool
  }

  val aprilFool = MonthDay.of(APRIL, 1)

  "beAprilFool(TemporalAccessor版)" should "Matcherでエイプリルフールであることを検証する" in {

    val beAprilFool = Matcher { sut: TemporalAccessor =>
      val monthDay = MonthDay.from(sut)
      MatchResult(
        monthDay == aprilFool,
        s"""${dateFormat.format(monthDay)}はエイプリルフールではありません。""",
        s"""${dateFormat.format(monthDay)}はエイプリルフールです。"""
      )
    }

    date_2016_4_1 should beAprilFool
    today shouldNot beAprilFool
    now shouldNot beAprilFool
//    today should beAprilFool
  }

  "beDateOf" should "Matcherで指定された日付であることを検証する" in {

    def beDateOf(month: Month, day: Int) = Matcher { sut: TemporalAccessor =>
      val monthDay = MonthDay.from(sut)
      MatchResult(
        monthDay == MonthDay.of(month, day),
        s"""${dateFormat.format(monthDay)}は${month.getValue}月${day}日ではありません。""",
        s"""${dateFormat.format(monthDay)}は${month.getValue}月${day}日です。"""
      )
    }

    today should beDateOf (SEPTEMBER, 1)
    today shouldNot beDateOf (APRIL, 1)
  }

  //***** BeMatcher *****
  "be dateOf" should "BeMatcherで指定された日付であることを検証する" in {

    def dateOf(month: Month, day: Int) = BeMatcher { sut: TemporalAccessor =>
      val monthDay = MonthDay.from(sut)
      MatchResult(
        monthDay == MonthDay.of(month, day),
        s"""${dateFormat.format(monthDay)}は${month.getValue}月${day}日ではありません。""",
        s"""${dateFormat.format(monthDay)}は${month.getValue}月${day}日です。"""
      )
    }

    today should be (dateOf(SEPTEMBER, 1))
    today shouldBe dateOf(SEPTEMBER, 1)
    today shouldBe dateOf(APRIL, 1)
  }

  //***** BePropertyMatcher *****
  "be a leapYear" should "BePropertyMatcherで閏年であることを検証する" in {
    val leapYear = BePropertyMatcher { sut: TemporalAccessor =>
      BePropertyMatchResult(Year.from(sut).isLeap, "leap year")
    }

    date_2016_4_1 should be a leapYear
    today should be a leapYear

    y2016 should be a 'leap
  }

  //***** HavePropertyMatcher *****
  "have (month (), day ())" should "HavePropertyMatcherでプロパティの値を検証する" in {
    def month(expected:Month) = HavePropertyMatcher { sut: TemporalAccessor =>
      val month = Month.from(sut)
      HavePropertyMatchResult(month == expected, "month", expected, month)
    }

    def day(expected:Int) = HavePropertyMatcher { sut: TemporalAccessor =>
      val day = MonthDay.from(sut).getDayOfMonth
      HavePropertyMatchResult(day == expected, "day", expected, day)
    }

    date_2016_4_1 should have (month (APRIL))

    today should have (
      month (SEPTEMBER),
      day (1)
    )
  }

  val y2015 = Year.of(2015)
  val y2016 = Year.of(2016)

  "have ('length ())" should "Year#length()メソッドでプロパティにアクセスする" in {
    y2015 should have ('length (365))
    y2016 should have ('length (366))
  }

  "have (length ())" should "lengthメソッドでプロパティアクセス" in {
    y2015 should have (length (365))
    y2015 should have (length (365) (of [Year]))
    y2016 should have length 366
  }

  "have (length ())" should "HavePropertyMatcherでlength" in {

    def length(seconds:Long) = new HavePropertyMatcher[TemporalAccessor, Long] {
      override def apply(sut: TemporalAccessor) = {
        val lengthBySecond = Year.from(sut) match {
          case y: Year if y.isLeap => days2seconds(366)
          case _ => days2seconds(365)
        }
        HavePropertyMatchResult(lengthBySecond == seconds, "length by second", lengthBySecond, seconds)
      }

      private def days2seconds(n: Int): Long = n * 24 * 60 * 60
    }

    y2016 should have ('length (366))
    y2016 should have (length (366L * 24L * 60L * 60L))
  }
}
