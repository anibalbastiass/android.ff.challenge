package com.ff.challenge.feature.currencies.ui.list.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.ff.challenge.feature.currencies.databinding.FragmentCurrencyListItemBinding
import com.ff.challenge.feature.currencies.presentation.model.UiCurrencyItem

class CurrencyListAdapter : RecyclerView.Adapter<CurrencyListAdapter.ViewHolder>() {

    lateinit var items: MutableList<UiCurrencyItem>
    lateinit var onClickAction: ClickListener

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val inflater = LayoutInflater.from(parent.context)
        val binding = FragmentCurrencyListItemBinding.inflate(inflater)
        return ViewHolder(binding)
    }

    override fun getItemCount(): Int = items.size

    override fun onBindViewHolder(holder: ViewHolder, position: Int) = holder.bind(items[position])

    inner class ViewHolder(private val binding: FragmentCurrencyListItemBinding) :
        RecyclerView.ViewHolder(binding.root) {
        fun bind(item: UiCurrencyItem) {
            binding.onClickAction = onClickAction
            binding.item = item
        }
    }

    interface ClickListener {
        fun onClickAction(item: UiCurrencyItem)
    }
}