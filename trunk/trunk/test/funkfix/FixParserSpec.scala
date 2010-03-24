package funkfix

import org.scalatest.Spec
import org.scalatest.matchers.ShouldMatchers

class FixParserSpec extends Spec with ShouldMatchers {
  describe("FixParser") {

    it("should provide correct SOH") {
      println("A" + FixParser.SOH + "B")
    }

  }
}