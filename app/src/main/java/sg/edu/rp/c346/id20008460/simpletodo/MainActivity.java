package sg.edu.rp.c346.id20008460.simpletodo;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.Spinner;
import android.widget.Toast;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    Button btnAdd , btnClear , btnDelete ;
    EditText editToDo;
    ListView lvTDL;

    ArrayList<String> toDoList;
    ArrayAdapter<String> adapterList;

    Spinner spinner;
    String selected = "";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        btnAdd = findViewById(R.id.buttonAdd);
        btnClear = findViewById(R.id.buttonClear);
        btnDelete = findViewById(R.id.buttonDelete);


        editToDo = findViewById(R.id.editList);

        lvTDL = findViewById(R.id.listToDo);
        spinner = findViewById(R.id.spinner2);

        toDoList = new ArrayList<String>();

        adapterList = new ArrayAdapter<>(this, android.R.layout.simple_list_item_1, toDoList);
        lvTDL.setAdapter(adapterList);

        btnAdd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                if (!editToDo.getText().toString().trim().isEmpty()) {
                    String toDo = editToDo.getText().toString();
                    toDoList.add(toDo);
                    adapterList.notifyDataSetChanged();
                    Toast.makeText(MainActivity.this, R.string.addSuccess, Toast.LENGTH_SHORT).show();
                } else {
                    Toast.makeText(MainActivity.this, R.string.addFail, Toast.LENGTH_SHORT).show();

                }

            }
        });

        btnClear.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                toDoList.clear();
                adapterList.notifyDataSetChanged();

            }
        });

        btnDelete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (!toDoList.isEmpty()) {
                        if (!editToDo.getText().toString().trim().isEmpty() ) {
                            int pos = Integer.parseInt(editToDo.getText().toString());
                            if (pos >= toDoList.size()) {
                                Toast.makeText(MainActivity.this, R.string.resultIndextOut, Toast.LENGTH_SHORT).show();
                            } else if (pos <= toDoList.size()) {
                                toDoList.remove(pos);
                                adapterList.notifyDataSetChanged();
                                Toast.makeText(MainActivity.this, R.string.resultSuccessfullyDeleted, Toast.LENGTH_SHORT).show();
                            }
                        } else {
                            Toast.makeText(MainActivity.this, R.string.emptyIndexInput, Toast.LENGTH_SHORT).show();
                        }
                    } else {
                        Toast.makeText(MainActivity.this, R.string.resultEmptyList , Toast.LENGTH_SHORT).show();

                }
            }
        });

        btnClear.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (!toDoList.isEmpty()){
                    toDoList.clear();
                    adapterList.notifyDataSetChanged();
                }else{
                    Toast.makeText(MainActivity.this, R.string.resultEmptyList, Toast.LENGTH_SHORT).show();
                }

            }
        });

        spinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                switch(position) {
                    case 0:
                        editToDo.setHint(R.string.hintNewTask);
                        btnAdd.setEnabled(true);
                        btnDelete.setEnabled(false);
                        break;

                    case 1:
                        editToDo.setHint(R.string.hintRemoved);
                        btnDelete.setEnabled(true);
                        btnAdd.setEnabled(false);
                        break;
                }
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });



    }
}