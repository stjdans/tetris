package com.example.tetris.network;

import android.app.Fragment;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import com.example.tetris.R;

/**
 * Created by ssm on 2015-01-08.
 */
public class ResponseFrag extends Fragment {
    TextView text;
    Button request_bt;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View root = inflater.inflate(R.layout.fragment, container, false);

        return root;

    }
    @Override
    public void onActivityCreated(Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        text = (TextView) getView().findViewById(R.id.response_text);
        request_bt = (Button) getView().findViewById(R.id.request_bt);
//        text.setText("응답 프래그먼트 생성");
        text.setText("");
    }
}