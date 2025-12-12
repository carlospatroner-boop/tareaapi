package com.tunombre.tareaapi;

import android.os.Bundle;
import android.widget.Toast;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import com.tunombre.tareaapi.WebServices.Asynchtask;
import com.tunombre.tareaapi.WebServices.WebService;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class MainActivity extends AppCompatActivity implements Asynchtask {

    private RecyclerView recyclerView;
    private LugaresAdapter adapter;
    private List<LugarTuristico> listaLugares;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


        recyclerView = findViewById(R.id.recyclerViewLugares);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));

        listaLugares = new ArrayList<>();


        Map<String, String> datos = new HashMap<>();
        WebService ws = new WebService(
                "https://turismo.quevedoenlinea.gob.ec/lugar_turistico/json_getlistadoGridLT",
                datos,
                this,
                this
        );
        ws.execute("GET");
    }

    @Override
    public void processFinish(String result) throws JSONException {
        if (result == null || result.isEmpty()) {
            Toast.makeText(this, "Error de conexión", Toast.LENGTH_LONG).show();
            return;
        }

        JSONObject jsonPrincipal = new JSONObject(result);
        JSONArray jsonArrayData = jsonPrincipal.getJSONArray("data");


        listaLugares.clear();

        for (int i = 0; i < jsonArrayData.length(); i++) {
            JSONObject obj = jsonArrayData.getJSONObject(i);

            String nombre = obj.optString("nombre_lugar", "Sin nombre");
            String categoria = obj.optString("categoria", "Sin categoría");
            String telefono = obj.optString("telefono", "Sin teléfono");

            listaLugares.add(new LugarTuristico(nombre, categoria, telefono));
        }

        adapter = new LugaresAdapter(listaLugares);
        recyclerView.setAdapter(adapter);
    }
}