package com.ideologer.zamishoapp.utils

import android.content.Context
import android.content.pm.PackageManager

class Util{
    companion object{
        fun verifyPkg(context: Context, pakgName: String): Boolean {
            var installed = false
            try {
                val info = context.packageManager.getApplicationInfo(pakgName, 0)
                installed = true
            } catch (e: PackageManager.NameNotFoundException) {
                installed = false
            }

            return installed
        }
    }
}
