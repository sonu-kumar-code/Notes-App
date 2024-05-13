package com.learn.noteapp.domain.use_case

import com.learn.noteapp.domain.model.Note
import com.learn.noteapp.domain.repository.NoteRepository

class DeleteNote(
    private val repository: NoteRepository
) {

    suspend operator fun invoke(note: Note) {
        repository.deleteNote(note)
    }
}