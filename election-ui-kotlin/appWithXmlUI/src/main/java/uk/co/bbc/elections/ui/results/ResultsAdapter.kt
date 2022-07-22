package uk.co.bbc.elections.ui.results

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import uk.co.bbc.elections.databinding.ViewResultItemBinding
import uk.co.bbc.elections.ui.results.ResultsViewState.Loaded.ResultsItemViewData

class ResultsAdapter :
    ListAdapter<ResultsItemViewData, ResultsAdapter.ResultsItemViewHolder>(ResultsDiffCallback) {

    class ResultsItemViewHolder(private val binding: ViewResultItemBinding) :
        RecyclerView.ViewHolder(binding.root) {

        fun bindItem(item: ResultsItemViewData) {
            binding.partyTv.text = item.party
            binding.candidateTv.text = item.candidate
            binding.votesTv.text = item.votes.toString()
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ResultsItemViewHolder {
        val binding =
            ViewResultItemBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return ResultsItemViewHolder(binding)
    }

    override fun onBindViewHolder(holder: ResultsItemViewHolder, position: Int) {
        holder.bindItem(getItem(position))
    }
}

object ResultsDiffCallback : DiffUtil.ItemCallback<ResultsItemViewData>() {
    override fun areItemsTheSame(
        oldItem: ResultsItemViewData,
        newItem: ResultsItemViewData
    ): Boolean = (oldItem === newItem)

    override fun areContentsTheSame(
        oldItem: ResultsItemViewData,
        newItem: ResultsItemViewData
    ): Boolean = (oldItem == newItem)
}
