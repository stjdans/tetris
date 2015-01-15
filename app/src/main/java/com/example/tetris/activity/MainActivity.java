package com.example.tetris.activity;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;

import com.example.tetris.R;
import com.example.tetris.config.GameConfig;

public class MainActivity extends Activity implements OnClickListener {
    public static final int REQUEST_CONFIG = 1;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Button start, config, result, fight;
        setContentView(R.layout.activity_main);

        start = (Button) findViewById(R.id.start);
        config = (Button) findViewById(R.id.config);
        result = (Button) findViewById(R.id.result);
        fight = (Button) findViewById(R.id.fight_bt);

        start.setOnClickListener(this);
        config.setOnClickListener(this);
        result.setOnClickListener(this);
        fight.setOnClickListener(this);
        GameConfig.size = GameConfig.SIZE_DEFAULT;
    }

    @Override
    protected void onNewIntent(Intent intent) {
        super.onNewIntent(intent);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        // TODO Auto-generated method stub
        switch (requestCode) {
            case REQUEST_CONFIG:
                if (resultCode == RESULT_OK) {
                    GameConfig.size = GameConfig.SIZE_DEFAULT;
                    int level = data.getIntExtra("level", 3000);
                    int row = data.getIntExtra("row", 11);
                    int col = data.getIntExtra("col", 7);
                    int block = data.getIntExtra("block", 15);

                    GameConfig.speed = level;
                    GameConfig.row = row;
                    GameConfig.col = col;
                    GameConfig.size = block;

                }
                break;
        }
        super.onActivityResult(requestCode, resultCode, data);

    }

    @Override
    protected void onStart() {
        super.onStart();
    }

    @Override
    public void onClick(View v) {
        // TODO Auto-generated method stub
        Intent in;
        switch (v.getId()) {
            case R.id.start:
                in = new Intent(MainActivity.this, SecondActivity.class);
                startActivity(in);
                break;
            case R.id.config:
                in = new Intent(MainActivity.this, ConfigActivity.class);
                startActivityForResult(in, REQUEST_CONFIG);
                break;
            case R.id.result:
                in = new Intent(MainActivity.this, ResultActivity.class);
                in.putExtra("where", "main");
                startActivity(in);
                break;
            case R.id.fight_bt:
                in = new Intent(MainActivity.this, FightActivity.class);
                startActivity(in);
                break;
        }
    }

    @Override
    public void onBackPressed() {
        // TODO Auto-generated method stub
        finish();
    }

}