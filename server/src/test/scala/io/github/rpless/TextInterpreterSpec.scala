package io.github.rpless

import org.scalatest.{FlatSpec, Matchers}

/**
 */
class TextInterpreterSpec extends FlatSpec with Matchers {
  "TextInterpreter" should "computer empty on an empty string" in {
    TextInterpreter("") shouldBe Seq()
  }

  it should "return one item form one line" in {
    TextInterpreter("item       $4.50") shouldBe Seq(("item","$4.50"))
  }

  it should "return one item from two lines" in {
    TextInterpreter("slkefksehf sekfhse\nitem        $12.00") shouldBe Seq(("item","$12.00"))
  }
}
