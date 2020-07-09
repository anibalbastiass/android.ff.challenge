package com.ff.challenge.app.feature

import org.kodein.di.Kodein

interface KodeinModuleProvider {

    val kodeinModule: Kodein.Module
}
