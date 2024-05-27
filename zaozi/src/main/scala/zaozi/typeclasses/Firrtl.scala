package zaozi.typeclasses

trait SerializableToFirrtl[A] {
  extension (a: A) def toFirrtl: String
}
