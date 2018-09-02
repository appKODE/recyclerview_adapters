package ru.rinekri.devfest2018.delegates

import android.support.v7.widget.RecyclerView
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import com.hannesdorfmann.adapterdelegates3.AbsListItemAdapterDelegate
import com.hannesdorfmann.adapterdelegates3.ListDelegationAdapter
import ru.rinekri.devfest2018.R
import ru.rinekri.devfest2018.inflate
import ru.rinekri.devfest2018.items.RubberDuckItem
import ru.rinekri.devfest2018.items.common.DisplayableItem
import ru.rinekri.devfest2018.showIcon

class DucksDelegatesAdapter : ListDelegationAdapter<List<DisplayableItem>>() {

  init {
    delegatesManager.addDelegate(RubberDuckDelegate())
  }

  fun setData(items: List<DisplayableItem>) {
    this.items = items
    notifyDataSetChanged()
  }
}

private class RubberDuckDelegate : AbsListItemAdapterDelegate<RubberDuckItem, DisplayableItem, RubberDuckDelegate.ViewHolder>() {

  override fun isForViewType(item: DisplayableItem, items: List<DisplayableItem>, position: Int): Boolean {
    return item is RubberDuckItem
  }

  override fun onCreateViewHolder(parent: ViewGroup): ViewHolder {
    val item = parent.context.inflate(R.layout.item_rubber_duck, parent, false)
    return ViewHolder(item)
  }

  override fun onBindViewHolder(item: RubberDuckItem, viewHolder: ViewHolder, payloads: List<Any>) {
    viewHolder.apply {
      rubberDuckImage.showIcon(item.icon)
    }
  }

  class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
    val rubberDuckImage: ImageView = itemView.findViewById(R.id.rubberDuckImage)
  }
}