package timrapp.breweries.data

import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.converter.moshi.MoshiConverterFactory
import retrofit2.http.GET
import timrapp.breweries.BuildConfig
import timrapp.breweries.R

interface BreweryApiService {
    @GET("breweries?by_state=colorado")
    suspend fun breweryList() : List<Brewery>

    companion object {
        fun get(): BreweryApiService {
            val httpAgent = System.getProperty("http.agent")

            val client = OkHttpClient.Builder()
                .addInterceptor {
                    it.proceed(
                        it.request().newBuilder()
                            .header("Accept", "application/json")
                            .header(
                                "User-Agent",
                                "${R.string.app_name}/${BuildConfig.VERSION_NAME} $httpAgent"
                            )
                            .build()
                    )
                }
                .build()

            val retrofit: Retrofit = Retrofit.Builder()
                .baseUrl("https://api.openbrewerydb.org/")
                .addConverterFactory(MoshiConverterFactory.create())
                .client(client)
                .build()

            return retrofit.create<BreweryApiService>(BreweryApiService::class.java)
        }
    }
}