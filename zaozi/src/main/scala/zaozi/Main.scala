package zaozi

import zaozi.builtin.{Bundle, Int, Signedness}

object UserDefined {
  given Context("Foo")

  val int42 = Int(42, Signedness.Unsigned)

  object Bar extends Module {
    def body = {}
  }

  object Foo extends Module {
    override def body = {
      val bar = Bar.instantiate
    }
  }
}

object Main {
  import UserDefined._

  def main(args: Array[String]): Unit = {
    println(UserDefined.int42.toFirrtl)

    println("你好凿子")
  }

}
