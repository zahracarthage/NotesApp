package com.example.notesapp.database

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.example.notesapp.dao.NoteDao
import com.example.notesapp.entities.Notes

@Database(entities = [Notes::class],version=1,exportSchema = false)
abstract class NotesDatabase: RoomDatabase() {

    companion object{
        var notesDatabase:NotesDatabase? = null

        @Synchronized
        fun getnotesdb(context: Context): NotesDatabase {
            if (notesDatabase != null)
             {
                notesDatabase = Room.databaseBuilder(context,
                    NotesDatabase::class.java,
                "notes.db").build()
            }
            return notesDatabase!!;
        }

    }

    abstract fun noteDato(): NoteDao


}