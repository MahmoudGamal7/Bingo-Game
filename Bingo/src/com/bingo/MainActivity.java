package com.bingo;


import com.example.bingo.R;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.ProgressBar;


public class MainActivity extends Activity {
	
	private ProgressBar myProgress;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        myProgress = (ProgressBar) findViewById(R.id.progressBar1);
        myProgress.setMax(100);
        Thread th = new Thread(){
        	int progressValue = 0;

        	@Override
			public void run() {
				while (progressValue <= 100) {
					try {
						sleep(300);
					} catch (InterruptedException e) {
						e.printStackTrace();
					}
					finally{

						Intent intent = new Intent(MainActivity.this,MainMenu.class);
	        			startActivity(intent);
	        		}
					//used to make changes on UI from other threads
					MainActivity.this.runOnUiThread(new Runnable() {
						@Override
						public void run() {
							myProgress.setProgress(progressValue);
//							text.setText(progressValue + "%");
							progressValue += 10;
						}
					});
				}
			}
		};
		
        th.start();
        
}



    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();
        if (id == R.id.action_settings) {
            return true;
        }
        return super.onOptionsItemSelected(item);
    }
    
    @Override
    public void onDestroy()
    {
        android.os.Process.killProcess(android.os.Process.myPid());
        super.onDestroy();
    }
}
