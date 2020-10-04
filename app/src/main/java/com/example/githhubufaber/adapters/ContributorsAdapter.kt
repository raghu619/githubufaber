package com.example.githhubufaber.adapters

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.example.githhubufaber.databinding.EachContributorViewBinding
import com.example.githhubufaber.databinding.EachRepositoryViewBinding
import com.example.githhubufaber.network.models.ContributorModel
import com.example.githhubufaber.network.models.GithubModelItem


class ContributorsAdapter(val onClickListener: ContributorsAdapter.OnClickListener) :
    ListAdapter<ContributorModel, ContributorsAdapter.ContributorViewHolder>(DiffCallback) {


    class ContributorViewHolder(private var binding: EachContributorViewBinding) :
        RecyclerView.ViewHolder(binding.root) {
        fun bind(contributorModel: ContributorModel) {
            binding.property = contributorModel
            binding.executePendingBindings()
        }

        companion object {
            fun from(parent: ViewGroup): ContributorsAdapter.ContributorViewHolder {
                val layoutInflater = LayoutInflater.from(parent.context)
                val binding = EachContributorViewBinding.inflate(layoutInflater, parent, false)
                return ContributorsAdapter.ContributorViewHolder(binding)
            }
        }


    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ContributorViewHolder {
        return ContributorViewHolder.from(parent)
    }

    override fun onBindViewHolder(holder: ContributorViewHolder, position: Int) {
        val contributorModel = getItem(position)
        holder.itemView.setOnClickListener {
            onClickListener.onClick(contributorModel)
        }
        holder.bind(contributorModel)

    }

    companion object DiffCallback : DiffUtil.ItemCallback<ContributorModel>() {
        override fun areItemsTheSame(
            oldItem: ContributorModel,
            newItem: ContributorModel
        ): Boolean {
            // === which is true when object references are same
            return oldItem === newItem

        }


        override fun areContentsTheSame(
            oldItem: ContributorModel,
            newItem: ContributorModel
        ): Boolean {
            return true
        }


    }

    class OnClickListener(val clickListener: (contributorModel: ContributorModel) -> Unit) {
        fun onClick(contributorModel: ContributorModel) = clickListener(contributorModel)
    }


}