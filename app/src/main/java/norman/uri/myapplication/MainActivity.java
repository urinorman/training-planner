package norman.uri.myapplication;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;

import androidx.appcompat.app.AppCompatActivity;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {

    Button loginPageBtn, registerPageBtn;
    ImageButton play, pause, stop;
    Context context;

    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // Initialize buttons
        loginPageBtn = findViewById(R.id.buttonPageLogin);
        registerPageBtn = findViewById(R.id.buttonPageRegister);

        loginPageBtn.setOnClickListener(this);
        registerPageBtn.setOnClickListener(this);

        // Initialize context
        context = this;

        // Initialize ImageButtons
        play = findViewById(R.id.imageButtonPlayService);
        pause = findViewById(R.id.imageButtonPauseService);
        stop = findViewById(R.id.imageButtonStopService);

        play.setOnClickListener(this);
        pause.setOnClickListener(this);
        stop.setOnClickListener(this);
    }

    @Override
    public void onClick(View view) {
        int id = view.getId();

        // Handle navigation buttons
        if (id == R.id.buttonPageLogin) {
            Intent intent = new Intent(MainActivity.this, LoginActivity.class);
            startActivity(intent);
        } else if (id == R.id.buttonPageRegister) {
            Intent intent = new Intent(MainActivity.this, RegisterActivity.class);
            startActivity(intent);
        }

        // Handle service buttons
        Intent intent = new Intent(context, MyService.class);
        if (id == R.id.imageButtonPlayService) {
            intent.setAction("PLAY");
            startService(intent);
        } else if (id == R.id.imageButtonPauseService) {
            intent.setAction("PAUSE");
            startService(intent);
        } else if (id == R.id.imageButtonStopService) {
            intent.setAction("STOP");
            startService(intent);
        }
    }
}
