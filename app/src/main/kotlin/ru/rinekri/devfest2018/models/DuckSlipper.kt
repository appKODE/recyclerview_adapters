package ru.rinekri.devfest2018.models

data class DuckSlipper(val icon: String, val size: Size) : Duck {
  enum class Size { XS, S, M, L, X, XL, XXL }
}