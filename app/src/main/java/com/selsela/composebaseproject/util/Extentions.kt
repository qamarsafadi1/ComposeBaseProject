package com.selsela.composebaseproject.util

import android.Manifest
import android.app.Activity
import android.app.AlertDialog
import android.content.ClipData
import android.content.Context
import android.content.ContextWrapper
import android.content.Intent
import android.content.pm.PackageManager
import android.graphics.Bitmap
import android.graphics.BitmapFactory
import android.location.Location
import android.net.Uri
import android.os.Build
import android.os.Environment
import android.os.Handler
import android.os.Looper
import android.provider.MediaStore
import android.text.Html
import android.text.format.DateUtils
import android.util.Log
import androidx.activity.compose.ManagedActivityResultLauncher
import androidx.activity.compose.rememberLauncherForActivityResult
import androidx.activity.result.ActivityResult
import androidx.activity.result.ActivityResultLauncher
import androidx.activity.result.contract.ActivityResultContracts
import androidx.appcompat.app.AppCompatActivity
import androidx.compose.runtime.Composable
import androidx.compose.runtime.DisposableEffect
import androidx.compose.runtime.SideEffect
import androidx.compose.runtime.State
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberUpdatedState
import androidx.compose.ui.platform.LocalLifecycleOwner
import androidx.core.content.ContextCompat
import androidx.core.content.FileProvider
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.LifecycleEventObserver
import androidx.lifecycle.LifecycleOwner
import androidx.lifecycle.flowWithLifecycle
import com.fondesa.kpermissions.extension.permissionsBuilder
import com.fondesa.kpermissions.extension.send
import com.google.android.gms.location.LocationServices
import com.google.android.gms.maps.model.LatLng
import com.google.android.gms.tasks.OnCompleteListener
import com.google.firebase.messaging.FirebaseMessaging
import com.selsela.composebaseproject.BaseApp.Companion.LocalData
import com.selsela.composebaseproject.BuildConfig
import com.selsela.composebaseproject.R
import com.selsela.composebaseproject.data.local.PreferenceHelper.fcmToken
import com.selsela.composebaseproject.util.FileHandler.Companion.compressImage
import com.selsela.composebaseproject.util.FileHelper.Companion.scaleBitmap
import com.selsela.composebaseproject.util.networking.model.ErrorBase
import com.selsela.composebaseproject.util.networking.model.Resource
import com.tapadoo.alerter.Alerter
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.MutableStateFlow
import java.io.File
import java.io.IOException
import java.text.SimpleDateFormat
import java.util.Date
import java.util.Locale
import kotlin.coroutines.CoroutineContext
import kotlin.coroutines.EmptyCoroutineContext

var absoulutePath: String? = null

fun Activity.showSuccessTop(message: String) {
    Alerter.create(this)
        .setTitle("")
        .setText(message)
        .setTitleAppearance(R.style.AlertTextAppearance_Title_1)
        .setTextAppearance(R.style.AlertTextAppearance_Text_1)
        .setBackgroundColorRes(R.color.green) // or setBackgroundColorInt(Color.CYAN)
        .setIcon(R.drawable.component_6___2)
        .setIconColorFilter(0) //
        .show()
}

fun Activity.showErrorTop(message: String) {
    Alerter.create(this)
        .setTitle("")
        .setText(message)
        .setTitleAppearance(R.style.AlertTextAppearance_Title_1)
        .setTextAppearance(R.style.AlertTextAppearance_Text_1)
        .setBackgroundColorRes(R.color.red) // or setBackgroundColorInt(Color.CYAN)
        .setIcon(R.drawable.svgexport_10__10_)
        .setIconColorFilter(0) //
        .show()
}


fun Context.getActivity(): AppCompatActivity? = when (this) {
    is AppCompatActivity -> this
    is ContextWrapper -> baseContext.getActivity()
    else -> null
}

fun <T> (() -> T).withDelay(delay: Long = 250L) {
    Handler(Looper.getMainLooper()).postDelayed({ this.invoke() }, delay)
}

fun Any.log(tag: String = "") {
    if(BuildConfig.DEBUG) {
        if (tag == "") {
            Log.d("Base: ", this.toString())
        } else {
            Log.d("Base$tag", this.toString())

        }
    }
}

