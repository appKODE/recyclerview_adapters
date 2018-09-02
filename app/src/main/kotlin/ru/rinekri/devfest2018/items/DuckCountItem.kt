package ru.rinekri.devfest2018.items

import ru.rinekri.devfest2018.items.common.DisplayableItem
import ru.rinekri.devfest2018.models.RubberDuck

data class DuckCountItem(
  val duck: RubberDuck,
  val count: Int
) : DisplayableItem
