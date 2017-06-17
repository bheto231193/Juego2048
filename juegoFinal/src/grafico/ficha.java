package grafico;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Font;

import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.SwingConstants;
import javax.swing.border.LineBorder;

public class ficha extends JPanel implements Comparable<ficha> {

	private static final long serialVersionUID = -1756048321861954387L;

	private Integer valor;
	private JLabel lblValor;
	private Font fuente;
	
	public ficha(Integer value) { 
		super.setLayout(new BorderLayout());
		super.setBorder(new LineBorder(Color.BLACK));
		
		this.fuente = new Font("Century Ghotic", Font.BOLD, 30);
		this.lblValor = new JLabel(String.valueOf(value));
		
		this.update(value);
	}

	public Integer getValue() {
		return valor;
	}

	public void setValue(Integer value) {
		this.update(value);
	}

	public boolean isEmpty() {
		return this.valor == 0;
	}
	
	private void update(Integer value) {
		this.valor = value;
		
		this.lblValor.setText(String.valueOf(value));
		this.lblValor.setHorizontalAlignment(SwingConstants.CENTER);
		this.lblValor.setFont(this.fuente);
		this.lblValor.setVisible(value >= 2);
		
		super.add(this.lblValor, BorderLayout.CENTER);
		this.assignColor(value);
	}
	
	private void assignColor(Integer value) {
		switch(value) {
		case 0:
			this.setBackground(new Color(0xF8F5E6));
			break;
		case 2: 
			this.setBackground(Color.WHITE);
			break;
		case 4:
			this.setBackground(new Color(0xede0c8));
			break;
		case 8: 
			this.setBackground(new Color(0xf2b179));
			break;
		case 16:
			this.setBackground(new Color(0xf59563));
			break;
		case 32:
			this.setBackground(new Color(0xf67c5f));
			break;
		case 64:
			this.setBackground(new Color(0xf65e3b));
			break;
		case 128:
			this.setBackground(new Color(0xedcf72));
			break;
		case 256:
			this.setBackground(new Color(0xedcc61));
			break;
		case 512:
			this.setBackground(new Color(0xedc850));
			break;
		case 1024:
			this.setBackground(new Color(0xedc53f));
			break;
		case 2048:
			this.setBackground(new Color(0xedc22e));
			break;
		default:
			this.setBackground(Color.GREEN);	
			break;
		}
	}

	@Override
	public int compareTo(ficha arg0) {
		return this.valor - arg0.valor;
	}

}