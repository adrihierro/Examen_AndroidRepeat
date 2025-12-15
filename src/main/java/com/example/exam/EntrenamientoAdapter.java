package com.example.exam;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.exam.Entrenamiento;

import java.util.List;

public class EntrenamientoAdapter extends ArrayAdapter<Entrenamiento> {

    public EntrenamientoAdapter(Context context, List<Entrenamiento> lista) {
        super(context, 0, lista);
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        if (convertView == null) {
            convertView = LayoutInflater.from(getContext())
                    .inflate(R.layout.item_entrenamiento, parent, false);
        }

        Entrenamiento e = getItem(position);

        ImageView img = convertView.findViewById(R.id.imgEntrenamiento);
        TextView titulo = convertView.findViewById(R.id.txtTitulo);

        img.setImageResource(e.getImagen());
        titulo.setText(e.getTitulo());

        return convertView;
    }
}
