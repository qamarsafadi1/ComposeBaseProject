package com.selsela.composebaseproject.util

import android.app.Activity
import android.graphics.Bitmap
import androidx.appcompat.app.AppCompatActivity
import com.selsela.composebaseproject.R
import com.selsela.composebaseproject.util.networking.model.Errors
import okhttp3.MediaType.Companion.toMediaTypeOrNull
import okhttp3.MultipartBody
import okhttp3.RequestBody
import okhttp3.RequestBody.Companion.asRequestBody
import okhttp3.RequestBody.Companion.toRequestBody
import java.io.ByteArrayOutputStream
import java.io.File

open class Common {
    companion object{
        fun validImageSize(bitmapPhoto: Bitmap): Boolean {
            return try {
                val stream = ByteArrayOutputStream()
                bitmapPhoto.compress(Bitmap.CompressFormat.JPEG, 100, stream)
                val imageInByte: ByteArray = stream.toByteArray()


                // Get length of file in bytes
                val imageSizeInBytes = imageInByte.size.toFloat()
                // Convert the bytes to Kilobytes (1 KB = 1024 Bytes)
                val imageSizeInKB = imageSizeInBytes / 1024
                // Convert the KB to MegaBytes (1 MB = 1024 KBytes)
                val imageSizeInMB = imageSizeInKB / 1024
                imageSizeInMB.log("imageSizeInMB")
                imageSizeInMB <= 20

            } catch (e: java.lang.Exception) {
                e.printStackTrace()
                true
            }
        }

        fun handleErrors(
            message: String?, errors: List<Errors>?, context: AppCompatActivity?
        ) {
            when {
                errors != null -> {
                    if (errors.isNotEmpty())
                        context?.showErrorTop(errors[0].error)
                    else context?.showErrorTop(message!!)
                }
                message != null -> context?.showErrorTop(message)
                else -> context?.showErrorTop(context.getString(R.string._msg_failed))
            }
        }
        fun createPartFromString(descriptionString: String?): RequestBody {
            return descriptionString?.toRequestBody(MultipartBody.FORM) ?: "".toRequestBody(
                MultipartBody.FORM
            )
        }
        fun createImageFile(image: File, key: String): MultipartBody.Part {
            val requestBody = image.asRequestBody("image/*".toMediaTypeOrNull())
            return MultipartBody.Part.createFormData(key, image.name ?: "123", requestBody!!)
        }

    }
}