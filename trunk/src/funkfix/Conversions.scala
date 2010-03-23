package fix


object Conversions {

  val keep = (x: String) => x
  val toInt = (x: String) => x.toInt
  val toLong = (x: String) => x.toLong
  val toBoolean = (x: String) => x == "Y"
  val toDouble = (x: String) => x.toDouble
}