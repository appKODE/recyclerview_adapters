package ru.rinekri.devfest2018.views

import android.content.Context
import android.support.v7.widget.CardView
import android.util.AttributeSet
import android.view.View
import android.widget.ImageView
import com.airbnb.epoxy.ModelProp
import com.airbnb.epoxy.ModelView
import ru.rinekri.devfest2018.R
import ru.rinekri.devfest2018.showIcon

@ModelView(autoLayout = ModelView.Size.MATCH_WIDTH_WRAP_HEIGHT)
class RubberDuckView @JvmOverloads constructor(
  context: Context, attrs: AttributeSet? = null, defStyleAttr: Int = 0
) : CardView(context, attrs, defStyleAttr) {

  private val iconView: ImageView

  init {
    View.inflate(context, R.layout.item_rubber_duck, this)
    iconView = findViewById(R.id.rubberDuckImage)
  }

  @ModelProp
  fun setIcon(uri: String) {
    iconView.showIcon(uri)
  }
}
