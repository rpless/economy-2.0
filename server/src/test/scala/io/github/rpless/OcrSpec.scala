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


  it should "get string from generated image" in {
    testWithText("A Longer String")
  }

  def testWithText(text: String): Unit = {
    val img = imageWithText(text)
    ocr.getRawText(img) should startWith (text)
  }

  def imageWithText(text: String): BufferedImage = {
    val img = new BufferedImage(1024, 800, BufferedImage.TYPE_4BYTE_ABGR)
    val graphics = img.getGraphics
    graphics.setColor(Color.BLACK)
    graphics.setFont(new Font("Arial Black", Font.BOLD, 20))
    graphics.drawString(text, 10, 50)
    ImageIO.write(img, "jpg", new File("/tmp/out.jpg"))
    img
  }
}
