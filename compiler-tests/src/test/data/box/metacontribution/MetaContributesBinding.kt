@MetaContribution
@ContributesBinding(AppScope::class)
annotation class ContributesBindingToApp

@MetaContribution
@ContributesBinding(AppScope::class, binding = binding<Config<*>>())
annotation class ContributesConfigToApp

interface Config<T> {
  val value: T
}

@ContributesBindingToApp
@Inject
class StringConfig : Config<String> {
  override val value: String get() = "str"
}

@ContributesConfigToApp
@Inject
class IntConfig : Config<Int> {
  override val value: Int get() = 42
}

@DependencyGraph(AppScope::class)
interface AppGraph {
  val stringConfig: Config<String>
  val intConfig: Config<*>
}

fun box(): String {
  val graph = createGraph<AppGraph>()
  assertEquals(graph.stringConfig.value, "str")
  assertEquals(graph.intConfig.value, 42)
  return "OK"
}
