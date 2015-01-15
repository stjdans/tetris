package com.example.tetris.activity;

import android.app.Activity;
import android.os.Bundle;
import android.widget.FrameLayout;
import android.widget.TextView;

import com.example.tetris.R;
import com.example.tetris.network.ResponseFrag;

/**
 * 미구현 ...
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
    }

    /*Handler handler = new Handler();

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
*/
}

