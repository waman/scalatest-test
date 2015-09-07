package scalatest.tutorial.matcher.custom

import java.time._
import java.time.temporal.{ChronoField, TemporalAccessor}

import org.scalactic.{Equality, Uniformity}
import org.scalatest.{FlatSpec, Matchers}

class EqualityMatcherSpec extends FlatSpec with Matchers{

  val dtGreenwich0 = ZonedDateTime.parse("2015-09-01T00:00Z[Greenwich]")
  val dtTokyo0 = ZonedDateTime.parse("2015-09-01T00:00:00.000+09:00[Asia/Tokyo]")
  val dtTokyo9 = ZonedDateTime.parse("2015-09-01T09:00:00.000+09:00[Asia/Tokyo]")
  val dtUS12 = ZonedDateTime.parse("2015-09-01T12:00-04:00[America/New_York]")

  val monthDay = new Equality[TemporalAccessor] {
    override def areEqual(a: TemporalAccessor, b: Any): Boolean =
      b match {
        case t: TemporalAccessor => MonthDay.from(a) == MonthDay.from(t)
        case _ => false
      }
  }

  val inTokyo = new Uniformity[TemporalAccessor] {

    val zone = ZoneId.of("Asia/Tokyo")

    override def normalized(temporal: TemporalAccessor) = temporal match {
      case zdt: ZonedDateTime => zdt.withZoneSameInstant(zone)
      case odt: OffsetDateTime => odt.atZoneSameInstant(zone)
      case _ => temporal
    }

    override def normalizedCanHandle(a: Any) = a match {
      case temporal: TemporalAccessor => normalizedCanHandle(temporal)
      case _ => false
    }
    
    private def normalizedCanHandle(temporal: TemporalAccessor) =
      temporal.isSupported(ChronoField.MONTH_OF_YEAR) &&
        temporal.isSupported(ChronoField.DAY_OF_MONTH)

    override def normalizedOrSame(a: Any): Any = a match {
      case temporal: TemporalAccessor if normalizedCanHandle(temporal) =>
        normalized(temporal)
      case _ => a
    }
  }

  "decided by monthDay" should "月日が等しいかことを検証する" in {
    dtGreenwich0 should not equal dtTokyo0
    dtGreenwich0 should equal (dtTokyo0) (decided by monthDay)
  }

  "after being inTokyo" should "東京での時刻で比べる" in {
    dtTokyo0 shouldNot equal (dtGreenwich0) (after being inTokyo)

//    dtTokyo9 should equal (dtGreenwich0)  // 失敗
    dtTokyo9 should equal (dtGreenwich0) (after being inTokyo)

    val dtOffset9_0 = OffsetDateTime.parse("2015-09-01T00:00+09:00")
//    dtOffset9_0 should equal (dtTokyo0)  // 失敗
    dtOffset9_0 should equal (dtTokyo0) (after being inTokyo)
  }

  "decided by monthDay afterBeing inTokyo" should "同時刻の東京での月日が等しいかことを検証する" in {
    dtGreenwich0 should equal (dtTokyo0) (decided by monthDay afterBeing inTokyo)

    dtUS12 shouldNot equal (dtTokyo0) (decided by monthDay afterBeing inTokyo)
      // USと東京の時差が 9-(-4) = 13 時間なので、東京0時、US12時なら25時間のズレ
      //  -> 日付が異なる
  }
}
