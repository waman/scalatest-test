package scalatest.tutorial.matcher.custom

import java.time.format.DateTimeFormatter
import java.time.{Month, LocalDate, ZonedDateTime}
import java.time.temporal.ChronoField._
import java.time.temporal.TemporalAccessor

import org.scalatest.matchers._
import org.scalatest.{FlatSpec, Matchers}

class DateMatcherSpec extends FlatSpec with Matchers{

  "be date" should "Javaオブジェクトのequalsが定義されていればカスタムマッチャーの必要なし" in {
    val today = ZonedDateTime.now.toLocalDate
//    today should be (LocalDate.of(2015, 9, 3))
  }

  "BeAprilFool(LocalDate版)" should "Matcherでエイプリルフールであることを検証する" in {
    class BeAprilFool extends Matcher[LocalDate]{

      override def apply(left: LocalDate): MatchResult = {
        MatchResult(
          left.getMonth == Month.APRIL && left.getDayOfMonth == 1,
          s"""${left.format(dateFormat)}はエイプリルフールではありません。""",
          s"""${left.format(dateFormat)}はエイプリルフールです。"""
        )
      }

      private val dateFormat = DateTimeFormatter.ofPattern("M月d日")
    }

    val beAprilFool = new BeAprilFool
    val today = ZonedDateTime.now.toLocalDate
//    today should beAprilFool
    today should not (beAprilFool)
  }

  "BeAprilFool(TemporalAccessor版)" should "Matcherでエイプリルフールであることを検証する" in {
    class BeAprilFool extends Matcher[TemporalAccessor]{

      override def apply(left: TemporalAccessor): MatchResult = {
        MatchResult(
          left.get(MONTH_OF_YEAR) == 4 && left.get(DAY_OF_MONTH) == 1,
          s"""${dateFormat.format(left)}はエイプリルフールではありません。""",
          s"""${dateFormat.format(left)}はエイプリルフールです。"""
        )
      }

      private val dateFormat = DateTimeFormatter.ofPattern("M月d日")
    }

    val beAprilFool = new BeAprilFool
    val today = ZonedDateTime.now
//    today should beAprilFool
    today should not (beAprilFool)
    today.toLocalDate should not (beAprilFool)
  }

  "AprilFool" should "BeMatcherでエイプリルフールであることを検証する" in {
    class AprilFool extends BeMatcher[TemporalAccessor]{

      override def apply(left: TemporalAccessor): MatchResult = {
        MatchResult(
          left.get(MONTH_OF_YEAR) == 4 && left.get(DAY_OF_MONTH) == 1,
          s"""${dateFormat.format(left)}はエイプリルフールではありません。""",
          s"""${dateFormat.format(left)}はエイプリルフールです。"""
        )
      }

      private val dateFormat = DateTimeFormatter.ofPattern("M月d日")
    }

    val aprilFool = new AprilFool
    val today = ZonedDateTime.now.toLocalDate
//    today should be (aprilFool)
    today should not be aprilFool
  }

  "BeDateOf" should "Matcherで指定された日付であることを検証する" in {
    class BeDateOf(month:Month, day:Int) extends Matcher[TemporalAccessor]{

      override def apply(left: TemporalAccessor): MatchResult = {
        MatchResult(
          left.get(MONTH_OF_YEAR) == month.getValue && left.get(DAY_OF_MONTH) == day,
          s"""${dateFormat.format(left)}は${month.getValue}月${day}日ではありません。""",
          s"""${dateFormat.format(left)}は${month.getValue}月${day}日です。"""
        )
      }

      private val dateFormat = DateTimeFormatter.ofPattern("M月d日")
    }

    def beDateOf(m:Month, d:Int) = new BeDateOf(m, d)

    val today = ZonedDateTime.now.toLocalDate

    import java.time.Month._
    today should beDateOf(SEPTEMBER, 3)
    today shouldNot beDateOf(APRIL, 1)
    today should not (beDateOf(APRIL, 1))
  }
}
