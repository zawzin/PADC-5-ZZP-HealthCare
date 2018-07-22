package xyz.zzp.hckotlin

import android.app.Application
import xyz.zzp.hckotlin.data.models.HealthModel

class HealthCareApp : Application() {
    companion object {
        const val TAG : String = "Health_Care"
    }

    override fun onCreate() {
        super.onCreate()
        HealthModel.initHealthModel(applicationContext)
    }
}