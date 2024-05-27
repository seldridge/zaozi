package zaozi

trait Readable[A] {
  extension (a: A) def read(): A
}

trait Writable[A] {
  extension (a: A) def write(): {}
}

/** The root class of all types. */
trait Type(using context: Context) {
  def key: Any
}
