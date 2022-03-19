package com.theteampotato.gifit.data

import android.content.res.Resources

object DeviceManager {

    val language: String
        get() = getDeviceLanguage()

    private fun getDeviceLanguage() = Resources.getSystem().configuration.locales[0].language

}