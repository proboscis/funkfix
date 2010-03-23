package fix.parser


trait Parser[+A] extends ((String, Int) => Result[A])

sealed class Result[+A]
case class Success[+A](value: A, input: String, index: Int) extends Result[A]
case class Failure(msg: String) extends  Result[Nothing]                      