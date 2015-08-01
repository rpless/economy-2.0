package io.github.rpless

import javax.imageio.ImageIO

import io.finch.request._
import io.finch.route._
import io.finch._

object Api {
  val ocr = Ocr().get

  //val authorizer = RequestReader.value[Boolean](true)

  def receiptEndpoint: Router[RequestReader[Seq[TextInterpreter.Item]]] =
    Post / "receipt" /> fileUpload("image").embedFlatMap({file =>
      val img = ImageIO.read(file.getFile)
      TextInterpreter(ocr.getRawText(img)).toFuture
    })

}
