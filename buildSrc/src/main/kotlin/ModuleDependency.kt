import kotlin.reflect.full.memberProperties

private const val FEATURE_PREFIX = ":feature:"

// "Module" means "project" in terminology of Gradle API. To be specific each "Android module" is a Gradle "subproject"
@Suppress("unused")
object ModuleDependency {
    // All consts are accessed via reflection
    const val APP = ":app"
    const val FEATURE_AUTH = ":feature:auth"
    const val FEATURE_CURRENCIES = ":feature:currencies"
    const val LIBRARY_BASE = ":library:base"
    const val LIBRARY_TEST_UTILS = ":library:test_utils"

    fun getAllModules() = ModuleDependency::class.memberProperties
        .filter { it.isConst }
        .map { it.getter.call().toString() }
        .toSet()

    fun getDynamicFeatureModules() = getAllModules()
        .filter { it.startsWith(FEATURE_PREFIX) }
        .toSet()
}
