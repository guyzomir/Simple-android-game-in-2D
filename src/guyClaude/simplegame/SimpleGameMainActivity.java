package guyClaude.simplegame;

import android.os.Bundle;
import android.app.Activity;
import android.view.Menu;

public class SimpleGameMainActivity extends Activity {
	private GameView gameView;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_simple_game_main);
		gameView = new GameView(this);
		setContentView(gameView);
	}
protected void onPause(){  
	super.onPause();
	gameView.stopGame();
	
}
	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.simple_game_main, menu);
		return true;
	}

}
