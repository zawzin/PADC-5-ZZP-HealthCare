package xyz.zzp.hckotlin.network.responses

import com.google.gson.annotations.SerializedName
import xyz.zzp.hckotlin.data.VO.HealthNewsVO

class GetHealthResponse{

    private var code : Int = 0

    private var message : String? = null

    @SerializedName("healthcare-info")
    private var healthCareInfo : List<HealthNewsVO>? = null

    fun getCode(): Int{
        return code
    }

    fun getMessage(): String?{
        return message
    }

    fun getHealthInfo(): List<HealthNewsVO>{
        if(healthCareInfo == null){
            healthCareInfo = ArrayList<HealthNewsVO>()
        }
        val healthNewsVL = healthCareInfo
        return healthNewsVL!!
    }
}