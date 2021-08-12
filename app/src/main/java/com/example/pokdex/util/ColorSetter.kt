package com.example.pokdex.util

import android.graphics.Bitmap
import android.graphics.Color
import android.graphics.drawable.BitmapDrawable
import android.graphics.drawable.Drawable
import android.os.Build
import androidx.annotation.RequiresApi
import androidx.palette.graphics.Palette

@RequiresApi(Build.VERSION_CODES.O)
fun getColorFromImage(image: Drawable, onFinish: (Color) -> Unit) {
    val bitmap = (image as BitmapDrawable).bitmap.copy(Bitmap.Config.ARGB_8888, true)

    Palette.from(bitmap).generate { palette ->
        palette?.dominantSwatch?.rgb?.let { colorValue ->
            onFinish(Color.valueOf(colorValue))
        }
    }
}