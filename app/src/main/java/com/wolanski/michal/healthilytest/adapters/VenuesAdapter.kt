package com.wolanski.michal.healthilytest.adapters

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.wolanski.michal.healthilytest.databinding.ResultsItemBinding
import com.wolanski.michal.healthilytest.entities.Venue

class VenuesAdapter :
    ListAdapter<Venue, VenuesAdapter.VenueViewHolder>(VenueDiffCallback()) {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): VenueViewHolder {
        val layoutInflater = LayoutInflater.from(parent.context)
        val binding = ResultsItemBinding.inflate(layoutInflater, parent, false)

        return VenueViewHolder(binding)
    }

    override fun onBindViewHolder(viewHolder: VenueViewHolder, position: Int) {
        viewHolder.bind(getItem(position))
    }

    fun addData(venues: List<Venue>) {
        submitList(venues)
    }

    class VenueViewHolder(private val binding: ResultsItemBinding) :
        RecyclerView.ViewHolder(binding.root) {

        fun bind(venue: Venue) {
            binding.name.text = venue.name
            binding.address.text = venue.location.address

            Glide.with(binding.root)
                .load(venue.categories[0].icon.prefix + "64" + venue.categories[0].icon.suffix)
                .into(binding.icon)
        }
    }

    class VenueDiffCallback : DiffUtil.ItemCallback<Venue>() {
        override fun areItemsTheSame(oldItem: Venue, newItem: Venue): Boolean {
            return newItem === oldItem
        }

        override fun areContentsTheSame(oldItem: Venue, newItem: Venue): Boolean {
            return newItem.name == oldItem.name
                    && newItem.location.address == oldItem.location.address
                    && newItem.categories[0].icon.prefix == oldItem.categories[0].icon.prefix
                    && newItem.categories[0].icon.suffix == oldItem.categories[0].icon.suffix
        }
    }
}