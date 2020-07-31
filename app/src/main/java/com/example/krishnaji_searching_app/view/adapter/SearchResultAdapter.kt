package com.example.krishnaji_searching_app.view.adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.bumptech.glide.load.engine.DiskCacheStrategy
import com.bumptech.glide.request.RequestOptions
import com.example.krishnaji_searching_app.R
import com.example.krishnaji_searching_app.data.remote.models.ImageListModel


//Created by krishnaji

class SearchResultAdapter(
    context: Context,
    imageList: List<ImageListModel>,
    listener: onListItemSelection
) :
    RecyclerView.Adapter<SearchResultAdapter.ViewHolder>() {
    val mContext: Context = context
    var mImageList: List<ImageListModel> = imageList
    private val listener: onListItemSelection = listener

    inner class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        private val imageView: ImageView = itemView.findViewById(R.id.image_ico)

        fun bind(model: ImageListModel) {
            try {
                if (model.type.contains(mContext.resources.getString(R.string.image))) {
                    val options: RequestOptions = RequestOptions()
                        .centerCrop()
                        .placeholder(R.drawable.ic_default_ico)
                        .error(R.drawable.ic_default_ico)
                    Glide.with(mContext).load(model.link)
                        .apply(options)
                        .diskCacheStrategy(DiskCacheStrategy.ALL)
                        .into(imageView)
                }
            } catch (e: Exception) {
                e.printStackTrace()
            }
            imageView.setOnClickListener {
                listener.onImageClick(mImageList[adapterPosition])
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.adapter_search_layout, parent, false)
        return ViewHolder(view)
    }

    override fun getItemCount(): Int = mImageList.size

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.bind(mImageList[position])
    }

    interface onListItemSelection {
        fun onImageClick(imageListModel: ImageListModel)

    }

}