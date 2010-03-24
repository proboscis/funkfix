package funkfix


object MessageType extends Enumeration {
  type MessageType = Value

  val Heartbeat = Value("0")
  val TestRequest = Value("1")
  val ResendRequest = Value("2")
  val Reject = Value("3")
  val SequenceReset = Value("4")
  val Logout = Value("5")
  val IOI = Value("6")
  val Advertisement = Value("7")
  val ExecutionReport = Value("8")
  val OrderCancelReject = Value("9")
  val Logon = Value("A")
  val NewOrderSingle = Value("D")
}