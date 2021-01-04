package com.example.todo;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.view.KeyEvent;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {
    //Button insertButton;
    EditText todoEdit;
    private ArrayList<Todo> todoArrayList;
    private TodoAdapter todoAdapter;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        RecyclerView recyclerView=findViewById(R.id.recycler1);

        recyclerView.setLayoutManager(new LinearLayoutManager(this));

        todoArrayList=new ArrayList<>();
        todoAdapter=new TodoAdapter(todoArrayList);
        recyclerView.setAdapter(todoAdapter);

        //insertButton=(Button)findViewById(R.id.button_insert_main);
        todoEdit=(EditText)findViewById(R.id.edit_todo_main);

        /*insertButton.setOnClickListener(new View.OnClickListener(){

            @Override
            public void onClick(View v) {
                Todo newTodo=new Todo(todoEdit.getText().toString());
                todoArrayList.add(newTodo);
                todoAdapter.notifyDataSetChanged();
                todoEdit.setText(null);

            }
        });*/

        todoEdit.setOnKeyListener(new View.OnKeyListener() {
            @Override
            public boolean onKey(View v, int keyCode, KeyEvent event) {
                if (keyCode==KeyEvent.KEYCODE_ENTER){
                    //키이벤트가 2번 발생하는 것 방지
                    if (event.getAction() == KeyEvent.ACTION_UP ){
                        Todo newTodo=new Todo(todoEdit.getText().toString());
                        todoArrayList.add(newTodo);
                        todoAdapter.notifyDataSetChanged();
                        todoEdit.setText(null);
                        return true;
                    }

                }
                return false;
            }
        });



    }
}