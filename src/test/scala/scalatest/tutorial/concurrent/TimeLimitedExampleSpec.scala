package scalatest.tutorial.concurrent

import org.scalatest.{DoNotDiscover, FlatSpec}
import org.scalatest.concurrent.TimeLimitedTests
import org.scalatest.time.SpanSugar._

@DoNotDiscover
class TimeLimitedExampleSpec extends FlatSpec with TimeLimitedTests{

  override def timeLimit = 200 millis

  "Calculator" should "タイムリミット内に終了すれば成功" in {
    Thread.sleep(100)
  }

  it should "タイムリミット内に終了しなければ失敗" in {
    Thread.sleep(300)
  }
}
