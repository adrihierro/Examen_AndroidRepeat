package com.example.exam;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;

import androidx.fragment.app.Fragment;

import java.util.ArrayList;

public class ListaFragment extends Fragment {
    ArrayList<Entrenamiento> lista;
    EntrenamientoAdapter adapter;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.fragment_list, container, false);
        ListView listView = view.findViewById(R.id.listEntrenamientos);

        //Usamos la lista global de MainActivity
        lista = MainActivity.listaEntrenamientos;

        //Si es la primera vez, rellenamos con los 3 iniciales
        if (lista.isEmpty()) {
            lista.add(new Entrenamiento(
                    "Piernas",
                    "1. 10 min a trote, 2. 10 min sprint",
                    R.drawable.cardio_exam));

            lista.add(new Entrenamiento(
                    "Pecho",
                    "Rutina completa de pecho",
                    R.drawable.resistencia_exam));

            lista.add(new Entrenamiento(
                    "Espalda",
                    "Ejercicios para la espalda",
                    R.drawable.fuerza_exam));
        }

        adapter = new EntrenamientoAdapter(getContext(), lista);
        listView.setAdapter(adapter);

        listView.setOnItemClickListener((parent, v, position, id) -> {
            Entrenamiento e = lista.get(position);
            ((MainActivity) getActivity()).mostrarDetalle(e);
        });

        return view;
    }

    //se refresca el adapter
    public void agregarEntrenamiento(Entrenamiento e) {
        adapter.notifyDataSetChanged();
    }

    //Al volver atrás desde el detalle, refrescamos por si hubo cambios
    @Override
    public void onResume() {
        super.onResume();
        if (adapter != null) {
            adapter.notifyDataSetChanged();
        }
    }
}
