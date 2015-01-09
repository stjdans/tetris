package com.example.tetris.activity;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.ListView;
import android.widget.TextView;

import com.example.tetris.R;
import com.example.tetris.config.ListViewItem;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.ArrayList;

public class ResultActivity extends Activity {
    private ListView listView;
    private ArrayList<ListViewItem> list;
    private Button main;

    private ObjectOutputStream oOut;
    private ObjectInputStream oin;


    @Override
    public void onBackPressed() {
        // TODO Auto-generated method stub
        finish();
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        // TODO Auto-generated method stub
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_result);

        main = (Button) findViewById(R.id.gomain);
        main.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                // TODO Auto-generated method stub
                finish();
            }
        });

        Intent in = getIntent();
        String where = in.getStringExtra("where");
//        inputResultList("result_list.txt");
        inputResultList("result_list2.txt");
        if (!where.equals("main")) {
            int point = in.getIntExtra("point", -1);
            int stage = in.getIntExtra("stage", -1);

            if (list.size() == 0) {
                list.add(new ListViewItem(point, stage, 1));
            } else {
                ListViewItem item = new ListViewItem(point, stage);
                rank(item);
            }
        }
        if (list.size() > 10)
            list.remove(10);

        listView = (ListView) findViewById(R.id.list1);
        MyListViewAdapter adapter = new MyListViewAdapter(this,
                R.layout.mylistview, list);
        listView.setAdapter(adapter);
    }

    @Override
    protected void onPause() {
        // TODO Auto-generated method stub
//        writeResultList("result_list.txt", list);
        writeResultList("result_list2.txt", list);
        super.onPause();

    }

    public void rank(ListViewItem item) {
        int point = item.getPoint();
        ListViewItem item2 = null;
        for (int i = list.size() - 1; i >= 0; i--) {
            item2 = list.get(i);
            if (item2.getPoint() < point)
                item2.setRank(item2.getRank() + 1);
            else {
                item.setRank(item2.getRank() + 1);
                list.add(i + 1, item);
                return;
            }
        }
        item.setRank(1);
        list.add(0, item);
    }

    public void writeResultList(String file, ArrayList<ListViewItem> list) {
        try {
            oOut = new ObjectOutputStream(openFileOutput(file, MODE_PRIVATE));
            oOut.writeObject(list);
        } catch (IOException e) {
            Log.i("error", "input IoException : ->" + e.getMessage());
        } finally {
            if (oOut != null) {
                try {
                    oOut.close();
                } catch (IOException e) {
                    Log.i("error", "ioException : Close() ->" + e.getMessage());
                }
            }
        }
    }

    public void inputResultList(String file) {
        try {
            oin = new ObjectInputStream(openFileInput(file));
            list = (ArrayList<ListViewItem>) oin.readObject();
        } catch (FileNotFoundException e) {
            list = new ArrayList<ListViewItem>();
        } catch (Exception e) {
            Log.i("error", "ioException : " + e.getMessage());
        } finally {
            if (oin != null) {
                try {
                    oin.close();
                } catch (IOException e) {
                    Log.i("error",
                            "write IoException : Close() ->" + e.getMessage());
                }
            }
        }
    }
}

class MyListViewAdapter extends BaseAdapter {
    Context myContext;
    ArrayList<ListViewItem> list = new ArrayList<ListViewItem>();
    LayoutInflater inflater;

    int layoutId;

    public MyListViewAdapter(Context context, int layoutId,
                             ArrayList<ListViewItem> list) {
        // TODO Auto-generated constructor stub
        this.myContext = context;
        this.list = list;
        this.layoutId = layoutId;
        inflater = (LayoutInflater) context
                .getSystemService(Context.LAYOUT_INFLATER_SERVICE);
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        // TODO Auto-generated method stub
        if (convertView == null) {
            convertView = inflater.inflate(layoutId, parent, false);
        }
        TextView rank = (TextView) convertView.findViewById(R.id.rank);
        TextView stage = (TextView) convertView.findViewById(R.id.stage);
        TextView point = (TextView) convertView.findViewById(R.id.point);
        TextView rankText = (TextView) convertView.findViewById(R.id.rankText);
        ListViewItem item = getItem(position);
        rank.setText(String.valueOf(item.getRank()));
        rank.setTextColor(Color.RED);
        rank.setTextSize(25f);
        rankText.setTextColor(Color.RED);
        stage.setText(String.valueOf(item.getStage()));
        stage.setTextColor(Color.BLUE);
        point.setText(String.valueOf(item.getPoint()));
        point.setTextColor(Color.BLUE);
        return convertView;
    }

    @Override
    public long getItemId(int position) {
        // TODO Auto-generated method stub
        return position;
    }

    @Override
    public ListViewItem getItem(int position) {
        // TODO Auto-generated method stub
        return list.get(position);
    }

    @Override
    public int getCount() {
        // TODO Auto-generated method stub
        return list.size();
    }

}