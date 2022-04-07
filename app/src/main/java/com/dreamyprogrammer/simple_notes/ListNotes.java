package com.dreamyprogrammer.simple_notes;

import android.content.Context;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.dreamyprogrammer.simple_notes.model.repositories.TaskElement;

import java.util.ArrayList;
import java.util.List;
import androidx.fragment.app.Fragment;

public class ListNotes extends Fragment {
    public List<String> list;
    private String mParam1;
    private String mParam2;
    private LinearLayoutManager layoutManager;
    private LinearLayout linearLayout;

    private void addTaskElement(TaskElement taskElement){
        Button button = new Button(getContext());
        button.setText(taskElement.getName());
        button.setOnClickListener(v-> {
            ((Controller) getActivity()).openNotes(taskElement);
        });
        linearLayout.addView(button);
    }


    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_list_notes, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        findView(view);
        setupRecyclerView(view);
    }

    private void findView(View view) {
    }

    private void setupRecyclerView(View view) {

        linearLayout = view.findViewById(R.id.linear);


        // todo Пока константный список. Потом доделаем.
        addTaskElement(new TaskElement("Разобраться с фрагментами", 0));
        addTaskElement(new TaskElement("Купить велосипед", 0));
        addTaskElement(new TaskElement("Поиграть с детьми", 0));
        addTaskElement(new TaskElement("Разобрать в шкафу", 0));
        addTaskElement(new TaskElement("Сделать грядки", 0));
    }



    @Override
    public void onAttach(@NonNull Context context) {
        super.onAttach(context);
        if (!(context instanceof Controller)) {
            throw new RuntimeException(getString(R.string.error_implement_controller));
        }
    }

    interface Controller {
        void openNotes(TaskElement taskElement);
    }
}
