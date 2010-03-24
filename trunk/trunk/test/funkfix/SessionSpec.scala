package funkfix

import org.scalatest.Spec
import org.scalatest.matchers.ShouldMatchers


class SessionSpec extends Spec with ShouldMatchers {
  describe("Session agent") {

    it("should be possible to set and get custom attributes") {
      val session = new Session
      session.start
      session ! SetAttribute("A", "B")
      val attr = session !? (500, GetAttribute("A"))
      attr should be (Some(Some("B")))
      session ! Stop
    }

    it("should return None for unknown attributes") {
      val session = new Session
      session.start
      val attr = session !? (500, GetAttribute("A"))
      attr should be (Some(None))
      session ! Stop
    }
  }
}