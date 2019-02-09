package dibujables;

import static utilidades.Fliper.createFlipped;

import java.awt.Graphics2D;
import java.awt.image.BufferedImage;
import static eventos.Movimiento.*;

public class Sapo {
	private BufferedImage[] sprites;
	private boolean saltando;
	private int x, y;
	private int velocidad, distancia, destino;
	
	public Sapo() {
		cargarSprites();
		x = 200;
		y = 570;
		velocidad = 8;
		destino = x;
		distancia = 104;
	}
	
	public void draw(Graphics2D g, Perro perro) {
		saltar(g, perro);
	}
	
	private void saltar(Graphics2D g, Perro perro) {
		if(perro.getX() < x) {
			if(x - perro.getX() < 120 && teclas[16]  && perro.mirandoDerecha()) {
				g.drawImage(sprites[1], (x += velocidad) % 800 , y, 30, 30, null);
			} else {
				g.drawImage(createFlipped(sprites[0]), x % 800 , y, 30, 30, null);
			}
		} else {
			if(perro.getX() - x < 120 && teclas[16] && !perro.mirandoDerecha()) {
				g.drawImage(createFlipped(sprites[1]), (x -= velocidad) % 800 , y, 30, 30, null);
			}
			else {
				g.drawImage(sprites[0], x % 800 , y, 30, 30, null);
			}
		}
	}
	
	private void cargarSprites() {
		int cntImg = 2;
		sprites = new BufferedImage[cntImg];
		for(int i = 1; i <= cntImg; i++) {
			sprites[i-1] = Loader.load("C:\\Users\\Room\\eclipse-workspace\\game\\src\\dibujables\\recursos\\sapo\\" + i + ".png");
		}
	}
	
	public BufferedImage[] getImagen() {
		return sprites;
	}
}
