package com.example.githhubufaber.adapters

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.example.githhubufaber.adapters.GithubRepoAdapter.*
import com.example.githhubufaber.databinding.EachRepositoryViewBinding
import com.example.githhubufaber.network.models.GithubModelItem


class GithubRepoAdapter() : ListAdapter<GithubModelItem, GithubItemViewHolder>(DiffCallback) {


    class GithubItemViewHolder(private var binding: EachRepositoryViewBinding) :
        RecyclerView.ViewHolder(binding.root) {

        fun bind(githubModelItem: GithubModelItem) {

            binding.property = githubModelItem
            binding.executePendingBindings()

        }


        companion object {
            fun from(parent: ViewGroup): GithubItemViewHolder {
                val layoutInflater = LayoutInflater.from(parent.context)
                val binding = EachRepositoryViewBinding.inflate(layoutInflater, parent, false)
                return GithubItemViewHolder(binding)
            }
        }


    }

    companion object DiffCallback : DiffUtil.ItemCallback<GithubModelItem>() {
        override fun areItemsTheSame(oldItem: GithubModelItem, newItem: GithubModelItem): Boolean {
            // === which is true when object references are same
            return oldItem === newItem

        }


        override fun areContentsTheSame(
            oldItem: GithubModelItem,
            newItem: GithubModelItem
        ): Boolean {
            return oldItem.id == newItem.id
        }


    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): GithubItemViewHolder {
        return GithubItemViewHolder.from(parent)
    }

    override fun onBindViewHolder(holder: GithubItemViewHolder, position: Int) {
        val githubModelItem = getItem(position)
        holder.bind(githubModelItem)
    }




}