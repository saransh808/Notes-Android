package com.example.n01452387_notes;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;

public class NotesRepository {

    private static ArrayList<Note> notes = new ArrayList<Note>();

    public static void initializeNotesFromPref(ArrayList<Note> prefNotes){
        if(notes.isEmpty()){
            notes.addAll(prefNotes);
        }
    }

    public static ArrayList<Note> getAllNotes(){
        Collections.sort(notes, new Comparator<Note>() {
            @Override
            public int compare(Note n1, Note n2) {
                return Boolean.compare(n2.getNotePriority(),n1.getNotePriority());
            }
        });
        return notes;
    }

    public static void updateNote(Note note) {
        Integer index = notes.indexOf(note);
        Note updatedNote = notes.get(index);
        updatedNote.setNoteTitle(note.getNoteTitle());
        updatedNote.setNoteContent(note.getNoteContent());
        updatedNote.setNotePriority(note.getNotePriority());
        notes.set(index, updatedNote);
        Collections.sort(notes, new Comparator<Note>() {
            @Override
            public int compare(Note n1, Note n2) {
                return Boolean.compare(n2.getNotePriority(),n1.getNotePriority());
            }
        });
    }

    public static void updateNoteChecked(int position, String noteId, boolean checked) {
        System.out.println("updateNoteChecked : Looking for note id : "+noteId);
        Note updatedNote = notes.get(position);
        updatedNote.setNotePriority(checked);
        notes.set(position, updatedNote);
        Collections.sort(notes, new Comparator<Note>() {
            @Override
            public int compare(Note n1, Note n2) {
                return Boolean.compare(n2.getNotePriority(),n1.getNotePriority());
            }
        });
    }
}
