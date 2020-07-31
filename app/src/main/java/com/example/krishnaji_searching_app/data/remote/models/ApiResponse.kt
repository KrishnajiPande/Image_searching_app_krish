package com.example.krishnaji_searching_app.data.remote.models

import android.os.Parcel
import android.os.Parcelable

//Created by krishnaji

data class ApiResponse(
    val data: ArrayList<DataModel>,
    val success: Boolean,
    val status: Int
) : Parcelable {
    constructor(source: Parcel) : this(
        source.createTypedArrayList(DataModel.CREATOR)!!,
        1 == source.readInt(),
        source.readInt()
    )

    override fun describeContents() = 0

    override fun writeToParcel(dest: Parcel, flags: Int) = with(dest) {
        writeTypedList(data)
        writeInt((if (success) 1 else 0))
        writeInt(status)
    }

    companion object {
        @JvmField
        val CREATOR: Parcelable.Creator<ApiResponse> = object : Parcelable.Creator<ApiResponse> {
            override fun createFromParcel(source: Parcel): ApiResponse = ApiResponse(source)
            override fun newArray(size: Int): Array<ApiResponse?> = arrayOfNulls(size)
        }
    }
}

