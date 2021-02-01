package com.example.todolist.data

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity
data class Todo(@ColumnInfo(name = "name") var name: String = "",
                @ColumnInfo(name = "checked") var checked: Boolean = false) {
    @PrimaryKey(autoGenerate = true) var id: Int = 0
}
