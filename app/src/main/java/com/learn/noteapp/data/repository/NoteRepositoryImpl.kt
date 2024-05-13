package com.learn.noteapp.data.repository

import com.learn.noteapp.data.data_source.NoteDao
import com.learn.noteapp.domain.model.Note
import com.learn.noteapp.domain.repository.NoteRepository
import kotlinx.coroutines.flow.Flow

class NoteRepositoryImpl(private val dao: NoteDao) : NoteRepository {

    override fun getNotes(title: String): Flow<List<Note>> {
        return dao.getAllNote(title)
    }

    override suspend fun getNoteById(id: Int): Note {
        return dao.getNote(id)
    }

    override suspend fun insertNote(note: Note) {
        dao.noteInsert(note)
    }

    override suspend fun deleteNote(note: Note) {
        dao.noteDelete(note)
    }
}