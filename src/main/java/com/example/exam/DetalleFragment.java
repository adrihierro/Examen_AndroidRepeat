package com.example.exam;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.fragment.app.Fragment;

public class DetalleFragment extends Fragment {

    private String titulo;
    private String descripcion;
    private int imagen;

    public DetalleFragment() {
    }

    public static DetalleFragment newInstance(String titulo, String descripcion, int imagen) {
        DetalleFragment fragment = new DetalleFragment();
        Bundle args = new Bundle();
        args.putString("titulo", titulo);
        args.putString("descripcion", descripcion);
        args.putInt("imagen", imagen);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        // ✅ Recuperar los datos del Bundle
        if (getArguments() != null) {
            titulo = getArguments().getString("titulo");
            descripcion = getArguments().getString("descripcion");
            imagen = getArguments().getInt("imagen");
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.fragment_detalle, container, false);

        ImageView img = view.findViewById(R.id.imgDetalle);
        TextView txtTitulo = view.findViewById(R.id.txtTituloDetalle);
        TextView txtDescripcion = view.findViewById(R.id.txtDescripcionDetalle);

        img.setImageResource(imagen);
        txtTitulo.setText(titulo);
        txtDescripcion.setText(descripcion);

        return view;
    }
}
