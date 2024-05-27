package zaozi

sealed trait Expression {
  def tpe: Type
}

class SubField(val tpe: Type) extends Expression

class SubIndex(val tpe: Type) extends Expression
