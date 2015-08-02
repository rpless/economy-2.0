package io.github.rpless

import javax.imageio.ImageIO

import io.finch.request._
import io.finch.route._
import TextInterpreter.Item

object Api {
  val ocr = Ocr().get

  val authorizer = RequestReader.value[Boolean](true).should(ValidationRule[Boolean]("auth"){f => f})

  val fileToImage = fileUpload("image") ~> {file =>
    val img = ImageIO.read(file.getFile)
    TextInterpreter(ocr.getRawText(img))
  }

  def receiptEndpoint: Router[RequestReader[Seq[Item]]] =
    Post / "receipt" /> (authorizer :: fileToImage).asTuple ~> {_._2}

}
