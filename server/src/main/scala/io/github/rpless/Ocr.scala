package io.github.rpless

import java.awt.image.BufferedImage
import net.sourceforge.tess4j.ITessAPI.TessPageSegMode
import net.sourceforge.tess4j.TessAPI1._
import net.sourceforge.tess4j.util.ImageIOHelper
import scala.util.{Success, Failure, Try}

/**
 */
class Ocr {
  val api = TessBaseAPICreate()
  val dataPath = getClass.getResource("/").getPath
  private val success = TessBaseAPIInit3(api, dataPath, "eng") == 0

  def getRawText(img: BufferedImage): String = {
    //TODO TYPE_INTEGER_ABGR causes segfaults
    val pixelSize = img.getColorModel.getPixelSize
    val bpp = pixelSize / 8.0
    val bpl = Math.ceil(img.getWidth * bpp)
    val buf = ImageIOHelper.convertImageData(img)

    TessBaseAPISetPageSegMode(api, TessPageSegMode.PSM_AUTO)
    TessBaseAPISetImage(api, buf, img.getWidth, img.getHeight, bpp.toInt, bpl.toInt)
    TessBaseAPISetRectangle(api, 0, 0, img.getWidth, img.getHeight)

    val ptr = TessBaseAPIGetUTF8Text(api)
    val str = ptr.getString(0)
    TessDeleteText(ptr)

    str
  }
}

object Ocr {

  System.setProperty("jna.encoding", "UTF8")
  def apply(): Try[Ocr] = {
    val ocr = new Ocr
    if (ocr.success) Success(ocr)
    else Failure(new RuntimeException("OCR init failed"))
  }
}
