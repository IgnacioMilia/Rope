package eventos;

import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

public class Movimiento implements KeyListener{
	public static boolean[] teclas = new boolean[256];
	
	public Movimiento() {
		teclas[0] = true;
	}

	@Override
	public void keyTyped(KeyEvent e) {}

	@Override
	public void keyPressed(KeyEvent e) {
		teclas[e.getKeyCode()] = true;
	}

	@Override
	public void keyReleased(KeyEvent e) {
		teclas[e.getKeyCode()] = false;
		if(e.getKeyCode() == 32) { // para que no siga saltando al mantener la barra
			teclas[0] = true;
		}
	}
	
}
