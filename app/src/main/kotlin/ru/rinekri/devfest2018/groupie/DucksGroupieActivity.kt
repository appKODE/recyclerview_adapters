package ru.rinekri.devfest2018.groupie

import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.support.v7.widget.LinearLayoutManager
import android.support.v7.widget.RecyclerView
import com.xwray.groupie.GroupAdapter
import com.xwray.groupie.ViewHolder
import ru.rinekri.devfest2018.DuckMockData
import ru.rinekri.devfest2018.R
import ru.rinekri.devfest2018.items.DuckItem

class DucksGroupieActivity : AppCompatActivity() {

  private lateinit var ducksList: RecyclerView

  override fun onCreate(savedInstanceState: Bundle?) {
    super.onCreate(savedInstanceState)
    setContentView(R.layout.activity_ducks_groupie)
    ducksList = findViewById(R.id.duckList)
    ducksList.apply {
      layoutManager = LinearLayoutManager(this@DucksGroupieActivity)
      adapter = GroupAdapter<ViewHolder>().apply {
        addAll(DuckMockData.ducks.orEmpty().map { DuckItem(it) })
      }
    }
  }
}
