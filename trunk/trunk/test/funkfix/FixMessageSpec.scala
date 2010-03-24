package funkfix

import org.scalatest.Spec
import org.scalatest.matchers.ShouldMatchers
import Conversions._


class FixMessageSpec extends Spec with ShouldMatchers {

  describe("Fix message") {

    val logon = new FixMessage(Map(35 -> "A", 11 -> "a", 12 -> "b", 108 -> "30"))
    val nos = new FixMessage(Map(35 -> "D", 38 -> "100", 54 -> "1", 55 -> "EUR/USD", 44 -> "1.251", 40 -> "1"))

    it("should return its tags as Options") {
      logon(11) should be (Some("a"))
      logon(12) should be (Some("b"))
    }

    it("should return None for missing tags") {
      logon(13) should be (None)
    }

    it("should return correct message type") {
      logon.msgType should be (Some(MessageType.Logon))
    }

    it("should return correct message type as a raw string") {
      logon.msgTypeRaw should be (Some("A"))
    }

    it("should allow pattern matching over type") {
      val result = logon.msgType match {
        case Some(MessageType.Logon) => true
        case _ => false
      }
      result should be (true)
    }

    it("should be able to extract one value with conversion") {
      val value = logon extract (Tag.HeartBtInt, toInt)
      value should be (Some(30))
    }

    it("should be able to extract two values with conversion") {
      val value = logon extract (Tag.HeartBtInt -> toInt, Tag.EncryptMethod -> keep)
      value should be ((Some(30), None))
    }

    it("should be able to extract three values at once") {
      val (qty, symbol, price) = nos extract(Tag.OrderQty -> toInt, Tag.Symbol -> keep, Tag.Price -> toDouble)
      qty should be (Some(100))
      symbol should be (Some("EUR/USD"))
      price should be (Some(1.251))
    }

    it("should be able to extract four values at once") {
      val (qty, symbol, price, side) = nos extract(Tag.OrderQty -> toInt, Tag.Symbol -> keep, Tag.Price -> toDouble,
              Tag.Side -> ((x: String) => if(x == "1") "BUY" else "SELL"))
      qty should be (Some(100))
      symbol should be (Some("EUR/USD"))
      price should be (Some(1.251))
      side should be (Some("BUY"))
    }

    it("should be able to extract five values at once") {
      val (qty, symbol, price, side, orderType) = nos extract(
              Tag.OrderQty -> toInt,
              Tag.Symbol -> keep,
              Tag.Price -> toDouble,
              Tag.Side -> ((x: String) => if(x == "1") "BUY" else "SELL"),
              Tag.OrdType -> ((x: String) => if(x == "1") "MARKET" else "LIMIT")
      )
      qty should be (Some(100))
      symbol should be (Some("EUR/USD"))
      price should be (Some(1.251))
      side should be (Some("BUY"))
      orderType should be (Some("MARKET"))
    }

    it("should be able to extract values with no conversion") {
      val value = logon extract (Tag.HeartBtInt, keep)
      value should be (Some("30"))
    }
  }
}