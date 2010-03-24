package funkfix

sealed case class Error(tag: Int, text: String)