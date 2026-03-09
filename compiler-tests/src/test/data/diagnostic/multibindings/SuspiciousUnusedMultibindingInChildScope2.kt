// RUN_PIPELINE_TILL: BACKEND
// RENDER_IR_DIAGNOSTICS_FULL_TEXT

// MODULE: lib
class AppReviewLauncher {
  fun launchAppReview() = Unit
}

@ContributesTo(AppScope::class)
interface AppReviewAction {
  @Provides
  @Named("OnFeedLoaded")
  @IntoSet
  fun provideAppReviewAction(): () -> Unit = {
    AppReviewLauncher().launchAppReview()
  }
}

// MODULE: feed
abstract class FeedScope private constructor()

@GraphExtension(FeedScope::class)
interface FeedGraph {
  @Named("OnFeedLoaded")
  val onFeedLoadedActions: Set<() -> Unit>
}

// MODULE: main(lib, feed)
@DependencyGraph(AppScope::class)
interface AppGraph {
  val feedGraph: FeedGraph
}
