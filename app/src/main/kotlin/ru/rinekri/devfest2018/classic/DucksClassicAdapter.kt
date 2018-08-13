package ru.rinekri.devfest2018.classic

import android.graphics.Bitmap
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import androidx.recyclerview.widget.RecyclerView
import com.squareup.picasso.Picasso
import ru.rinekri.devfest2018.R
import ru.rinekri.devfest2018.models.Duck

class DucksClassicAdapter(
  private val data: List<Duck>,
  private val onDuckClickAction: (Duck) -> Unit
) : RecyclerView.Adapter<DucksClassicAdapter.ViewHolder>() {

  override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
    val rubberDuckView = LayoutInflater.from(parent.context).inflate(R.layout.item_rubber_duck, parent, false)
    return ViewHolder(rubberDuckView)
  }

  override fun onBindViewHolder(holder: ViewHolder, position: Int) {
    val duck = data[position]
    holder.rubberDuckImage.apply {
      Picasso.get()
        .load(duck.icon)
        .config(Bitmap.Config.ARGB_4444)
        .fit()
        .centerCrop()
        .noFade()
        .placeholder(R.drawable.duck_stub)
        .into(this)
    }
    holder.clicksHolder.setOnClickListener { onDuckClickAction.invoke(duck) }
  }

  override fun getItemCount() = data.count()

  class ViewHolder(view: View) : RecyclerView.ViewHolder(view) {
    val rubberDuckImage: ImageView = view.findViewById(R.id.rubberDuckImage)
    val clicksHolder: View = view.findViewById(R.id.clicksHolder)
  }
}