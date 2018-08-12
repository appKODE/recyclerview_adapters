package ru.rinekri.devfest2018

import android.app.Application
import timber.log.Timber

class DuckApplication : Application() {

  override fun onCreate() {
    super.onCreate()
    if (BuildConfig.DEBUG) {
      Timber.plant(Timber.DebugTree())
    }
  }
}
