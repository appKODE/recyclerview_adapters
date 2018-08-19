package ru.rinekri.devfest2018

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup

fun Context.inflate(resource: Int, root: ViewGroup, attachToRoot: Boolean = false): View {
  return LayoutInflater.from(this).inflate(resource, root, attachToRoot)
}