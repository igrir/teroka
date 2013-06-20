package com.tanyoo.teroka.activities;

import android.app.Activity;
import android.bluetooth.BluetoothAdapter;
import android.bluetooth.BluetoothDevice;
import android.content.Context;
import android.content.Intent;
import android.content.pm.ActivityInfo;
import android.hardware.Sensor;
import android.hardware.SensorEvent;
import android.hardware.SensorEventListener;
import android.hardware.SensorManager;
import android.location.LocationListener;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.util.Log;
import android.view.Menu;
import android.view.MotionEvent;
import android.view.View;
import android.view.View.OnTouchListener;
import android.view.Window;
import android.view.WindowManager;
import android.widget.ArrayAdapter;
import android.widget.Toast;
import android.widget.ViewFlipper;

import com.tanyoo.teroka.R;
import com.tanyoo.teroka.lib.Acel;
import com.tanyoo.teroka.lib.BluetoothTerokaService;
import com.tanyoo.teroka.lib.GameActivity;
import com.tanyoo.teroka.lib.GameView;
import com.tanyoo.teroka.lib.SoundGame;
import com.tanyoo.teroka.view.Bertarung;

public class BertarungActivity extends GameActivity  implements OnTouchListener, SensorEventListener{

	public int health;
	public int musuhHealth;
	public int healthFull;
	public int statusMusuhBerdiri;
	public int statusBerdiri;
	public int attackMusuh;
	public int attack;
	
	
	
	// Debugging
    private static final String TAG = "BluetoothChat";
    private static final boolean D = true;

    // Message types sent from the BluetoothChatService Handler
    public static final int MESSAGE_STATE_CHANGE = 1;
    public static final int MESSAGE_READ = 2;
    public static final int MESSAGE_WRITE = 3;
    public static final int MESSAGE_DEVICE_NAME = 4;
    public static final int MESSAGE_TOAST = 5;

    // Key names received from the BluetoothChatService Handler
    public static final String DEVICE_NAME = "device_name";
    public static final String TOAST = "toast";

    // Intent request codes
    private static final int REQUEST_CONNECT_DEVICE_SECURE = 1;
    private static final int REQUEST_CONNECT_DEVICE_INSECURE = 2;
    private static final int REQUEST_ENABLE_BT = 3;
    
    // Name of the connected device
    private String mConnectedDeviceName = null;
    // Array adapter for the conversation thread
    private ArrayAdapter<String> mConversationArrayAdapter;
    // String buffer for outgoing messages
    private StringBuffer mOutStringBuffer;
    // Local Bluetooth adapter
    private BluetoothAdapter mBluetoothAdapter = null;
	// mesin
    private BluetoothTerokaService mTerokaService = null;
    
	private Bertarung gv;
	
	// views
	//public MenuBertarung mu;
	public Bertarung mu;
	
	// kontrol accelerometer 
	private SensorManager mSensorManager;
	private Sensor mSensor;
	private Acel acel;
	
	//sound
	SoundGame sound;
	
	public int statusDefense;
		
	public boolean statusBTConnected = false;
	public boolean disconnectBT = false;
	public int statusGame; 	//0 = mulai, 1 = bermain, 2 = game over
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		
		super.onCreate(savedInstanceState);
		
		this.statusBTConnected = false;
		this.disconnectBT = false;
		
		//orientasi
		setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_NOSENSOR);
		//hilangkan title bar
		this.requestWindowFeature(Window.FEATURE_NO_TITLE);
		//hilangkan notification bar
		this.getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);
		
		//inisiaslisi graphic view
		//mu = new MenuBertarung(this);
		mu = new Bertarung(this);
		
		gv = mu;
		
		//set aksi yang dilakukan oleh touch dilakukan siapa
		gv.setOnTouchListener(this);

		
		
		//set tampilan yang muncul
		setContentView(gv);
		// Get local Bluetooth adapter
        mBluetoothAdapter = BluetoothAdapter.getDefaultAdapter();
        
     // If the adapter is null, then Bluetooth is not supported
        if (mBluetoothAdapter == null) {
            Toast.makeText(this, "Bluetooth is not available", Toast.LENGTH_LONG).show();
            finish();
            return;
        }

