package com.learn.noteapp.di

import android.app.Application
import androidx.room.Room
import com.learn.noteapp.data.data_source.NoteDatabase
import com.learn.noteapp.data.repository.NoteRepositoryImpl
import com.learn.noteapp.domain.repository.NoteRepository
import com.learn.noteapp.domain.use_case.AddNote
import com.learn.noteapp.domain.use_case.DeleteNote
import com.learn.noteapp.domain.use_case.GetNote
import com.learn.noteapp.domain.use_case.GetNotes
import com.learn.noteapp.domain.use_case.NoteUseCases
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object NoteAppModule {

    @Provides
    @Singleton
    fun provideNoteDatabase(app: Application): NoteDatabase {
        return Room.databaseBuilder(
            app, NoteDatabase::class.java, NoteDatabase.DATABASE_NAME
        ).build()
    }

    @Provides
    @Singleton
    fun provideNoteRepository(db: NoteDatabase): NoteRepository {
        return NoteRepositoryImpl(db.noteDao)
    }

    @Provides
    @Singleton
    fun provideNoteUseCases(repository: NoteRepository): NoteUseCases {
        return NoteUseCases(
            getNotes = GetNotes(repository),
            deleteNote = DeleteNote(repository),
            addNote = AddNote(repository),
            getNote = GetNote(repository)
        )
    }
}