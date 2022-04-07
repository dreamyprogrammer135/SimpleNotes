package com.dreamyprogrammer.simple_notes.model.repositories;

import android.os.Parcel;
import android.os.Parcelable;

public class Note implements Parcelable {
    public static final Creator<Note> CREATOR = new Creator<Note>() {
        @Override
        public Note createFromParcel(Parcel in) {
            return new Note(in);
        }

        @Override
        public Note[] newArray(int size) {
            return new Note[size];
        }
    };
    private String note;
    private Boolean completed;

    public Note() {
    }

    public Note(String note, Boolean completed) {
        this.note = note;
        this.completed = completed;
    }

    protected Note(Parcel in) {
        note = in.readString();
        byte tmpCompleted = in.readByte();
        completed = tmpCompleted == 0 ? null : tmpCompleted == 1;
    }

    public String getNote() {
        return note;
    }

    public void setNote(String note) {
        this.note = note;
    }

    public Boolean getCompleted() {
        return completed;
    }

    public void setCompleted(Boolean completed) {
        this.completed = completed;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel parcel, int i) {
        parcel.writeString(note);
        parcel.writeByte((byte) (completed == null ? 0 : completed ? 1 : 2));
    }
}
