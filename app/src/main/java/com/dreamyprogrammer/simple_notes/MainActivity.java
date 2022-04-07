package com.dreamyprogrammer.simple_notes;

import android.content.res.Configuration;
import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;

import com.dreamyprogrammer.simple_notes.model.repositories.TaskElement;

public class MainActivity extends AppCompatActivity implements ListNotes.Controller, EditListNotes.Controller {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        getSupportFragmentManager()
                .beginTransaction()
                .add(R.id.container, new ListNotes())
                .commit();


    }

    @Override
    public void openNotes(TaskElement taskElement) {
        boolean isLandscape = getResources().getConfiguration().orientation == Configuration.ORIENTATION_LANDSCAPE;
        if (isLandscape) {
            getSupportFragmentManager()
                    .beginTransaction()
                    .replace(R.id.detail_container, EditListNotes.newInstance(taskElement))
                    .commit();
        } else {
            getSupportFragmentManager()
                    .beginTransaction()
                    .replace(R.id.container, EditListNotes.newInstance(taskElement))
                    .addToBackStack(null)
                    .commit();
        }
    }

    @Override
    public void saveNotes(TaskElement taskElement) {

    }
}