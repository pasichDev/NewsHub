package com.pasichdev.newshub.data.model

import android.os.Parcel
import android.os.Parcelable
import androidx.room.Entity
import androidx.room.PrimaryKey
import com.google.gson.annotations.SerializedName
import javax.annotation.Nullable

@Entity(tableName = "saved")
data class News(
    @field:SerializedName("source") val source: Source?,

    @field:SerializedName("author") @Nullable val author: String? = null,

    @field:SerializedName("title") val title: String,

    @field:SerializedName("description") val description: String? = null,

    @PrimaryKey(autoGenerate = false) @field:SerializedName("url") val url: String,

    @field:SerializedName("urlToImage") val urlToImage: String,

    @field:SerializedName("publishedAt") val publishedAt: String,

    var saveTime: Long = 0
) : Parcelable {

    constructor(parcel: Parcel) : this(
        parcel.readParcelable(Source::class.java.classLoader),
        parcel.readString(),
        parcel.readString()!!,
        parcel.readString(),
        parcel.readString()!!,
        parcel.readString()!!,
        parcel.readString()!!
    )

    override fun writeToParcel(parcel: Parcel, flags: Int) {
        parcel.writeParcelable(source, flags)
        parcel.writeString(author)
        parcel.writeString(title)
        parcel.writeString(description)
        parcel.writeString(url)
        parcel.writeString(urlToImage)
        parcel.writeString(publishedAt)
    }

    override fun describeContents(): Int {
        return 0
    }

    companion object CREATOR : Parcelable.Creator<News> {
        override fun createFromParcel(parcel: Parcel): News {
            return News(parcel)
        }

        override fun newArray(size: Int): Array<News?> {
            return arrayOfNulls(size)
        }
    }
}