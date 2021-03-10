package com.theteampotato.gifit.ui

import android.app.Activity
import android.content.Context
import android.view.inputmethod.InputMethodManager
import android.view.inputmethod.InputMethodManager.HIDE_IMPLICIT_ONLY

fun hideKeyboard(context: Context) {
    val imm = context.getSystemService(Activity.INPUT_METHOD_SERVICE) as InputMethodManager
    imm.toggleSoftInput(HIDE_IMPLICIT_ONLY, 0);
}