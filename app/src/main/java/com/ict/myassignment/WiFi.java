package com.ict.myassignment;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;

import android.Manifest;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.content.pm.PackageManager;
import android.net.wifi.ScanResult;
import android.net.wifi.WifiManager;
import android.os.Bundle;
import android.widget.CompoundButton;
import android.widget.Switch;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

public class WiFi extends AppCompatActivity {
    Switch s;
    WifiManager wm;
    BroadcastReceiver br;
    IntentFilter ifr;
    public static final int REQ_ID_PERMISSION=1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_wi_fi);
        checkandRequestPermission();
        s=findViewById(R.id.switch1);
        wm=(WifiManager) getApplicationContext().getSystemService(WIFI_SERVICE);
        s.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton compoundButton, boolean b) {
                if (b)
                {
                    if (!wm.isWifiEnabled())
                    {
                        wm.setWifiEnabled(true);
                    }
                    wm.startScan();
                }
                else
                {
                    if(wm.isWifiEnabled())
                    {
                        wm.setWifiEnabled(false);
                    }
                }
            }
        });
        br=new BroadcastReceiver() {
            @Override
            public void onReceive(Context context, Intent intent) {
                List<ScanResult> ls=wm.getScanResults();
                for(ScanResult s:ls)
                {
                    Toast.makeText(context, ""+s.SSID+"\n"+s.BSSID+"\n"+s.level, Toast.LENGTH_SHORT).show();
                }
            }
        };
        wm.startScan();
        ifr=new IntentFilter(WifiManager.SCAN_RESULTS_AVAILABLE_ACTION);
        registerReceiver(br,ifr);
    }
    private boolean checkandRequestPermission()
    {
        int phone_state= ContextCompat.checkSelfPermission(this, Manifest.permission.ACCESS_WIFI_STATE);
        int coll_log=ContextCompat.checkSelfPermission(this,Manifest.permission.CHANGE_WIFI_STATE);
        int fine_1=ContextCompat.checkSelfPermission(this,Manifest.permission.ACCESS_FINE_LOCATION);
        int coarse_1=ContextCompat.checkSelfPermission(this,Manifest.permission.ACCESS_COARSE_LOCATION);
        List<String> ls=new ArrayList<>();
        if (phone_state!= PackageManager.PERMISSION_GRANTED)
        {
            ls.add(Manifest.permission.ACCESS_WIFI_STATE);
        }
        if (coll_log!=PackageManager.PERMISSION_GRANTED)
        {
            ls.add(Manifest.permission.CHANGE_WIFI_STATE);
        }
        if (coarse_1!=PackageManager.PERMISSION_GRANTED)
        {
            ls.add(Manifest.permission.ACCESS_COARSE_LOCATION);
        }
        if (fine_1!=PackageManager.PERMISSION_GRANTED)
        {
            ls.add(Manifest.permission.ACCESS_FINE_LOCATION);
        }
        if (!ls.isEmpty())
        {
            ActivityCompat.requestPermissions(this,ls.toArray(new String[ls.size()]),REQ_ID_PERMISSION);
            return false;
        }
        return true;
    }
}