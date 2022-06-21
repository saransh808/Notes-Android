package com.example.n01452387_notes;


import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.recyclerview.widget.StaggeredGridLayoutManager;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.lang.reflect.Type;
import java.util.ArrayList;

public class MainActivity extends AppCompatActivity implements SelectListener{

    private static final int REQUEST_CODE_CREATE = 101;
    private static final int REQUEST_CODE_EDIT = 102;
    private RecyclerView recyclerView;
    static NotesRepository notesRepository;
    static NotesViewAdapter adapter;
    Button newNote;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        handleSharedPref();
        newNote = findViewById(R.id.new_note);
        newNote.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(MainActivity.this, NoteEdit.class).putExtra("nodata", ""));
                Toast.makeText(getApplicationContext(), "Creating New Note", Toast.LENGTH_LONG).show();
            }
        });
        recyclerView=findViewById(R.id.notes_rec_view);
        adapter=new NotesViewAdapter(NotesRepository.getAllNotes(),this, this);
        StaggeredGridLayoutManager layoutManager=new StaggeredGridLayoutManager(2,StaggeredGridLayoutManager.VERTICAL);
        recyclerView.setLayoutManager(layoutManager);
        recyclerView.setAdapter(adapter);
    }

    private void handleSharedPref() {
        System.out.println("Trying hit on shared Pref");
        SharedPreferences sharedPreferences = getSharedPreferences(getString(R.string.prefName), MODE_PRIVATE);
        Gson gson = new Gson();
        String response = sharedPreferences.getString(getString(R.string.prefItem) , "[]");
        System.out.println(response);
        Type userListType = new TypeToken<ArrayList<Note>>(){}.getType();
        ArrayList<Note> notesFromPref = gson.fromJson(response,userListType);
        System.out.println(notesFromPref.toArray());
        NotesRepository.initializeNotesFromPref(notesFromPref);
    }

    @Override
    public void onNoteClicked(Note note, Boolean checked) {
        startActivity(new Intent(MainActivity.this, NoteEdit.class).putExtra("data", note));
    }
}