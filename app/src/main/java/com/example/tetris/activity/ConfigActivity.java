package com.example.tetris.activity;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.RadioButton;
import android.widget.RadioGroup;

import com.example.tetris.R;

public class ConfigActivity extends Activity implements OnClickListener {
    RadioGroup level_group, row_group, col_group, block_group;
    Button complete;
    RadioButton[] r_btn;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        // TODO Auto-generated method stub
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_config);

        level_group = (RadioGroup) findViewById(R.id.group1);
        row_group = (RadioGroup) findViewById(R.id.group2);
        col_group = (RadioGroup) findViewById(R.id.group3);
        block_group = (RadioGroup) findViewById(R.id.group4);

        complete = (Button) findViewById(R.id.complete);
        complete.setOnClickListener(this);
        r_btn = new RadioButton[4];
    }


    @Override
    public void onClick(View v) {
        // TODO Auto-generated method stub
        switch (v.getId()) {
            case R.id.complete:

                r_btn[0] = (RadioButton) findViewById(level_group.getCheckedRadioButtonId());
                r_btn[1] = (RadioButton) findViewById(row_group.getCheckedRadioButtonId());
                r_btn[2] = (RadioButton) findViewById(col_group.getCheckedRadioButtonId());
                r_btn[3] = (RadioButton) findViewById(block_group.getCheckedRadioButtonId());
                Intent in = getIntent();
                in.putExtra("level", Integer.parseInt(r_btn[0].getTag().toString()));
                in.putExtra("row", Integer.parseInt(r_btn[1].getText().toString()));
                in.putExtra("col", Integer.parseInt(r_btn[2].getText().toString()));
                in.putExtra("block", Integer.parseInt(r_btn[3].getTag().toString()));
                setResult(RESULT_OK, in);
                v.postDelayed(new Runnable() {
                    @Override
                    public void run() {
                      finish();
                    }
                },20);
                break;
        }
    }

    @Override
    protected void onPause() {
        super.onPause();
    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
        finish();
     }
}
