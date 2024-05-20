package com.learn.noteapp.presentation.notes

import com.learn.noteapp.domain.model.Note
import com.learn.noteapp.domain.util.NoteOrder

sealed class NotesEvent {
    data class Order(val noteOrder: NoteOrder) : NotesEvent()
    data class DeleteNote(val note: Note) : NotesEvent()
    data object RestoreNote : NotesEvent()
    data object ToggleOrderSection : NotesEvent()
    data class SearchItem(val searchText: String) : NotesEvent()
}