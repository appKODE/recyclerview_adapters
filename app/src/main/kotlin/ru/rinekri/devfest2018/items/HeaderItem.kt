package ru.rinekri.devfest2018.items

import ru.rinekri.devfest2018.items.common.DisplayableItem

data class HeaderItem(
  val isCollapsed: Boolean,
  val titleRes: Int
) : DisplayableItem
