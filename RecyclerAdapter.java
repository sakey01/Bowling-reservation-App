package pitch.example;
import android.app.DatePickerDialog;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AlertDialog;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

public class RecAdapter extends RecyclerView.Adapter<RecAdapter.ViewHolder> {
    private Context context;
    private ArrayList<LaneItem> laneItems;

    public RecAdapter(Context context, ArrayList<LaneItem> laneItems) {
        this.context = context;
        this.laneItems = laneItems;
    }

    @NonNull
    @Override
    public RecAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(context);
        View view = inflater.inflate(R.layout.alley_item, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        holder.imageView.setImageResource(laneItems.get(position).getImage());
        holder.name.setText(laneItems.get(position).getLanes());
        holder.location.setText(laneItems.get(position).getLocation());
        holder.openings.setText(laneItems.get(position).getOpenings());
        holder.reserveButton.setOnClickListener(v -> showDate(laneItems.get(position)));
    }

    @Override
    public int getItemCount() {
        return laneItems.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        ImageView imageView;
        TextView name, location, openings;
        Button reserveButton;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);

            imageView = itemView.findViewById(R.id.imageView);
            name = itemView.findViewById(R.id.name);
            location = itemView.findViewById(R.id.location);
            openings = itemView.findViewById(R.id.opening);
            reserveButton = itemView.findViewById(R.id.reserveButton);
        }
    }
    private void showDate(LaneItem lane) {
        DatePickerDialog datePickerDialog = new DatePickerDialog(context);
        datePickerDialog.setOnDateSetListener((view, year, month, dayOfMonth) -> {
            String selectedDate = dayOfMonth + "/" + (month + 1) + "/" + year;
            lane.setReserved(true);

            AlertDialog alertDialog = new AlertDialog.Builder(context)
                    .setTitle("Lane Reserved")
                    .setMessage("You reserved the following lanes: " +
                            (context.getResources().getString(R.string.seperator)) +
                            lane.getLanes() + "\n" + lane.getLocation() + "\n" + selectedDate)

                    .setNegativeButton("KEEP BROWSING", (dialog, which) -> {
                        //reserveButton.setText("Reserved");
                        //reserveButton.setBackgroundColor(context.getResources().getColor(R.color.grey));
                    })
                    .setPositiveButton("CHECK OUT", (dialog, which) -> {
                        notifyDataSetChanged();
                    })
                    .create();
            alertDialog.show();
        });
        datePickerDialog.show();
    }
}
