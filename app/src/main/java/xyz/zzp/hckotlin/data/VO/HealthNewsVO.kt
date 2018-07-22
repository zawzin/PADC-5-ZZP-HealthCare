package xyz.zzp.hckotlin.data.VO

import android.arch.persistence.room.ColumnInfo
import android.arch.persistence.room.Embedded
import android.arch.persistence.room.Entity
import android.arch.persistence.room.PrimaryKey
import com.google.gson.annotations.SerializedName

@Entity(tableName = "healthnews")
class HealthNewsVO {

    @PrimaryKey(autoGenerate = true)
    var autoId: Long = 0

    @SerializedName("id")
    @ColumnInfo(name = "health_news_id")
    var healthNewsId : Int? = 0

    var title : String? = null

    var image : String? = null

    @Embedded
    var author : AuthorVO? = null
    get() = if (field == null) AuthorVO() else field

    @SerializedName("short-description")
    var shortDesc : String? = null

    @SerializedName("published-date")
    var publishedDate : String? = null

    @SerializedName("complete-url")
    var completeUrl : String? = null

    @SerializedName("info-type")
    var infoType : String? = null

}