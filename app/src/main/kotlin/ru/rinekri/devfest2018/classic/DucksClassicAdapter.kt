package ru.rinekri.devfest2018.classic

import android.annotation.SuppressLint
import android.graphics.Bitmap
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.squareup.picasso.Picasso
import ru.rinekri.devfest2018.R
import ru.rinekri.devfest2018.inflate
import ru.rinekri.devfest2018.models.Duck
import ru.rinekri.devfest2018.models.DuckSlipper
import ru.rinekri.devfest2018.models.RubberDuck

private const val VIEW_TYPE_RUBBER_DUCK: Int = 1
private const val VIEW_TYPE_SLIPPER_DUCK: Int = 2
private const val VIEW_TYPE_HEADER: Int = 3

class DucksClassicAdapter(
  private val data: List<Duck>,
  private val onDuckClickAction: (Duck) -> Unit
) : RecyclerView.Adapter<RecyclerView.ViewHolder>() {

  override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
    return when (viewType) {
      VIEW_TYPE_RUBBER_DUCK -> {
        val view = parent.context.inflate(R.layout.item_rubber_duck, parent)
        DuckViewHolder(view)
      }
      VIEW_TYPE_SLIPPER_DUCK -> {
        val view = parent.context.inflate(R.layout.item_duck_slipper, parent)
        SlipperViewHolder(view)
      }
      VIEW_TYPE_HEADER -> {
        val view = parent.context.inflate(R.layout.item_header, parent)
        Header(view)
      }
      else -> throw UnsupportedOperationException("view type $viewType without layout")
    }
  }

  override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
    when (holder) {
      is DuckViewHolder -> bindDuckViewHolder(holder, position)
      is SlipperViewHolder -> bindSlipperViewHolder(holder, position)
    }
  }

  @SuppressLint("SetTextI18n")
  private fun bindSlipperViewHolder(holder: SlipperViewHolder, position: Int) {
    val slipper = data[position] as DuckSlipper
    holder.duckSlipperImage.showIcon(slipper.icon)
    holder.duckSlipperSize.text = "Размер: ${slipper.size}"
    holder.clicksHolder.setOnClickListener { onDuckClickAction.invoke(slipper) }
  }

  private fun ImageView.showIcon(icon: String) {
    Picasso.get()
      .load(icon)
      .config(Bitmap.Config.ARGB_4444)
      .fit()
      .centerCrop()
      .noFade()
      .placeholder(R.drawable.duck_stub)
      .into(this)
  }

  private fun bindDuckViewHolder(holder: DuckViewHolder, position: Int) {
    val duck = data[position] as RubberDuck
    holder.rubberDuckImage.showIcon(duck.icon)
    holder.clicksHolder.setOnClickListener { onDuckClickAction.invoke(duck) }
  }

  override fun getItemViewType(position: Int): Int {
    return when (data[position]) {
      is RubberDuck -> VIEW_TYPE_RUBBER_DUCK
      is DuckSlipper -> VIEW_TYPE_SLIPPER_DUCK
      else -> throw UnsupportedOperationException("unknown type for $position position")
    }
  }

  override fun getItemCount() = data.count()

  class DuckViewHolder(view: View) : RecyclerView.ViewHolder(view) {
    val rubberDuckImage: ImageView = view.findViewById(R.id.rubberDuckImage)
    val clicksHolder: View = view.findViewById(R.id.clicksHolder)
  }

  class SlipperViewHolder(view: View) : RecyclerView.ViewHolder(view) {
    val duckSlipperImage: ImageView = view.findViewById(R.id.duckSlipperImage)
    val duckSlipperSize: TextView = view.findViewById(R.id.duckSlipperSize)
    val clicksHolder: View = view.findViewById(R.id.clicksHolder)
  }

  class Header(view: View) : RecyclerView.ViewHolder(view) {
    val title: ImageView = view.findViewById(R.id.headerTitle)
    val arrow: TextView = view.findViewById(R.id.headerArrow)
  }
}