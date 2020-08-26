package com.ict.myassignment;

import androidx.appcompat.app.AppCompatActivity;

import android.bluetooth.BluetoothAdapter;
import android.bluetooth.BluetoothDevice;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.os.Bundle;
import android.widget.CompoundButton;
import android.widget.Switch;
import android.widget.Toast;

public class Bluetooth extends AppCompatActivity {
    BluetoothDevice bd;
    BluetoothAdapter ba;
    BroadcastReceiver br;
    IntentFilter ifr;
    Switch s;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_bluetooth);
        s=findViewById(R.id.switch2);
        ba=BluetoothAdapter.getDefaultAdapter();
        if(ba==null)
        {
            Toast.makeText(this, "Bluetooth not present", Toast.LENGTH_SHORT).show();
            return;
        }
        else
        {
            s.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
                @Override
                public void onCheckedChanged(CompoundButton compoundButton, boolean b) {
                    if (b)
                    {
                        if(!ba.isEnabled())
                        {
                            Intent intent=new Intent(BluetoothAdapter.ACTION_REQUEST_ENABLE);
                            startActivity(intent);
                            Toast.makeText(Bluetooth.this, "Turning on...", Toast.LENGTH_SHORT).show();
                        }
                        ba.startDiscovery();
                    }
                    else
                    {
                        if (ba.isEnabled())
                        {
                            ba.disable();
                        }
                    }
                }
            });
        }
        br=new BroadcastReceiver() {
            @Override
            public void onReceive(Context context, Intent intent) {
                String action=intent.getAction();
                Toast.makeText(context, "Device found", Toast.LENGTH_SHORT).show();
                if(action.equals(BluetoothDevice.ACTION_FOUND))
                {
                    Bundle b=intent.getExtras();
                    bd=b.getParcelable(BluetoothDevice.EXTRA_DEVICE);
                    Toast.makeText(context, ""+bd.getName()+"\n"+bd.getAddress(), Toast.LENGTH_SHORT).show();
                }
            }
        };
        ba.startDiscovery();
        ifr=new IntentFilter(BluetoothDevice.ACTION_FOUND);
        registerReceiver(br,ifr);
    }

    @Override
    protected void onResume() {
        super.onResume();
        ba.startDiscovery();
    }
}