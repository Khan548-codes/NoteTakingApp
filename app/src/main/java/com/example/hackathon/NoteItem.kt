package com.example.hackathon

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.background
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Delete
import androidx.compose.material.icons.filled.Edit
import kotlin.random.Random

@Composable
fun NoteItem(note: Note, onEdit: (Note) -> Unit, onDelete: (Note) -> Unit) {
    // Generate a pastel color for the background
    val pastelColor = generatePastelColor()

    Card(
        modifier = Modifier
            .fillMaxWidth()
            .padding(8.dp),
        elevation = CardDefaults.cardElevation(),
        shape = RoundedCornerShape(16.dp) // Rounded corners with radius of 16dp for the Card
    ) {
        // Set the pastel background color for the note card
        Column(
            modifier = Modifier
                .padding(16.dp)
                .background(pastelColor, shape = RoundedCornerShape(12.dp)) // Rounded corners for inner box
        ) {
            Row(Modifier.fillMaxWidth(), horizontalArrangement = Arrangement.SpaceBetween) {
                Text(
                    note.title,
                    style = MaterialTheme.typography.titleMedium,
                    modifier = Modifier.padding(start = 8.dp) // Adjust the value as needed
                )

                Row {
                    IconButton(onClick = { onEdit(note) }) {
                        Icon(Icons.Default.Edit, contentDescription = "Edit")
                    }
                    IconButton(onClick = { onDelete(note) }) {
                        Icon(Icons.Default.Delete, contentDescription = "Delete")
                    }
                }
            }
            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.End // This will place the text at the right-hand side
            ) {
                Text(
                    note.date + " " + note.time,
                    style = MaterialTheme.typography.bodySmall,
                    modifier = Modifier.padding(start = 8.dp) // Optional padding from the left
                )
            }


            Text(
                note.description,
                style = MaterialTheme.typography.bodyMedium,
                modifier = Modifier.padding(start = 8.dp) // Adjust the value as needed
            )

        }
    }
}

// Function to generate a pastel color
fun generatePastelColor(): Color {
    val random = Random

    // Generate pastel colors by ensuring the RGB values are higher (lighter)
    val red = (random.nextFloat() * 0.5f + 0.5f)  // Value between 0.5 and 1.0
    val green = (random.nextFloat() * 0.5f + 0.5f)  // Value between 0.5 and 1.0
    val blue = (random.nextFloat() * 0.5f + 0.5f)  // Value between 0.5 and 1.0

    return Color(red, green, blue)
}
