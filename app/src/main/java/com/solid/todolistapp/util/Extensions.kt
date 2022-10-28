package com.solid.todolistapp.util

import android.widget.Button
import androidx.annotation.ColorRes
import androidx.core.content.ContextCompat

fun Button.invert(
    checked: Boolean?,
    @ColorRes checkedColorText: Int, @ColorRes uncheckedColorText: Int,
    @ColorRes checkedColorBg: Int, @ColorRes uncheckedColorBg: Int
) {
    if (checked == true) {
        setTextColor(ContextCompat.getColor(context, checkedColorText))
        setBackgroundColor(ContextCompat.getColor(context, checkedColorBg))
    } else {
        setTextColor(ContextCompat.getColor(context, uncheckedColorText))
        setBackgroundColor(ContextCompat.getColor(context, uncheckedColorBg))
    }
}
