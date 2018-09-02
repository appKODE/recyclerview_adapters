package ru.rinekri.devfest2018.delegates

import android.support.v7.widget.LinearLayoutManager
import android.support.v7.widget.RecyclerView
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.LinearLayout
import android.widget.TextView
import com.hannesdorfmann.adapterdelegates3.AbsListItemAdapterDelegate
import com.hannesdorfmann.adapterdelegates3.AdapterDelegatesManager
import com.hannesdorfmann.adapterdelegates3.ListDelegationAdapter
import ru.rinekri.devfest2018.R
import ru.rinekri.devfest2018.inflate
import ru.rinekri.devfest2018.items.*
import ru.rinekri.devfest2018.items.common.DisplayableItem
import ru.rinekri.devfest2018.showIcon

class DucksDelegatesAdapter(
  onDuckCountClickAction: (DuckCountItem) -> Unit,
  onSlipperClickAction: (DuckSlipperItem) -> Unit,
  onAdvertClickAction: (AdvertItem) -> Unit,
  onHeaderClickAction: (HeaderItem) -> Unit
) : ListDelegationAdapter<List<DisplayableItem>>() {

  init {
    delegatesManager.addDelegate(RubberDuckDelegate(onDuckCountClickAction))
    delegatesManager.addDelegate(DuckSlipperDelegate(onSlipperClickAction))
    delegatesManager.addDelegate(AdvertDelegate(onAdvertClickAction))
    delegatesManager.addDelegate(HeaderDelegate(onHeaderClickAction))
  }

  fun setData(items: List<DisplayableItem>) {
    this.items = items
    notifyDataSetChanged()
  }
}

private class RubberDuckDelegate(
  private val onDuckCountClickAction: (DuckCountItem) -> Unit
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
      val context = itemView.context
      rubberDuckImage.showIcon(item.icon)
      val manager = AdapterDelegatesManager<List<DisplayableItem>>()
        .addDelegate(DuckCountDelegate(onDuckCountClickAction))
      rubberDuckCounts.adapter = ListDelegationAdapter<List<DisplayableItem>>(manager).apply {
        items = item.counts
        notifyDataSetChanged()
      }
      rubberDuckCounts.layoutManager = LinearLayoutManager(context, LinearLayout.HORIZONTAL, false)
    }
  }

  class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
    val rubberDuckImage: ImageView = itemView.findViewById(R.id.rubberDuckImage)
    val rubberDuckCounts: RecyclerView = itemView.findViewById(R.id.rubberDuckCounts)
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

private class AdvertDelegate(
  private val onAdvertClickAction: (AdvertItem) -> Unit
) : AbsListItemAdapterDelegate<AdvertItem, DisplayableItem, AdvertDelegate.ViewHolder>() {

  override fun isForViewType(item: DisplayableItem, items: List<DisplayableItem>, position: Int): Boolean {
    return item is AdvertItem
  }

  override fun onCreateViewHolder(parent: ViewGroup): ViewHolder {
    val item = parent.context.inflate(R.layout.item_advert, parent, false)
    return ViewHolder(item)
  }

  override fun onBindViewHolder(item: AdvertItem, viewHolder: ViewHolder, payloads: List<Any>) {
    viewHolder.apply {
      advertImage.showIcon(item.icon)
      advertTagline.text = item.tagline
      itemView.setOnClickListener { onAdvertClickAction.invoke(item) }
    }
  }

  class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
    val advertTagline: TextView = itemView.findViewById(R.id.advertTagline)
    val advertImage: ImageView = itemView.findViewById(R.id.advertImage)
  }
}

private class DuckCountDelegate(
  private val onDuckCountClickAction: (DuckCountItem) -> Unit
) : AbsListItemAdapterDelegate<DuckCountItem, DisplayableItem, DuckCountDelegate.ViewHolder>() {

  override fun isForViewType(item: DisplayableItem, items: List<DisplayableItem>, position: Int): Boolean {
    return item is DuckCountItem
  }

  override fun onCreateViewHolder(parent: ViewGroup): ViewHolder {
    val item = parent.context.inflate(R.layout.item_duck_count, parent, false)
    return ViewHolder(item)
  }

  override fun onBindViewHolder(item: DuckCountItem, viewHolder: ViewHolder, payloads: List<Any>) {
    viewHolder.count.apply {
      text = item.count.toString()
      setOnClickListener { onDuckCountClickAction.invoke(item) }
    }
  }

  class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
    val count: TextView = itemView.findViewById(R.id.count)
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
