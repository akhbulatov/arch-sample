package com.akhbulatov.archsample.presentation.utils

import android.databinding.BindingAdapter
import android.graphics.drawable.Drawable
import android.view.View
import android.widget.ImageView
import com.squareup.picasso.Picasso

@BindingAdapter("visible")
fun isVisible(view: View, show: Boolean) {
    view.visibility = if (show) View.VISIBLE else View.GONE
}

@BindingAdapter("imageUrl", "placeholder")
fun loadImage(view: ImageView, url: String?, placeholder: Drawable) {
    Picasso.get()
        .load(url)
        .placeholder(placeholder)
        .into(view)
}