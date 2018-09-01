package ru.rinekri.devfest2018.delegates

import android.os.Bundle
import android.support.v7.app.AlertDialog
import android.support.v7.app.AppCompatActivity
import android.support.v7.widget.LinearLayoutManager
import android.support.v7.widget.RecyclerView
import ru.rinekri.devfest2018.DuckMockData
import ru.rinekri.devfest2018.R
import ru.rinekri.devfest2018.items.AdvertItem
import ru.rinekri.devfest2018.items.DuckCountItem
import ru.rinekri.devfest2018.items.DuckSlipperItem
import ru.rinekri.devfest2018.items.RubberDuckItem
import ru.rinekri.devfest2018.items.common.DisplayableItem

class DucksDelegatesActivity : AppCompatActivity() {

  override fun onCreate(savedInstanceState: Bundle?) {
    super.onCreate(savedInstanceState)
    setContentView(R.layout.activity_ducks_delegates)
    findViewById<RecyclerView>(R.id.duckList).apply {
      layoutManager = LinearLayoutManager(this@DucksDelegatesActivity)
      adapter = DucksDelegatesAdapter(
        onDuckCountClickAction = {
          AlertDialog
            .Builder(this@DucksDelegatesActivity)
            .setTitle("Спасибо!")
            .setMessage("Ваша заявка на уточку в количестве ${it.count} штук принята и ожидает обработки.")
            .setPositiveButton("Прекрасно", null)
            .show()
        },
        onAdvertClickAction = {
          AlertDialog
            .Builder(this@DucksDelegatesActivity)
            .setTitle("Готово!")
            .setMessage("Ваш заказ на соль успешно оформлен, мы с вами свяжемся.")
            .setPositiveButton("ОК", null)
            .show()
        },
        onSlipperClickAction = {
          AlertDialog
            .Builder(this@DucksDelegatesActivity)
            .setTitle("Спасибо!")
            .setMessage("Ваша заявка на шлепки с уточками принята и ожидает обработки.")
            .setPositiveButton("Хорошо", null)
            .show()
        }

      ).apply {
        val data = getAdvert().plus(getRubberDucks()).plus(getDuckSlippers())
        setData(data)
      }
    }
  }

  private fun getAdvert(): List<DisplayableItem> {
    val firstAdvert = DuckMockData.adverts.orEmpty().map { AdvertItem(it.icon, it.tagline) }.shuffled().first()
    return listOf(firstAdvert)
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
