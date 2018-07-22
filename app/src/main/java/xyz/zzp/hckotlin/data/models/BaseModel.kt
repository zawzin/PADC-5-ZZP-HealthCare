package xyz.zzp.hckotlin.data.models

import android.content.Context
import com.google.gson.Gson
import com.jakewharton.retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import xyz.zzp.hckotlin.network.HealthApi
import xyz.zzp.hckotlin.persistence.HealthNewsDB
import java.util.concurrent.TimeUnit

abstract class BaseModel protected constructor(context: Context) {

    protected var mTheApi: HealthApi
    protected var mTheDB: HealthNewsDB

    init {
        val okHttpClient = OkHttpClient.Builder()
                .connectTimeout(60, TimeUnit.SECONDS)
                .writeTimeout(60, TimeUnit.SECONDS)
                .readTimeout(60, TimeUnit.SECONDS)
                .build()

        val retrofit = Retrofit.Builder()
                .baseUrl("http://padcmyanmar.com/padc-5/mm-healthcare/")
                .addConverterFactory(GsonConverterFactory.create(Gson()))
                .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                .client(okHttpClient)
                .build()

        mTheApi = retrofit.create(HealthApi::class.java)
        mTheDB = HealthNewsDB.getDatabase(context)
    }
}