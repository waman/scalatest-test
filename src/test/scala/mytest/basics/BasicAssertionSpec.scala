package mytest.basics

import org.scalatest.exceptions.{TestCanceledException, TestFailedException}
import org.scalatest.{FlatSpec, Matchers}

class BasicAssertionSpec extends FlatSpec with Matchers{

  "assert, assume" should "Boolean’l‚ğˆø”‚É‚Æ‚Á‚Äfalse‚È‚ç—áŠO‚ğ“Š‚°‚é" in {
    intercept[TestFailedException]{ assert(false) }
    intercept[TestCanceledException]{ assume(false) }
  }

  "fail, cancel" should "ˆø”‚ğ‚Æ‚ç‚¸‚É—áŠO‚ğ“Š‚°‚é" in {
    intercept[TestFailedException]{ fail() }
    intercept[TestCanceledException]{ cancel() }
  }
}