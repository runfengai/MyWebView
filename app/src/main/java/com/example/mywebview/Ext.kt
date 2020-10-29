package com.example.mywebview

import android.content.Context
import android.content.res.Configuration
import android.os.Build

fun Context.getLollipopFixWebView(): Context {
    return if (Build.VERSION.SDK_INT in 21..22) {
        createConfigurationContext(Configuration())
    } else this
}