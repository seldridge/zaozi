package zaozi

import scala.collection.mutable

/** Class that stores information that should be shared across a zaozi
  * circuit/package.
  */
private[zaozi] trait Context(name: String) {

  val typeStorage: mutable.HashSet[(Class[Type], Any)] = mutable.HashSet.empty

}

/** Class that stores information about a zaozi module.
  *
  * @param module
  */
private[zaozi] final class ModuleContext(module: Module) {

  val expressions: mutable.HashSet[Component] = mutable.HashSet.empty

}
