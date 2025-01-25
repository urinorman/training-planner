package norman.uri.myapplication;

import android.os.Bundle;
import android.text.InputType;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.Toast;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;
import java.util.List;

public class ShowAllActivity extends AppCompatActivity {

    private MyAdapter adapter;
    private List<Workout> workoutList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_show_all);

        // Initialize RecyclerView
        RecyclerView recyclerView = findViewById(R.id.recyclerViewALL);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));

        // Initialize and populate the workout list
        workoutList = new ArrayList<>();
        workoutList.add(new Workout("Push-ups", "A basic upper-body strength workout", 3, 10, false));
        workoutList.add(new Workout("Squats", "A lower-body strength workout", 4, 15, false));
        workoutList.add(new Workout("Plank", "A core stability exercise", 5, 1, false));

        // Initialize the adapter
        adapter = new MyAdapter(workoutList, position -> {
            // Handle workout item clicks (e.g., edit)
            editWorkoutDialog(position, workoutList, adapter);
        });
        recyclerView.setAdapter(adapter);

        // Set up the Add Workout button
        findViewById(R.id.addWorkoutButton).setOnClickListener(v -> showAddWorkoutDialog());
    }

    // Method to show the add workout dialog
    private void showAddWorkoutDialog() {
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setTitle("Add New Workout");

        // Layout for the dialog
        LinearLayout layout = new LinearLayout(this);
        layout.setOrientation(LinearLayout.VERTICAL);

        final EditText inputName = new EditText(this);
        inputName.setHint("Workout Name");
        layout.addView(inputName);

        final EditText inputDescription = new EditText(this);
        inputDescription.setHint("Workout Description");
        layout.addView(inputDescription);

        final EditText inputDifficulty = new EditText(this);
        inputDifficulty.setHint("Difficulty (1-10)");
        inputDifficulty.setInputType(InputType.TYPE_CLASS_NUMBER);
        layout.addView(inputDifficulty);

        final EditText inputLength = new EditText(this);
        inputLength.setHint("Length (minutes)");
        inputLength.setInputType(InputType.TYPE_CLASS_NUMBER);
        layout.addView(inputLength);

        builder.setView(layout);

        // Set up dialog buttons
        builder.setPositiveButton("Add", (dialog, which) -> {
            String name = inputName.getText().toString().trim();
            String description = inputDescription.getText().toString().trim();
            int difficulty;
            int length;

            try {
                difficulty = Integer.parseInt(inputDifficulty.getText().toString());
                length = Integer.parseInt(inputLength.getText().toString());

                if (name.isEmpty() || description.isEmpty() || difficulty < 1 || difficulty > 10 || length <= 0) {
                    Toast.makeText(this, "Please provide valid inputs.", Toast.LENGTH_SHORT).show();
                    return;
                }

                // Add the new workout to the list
                workoutList.add(new Workout(name, description, difficulty, length, false));
                adapter.notifyItemInserted(workoutList.size() - 1);
            } catch (NumberFormatException e) {
                Toast.makeText(this, "Please enter valid numbers for difficulty and length.", Toast.LENGTH_SHORT).show();
            }
        });

        builder.setNegativeButton("Cancel", (dialog, which) -> dialog.cancel());

        builder.show();
    }

    // Method to show the edit dialog for a workout
    private void editWorkoutDialog(int position, List<Workout> workoutList, MyAdapter adapter) {
        Workout selectedWorkout = workoutList.get(position);

        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setTitle("Edit Workout");

        // Layout for the dialog
        LinearLayout layout = new LinearLayout(this);
        layout.setOrientation(LinearLayout.VERTICAL);

        final EditText inputDifficulty = new EditText(this);
        inputDifficulty.setHint("Difficulty (1-10)");
        inputDifficulty.setInputType(InputType.TYPE_CLASS_NUMBER);
        inputDifficulty.setText(String.valueOf(selectedWorkout.getDifficulty()));
        layout.addView(inputDifficulty);

        final EditText inputLength = new EditText(this);
        inputLength.setHint("Length (minutes)");
        inputLength.setInputType(InputType.TYPE_CLASS_NUMBER);
        inputLength.setText(String.valueOf(selectedWorkout.getLength()));
        layout.addView(inputLength);

        builder.setView(layout);

        // Buttons for the dialog
        builder.setPositiveButton("Save", (dialog, which) -> {
            try {
                int difficulty = Integer.parseInt(inputDifficulty.getText().toString());
                int length = Integer.parseInt(inputLength.getText().toString());

                if (difficulty < 1 || difficulty > 10) {
                    Toast.makeText(this, "Difficulty must be between 1 and 10", Toast.LENGTH_SHORT).show();
                } else {
                    // Update the workout
                    selectedWorkout.setDifficulty(difficulty);
                    selectedWorkout.setLength(length);

                    // Notify the adapter to refresh the list
                    adapter.notifyItemChanged(position);
                    Toast.makeText(this, "Workout updated successfully", Toast.LENGTH_SHORT).show();
                }
            } catch (NumberFormatException e) {
                Toast.makeText(this, "Invalid input. Please enter valid numbers.", Toast.LENGTH_SHORT).show();
            }
        });

        builder.setNegativeButton("Cancel", (dialog, which) -> dialog.cancel());

        builder.show();
    }
}
