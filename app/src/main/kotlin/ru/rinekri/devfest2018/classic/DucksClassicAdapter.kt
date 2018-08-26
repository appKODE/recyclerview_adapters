package ru.rinekri.devfest2018.classic

import android.annotation.SuppressLint
import android.graphics.Bitmap
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.squareup.picasso.Picasso
import ru.rinekri.devfest2018.DuckMockData
import ru.rinekri.devfest2018.R
import ru.rinekri.devfest2018.inflate
import ru.rinekri.devfest2018.models.Advert
import ru.rinekri.devfest2018.models.Duck
import ru.rinekri.devfest2018.models.DuckSlipper
import ru.rinekri.devfest2018.models.RubberDuck

private const val VIEW_TYPE_RUBBER_DUCK: Int = 1
private const val VIEW_TYPE_SLIPPER_DUCK: Int = 2
private const val VIEW_TYPE_HEADER: Int = 3
private const val VIEW_TYPE_ADVERT: Int = 4
private const val ADVERTS_COUNT = 1

class DucksClassicAdapter(
  private val onDuckClickAction: (Duck) -> Unit,
  private val onAdvertClickAction: (Advert) -> Unit
) : RecyclerView.Adapter<RecyclerView.ViewHolder>() {

  var data: List<Duck> = emptyList()
    set(value) {
      field = value
      internalData = data.groupBy { it.javaClass.kotlin }
        .flatMap { groupedDucks ->
          val titleRes = when (groupedDucks.key) {
            DuckSlipper::class -> R.string.slippers
            RubberDuck::class -> R.string.rubber_ducks
            else -> R.string.mixed_ducks
          }
          groupedDucks.value.let { listOf(FakeDuck(titleRes, it)).plus(it) }
        }
        .toMutableList()
    }

  private val advert = DuckMockData.adverts.orEmpty().shuffled().first()

  private var internalData: MutableList<Duck> = mutableListOf()
  private var collapsedHeaders: MutableSet<Duck> = hashSetOf()

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
        HeaderViewHolder(view)
      }
      VIEW_TYPE_ADVERT -> {
        val view = parent.context.inflate(R.layout.item_advert, parent)
        AdvertViewHolder(view)
      }
      else -> throw UnsupportedOperationException("view type $viewType without ViewHolder")
    }
  }

  override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
    when (holder) {
      is HeaderViewHolder -> bindHeaderViewHolder(holder, position)
      is DuckViewHolder -> bindDuckViewHolder(holder, position)
      is SlipperViewHolder -> bindSlipperViewHolder(holder, position)
      is AdvertViewHolder -> bindAdvertViewHolder(holder)
    }
  }

  private fun bindAdvertViewHolder(holder: AdvertViewHolder) {
    holder.advertImage.showIcon(advert.icon)
    holder.advertTagline.text = advert.tagline
    holder.itemView.setOnClickListener { onAdvertClickAction.invoke(advert) }
  }

  private fun bindHeaderViewHolder(holder: HeaderViewHolder, position: Int) {
    val item = getItem(position) as FakeDuck
    holder.clicksHolder.setOnClickListener { changeCollapseState(item, position) }
    val arrowRes = if (collapsedHeaders.contains(item))
      R.drawable.ic_keyboard_arrow_up_black_24dp
    else
      R.drawable.ic_keyboard_arrow_down_black_24dp
    holder.arrow.setImageResource(arrowRes)
    holder.title.setText(item.titleRes)
  }

  private fun changeCollapseState(item: FakeDuck, position: Int) {
    val isCollapsed = collapsedHeaders.contains(item)
    if (isCollapsed) {
      collapsedHeaders.remove(item)
    } else {
      collapsedHeaders.add(item)
    }
    // 1 to add items after header
    val startPosition = position + 1
    if (isCollapsed) {
      internalData.addAll(startPosition - ADVERTS_COUNT, item.items)
      notifyItemRangeInserted(startPosition, item.items.count())
    } else {
      internalData.removeAll(item.items)
      notifyItemRangeRemoved(startPosition, item.items.count())
    }
    notifyItemChanged(position)
  }

  @SuppressLint("SetTextI18n")
  private fun bindSlipperViewHolder(holder: SlipperViewHolder, position: Int) {
    val slipper = getItem(position) as DuckSlipper
    holder.duckSlipperImage.showIcon(slipper.icon)
    holder.duckSlipperSize.text = "Размер: ${slipper.size}"
    holder.clicksHolder.setOnClickListener { onDuckClickAction.invoke(slipper) }
  }

  private fun bindDuckViewHolder(holder: DuckViewHolder, position: Int) {
    val duck = getItem(position) as RubberDuck
    holder.rubberDuckImage.showIcon(duck.icon)
    holder.clicksHolder.setOnClickListener { onDuckClickAction.invoke(duck) }
  }

  override fun getItemViewType(position: Int): Int {
    if (position == 0) return VIEW_TYPE_ADVERT
    return when (getItem(position)) {
      is FakeDuck -> VIEW_TYPE_HEADER
      is RubberDuck -> VIEW_TYPE_RUBBER_DUCK
      is DuckSlipper -> VIEW_TYPE_SLIPPER_DUCK
      else -> throw UnsupportedOperationException("unknown type for $position position")
    }
  }

  private fun getItem(position: Int) = internalData[position - ADVERTS_COUNT]

  override fun getItemCount() = internalData.count() + ADVERTS_COUNT

  class DuckViewHolder(view: View) : RecyclerView.ViewHolder(view) {
    val rubberDuckImage: ImageView = view.findViewById(R.id.rubberDuckImage)
    val clicksHolder: View = view.findViewById(R.id.clicksHolder)
  }

  class SlipperViewHolder(view: View) : RecyclerView.ViewHolder(view) {
    val duckSlipperImage: ImageView = view.findViewById(R.id.duckSlipperImage)
    val duckSlipperSize: TextView = view.findViewById(R.id.duckSlipperSize)
    val clicksHolder: View = view.findViewById(R.id.clicksHolder)
  }

  class HeaderViewHolder(view: View) : RecyclerView.ViewHolder(view) {
    val title: TextView = view.findViewById(R.id.headerTitle)
    val arrow: ImageView = view.findViewById(R.id.headerArrow)
    val clicksHolder: View = view.findViewById(R.id.clicksHolder)
  }

  class AdvertViewHolder(view: View) : RecyclerView.ViewHolder(view) {
    val advertTagline: TextView = view.findViewById(R.id.advertTagline)
    val advertImage: ImageView = view.findViewById(R.id.advertImage)
  }
}

private class FakeDuck(
  val titleRes: Int,
  val items: List<Duck>
) : Duck

private fun ImageView.showIcon(icon: String, placeHolderRes: Int = R.drawable.duck_stub) {
  Picasso.get()
    .load(icon)
    .config(Bitmap.Config.ARGB_4444)
    .fit()
    .centerCrop()
    .noFade()
    .placeholder(placeHolderRes)
    .into(this)
}
