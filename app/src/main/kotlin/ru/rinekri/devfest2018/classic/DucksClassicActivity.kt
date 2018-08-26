package ru.rinekri.devfest2018.classic

import android.os.Bundle
import androidx.appcompat.app.AlertDialog
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import ru.rinekri.devfest2018.DuckMockData
import ru.rinekri.devfest2018.R
import ru.rinekri.devfest2018.models.Duck

class DucksClassicActivity : AppCompatActivity() {

  override fun onCreate(savedInstanceState: Bundle?) {
    super.onCreate(savedInstanceState)
    setContentView(R.layout.activity_ducks_classsic)
    findViewById<RecyclerView>(R.id.duckList).apply {
      layoutManager = LinearLayoutManager(this@DucksClassicActivity)
      adapter = DucksClassicAdapter(
        onDuckClickAction = {
          AlertDialog
            .Builder(this@DucksClassicActivity)
            .setTitle("Спасибо!")
            .setMessage("Ваша заявка принята и ожидает обработки.")
            .setPositiveButton("Хорошо", null)
            .show()
        },
        onAdvertClickAction = {
          AlertDialog
            .Builder(this@DucksClassicActivity)
            .setTitle("Готово!")
            .setMessage("Ваш заказ на соль успешно оформлен, мы с вами свяжемся.")
            .setPositiveButton("ОК", null)
            .show()
        }
      ).apply {
        data = getDucks()
      }
    }
  }

  private fun getDucks(): List<Duck> {
    val rubberDucks = DuckMockData.ducks.orEmpty()
    val duckSlippers = DuckMockData.slippers.orEmpty()
    return rubberDucks.plus(duckSlippers).shuffled()
  }
}
