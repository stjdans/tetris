package com.example.tetris.activity;

import android.app.Activity;
import android.content.Context;
import android.net.DhcpInfo;
import android.net.wifi.WifiManager;
import android.os.Bundle;
import android.os.Handler;
import android.util.Log;
import android.widget.FrameLayout;
import android.widget.TextView;

import com.example.tetris.R;
import com.example.tetris.network.GameServer;
import com.example.tetris.network.ResponseFrag;

import org.apache.http.HttpResponse;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.DefaultHttpClient;

import java.io.BufferedInputStream;
import java.io.IOException;

/**
 * Created by ssm on 2015-01-08.
 */
public class FightActivity extends Activity {
    FrameLayout frame;
    TextView tx;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_fight);
        frame = (FrameLayout) findViewById(R.id.fight_frame);

        ResponseFrag frag = new ResponseFrag();
        getFragmentManager().beginTransaction().add(R.id.fight_frame, new ResponseFrag(), "response").commit();
        tx = (TextView) findViewById(R.id.t1);
        Thread t = new Thread() {
            @Override
            public void run() {
                new GameServer();

            }
        };
        t.setDaemon(true);
        t.start();
        Thread client = new Thread() {
            @Override
            public void run() {
                final StringBuffer buffer = new StringBuffer();
                try {

                    HttpGet get = new HttpGet("HTTP://"+getIp().trim());
                    HttpClient client = new DefaultHttpClient();
                    HttpResponse res = client.execute(get);
                    buffer.append(res.getStatusLine().toString() + "\n");
                    BufferedInputStream in = new BufferedInputStream(res.getEntity().getContent());
                    byte[] msg = new byte[in.available()];
                    in.read(msg);
                    String str = new String(msg);
                    buffer.append(str+"\n");
                    handler.postDelayed(new Runnable() {
                        @Override
                        public void run() {
                            tx.setText(buffer.toString());

                        }
                    }, 5000);

                } catch (IOException e) {
                    Log.e("net", "클라이언트 연결 실패");
                }
            }
        };
        client.start();
    }

    Handler handler = new Handler();

    public String getIp() {
        String realIP = "0.0.0.0";
        try {
            WifiManager wifiManager = (WifiManager) getSystemService(Context.WIFI_SERVICE);
            DhcpInfo dhcpInfo = wifiManager.getDhcpInfo();

            String[] dhcpInfos = dhcpInfo.toString().split(" ");
            int i = 0;
            while (i < dhcpInfos.length) {
                if (dhcpInfos[i].equals("ipaddr")) {
                    realIP = dhcpInfos[i + 1];
                    break;
                }
                i++;
            }
            tx.setText("와이파이 : " + realIP);
        } catch (Exception e) {
//            Log.e("net", e.getMessage());
        }
        return realIP;
    }

}

