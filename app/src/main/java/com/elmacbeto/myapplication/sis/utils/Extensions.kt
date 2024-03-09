package com.elmacbeto.myapplication.sis.utils

import android.Manifest
import android.annotation.SuppressLint
import android.app.Activity
import android.content.Context
import android.content.pm.PackageManager
import android.os.Build
import android.text.SpannableStringBuilder
import android.view.View
import android.widget.TextView
import androidx.annotation.RequiresApi
import androidx.core.content.ContextCompat
import androidx.core.text.bold
import androidx.core.text.color
import com.bumptech.glide.Glide
import com.bumptech.glide.load.engine.DiskCacheStrategy
import com.bumptech.glide.request.RequestOptions
import com.elmacbeto.myapplication.App
import com.elmacbeto.myapplication.R
import com.elmacbeto.myapplication.sis.timmer.CheckConnection
import com.elmacbeto.myapplication.sis.utils.Constants.PHOTO_AUTHENTICATION_DEFAULT
import de.hdodenhof.circleimageview.CircleImageView
import java.text.DateFormat
import java.text.DateFormatSymbols
import java.text.SimpleDateFormat
import java.time.Instant
import java.time.LocalDateTime
import java.time.ZoneOffset
import java.time.format.DateTimeFormatter
import java.util.Locale
import java.util.TimeZone
import java.util.Timer

fun CircleImageView.loadImageUrl(resource: String?) {
    if (resource != null) {
        val options: RequestOptions = RequestOptions()
            .diskCacheStrategy(DiskCacheStrategy.ALL)
            .centerCrop()

        Glide.with(this.context)
            .load(
                resource.ifEmpty { PHOTO_AUTHENTICATION_DEFAULT }
            )
            .apply(options)
            .into(this)
    }
}


fun Activity.launchTimer() {
    val timer = Timer()
    timer.schedule(CheckConnection(this), 0, 10000)
}

@SuppressLint("SimpleDateFormat")
fun String.toDateFormatMonths(format: String = "dd/MM/yyyy"): String {
    val symbols = DateFormatSymbols(Locale.forLanguageTag("es-MX"))

    val dateFormat = SimpleDateFormat(format, symbols)
    dateFormat.timeZone = TimeZone.getTimeZone("America/Mexico_City")
    val date = dateFormat.parse(this)

    val formatter: DateFormat = SimpleDateFormat("dd MMM", symbols)
    formatter.timeZone = TimeZone.getTimeZone("America/Mexico_City")
    val months = arrayOf(
        "ene", "feb", "mar", "abr", "may", "jun",
        "jul", "ago", "sep", "oct", "nov", "dic"
    )
    symbols.shortMonths = months
    return formatter.format(date!!).replace(".", "")
}

@RequiresApi(Build.VERSION_CODES.O)
fun formatDate(inputDate: String): String {
    val inputFormatter = DateTimeFormatter.ofPattern("yyyy-MM-dd'T'HH:mm:ss.SSSX")
    val instant = Instant.from(inputFormatter.parse(inputDate))

    val localDateTime = LocalDateTime.ofInstant(instant, ZoneOffset.UTC)

    val outputFormatter = DateTimeFormatter.ofPattern("yyyy/MM/dd")

    return outputFormatter.format(localDateTime)
}

fun View.setSafeOnClickListener(onSafeClick: (View) -> Unit) {
    val safeClickListener = SafeClickListener {
        onSafeClick(it)
    }
    setOnClickListener(safeClickListener)
}

fun Boolean.getDrawableFavorite(): Int {
    return if (this) {
        R.drawable.ic_favorite_active
    } else {
        R.drawable.ic_favorite
    }
}

fun TextView.latitudeLongitudeFormat(type: String, value: String) {
    this.text = SpannableStringBuilder()
        .bold { color(App.instance.getColor(R.color.black)) { append("$type: ") } }
        .color(App.instance.getColor(R.color.blue11)) { append(value) }
}

fun Context.isPermissionGranted(): Boolean {
    return ContextCompat.checkSelfPermission(
        this,
        Manifest.permission.ACCESS_FINE_LOCATION
    ) == PackageManager.PERMISSION_GRANTED && ContextCompat.checkSelfPermission(
    this,
    Manifest.permission.ACCESS_COARSE_LOCATION
    ) == PackageManager.PERMISSION_GRANTED
}