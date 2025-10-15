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

@ContributesBinding(AppScope::class)
@Inject
class LongConfig : Config<Long> {
  override val value: Long get() = 1L
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
