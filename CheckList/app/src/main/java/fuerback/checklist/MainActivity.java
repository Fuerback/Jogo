package fuerback.checklist;

import android.content.Context;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;

import fuerback.checklist.R;
import fuerback.checklist.modelo.Atividade;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        ListView list = (ListView) findViewById(R.id.lista_atividade);

        String[] arrayList = {"Felipe", "Duda", "Felipe", "Duda","Felipe", "Duda","Felipe", "Duda","Felipe", "Duda","Felipe", "Duda"};
        ArrayAdapter<String> adapter = new ArrayAdapter<String>(MainActivity.this,
                android.R.layout.simple_list_item_1,
                arrayList
        );
        list.setAdapter(adapter);

        Button botao_criar = (Button) findViewById(R.id.botao_criar);
        botao_criar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this, FormularioAtividade.class);
                startActivity(intent);
            }
        });

    }
}
