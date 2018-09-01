package ru.rinekri.devfest2018.models.items

import ru.rinekri.devfest2018.delegates.DucksDelegatesCountAdapter

data class DuckItem(
  val icon: String,
  val duckCountsAdapter: DucksDelegatesCountAdapter
) : DisplayableItem
