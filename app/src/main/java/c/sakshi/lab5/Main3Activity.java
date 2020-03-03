package c.sakshi.lab5;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

public class Main3Activity extends AppCompatActivity {

    int noteid = -1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main3);

        // 1. get EditText view
        EditText editText = (EditText) findViewById(R.id.editText);

        // 2. get intent
        Intent intent = getIntent();

        // 3. get value from intent
        noteid = intent.getIntExtra("noteid", -1);

        // 4. init class
        if (noteid != -1) {
            Note note = Main2Activity.notes.get(noteid);
            String noteContent = note.getContent();

            editText.setText(noteContent);
        }
    }

    public void saveMethod (View view) {
        // 1. get EditText view
        EditText editText = (EditText) findViewById(R.id.editText);

        // 2. get SQL Database
        Context context = getApplicationContext();
        SQLiteDatabase sqLiteDatabase = context.openOrCreateDatabase("notes", Context.MODE_PRIVATE, null);
        DBHelper dbHelper = new DBHelper(sqLiteDatabase);

        // 3. init db
        dbHelper.createTable();

        // 4. set unsername
        SharedPreferences sharedPreferences = getSharedPreferences("c.sakshi.lab5", Context.MODE_PRIVATE);
        String name = sharedPreferences.getString("username", "");

        // 5. save info to database
        String title;
        DateFormat dateFormat = new SimpleDateFormat("MM/dd/yyyy HH:mm:ss");
        String date = dateFormat.format(new Date());
        //EditText editText = (EditText) findViewById(R.id.editText);
        String content = editText.getText().toString();

        if (noteid == -1) {
            title = "NOTE_" + (Main2Activity.notes.size()+1);
            dbHelper.saveNotes(name, title, content, date);
        } else {
            title = "NOTE_" + (noteid + 1);
            dbHelper.updateNote(name, title, content, date);
        }

        // go back
        Intent intent = new Intent(this, Main2Activity.class);
        startActivity(intent);
    }
}
