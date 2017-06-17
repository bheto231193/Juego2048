package controladora;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;

public class Controlador {
	private Integer puntaje;
	private Integer puntajealto;
	
	public Controlador() {
		this.puntaje = 0;
		try {
			this.puntajealto = this.recoverHighscore();
		} catch (NumberFormatException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	public Integer getHighScore() {
		return puntajealto;
	}

	public void setHighScore(Integer highScore) {
		this.puntajealto = highScore;
	}

	public Integer getScore() {
		return puntaje;
	}

	public void setScore(Integer score) {
		this.puntaje = score;
	}
	
	public void saveHighscore(Integer highScore) throws IOException {
		File file = new File("score.txt");
		FileWriter fw = new FileWriter(file);
		BufferedWriter bw = new BufferedWriter(fw);
		bw.write(highScore.toString());
		bw.close();
	}
	
	public Integer recoverHighscore() throws NumberFormatException, IOException {
		File file = new File("score.txt");
		Integer highScore = 0;
		if(file.exists()) {
			FileReader fr = new FileReader(file);
			BufferedReader br = new BufferedReader(fr);
			highScore = Integer.parseInt(br.readLine());
			br.close();	
		} 
		return highScore;
	}
	
	public static void main(String[] args) {
		Controlador c = new Controlador();
		try {
			c.saveHighscore(90000);
		} catch (IOException e) {
			e.printStackTrace();
		}
		
		try {
			System.out.println(c.recoverHighscore());
		} catch (NumberFormatException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
}