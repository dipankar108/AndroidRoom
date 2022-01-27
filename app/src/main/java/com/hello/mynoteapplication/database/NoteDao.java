package com.hello.mynoteapplication.database;

import androidx.lifecycle.LiveData;
import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;
import androidx.room.Update;

import java.util.List;
@Dao
public interface NoteDao {
    @Insert
    void insert(Note note);
    @Query("SELECT * FROM note_data ORDER BY priority ASC")
    LiveData<List<Note>>getAllNotes();
}
