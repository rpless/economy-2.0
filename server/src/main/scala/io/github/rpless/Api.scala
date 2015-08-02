package io.github.rpless

import javax.imageio.ImageIO

import io.finch.request._
import io.finch.route._
import io.finch._

object Api {
  val ocr = Ocr().get

  val authorizer = RequestReader.value[Boolean](true).should(ValidationRule[Boolean]("auth"){f => f})

  val fileToImage = fileUpload("image").embedFlatMap({file =>
    val img = ImageIO.read(file.getFile)
    TextInterpreter(ocr.getRawText(img)).toFuture
  })

  def receiptEndpoint: Router[RequestReader[Seq[TextInterpreter.Item]]] =
    Post / "receipt" /> (authorizer :: fileToImage).asTuple.embedFlatMap(_._2.toFuture)
}
