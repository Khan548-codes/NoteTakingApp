package com.example.hackathon

import android.os.Build
import androidx.annotation.RequiresApi
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.hackathon.AddEditNoteDialog
import com.example.hackathon.DeleteConfirmationDialog
import com.example.hackathon.NoteItem
import com.example.hackathon.NoteViewModel
import com.example.hackathon.Note

@RequiresApi(Build.VERSION_CODES.O)
@Composable
fun NoteScreen(viewModel: NoteViewModel = viewModel()) {
    val notes by viewModel.allNotes.collectAsState()
    var showDialog by remember { mutableStateOf(false) }
    var noteToEdit by remember { mutableStateOf<Note?>(null) }
    var noteToDelete by remember { mutableStateOf<Note?>(null) }

    Scaffold(
        // Set light purple background color for the screen
        containerColor = Color(0xFFB39DDB), // Light Purple color
        floatingActionButton = {
            FloatingActionButton(onClick = { showDialog = true }) {
                Icon(Icons.Default.Add, contentDescription = "Add Note")
            }
        }
    ) { paddingValues ->
        LazyColumn(modifier = Modifier.padding(paddingValues)) {
            items(notes) { note ->
                NoteItem(
                    note = note,
                    onEdit = {
                        noteToEdit = it
                        showDialog = true
                    },
                    onDelete = {
                        noteToDelete = it
                    }
                )
            }
        }

        if (showDialog) {
            AddEditNoteDialog(
                initialNote = noteToEdit,
                onDismiss = {
                    showDialog = false
                    noteToEdit = null
                },
                onSave = {
                    viewModel.addNote(it)
                    showDialog = false
                    noteToEdit = null
                }
            )
        }

        noteToDelete?.let {
            DeleteConfirmationDialog(
                note = it,
                onConfirm = {
                    viewModel.deleteNote(it)
                    noteToDelete = null
                },
                onDismiss = {
                    noteToDelete = null
                }
            )
        }
    }
}
