package io.github.rpless

import java.awt.{Font, Color}
import java.awt.image.BufferedImage

import org.scalatest.{Matchers, FlatSpec}

/**
 */
class OcrSpec extends FlatSpec with Matchers {
  val ocr = Ocr().get

  "Ocr" should "get empty text from empty image" in {
    val img = new BufferedImage(1024, 768, BufferedImage.TYPE_INT_ARGB)
    ocr.getRawText(img) shouldBe ""
  }

  "Ocr" should "get simple string from generated image" in {
    val text = "LALALALALALAE FA#R #WR W# ))W$T:W:# RW :FW :#!{}|"
    val img = new BufferedImage(1024, 768, BufferedImage.TYPE_INT_ARGB)
    val graphics = img.getGraphics
    graphics.fillRect(0, 0, 200, 50);
    graphics.setColor(Color.BLACK);
    graphics.setFont(new Font("Arial Black", Font.BOLD, 20));
    graphics.drawString(text,10,50)
    ocr.getRawText(img).get shouldBe text
  }
}
