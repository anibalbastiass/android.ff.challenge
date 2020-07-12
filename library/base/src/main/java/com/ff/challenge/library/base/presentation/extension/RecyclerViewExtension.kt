package com.ff.challenge.library.base.presentation.extension

import android.view.animation.AnimationUtils
import androidx.recyclerview.widget.RecyclerView
import com.ff.challenge.library.base.R

object RecyclerViewExtension {
    fun RecyclerView.runLayoutAnimation() {
        layoutAnimation = AnimationUtils.loadLayoutAnimation(context, R.anim.layout_animation_fall_down)
        adapter?.notifyDataSetChanged()
        scheduleLayoutAnimation()
    }
}