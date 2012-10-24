package rdf.game.medievalbattletouchproto.renderer;

//import rdf.game.medievalbattletouchproto.game.Bola;
//import rdf.game.medievalbattletouchproto.game.BolaMoveThread;
import rdf.game.medievalbattletouchproto.R;
import rdf.game.medievalbattletouchproto.game.Coordenada;
import rdf.game.medievalbattletouchproto.game.Unidad;
import rdf.game.medievalbattletouchproto.game.Guerrero;
import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Rect;
import android.util.DisplayMetrics;
import android.view.MotionEvent;
import android.view.SurfaceHolder;
import android.view.SurfaceView;

public class GameView extends SurfaceView implements SurfaceHolder.Callback {

	private GameRenderer renderer;
	//private BolaMoveThread bolaThread;

	private Unidad playerIzda;
	private Unidad playerDcha;
	//private ElementoPong bola;

	private Unidad elementoActivo = null;
	private int origenY;
	private int origenX;
	
	//Fondo:
	
	public GameView(Context context) {
		super(context);
		getHolder().addCallback(this);
	}

	public void surfaceChanged(SurfaceHolder holder, int format, int width,
			int height) {
	}

	public void surfaceCreated(SurfaceHolder holder) {
		playerIzda = new Guerrero(new Coordenada(50, getHeight() / 2 - 50), 50, 50,"ROJO");
		playerDcha = new Guerrero(new Coordenada(getWidth() - 70, getHeight() / 2 - 50), 50, 50, "AMARILLO");
		//bola = new Bola(new Coordenada(getWidth() / 2 - 5, getHeight() / 2 - 5), 10, 10);

		renderer = new GameRenderer(getHolder(), this);
		renderer.setRunning(true);
		renderer.start();

		//bolaThread = new BolaMoveThread((Bola) bola, (Raqueta) raquetaIzda,(Raqueta) raquetaDcha, new Rect(0, 0, getWidth(), getHeight()));
		//bolaThread.setRunning(true);
		//bolaThread.start();
	}

	public void surfaceDestroyed(SurfaceHolder arg0) {
		boolean retry = true;
		renderer.setRunning(false);
		//bolaThread.setRunning(false);
		while (retry) {
			try {
				renderer.join();
				//bolaThread.join();
				retry = false;
			} catch (InterruptedException e) {
			}
		}
	}

	@Override
	public void onDraw(Canvas canvas) {
		Paint paint = new Paint();

		canvas.drawColor(Color.WHITE);
		
		Bitmap bmp=BitmapFactory.decodeResource(getResources(), R.drawable.cuadricula);
		DisplayMetrics metrics = new DisplayMetrics();
   
		int screenHeight = metrics.heightPixels;
        int screenWidth = metrics.widthPixels;
        screenWidth = getWidth();        
        screenHeight = getHeight();
		
		bmp = Bitmap.createScaledBitmap(bmp,screenWidth, screenHeight, true);
        canvas.drawBitmap(bmp, 0,0, null);
        
        //canvas.drawBitmap(bitmap, left, top, paint);
        paint.setColor(Color.RED);
		canvas.drawRect(playerIzda.getRectElemento(), paint);
		paint.setColor(Color.YELLOW);
		canvas.drawRect(playerDcha.getRectElemento(), paint);

		//canvas.drawRect(bola.getRectElemento(), paint);
	}

	@Override
	public boolean onTouchEvent(MotionEvent event) {
		int x = (int) event.getX();
		int y = (int) event.getY();

		switch (event.getAction()) {
		case MotionEvent.ACTION_DOWN:
			// hemos pulsado
			if (playerIzda.getRectElemento().contains(x, y)) {
				elementoActivo = playerIzda;
				origenY = y;
				origenX = x;
				break;
			}
			if (playerDcha.getRectElemento().contains(x, y)) {
				elementoActivo = playerDcha;
				origenY = y;
				origenX = x;
				break;
			}
			break;
		case MotionEvent.ACTION_MOVE:
			// hemos arrastrado
			if (elementoActivo != null) {
				Guerrero r = (Guerrero) elementoActivo;
				if (r.puedoMover(x - origenX, y - origenY, new Rect(0, 0, getWidth(),
						getHeight())))
					r.move(x - origenX, y - origenY);
			}
			origenY = y;
			origenX = x;
			break;
		case MotionEvent.ACTION_UP:
			// hemos levantado
			elementoActivo = null;
			break;
		}

		return true;
	}
}