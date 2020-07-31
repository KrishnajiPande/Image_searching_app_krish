package com.example.krishnaji_searching_app.data.remote.models

import android.os.Parcel
import android.os.Parcelable

//Created by krishnaji

data class DataModel(
    val id: String,
    val title: String,
    val images: ArrayList<ImageListModel>

) : Parcelable {
    constructor(source: Parcel) : this(
        source.readString()!!,
        source.readString()!!,
        ArrayList<ImageListModel>().apply {
            source.readList(
                this as List<ImageListModel>,
                ImageListModel::class.java.classLoader
            )
        }
    )

    override fun describeContents() = 0

    override fun writeToParcel(dest: Parcel, flags: Int) = with(dest) {
        writeString(id)
        writeString(title)
        writeList(!images.isNullOrEmpty() as List<ImageListModel>?)
    }

    companion object {
        @JvmField
        val CREATOR: Parcelable.Creator<DataModel> = object : Parcelable.Creator<DataModel> {
            override fun createFromParcel(source: Parcel): DataModel = DataModel(source)
            override fun newArray(size: Int): Array<DataModel?> = arrayOfNulls(size)
        }
    }
}
