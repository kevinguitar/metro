@MetaContribution
@ContributesBinding(Unit::class)
annotation class ContributesBindingToUnit

@MetaContribution
@ContributesBinding(Unit::class, binding = binding<Config<*>>())
annotation class ContributesConfigToUnit

interface Config<T> {
  val value: T
}

@ContributesBindingToUnit
@Inject
class StringConfig : Config<String> {
  override val value: String get() = "str"
}

@ContributesConfigToUnit
@Inject
class IntConfig : Config<Int> {
  override val value: Int get() = 42
}

@GraphExtension(Unit::class)
interface UnitGraph {
  val stringConfig: Config<String>
  val intConfig: Config<*>
}

@DependencyGraph
interface AppGraph {
  val unitGraph: UnitGraph
}

fun box(): String {
  val unitGraph = createGraph<AppGraph>().unitGraph
  assertEquals(unitGraph.stringConfig.value, "str")
  assertEquals(unitGraph.intConfig.value, 42)
  return "OK"
}
