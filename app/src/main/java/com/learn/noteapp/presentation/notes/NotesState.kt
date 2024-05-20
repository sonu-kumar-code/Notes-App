package com.learn.noteapp.presentation.notes

import com.learn.noteapp.domain.model.Note
import com.learn.noteapp.domain.util.NoteOrder
import com.learn.noteapp.domain.util.OrderType

data class NotesState(
    val notes: List<Note> = emptyList(),
    val noteOrder: NoteOrder = NoteOrder.Date(OrderType.Descending),
    val isOrderSectionVisible: Boolean = false,
    val searchText: String = ""
)