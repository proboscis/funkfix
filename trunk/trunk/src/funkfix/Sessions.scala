package funkfix

abstract sealed class SessionEvent

case object New extends SessionEvent
case object Established extends SessionEvent


object Sessions {
  type SessionFunc = (Session) => Unit
  type MsgFunc = (Session, Iterable[FixMessage]) => Unit

  def subscribeForEvents(functions: (SessionEvent, SessionFunc)*) = {}

  def subscribeForMessages(func: MsgFunc) = {}
}