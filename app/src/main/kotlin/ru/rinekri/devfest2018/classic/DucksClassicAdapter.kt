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
  private val onDuckClickAction: (Duck) -> Unit
) : RecyclerView.Adapter<RecyclerView.ViewHolder>() {


  var data: List<Duck> = emptyList()
    set(value) {
      field = value
      groupedData = data.groupBy { it.javaClass.kotlin }
        .flatMap {
          val titleRes = when (it.key) {
            DuckSlipper::class -> R.string.slippers
            RubberDuck::class -> R.string.rubber_ducks
            else -> throw UnsupportedOperationException("DADADada")
          }
          listOf(FakeDuck(titleRes)).plus(it.value)
        }
    }

  private var groupedData: List<Duck> = emptyList()
  private var collapsedHeaders: MutableSet<Duck> = hashSetOf()

//  private var groupedData: Map<Pair<Class<Duck>, Boolean>, List<Duck>> = emptyMap()
//    set(value) {
//      field = value
//      value.forEach {
//        Timber.tag("DUCK").e("${it.key}: ${it.value}")
//      }
//      notifyDataSetChanged()
//    }

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
      is Header -> bindHeaderViewHolder(holder, position)
      is DuckViewHolder -> bindDuckViewHolder(holder, position)
      is SlipperViewHolder -> bindSlipperViewHolder(holder, position)
    }
  }

  private fun bindHeaderViewHolder(holder: Header, position: Int) {
    val item = groupedData[position] as FakeDuck
    holder.itemView.setOnClickListener {
      if (collapsedHeaders.contains(groupedData[position])) {
//        collapsedHeaders.
      }
      //      TODO: Логика скрытия
    }
//    val res = when(groupedData.ke)
    val arrowRes = if (collapsedHeaders.contains(item))
      R.drawable.ic_keyboard_arrow_up_black_24dp
    else
      R.drawable.ic_keyboard_arrow_down_black_24dp
    holder.arrow.setImageResource(arrowRes)
    holder.title.setText(item.titleRes)
  }

  @SuppressLint("SetTextI18n")
  private fun bindSlipperViewHolder(holder: SlipperViewHolder, position: Int) {
    val slipper = groupedData[position] as DuckSlipper
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
    val duck = groupedData[position] as RubberDuck
    holder.rubberDuckImage.showIcon(duck.icon)
    holder.clicksHolder.setOnClickListener { onDuckClickAction.invoke(duck) }
  }

  override fun getItemViewType(position: Int): Int {
    return when (groupedData[position]) {
      is FakeDuck -> VIEW_TYPE_HEADER
      is RubberDuck -> VIEW_TYPE_RUBBER_DUCK
      is DuckSlipper -> VIEW_TYPE_SLIPPER_DUCK
      else -> throw UnsupportedOperationException("unknown type for $position position")
    }
  }

  override fun getItemCount() = groupedData.count()
//  override fun getItemCount() = groupedData.keys.count() + groupedData.values.count()

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
    val title: TextView = view.findViewById(R.id.headerTitle)
    val arrow: ImageView = view.findViewById(R.id.headerArrow)
  }
}

private class FakeDuck(var titleRes: Int) : Duck