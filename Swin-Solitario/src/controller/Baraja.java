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
		//Saltos
		if(tipoBaraja) {
			String[] espanola = {"1C","2C","3C","4C","5C","6C","7C","8C","9C","XC",
					  	  "1B","2B","3B","4B","5B","6B","7B","8B","9B","XB",
					  	  "1E","2E","3E","4E","5E","6E","7E","8E","9E","XE",
					  	  "1O","2O","3O","4O","5O","6O","7O","8O","9O","XO",};
		}else { //Frances
			String[] francesa = {"1C","2C","3C","4C","5C","6C","7C","8C","9C","XC","JC","QC","KC",
						  "1P","2P","3P","4P","5P","6P","7P","8P","9P","XP","JP","QP","KP",
						  "1R","2R","3R","4R","5R","6R","7R","8R","9R","XR","JR","QR","KR",
						  "1T","2T","3T","4T","5T","6T","7T","8T","9T","XT","JT","QT","KT"};
			 
			for (int i = 0; i < francesa.length; i++) {
				//System.out.println("/imagen_francesa/" + francesa[i].charAt(0) + "_" + francesa[i].charAt(1) + ".JPG");
				ImageIcon image = new ImageIcon(getClass().getResource("/imagen_francesa/" + francesa[i].charAt(0) + "_" + francesa[i].charAt(1) + ".JPG"));
				Carta nueva = new Carta("" + francesa[i].charAt(0), "" + francesa[i].charAt(1));
				nueva.setIcon(image);
				this.addCarta(nueva);
			}
			Collections.shuffle(baraja);
			System.out.println(baraja);
		}
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

	@Override
	public String toString() {
		String salida = "Baraja [";
		for (Carta carta : baraja) {
			salida += carta.toString();
		}
		salida += "]\n";
		return salida;
	}

}