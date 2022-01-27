package com.hello.mynoteapplication.adapter;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.hello.mynoteapplication.R;
import com.hello.mynoteapplication.RecyleClick;
import com.hello.mynoteapplication.database.Note;

import java.util.ArrayList;
import java.util.List;

public class MyNoteAdapeter extends RecyclerView.Adapter<MyNoteAdapeter.NoteViewHolder>{
   RecyleClick recyleClick;

    public MyNoteAdapeter(RecyleClick recyleClick) {
        this.recyleClick = recyleClick;
    }

    List<Note>mallNotes=new ArrayList<>();
    @Override
    public NoteViewHolder onCreateViewHolder( ViewGroup parent, int viewType) {
        View view= LayoutInflater.from(parent.getContext()).inflate(R.layout.itemview,parent,false);
        return new NoteViewHolder(view);
    }

    @Override
    public void onBindViewHolder( NoteViewHolder holder, int position) {
    Note note= mallNotes.get(position);
    holder.title.setText(note.getTitle());
    holder.desc.setText(note.getDesc());
    holder.priority.setText(String.valueOf(note.getPriority()));
    }

    @Override
    public int getItemCount() {
        return mallNotes.size();
    }
    public void setAllNotes(List<Note>mallNotes){
        this.mallNotes=mallNotes;
        notifyDataSetChanged();
    }
    protected class NoteViewHolder extends RecyclerView.ViewHolder{
        TextView title,desc,priority;
    public NoteViewHolder(View itemView) {
        super(itemView);
     title=itemView.findViewById(R.id.text_view_title_id);
          desc=itemView.findViewById(R.id.text_view_desc_id);
         priority=itemView.findViewById(R.id.text_view_priority_id);
         itemView.setOnClickListener(new View.OnClickListener() {
             @Override
             public void onClick(View view) {
                 recyleClick.onClick(getAdapterPosition());
             }
         });
    }
}

}
