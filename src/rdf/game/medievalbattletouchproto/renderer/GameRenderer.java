package rdf.game.medievalbattletouchproto.renderer;

import android.graphics.Canvas;
import android.view.SurfaceHolder;

public class GameRenderer extends Thread {
	private SurfaceHolder sh;
	private GameView gview;
	private boolean run;

	public GameRenderer(SurfaceHolder sh, GameView gview) {
		this.sh = sh;
		this.gview = gview;
		run = false;
	}

	public void setRunning(boolean run) {
		this.run = run;
	}

	public void run() {
		Canvas canvas;
		while (run) {
			canvas = null;
			try {
				canvas = sh.lockCanvas(null);
				synchronized (sh) {
					gview.onDraw(canvas);
				}
			} finally {
				if (canvas != null)
					sh.unlockCanvasAndPost(canvas);
			}
		}
	}
}
