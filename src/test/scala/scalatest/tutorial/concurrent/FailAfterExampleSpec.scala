package scalatest.tutorial.concurrent

import org.scalatest.{DoNotDiscover, FlatSpec}
import org.scalatest.concurrent.Timeouts
import org.scalatest.time.SpanSugar._

@DoNotDiscover
class FailAfterExampleSpec extends FlatSpec with Timeouts{

  "Calculator" should "タイムアウト内に終了すれば成功" in failAfter(200 millis){
    Thread.sleep(100)
  }

  it should "タイムアウト内に終しなければ失敗" in failAfter(200 millis){
    Thread.sleep(300)
  }
}
