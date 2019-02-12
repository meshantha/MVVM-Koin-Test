package com.kalum.monese.rockets.ui.adapter

import android.view.ViewGroup
import androidx.databinding.ViewDataBinding
import androidx.recyclerview.widget.RecyclerView
import com.kalum.monese.rockets.BR
import com.kalum.monese.rockets.data.local.entity.RocketItem
import com.kalum.monese.rockets.R
import com.kalum.monese.rockets.util.bind

class RocketsAdapter(private val itemClick: (Any) -> Unit) :
        RecyclerView.Adapter<RocketsAdapter.ViewHolder>() {

    var rocketList: List<RocketItem> = ArrayList()
        set(value) {
            field = value
            notifyDataSetChanged()
        }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val binding = parent.bind<ViewDataBinding>(R.layout.rocket_item)
        return ViewHolder(binding, itemClick)
    }

    override fun getItemCount() = rocketList.size


    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.bind(rocketList[position])
    }

    class ViewHolder(
            private val binding: ViewDataBinding,
            private val itemClick: (Any) -> Unit
    ) : RecyclerView.ViewHolder(binding.root) {

        fun bind(view: Any) {

            itemView.setOnClickListener {
                itemClick(view)
            }
            with(binding) {
                setVariable(BR.model, view)
                executePendingBindings()
            }

        }
    }
}