package com.dreamyprogrammer.simple_notes;

import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.FragmentManager;

import com.dreamyprogrammer.simple_notes.model.repositories.TaskElement;

public class MainActivity extends AppCompatActivity implements ListNotes.Controller, EditListNotes.Controller {
    private static final String NOTES_LIST_FRAGMENT_TAG = "NOTES_LIST_FRAGMENT_TAG";

    private final FragmentManager fragmentManager = getSupportFragmentManager();
    private boolean isTwoPaneMode = false;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        showNoteList();
    }

    private void showNoteList() {
        getSupportFragmentManager()
                .beginTransaction()
                .add(R.id.container, new ListNotes())
                .commit();
    }

    @Override
    public void openNotes(TaskElement taskElement) {
        getSupportFragmentManager()
                .beginTransaction()
                .replace(R.id.container, EditListNotes.newInstance(taskElement))
                .addToBackStack(null)
                .commit();
    }

    @Override
    public void saveNotes(TaskElement taskElement) {

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        int id = item.getItemId();
        if (id == R.id.action_about) {
            fragmentManager.beginTransaction()
                    .replace(R.id.container, new AboutTheApp())
                    .addToBackStack(null)
                    .commit();
        }
        return super.onOptionsItemSelected(item);
    }
}