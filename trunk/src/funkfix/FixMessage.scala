package funkfix
import Tag._


class FixMessage(tags: Map[Int, String]) {

  def apply(tag: Int) = tags get tag

  def msgTypeRaw = apply(MsgType)

  def msgType = {
    val raw = msgTypeRaw
    if(raw.isDefined) MessageType valueOf raw.get else None
  }

  def filter (p : ((Int, String)) => Boolean) : Map[Int, String] = tags filter p

  def foreach(f : ((Int, String)) => Unit) : Unit = tags foreach f


  def extract[A](tag: Int, converter: String => A) = {
    val value = this(tag)
    if(value.isDefined) Some(converter(value.get)) else None
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
    (extract[A](a._1, a._2), extract[B](b._1, b._2), extract[C](c._1, c._2), extract[D](d._1, d._2), extract[E](e._1, e._2),
     extract[F](f._1, f._2), extract[G](g._1, g._2))
  }
}