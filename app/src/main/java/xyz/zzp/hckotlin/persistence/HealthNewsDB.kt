package xyz.zzp.hckotlin.persistence

import android.arch.persistence.room.Database
import android.arch.persistence.room.Room
import android.arch.persistence.room.RoomDatabase
import android.content.Context
import xyz.zzp.hckotlin.data.VO.HealthNewsVO
import xyz.zzp.hckotlin.persistence.daos.HealthNewsDao

@Database(entities = arrayOf(HealthNewsVO::class), version = 1, exportSchema = false)
abstract class HealthNewsDB : RoomDatabase() {

    abstract fun healthNewsDao(): HealthNewsDao

    companion object {
        private val DB_NAME = "Health_Care.DB"
        private var INSTANCE: HealthNewsDB? = null

        fun getDatabase(context: Context): HealthNewsDB {
            if (INSTANCE == null) {
                INSTANCE = Room.databaseBuilder(context, HealthNewsDB::class.java, DB_NAME)
                        .allowMainThreadQueries()
                        .build()
            }
            val i = INSTANCE
            return i!!
        }
    }
}

