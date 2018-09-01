package ru.rinekri.devfest2018.classic

import android.support.v7.widget.RecyclerView
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import ru.rinekri.devfest2018.R
import ru.rinekri.devfest2018.inflate
import ru.rinekri.devfest2018.models.Duck

class DucksClassicCountAdapter(
  private val data: List<Pair<Duck, Int>>,
  private val onCountClickAction: (Pair<Duck, Int>) -> Unit
) : RecyclerView.Adapter<RecyclerView.ViewHolder>() {
  override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
    val view = parent.context.inflate(R.layout.item_duck_count, parent)
    return CountViewHolder(view)
  }

  override fun getItemCount() = data.count()

  override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
    (holder as CountViewHolder).count.apply {
      val item = data[position]
      text = item.second.toString()
      setOnClickListener { onCountClickAction.invoke(item) }
    }
  }

  class CountViewHolder(view: View) : RecyclerView.ViewHolder(view) {
    val count: TextView = view.findViewById(R.id.count)
  }
}
