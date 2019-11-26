package com.example.cash_register.greedo_layout_tools

import android.content.Context
import android.graphics.BitmapFactory
import android.provider.SyncStateContract
import com.squareup.picasso.Picasso
import android.view.ViewGroup
import android.widget.ImageView.ScaleType
import android.support.v7.widget.RecyclerView
import android.widget.ImageView
import com.fivehundredpx.greedolayout.GreedoLayoutSizeCalculator.SizeCalculatorDelegate


class PhotosAdapter(private val mContext: Context) : RecyclerView.Adapter<PhotosAdapter.PhotoViewHolder>(),
    SizeCalculatorDelegate {

    private val mImageResIds = Constants.IMAGES
    private val mImageAspectRatios = DoubleArray(Constants.IMAGES.size)

    override fun aspectRatioForIndex(index: Int): Double {
        // Precaution, have better handling for this in greedo-layout
        return if (index >= itemCount) 1.0 else mImageAspectRatios[getLoopedIndex(index)]
    }

    inner class PhotoViewHolder(val mImageView: ImageView) : RecyclerView.ViewHolder(mImageView)

    init {
        calculateImageAspectRatios()
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): PhotoViewHolder {
        val imageView = ImageView(mContext)
        imageView.scaleType = ImageView.ScaleType.CENTER_CROP

        imageView.layoutParams = ViewGroup.LayoutParams(
            ViewGroup.LayoutParams.MATCH_PARENT,
            ViewGroup.LayoutParams.MATCH_PARENT
        )
        return PhotoViewHolder(imageView)
    }

    override fun onBindViewHolder(holder: PhotoViewHolder, position: Int) {
        Picasso.with(mContext)
            .load(mImageResIds[getLoopedIndex(position)])
            .into(holder.mImageView)
    }

    override fun getItemCount(): Int {
        return IMAGE_COUNT
    }

    private fun calculateImageAspectRatios() {
        val options = BitmapFactory.Options()
        options.inJustDecodeBounds = true

        for (i in mImageResIds.indices) {
            BitmapFactory.decodeResource(mContext.resources, mImageResIds[i], options)
            mImageAspectRatios[i] = options.outWidth / options.outHeight.toDouble()
        }
    }

    private fun getLoopedIndex(index: Int): Int {
        return index % Constants.IMAGES.size // wrap around
    }

    companion object {
        private val IMAGE_COUNT = 500 // number of images adapter will show
    }
}