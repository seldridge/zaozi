package zaozi

import zaozi.builtin.Bundle

// final case class Ports(private val bundle: Bundle) extends Component(bundle) {}

abstract class Module(using context: Context) { this: Singleton =>
  given ModuleContext = new ModuleContext(this)

  def body: Unit

  def instantiate(using moduleContext: ModuleContext) = new Instance(this)
}

class Instance(module: Module)(using moduleContext: ModuleContext) {}
