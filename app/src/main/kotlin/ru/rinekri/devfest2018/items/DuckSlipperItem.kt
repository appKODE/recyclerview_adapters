package ru.rinekri.devfest2018.items

import ru.rinekri.devfest2018.items.common.DisplayableItem
import ru.rinekri.devfest2018.models.DuckSlipperSize

data class DuckSlipperItem(
  val icon: String,
  val size: DuckSlipperSize
) : DisplayableItem
