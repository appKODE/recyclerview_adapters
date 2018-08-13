package ru.rinekri.devfest2018.classic

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView

import ru.rinekri.devfest2018.DucksMockData
import ru.rinekri.devfest2018.R
import timber.log.Timber

class DucksClassicActivity : AppCompatActivity() {

  override fun onCreate(savedInstanceState: Bundle?) {
    super.onCreate(savedInstanceState)
    setContentView(R.layout.activity_ducks_classsic)
    findViewById<RecyclerView>(R.id.duckList).apply {
      layoutManager = LinearLayoutManager(this@DucksClassicActivity)
      adapter = DucksClassicAdapter(
        data = DucksMockData.data.orEmpty().shuffled(),
        onDuckClickAction = { Timber.e("duck = $it") }
      )
    }
  }
}
