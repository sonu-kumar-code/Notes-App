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
            ColorModel(backgroundColor = R.color.teal_200),
            ColorModel(backgroundColor = R.color.red),
            ColorModel(backgroundColor = R.color.cyan),
            ColorModel(backgroundColor = R.color.blue),
            ColorModel(backgroundColor = R.color.dark_blue),
            ColorModel(backgroundColor = R.color.light_blue),
            ColorModel(backgroundColor = R.color.purple),
            ColorModel(backgroundColor = R.color.yellow),
            ColorModel(backgroundColor = R.color.lime),
            ColorModel(backgroundColor = R.color.magenta),
            ColorModel(backgroundColor = R.color.pink),
            ColorModel(backgroundColor = R.color.silver),
            ColorModel(backgroundColor = R.color.gray),
            ColorModel(backgroundColor = R.color.orange),
            ColorModel(backgroundColor = R.color.brown),
            ColorModel(backgroundColor = R.color.maroon),
            ColorModel(backgroundColor = R.color.green),
            ColorModel(backgroundColor = R.color.olive),
            ColorModel(backgroundColor = R.color.aquamarine),
        )
    }

    data class ColorModel(
        val titleColor: Int = R.color.white,
        val contentColor: Int = R.color.white,
        val backgroundColor: Int,
        val deleteIconColor: Int = R.color.white
    )
}

class InvalidNoteException(message: String) : Exception(message)
