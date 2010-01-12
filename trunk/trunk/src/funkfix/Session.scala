package funkfix

trait Session {

  def setAttribute[T](key: Any, value: T)

  def getAttribute[T](key: Any): T

  def apply[T](key: Any): T = getAttribute(key)

  def sendMessage(msg: FixMessage)

  // TODO filter
}