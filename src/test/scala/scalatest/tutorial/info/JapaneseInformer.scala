package scalatest.tutorial.info

import org.scalatest.Informing

trait JapaneseInformer extends Informing{

  def 事前準備() = info("事前準備")
  def 事前準備(message:String) = info("事前準備：" + message)

  def 実行() = info("実行")
  def 実行(message:String) = info("実行：" + message)

  def 検証() = info("検証")
  def 検証(message:String) = info("検証：" + message)

  def 後処理() = info("後処理")
  def 後処理(message:String) = info("後処理：" + message)

  def また() = info("また")
  def また(message:String) = info("また：" + message)
}
