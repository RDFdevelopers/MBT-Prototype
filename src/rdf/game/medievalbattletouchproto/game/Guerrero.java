package rdf.game.medievalbattletouchproto.game;

public class Guerrero extends Unidad implements IUnidad {

	public Guerrero(Coordenada origen, int ancho, int alto, String nombre) {
		super(origen, ancho, alto, nombre);
	}
	
	@Override
	public void move(int x, int y) {
		origen.setY(origen.getY() + y);
		origen.setX(origen.getX() + x);
	}

	@Override
	public void animacion(int x, int y) {
		int dx = origen.getY()-y;
		int dy = origen.getY()-x;
	}
}