@Composable
fun Context.RequestPermission(
    permission: String,
    onGranted: (Boolean) -> Unit
) {
    val launcher = rememberLauncherForActivityResult(
        ActivityResultContracts.RequestPermission()
    ) { isGranted: Boolean ->
        onGranted(isGranted)
    }
    when (PackageManager.PERMISSION_GRANTED) {
        ContextCompat.checkSelfPermission(
            this,
            permission
        ) -> {
            onGranted(true)
        }

        else -> {
            SideEffect { // SideEffect just when you need to request your permission
                // first time before composition
                launcher.launch(Manifest.permission.ACCESS_COARSE_LOCATION)
            }
        }
    }


}

fun getMyLocation(
    context: Context,
    onMyLocation: (LatLng) -> Unit
) {
    try {
        val fusedLocationClient =
            LocationServices.getFusedLocationProviderClient(context)
        fusedLocationClient.lastLocation.addOnSuccessListener { location: Location? ->
            location?.let {
                val currentLocation = LatLng(location.latitude, location.longitude)
                onMyLocation(currentLocation)
            } ?: run {}
        }

    } catch (e: SecurityException) {
        e.printStackTrace()
    }
}


fun <E> handleSuccess(data: E?, message: String? = ""): MutableStateFlow<Resource<E>> {
    return MutableStateFlow(
        Resource.success(
            data,
            message = message
        )
    )
}

fun <E> handleExceptions(errorBase: Exception): MutableStateFlow<Resource<E>> {
    return MutableStateFlow(
        Resource.error(
            null,
            errorBase.message,
            null
        )
    )
}

fun <E> handleExceptions(errorBase: ErrorBase): MutableStateFlow<Resource<E>> {
    return MutableStateFlow<Resource<E>>(
        Resource.error(
            null,
            errorBase.responseMessage,
            errorBase.errors,
            errorBase.statusCode
        )
    )
}

@Composable
fun <T> rememberFlow(
    flow: Flow<T>,
    lifecycleOwner: LifecycleOwner = LocalLifecycleOwner.current
): Flow<T> {
    return remember(key1 = flow, key2 = lifecycleOwner) {
        flow.flowWithLifecycle(
            lifecycleOwner.lifecycle,
            Lifecycle.State.STARTED
        )
    }
}

@Composable
fun <T : R, R> Flow<T>.collectAsStateLifecycleAware(
    initial: R,
    context: CoroutineContext = EmptyCoroutineContext
): State<R> {
    val lifecycleAwareFlow = rememberFlow(flow = this)
    return lifecycleAwareFlow.collectAsState(initial = initial, context = context)
}


fun Context.showAlertDialog(
    title: String, content: String,
    positiveButtonText: String,
    negativeButtonText: String,
    onPositiveClick: () -> Unit
) {
    AlertDialog.Builder(this)
        .setTitle(title)
        .setMessage(content)
        .setPositiveButton(positiveButtonText) { dialog, _ ->
            dialog.dismiss()
            onPositiveClick()
        }.setNegativeButton(negativeButtonText) { dialog, _ ->
            dialog.dismiss()
        }.show()
}


@Composable
fun mStartActivityForResult(
    context: Context,
    lambda: (File?, Bitmap?) -> Unit
): ManagedActivityResultLauncher<Intent, ActivityResult> {
    return rememberLauncherForActivityResult(
        contract = ActivityResultContracts.StartActivityForResult(),
        onResult = { result ->
            val data = result.data
            if (result.resultCode == Activity.RESULT_OK) {
                try {
                    if (data != null && data.data != null) {
                        val image = FileHelper.getImage(data.data, context)
                        val bitmap = FileHelper.decodeBitmap(context, data.data!!)
                        lambda(image, bitmap)
                    } else if (data != null && data.clipData != null) {
                        val mClipData: ClipData? = data.clipData
                        if (mClipData != null)
                            for (i in 0 until mClipData.itemCount) {
                                val item: ClipData.Item = mClipData.getItemAt(i)
                                val uri: Uri = item.uri
                                val image = FileHelper.getImage(uri, context)
                                val bitmap = FileHelper.decodeBitmap(context, uri)
                                lambda(image, bitmap)

                            }
                    } else if (absoulutePath != null) {
                        BitmapFactory.Options().apply {
                            // Get the dimensions of the bitmap
                            inJustDecodeBounds = true
                            var bitmap = compressImage(
                                context!!,
                                Uri.fromFile(File(absoulutePath!!))
                            )
                            bitmap = scaleBitmap(bitmap!!, 2000*1000)
                            val image = FileHelper.getBitmapImage(
                                bitmap!!,
                                context
                            )
                            lambda(image, bitmap)
                        }
                    }
                } catch (ex: Exception) {
                    ex.printStackTrace()
                }
            }
        })
}


