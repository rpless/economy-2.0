package io.github.rpless

import java.awt.image.BufferedImage

import org.scalatest.{Matchers, FlatSpec}

/**
 */
class OcrSpec extends FlatSpec with Matchers {
  "Ocr" should "get text from generated image" in {
    val ocr = Ocr().get
    val img = new BufferedImage(1024, 768, BufferedImage.TYPE_INT_ARGB)
    ocr.getRawText(img).get shouldBe ""
  }
}
