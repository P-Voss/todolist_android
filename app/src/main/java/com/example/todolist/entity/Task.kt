package com.example.todolist.entity

import com.example.todolist.Enums.DelegatedState
import com.example.todolist.Enums.Priority
import kotlinx.serialization.Serializable
import java.text.SimpleDateFormat
import java.util.Date

@Serializable
data class Task(

    val id: Int? = null,
    var title: String = "",
    var description: String = "",
    var priority: Priority = Priority.LOW,
    var creationDate: Date = Date(),
    var dueDate: Date? = null,
    var creator: User? = null,
    var creatorName: String = "",
    var owner: User? = null,
    var ownerName: String = "",
    var delegatedState: DelegatedState = DelegatedState.NOT_DELEGATED
)
{
    fun getFormattedDueDate(): String {
        val dateFormat = SimpleDateFormat("dd.MM.yyyy")
        return dateFormat.format(dueDate)
    }

    fun getFormattedCreationDate(): String {
        val dateFormat = SimpleDateFormat("dd.MM.yyyy")
        return dateFormat.format(creationDate)
    }
}