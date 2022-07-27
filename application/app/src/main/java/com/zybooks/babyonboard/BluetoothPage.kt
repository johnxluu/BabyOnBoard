package com.zybooks.babyonboard

import android.annotation.SuppressLint
import android.app.Activity
import android.bluetooth.BluetoothAdapter
import android.content.Intent
import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.activity.result.contract.ActivityResultContracts
import kotlinx.android.synthetic.main.activity_main.*

class BluetoothPage : AppCompatActivity() {

    private val REQUEST_CODE_ENABLE_BT:Int = 1
    private val REQUEST_CODE_DISCOVERABLE_BT:Int = 2

    //bluetooth adapter
    lateinit var bAdapter: BluetoothAdapter

    @SuppressLint("MissingPermission")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)


        //init bluetooth adapter
        bAdapter = BluetoothAdapter.getDefaultAdapter()
        //check if bluetooth is on/off
        if(bAdapter==null){
            bluetoothStatusTv.text = "Bluetooth is not available"
        } else {
            bluetoothStatusTv.text = "Bluetooth is available"
        }

        //set image according to bluetooth status(on/off)
        if(bAdapter.isEnabled){
            //bluetooth is on
            bluetoothIv.setImageResource(R.drawable.ic_bluetooth_off)
        } else {
            //bluetooth is off
            bluetoothIv.setImageResource(R.drawable.ic_bluetooth_on)
        }


        //turn on bluetooth
        turnOnBtn.setOnClickListener {
            if (bAdapter.isEnabled){
                //already enabled
                Toast.makeText(this, "already on", Toast.LENGTH_LONG).show()
            } else {
                //tun on bluetooth
                val intent = Intent(BluetoothAdapter.ACTION_REQUEST_ENABLE)
                startActivityForResult(intent, REQUEST_CODE_ENABLE_BT)
            }
        }

        //turn off bluetooth
        turnOffBtn.setOnClickListener {
            if (!bAdapter.isEnabled){
                //already enabled
                Toast.makeText(this, "already off", Toast.LENGTH_LONG).show()
            } else {
                //tun on bluetooth
                bAdapter.disable()
                bluetoothIv.setImageResource(R.drawable.ic_bluetooth_off)
                Toast.makeText(this, "Bluetooth turned off", Toast.LENGTH_LONG).show()

            }
        }

        //discoverable the bluetooth
        discoverableBtn.setOnClickListener {
            if (!bAdapter.isDiscovering){
                Toast.makeText(this, "Making your device discvoerable", Toast.LENGTH_LONG).show()
                val intent = (Intent(BluetoothAdapter.ACTION_REQUEST_DISCOVERABLE))
                startActivityForResult(intent, REQUEST_CODE_DISCOVERABLE_BT )
            }
        }
        //get list of paired devices
        pairedBtn.setOnClickListener{
            if(bAdapter.isEnabled){
                pairedTv.text = "Paired Devices"
                //get list of paired devices
                val devices = bAdapter.bondedDevices
                for (device in devices){
                    val deviceName = device.name
                    val deviceAddress = device
                    pairedTv.append("\nDevice: $deviceName, $device")
                }
            } else {
                Toast.makeText(this, "Turn on bluetooth first", Toast.LENGTH_LONG).show()

            }
        }
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        when(requestCode){
            REQUEST_CODE_ENABLE_BT ->
                if(resultCode== Activity.RESULT_OK){
                    bluetoothIv.setImageResource(R.drawable.ic_bluetooth_on)
                    Toast.makeText(this, "Bluetooth is on", Toast.LENGTH_LONG).show()

                } else {
                    Toast.makeText(this, "COuld not turn on bluetooth", Toast.LENGTH_LONG).show()

                }
        }
        super.onActivityResult(requestCode, resultCode, data)
    }
}