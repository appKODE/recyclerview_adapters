package ru.rinekri.devfest2018.delegates

import android.support.v7.widget.RecyclerView
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import com.hannesdorfmann.adapterdelegates3.AbsListItemAdapterDelegate
import com.hannesdorfmann.adapterdelegates3.ListDelegationAdapter
import ru.rinekri.devfest2018.R
import ru.rinekri.devfest2018.inflate
import ru.rinekri.devfest2018.items.*
import ru.rinekri.devfest2018.items.common.DisplayableItem
import ru.rinekri.devfest2018.showIcon

class DucksDelegatesAdapter(
  onSlipperClickAction: (DuckSlipperItem) -> Unit,
  onHeaderClickAction: (HeaderItem) -> Unit
) : ListDelegationAdapter<List<DisplayableItem>>() {

  init {
    delegatesManager.addDelegate(RubberDuckDelegate())
    delegatesManager.addDelegate(DuckSlipperDelegate(onSlipperClickAction))
    delegatesManager.addDelegate(HeaderDelegate(onHeaderClickAction))
  }

  fun setData(items: List<DisplayableItem>) {
    this.items = items
    notifyDataSetChanged()
  }
}

private class RubberDuckDelegate(
) : AbsListItemAdapterDelegate<RubberDuckItem, DisplayableItem, RubberDuckDelegate.ViewHolder>() {

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

private class DuckSlipperDelegate(
  private val onSlipperClickAction: (DuckSlipperItem) -> Unit
) : AbsListItemAdapterDelegate<DuckSlipperItem, DisplayableItem, DuckSlipperDelegate.ViewHolder>() {

  override fun isForViewType(item: DisplayableItem, items: List<DisplayableItem>, position: Int): Boolean {
    return item is DuckSlipperItem
  }

  override fun onCreateViewHolder(parent: ViewGroup): ViewHolder {
    val item = parent.context.inflate(R.layout.item_duck_slipper, parent, false)
    return ViewHolder(item)
  }

  override fun onBindViewHolder(item: DuckSlipperItem, viewHolder: ViewHolder, payloads: List<Any>) {
    viewHolder.apply {
      duckSlipperImage.showIcon(item.icon)
      duckSlipperSize.text = "Размер: ${item.size}"
      clicksHolder.setOnClickListener { onSlipperClickAction.invoke(item) }
    }
  }

  class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
    val duckSlipperImage: ImageView = itemView.findViewById(R.id.duckSlipperImage)
    val duckSlipperSize: TextView = itemView.findViewById(R.id.duckSlipperSize)
    val clicksHolder: View = itemView.findViewById(R.id.clicksHolder)
  }
}

private class HeaderDelegate(
  private val onHeaderClickAction: (HeaderItem) -> Unit
) : AbsListItemAdapterDelegate<HeaderItem, DisplayableItem, HeaderDelegate.ViewHolder>() {

  override fun isForViewType(item: DisplayableItem, items: List<DisplayableItem>, position: Int): Boolean {
    return item is HeaderItem
  }

  override fun onCreateViewHolder(parent: ViewGroup): ViewHolder {
    val item = parent.context.inflate(R.layout.item_header, parent, false)
    return ViewHolder(item)
  }

  override fun onBindViewHolder(item: HeaderItem, viewHolder: ViewHolder, payloads: List<Any>) {
    viewHolder.apply {
      clicksHolder.setOnClickListener { onHeaderClickAction.invoke(item) }
      val arrowRes = if (item.isCollapsed)
        R.drawable.ic_keyboard_arrow_up_black_24dp
      else
        R.drawable.ic_keyboard_arrow_down_black_24dp
      arrow.setImageResource(arrowRes)
      title.setText(item.titleRes)
    }
  }

  class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
    val title: TextView = itemView.findViewById(R.id.headerTitle)
    val arrow: ImageView = itemView.findViewById(R.id.headerArrow)
    val clicksHolder: View = itemView.findViewById(R.id.clicksHolder)
  }
}
