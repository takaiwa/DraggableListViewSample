package net.takaiwa.draggablelistviewsample;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.os.Build;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by takaiwa.net on 2016/04/08.
 */
public class Adapter extends ArrayAdapter<Data> {

    final int INVALID_ID = -1;
    public interface Listener {
        void onGrab(int position, RelativeLayout row);
    }

    final Listener listener;
    final Map<Data, Integer> mIdMap = new HashMap<>();

    public Adapter(Context context, List<Data> list, Listener listener) {
        super(context, 0, list);
        this.listener = listener;
        for (int i = 0; i < list.size(); ++i) {
            mIdMap.put(list.get(i), i);
        }
    }

    @Override
    public View getView(final int position, View view, ViewGroup parent) {
        Context context = getContext();
        final Data data = getItem(position);
        if(null == view) {
            view = LayoutInflater.from(context).inflate(R.layout.list_row, null);
        }

        final RelativeLayout row = (RelativeLayout) view.findViewById(
                R.id.lytPattern);

        View color = view.findViewById(R.id.lytPatternColorDraw);
        color.setBackgroundColor(data.color);

        TextView textView = (TextView)view.findViewById(R.id.textViewTitle);
        textView.setText(data.title);

        TextView timerange = (TextView)view.findViewById(R.id.textViewTimeRange);
        timerange.setText(data.time);

        view.findViewById(R.id.imageViewGrab)
            .setOnTouchListener(new View.OnTouchListener() {
                @Override
                public boolean onTouch(View v, MotionEvent event) {
                    listener.onGrab(position, row);
                    return false;
                }
            });

        return view;
    }

    @Override
    public long getItemId(int position) {
        if (position < 0 || position >= mIdMap.size()) {
            return INVALID_ID;
        }
        Data item = getItem(position);
        return mIdMap.get(item);
    }

    @Override
    public boolean hasStableIds() {
        return Build.VERSION.SDK_INT < Build.VERSION_CODES.LOLLIPOP;
    }
}
