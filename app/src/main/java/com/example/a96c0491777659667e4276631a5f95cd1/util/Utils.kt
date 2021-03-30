package com.example.a96c0491777659667e4276631a5f95cd1.util

import android.content.Context
import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity

fun goToActivity(
    context: Context,
    target: Class<*>?,
    isFinish: Boolean = false,
    bundle: Bundle? = null
) {
    val intent = Intent(context, target)
    bundle?.let {
        intent.putExtras(bundle)
    }

    context.startActivity(intent)
    if (isFinish) (context as AppCompatActivity).finish()
}

fun goToActivityWithIntent(
    context: Context,
    intent: Intent,
    isFinish: Boolean = false
) {
    context.startActivity(intent)
    if (isFinish) (context as AppCompatActivity).finish()
}