fun uploadImages(
    mActivity: Context,
    images: ActivityResultLauncher<Intent>,
    isMultiple: Boolean
) {
//    permissionsBuilder(
//        Manifest.permission.READ_EXTERNAL_STORAGE
//    ).build().send {
    mActivity.getActivity()?.permissionsBuilder(
        Manifest.permission.CAMERA
    )?.build()?.send {
        kotlin.run {
            // if (it.allGranted()) {
            absoulutePath = null

            try {
                val cameraIntent =
                    Intent(
                        Intent.ACTION_PICK,
                        MediaStore.Images.Media.EXTERNAL_CONTENT_URI
                    )

                cameraIntent.putExtra(Intent.EXTRA_ALLOW_MULTIPLE, isMultiple);
                val takePhotoIntent = Intent(MediaStore.ACTION_IMAGE_CAPTURE)
                // takePhotoIntent.putExtra(MediaStore.EXTRA_OUTPUT, getFileDirectory())
                takePhotoIntent.putExtra("android.intent.extras.CAMERA_FACING", 1)
                // Create the File where the photo should go

                val photoFile: File =
                    mActivity.createFullImageFile()

                // Continue only if the File was successfully created
                val photoURI: Uri = FileProvider.getUriForFile(
                    mActivity,
                    "com.selsela.airconditioner.fileprovider",
                    photoFile
                )

                takePhotoIntent.putExtra(MediaStore.EXTRA_OUTPUT, photoURI)
                val pickTitle = "Select or take a new Picture"
                val chooserIntent = Intent.createChooser(cameraIntent, pickTitle)
                chooserIntent.putExtra(
                    Intent.EXTRA_INITIAL_INTENTS, arrayOf(takePhotoIntent)
                )
                images.launch(chooserIntent)


            } catch (ex: IOException) {
                ex.printStackTrace()
            }
        }
    }
}

@Composable
fun OnLifecycleEvent(onEvent: (owner: LifecycleOwner, event: Lifecycle.Event) -> Unit) {
    val eventHandler = rememberUpdatedState(onEvent)
    val lifecycleOwner = rememberUpdatedState(LocalLifecycleOwner.current)

    DisposableEffect(lifecycleOwner.value) {
        val lifecycle = lifecycleOwner.value.lifecycle
        val observer = LifecycleEventObserver { owner, event ->
            eventHandler.value(owner, event)
        }

        lifecycle.addObserver(observer)
        onDispose {
            lifecycle.removeObserver(observer)
        }
    }
}

