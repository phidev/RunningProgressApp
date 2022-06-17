package com.phidev.runningprogressapp.list

import android.text.Editable
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.phidev.runningprogressapp.data.entity.Progress
import com.phidev.runningprogressapp.databinding.RvRunningResultsBinding

class ListAdapter : RecyclerView.Adapter<ListAdapter.MyViewHolder>() {
    private var progressList = emptyList<Progress>()


    class MyViewHolder(val binding: RvRunningResultsBinding) :
        RecyclerView.ViewHolder(binding.root)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyViewHolder {
        return MyViewHolder(
            RvRunningResultsBinding.inflate(
                LayoutInflater.from(parent.context),
                parent, false
            )

        )
    }

    override fun onBindViewHolder(holder: MyViewHolder, position: Int) {
        val progressList = progressList[position]
        holder.binding.tvRunningDate.text = progressList.date.toEditable()
        holder.binding.tvRunningDistance.text = progressList.distance.toEditable()
        holder.binding.tvRunningDuration.text = progressList.duration.toEditable()


    }

    override fun getItemCount(): Int = progressList.size

    fun setData(progress: List<Progress>) {
        this.progressList = progress
        notifyDataSetChanged()
    }

    private fun String.toEditable(): Editable = Editable.Factory.getInstance()
        .newEditable(this)
}