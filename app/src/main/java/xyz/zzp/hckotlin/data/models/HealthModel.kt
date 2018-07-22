package xyz.zzp.hckotlin.data.models

import android.arch.lifecycle.LiveData
import android.arch.lifecycle.MutableLiveData
import android.content.Context
import android.util.Log
import android.util.Log.d
import io.reactivex.Observer
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.Disposable
import io.reactivex.schedulers.Schedulers
import xyz.zzp.hckotlin.HealthCareApp
import xyz.zzp.hckotlin.data.VO.HealthNewsVO
import xyz.zzp.hckotlin.network.responses.GetHealthResponse

class HealthModel private constructor(context: Context) : BaseModel(context){
    companion object {
        private var INSTANCE : HealthModel? = null
        fun getInstance() : HealthModel {

            val i = INSTANCE
            return i!!
        }
        fun initHealthModel(context: Context){
            INSTANCE = HealthModel(context)
        }
    }

    fun load(mHealthNewsListLD: MutableLiveData<List<HealthNewsVO>>,mErrorLD: MutableLiveData<String>){

        mTheDB.clearAllTables()

        mTheApi.getHealthNews("b002c7e1a528b7cb460933fc2875e916")
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(object : Observer<GetHealthResponse> {
                    override fun onSubscribe(d: Disposable) {

                    }

                    override fun onNext(getHealthResponse: GetHealthResponse) {
                        if (getHealthResponse.getCode() == 200 ){
                            persistHealthNewsList(getHealthResponse.getHealthInfo())
                        }
                        else
                            mErrorLD.postValue("Network Error!")
                        Log.i("HealthCare","${getHealthResponse.getHealthInfo().size}")
                    }

                    override fun onError(e: Throwable) {
                        Log.i("HealthCare","Error!")
                        mErrorLD.postValue(e.message)
                        mHealthNewsListLD.postValue(mTheDB.healthNewsDao().getAllHealthNews)
                    }

                    override fun onComplete() {
                        mHealthNewsListLD.postValue(mTheDB.healthNewsDao().getAllHealthNews)
                    }
                })
    }

    private fun persistHealthNewsList(healthNewsList: List<HealthNewsVO>){

        val insertedHealthNews = mTheDB.healthNewsDao().insertHealthNews(healthNewsList)
        d(HealthCareApp.TAG,"insertedList :${insertedHealthNews.size}")
        d(HealthCareApp.TAG,"newsCount :${mTheDB.healthNewsDao().getAllHealthNews.size}")
    }
}