package ru.rinekri.devfest2018.common

import android.support.v7.util.DiffUtil
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
    return oldItems[oldItemPosition] == newItems[newItemPosition]
  }

  override fun areContentsTheSame(oldItemPosition: Int, newItemPosition: Int): Boolean {
    return oldItems[oldItemPosition] == newItems[newItemPosition]
  }
}