package xyz.zzp.hckotlin.network

import io.reactivex.Observable
import retrofit2.http.Field
import retrofit2.http.FormUrlEncoded
import retrofit2.http.POST
import xyz.zzp.hckotlin.network.responses.GetHealthResponse

interface HealthApi {

    @FormUrlEncoded
    @POST("GetHealthcareInfo.php")
    fun getHealthNews(@Field("access_token") accessToken: String?) : Observable<GetHealthResponse>
}