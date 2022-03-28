package com.test.searchapp.core.extensions

import androidx.core.view.doOnLayout
import com.google.android.material.textfield.TextInputLayout

fun TextInputLayout.setHintStyle(id: Int) {
    doOnLayout {
        setHintTextAppearance(id)
    }
}