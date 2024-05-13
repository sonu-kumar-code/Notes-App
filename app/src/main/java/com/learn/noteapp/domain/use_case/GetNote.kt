package com.learn.noteapp.domain.use_case

import com.learn.noteapp.domain.model.Note
import com.learn.noteapp.domain.repository.NoteRepository

class GetNote(
    private val repository: NoteRepository
) {

    suspend operator fun invoke(id: Int): Note? {
        return repository.getNoteById(id)
    }
}