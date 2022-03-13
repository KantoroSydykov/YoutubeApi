package com.example.youtubeapi.core.extensions

import android.view.View
import android.widget.ImageView
import com.bumptech.glide.Glide

fun ImageView.load(resource:String){

    Glide.with(this)
        .load(resource)
        .centerCrop()
        .into(this)
}

fun View.visible() {
    visibility = View.VISIBLE
}

fun View.invisible() {
    visibility = View.INVISIBLE
}

fun View.gone() {
    visibility = View.GONE
}