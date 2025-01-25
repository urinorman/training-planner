package norman.uri.myapplication;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

public class RegisterActivity extends AppCompatActivity {
    private EditText usernameInput;
    private EditText emailInput;
    private EditText passwordInput;
    private Button registerButton;

    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);

        // Bind views to their IDs
        usernameInput = findViewById(R.id.username);
        emailInput = findViewById(R.id.email);
        passwordInput = findViewById(R.id.password);
        registerButton = findViewById(R.id.register_button);

        // Set click listener for the register button
        registerButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String username = usernameInput.getText().toString().trim();
                String email = emailInput.getText().toString().trim();
                String password = passwordInput.getText().toString().trim();

                if (username.isEmpty() || email.isEmpty() || password.isEmpty()) {
                    Toast.makeText(RegisterActivity.this, "Please fill all fields", Toast.LENGTH_SHORT).show();
                } else {
                    // Navigate to ShowAllActivity
                    Intent intent = new Intent(RegisterActivity.this, ShowAllActivity.class);
                    startActivity(intent);
                    finish(); // Optional: Finish RegisterActivity to prevent going back
                }
            }
        });
    }
}
