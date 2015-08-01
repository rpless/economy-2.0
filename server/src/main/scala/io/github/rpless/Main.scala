package io.github.rpless

import com.twitter.util.Await
import com.twitter.finagle.Httpx
import io.finch.route._

object Main extends App {
  Await.ready(Httpx.serve(":8080", (Get / "hello" /> "Hello, World!").toService))
}
