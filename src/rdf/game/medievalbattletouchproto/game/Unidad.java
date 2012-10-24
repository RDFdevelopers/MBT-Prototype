package rdf.game.medievalbattletouchproto.game;

import android.graphics.Rect;

public abstract class Unidad {

	protected Coordenada origen;
	protected int ancho;
	protected int alto;
	protected String id;
	
	public Unidad(Coordenada origen, int ancho, int alto, String id) {
		this.origen = origen;
		this.ancho = ancho;
		this.alto = alto;
		this.id = id;
	}
	
	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public int getOrigenX() {
		return origen.getX();
	}
	
	public int getOrigenY() {
		return origen.getY();
	}
	
	public void setOrigenX(int newX) {
		origen.setX(newX);
	}
	
	public void setOrigenY(int newY) {
		origen.setY(newY);
	}
	
	public int getAncho() {
		return ancho;
	}
	
	public int getAlto() {
		return alto;
	}
	
	public Rect getRectElemento() {
		return new Rect(getOrigenX(), getOrigenY(), getOrigenX()+ancho, getOrigenY()+alto);
	}
	
	public boolean puedoMover(int x, int y, Rect screen) {
		return screen.contains(origen.getX() + x, origen.getY() + y,
				origen.getX() + ancho + x, origen.getY() + alto + y);
	}
}
