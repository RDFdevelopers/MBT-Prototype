package rdf.game.medievalbattletouchproto;

import android.net.Uri;
import android.os.Bundle;
import android.os.Handler;
import android.app.Activity;
import android.content.Intent;
import android.view.Menu;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends Activity {
	
	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		
		// --------------------------------------------
		TextView play = (TextView) findViewById(R.id.button_play);
		play.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {
				Toast.makeText(getApplicationContext(), R.string.menu_play,
						Toast.LENGTH_SHORT).show();
				startGame();
			}
		});

		TextView options = (TextView) findViewById(R.id.button_options);
		options.setOnClickListener(new OnClickListener() {
			@Override
			public void onClick(View v) {
				Toast.makeText(getApplicationContext(), R.string.menu_options,
						Toast.LENGTH_SHORT).show();
			}
		});

		TextView exit = (TextView) findViewById(R.id.button_exit);
		exit.setOnClickListener(new OnClickListener() {
			@Override
			public void onClick(View v) {
				Toast.makeText(getApplicationContext(), R.string.menu_exit,
						Toast.LENGTH_SHORT).show();
			}
		});

		ImageView logo = (ImageView) findViewById(R.id.imageView2);
		logo.setOnClickListener(new OnClickListener() {
			@Override
			public void onClick(View v) {
				Intent viewIntent = new Intent("android.intent.action.VIEW",
						Uri.parse("https://github.com/RDFdevelopers"));
				startActivity(viewIntent);
			}
		});
	}

	protected void startGame() {
		Intent game = new Intent(this, GameActivity.class);
		this.startActivity(game);
		
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.activity_main, menu);
		return true;
	}

	@Override
	protected void onDestroy() {
		super.onDestroy();
	}

}
