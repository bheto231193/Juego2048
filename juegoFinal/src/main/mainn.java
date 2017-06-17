package main;

import javax.swing.SwingUtilities;

import grafico.ventana;

public class mainn {

	public static void main(String ... args) {
		SwingUtilities.invokeLater(new Runnable() {
			@Override
			public void run() {
				new ventana();
			}
		});
	}

}