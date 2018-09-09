package ru.rinekri.devfest2018.common

import android.support.v7.util.DiffUtil
import ru.rinekri.devfest2018.items.HeaderItem
import ru.rinekri.devfest2018.items.common.DisplayableItem

class DisplayableItemsDiffUtilCallback(
  private val oldItems: List<DisplayableItem>,
  private val newItems: List<DisplayableItem>
) : DiffUtil.Callback() {

  override fun getOldListSize(): Int {
    return oldItems.size
  }

  override fun getNewListSize(): Int {
    return newItems.size
  }

  override fun areItemsTheSame(oldItemPosition: Int, newItemPosition: Int): Boolean {
    val oldItem = oldItems[oldItemPosition]
    val newItem = newItems[newItemPosition]
    return when {
      oldItem is HeaderItem && newItem is HeaderItem -> true
      else -> oldItem == newItem
    }
  }

  override fun areContentsTheSame(oldItemPosition: Int, newItemPosition: Int): Boolean {
    val oldItem = oldItems[oldItemPosition]
    val newItem = newItems[newItemPosition]
    return oldItem == newItem
  }
}