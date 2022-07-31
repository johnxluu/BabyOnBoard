package com.zybooks.babyonboard

import android.annotation.SuppressLint
import android.app.Activity
import android.bluetooth.BluetoothAdapter
import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.activity.OnBackPressedCallback
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentTransaction
import kotlinx.android.synthetic.main.fragment_bluetooth_page.*

class BluetoothPageFragment : Fragment() {

    private val REQUEST_CODE_ENABLE_BT:Int = 1
    private val REQUEST_CODE_DISCOVERABLE_BT:Int = 2

    //bluetooth adapter
    lateinit var bAdapter: BluetoothAdapter

    @SuppressLint("MissingPermission")
    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {
        super.onCreate(savedInstanceState)
        val view = inflater.inflate(R.layout.fragment_bluetooth_page, container, false)

        /*
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
                Toast.makeText(context, "already on", Toast.LENGTH_LONG).show()
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
                Toast.makeText(context, "already off", Toast.LENGTH_LONG).show()
            } else {
                //tun on bluetooth
                bAdapter.disable()
                bluetoothIv.setImageResource(R.drawable.ic_bluetooth_off)
                Toast.makeText(context, "Bluetooth turned off", Toast.LENGTH_LONG).show()

            }
        }

        //discoverable the bluetooth
        discoverableBtn.setOnClickListener {
            if (!bAdapter.isDiscovering){
                Toast.makeText(context, "Making your device discoverable", Toast.LENGTH_LONG).show()
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
                Toast.makeText(context, "Turn on bluetooth first", Toast.LENGTH_LONG).show()

            }
        }
         */

        // This callback will only be called when the fragment is at least started.
        val callback: OnBackPressedCallback =
            object : OnBackPressedCallback(true /* enabled by default */) {
                override fun handleOnBackPressed() {
                    val methodSelectionFrag = MethodSelectionFragment()
                    val transaction: FragmentTransaction = requireFragmentManager().beginTransaction()
                    transaction.replace(R.id.nav_host_fragment, methodSelectionFrag)
                        .commit()
                }
            }
        requireActivity().onBackPressedDispatcher.addCallback(viewLifecycleOwner, callback)
        // The callback can be enabled or disabled here or in handleOnBackPressed()

        return view
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        when(requestCode){
            REQUEST_CODE_ENABLE_BT ->
                if(resultCode== Activity.RESULT_OK){
                    bluetoothIv.setImageResource(R.drawable.ic_bluetooth_on)
                    Toast.makeText(context, "Bluetooth is on", Toast.LENGTH_LONG).show()

                } else {
                    Toast.makeText(context, "Could not turn on bluetooth", Toast.LENGTH_LONG).show()

                }
        }
        super.onActivityResult(requestCode, resultCode, data)
    }
}