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

public class WorkoutListActivity extends AppCompatActivity {

    private MyAdapter adapter; // Declare the adapter here
    private List<Workout> workoutList; // Declare the workout list here

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_show_all); // Ensure this layout contains a RecyclerView with the correct ID

        // Initialize the RecyclerView
        RecyclerView rv = findViewById(R.id.recyclerViewALL);
        rv.setLayoutManager(new LinearLayoutManager(this));

        // Initialize and populate the workout list
        workoutList = new ArrayList<>();
        workoutList.add(new Workout("Push-ups", "A basic upper-body strength workout", 2, 10, false));
        workoutList.add(new Workout("Squats", "A lower-body strength workout", 3, 15, false));
        workoutList.add(new Workout("Plank", "A core stability exercise", 4, 5, false));
        workoutList.add(new Workout("Endurance Run", "A 30-minute cardio workout", 5, 30, false));

        // Initialize the adapter with a click listener
        adapter = new MyAdapter(workoutList, position -> {
            // Show a dialog to edit the selected workout
            editWorkoutDialog(position, workoutList, adapter);
        });

        // Set the adapter for the RecyclerView
        rv.setAdapter(adapter);
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
