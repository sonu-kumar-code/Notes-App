package com.learn.noteapp.data

import androidx.room.Database
import androidx.room.RoomDatabase
import com.learn.noteapp.domain.model.Note

@Database(
    entities = [Note::class], version = 1
)
abstract class NoteDatabase : RoomDatabase() {
    abstract fun noteDao(): NoteDao

    companion object{
        const val DATABASE_NAME = "note_database"
    }
}