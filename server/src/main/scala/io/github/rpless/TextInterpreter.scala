package io.github.rpless

object TextInterpreter {
  type Item = (String, String)
  def apply(text: String): Seq[Item] = {
    text.lines.toSeq.foldRight(Seq.empty[Item]) { (line, acc) =>
      matches(line).fold({acc}) {acc :+ _}
    }
  }

  def matches(line: String): Option[Item]= {
    val regex = """(.+?)\s{4,}(.+)""".r
    line match {
      case regex(name, amt) => Some((name, amt))
      case _ => None
    }
  }
}
