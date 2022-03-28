package com.test.searchapp.core.extensions

import android.app.Activity
import android.view.View
import android.view.inputmethod.InputMethodManager
import androidx.core.view.doOnLayout
import com.google.android.material.textfield.TextInputLayout

fun TextInputLayout.setHintStyle(id: Int) {
    doOnLayout {
        setHintTextAppearance(id)
    }
}

fun View.hideKeyboard() {
    val imm = context.getSystemService(Activity.INPUT_METHOD_SERVICE) as InputMethodManager
    imm.hideSoftInputFromWindow(windowToken, 0)
}