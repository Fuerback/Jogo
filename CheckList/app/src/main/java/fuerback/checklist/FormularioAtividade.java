package fuerback.checklist;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.widget.ArrayAdapter;
import android.widget.Spinner;

/**
 * Created by Usuario on 26/09/2016.
 */

public class FormularioAtividade extends AppCompatActivity {

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_formulario);

        Spinner spinner = (Spinner) findViewById(R.id.formulario_prioridade);
        ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(FormularioAtividade.this,
                R.array.Prioridade, android.R.layout.simple_spinner_item);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinner.setAdapter(adapter);

    }
}
