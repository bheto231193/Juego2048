package grafico;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JFrame;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;
import javax.swing.JPanel;

import controladora.Controlador;

public class ventana extends JFrame {

	private static final long serialVersionUID = 8456560429229699542L;

	private teclado panelTeclado;
	private puntajesPanel panelpuntaje;
	
	private Controlador ctrl;
	
	public ventana() {
		super("2048");
		super.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		super.setSize(500, 600);
		super.setLayout(new BorderLayout());
		super.setJMenuBar(this.menuBar());
		super.setLocationRelativeTo(null);
		
		this.initGame();
		super.setVisible(true);
	}
	
	private void initGame() {
		this.ctrl = new Controlador();
		
		JPanel pnlWest = new JPanel();
		pnlWest.setPreferredSize(new Dimension(10, 10));
		pnlWest.setBackground(new Color(0xFAF4DA));
		super.add(pnlWest, BorderLayout.WEST);
		
		JPanel pnlEast = new JPanel();
		pnlEast.setPreferredSize(new Dimension(10, 10));
		pnlEast.setBackground(new Color(0xFAF4DA));
		super.add(pnlEast, BorderLayout.EAST);
		
		JPanel pnlSouth = new JPanel();
		pnlSouth.setPreferredSize(new Dimension(10, 10));
		pnlSouth.setBackground(new Color(0xFAF4DA));
		super.add(pnlSouth, BorderLayout.SOUTH);
		
		this.panelTeclado = new teclado();
		this.panelTeclado.setController(this.ctrl);
		super.add(this.panelTeclado, BorderLayout.CENTER);
		
		this.panelpuntaje = new puntajesPanel(this.ctrl);
		this.panelpuntaje.setBackground(new Color(0xdFAF4DA));
		super.add(this.panelpuntaje, BorderLayout.NORTH);
		
		this.panelTeclado.setScorePanel(this.panelpuntaje);
		
	}
	
	private JMenuBar menuBar() {
		JMenuBar bar = new JMenuBar();
		
		JMenu mnGame = new JMenu("juego");
		JMenu mnHelp = new JMenu("ayuda");
		
		JMenuItem mnNewGame = new JMenuItem("nuevo juego");
		JMenuItem mnExit = new JMenuItem("salir");
		JMenuItem mnAbout = new JMenuItem("acerca de");
		
		mnNewGame.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent arg0) {
				ctrl = new Controlador();
				panelTeclado.initComponents();
				panelTeclado.removeKeyListener(panelTeclado.getKeyListeners()[0]);
				panelTeclado.setController(ctrl);
				panelpuntaje.setController(ctrl);
				panelpuntaje.updateScores();
			}
		});
		
		mnExit.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent arg0) {
				System.exit(0);
			}
		});
		
		mnAbout.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent arg0) {
				JOptionPane.showMessageDialog(null, "proyecto estructuras");
			}
		});
		mnGame.add(mnNewGame);
		mnGame.addSeparator();
		mnGame.add(mnExit);
		mnHelp.add(mnAbout);
		
		bar.add(mnGame);
		bar.add(mnHelp);
		
		return bar;
	}
	
}