//			mu.startThread();
        
      //Sensor
		this.acel = new Acel();
		mSensorManager = (SensorManager) getSystemService(Context.SENSOR_SERVICE);
		if (mSensorManager.getDefaultSensor(Sensor.TYPE_ACCELEROMETER) != null) {
			mSensor = mSensorManager.getDefaultSensor(Sensor.TYPE_ACCELEROMETER);
			mSensorManager.registerListener(this, mSensor, SensorManager.SENSOR_DELAY_GAME);
			
		}
		
		sound = new SoundGame(this);
		sound.initSound();
		
	}
	@Override
    public void onStart() {
        super.onStart();
        if(D) Log.e(TAG, "++ ON START ++");
        // If BT is not on, request that it be enabled.
        // setupChat() will then be called during onActivityResult
        if (!mBluetoothAdapter.isEnabled()) {
        	
        	Intent enableIntent = new Intent(BluetoothAdapter.ACTION_REQUEST_ENABLE);
            
            startActivityForResult(enableIntent, REQUEST_ENABLE_BT);
        } else {
            if (mTerokaService == null){
            	// Initialize the BluetoothChatService to perform bluetooth connections
                mTerokaService= new BluetoothTerokaService(this, mHandler);

                // Initialize the buffer for outgoing messages
                mOutStringBuffer = new StringBuffer("");
                
            }
        }
    }
	
    @Override
     public synchronized void onResume() {
        super.onResume();
        if(D) Log.e(TAG, "+ ON RESUME +");

        // Performing this check in onResume() covers the case in which BT was
        // not enabled during onStart(), so we were paused to enable it...
        // onResume() will be called when ACTION_REQUEST_ENABLE activity returns.
        if (mTerokaService!= null) {
            // Only if the state is STATE_NONE, do we know that we haven't started already
            if (mTerokaService.getState() == BluetoothTerokaService.STATE_NONE) {
              // Start the Bluetooth chat services
              mTerokaService.start();
            }
        }
    }
	
	@Override
	protected void onPause() {
		// TODO Auto-generated method stub
		super.onPause();
		if(D) Log.e(TAG, "- ON PAUSE -");
		gv.setReady(false);
		System.gc();
	}
	@Override
    public void onStop() {
        super.onStop();
        if(D) Log.e(TAG, "-- ON STOP --");
    }

    @Override
    public void onDestroy() {
        
    	mu.recycleEntityCollection();
    	
        // Stop the Bluetooth chat services
        if (mTerokaService!= null) mTerokaService.stop();
        if(D) Log.e(TAG, "--- ON DESTROY ---");
        mSensorManager.unregisterListener(this);
        
		System.out.println("DESTROY THE BITMAPS");
		System.gc();
		mu.shutDownThread();
		setResult(1);
		super.onDestroy();
    }

    @Override
    public void run() {
    	// TODO Auto-generated method stub
    	super.run();

    	if (this.statusBTConnected == true) {    		
    		if (this.statusGame == 0) {
    			updateHUD();
    			gv.setMusuh(String.valueOf(this.statusMusuhBerdiri), String.valueOf(this.attackMusuh), String.valueOf(this.musuhHealth));
            	gv.setKita(String.valueOf(this.statusBerdiri), String.valueOf(this.attack), String.valueOf(this.health));
            	
            	//kalau musuh menyerang dan kita tidak bertahan
            	if (this.attackMusuh > 0 && this.statusBerdiri == 1) {
            		this.health -= attackMusuh;
            	}
            	
            	if (this.health <= 0 || this.musuhHealth <= 0) {
            		this.statusGame = 2;
            	}
    		}else{
    			//jika player menang
        		if (this.health <= 0 && this.musuhHealth > 0) {
        			gv.showELose(true);
        		}
        		if (this.health > 0 && this.musuhHealth <= 0){
        			gv.showEWin(true);
        		}
    		}
    		//cek koneksi blue
    		if(mTerokaService.getState()!=BluetoothTerokaService.STATE_CONNECTED)
        	{
        		
        		this.statusBTConnected = false;  
        		this.disconnectBT = true;
        	}
    		
    	}
    	if(this.disconnectBT == true)
    	{
    			finish();
    			this.disconnectBT = false;
    	}
    	
    }
    
    public void updateHUD(){
    	float playerHealth = this.health;
		float playerHealthFull = this.healthFull;	//maksimum health
		int percentHealth = (int)Math.ceil(((playerHealth/playerHealthFull)*(float)100)); 
		gv.setBarHealthPlayer(percentHealth);
		
		float musuhHealth = this.musuhHealth;
		float musuhHealthFull = this.healthFull;	//maksimum health
		int percentHealthMusuh = (int)Math.ceil(((musuhHealth/musuhHealthFull)*(float)100)); 
		gv.setBarHealthMusuh(percentHealthMusuh);
		
		gv.showELose(false);
		gv.showEWin(false);
    }
    
    
	@Override
	public boolean onTouch(View v, MotionEvent event) {
		//passing posisi ke graphicsview
		int action = event.getAction();
		switch (action) {
		  case MotionEvent.ACTION_DOWN: //jari menyentuh layar
			  	 gv.posXDown = event.getX();  
			  	 gv.posYDown = event.getY();
				gv.onDown();
				break;
		  case MotionEvent.ACTION_MOVE:  //bergerak
			   gv.onMove();
			   break;
		  case MotionEvent.ACTION_UP:  //diangkat
			  	gv.posXUp = event.getX();  
			  	gv.posYUp = event.getY();
			   gv.onUp();
			   break;
		  case MotionEvent.ACTION_CANCEL: //batal
			   break;
		  default:
			   break;
			
		}
		gv.invalidate(); //draw ulang
		return true;
	}
	private void ensureDiscoverable() {
        if(D) Log.d(TAG, "ensure discoverable");
        if (mBluetoothAdapter.getScanMode() !=
            BluetoothAdapter.SCAN_MODE_CONNECTABLE_DISCOVERABLE) {
            Intent discoverableIntent = new Intent(BluetoothAdapter.ACTION_REQUEST_DISCOVERABLE);
            discoverableIntent.putExtra(BluetoothAdapter.EXTRA_DISCOVERABLE_DURATION, 300);
            startActivity(discoverableIntent);
        }
    }
	private final Handler mHandler = new Handler() {
        @Override
        public void handleMessage(Message msg) {
            switch (msg.what) {
            case MESSAGE_STATE_CHANGE:
                if(D) Log.i(TAG, "MESSAGE_STATE_CHANGE: " + msg.arg1);
                switch (msg.arg1) {
                case BluetoothTerokaService.STATE_CONNECTED:
                	Toast.makeText(getApplicationContext(),getString(R.string.title_connected_to, mConnectedDeviceName) ,Toast.LENGTH_SHORT).show();
                    ;
                    statusBTConnected = true;
                    break;
                case BluetoothTerokaService.STATE_CONNECTING:
                	Toast.makeText(getApplicationContext(),getString(R.string.title_connecting) ,Toast.LENGTH_SHORT).show();
                    ;
                    break;
                case BluetoothTerokaService.STATE_LISTEN:
                	Toast.makeText(getApplicationContext(),"Menunggu Koneksi" ,Toast.LENGTH_SHORT).show();
                    
                	break;
                case BluetoothTerokaService.STATE_NONE:
                	Toast.makeText(getApplicationContext(),getString(R.string.title_not_connected) ,Toast.LENGTH_SHORT).show();
                    
                    break;
                }
                break;
            case MESSAGE_WRITE:
                byte[] writeBuf = (byte[]) msg.obj;
                // construct a string from the buffer
                String writeMessage = new String(writeBuf);
                //Toast.makeText(getApplicationContext(),writeMessage, Toast.LENGTH_SHORT).show();
                break;
            case MESSAGE_READ:
                byte[] readBuf = (byte[]) msg.obj;
                // construct a string from the valid bytes in the buffer
                String readMessage = new String(readBuf, 0, msg.arg1);
//                Toast.makeText(getApplicationContext(),readMessage, Toast.LENGTH_SHORT).show();
                
                readMessage(readMessage);
                
                break;
            case MESSAGE_DEVICE_NAME:
                // save the connected device's name
                mConnectedDeviceName = msg.getData().getString(DEVICE_NAME);
                Toast.makeText(getApplicationContext(), "Connected to "
                               + mConnectedDeviceName, Toast.LENGTH_SHORT).show();
                
                //BertarungActivity.this.sendMessage("1000");
                
                startBertarung();
                
                break;
            case MESSAGE_TOAST:
//                Toast.makeText(getApplicationContext(), msg.getData().getString(TOAST),
//                               Toast.LENGTH_SHORT).show();
                break;
            }
        }
    };
    
    public void startBertarung(){
    	gv.hideMenu();
    	
    	//inisialisasi health musuh dan player
    	this.health = 1000;
    	this.musuhHealth = 1000;
    	
    	this.healthFull = this.health;
    	this.statusDefense = 0;
    	this.statusGame = 0;
    }
    
	public void onActivityResult(int requestCode, int resultCode, Intent data) {
        if(D) Log.d(TAG, "onActivityResult " + resultCode);
        switch (requestCode) {
        case REQUEST_CONNECT_DEVICE_SECURE:
            // When DeviceListActivity returns with a device to connect
            if (resultCode == Activity.RESULT_OK) {
            	connectDevice(data, true);
            }
            break;
        case REQUEST_CONNECT_DEVICE_INSECURE:
            // When DeviceListActivity returns with a device to connect
            if (resultCode == Activity.RESULT_OK) {
            	 connectDevice(data, false);
            }
            break;
        case REQUEST_ENABLE_BT:
            // When the request to enable Bluetooth returns
            if (resultCode == Activity.RESULT_OK) {
                // Bluetooth is now enabled, so set up a chat session
                //setupChat();
            } else {
                // User did not enable Bluetooth or an error occurred
                Log.d(TAG, "BT not enabled");
                Toast.makeText(this, R.string.bt_not_enabled_leaving, Toast.LENGTH_SHORT).show();
                finish();
            }
        }
    }
	 private void connectDevice(Intent data, boolean secure) {
	        // Get the device MAC address
	        String address = data.getExtras()
	            .getString(ListPlayerActivity.EXTRA_DEVICE_ADDRESS);
	        // Get the BluetoothDevice object
	        Toast.makeText(this, address, Toast.LENGTH_SHORT).show();
	        BluetoothDevice device = mBluetoothAdapter.getRemoteDevice(address);
	        // Attempt to connect to the device
	        mTerokaService.connect(device, secure);
	    }
	 
	public void tombolClient(){
		
		Intent iBattle = new Intent(this, ListPlayerActivity.class);
		startActivityForResult(iBattle, REQUEST_CONNECT_DEVICE_SECURE);
	}

	public void tombolHost() {
		// TODO Auto-generated method stub
		ensureDiscoverable();
		//mTerokaService.connect();
		Toast.makeText(getApplicationContext(), "WAITING FOR BLUETHOOT", Toast.LENGTH_LONG).show();
		//mTerokaService.start();
		
		
		//mengubah tampilan menjadi bertarung
		//setContentView(b);
	}
	/**
     * Sends a message.
     * @param message  A string of text to send.
     */
    private void sendMessage(String message) {
        // Check that we're actually connected before trying anything
        if (mTerokaService.getState() != BluetoothTerokaService.STATE_CONNECTED) {
           finish();
        	return;
        }

        // Check that there's actually something to send
        if (message.length() > 0) {
            // Get the message bytes and tell the BluetoothChatService to write
            byte[] send = message.getBytes();
            mTerokaService.write(send);

        }
    }
	@Override
	public void onAccuracyChanged(Sensor arg0, int arg1) {
		// TODO Auto-generated method stub
		
	}
	@Override
	public void onSensorChanged(SensorEvent event) {
		// TODO Auto-generated method stub
		
		double ax=0,ay=0,az=0;
		
		if (event.sensor.getType() == Sensor.TYPE_ACCELEROMETER){
			ax = event.values[0];
			ay = event.values[1];
			az = event.values[2];
		}
		
		if (this.statusBTConnected == true) {
			//cek attack
			if (acel.attack(ax)){
				if(acel.attackStat==true){ //jika attack 
					//sound.soundAttack(); //aktifkan suara attack
					sound.playSound(SoundGame.SOUND_SWING);
					playerAttack();
					this.statusDefense = 0;
				}					
			}else if (acel.defense(az)) {
				if (this.statusDefense == 0) {
					sound.playSound(SoundGame.SOUND_DEFENSE);
					this.statusDefense = 1;
				}
				
				playerDefense();
			}else{
				this.statusDefense = 0;
				playerIdle();
			}
		}
		
	}
	
	/**
	 * Kirim data player menyerang
	 */
	public void playerAttack(){
		
		//status_berdiri serangan health
		//status_berdiri 1 = terbuka, 2 = defense
		
		
		int randomSerangan = (int)(Math.random()*8);
		this.statusBerdiri = 1;
    	sendMessage(this.statusBerdiri+ "#"+String.valueOf(randomSerangan)+ "#" + this.health);
	}
	
	public void playerIdle(){
		this.statusBerdiri = 1;
		BertarungActivity.this.sendMessage("1#0#"+this.health);
	}
	
	public void readMessage(String message){
		
		String parsedMessage[] = parseSend(message);
		int m_status_berdiri = Integer.valueOf(parsedMessage[0]);
		int m_serangan = Integer.valueOf(parsedMessage[1]);
		int m_health = Integer.valueOf(parsedMessage[2]);
		
		this.musuhHealth = m_health;
		this.statusMusuhBerdiri = m_status_berdiri;
		this.attackMusuh = m_serangan;
		
		//kita kena serangan
		if (m_serangan > 0 && this.statusBerdiri == 1) {
			this.health -= m_serangan;
		}
		
		
		
	}
	
	/**
	 * Kirim data player bertahan
	 */
	public void playerDefense(){
		this.statusBerdiri = 2;
		sendMessage("2#0#"+this.health);
	}
	
	public String[] parseSend(String pesan){
		String[] hasil = pesan.split("#");
		System.out.println(hasil[0] + " "+ hasil[1] + " "+ hasil[2]);
		return hasil;
	}
	
}
