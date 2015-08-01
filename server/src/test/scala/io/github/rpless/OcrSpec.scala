package io.github.rpless

import java.awt.{Font, Color}
import java.awt.image.BufferedImage

import org.scalatest.{Matchers, FlatSpec}

/**
 */
class OcrSpec extends FlatSpec with Matchers {
  val ocr = Ocr().get

  "Ocr" should "get empty text from empty image" in {
    testWithText("")
  }

  "Ocr" should "get simple string from generated image" in {
    testWithText("LALALALALALAE FA#R #WR W# ))W$T:W:# RW :FW :#!{}|")
  }

  def testWithText(text: String): Unit = {
    val img = imageWithText(text)
    ocr.getRawText(img) shouldBe text
  }

  def imageWithText(text: String): BufferedImage = {
    val img = new BufferedImage(1024, 768, BufferedImage.TYPE_INT_ARGB)
    val graphics = img.getGraphics
    graphics.fillRect(0, 0, 200, 50);
    graphics.setColor(Color.BLACK);
    graphics.setFont(new Font("Arial Black", Font.BOLD, 20));
    graphics.drawString(text,10,50)
    return img
  }
}
