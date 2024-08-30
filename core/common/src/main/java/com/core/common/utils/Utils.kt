package com.core.common.utils

import android.content.Context
import androidx.appcompat.app.AlertDialog
import com.core.common.R
import com.core.common.utils.Constants.Companion.BUTTON_POSITIVE

object Utils {


    fun showAlertDialog(context: Context, message: String) {
        try {
            val builder = AlertDialog.Builder(context)
            builder.setTitle(R.string.app_name)
            builder.setMessage(message)
            builder.setIcon(android.R.drawable.ic_dialog_alert)
            builder.setPositiveButton(BUTTON_POSITIVE) { dialog, _ ->
                dialog.dismiss()
            }
            val alertDialog = builder.create()
            alertDialog.setCancelable(false)
        } catch (e: Exception) {
            e.stackTrace
        }
    }
}