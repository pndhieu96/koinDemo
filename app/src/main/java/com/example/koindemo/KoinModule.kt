package com.example.koindemo

import com.google.gson.FieldNamingPolicy
import com.google.gson.Gson
import com.google.gson.GsonBuilder
import okhttp3.OkHttpClient
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class KoinModule {
    companion object {
        val viewModelModule = module {
            viewModel {
                BookViewModel(get())
            }
        }

        val repositoryModule = module {
            single {
                BookRepository(get())
            }
        }

        val apiModule = module {
            fun provideUseApi(retrofit: Retrofit): ApiService {
                return retrofit.create(ApiService::class.java)
            }

            single { provideUseApi(get()) }
        }

        val retrofitModule = module {
            fun provideGson(): Gson {
                return GsonBuilder().setFieldNamingPolicy(FieldNamingPolicy.IDENTITY).create()
            }

            fun provideHttpClient(): OkHttpClient {
                val okHttpClientBuilder = OkHttpClient.Builder()
                return okHttpClientBuilder.build()
            }

            fun provideRetrofit(factory: Gson, client: OkHttpClient): Retrofit {
                return Retrofit.Builder()
                    .baseUrl("https://www.googleapis.com/")
                    .addConverterFactory(GsonConverterFactory.create(factory))
                    .client(client)
                    .build()
            }

            single { provideGson() }
            single { provideHttpClient() }
            single { provideRetrofit(get(), get()) }
        }
    }
}