package com.learn.noteapp.presentation.add_edit_note

import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.navigation.toRoute
import com.learn.noteapp.domain.model.InvalidNoteException
import com.learn.noteapp.domain.model.Note
import com.learn.noteapp.domain.use_case.NoteUseCases
import com.learn.noteapp.presentation.AddNoteScreenRoute
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.asSharedFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class AddEditNoteViewModel @Inject constructor(
    private val noteUseCases: NoteUseCases, savedStateHandle: SavedStateHandle
) : ViewModel() {

    private val _noteState = mutableStateOf(NoteState("", "", Note.colors.random().backgroundColor))
    val noteState: State<NoteState> = _noteState

    private val _eventFlow = MutableSharedFlow<UiEvent>()
    val eventFlow = _eventFlow.asSharedFlow()

    private var currentNoteId: Int? = null

    init {
        savedStateHandle.toRoute<AddNoteScreenRoute>().let { noteId ->
            noteId.noteId?.let {
                viewModelScope.launch {
                    noteUseCases.getNote(noteId.noteId)?.also { note ->
                        currentNoteId = note.id
                        _noteState.value = noteState.value.copy(
                            titleText = note.title,
                            contentText = note.content,
                            color = note.color
                        )
                    }
                }
            }
        }
    }

    fun onEvent(event: AddEditNoteEvent) {
        when (event) {
            is AddEditNoteEvent.EnteredTitle -> {
                _noteState.value = noteState.value.copy(
                    titleText = event.value
                )
            }

            is AddEditNoteEvent.EnteredContent -> {
                _noteState.value = noteState.value.copy(
                    contentText = event.value
                )
            }

            is AddEditNoteEvent.ChangeColor -> {
                _noteState.value = noteState.value.copy(
                    color = event.color
                )
            }

            is AddEditNoteEvent.SaveNote -> {
                viewModelScope.launch {
                    try {
                        noteUseCases.addNote(
                            Note(
                                title = noteState.value.titleText,
                                content = noteState.value.contentText,
                                timestamp = System.currentTimeMillis(),
                                color = noteState.value.color,
                                id = currentNoteId ?: 0
                            )
                        )
                        _eventFlow.emit(UiEvent.SaveNote)
                    } catch (e: InvalidNoteException) {
                        _eventFlow.emit(
                            UiEvent.ShowSnackbar(
                                message = e.message ?: "Couldn't save note"
                            )
                        )
                    }
                }
            }
        }
    }

    sealed class UiEvent {
        data class ShowSnackbar(val message: String) : UiEvent()
        data object SaveNote : UiEvent()
    }
}