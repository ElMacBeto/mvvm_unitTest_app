package com.elmacbeto.myapplication.sis.alerts

import android.annotation.SuppressLint
import android.app.Activity
import android.text.SpannableStringBuilder
import android.view.Gravity
import android.view.View
import android.widget.*
import androidx.core.content.ContextCompat
import androidx.core.text.bold
import androidx.core.text.color
import com.elmacbeto.myapplication.App
import com.elmacbeto.myapplication.R
import com.elmacbeto.myapplication.databinding.WidgetCustomToastBinding


enum class TypeToast(val type: Int) {
    SUCCESS(R.drawable.ic_toast_status_correct),
    ERROR(R.drawable.ic_toast_status_error),
    INFORMATION(R.drawable.ic_toast_status_informative),
    WARNING(R.drawable.ic_toast_status_warning)
}

class CustomToastWidget {
    companion object {
        @SuppressLint("ResourceAsColor")
        fun show(
            activity: Activity,
            message: String,
            type: TypeToast = TypeToast.SUCCESS,
            toastLength: Int = Toast.LENGTH_SHORT,
        ) {
            val binding = WidgetCustomToastBinding.inflate(activity.layoutInflater)

            binding.tvTextToast.text = SpannableStringBuilder()
                .bold { color(getIdColor(type)) { append(getTextDetail(type) + "  ") } }
                .bold { append(message) }

            binding.imvStatusIcon.setImageDrawable(ContextCompat.getDrawable(activity, type.type))
            makeToast(activity, toastLength, binding.root)
        }

        private fun getTextDetail(type: TypeToast): String {
            return when (type) {
                TypeToast.SUCCESS -> App.instance.getString(R.string.label_success)
                TypeToast.ERROR -> App.instance.getString(R.string.label_error)
                TypeToast.INFORMATION -> App.instance.getString(R.string.label_information)
                TypeToast.WARNING -> App.instance.getString(R.string.label_warning)
            }
        }

        private fun getIdColor(type: TypeToast): Int {
            return when (type) {
                TypeToast.SUCCESS -> App.instance.getColor(R.color.toast_success)
                TypeToast.ERROR -> App.instance.getColor(R.color.toast_error)
                TypeToast.INFORMATION -> App.instance.getColor(R.color.toast_information)
                TypeToast.WARNING -> App.instance.getColor(R.color.toast_waerning)
            }
        }

        @Suppress("DEPRECATION")
        private fun makeToast(activity: Activity, toastLength: Int, layout: View) {
            val toast = Toast(activity)
            toast.setGravity(Gravity.TOP or Gravity.FILL_HORIZONTAL, 0, 0)
            toast.duration = toastLength
            toast.view = layout
            toast.show()
        }
    }
}