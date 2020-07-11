package com.ff.challenge.app.di.feature

import com.ff.challenge.BuildConfig

@Suppress("detekt.UnsafeCast")
object FeatureManager {

    private const val featurePackagePrefix = "com.ff.challenge.feature"

    val kodeinModules = BuildConfig.FEATURE_MODULE_NAMES
        .map { "$featurePackagePrefix.${it.replace(":feature:", "")}.FeatureKodeinModule" }
        .map {
            try {
                Class.forName(it).kotlin.objectInstance as KodeinModuleProvider
            } catch (e: ClassNotFoundException) {
                throw ClassNotFoundException("Kodein module class not found $it")
            }
        }
        .map { it.kodeinModule }
}
