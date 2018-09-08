package ru.rinekri.devfest2018.delegates

import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import com.airbnb.epoxy.EpoxyController
import com.airbnb.epoxy.EpoxyRecyclerView
import ru.rinekri.devfest2018.DuckMockData
import ru.rinekri.devfest2018.R
import ru.rinekri.devfest2018.views.rubberDuckView

class DucksEpoxyActivity : AppCompatActivity() {

  private lateinit var ducksList: EpoxyRecyclerView

  override fun onCreate(savedInstanceState: Bundle?) {
    super.onCreate(savedInstanceState)
    setContentView(R.layout.activity_ducks_epoxy)
    ducksList = findViewById(R.id.duckList)
    ducksList.withModels {
      DuckMockData.ducks.orEmpty().forEachIndexed { id, duck ->
        rubberDuckView {
          id(id)
          icon(duck.icon)
        }
      }
    }
  }
}

fun EpoxyRecyclerView.withModels(buildModelsCallback: EpoxyController.() -> Unit) {
  setControllerAndBuildModels(object : EpoxyController() {
    override fun buildModels() {
      buildModelsCallback()
    }
  })
}