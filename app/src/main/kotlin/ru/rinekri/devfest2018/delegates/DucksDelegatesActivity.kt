package ru.rinekri.devfest2018.delegates

import android.os.Bundle
import android.support.v7.app.AlertDialog
import android.support.v7.app.AppCompatActivity
import android.support.v7.widget.LinearLayoutManager
import android.support.v7.widget.RecyclerView
import ru.rinekri.devfest2018.DuckMockData
import ru.rinekri.devfest2018.R
import ru.rinekri.devfest2018.items.*
import ru.rinekri.devfest2018.items.common.DisplayableItem

class DucksDelegatesActivity : AppCompatActivity() {

  private val advert = DuckMockData.adverts.orEmpty().map { AdvertItem(it.icon, it.tagline) }.shuffled().first()
  private val collapsedItems: MutableSet<Int> = hashSetOf()

  private lateinit var ducksList: RecyclerView

  override fun onCreate(savedInstanceState: Bundle?) {
    super.onCreate(savedInstanceState)
    setContentView(R.layout.activity_ducks_delegates)
    ducksList = findViewById(R.id.duckList)

    ducksList.apply {
      layoutManager = LinearLayoutManager(this@DucksDelegatesActivity)
      adapter = createAdapter().apply { showData() }
    }
  }

  private fun createAdapter(): DucksDelegatesAdapter {
    return DucksDelegatesAdapter(
      onDuckCountClickAction = {
        AlertDialog
          .Builder(this@DucksDelegatesActivity)
          .setTitle("Спасибо!")
          .setMessage("Ваша заявка на уточку в количестве ${it.count} штук принята и ожидает обработки.")
          .setPositiveButton("Прекрасно", null)
          .show()
      },
      onSlipperClickAction = {
        AlertDialog
          .Builder(this@DucksDelegatesActivity)
          .setTitle("Спасибо!")
          .setMessage("Ваша заявка на шлепки с уточками принята и ожидает обработки.")
          .setPositiveButton("Хорошо", null)
          .show()
      },
      onHeaderClickAction = {
        if (collapsedItems.contains(it.titleRes)) {
          collapsedItems.remove(it.titleRes)
        } else {
          collapsedItems.add(it.titleRes)
        }
        (ducksList.adapter as DucksDelegatesAdapter).apply { showData() }
      },
      onAdvertClickAction = {
        AlertDialog
          .Builder(this@DucksDelegatesActivity)
          .setTitle("Готово!")
          .setMessage("Ваш заказ на соль успешно оформлен, мы с вами свяжемся.")
          .setPositiveButton("ОК", null)
          .show()
      }
    )
  }

  private fun DucksDelegatesAdapter.showData() {
    val data = mutableListOf<DisplayableItem>().apply {
      add(advert)
      val isRubberDucksCollapsed = collapsedItems.contains(R.string.rubber_ducks)
      add(HeaderItem(isRubberDucksCollapsed, R.string.rubber_ducks))
      if (!isRubberDucksCollapsed) addAll(getRubberDucks())
      val isDuckSlippersCollapsed = collapsedItems.contains(R.string.slippers)
      add(HeaderItem(isDuckSlippersCollapsed, R.string.slippers))
      if (!isDuckSlippersCollapsed) addAll(getDuckSlippers())
    }
    setData(data)
  }

  private fun getRubberDucks(): List<DisplayableItem> {
    return DuckMockData.ducks.orEmpty().map {
      val counts = (1..it.count).map { count -> DuckCountItem(it, count) }
      RubberDuckItem(it.icon, counts)
    }
  }

  private fun getDuckSlippers(): List<DisplayableItem> {
    return DuckMockData.slippers.orEmpty().map {
      DuckSlipperItem(it.icon, it.size)
    }
  }
}
