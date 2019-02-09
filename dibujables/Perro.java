package dibujables;

import static utilidades.Fliper.createFlipped;
import java.awt.Graphics2D;
import java.awt.image.BufferedImage;
import static eventos.Movimiento.*;

public class Perro {
	private BufferedImage[] sprites;
	private int x;
	private int y;
	private boolean mirandoDerecha, saltando, cayendo, grunnendo, cargandoSapo;
	private int pasos;
	
	public Perro() {
		cargarSprites();
		mirandoDerecha = true;
		pasos = 0;
		y = 0;
	}
	public Perro(int x, int y) {
		this.x = x;
		this.y = y;
		pasos = 0;
		teclas[0] = true;
	}
	
	public void draw(Graphics2D g) {
		
		// cargando sapo test
//		if(teclas[17]) { 
//			cargarSapo(g);
//		}else {
			
		if(teclas[32] && teclas[0]) {
			saltando = true;
		}
		if(teclas[68]) {
			mirandoDerecha = true;
		}
		if(teclas[65]) {
			mirandoDerecha = false;
		}
		if(teclas[16]) { 
			grunnendo = true;
		} else {
			grunnendo = false;
		}
		
		if(saltando) {
			saltar(g);
		} else {
		
			if(teclas[68] && !grunnendo) {
				mirandoDerecha = true;
				pasos++;
				x = (x += 2) % 800;
				if(pasos <= 3) {
					g.drawImage(sprites[1],  x, 550 + y, 50, 50, null);
				} else {
					g.drawImage(sprites[0], x, 550 + y, 50, 50, null);
					if(pasos > 6) pasos = 0;
				}
			} 
			
			if(teclas[65] && !grunnendo) {
				if(mirandoDerecha)
					pasos = 0;
				pasos++;
				mirandoDerecha = false;
				x = (x -= 2) % 800;
				if(pasos <= 3) {
					g.drawImage(createFlipped(sprites[0]),  x, 550 + y, 50, 50, null);
				} else {
					g.drawImage(createFlipped(sprites[1]),  x, 550 + y, 50, 50, null);
					if(pasos > 6) pasos = 0;
				}
			}
			
			if(!teclas[68] & !teclas[65] & !grunnendo) {
				x = x % 800;
				if(mirandoDerecha) {
					g.drawImage(sprites[3],  x, 550, 50, 50, null);
				} else {
					g.drawImage(createFlipped(sprites[3]),  x, 550, 50, 50, null);
				}
			}
			
			if(grunnendo) {
				x = x % 800;
				if(mirandoDerecha) {
					g.drawImage(sprites[4], x, 550, 50, 50, null);
				} else {
					g.drawImage(createFlipped(sprites[4]), x, 550, 50, 50, null);
				}
			}
		}
//		} cargando sapo test
	}
	
	private void saltar(Graphics2D g) {
		if(cayendo) {
			y += 4;
			if(y >= 0) {
				saltando = false;
				cayendo = false;
				if(teclas[32])
					teclas[0] = false;
			}
		} else {
			if(y < -60) {
				cayendo = true;
			}
			y -= 5;
		}
		if(mirandoDerecha) {
			g.drawImage(sprites[2], x = (x += 4) % 800, 550 + y, 50, 50, null);
		} else {
			g.drawImage(createFlipped(sprites[2]), x = (x -= 4) % 800, 550 + y, 50, 50, null);
		}
	}
	
	private void cargarSapo(Graphics2D g) {
		if(teclas[68] && !grunnendo) {
			mirandoDerecha = true;
			pasos++;
			 x = (x += 2) % 800;
			if(pasos <= 3) {
				g.drawImage(sprites[7], x, 550 + y, 50, 50, null);
			} else {
				g.drawImage(sprites[7], x, 550 + y, 50, 50, null);
				if(pasos > 6) pasos = 0;
			}
		} 
		
		if(teclas[65] && !grunnendo) {
			if(mirandoDerecha)
				pasos = 0;
			pasos++;
			mirandoDerecha = false;
			x = (x -= 2) % 800;
			if(pasos <= 3) {
				g.drawImage(createFlipped(sprites[7]), x, 550 + y, 50, 50, null);
			} else {
				g.drawImage(createFlipped(sprites[7]), x, 550 + y, 50, 50, null);
				if(pasos > 6) pasos = 0;
			}
		}
	}
	
	public int getX() {
		return x;
	}
	
	public int getY() {
		return y;
	}
	
	public boolean mirandoDerecha() {
		return mirandoDerecha;
	}
	
	private void cargarSprites() {
		int cntImg = 8;
		sprites = new BufferedImage[cntImg];
		for(int i = 1; i <= cntImg; i++) {
			sprites[i-1] = Loader.load("C:\\Users\\Room\\eclipse-workspace\\game\\src\\dibujables\\recursos\\perro\\" + i + ".png");
		}
	}
	
	public BufferedImage[] getImagen() {
		return sprites;
	}
}