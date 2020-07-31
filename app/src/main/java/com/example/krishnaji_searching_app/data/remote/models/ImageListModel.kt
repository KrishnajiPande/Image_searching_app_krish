package com.example.krishnaji_searching_app.data.remote.models

import android.os.Parcel
import android.os.Parcelable

//Created by krishnaji

data class ImageListModel(
    val id: String,
    val type: String,
    val link: String

) : Parcelable {
    constructor(source: Parcel) : this(
        source.readString()!!,
        source.readString()!!,
        source.readString()!!
    )

    override fun describeContents() = 0

    override fun writeToParcel(dest: Parcel, flags: Int) = with(dest) {
        writeString(id)
        writeString(type)
        writeString(link)
    }

    companion object {
        @JvmField
        val CREATOR: Parcelable.Creator<ImageListModel> =
            object : Parcelable.Creator<ImageListModel> {
                override fun createFromParcel(source: Parcel): ImageListModel =
                    ImageListModel(source)

                override fun newArray(size: Int): Array<ImageListModel?> = arrayOfNulls(size)
            }
    }
}


