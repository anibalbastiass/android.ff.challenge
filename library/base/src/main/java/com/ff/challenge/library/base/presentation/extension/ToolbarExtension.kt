package com.ff.challenge.library.base.presentation.extension

import android.app.Activity
import android.content.res.Resources
import android.util.TypedValue
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.widget.Toolbar
import androidx.core.content.res.ResourcesCompat
import com.ff.challenge.library.base.R

object ToolbarExtension {
    fun Toolbar.applyFontForToolbarTitle(context: Activity, color: Int = 0) {
        for (i in 0 until this.childCount) {
            val view = this.getChildAt(i)
            if (view is TextView) {
                try {
                    val titleFont = ResourcesCompat.getFont(context, R.font.opensans_regular)
                    val subtitleFont = ResourcesCompat.getFont(context, R.font.opensans_regular)
                    if (view.text == this.title) {
                        view.setTextSize(TypedValue.COMPLEX_UNIT_SP, 16.0f)
                        view.typeface = titleFont
                    } else if (view.text == this.subtitle) {
                        view.setTextSize(TypedValue.COMPLEX_UNIT_SP, 12.0f)
                        view.typeface = subtitleFont
                        break
                    }
                } catch (e: Resources.NotFoundException) {
                    e.printStackTrace()
                }
            }
        }
        (context as AppCompatActivity).setSupportActionBar(this)
    }

    fun Toolbar.setArrowUpToolbar(context: Activity) {
        (context as AppCompatActivity).setSupportActionBar(this)
        context.supportActionBar?.setDisplayHomeAsUpEnabled(true)
    }

    fun Toolbar.setNoArrowUpToolbar(context: Activity) {
        (context as AppCompatActivity).setSupportActionBar(this)
        context.supportActionBar?.setDisplayHomeAsUpEnabled(false)
    }
}