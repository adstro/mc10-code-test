package com.mc10inc.codetest.di;

import com.mc10inc.codetest.BuildConfig;
import com.mc10inc.codetest.CodeTestApplication;
import com.mc10inc.codetest.PreferenceManager;
import com.mc10inc.codetest.api.Mc10WebService;
import com.squareup.moshi.FromJson;
import com.squareup.moshi.Moshi;
import com.squareup.moshi.ToJson;

import org.threeten.bp.Instant;

import java.util.TimeZone;
import java.util.UUID;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;
import okhttp3.OkHttpClient;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory;
import retrofit2.converter.moshi.MoshiConverterFactory;
import timber.log.Timber;

/**
 * TODO
 *
 * @author Adam Stroud &#60;<a href="mailto:adam.stroud@gmail.com">adam.stroud@gmail.com</a>&#62;
 */
@Module
public class ApplicationModule {
    private final CodeTestApplication codeTestApplication;

    public ApplicationModule(CodeTestApplication codeTestApplication) {
        this.codeTestApplication = codeTestApplication;
    }

    @Provides
    @Singleton
    public CodeTestApplication provideApplication(){
        return this.codeTestApplication;
    }

    @Provides
    @Singleton
    public PreferenceManager providePreferenceManager(CodeTestApplication application) {
        return new PreferenceManager(application);
    }

    @Provides
    @Singleton
    public Moshi provideMoshi() {
        return new Moshi.Builder()
                .add(new Object() {
                    @ToJson
                    public String toJson(UUID uuid) {
                        return uuid.toString();
                    }

                    @FromJson
                    public UUID fromJson(String uuid) {
                        return UUID.fromString(uuid);
                    }
                })
                .add(new Object() {
                    @ToJson
                    public String toJson(TimeZone timeZone) {
                        return timeZone.getID();
                    }

                    @FromJson
                    public TimeZone fromJson(String timezone) {
                        return TimeZone.getTimeZone(timezone);
                    }
                })
                .add(new Object() {
                    @ToJson
                    public Long toJson(Instant instant) {
                        return instant.toEpochMilli();
                    }

                    @FromJson
                    public Instant fromJson(Long instant) {
                        return Instant.ofEpochMilli(instant);
                    }
                })
                .build();
    }

    @Provides
    @Singleton
    public Mc10WebService provideWebService(Moshi moshi) {
        final OkHttpClient.Builder okHttpClientBuilder = new OkHttpClient.Builder();

        if (BuildConfig.DEBUG) {
            final HttpLoggingInterceptor logging =
                    new HttpLoggingInterceptor(message -> Timber.d(message)).setLevel(HttpLoggingInterceptor.Level.BODY);

            okHttpClientBuilder.addInterceptor(logging);
        }

        return new Retrofit.Builder()
                .baseUrl("https://qa.mc10cloud.com")
                .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                .addConverterFactory(MoshiConverterFactory.create(moshi))
                .client(okHttpClientBuilder.build())
                .build()
                .create(Mc10WebService.class);
    }
}
