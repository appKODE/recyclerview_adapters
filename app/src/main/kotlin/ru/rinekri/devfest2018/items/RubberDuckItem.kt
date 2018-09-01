package ru.rinekri.devfest2018.items

import ru.rinekri.devfest2018.items.common.DisplayableItem

data class RubberDuckItem(
  val icon: String,
  val counts: List<DuckCountItem>
) : DisplayableItem
