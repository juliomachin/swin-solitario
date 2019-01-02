package controller;
import java.util.ArrayList;
import java.util.Scanner;
import java.util.Stack;

public class Automatico {

	private ArrayList<Baraja> barajas = new ArrayList<Baraja>();
	private ArrayList< Stack<Carta> > columnas = new ArrayList< Stack<Carta> >();

	private void add(Baraja baraja) {
		this.barajas.add(baraja);
	}

	private void mover() {
		for (int i = 0; i < columnas.size(); i++) {
			try {
				if(columnas.get(i).peek().equals(columnas.get(i-3).peek())) {
					if(!columnas.get(i).isEmpty())
						columnas.get(i-3).push(columnas.get(i).pop());
					
					if(columnas.get(i).isEmpty())
						columnas.remove(columnas.get(i));
					
					return;
				}
			}catch(Exception e) {}
			
			try {
				if(columnas.get(i).peek().equals(columnas.get(i-1).peek())) {
					if(!columnas.get(i).isEmpty())
						columnas.get(i-1).push(columnas.get(i).pop());
					
					if(columnas.get(i).isEmpty())
						columnas.remove(columnas.get(i));
					
					return;
				}
			}catch(Exception e) {}
		}
	}

	private boolean hasMove() {
		for (int i = 0; i < columnas.size(); i++) {
			try {
				if(columnas.get(i).peek().equals(columnas.get(i-3).peek()))
					return true;
			}catch(Exception e) {}
			
			try {
				if(columnas.get(i).peek().equals(columnas.get(i-1).peek()))
					return true;
			}catch(Exception e) {}
		}
		return false;
	}
	
	private void startGame() {
		for (Baraja bar : barajas) {
			columnas.clear();
			for (int i = 0; i < 52; i++) {
				Stack<Carta> columna = new Stack<Carta>();
				columna.push(bar.pedirCarta());
				columnas.add(columna);
			}
			
			impMesaActual();
			while(hasMove()) {
				mover();
			}
			impMesaActual();
			
			String imp = new String();
			for (Stack<Carta> stack : columnas) {
				 imp = imp + stack.size() + " ";
			}
			imp = imp.substring(0, imp.length()-1);
			if(columnas.size() == 1)
				System.out.println("Ha quedado " + columnas.size()+" " + "pila: " + imp);
			else
				System.out.println("Han quedado " + columnas.size()+ " "+  "pilas: " + imp);
		}
	}
	
	private void impMesaActual() {
		//System.out.println(columnas.size());
		for (Stack<Carta> columna : columnas) {
			//System.out.print(columna.peek());
		}//System.out.println();
	}
	
	@Override
	public String toString() {
		String salida = "Mesa [\n";
		for (Baraja baraja : barajas) {
			salida += baraja.toString();
		}
		salida += "]\n";
		return salida;
	}
	
	public static void main(String[] args) {
		Automatico mesa = new Automatico();
		
		ArrayList<String> palo = new ArrayList<String>();
		palo.add("C");
		palo.add("D");
		palo.add("H");
		palo.add("S");
		ArrayList<String> numero = new ArrayList<String>();
		numero.add("A");
		numero.add("2");
		numero.add("3");
		numero.add("4");
		numero.add("5");
		numero.add("6");
		numero.add("7");
		numero.add("8");
		numero.add("9");
		numero.add("T");
		numero.add("J");
		numero.add("Q");
		numero.add("K");

		Scanner teclado = new Scanner(System.in);
		Baraja baraja = null;
		boolean bien = false;
		while(teclado.hasNext()) {
			String linea = teclado.nextLine();
			if(linea.split("\\s+")[0].charAt(0) == '#') {
				if(!bien) {
					System.out.println("Han quedado " +  " 0 " + "pilas: ");
					break;
				}
					
				else {
					System.out.println("Entrada incorrecta.");
					System.exit(0);
				}
			}
			
			if(!bien)
				baraja = new Baraja(true);
			
			String[] lineaCort = linea.split("\\s+");
			if(lineaCort.length != 26) {
				System.out.println("Entrada incorrecta.");
				System.exit(0);
			}
			
			
			
			for (int i = 0; i < lineaCort.length; i++) {
				if(lineaCort[i].charAt(0) == '#')
					
				if(lineaCort[i].length() != 2) {
					System.out.println("Entrada incorrecta.");
					System.exit(0);
				}else {
					if(!numero.contains(lineaCort[i].substring(0,1)) || !palo.contains(lineaCort[i].substring(1))) {
						System.out.println("Entrada incorrecta.");
						System.exit(0);
					}else {
						baraja.addCarta(new Carta(lineaCort[i].substring(0,1),lineaCort[i].substring(1)));
					}
				}
			}
			
			if(bien)
				mesa.add(baraja);
			
			bien = !bien;
		}
		teclado.close();
		mesa.startGame();
	}
}

