package controller;

import java.awt.Graphics;
import java.awt.Image;
import java.util.ArrayList;

import javax.swing.ImageIcon;

public class Baraja {

	private ArrayList<Carta> baraja = new ArrayList<Carta>();

	/**
	 * 
	 * @param tipoBaraja false para frances, true para saltos
	 */
	public Baraja(boolean tipoBaraja) {
		//Saltos
		if(tipoBaraja) {
			String[] e = {"","",""};
		}else { //Frances
			String[] f = {"1C"};
			/*
			,"2C","3C","4C","5C","6C","7C","8C","9C","XC","JC","QC","KC",
					"1P","","","","","","","","","","","","",
					"1R","","","","","","","","","","","","",
					"1T","","","","","","","","","","","","",};
			 */
			for (int i = 0; i < f.length; i++) {
				System.out.println("/imagen/" + f[i].charAt(0) + "_" + f[i].charAt(1) + ".JPG");
				ImageIcon image = new ImageIcon(getClass().getResource("/imagen/" + f[i].charAt(0) + "_" + f[i].charAt(1) + ".JPG"));
				Carta nueva = new Carta("" + f[i].charAt(0), "" + f[i].charAt(1));
				nueva.setIcon(image);
				this.addCarta(nueva);
			}
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