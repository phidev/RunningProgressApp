package com.phidev.runningprogressapp.list

import android.content.Context
import android.text.Editable
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.google.android.material.dialog.MaterialAlertDialogBuilder
import com.phidev.runningprogressapp.R
import com.phidev.runningprogressapp.data.entity.Progress
import com.phidev.runningprogressapp.databinding.RvRunningResultsBinding

class ListAdapter(val context: Context) : RecyclerView.Adapter<ListAdapter.MyViewHolder>() {
    private var progressList = mutableListOf<Progress>()

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
        holder.binding.buttonDelete.setOnClickListener {
            deleteAlertDialog(position)
        }
    }

    override fun getItemCount(): Int = progressList.size

    fun setData(progress: List<Progress>) {
        this.progressList = progress as MutableList<Progress>
        notifyDataSetChanged()
    }

    private fun deleteAlertDialog(position: Int) {
        val showDialog = MaterialAlertDialogBuilder(context)
        showDialog.setTitle(R.string.delete_alert_title)
        showDialog.setIcon(R.drawable.ic_baseline_delete_24)
        showDialog.setMessage(R.string.delete_alert_question)
        showDialog.setPositiveButton(R.string.delete_alert_button_positive) { _, _ ->
            progressList.removeAt(position)
            notifyItemRemoved(position)
            notifyItemRangeChanged(position, progressList.size)
        }
        showDialog.setNegativeButton(R.string.delete_alert_button_negative) { _, _ -> }
        showDialog.show()
    }

    private fun String.toEditable(): Editable = Editable.Factory.getInstance()
        .newEditable(this)
}