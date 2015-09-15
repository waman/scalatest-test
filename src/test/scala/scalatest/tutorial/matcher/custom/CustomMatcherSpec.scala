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
  "BeAprilFool(LocalDate版)" should "Matcherでエイプリルフールであることを検証する" in {

//    val beAprilFool = new Matcher[LocalDate]{
//
//      override def apply(left: LocalDate) =
//        MatchResult(
//          left.getMonth == Month.APRIL && left.getDayOfMonth == 1,
//          s"""${left.format(dateFormat)}はエイプリルフールではありません。""",
//          s"""${left.format(dateFormat)}はエイプリルフールです。"""
//        )
//    }

    val beAprilFool = Matcher { left: LocalDate =>
      MatchResult(
        left.getMonth == Month.APRIL && left.getDayOfMonth == 1,
        s"""${left.format(dateFormat)}はエイプリルフールではありません。""",
        s"""${left.format(dateFormat)}はエイプリルフールです。"""
      )
    }

    date_2016_4_1 should beAprilFool
    today should not (beAprilFool)
//    today should beAprilFool
  }

  "BeAprilFool(TemporalAccessor版)" should "Matcherでエイプリルフールであることを検証する" in {

    val beAprilFool = Matcher { left: TemporalAccessor =>
      val monthDay = MonthDay.from(left)
      MatchResult(
        monthDay.getMonth == APRIL && monthDay.getDayOfMonth == 1,
        s"""${dateFormat.format(monthDay)}はエイプリルフールではありません。""",
        s"""${dateFormat.format(monthDay)}はエイプリルフールです。"""
      )
    }

    date_2016_4_1 should beAprilFool
    today should not (beAprilFool)
    now should not (beAprilFool)
//    today should beAprilFool
  }

  "BeDateOf" should "Matcherで指定された日付であることを検証する" in {

    def beDateOf(month:Month, day:Int) = Matcher { left: TemporalAccessor =>
      val monthDay = MonthDay.from(left)
      MatchResult(
        monthDay.getMonth == month && monthDay.getDayOfMonth == day,
        s"""${dateFormat.format(monthDay)}は${month.getValue}月${day}日ではありません。""",
        s"""${dateFormat.format(monthDay)}は${month.getValue}月${day}日です。"""
      )
    }

    today should beDateOf(SEPTEMBER, 1)
    today shouldNot beDateOf(APRIL, 1)
    today should not (beDateOf(APRIL, 1))
//    date_2015_4_1 should beDateOf(SEPTEMBER, 1)
  }

  //***** BeMatcher *****
  "be aprilFool" should "BeMatcherでエイプリルフールであることを検証する" in {
    val aprilFool = BeMatcher { left: TemporalAccessor =>
      val monthDay = MonthDay.from(left)
      MatchResult (
        monthDay.getMonth == APRIL && monthDay.getDayOfMonth == 1,
        s"""${dateFormat.format(monthDay)}はエイプリルフールではありません。""",
        s"""${dateFormat.format(monthDay)}はエイプリルフールです。"""
      )
    }

    date_2016_4_1 should be (aprilFool)
    today should not be aprilFool
    //    now should be (aprilFool)
  }

  "be dateOf" should "BeMatcherで指定された日付であることを検証する" in {

    def dateOf(month: Month, day: Int) = BeMatcher { left: TemporalAccessor =>
      val monthDay = MonthDay.from(left)
      MatchResult(
        monthDay.getMonth == month && monthDay.getDayOfMonth == day,
        s"""${dateFormat.format(monthDay)}は${month.getValue}月${day}日ではありません。""",
        s"""${dateFormat.format(monthDay)}は${month.getValue}月${day}日です。"""
      )
    }

    today should be (dateOf(SEPTEMBER, 1))
    today shouldBe dateOf(SEPTEMBER, 1)
    today shouldNot be (dateOf(APRIL, 1))
    today should not be dateOf(APRIL, 1)
  }

  //***** BePropertyMatcher *****
  "be a leapYear" should "BePropertyMatcherで閏年であることを検証する" in {
    val leapYear = BePropertyMatcher { left: TemporalAccessor =>
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
      HavePropertyMatcher { left: TemporalAccessor =>
        val month = Month.from(left)
        HavePropertyMatchResult(month == expectedValue, "month", expectedValue, month)
      }

    def day(expectedValue:Int) =
      HavePropertyMatcher { left: TemporalAccessor =>
        val day = MonthDay.from(left).getDayOfMonth
        HavePropertyMatchResult(day == expectedValue, "day", expectedValue, day)
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

  "have (length ()(of []))" should "HavePropertyMatcherでlength" in {

    def length(seconds:Long) = new HavePropertyMatcher[TemporalAccessor, Long] {
      override def apply(left: TemporalAccessor) = {
        val lengthBySecond = Year.from(left) match {
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
