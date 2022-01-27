package com.hello.mynoteapplication.vmrepo;

import android.app.Application;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;

import com.hello.mynoteapplication.database.Note;

import java.util.List;

public class NoteVIewModel extends AndroidViewModel {
    NoteRepo noteRepo;
    LiveData<List<Note>>getAllNotes;
    public NoteVIewModel(Application application) {
        super(application);
        noteRepo=new NoteRepo(application);
        getAllNotes=noteRepo.mgetAllnotes();
    }
  public LiveData<List<Note>>getGetAllNotes(){
        return getAllNotes;
    }
    public void insert(Note note){
        noteRepo.insert(note);
    }
}
