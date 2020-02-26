package com.example.ytodomobile;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.Toast;

import java.util.ArrayList;

public class AddActivity extends AppCompatActivity implements View.OnClickListener {

    private EditText editText;
    private Button button;
    private ArrayList<String> items;
    private ArrayAdapter<String> adapter;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add);

        editText = findViewById(R.id.edit_text);
        button = findViewById(R.id.add_button);

        items = FileBackend.readData(this);

        adapter = new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1, items);

        button.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        switch(v.getId()) {
            case R.id.add_button:
                String newItem = editText.getText().toString();adapter.add(newItem);
                if(newItem.equals(""))
                {
                    Toast.makeText(this, "Cannot Be Empty", Toast.LENGTH_SHORT).show();
                }
                else
                {
                    editText.setText("");
                    FileBackend.writeData(items, this);
                    Toast.makeText(this, "Item Added", Toast.LENGTH_SHORT).show();
                    break;
                }
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.back_menu, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        switch (item.getItemId()) {
            case R.id.back:
                openActivityMain();
                return true;
        }
        return super.onOptionsItemSelected(item);
    }
    public void openActivityMain()
    {
        Intent intent = new Intent(this, MainActivity.class);
        startActivity(intent);
    }

}
