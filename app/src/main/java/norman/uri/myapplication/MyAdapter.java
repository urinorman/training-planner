package norman.uri.myapplication;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

public class MyAdapter extends RecyclerView.Adapter<MyAdapter.MyViewHolder> {

    private final List<Workout> workoutList;
    private final OnWorkoutClickListener listener; // Listener for item clicks

    // Interface to handle item clicks
    public interface OnWorkoutClickListener {
        void onWorkoutClick(int position);
    }

    // Constructor to pass data and listener
    public MyAdapter(List<Workout> workoutList, OnWorkoutClickListener listener) {
        this.workoutList = workoutList;
        this.listener = listener;
    }

    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.one_item, parent, false);
        return new MyViewHolder(view, listener);
    }

    @Override
    public void onBindViewHolder(@NonNull MyViewHolder holder, int position) {
        Workout workout = workoutList.get(position);

        // Bind workout details to the views
        holder.title.setText(workout.getName());
        holder.description.setText(workout.getDescription());
        holder.difficulty.setText("Difficulty: " + workout.getDifficulty());
        holder.length.setText("Length: " + workout.getLength() + " mins");
        holder.equipment.setText(workout.isNeedEquipment() ? "Requires Equipment" : "No Equipment");
    }

    @Override
    public int getItemCount() {
        return workoutList.size();
    }

    public static class MyViewHolder extends RecyclerView.ViewHolder {
        TextView title, description, difficulty, length, equipment;

        public MyViewHolder(@NonNull View itemView, OnWorkoutClickListener listener) {
            super(itemView);

            title = itemView.findViewById(R.id.itemTitle);
            description = itemView.findViewById(R.id.itemDescription);
            difficulty = itemView.findViewById(R.id.itemDifficulty);
            length = itemView.findViewById(R.id.itemLength);
            equipment = itemView.findViewById(R.id.itemEquipment);

            // Set click listener on the item view
            itemView.setOnClickListener(v -> {
                if (listener != null) {
                    int position = getAdapterPosition();
                    if (position != RecyclerView.NO_POSITION) {
                        listener.onWorkoutClick(position); // Notify listener
                    }
                }
            });
        }
    }
}
