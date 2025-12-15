package com.example.exam;

import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.EditText;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import java.util.ArrayList;
import java.util.Random;

public class MainActivity extends AppCompatActivity {

    private boolean landscapeMode;
    public static ArrayList<Entrenamiento> listaEntrenamientos = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_main);

        // Toolbar
        setSupportActionBar(findViewById(R.id.toolbar));

        landscapeMode = findViewById(R.id.contenedor_detalle) != null;

        if (landscapeMode) {
            getSupportFragmentManager()
                    .beginTransaction()
                    .replace(R.id.contenedor_lista, new ListaFragment())
                    .commit();
        } else {
            getSupportFragmentManager()
                    .beginTransaction()
                    .replace(R.id.contenedor, new ListaFragment())
                    .commit();
        }
    }

    public void mostrarDetalle(Entrenamiento e) {
        DetalleFragment detalle = DetalleFragment.newInstance(
                e.getTitulo(),
                e.getDescripcion(),
                e.getImagen()
        );

        if (landscapeMode) {
            getSupportFragmentManager()
                    .beginTransaction()
                    .replace(R.id.contenedor_detalle, detalle)
                    .commit();
        } else {
            getSupportFragmentManager()
                    .beginTransaction()
                    .replace(R.id.contenedor, detalle)
                    .addToBackStack(null)
                    .commit();
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        if (item.getItemId() == R.id.action_settings) {
            mostrarDialogoNuevo();   // ✅ AHORA SÍ SE ABRE EL DIÁLOGO
            return true;
        }
        return super.onOptionsItemSelected(item);
    }

    private void mostrarDialogoNuevo() {

        View dialogView = getLayoutInflater().inflate(R.layout.entrenamientoaddwindow, null);

        EditText edtTitulo = dialogView.findViewById(R.id.edtTitulo);
        EditText edtDescripcion = dialogView.findViewById(R.id.edtDescripcion);

        int[] imagenes = {
                R.drawable.cardio_exam,
                R.drawable.resistencia_exam,
                R.drawable.fuerza_exam
        };

        new AlertDialog.Builder(this).setTitle("Añadir entrenamiento")
                .setView(dialogView)
                .setPositiveButton("Guardar", (dialog, which) -> {

                    String titulo = edtTitulo.getText().toString();
                    String descripcion = edtDescripcion.getText().toString();

                    int imagenAleatoria = imagenes[new Random().nextInt(imagenes.length)];

                    Entrenamiento nuevo = new Entrenamiento(titulo, descripcion, imagenAleatoria);

                    MainActivity.listaEntrenamientos.add(nuevo);

                    ListaFragment listaFragment;

                    if (landscapeMode) {
                        listaFragment = (ListaFragment) getSupportFragmentManager()
                                .findFragmentById(R.id.contenedor_lista);
                    } else {
                        listaFragment = (ListaFragment) getSupportFragmentManager()
                                .findFragmentById(R.id.contenedor);
                    }

                    if (listaFragment != null) {
                        listaFragment.agregarEntrenamiento(nuevo);
                    }

                })
                .setNegativeButton("Cancelar", null)
                .show();
    }
}
