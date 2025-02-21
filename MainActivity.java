package pitch.example;

import android.os.Bundle;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {
    String[] names, locations, openings;
    ArrayList<LaneItem> laneItems = new ArrayList<>();
    int[] images = {(R.drawable.lane1), (R.drawable.lane2), (R.drawable.lane3),
            (R.drawable.lane4), (R.drawable.lane5)};

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_main);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });
        RecyclerView recView = findViewById(R.id.recycler);
        setUpRecyclerView();
        RecAdapter Myadapter = new RecAdapter(this, laneItems);
        recView.setAdapter(Myadapter);
        recView.setLayoutManager(new LinearLayoutManager(this));

    }
    private void setUpRecyclerView() {
        names = getResources().getStringArray(R.array.lanes);
        locations = getResources().getStringArray(R.array.locations);
        openings = getResources().getStringArray(R.array.openings);

        for (int i = 0; i < names.length; i++) {
            laneItems.add(new LaneItem(names[i], locations[i], openings[i], images[i]));
        }
    }
}
