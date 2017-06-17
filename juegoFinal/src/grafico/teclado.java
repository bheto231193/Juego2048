package grafico;

import java.awt.Color;
import java.awt.GridLayout;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.io.IOException;
import java.util.Random;

import javax.swing.JOptionPane;
import javax.swing.JPanel;

import bases.lista;
import bases.nodo;
import controladora.Controlador;
import interfaces.movimineto;

public class teclado extends JPanel implements movimineto {

	private static final long serialVersionUID = -3256290564832372809L;
	private static final int ROWS = 4;
	private static final int COLUMNS = 4;

	private ficha[][] fichas;
	private Controlador ctrl;
	private puntajesPanel pnlPuntaje;
	
	public teclado() {
		this.initComponents();
	}

	public void initComponents() {
		super.removeAll();
		super.addKeyListener(new KeyAdapter() {
			@Override
			public void keyPressed(KeyEvent e) {
				if(e.getKeyCode() == KeyEvent.VK_RIGHT) {
					derecha();
				}
				if(e.getKeyCode() == KeyEvent.VK_LEFT) {
					izquierda();
				}
				if(e.getKeyCode() == KeyEvent.VK_UP) {
					arriba();
				}
				if(e.getKeyCode() == KeyEvent.VK_DOWN) {
					abajo();
				}
				pnlPuntaje.setLabelScore(ctrl.getScore());
				
				if(isGameLost()) {
					gameLost();
				}
			}
		});
		super.setLayout(new GridLayout(ROWS, COLUMNS, 8, 8));
		super.setBackground(new Color(0xFAF4DA));
		super.setFocusable(true);

		
		this.fichas = new ficha[ROWS][COLUMNS];
		for(int i = 0; i < ROWS; i++) {
			for(int j = 0; j < COLUMNS; j++) {
				this.fichas[i][j] = new ficha(0);
				super.add(this.fichas[i][j]);
			}
		}
		this.generateRandomTile();
		this.generateRandomTile();
	}

	private void generateRandomTile() {
		Random rand = new Random();
		int row = 0;
		int column = 0;
		do {
			row = rand.nextInt(ROWS);
			column = rand.nextInt(COLUMNS);
		}while(!this.fichas[row][column].isEmpty());

		this.fichas[row][column].setValue(2);
	}

	private lista<ficha> getRowAt(Integer index) {
		lista<ficha> tiles = new lista<ficha>();
		for(int i = 0; i < ROWS; i++) {
			for(int j = 0; j < COLUMNS; j++) {
				if(i == index) {
					tiles.add(this.fichas[i][j]);
				}	
			}
		}
		return tiles;
	}

	private lista<ficha> getColumnAt(Integer index) {
		lista<ficha> tiles = new lista<ficha>();
		for(int i = 0; i < ROWS; i++) {
			for(int j = 0; j < COLUMNS; j++) {
				if(j == index) {
					tiles.add(this.fichas[i][j]);
				}
			}
		}
		return tiles;
	}

	//direction ? right : left
	private void sumTiles(lista<ficha> tiles, boolean direction) {
		if(!direction) {
			tiles.reverse();
		}
		this.displaceTiles(tiles);
		nodo <ficha> aux = tiles.getLast();
		nodo<ficha> prev = aux.getPrevious();

		while(aux.getPrevious() != null) {
			if(aux.getData().compareTo(prev.getData()) == 0) {
				aux.getData().setValue(aux.getData().getValue() * 2);
				prev.getData().setValue(0);
				this.ctrl.setScore(this.ctrl.getScore() + aux.getData().getValue());
				this.displaceTiles(tiles);
			}
			aux = aux.getPrevious();
			prev = aux.getPrevious();
		}
	}

	private void displaceTiles(lista<ficha> tiles) {
		nodo<ficha> aux = null;
		nodo<ficha> prev = null;

		for(int i = 0; i < 2; i++) {
			aux = tiles.getLast();
			prev = aux.getPrevious();
			while(aux.getPrevious() != null) {
				if(aux.getData().isEmpty()) {
					this.exchange(aux.getData(), prev.getData());
				}
				prev = prev.getPrevious();
				aux = aux.getPrevious();
			}
		}
	}

