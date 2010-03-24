package funkfix
import Tags._
import collection.mutable.ListBuffer

class FixMessage(tags: List[(Int, String)]) {

  def apply(tag: Int) = findTag(tag)

  private def findTag(tag: Int) = tags.find(x => x._1 == tag) match {
    case Some((tag, value)) => Some(value)
    case _ => None
  }

  def msgTypeRaw = apply(MsgType)

  def msgType = {
    msgTypeRaw match {
      case Some(value) => MessageType valueOf value
      case _ => None
    }
  }

  def filter (p : ((Int, String)) => Boolean) : List[(Int, String)] = tags filter p

  def foreach(f : ((Int, String)) => Unit) : Unit = tags foreach f

  def extract[A](tag: Int, converter: String => A) = {
    apply(tag) match {
      case Some(value) => Some(converter(value))
      case _ => None
    }
  }

  def extract[A, B](a: (Int, String => A), b: (Int, String => B)) = {
    (extract[A](a._1, a._2), extract[B](b._1, b._2))
  }

  def extract[A, B, C](a: (Int, String => A), b: (Int, String => B), c: (Int, String => C)) = {
    (extract[A](a._1, a._2), extract[B](b._1, b._2), extract[C](c._1, c._2))
  }

  def extract[A, B, C, D](a: (Int, String => A), b: (Int, String => B), c: (Int, String => C), d: (Int, String => D)) = {
    (extract[A](a._1, a._2), extract[B](b._1, b._2), extract[C](c._1, c._2), extract[D](d._1, d._2))
  }

  def extract[A, B, C, D, E](a: (Int, String => A),
                             b: (Int, String => B),
                             c: (Int, String => C),
                             d: (Int, String => D),
                             e: (Int, String => E)) = {
    (extract[A](a._1, a._2), extract[B](b._1, b._2), extract[C](c._1, c._2), extract[D](d._1, d._2), extract[E](e._1, e._2))
  }

  def extract[A, B, C, D, E, F](a: (Int, String => A),
                             b: (Int, String => B),
                             c: (Int, String => C),
                             d: (Int, String => D),
                             e: (Int, String => E),
                             f: (Int, String => F)) = {
    (extract[A](a._1, a._2), extract[B](b._1, b._2), extract[C](c._1, c._2), extract[D](d._1, d._2), extract[E](e._1, e._2),
     extract[F](f._1, f._2))
  }

  def extract[A, B, C, D, E, F, G](a: (Int, String => A),
                             b: (Int, String => B),
                             c: (Int, String => C),
                             d: (Int, String => D),
                             e: (Int, String => E),
                             f: (Int, String => F),
                             g: (Int, String => G)) = {
    (extract[A](a._1, a._2),
     extract[B](b._1, b._2),
     extract[C](c._1, c._2),
     extract[D](d._1, d._2),
     extract[E](e._1, e._2),
     extract[F](f._1, f._2),
     extract[G](g._1, g._2))
  }
}

class FixMessageBuilder {
  val buffer = new ListBuffer[(Int, String)]

  def append(tag: (Int, String)) = {
    buffer.append(tag)
    this
  }

  def build = new FixMessage(buffer.toList)
}