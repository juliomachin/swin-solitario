package controller;

import java.util.ArrayList;

public class Baraja {

	private ArrayList<Carta> baraja = new ArrayList<Carta>();

	public Baraja() {
		
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
		if(!isFull()){
			for (Carta c : baraja) {
				if(c.getPalo().equals(carta.getPalo()) && c.getNumero().equals(carta.getNumero())) {
					System.out.println("Entrada incorrecta.");
					System.exit(0);
				}
			}
			baraja.add(carta);
		}
	}
	
	public boolean isFull() {
		return baraja.size() == 52;
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