package com.example.sosbicicletta;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;
import java.util.List;


public class ContattiFragment extends Fragment {

    View v;
   private RecyclerView myrecyclerview;
   private List<Contatti> lstContatti;
    public ContattiFragment(){

    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        v = inflater.inflate(R.layout.fragment_contatti,container,false);
        myrecyclerview = (RecyclerView) v.findViewById(R.id.cont_recycler);
        RecyclerViewAdapter recyclerViewAdapter = new RecyclerViewAdapter(getContext(),lstContatti);
        myrecyclerview.setLayoutManager(new LinearLayoutManager(getActivity()));
        myrecyclerview.setAdapter(recyclerViewAdapter);

        return v;
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        lstContatti = new ArrayList<>();
        lstContatti.add(new Contatti("Driver 1","111111111",R.drawable.ic_launcher_background));
        lstContatti.add(new Contatti("Driver 2","222222222",R.drawable.ic_launcher_background));
        lstContatti.add(new Contatti("Driver 3","333333333",R.drawable.ic_launcher_background));
        lstContatti.add(new Contatti("Driver 4","444444444",R.drawable.ic_launcher_background));
        lstContatti.add(new Contatti("Driver 5","555555555",R.drawable.ic_launcher_background));
        lstContatti.add(new Contatti("Driver 6","666666666",R.drawable.ic_launcher_background));
        lstContatti.add(new Contatti("Driver 7","777777777",R.drawable.ic_launcher_background));
        lstContatti.add(new Contatti("Driver 1","111111111",R.drawable.ic_launcher_background));
        lstContatti.add(new Contatti("Driver 2","222222222",R.drawable.ic_launcher_background));
        lstContatti.add(new Contatti("Driver 3","333333333",R.drawable.ic_launcher_background));
        lstContatti.add(new Contatti("Driver 4","444444444",R.drawable.ic_launcher_background));
        lstContatti.add(new Contatti("Driver 5","555555555",R.drawable.ic_launcher_background));
        lstContatti.add(new Contatti("Driver 6","666666666",R.drawable.ic_launcher_background));
        lstContatti.add(new Contatti("Driver 7","777777777",R.drawable.ic_launcher_background));


    }
}