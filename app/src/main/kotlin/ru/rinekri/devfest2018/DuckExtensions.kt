package ru.rinekri.devfest2018

import android.content.Context
import android.graphics.Bitmap
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import com.squareup.picasso.Picasso

fun Context.inflate(resource: Int, root: ViewGroup, attachToRoot: Boolean = false): View {
  return LayoutInflater.from(this).inflate(resource, root, attachToRoot)
}

fun ImageView.showIcon(icon: String, placeHolderRes: Int = R.drawable.duck_stub) {
  Picasso.get()
          .load(icon)
          .config(Bitmap.Config.ARGB_4444)
          .fit()
          .centerCrop()
          .noFade()
          .placeholder(placeHolderRes)
          .into(this)
}
