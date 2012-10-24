package rdf.game.medievalbattletouchproto.game;

import android.graphics.Rect;

public class BolaMoveThread extends Thread {

	private Bola bola;
	private Guerrero raquetaIzda;
	private Guerrero raquetaDcha;
	private Rect screen;
	
	private boolean run;
	private int speed;
	
	public BolaMoveThread(Bola bola, Guerrero izda, Guerrero dcha, Rect screen) {
		this.bola = bola;
		this.raquetaIzda = izda;
		this.raquetaDcha = dcha;
		this.screen = screen;
		this.run = false;
		this.speed = 1;
	}
	
	public void setRunning(boolean run) {
		this.run = run;
	}
	
	@Override
	public void run() {
		while(run) {
			try {
				Thread.sleep(10);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
			if(!bola.puedoMover(speed, speed, screen, raquetaIzda.getRectElemento(), raquetaDcha.getRectElemento()))
				bola.rebota(speed, speed, screen, raquetaIzda.getRectElemento(), raquetaDcha.getRectElemento());
			bola.move(speed, speed);
		}
	}
}
