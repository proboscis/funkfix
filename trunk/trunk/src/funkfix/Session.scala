package funkfix

import scala.actors.Actor
import scala.actors.Actor._

abstract sealed class SessionCommand

case class SetAttribute(key: Any, value: Any) extends SessionCommand
case class GetAttribute(key: Any) extends SessionCommand
case object Stop extends SessionCommand

class Session(filter: (String => Either[Error, FixMessage])) extends Actor {

  type Attr = Map[Any, Any]

  private var attrs: Attr = Map.empty

  def act() {
    loop {
      react {
        case SetAttribute(key, value) => attrs += (key -> value)
        case GetAttribute(key) => reply(getAttribute(key))
        case Stop => exit()
      }
    }
  }

  private def getAttribute(key: Any): Option[Any] = attrs.get(key)

  def sendMessage(msg: FixMessage) {}

  // TODO filter
}