package com.test.searchapp.feature_search.presentation.view

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import com.bumptech.glide.load.engine.DiskCacheStrategy
import com.bumptech.glide.load.model.GlideUrl
import com.bumptech.glide.load.resource.bitmap.CenterCrop
import com.bumptech.glide.load.resource.bitmap.RoundedCorners
import com.bumptech.glide.request.RequestOptions
import com.test.searchapp.R
import com.test.searchapp.core.adapter.BaseAdapter
import com.test.searchapp.core.adapter.BaseViewHolder
import com.test.searchapp.core.di.GlideApp
import com.test.searchapp.databinding.ItemTrackBinding
import com.test.searchapp.feature_search.presentation.model.TrackPresentationModel

class TracksAdapter : BaseAdapter<TrackPresentationModel>() {

    private val requestOptions = RequestOptions().transform(
        RoundedCorners(18),
        CenterCrop()
    )

    override val areItemsTheSameCallback: (TrackPresentationModel, TrackPresentationModel) -> Boolean =
        { oldValue, newValue -> oldValue == newValue }

    override val areContentsTheSameCallback: (TrackPresentationModel, TrackPresentationModel) -> Boolean =
        { oldValue, newValue -> oldValue.trackId == newValue.trackId }

    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): BaseViewHolder<TrackPresentationModel> = ViewHolder(
        binding = DataBindingUtil.inflate(
            LayoutInflater.from(parent.context),
            R.layout.item_track,
            parent,
            false
        )
    )

    inner class ViewHolder(
        private val binding: ItemTrackBinding
    ) : BaseViewHolder<TrackPresentationModel>(binding.root) {

        override fun bind(item: TrackPresentationModel, position: Int) {
            GlideApp.with(binding.root)
                .applyDefaultRequestOptions(
                    requestOptions
                )
                .load(GlideUrl(item.trackImageUrl))
                .diskCacheStrategy(DiskCacheStrategy.DATA)
                .placeholder(R.drawable.ic_placeholder)
                .error(R.drawable.ic_error)
                .into(binding.trackImageView)
            binding.trackTitleTextView.text = item.trackTitle
            binding.trackSubtitleTextView.text = item.trackSubtitle
        }

    }

}