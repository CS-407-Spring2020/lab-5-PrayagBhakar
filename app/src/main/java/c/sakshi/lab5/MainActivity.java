package c.sakshi.lab5;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {

    public void onButtonClick(View view) {
        EditText myTextField = (EditText) findViewById(R.id.username);

        Intent intent = new Intent(this, Main2Activity.class);
        SharedPreferences sharedPreferences = getSharedPreferences("c.sakshi.lab5", Context.MODE_PRIVATE);
        sharedPreferences.edit().putString("username", myTextField.getText().toString()).apply();
        startActivity(intent);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        SharedPreferences sharedPreferences = getSharedPreferences("c.sakshi.lab5", Context.MODE_PRIVATE);
        String name = sharedPreferences.getString("username", "");

        if (!name.equals("")) {
            Intent intent = new Intent(this, Main2Activity.class);
            startActivity(intent);
        } else {
            // name does not exists so go back to login
            setContentView(R.layout.activity_main);
        }
    }
}
