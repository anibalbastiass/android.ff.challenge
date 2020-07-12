package com.ff.challenge

import androidx.fragment.app.FragmentActivity
import com.ff.challenge.app.data.cache.FFSharedPreference
import com.ff.challenge.app.data.cache.FFSharedPreferenceFactory
import com.ff.challenge.app.data.repository.CacheRepositoryImpl
import com.ff.challenge.app.domain.repository.CacheRepository
import com.ff.challenge.app.domain.usecase.GetSessionUseCase
import com.ff.challenge.app.domain.usecase.SignOutUseCase
import com.ff.challenge.app.domain.usecase.StoreSessionUseCase
import com.ff.challenge.app.presentation.viewmodel.CacheViewModel
import com.ff.challenge.library.base.data.interceptor.FakeInterceptor
import com.ff.challenge.library.base.di.KotlinViewModelProvider
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import org.kodein.di.Kodein
import org.kodein.di.android.x.AndroidLifecycleScope
import org.kodein.di.generic.bind
import org.kodein.di.generic.instance
import org.kodein.di.generic.scoped
import org.kodein.di.generic.singleton
import retrofit2.Retrofit
import retrofit2.converter.moshi.MoshiConverterFactory

internal const val MODULE_NAME = "App"

val appModule = Kodein.Module("${MODULE_NAME}Module") {

    bind<HttpLoggingInterceptor>() with singleton {
        HttpLoggingInterceptor().apply {
            level = HttpLoggingInterceptor.Level.BODY
        }
    }

    bind<Retrofit.Builder>() with singleton { Retrofit.Builder() }

    bind<OkHttpClient.Builder>() with singleton { OkHttpClient.Builder() }

    bind<OkHttpClient>() with singleton {
        val okHttpClient = instance<OkHttpClient.Builder>()
            .addInterceptor(instance<HttpLoggingInterceptor>())

        if (BuildConfig.FLAVOR.equals("dummy")) {
            okHttpClient.addInterceptor(FakeInterceptor(instance()))
        }

        okHttpClient.build()
    }

    bind<Retrofit>() with singleton {
        instance<Retrofit.Builder>()
            .baseUrl(BuildConfig.GRADLE_API_BASE_URL)
            .addConverterFactory(MoshiConverterFactory.create())
            .client(instance())
            .build()
    }

    bind() from singleton { FFSharedPreference(instance()) }

    bind() from singleton { FFSharedPreferenceFactory.makeSharedPreferenceForFF(instance()) }

    bind<CacheRepository>() with singleton {
        CacheRepositoryImpl(
            instance()
        )
    }

    bind() from singleton { StoreSessionUseCase(instance()) }

    bind() from singleton { GetSessionUseCase(instance()) }

    bind() from singleton { SignOutUseCase(instance()) }

    bind<CacheViewModel>() with scoped<FragmentActivity>(AndroidLifecycleScope).singleton {
        KotlinViewModelProvider.of(context) {
            CacheViewModel(
                instance(),
                instance(),
                instance()
            )
        }
    }
}
