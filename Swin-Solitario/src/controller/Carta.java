package controller;

import javax.swing.JButton;

public class Carta extends JButton{
	
	private String palo;
	private String numero;
	
	public Carta(String palo, String numero) {
		this.palo = palo;
		this.numero = numero;
	}
	
	public String getPalo() {
		return palo;
	}
	
	public void setPalo(String palo) {
		this.palo = palo;
	}
	
	public String getNumero() {
		return numero;
	}
	
	public void setNumero(String numero) {
		this.numero = numero;
	}
	
	public boolean equals(Carta carta) {
		return this.palo.equals(carta.getPalo()) || this.numero.equals(carta.getNumero());
	}
	
	@Override
	public String toString() {
		return  "[" + palo +""+ numero + "]";
	}
	
}
