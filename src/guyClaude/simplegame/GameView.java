package guyClaude.simplegame;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Point;
import android.view.SurfaceHolder;
import android.view.SurfaceHolder.Callback;
import android.view.SurfaceView;

public class GameView extends SurfaceView implements Callback{
	private Point myCircle;
	private int direction;
	private int radius;
	private Paint circlePaint;
	private GameThread gameThread;
	private int screenWidth;
	public GameView (Context context) {
		super (context);
		getHolder().addCallback(this);
		circlePaint = new Paint();
		circlePaint.setColor(Color.GREEN);
		circlePaint.setAntiAlias(true);
		
myCircle = new Point();
myCircle.x=0;
myCircle.y=300;
direction=1;
radius = 30;

	}
	private void updatedPosition (double elapsedTime){
		myCircle.x += elapsedTime/5 *direction;
		if(myCircle.x>screenWidth){
			myCircle.x = screenWidth;
			
		direction = -1;
			
		}else if( myCircle.x<=0){
			myCircle.x = 0;
			
		direction = 1;}
		
		
	}
@Override
	protected void onSizeChanged(int w, int h, int oldw, int oldh) {
		screenWidth=w;
	}

public void drawGameElements(Canvas canvas){
	canvas.drawColor(Color.YELLOW);
	canvas.drawCircle(myCircle.x, myCircle.y, radius, circlePaint);
	
}

private class GameThread extends Thread { 
	private SurfaceHolder surfaceHolder;
	private boolean threadRunning = true;
	long previousFrameTime = 
			System.currentTimeMillis();
	long currentTime;
	double elapsedTimeMS;
	public GameThread (SurfaceHolder holder){
		
		surfaceHolder = holder;
	}
	
	public void setRunning (boolean state){
		threadRunning = state;
		
		
	}
	
	
	public void run(){
		Canvas canvas = null;
		while(threadRunning){
			try {
				canvas=surfaceHolder.lockCanvas(null);
				synchronized(surfaceHolder){
					

					currentTime = System.currentTimeMillis();
					elapsedTimeMS=currentTime-previousFrameTime;
			        previousFrameTime = currentTime;
			        updatedPosition(elapsedTimeMS);
			        drawGameElements(canvas);
				}
			}finally{
				if(canvas !=null){
					surfaceHolder.unlockCanvasAndPost(canvas);
				}
			}
		}
	}
	
	
	
	

		
        
	
	}





	


public void surfaceChanged(SurfaceHolder arg0, int arg1, int arg2, int arg3) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void surfaceCreated(SurfaceHolder holder) {
		gameThread = new GameThread(holder);
		gameThread.setRunning(true);
		gameThread.start();
		
	}

	@Override
	public void surfaceDestroyed(SurfaceHolder holder) {
		gameThread.setRunning(false);
		
	}
	
public void stopGame(){
	if(gameThread!=null)
		gameThread.setRunning(false);
}


}
