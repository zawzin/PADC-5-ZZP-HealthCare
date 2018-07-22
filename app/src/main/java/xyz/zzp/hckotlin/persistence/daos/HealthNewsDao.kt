package xyz.zzp.hckotlin.persistence.daos

import android.arch.lifecycle.LiveData
import android.arch.lifecycle.MutableLiveData
import android.arch.persistence.room.Dao
import android.arch.persistence.room.Insert
import android.arch.persistence.room.OnConflictStrategy
import android.arch.persistence.room.Query
import xyz.zzp.hckotlin.data.VO.HealthNewsVO

@Dao
interface HealthNewsDao{
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertHealthNews(healthNews: List<HealthNewsVO>): LongArray

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertAHealthNews(aHealthNews: HealthNewsVO) : Long

    @get:Query("SELECT * FROM healthnews")
    val getAllHealthNews: List<HealthNewsVO>

    @Query("SELECT * FROM healthnews")
    fun getHealthNewsLD() : LiveData<List<HealthNewsVO>>
}