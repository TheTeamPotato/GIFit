package com.theteampotato.gifit.data

import android.content.res.Resources
import android.os.Build

object DeviceManager {

    val language: String
        get() = getDeviceLanguage()

    private fun getDeviceLanguage(): String {
        val locale = if (Build.VERSION.SDK_INT < Build.VERSION_CODES.N)
            Resources.getSystem().configuration.locale
        else
            Resources.getSystem().configuration.locales[0]

        return locale.language
    }

}