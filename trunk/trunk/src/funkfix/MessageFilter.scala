package funkfix


trait MessageFilter[A, B] extends ((A) => Option[B])

class FilterChain[A, Z]() {

  class ChainElement[A, B, C](next: C) extends

}