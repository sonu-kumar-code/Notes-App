package com.learn.noteapp.domain.model

import androidx.annotation.ColorRes
import androidx.room.Entity
import androidx.room.PrimaryKey
import com.learn.noteapp.R

@Entity
data class Note(
    @PrimaryKey(autoGenerate = true) val id: Int = 1,
    val title: String,
    val content: String,
    val timestamp: Long,
    @ColorRes val color: Int
) {
    companion object {
        val colors = listOf(
            ColorModel(backgroundColor = R.color.teal_200)
        )
    }

    data class ColorModel(
        val titleColor: Int = R.color.white,
        val contentColor: Int = R.color.white,
        val backgroundColor: Int,
        val deleteIconColor: Int = R.color.white
    )
}
