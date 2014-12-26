package com.bingo;

import com.example.bingo.R;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class Scores extends Activity {
	
	Button back;
	TextView plays;
	TextView win;
	TextView lose;
	TextView level;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_scores);
		String text = "0";
		
		plays = (TextView) findViewById(R.id.PlayID);
		plays.setText(text);
		win = (TextView) findViewById(R.id.WinID);
		win.setText(text);
		lose = (TextView) findViewById(R.id.LoseID);
		lose.setText(text);
		level = (TextView) findViewById(R.id.LevelID);
		level.setText(text);
		
		back = (Button) findViewById(R.id.BAck);
		back.setOnClickListener(new View.OnClickListener() {
			
			@Override
			public void onClick(View v) {
				Intent intent = new Intent(Scores.this, MainActivity.class);
				startActivity(intent);
				
			}
		});
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.scores, menu);
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
}
