package io.github.rpless

import java.awt.image.BufferedImage

import net.sourceforge.tess4j.Tesseract

import scala.util.Try

/**
 */
class Ocr {
  val api = new Tesseract()

  def getRawText(img: BufferedImage): Try[String] = {
    Try(api.doOCR(img))
  }
}

object Ocr {
  System.setProperty("jna.encoding", "UTF8")
  def apply(): Try[Ocr] = {
    Try(new Ocr)
  }
}
