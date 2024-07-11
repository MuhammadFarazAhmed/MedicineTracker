package com.example.medicinetracker.di

import android.app.Application
import android.util.Log
import androidx.room.Room
import com.example.medicinetracker.common.NetworkStatusTracker
import com.example.medicinetracker.db.ProblemsDatabase
import com.example.medicinetracker.db.ProblemDao
import com.example.medicinetracker.domain.repo.HomeRepository
import com.example.medicinetracker.domain.repo.HomeRepositoryImp
import com.example.medicinetracker.domain.usecase.HomeUseCase
import com.example.medicinetracker.domain.usecase.HomeUseCaseImp
import com.example.medicinetracker.vm.HomeViewModel
import com.example.medicinetracker.vm.LoginViewModel
import io.ktor.client.*
import io.ktor.client.engine.android.*
import io.ktor.client.plugins.*
import io.ktor.client.plugins.contentnegotiation.*
import io.ktor.client.plugins.logging.*
import io.ktor.client.plugins.observer.*
import io.ktor.client.request.*
import io.ktor.http.*
import io.ktor.serialization.gson.*
import org.koin.android.ext.koin.androidApplication
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.core.qualifier.named
import org.koin.dsl.module


fun featureModules() = listOf(homeModule, loginModule)

val AppModule = module {
    single {
        NetworkStatusTracker(androidApplication())
    }
}

val LocalModule = module {

    fun provideDataBase(application: Application): ProblemsDatabase {
        return Room.databaseBuilder(application, ProblemsDatabase::class.java, "medicine_database")
            .fallbackToDestructiveMigration().build()
    }

    fun provideDao(dataBase: ProblemsDatabase): ProblemDao {
        return dataBase.problemDao()
    }

    single { provideDataBase(androidApplication()) }
    single { provideDao(get()) }
}

val NetworkModule = module {
    single {
        HttpClient(Android) {

            defaultRequest {
                url {
                    contentType(ContentType.Application.Json)
                    accept(ContentType.Application.Json)
                }
            }

            install(Logging) { level = LogLevel.ALL }

            install(ContentNegotiation) {
                gson {
                    serializeNulls()
                }
            }

            HttpResponseValidator {
                validateResponse {
                    when (it.status.value) {
                        in 300..399 -> {}
                        in 400..499 -> {
                            Log.d("TAG", "${it.status.value}")
                        }

                        in 500..599 -> {}
                    }
                }
                handleResponseExceptionWithRequest { cause: Throwable, request: HttpRequest ->
                    Log.d("TAG", "$cause: $request")
                }
            }

            install(ResponseObserver) {
                onResponse { response ->
                    println("HTTP status: ${response.status.value}")
                }
            }

        }
    }
}

private val loginModule = module {
    viewModel { LoginViewModel() }
}
private val homeModule = module {
    single<HomeRepository> { HomeRepositoryImp(get(), get(), get(), get()) }
    single<HomeUseCase> { HomeUseCaseImp(get()) }
    viewModel { HomeViewModel(get(), get()) }
}

