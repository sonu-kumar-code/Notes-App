package com.learn.noteapp.presentation.add_edit_note

data class NoteState(
    val titleText: String = "",
    val contentText: String = "",
    val color: Int = -1,
)