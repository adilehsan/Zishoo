package com.ideologer.zamishoapp.utils

import android.content.Context
import android.graphics.Bitmap
import android.net.Uri
import android.widget.Toast
import androidx.core.content.FileProvider
import com.ideologer.zamishoapp.BuildConfig
import com.ideologer.zamishoapp.R
import java.io.File
import java.io.FileOutputStream
import java.io.IOException
import java.util.*
import kotlin.collections.ArrayList

object Constants {

    const val BOLD = "fonts/Montserrat-Bold.otf"
    const val SEMIBOLD = "fonts/Montserrat-SemiBold.otf"
    const val EXTRABOLD = "fonts/Montserrat-ExtraBold.otf"
    const val LIGHT = "fonts/Montserrat-Light.otf"
    const val MEDIUM = "fonts/Montserrat-Medium.otf"
    const val REGULAR = "fonts/Montserrat-Regular.otf"
    const val ITALIC = "fonts/Montserrat-SemiBoldItalic.otf"
    const val THIN = "fonts/Montserrat-Thin.otf"
    const val BLACK = "fonts/Montserrat-Black.otf"
    fun getProvincesList(): java.util.ArrayList<String> {
        var provinceList: ArrayList<String> = ArrayList()
        provinceList.add("Punjab")
        provinceList.add("Sindh")
        provinceList.add("KPK")
        return provinceList
    }

    fun getImageUri(inContext: Context, inImage: Bitmap?): Uri {

        val random = Random()
        val imageName = "/image_${random.nextInt()}.jpg"
        try {
            // File(inContext.cacheDir, "images").deleteOnExit() // delete old images
            val cachePath = File(inContext.cacheDir, "images")
            cachePath.mkdirs() // don't forget to make the directory
            val stream = FileOutputStream("$cachePath$imageName")
            inImage?.compress(
                Bitmap.CompressFormat.JPEG,
                50,
                stream
            ) // can be png and any quality level
            stream.close()

        } catch (e: IOException) {
            Toast.makeText(inContext, e.javaClass.canonicalName, Toast.LENGTH_LONG)
                .show() // You can replace this with Log.e(...)
        }

        val imagePath = File(inContext.getCacheDir(), "images")
        val newFile = File(imagePath, imageName)
        val contentUri =
            FileProvider.getUriForFile(
                inContext,
                BuildConfig.APPLICATION_ID + ".fileprovider",
                newFile
            )
//        val bytes = ByteArrayOutputStream()
//        inImage?.compress(Bitmap.CompressFormat.JPEG, 100, bytes)
//        val path = MediaStore.Images.Media.insertImage(
//            inContext.getContentResolver(),
//            inImage,
//            "Title",
//            null
//        )
        return contentUri
    }

    fun getCitiesList(): java.util.ArrayList<String> {
        var citiesList: ArrayList<String> = ArrayList()
        citiesList.add("Lahore")
        citiesList.add("Islamabad")
        citiesList.add("Arifwala")
        citiesList.add("Jaranwala")
        citiesList.add("Chiniot")
        citiesList.add("Faisalabad")
        citiesList.add("Gojra")
        citiesList.add("Gujranwala")
        citiesList.add("Gujrat")
        citiesList.add("Hafizabad")
        citiesList.add("Mailsi")
        citiesList.add("Pattoki")
        citiesList.add("Pir Mahal")
        citiesList.add("Raiwind")
        citiesList.add("Rawalpindi")
        citiesList.add("Sialkot")
        citiesList.add("Sheikhupura")
        citiesList.add("Sargodha")
        citiesList.add("Samundri")
        citiesList.add("Sambrial")
        citiesList.add("Sahiwal")
        citiesList.add("Toba Tek Singh")
        citiesList.add("Vehari")
        citiesList.add("Wah Cantonment")
        citiesList.add("Wazirabad")
        citiesList.add("Zafarwal")
        citiesList.add("Kasur")
        citiesList.add("Nankana Sahib")
        citiesList.add("Multan")
        citiesList.add("Kasur")
        citiesList.add("Jhelum")
        citiesList.add("Jhang")
        citiesList.add("Mianwali")
        return citiesList
    }

    enum class DrawerItems(val menuItemId: Int) {
        PRODUCTS(R.id.menu_for_you),
        COLLECTIONS(R.id.menu_collections),
        ORDERS(R.id.menu_orders),
        ACCOUNT(R.id.menu_accounts);
    }
}