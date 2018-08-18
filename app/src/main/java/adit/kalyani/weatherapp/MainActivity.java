package adit.kalyani.weatherapp;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.KeyEvent;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        final EditText editText = (EditText) findViewById(R.id.queryET);
        editText.setOnEditorActionListener(new TextView.OnEditorActionListener() {
            @Override
            public boolean onEditorAction(TextView textView, int i, KeyEvent keyEvent) {
                if(editText.getText()!=null){
                    String city = editText.getText().toString();
                    Intent intent = new Intent(MainActivity.this, WeatherController.class);
                    intent.putExtra("city",city);
                    startActivity(intent);
                }else{
                    Toast.makeText(MainActivity.this, "Add a proper city idiot", Toast.LENGTH_SHORT).show();
                }
                return false;
            }
        });
    }
}
