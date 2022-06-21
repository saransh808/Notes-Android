package com.example.n01452387_notes;

import java.io.Serializable;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Objects;
import java.util.UUID;

public class Note implements Serializable {
    private String noteId;
    private String noteTitle;
    private Boolean notePriority;
    private String noteContent;
    private String noteDateTime;

    public Note(String noteTitle, Boolean notePriority, String noteContent) {
        this.noteId = UUID.randomUUID().toString();
        this.noteTitle = noteTitle;
        this.notePriority = notePriority;
        this.noteContent = noteContent;
        this.noteDateTime = getDateTime();
    }

    public String getNoteTitle() {
        return noteTitle;
    }

    public void setNoteTitle(String noteTitle) {
        this.noteTitle = noteTitle;
    }

    public Boolean getNotePriority() {
        return notePriority;
    }

    public void setNotePriority(Boolean notePriority) {
        this.notePriority = notePriority;
    }

    public String getNoteContent() {
        return noteContent;
    }

    public void setNoteContent(String noteContent) {
        this.noteContent = noteContent;
    }

    public String getNoteDateTime() {
        return noteDateTime;
    }

    public void setNoteDateTime(String noteDateTime) {
        this.noteDateTime = noteDateTime;
    }

    public String getNoteId() {
        return noteId;
    }

    public void setNoteId(String noteId) {
        this.noteId = noteId;
    }

    public String getDateTime(){
        SimpleDateFormat format = new SimpleDateFormat("MMM dd, yyyy ");
        String dateString = format.format( new Date() );
        return dateString;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Note note = (Note) o;
        return noteId.equals(note.noteId);
    }

    @Override
    public int hashCode() {
        return Objects.hash(noteId);
    }

    @Override
    public String toString() {
        return "Note{" +
                "noteId=" + noteId +
                ", noteTitle='" + noteTitle + '\'' +
                ", notePriority=" + notePriority +
                ", noteContent='" + noteContent + '\'' +
                ", noteDateTime='" + noteDateTime + '\'' +
                '}';
    }
}
