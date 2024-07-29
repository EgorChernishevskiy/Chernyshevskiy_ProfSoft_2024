package com.example.lesson8.presentation.main_activity

import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView.Adapter
import androidx.recyclerview.widget.RecyclerView.ViewHolder

class MainAdapter: Adapter<ViewHolder>() {
    companion object {
        private const val JUST_TEXT_ITEM = 0
        private const val BLOCK_WITH_TEXT_ITEM = 1
    }

    private val items = mutableListOf<AdapterItem>()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        return when (viewType) {
            JUST_TEXT_ITEM -> JustTextItemViewHolder(parent)
            BLOCK_WITH_TEXT_ITEM -> BlockWithTextItemViewHolder(parent)
            else -> throw Exception("incorrect view type")
        }
    }

    override fun getItemCount(): Int {
        return items.size
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        when (getItemViewType(position)) {
            JUST_TEXT_ITEM -> (holder as JustTextItemViewHolder).bind(items[position] as JustTextItem)
            BLOCK_WITH_TEXT_ITEM -> (holder as BlockWithTextItemViewHolder).bind(items[position] as BlockWithTextItem)
        }
        holder.itemView.setOnClickListener {
            removeItem(position)
        }
    }

    override fun getItemViewType(position: Int): Int {
        return if (items[position] is JustTextItem) JUST_TEXT_ITEM else BLOCK_WITH_TEXT_ITEM
    }

    fun setItems(items: List<AdapterItem>) {
        this.items.clear()
        this.items.addAll(items)
        notifyDataSetChanged()
    }

    fun removeItem(position: Int) {
        if (position in 0 until items.size) {
            items.removeAt(position)
            notifyItemRemoved(position)
            notifyItemRangeChanged(position, items.size)
        }
    }

    fun addItem(item: AdapterItem) {
        items.add(item)
        notifyItemInserted(items.size - 1)
    }
}