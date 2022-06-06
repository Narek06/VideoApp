package com.example.videoapp.interfaceis

import android.app.Activity
import android.os.IBinder
import android.view.inputmethod.InputMethodManager

interface Keyboard {
    fun Activity.hideKeyeboard(): Boolean {
        val inputMethodManager: InputMethodManager = this.getSystemService(
            Activity.INPUT_METHOD_SERVICE
        ) as InputMethodManager
        if (inputMethodManager.isActive) {
            val windowToken: IBinder =
                this.window.decorView.rootView.windowToken
            inputMethodManager.hideSoftInputFromWindow(
                windowToken, 0
            )
        }
        return false
    }
}