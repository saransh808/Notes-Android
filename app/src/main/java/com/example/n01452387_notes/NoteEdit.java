package com.example.n01452387_notes;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.google.gson.Gson;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.Set;

public class NoteEdit extends AppCompatActivity {

    private static final int REQUEST_CODE_CREATE = 101;
    private static final int REQUEST_CODE_EDIT = 102;
    private Note note;
    EditText et_note_title, et_note_content;
    CheckBox cb_note_checkbox;
    Button saveNote;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_note_edit);
        et_note_title = findViewById(R.id.et_note_title);
        et_note_content = findViewById(R.id.et_note_content);
        cb_note_checkbox = findViewById(R.id.cb_note_checkbox);

        if(getIntent().hasExtra("data")){
            note = (Note) getIntent().getSerializableExtra("data");
            et_note_title.setText(note.getNoteTitle());
            et_note_content.setText(note.getNoteContent());
            cb_note_checkbox.setChecked(note.getNotePriority());
        }
        saveNote = findViewById(R.id.save_note);
        saveNote.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                handleSaveOrEditNote();
            }
        });
    }

    private void handleSaveOrEditNote() {
        if(!et_note_title.getText().toString().isEmpty() || !et_note_content.getText().toString().isEmpty()) {
            if (note == null) {
                note = new Note(et_note_title.getText().toString(), cb_note_checkbox.isChecked(), et_note_content.getText().toString());
                NotesRepository.getAllNotes().add(note);
                Toast.makeText(getApplicationContext(), "Created New Note : "+et_note_title.getText().toString(), Toast.LENGTH_LONG).show();
            } else {
                note.setNoteTitle(et_note_title.getText().toString());
                note.setNotePriority(cb_note_checkbox.isChecked());
                note.setNoteContent(et_note_content.getText().toString());
                System.out.println("Edited Note : " + note.toString());
                NotesRepository.updateNote(note);
                Toast.makeText(getApplicationContext(), "Edited Note : "+et_note_title.getText().toString(), Toast.LENGTH_LONG).show();
            }
        }else if(et_note_title.getText().toString().isEmpty() && et_note_content.getText().toString().isEmpty()){
            Toast.makeText(getApplicationContext(), "Can't Save Empty Note", Toast.LENGTH_LONG).show();

        }
        HandleSharedPref.createUpdateSharedPref(getApplicationContext());
        MainActivity.adapter.notifyDataSetChanged();
        finish();
    }

    @Override
    public void onBackPressed() {
        handleSaveOrEditNote();
    }

}
