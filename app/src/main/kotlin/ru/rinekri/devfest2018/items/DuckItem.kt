package ru.rinekri.devfest2018.items

import com.xwray.groupie.kotlinandroidextensions.Item
import com.xwray.groupie.kotlinandroidextensions.ViewHolder
import kotlinx.android.synthetic.main.item_rubber_duck.*
import ru.rinekri.devfest2018.R
import ru.rinekri.devfest2018.models.RubberDuck
import ru.rinekri.devfest2018.showIcon

class DuckItem(private val rubberDuck: RubberDuck) : Item() {
  override fun getLayout() = R.layout.item_rubber_duck

  override fun bind(viewHolder: ViewHolder, position: Int) {
    viewHolder.rubberDuckImage.showIcon(rubberDuck.icon)
  }
}