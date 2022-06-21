package com.example.n01452387_notes;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

public class NotesViewAdapter extends RecyclerView.Adapter<NotesViewAdapter.NotesViewHolder>{

    private Context mcontext;
    private SelectListener listener;

    public NotesViewAdapter(ArrayList<Note> notes, Context mcontext, SelectListener listener) {
        this.mcontext = mcontext;
        this.listener = listener;
    }

    @NonNull
    @Override
    public NotesViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.notes_card, parent, false);
        return new NotesViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull NotesViewHolder holder, @SuppressLint("RecyclerView") int position) {
        int pos = position;
        Note noteForCard = NotesRepository.getAllNotes().get(position);
        holder.note_title.setText(noteForCard.getNoteTitle());
        holder.note_content.setText(noteForCard.getNoteContent());
        holder.note_datetime.setText(noteForCard.getNoteDateTime());
        holder.note_priority.setChecked(noteForCard.getNotePriority());
        holder.note_priority.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                System.out.println("INVOKED CHECKBOX");
                if(!noteForCard.getNotePriority()){
                    System.out.println("Checked : "+noteForCard);
                    NotesRepository.updateNoteChecked(position, noteForCard.getNoteId(), false);
                    noteForCard.setNotePriority(true);
                    Toast.makeText(mcontext, "Note : "+noteForCard.getNoteTitle()+" - HIGH PRIORITY", Toast.LENGTH_LONG).show();
                }else if(noteForCard.getNotePriority()){
                    System.out.println("UnChecked : "+noteForCard);
                    NotesRepository.updateNoteChecked(position, noteForCard.getNoteId(), true);
                    noteForCard.setNotePriority(false);
                    Toast.makeText(mcontext, "Note : "+noteForCard.getNoteTitle()+" - LOW PRIORITY", Toast.LENGTH_LONG).show();
                }
                HandleSharedPref.createUpdateSharedPref(mcontext);
                MainActivity.adapter.notifyDataSetChanged();
            }
        });

        holder.note_card.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                listener.onNoteClicked(NotesRepository.getAllNotes().get(position), NotesRepository.getAllNotes().get(position).getNotePriority());
                Toast.makeText(mcontext.getApplicationContext(), "Edit Note : "+NotesRepository.getAllNotes().get(position).getNoteTitle(), Toast.LENGTH_LONG).show();
            }
        });
    }

    @Override
    public int getItemCount() {
        return NotesRepository.getAllNotes().size();
    }

    public class NotesViewHolder extends RecyclerView.ViewHolder{
        private TextView note_title;
        private TextView note_content;
        private TextView note_datetime;
        private CheckBox note_priority;
        private CardView note_card;
        public NotesViewHolder(@NonNull View view) {
            super(view);
            note_title = view.findViewById(R.id.note_title);
            note_content = view.findViewById(R.id.note_content);
            note_datetime = view.findViewById(R.id.note_datetime);
            note_priority = view.findViewById(R.id.note_checkbox);
            note_card = view.findViewById(R.id.note_card);
        }
    }
}
