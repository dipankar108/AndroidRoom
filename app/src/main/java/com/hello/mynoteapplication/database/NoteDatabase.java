package com.hello.mynoteapplication.database;

import android.app.Application;
import android.content.Context;
import android.os.AsyncTask;

import androidx.annotation.NonNull;
import androidx.room.Database;
import androidx.room.Room;
import androidx.room.RoomDatabase;
import androidx.sqlite.db.SupportSQLiteDatabase;

@Database(entities = {Note.class},version = 1)
public abstract class NoteDatabase extends RoomDatabase {
    public static NoteDatabase instance;
    public  abstract NoteDao noteDao();
    public static synchronized NoteDatabase getInstance(Context context){
        if (instance==null){
            instance= Room.databaseBuilder(context,NoteDatabase.class,"note_databaseInstance")
                    .fallbackToDestructiveMigration()
                    .addCallback(roomCallBack)
                    .build();
        }
        return instance;
    }
  public static   RoomDatabase.Callback roomCallBack=new RoomDatabase.Callback() {
        @Override
        public void onCreate(SupportSQLiteDatabase db) {
            super.onCreate(db);
            new AscynInsert(instance).execute();
        }
    };
    private static class AscynInsert extends AsyncTask<Note,Void,Void>{
        NoteDatabase noteDatabase;
NoteDao noteDao;
        public AscynInsert(NoteDatabase noteDatabase) {
            this.noteDatabase = noteDatabase;
        }

        @Override
        protected Void doInBackground(Note... notes) {
            noteDao=noteDatabase.noteDao();
            noteDao.insert(new Note("Title1","Desc1",1));
            return null;
        }
    }
}
