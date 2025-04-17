package com.example.hackathon

import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import com.example.hackathon.Note



@Composable
fun DeleteConfirmationDialog(note: Note, onConfirm: () -> Unit, onDismiss: () -> Unit) {
    AlertDialog(
        onDismissRequest = onDismiss,
        title = { Text("Delete Note") },
        text = { Text("Are you sure you want to delete this note?") },
        confirmButton = {
            TextButton(onClick = onConfirm) { Text("Delete") }
        },
        dismissButton = {
            TextButton(onClick = onDismiss) { Text("Cancel") }
        }
    )
}
