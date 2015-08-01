package io.github.rpless

import java.awt.{Font, Color}
import java.awt.image.BufferedImage
import java.io.File
import javax.imageio.ImageIO

import org.scalatest.{Matchers, FlatSpec}

/**
 */
class OcrSpec extends FlatSpec with Matchers {
  val ocr = Ocr().get

  "Ocr" should "get empty text from empty image" in {
    testWithText("")
  }

  "Ocr" should "get one char string from generated image" in {
    testWithText("L")
  }

  "Ocr" should "get long string from generated image" in {
    testWithText("LALALALALALAE FA#Rsfe")
  }

  def testWithText(text: String): Unit = {
    val img = imageWithText(text)
    ocr.getRawText(img) shouldBe text
  }

  def imageWithText(text: String): BufferedImage = {
    val img = new BufferedImage(1024, 800, BufferedImage.TYPE_4BYTE_ABGR)
    val graphics = img.getGraphics
    graphics.setColor(Color.BLACK)
    graphics.setFont(new Font("Arial Black", Font.BOLD, 20))
    graphics.drawString(text,10,50)
    ImageIO.write(img,"jpg",new File("/tmp/out.jpg"))
    img
  }
}
