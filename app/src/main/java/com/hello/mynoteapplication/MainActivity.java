package com.hello.mynoteapplication;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Toast;

import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.hello.mynoteapplication.adapter.MyNoteAdapeter;
import com.hello.mynoteapplication.database.Note;
import com.hello.mynoteapplication.vmrepo.NoteVIewModel;

import java.util.List;

public class MainActivity extends AppCompatActivity implements RecyleClick{
NoteVIewModel noteVIewModel;
RecyclerView recyclerView;
FloatingActionButton floatingActionButton;
int ADD_REQ_RESULT_CODE=1;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        recyclerView=findViewById(R.id.recyclerViewId);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        MyNoteAdapeter myNoteAdapeter=new MyNoteAdapeter(this);
        recyclerView.setAdapter(myNoteAdapeter);
        floatingActionButton=findViewById(R.id.floatingActionbtn_Id);
        floatingActionButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent=new Intent(MainActivity.this,AddActivity.class);
                startActivityForResult(intent,ADD_REQ_RESULT_CODE);
            }
        });
        noteVIewModel=new ViewModelProvider(this).get(NoteVIewModel.class);
        noteVIewModel.getGetAllNotes().observe(this, new Observer<List<Note>>() {
    @Override
    public void onChanged(List<Note> notes) {
        myNoteAdapeter.setAllNotes(notes);
    }
});
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode==ADD_REQ_RESULT_CODE && resultCode==RESULT_OK){

            String title= data.getStringExtra(AddActivity.TITLE_EXTRA);
            String desc=data.getStringExtra(AddActivity.DESC_EXTRA);
            int priority=data.getIntExtra(AddActivity.PRIORITY_EXTRA,1);
            Note note=new Note(title,desc,priority);
            noteVIewModel.insert(note);
            Log.d("TAG", "onActivityResult: "+title+desc+priority);
        }
        else{
            Toast.makeText(MainActivity.this,"Note not inserted",Toast.LENGTH_SHORT).show();
        }
    }

    @Override
    public void onClick(int i) {
        Log.d("TAG", "onClick: "+noteVIewModel.getGetAllNotes().getValue().get(i).getTitle());
    }
}