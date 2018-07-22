package xyz.zzp.hckotlin.data.VO

import com.google.gson.annotations.SerializedName

class AuthorVO{

    @SerializedName("author-healthNewsId")
    var authorId : Int? = null

    @SerializedName("author-name")
    var autorName : String? = null

    @SerializedName("author-picture")
    var authorPicture : String? = null
}