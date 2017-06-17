package grafico;

import java.awt.BorderLayout;
import java.awt.Font;
import java.io.IOException;

import javax.swing.JLabel;
import javax.swing.JPanel;

import controladora.Controlador;

public class puntajesPanel extends JPanel {

	private static final long serialVersionUID = 8556364468401385409L;
	
	private JLabel lblPuntaje;
	private JLabel lblPuntajeAlto;
	private Controlador ctrl;
	
	public puntajesPanel(Controlador ctrl) {
		super.setLayout(new BorderLayout());
		
		this.ctrl = ctrl;
		
		this.lblPuntaje = new JLabel("puntaje: 0");
		this.lblPuntaje.setFont(new Font("Century Ghotic", Font.BOLD, 20));
		super.add(this.lblPuntaje, BorderLayout.WEST);
		
		try {
			this.lblPuntajeAlto = new JLabel("puntaje maximo: " + String.valueOf(this.ctrl.recoverHighscore()));
		} catch (NumberFormatException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
		this.lblPuntajeAlto.setFont(new Font("Century Ghotic", Font.BOLD, 20));
		super.add(this.lblPuntajeAlto, BorderLayout.EAST);
	}
	
	public void setLabelScore(Integer score) {
		this.lblPuntaje.setText("Score: " + String.valueOf(score));
	}

	public void setController(Controlador ctrl) {
		this.ctrl = ctrl;
	}
	
	public void updateScores() {
		try {
			this.lblPuntaje.setText("Score:" + String.valueOf(this.ctrl.getScore()));
			this.lblPuntajeAlto.setText("Highscore: " + String.valueOf(this.ctrl.recoverHighscore()));
		} catch (NumberFormatException | IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}