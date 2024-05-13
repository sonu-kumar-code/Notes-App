package com.learn.noteapp.data.data_source

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.Query
import com.learn.noteapp.domain.model.Note
import kotlinx.coroutines.flow.Flow

@Dao
interface NoteDao {

    @Insert
    suspend fun noteInsert(note: Note)

    @Delete
    suspend fun noteDelete(note: Note)

    @Query("SELECT * FROM note where title like :title")
    fun getAllNote(title:String): Flow<List<Note>>

    @Query("SELECT * FROM note where id=:id")
    suspend fun getNote(id: Int): Note

}