@Composable
fun mStartActivityForResultMultiple(
    context: Context,
    lambda: (List<File>?) -> Unit
): ActivityResultLauncher<Intent> {
    val imges: MutableList<File>? = mutableListOf()

    return rememberLauncherForActivityResult(ActivityResultContracts.StartActivityForResult()) { result: ActivityResult ->
        "heeere".log("absoulutePath")
        val data = result.data
        if (result.resultCode == Activity.RESULT_OK) {
            try {
                if (data != null && data.data != null) {
                    val image = FileHelper.getImage(data.data, context)
                    image?.let { it1 -> imges?.add(it1) }
                    lambda(imges)

                    /*   if (bitmap != null && Common.validImageSize(
                               FileUtils.getFile(
                                   requireContext(),
                                   data.data!!
                               )
                           )
                       ) {
                           lambda(image, bitmap)
                           *//*  viewModel.file = image
                       binding?.imageIv?.setImageBitmap(bitmap)*//*
                        //   viewModel.bitmaps.add(bitmap)
                    } else {
                        context?.showError(getString(R.string.msg_size_exced_max))

                    }*/
                } else if (data != null && data.clipData != null) {

                    val mClipData: ClipData? = data.clipData
                    var imageValidSize = true

                    val count = mClipData?.itemCount ?: 0
                    if (mClipData != null)
                        for (i in 0 until count) {
                            val imageUri = result.data?.clipData?.getItemAt(i)?.uri
                            imageUri?.let { it1 ->
                                val selectedImage = FileHandler.decodeBitmap(context, it1)
                                selectedImage?.log()
                                val filename = FileUtils.getFileName(context, imageUri)
                                val file = FileUtils.createFile(context, imageUri, filename)
                                file.let { it1 -> imges?.add(it1) }
                                imges?.size?.log("viewModel.photosImg")
                            }
                        }
                    lambda(imges)
                    /*                    if (imageValidSize.not()) {
                                            context?.showError(getString(R.string.msg_size_exced_max))

                                        }*/

                } else if (absoulutePath != null) {
                    BitmapFactory.Options().apply {
                        // Get the dimensions of the bitmap
                        inJustDecodeBounds = true
                        var bitmap = compressImage(context, Uri.fromFile(File(absoulutePath!!)))
                        bitmap = scaleBitmap(bitmap!!, 2000*1000)
                        val image = FileHelper.getBitmapImage(
                            bitmap!!,
                            context
                        )
                        image.let { it1 ->
                            if (it1 != null) {
                                imges?.add(it1)
                            }
                        }
                    }
                    lambda(imges)
                }


                /* else if (data.extras?.get("data") != null) {
                        val image = FileHelper.getBitmapImage(
                            mActivity,
                            data.extras?.get("data")!! as Bitmap,
                            requireContext()
                        )
                        val bitmap = data.extras?.get("data")!! as Bitmap
                        if (Common.validImageSize(bitmap)) {
                            lambda(image, bitmap)
                            //   viewModel.bitmaps.add(bitmap)
                        } else {
                            context?.showError(getString(R.string.msg_size_exced_max))

                        }
                    } */
            } catch (ex: Exception) {
                ex.printStackTrace()
            }
        }
    }
}

fun Context.createFullImageFile(): File {
    // Create an image file name
    val timeStamp: String =
        SimpleDateFormat("yyyyMMdd_HHmmss", Locale.ENGLISH).format(Date())
    val storageDir: File? = getExternalFilesDir(Environment.DIRECTORY_PICTURES)
    val ff = File.createTempFile(
        "JPEG_${timeStamp}_", /* prefix */
        ".jpg", /* suffix */
        storageDir /* directory */
    )
    absoulutePath = ff.absolutePath

    return ff
}


fun String.getTimeAgo(): String {
    var text = ""
    val sdf = SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss.SSSSSS'Z'", Locale.ENGLISH)
    val date: Date = sdf.parse(this)
    val time = DateUtils.getRelativeTimeSpanString(
        date.time,
        Date().time, DateUtils.MINUTE_IN_MILLIS
    )
    text = if (time == "قبل ٠ دقيقة")
        "الآن"
    else time.toString().preventArabicNumber()
    return text
}

fun String.preventArabicNumber(): String {
    val input = this
    if (input.isEmpty()) {
        return input
    }
    val builder = StringBuilder()
    for (element in input) {
        val ch = element
        if (Character.isDigit(ch) && !(ch in '0'..'9')) {
            val numericValue = Character.getNumericValue(ch)
            if (numericValue >= 0) {
                builder.append(numericValue)
            }
        } else {
            builder.append(ch)
        }
    }
    return builder.toString()
}


fun bindHtml(html: String): String {
    return if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.N) {
        Html.fromHtml(html, Html.FROM_HTML_MODE_LEGACY)
            .toString()
    } else {
        Html.fromHtml(html)
            .toString()
    }
}

fun Context.whatsappContact(phone: String) {
    val url = "https://api.whatsapp.com/send?phone=${phone}"
    val i = Intent(Intent.ACTION_VIEW)
    i.data = Uri.parse(url)
    this.startActivity(i)
}


fun receiveToken() {
    FirebaseMessaging.getInstance().token
        .addOnCompleteListener(OnCompleteListener { task ->
            if (!task.isSuccessful) {
                Log.w("Token =>", "getInstanceId failed", task.exception)
                return@OnCompleteListener
            }
            val token = task.result
            LocalData.fcmToken = token
            LocalData.fcmToken?.log("Token")
        })
}


