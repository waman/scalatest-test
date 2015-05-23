package mytest.basics

import org.scalatest.exceptions.{TestCanceledException, TestFailedException}
import org.scalatest.{FlatSpec, Matchers}

class BasicAssertionSpec extends FlatSpec with Matchers{

  "assert, assume" should "Boolean�l�������ɂƂ���false�Ȃ��O�𓊂���" in {
    intercept[TestFailedException]{ assert(false) }
    intercept[TestCanceledException]{ assume(false) }
  }

  "fail, cancel" should "�������Ƃ炸�ɗ�O�𓊂���" in {
    intercept[TestFailedException]{ fail() }
    intercept[TestCanceledException]{ cancel() }
  }
}