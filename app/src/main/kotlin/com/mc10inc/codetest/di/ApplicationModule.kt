package com.mc10inc.codetest.di

import com.mc10inc.codetest.BuildConfig
import com.mc10inc.codetest.CodeTestApplication
import com.mc10inc.codetest.PreferenceManager
import com.mc10inc.codetest.api.Mc10WebService
import com.squareup.moshi.FromJson
import com.squareup.moshi.Moshi
import com.squareup.moshi.ToJson
import dagger.Module
import dagger.Provides
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import org.threeten.bp.Instant
import retrofit2.Retrofit
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import retrofit2.converter.moshi.MoshiConverterFactory
import timber.log.Timber
import java.util.*
import javax.inject.Singleton


/**
 * TODO
 *
 * @author Adam Stroud &#60;[adam.stroud@gmail.com](mailto:adam.stroud@gmail.com)&#62;
 */
@Module
class ApplicationModule(private val codeTestApplication: CodeTestApplication) {
    @Provides
    @Singleton
    fun provideApplication(): CodeTestApplication {
        return this.codeTestApplication
    }

    @Provides
    @Singleton
    fun providePreferenceManager(application: CodeTestApplication): PreferenceManager {
        return PreferenceManager(application)
    }

    @Provides
    @Singleton
    fun provideMoshi(): Moshi {
        return Moshi.Builder()
                .add(object {
                    @ToJson
                    fun toJson(uuid: UUID) = uuid.toString()

                    @FromJson
                    fun fromJson(uuid: String) = UUID.fromString(uuid)
                })
                .add(object {
                    @ToJson
                    fun toJson(timeZone: TimeZone) = timeZone.id

                    @FromJson
                    fun fromJson(timezone: String) = TimeZone.getTimeZone(timezone)
                })
                .add(object {
                    @ToJson
                    fun toJson(instant: Instant) = instant.toEpochMilli()

                    @FromJson
                    fun fromJson(instant: Long) = Instant.ofEpochMilli(instant)
                })
                .build()
    }

    @Provides
    @Singleton
    fun provideWebService(moshi: Moshi): Mc10WebService {
        val okHttpClientBuilder = OkHttpClient.Builder()

        if (BuildConfig.DEBUG) {
            val logging = HttpLoggingInterceptor { message -> Timber.d(message) }
                    .setLevel(HttpLoggingInterceptor.Level.BODY)

            okHttpClientBuilder.addInterceptor(logging)
        }

        return Retrofit.Builder()
                .baseUrl("https://qa.mc10cloud.com")
                .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                .addConverterFactory(MoshiConverterFactory.create(moshi))
                .client(okHttpClientBuilder.build())
                .build()
                .create(Mc10WebService::class.java)
    }
}
