package frames;

import java.awt.Canvas;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics2D;
import java.awt.image.BufferStrategy;
import javax.swing.JFrame;
import dibujables.Perro;
import dibujables.Sapo;

public class Frame extends JFrame implements Runnable{
	private static final long serialVersionUID = 1L;
	private BufferStrategy buffer;
	private Dimension frameSize;
	private Graphics2D g;
	private Canvas canvas;
	private Thread thread;
	private Perro perro;
	private Sapo sapo;
	
	public Frame() {
		setDefaultCloseOperation(EXIT_ON_CLOSE);
		frameSize = new Dimension(800, 641);
		canvas = new Canvas();
		canvas.setSize(frameSize);
		canvas.setBackground(Color.BLACK);
		canvas.addKeyListener(new eventos.Movimiento());
		setSize(frameSize);
		setLocationRelativeTo(null);
		setVisible(true);
		add(canvas);
		
		canvas.createBufferStrategy(2);
		buffer = canvas.getBufferStrategy();
		g = (Graphics2D) buffer.getDrawGraphics();
		
		perro = new Perro();
		sapo = new Sapo();
		thread = new Thread();
		
		start();
	}

	public void draw(){
		g.setColor(Color.BLACK);
		g.fillRect(0, 0, 800, 641); // limpia pantalla
		
		Color color = new Color(255,0,0,0);  // color transparente
		g.setColor(color);
		
		sapo.draw(g, perro);
		perro.draw(g);
		
		g.setColor(Color.darkGray);
		g.fillRect(0, 594, 800, 594); // piso
		
		try {
			Thread.sleep(15);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		
		buffer.show();
	}
	
	@Override
	public void run() {
		thread.start();
	}

	private void start() {
		while(true) {
			draw();
		}
	}
}