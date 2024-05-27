package zaozi.builtin

import zaozi.{Context, Type}
import zaozi.typeclasses.SerializableToFirrtl
import scala.collection.immutable

enum Signedness {
  case Signed, Unsigned
}

final case class Int(width: BigInt, signedness: Signedness)(using
    context: Context
) extends Type {
  override def key = (width, signedness)
}

trait Bundle extends Type with Product {
  require(
    productIterator.forall {
      _ match {
        case _: Type => true
        case _       => false
      }
    },
    "A bundle must only contain elements of 'zaozi.Type'"
  )

  private[zaozi] val typeMap: immutable.HashMap[String, Type] = {
    immutable.HashMap.from(
      productElementNames.zip(productIterator.map(_.asInstanceOf[Type]))
    )
  }

  override val key =
    productElementNames.zip(productIterator.map(_.asInstanceOf[Type].key)).toSeq

}

object Int {

  given SerializableToFirrtl[Int] with {
    extension (tpe: Int) {
      def toFirrtl: String = {
        tpe.signedness match {
          case Signedness.Signed   => s"SInt<${tpe.width}>"
          case Signedness.Unsigned => s"UInt<${tpe.width}>"
        }
      }
    }
  }

  given SerializableToFirrtl[Bundle] with {
    extension (tpe: Bundle) {
      def toFirrtl: String = ???
    }
  }
}
