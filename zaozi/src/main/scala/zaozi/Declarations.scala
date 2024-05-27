package zaozi

import scala.language.dynamics
import zaozi.builtin.Bundle

trait Component(a: Type)(using moduleContext: ModuleContext) extends Dynamic {

  def selectDynamic(name: String) = a match {
    case bundle: Bundle => new SubField(bundle.typeMap(name))
    case _              => ???
  }

  def getType: Type = a

}
