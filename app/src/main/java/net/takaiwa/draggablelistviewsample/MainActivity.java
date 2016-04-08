package net.takaiwa.draggablelistviewsample;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.RelativeLayout;

import java.util.Arrays;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    List<Data> list = Arrays.asList(
        new Data("Walking", "06:30 - 07:00", -8860075),
        new Data("Meeting", "09:00 - 10:00", -10725938),
        new Data("Business trip", "10:00 - 17:00", -3254946),
        new Data("Vist dentist", "18:00 - 19:00", -11159858)
    );

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        final CustomListView listView = (CustomListView)findViewById(R.id.listView1);
        Adapter adapter = new Adapter(this, list, new Adapter.Listener() {
            @Override
            public void onGrab(int position, RelativeLayout row) {
                listView.onGrab(position, row);
            }
        });

        listView.setAdapter(adapter);
        listView.setListener(new CustomListView.Listener() {
            @Override
            public void swapElements(int indexOne, int indexTwo) {
                Data temp = list.get(indexOne);
                list.set(indexOne, list.get(indexTwo));
                list.set(indexTwo, temp);
            }
        });
    }
}
