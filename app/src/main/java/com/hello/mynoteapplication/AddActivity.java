package com.hello.mynoteapplication;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.widget.EditText;
import android.widget.NumberPicker;
import android.widget.Toast;

public class AddActivity extends AppCompatActivity {
EditText title,desc;
NumberPicker priority;
public  static String TITLE_EXTRA="com.hello.mynoteapplication.TITLE_EXTRA";
    public  static String DESC_EXTRA="com.hello.mynoteapplication.DESC_EXTRA";
    public  static String PRIORITY_EXTRA="com.hello.mynoteapplication.PRIORITY_EXTRA";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add);
        title=findViewById(R.id.edit_text_title_id);
        desc=findViewById(R.id.edit_text_desc_id);
        priority=findViewById(R.id.number_picker_id);
        priority.setMinValue(0);
        priority.setMaxValue(10);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater menuInflater=getMenuInflater();
        menuInflater.inflate(R.menu.save,menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        switch (item.getItemId()){
            case R.id.save_menu_id:insertItemToDatabase();
            break;
            default:super.onOptionsItemSelected(item);
        }
        return true;
    }
    private void insertItemToDatabase(){
        String mtitle=title.getText().toString();
        String mdesc=desc.getText().toString();
        int mpriority=priority.getValue();
        if (mtitle.trim().isEmpty()| mdesc.trim().isEmpty()){
            Toast.makeText(AddActivity.this,"Title or desc should not be empty",Toast.LENGTH_SHORT).show();
            return;
        }
        Intent intent=new Intent(AddActivity.this,MainActivity.class);
        intent.putExtra(TITLE_EXTRA,mtitle);
        intent.putExtra(DESC_EXTRA,mdesc);
        intent.putExtra(PRIORITY_EXTRA,mpriority);
        setResult(RESULT_OK,intent);
        finish();
    }
}