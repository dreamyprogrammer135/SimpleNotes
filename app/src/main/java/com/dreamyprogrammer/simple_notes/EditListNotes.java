package com.dreamyprogrammer.simple_notes;

import android.content.Context;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import com.dreamyprogrammer.simple_notes.model.repositories.TaskElement;

import java.text.SimpleDateFormat;
import java.util.Locale;

public class EditListNotes extends Fragment {


    private static final String TASK_ELEMENT_ARGS_KEY = "TASK_ELEMENT_ARGS_KEY";
    private static SimpleDateFormat dateFormat = new SimpleDateFormat("dd-MMM-yyyy", Locale.getDefault());

    private TaskElement task;
    private EditText editTextNotes;
    private Button saveButton;
    private TextView textViewDate;


    public static EditListNotes newInstance(TaskElement taskElement) {
        EditListNotes editListNotes = new EditListNotes();
        Bundle args = new Bundle();
        args.putParcelable(TASK_ELEMENT_ARGS_KEY, taskElement);
        editListNotes.setArguments(args);
        return editListNotes;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_edit_list_notes, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        editTextNotes = view.findViewById(R.id.edit_text_notes);
        saveButton = view.findViewById(R.id.save_button);
        textViewDate = view.findViewById(R.id.text_view_date);
        textViewDate.setText(dateFormat.format(task.getCreateDate()));
        editTextNotes.setText(task.getName());


        saveButton.setOnClickListener(v -> {
            Controller controller = (Controller) getActivity();
            controller.saveNotes(new TaskElement(
                    editTextNotes.getText().toString(),
                    0
            ));
        });

    }

    @Override
    public void onAttach(@NonNull Context context) {
        super.onAttach(context);
        if (!(context instanceof Controller)) {
            throw new RuntimeException(getString(R.string.error_implement_controller));
        }
        if (getArguments() != null) {
            task = getArguments().getParcelable(TASK_ELEMENT_ARGS_KEY);
        }
    }


    interface Controller {
        void saveNotes(TaskElement taskElement);
    }
}
