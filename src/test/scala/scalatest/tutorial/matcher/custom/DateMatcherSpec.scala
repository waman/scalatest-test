package scalatest.tutorial.matcher.custom

import java.time.Month._
import java.time._
import java.time.format.DateTimeFormatter
import java.time.temporal.TemporalAccessor

import org.scalatest.matchers._
import org.scalatest.{FlatSpec, Matchers}

class DateMatcherSpec extends FlatSpec with Matchers{

  val now = ZonedDateTime.parse("2015-09-01T00:00:00.000+09:00[Asia/Tokyo]")
  val today = now.toLocalDate
  val date_2016_4_1 = LocalDate.of(2016, APRIL, 1)

  val dateFormat = DateTimeFormatter.ofPattern("M月d日")

  "be date" should "Javaオブジェクトのequalsが定義されていればカスタムマッチャーの必要なし" in{
    today should be (LocalDate.of(2015, 9, 1))
  }

  //***** Matcher *****
  "BeAprilFool(LocalDate版)" should "Matcherでエイプリルフールであることを検証する" in {

    val beAprilFool = new Matcher[LocalDate]{

      override def apply(left: LocalDate): MatchResult = {
        MatchResult(
          left.getMonth == Month.APRIL && left.getDayOfMonth == 1,
          s"""${left.format(dateFormat)}はエイプリルフールではありません。""",
          s"""${left.format(dateFormat)}はエイプリルフールです。"""
        )
      }
    }

    date_2016_4_1 should beAprilFool
    today should not (beAprilFool)
//    today should beAprilFool
  }

  "BeAprilFool(TemporalAccessor版)" should "Matcherでエイプリルフールであることを検証する" in {

    val beAprilFool = new Matcher[TemporalAccessor] {

      override def apply(left: TemporalAccessor): MatchResult = {
        val monthDay = MonthDay.from(left)
        MatchResult(
          monthDay.getMonth == APRIL && monthDay.getDayOfMonth == 1,
          s"""${dateFormat.format(monthDay)}はエイプリルフールではありません。""",
          s"""${dateFormat.format(monthDay)}はエイプリルフールです。"""
        )
      }
    }

    date_2016_4_1 should beAprilFool
    today should not (beAprilFool)
    now should not (beAprilFool)
//    today should beAprilFool
  }

  "BeDateOf" should "Matcherで指定された日付であることを検証する" in {

    def beDateOf(month:Month, day:Int) = new Matcher[TemporalAccessor] {

      override def apply(left: TemporalAccessor): MatchResult = {
        val monthDay = MonthDay.from(left)
        MatchResult(
          monthDay.getMonth == month && monthDay.getDayOfMonth == day,
          s"""${dateFormat.format(monthDay)}は${month.getValue}月${day}日ではありません。""",
          s"""${dateFormat.format(monthDay)}は${month.getValue}月${day}日です。"""
        )
      }
    }

    today should beDateOf(SEPTEMBER, 1)
    today shouldNot beDateOf(APRIL, 1)
    today should not (beDateOf(APRIL, 1))
//    date_2015_4_1 should beDateOf(SEPTEMBER, 1)
  }

  //***** BeMatcher *****
  "be aprilFool" should "BeMatcherでエイプリルフールであることを検証する" in {
    val aprilFool = new BeMatcher[TemporalAccessor]{

      override def apply(left: TemporalAccessor): MatchResult = {
        val monthDay = MonthDay.from(left)
        MatchResult (
          monthDay.getMonth == APRIL && monthDay.getDayOfMonth == 1,
          s"""${dateFormat.format(monthDay)}はエイプリルフールではありません。""",
          s"""${dateFormat.format(monthDay)}はエイプリルフールです。"""
        )
      }
    }

    date_2016_4_1 should be (aprilFool)
    today should not be aprilFool
    //    now should be (aprilFool)
  }

  "be dateOf" should "BeMatcherで指定された日付であることを検証する" in {

    def dateOf(month:Month, day:Int) = new BeMatcher[TemporalAccessor] {

      override def apply(left: TemporalAccessor): MatchResult = {
        val monthDay = MonthDay.from(left)
        MatchResult(
          monthDay.getMonth == month && monthDay.getDayOfMonth == day,
          s"""${dateFormat.format(monthDay)}は${month.getValue}月${day}日ではありません。""",
          s"""${dateFormat.format(monthDay)}は${month.getValue}月${day}日です。"""
        )
      }
    }

    today should be (dateOf(SEPTEMBER, 1))
    today shouldBe dateOf(SEPTEMBER, 1)
    today shouldNot be (dateOf(APRIL, 1))
    today should not be dateOf(APRIL, 1)
  }

  //***** BePropertyMatcher *****
  "be a leapYear" should "BePropertyMatcherで閏年であることを検証する" in {
    val leapYear = new BePropertyMatcher[TemporalAccessor]{
      override def apply(left:TemporalAccessor) =
        BePropertyMatchResult(Year.from(left).isLeap, "leap year")
    }

    val y2016 = Year.of(2016)
    y2016 should be a leapYear

    date_2016_4_1 should be a leapYear

    today should not be a (leapYear)
//    today should be a leapYear

    y2016 should be a 'leap
  }

  //***** HavePropertyMatcher *****
  "have (month (), day ())" should "HavePropertyMatcherでプロパティの値を検証する" in {
    def month(expectedValue:Month) =
      new HavePropertyMatcher[TemporalAccessor, Month] {
        override def apply(left: TemporalAccessor) = {
          val month = Month.from(left)
          HavePropertyMatchResult(month == expectedValue, "month", expectedValue, month)
        }
      }

    def day(expectedValue:Int) =
      new HavePropertyMatcher[TemporalAccessor, Int] {
        override def apply(left: TemporalAccessor) = {
          val day = MonthDay.from(left).getDayOfMonth
          HavePropertyMatchResult(day == expectedValue, "day", expectedValue, day)
        }
      }

    date_2016_4_1 should have (month (APRIL))

    today should have (
      month (SEPTEMBER),
      day (1)
    )
  }

  "have (length ()(of []))" should "HavePropertyMatcherでlength" in {
//    def length(n:Int) = new HavePropertyMatcher[TemporalAccessor, Int]{
//      override def apply(left: TemporalAccessor) ={
//        val year = Year.from(left)
//        HavePropertyMatchResult(year.length == n, "length", year.length, n)
//      }
//    }
//
//    date_2016_4_1 should have (
//      length (366)
//    )

    val y2016 = Year.of(2016)
//    y2016 should have (length (365))

    def length(l:Long) = new HavePropertyMatcher[TemporalAccessor, Long] {
      override def apply(left: TemporalAccessor) = {
        val daysInYear = if(Year.from(left).isLeap) 366 else 355
        val lengthBySecond = daysInYear * 24 * 60 * 60
        HavePropertyMatchResult(lengthBySecond == l, "length by second", lengthBySecond, l)
    }
  }

    y2016 should have ('length (366))
    y2016 should have (length (366L * 24L * 60L * 60L))
  }
}
