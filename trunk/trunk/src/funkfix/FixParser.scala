package funkfix

object FixParser extends (String => Either[Error, FixMessage]) {
  val SOH: Char = '\001'

  def apply(x: String) : Either[Error, FixMessage] = {
    require(x != null, "Message should not be null")
    parse(x, 0, new StringBuilder, new FixMessageBuilder)
  }

  private def parse(x: String, idx: Int, sb: StringBuilder, mb: FixMessageBuilder) = {
    if(idx >= x.length) Right(mb.build)
    else {
    }
  }

  private def readNextTag(x: String, idx: Int) : Either[Error, (Int, String)] = {
    if(idx >= x.length) null
    else {
      try {

      }
      catch {
        case e: MalformedMessageException => Left(Error())   
      }
    }
  }

  private class MalformedMessageException(msg: String) extends Exception

  private def readTag(x: String, from: Int) : Option[(String, Int)] = {
    if(idx >= from.length) {
      None
    }
    else {
      Some(takeUntil(x, from, '='))
    }
  }

  private def readValue(x: String, from: Int, tag: String) : Option[(String, Int)] = {
    if(idx >= from.length) {
      throw new MalformedMessageException("Missing value for tag " + tag)
    }
    else {
      Some(takeUntil(x, from, SOH))
    }
  }

  private case class CouldNotReadUntil extends Exception

  private def takeUntil(x: String, from: Int, until: Char) : (String, Int) = {

    def readUntil(idx: Int) {
      if(idx == x.length) throw new CouldNotReadUntil
      else {
        val ch = x.charAt(idx)
        if(ch == until) (x.substring(from, idx - 1), idx - 1)
        else readUntil(idx + 1)
      }
    } 

    readUntil(from)
  }
}