	private void exchange(ficha a, ficha b) {
		Integer value = a.getValue();
		a.setValue(b.getValue());
		b.setValue(value);
	}

	private boolean isFull() {
		Integer tiles = 0;
		for(int i = 0; i < ROWS; i++) {
			for(int j = 0; j < COLUMNS; j++) {
				if(!this.fichas[i][j].isEmpty()) {
					tiles++;
				}
			}
		}
		return tiles == ROWS * COLUMNS;
	}

	//direction ? right : left
	private boolean canGenerateTile(lista<ficha> tiles, boolean direction) {
		boolean move = false;
		if(!direction) {
			tiles.reverse();
		}
		nodo<ficha> aux = tiles.getFirst();
		while(aux != null && aux.getData().isEmpty()) {
			aux = aux.getNext();
		}
		if(aux != null && aux.getNext() != null) {
			if(aux.getNext().getData().isEmpty() || aux.getData().compareTo(aux.getNext().getData()) == 0) {
				move = true;
			}else {
				while(aux != null) {
					if(aux.getData().isEmpty()) {
						move = true;
					}
					aux = aux.getNext();
				}
			}
		}
		if(!direction) {
			tiles.reverse();
		}
		return move;
	}

	private boolean isGameLost() {
		boolean gameLost = false;
		if(this.isFull() && !this.canMove()) {
			gameLost = true;
		}
		return gameLost;
	}
	
	private boolean canMove() {
		boolean move = false;
		for(int i = 0; i < ROWS; i++) {
			for(int j = 0; j < COLUMNS; j++) {
				if((i < ROWS - 1 && this.fichas[i][j].compareTo(this.fichas[i + 1][j]) == 0) 
						|| (j < COLUMNS - 1 && this.fichas[i][j].compareTo(this.fichas[i][j + 1]) == 0)) {
					move = true;
				}
			}
		}
		return move;
	}
	
	public void gameLost() {
		if(this.ctrl.getScore() > this.ctrl.getHighScore()) {
			try {
				JOptionPane.showMessageDialog(null, "fin del juego puntaje maximo superado: " + this.ctrl.getScore() + " pts.");
				this.ctrl.saveHighscore(this.ctrl.getScore());
			} catch (IOException e) {
				e.printStackTrace();
			}
		}else {
			JOptionPane.showMessageDialog(null, "fin del juego tu puntaje es: " + this.ctrl.getScore() + " pts.");
		}
		
	}
	
	public void setController(Controlador ctrl) {
		this.ctrl = ctrl;
	}

	public void setScorePanel(puntajesPanel pnlScore) {
		this.pnlPuntaje = pnlScore;
	}
	
	@Override
	public void derecha() {
		Integer moves = 0;
		for(int i = 0; i < ROWS; i++) {
			lista<ficha> row = this.getRowAt(i);
			if(this.canGenerateTile(row, true)) {
				moves++;
			}
			this.sumTiles(row, true);
		}
		if(!isFull() && moves > 0) {
			generateRandomTile();	
		}
	}

	@Override
	public void izquierda() {
		Integer moves = 0;
		for(int i = 0; i < ROWS; i++) {
			lista<ficha> row = this.getRowAt(i);
			if(this.canGenerateTile(row, false)) {
				moves++;
			}
			this.sumTiles(row, false);
		}
		if(!isFull() && moves > 0) {
			generateRandomTile();	
		}
	}

	@Override
	public void arriba() {
		Integer moves = 0;
		for(int i = 0; i < COLUMNS; i++) {	
			lista<ficha> column = this.getColumnAt(i);
			if(this.canGenerateTile(column, false)) {
				moves++;
			}
			this.sumTiles(column, false);
		}
		if(!isFull() && moves > 0) {
			generateRandomTile();
		}
	}

	@Override
	public void abajo() {
		Integer moves = 0;
		for(int i = 0; i < COLUMNS; i++) {
			lista<ficha> column = this.getColumnAt(i);
			if(this.canGenerateTile(column, true)) {
				moves++;
			}
			this.sumTiles(column, true);
		}
		if(!isFull() && moves > 0) {
			generateRandomTile();	
		}
	}

}