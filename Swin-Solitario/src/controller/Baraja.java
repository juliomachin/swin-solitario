package controller;

import java.util.ArrayList;
import java.util.Collections;

import javax.swing.ImageIcon;

public class Baraja {

	private ArrayList<Carta> baraja = new ArrayList<Carta>();
	private Frances controladorFran;
	private Saltos controladorEsp;
	/**
	 * 
	 * @param tipoBaraja false para frances, true para saltos
	 */
	public Baraja(boolean tipoBaraja) {
		if(tipoBaraja) { //Saltos
			String[] espanola = {"AC","2C","3C","4C","5C","6C","7C","SC","CC","RC",
					"AB","2B","3B","4B","5B","6B","7B","SB","CB","RB",
					"AE","2E","3E","4E","5E","6E","7E","SE","CE","RE",
					"AO","2O","3O","4O","5O","6O","7O","SO","CO","RO"};

			for (int i = 0; i < espanola.length; i++) {
				Carta nueva = new Carta("" + espanola[i].charAt(0), "" + espanola[i].charAt(1));
				ImageIcon image = new ImageIcon(getClass().getResource("/imagen_espanola/" + espanola[i].charAt(0) + "_" + espanola[i].charAt(1) + ".JPG"));
				nueva.setIcon(image);
				this.addCarta(nueva);
			}
		}else { //Frances
			String[] francesa = {"AH","2H","3H","4H","5H","6H","7H","8H","9H","TH","JH","QH","KH",
					"AC","2C","3C","4C","5C","6C","7C","8C","9C","TC","JC","QC","KC",
					"AD","2D","3D","4D","5D","6D","7D","8D","9D","TD","JD","QD","KD",
					"AS","2S","3S","4S","5S","6S","7S","8S","9S","TS","JS","QS","KS"};

			for (int i = 0; i < francesa.length; i++) {
				Carta nueva = new Carta("" + francesa[i].charAt(0), "" + francesa[i].charAt(1));
				ImageIcon image = new ImageIcon(getClass().getResource("/imagen_francesa/" + francesa[i].charAt(0) + "_" + francesa[i].charAt(1) + ".JPG"));
				nueva.setIcon(image);
				this.addCarta(nueva);
			}
		}
		Collections.shuffle(baraja);
	}

	public Carta pedirCarta() {
		if(!baraja.isEmpty()) {
			Carta carta = baraja.get(0);
			baraja.remove(carta);
			return carta;
		}
		return null;
	}

	public void addCarta(Carta carta) {
		for (Carta c : baraja) {
			if(c.getPalo().equals(carta.getPalo()) && c.getNumero().equals(carta.getNumero())) {
				System.out.println("Entrada incorrecta.");
				System.exit(0);
			}
		}
		baraja.add(carta);
	}

	public int size() {
		return baraja.size();
	}

	public void setControladorFran(Frances controladorFran) {
		for (Carta carta : baraja) {
			carta.addActionListener(controladorFran);
		}
		this.controladorFran = controladorFran;
	}

	public void setControladorEsp(Saltos controladorEsp) {
		for (Carta carta : baraja) {
			carta.addActionListener(controladorEsp);
		}
		this.controladorEsp = controladorEsp;
	}

	public Frances getControladorFran() {
		return controladorFran;
	}

	public Saltos getControladorEsp() {
		return controladorEsp;
	}

	public void clear() {
		baraja.clear();
	}

	@Override
	public String toString() {
		String salida = "";
		for (int i = baraja.size()-1; i >= 0; i--) {
			salida += baraja.get(i).toString() + " ";
		}
		return salida.trim();
	}

}