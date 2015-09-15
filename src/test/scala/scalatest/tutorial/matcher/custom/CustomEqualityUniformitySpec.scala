package scalatest.tutorial.matcher.custom

import java.time._
import java.time.temporal.TemporalAccessor

import org.scalactic.{Equality, Uniformity}
import org.scalatest.{FlatSpec, Matchers}

class CustomEqualityUniformitySpec extends FlatSpec with Matchers{

  val dtGreenwich0 = ZonedDateTime.parse("2015-09-01T00:00Z[Greenwich]")
  val dtTokyo0     = ZonedDateTime.parse("2015-09-01T00:00+09:00[Asia/Tokyo]")
  val dtTokyo9     = ZonedDateTime.parse("2015-09-01T09:00+09:00[Asia/Tokyo]")
  val dtUS12       = ZonedDateTime.parse("2015-09-01T12:00-04:00[America/New_York]")

  val monthDay = new Equality[TemporalAccessor] {
    override def areEqual(a: TemporalAccessor, b: Any) = b match {
      case t: TemporalAccessor => MonthDay.from(a) == MonthDay.from(t)
      case _ => false
    }
  }

  val inTokyo = new Uniformity[TemporalAccessor] {

    val tokyo = ZoneId.of("Asia/Tokyo")
    val tokyoOffset = ZoneOffset.ofHours(9)

    override def normalized(temporal: TemporalAccessor): TemporalAccessor =
      temporal match {
        case zdt: ZonedDateTime => zdt.withZoneSameInstant(tokyo)
        case odt: OffsetDateTime => odt.atZoneSameInstant(tokyo)
        case ot: OffsetTime => ot.withOffsetSameInstant(tokyoOffset)
        case _ => temporal
      }

    override def normalizedCanHandle(a: Any): Boolean = a.isInstanceOf[TemporalAccessor]

    override def normalizedOrSame(a: Any): Any = a match {
      case temporal: TemporalAccessor => normalized(temporal)
      case _ => a
    }
  }

  "decided by monthDay" should "月日が等しいかことを検証する" in {
    dtGreenwich0 should not equal dtTokyo0
    dtGreenwich0 should equal (dtTokyo0) (decided by monthDay)
  }

  "after being inTokyo" should "東京での時刻で比べる" in {
    dtTokyo0 shouldNot equal (dtGreenwich0) (after being inTokyo)  // 等しくない

    dtTokyo9 shouldNot equal (dtGreenwich0)  // 等しくない
    dtTokyo9 should equal (dtGreenwich0) (after being inTokyo)

    val dtOffset9_0 = OffsetDateTime.parse("2015-09-01T00:00+09:00")
    dtOffset9_0 shouldNot equal (dtTokyo0)  // 等しくない
    dtOffset9_0 should equal (dtTokyo0) (after being inTokyo)
  }

  "decided by monthDay afterBeing inTokyo" should "同時刻の東京での月日が等しいかことを検証する" in {
    dtGreenwich0 should equal (dtTokyo0) (decided by monthDay afterBeing inTokyo)

    dtUS12 shouldNot equal (dtTokyo0) (decided by monthDay afterBeing inTokyo)
    // USと東京の時差が 9-(-4) = 13 時間なので、東京0時、US12時なら25時間のズレ
    //  -> 日付が異なる
  }
}
