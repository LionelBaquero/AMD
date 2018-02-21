package com.example.lio.misestudiantes;

import android.content.ContentValues;
import android.content.DialogInterface;
import android.content.pm.ActivityInfo;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.support.v7.app.AlertDialog;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

public class Insertar extends ActionBarActivity {
    private EditText numero, nombre, grupo, caract;
    private Button insertar, eliminar;
    private ListView listview;
    private List<Student> estudiantes= new ArrayList<>();
    private ArrayAdapter<Student> adapter;
    AdminSQLiteOpenHelper admin= new AdminSQLiteOpenHelper (this, "admin", null, 1);

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_insertar);
        setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);

        //recuperamos los objetos controles
        numero=(EditText)findViewById(R.id.numero);
        nombre=(EditText)findViewById(R.id.nombre);
        grupo=(EditText)findViewById(R.id.grupo);
        caract=(EditText)findViewById(R.id.caract);
        insertar=(Button)findViewById(R.id.insertar);
        eliminar=(Button)findViewById(R.id.eliminar);
        listview=(ListView) findViewById(R.id.listview);

        adapter= new ArrayAdapter<Student>(this,android.R.layout.simple_list_item_1, estudiantes);
        listview.setAdapter(adapter);


        insertar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                add(v);
            }
        });
        eliminar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                delete(v);
            }
        });

        listview.setOnItemLongClickListener(new AdapterView.OnItemLongClickListener() {
            @Override
            public boolean onItemLongClick(AdapterView<?> parent, final View view, int i, long l) {
                final int posicion=i;

                AlertDialog.Builder dialogo1= new AlertDialog.Builder(Insertar.this);
                dialogo1.setTitle("Eliminar estudiante");
                dialogo1.setMessage("Desea eliminar el estudiante seleccionado?");
                dialogo1.setCancelable(false);
                dialogo1.setPositiveButton("Confirmar", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogo1, int id) {
                        estudiantes.remove(posicion);
                        adapter.notifyDataSetChanged();
                    }
                });

                dialogo1.setNegativeButton("Cancelar", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogo1, int id) {

                    }
                });
                dialogo1.show();
                return false;
            }
        });

    }

    @Override
    protected void onResume() {
        super.onResume();
        showAll();
    }

    public void showAll(){
        String selectAllQuery= "select * from estudiante";
        SQLiteDatabase bd= admin.getReadableDatabase();
        estudiantes.clear();

        Cursor c= bd.rawQuery(selectAllQuery, null);

        try {
            while (c.moveToNext()) {
                Student e = new Student();

                e.setNumero(c.getInt(c.getColumnIndex("numero")));
                e.setNombre(c.getString(c.getColumnIndex("nombre")));
                e.setGrupo(c.getString(c.getColumnIndex("grupo")));
                e.setCaract(c.getString(c.getColumnIndex("caract")));

                estudiantes.add(e);
            }
        } finally {
            c.close();
        }

        adapter.notifyDataSetChanged();
    }

    public boolean validation(int num) {
        boolean retorno=true;
        SQLiteDatabase bd = admin.getWritableDatabase();
        String selectAllQuery = "select * from estudiante";
        Cursor c = bd.rawQuery(selectAllQuery, null);
        if ((numero.getText().toString().isEmpty()) || (nombre.getText().toString().isEmpty()) ||
                (grupo.getText().toString().isEmpty()) || (caract.getText().toString().isEmpty())) {
            Toast.makeText(getApplicationContext(), "Debe llenar todos los campos", Toast.LENGTH_LONG).show();
            retorno=false;
        } else {
            while (c.moveToNext()) {
                if (c.getInt(c.getColumnIndex("numero")) == num) {
                    Toast.makeText(this, "Ya existe un estudiante con ese número", Toast.LENGTH_SHORT).show();
                    retorno= false;
                }
            }
        }
        return retorno;
    }

    public void add(View view){
        SQLiteDatabase bd=admin.getWritableDatabase();
        int num= Integer.parseInt(numero.getText().toString());
        String name= nombre.getText().toString();
        String group= grupo.getText().toString();
        String carc= caract.getText().toString();


        ContentValues registro= new ContentValues();
        registro.put("numero", num);
        registro.put("nombre", name);
        registro.put("grupo", group);
        registro.put("caract", carc);
    if (validation(num)==true) {

        bd.insert("estudiante", null, registro);
        bd.close();

        numero.setText("");
        nombre.setText("");
        grupo.setText("");
        caract.setText("");

        showAll();

        Toast.makeText(this, "Estudiante insertado", Toast.LENGTH_SHORT).show();
    }
    }

    public void delete(View view){
        SQLiteDatabase bd=admin.getWritableDatabase();
        int num= Integer.parseInt(numero.getText().toString());
        int cant = bd.delete("estudiante", "numero="+num, null);
        bd.close();

        numero.setText("");
        nombre.setText("");
        grupo.setText("");
        caract.setText("");

        showAll();

        if (cant==1){
            Toast.makeText(this, "Estudiante eliminado", Toast.LENGTH_SHORT).show();
        }
        else {
            Toast.makeText(this, "No existe un estudiante con ese número", Toast.LENGTH_SHORT).show();
        }


    }

   /* public void deleteListView(View view, int posicion) {
        SQLiteDatabase bd = admin.getWritableDatabase();
        int num = estudiantes.get(posicion).getNumero();
        int cant = bd.delete("estudiante", "numero=" + num, null);
        bd.close();

        numero.setText("");
        nombre.setText("");
        grupo.setText("");
        caract.setText("");

        showAll();

        if (cant == 1) {
            Toast.makeText(this, "Estudiante eliminado", Toast.LENGTH_SHORT).show();
        }
    }*/
}
