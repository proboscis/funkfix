package funkfix.parser

sealed trait Result[+A]

case class Success[+A](value: String, idx: Int) extends Result[A]
case class Error(msg: String) extends Result[Nothing]

trait StringParser[+A] extends ((String, Int) => Result)


object StringParser {

  implicit def char(str: String) = new StringParser[String] {
    def apply()
  }

}