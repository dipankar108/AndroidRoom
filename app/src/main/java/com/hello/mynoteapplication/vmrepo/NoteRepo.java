package com.hello.mynoteapplication.vmrepo;

import android.app.Application;
import android.os.AsyncTask;

import androidx.lifecycle.LiveData;

import com.hello.mynoteapplication.database.Note;
import com.hello.mynoteapplication.database.NoteDao;
import com.hello.mynoteapplication.database.NoteDatabase;

import java.util.List;

public class NoteRepo {
NoteDatabase noteDatabase;
NoteDao noteDao;
LiveData<List<Note>>getAllnotes;
NoteRepo(Application application){
    noteDatabase=NoteDatabase.getInstance(application);
    noteDao=noteDatabase.noteDao();
    getAllnotes=noteDao.getAllNotes();
}
public void insert(Note note){
    new insertAsync(noteDao).execute(note);
}
public LiveData<List<Note>>mgetAllnotes(){
    return getAllnotes;
}
public class insertAsync extends AsyncTask<Note,Void,Void>{
    NoteDao noteDao;

    public insertAsync(NoteDao noteDao) {
        this.noteDao = noteDao;
    }

    @Override
    protected Void doInBackground(Note... notes) {
        noteDao.insert(notes[0]);
        return null;
    }
}
}
