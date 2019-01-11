package controller;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.Image;
import java.awt.Point;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;

import javax.swing.BorderFactory;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JLayeredPane;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.filechooser.FileNameExtensionFilter;

@SuppressWarnings("serial")
public class Saltos extends JFrame implements ActionListener {

	private JPanel fondo = new JPanel(new GridLayout());
	private Baraja baraja = new Baraja(true);

	public Saltos() {
		baraja.setControladorEsp(this);
		setTitle("Solitario Saltos");
		setFont(new Font("Goudy Stout", Font.BOLD, 12));

		Image icon = new ImageIcon(getClass().getResource("/imagen_espanola/icono_espanola.jpg")).getImage();
		setIconImage(icon);

		JMenu menu_archivo , menu_editar, menu_historial, menu_ayuda;

		//Item Archivo
		JMenu item_nuevo = new JMenu("Nuevo... ");
		JMenuItem solitario = new JMenuItem("Solitario Frances");
		solitario.addActionListener(e -> {
			Frances n = new Frances();
			n.setVisible(true);
		});

		JMenuItem solitario_saltos = new JMenuItem("Solitario Saltos");
		solitario_saltos.addActionListener(e -> {
			Saltos n = new Saltos();
			n.setVisible(true);
		});

		JMenuItem item_cargar = new JMenuItem("Cargar... ");
		item_cargar.addActionListener(e -> {
			cargar();
		});

		JMenuItem item_guardar = new JMenuItem("Guardar...");
		item_guardar.addActionListener(e -> {
			guardar();
		});

		JMenuItem item_guardarComo = new JMenuItem("Guardar como...");
		item_guardarComo.addActionListener(e -> {
			guardar_como();
		});

		JMenuItem salir = new JMenuItem("Salir");
		salir.addActionListener(e -> {
			System.exit(1);
		});

		//Item Editar
		JMenuItem item_deshacer = new JMenuItem("Deshacer");
		JMenuItem item_rechacer = new JMenuItem("Rehacer");
		JMenuItem item_resolver = new JMenuItem("Resolver");

		//Item Historial
		JMenuItem item_estadistica = new JMenuItem("Estadisticas");
		JMenuItem item_ficheroEstadistica = new JMenuItem("Ficheros Estadisticas");

		JMenuBar subMenu = new JMenuBar();
		subMenu.setSize(50,50);
		menu_archivo = new JMenu("Archivo");
		menu_editar = new JMenu("Editar");
		menu_historial = new JMenu("Historial");
		menu_ayuda = new JMenu("Ayuda");
		menu_ayuda.setBorder(null);
		menu_ayuda.setFocusable(true);
		menu_ayuda.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				ayuda();
			}
		});
		setJMenuBar(subMenu);
		subMenu.add(menu_archivo);
		subMenu.add(menu_editar);
		subMenu.add(menu_historial);
		subMenu.add(menu_ayuda);

		menu_archivo.add(item_nuevo);
		item_nuevo.add(solitario);
		item_nuevo.add(solitario_saltos);
		menu_archivo.addSeparator();
		menu_archivo.add(item_cargar);
		menu_archivo.addSeparator();
		menu_archivo.add(item_guardar);
		menu_archivo.addSeparator();
		menu_archivo.add(item_guardarComo);
		menu_archivo.addSeparator();
		menu_archivo.add(salir);

		menu_editar.add(item_deshacer);
		menu_editar.addSeparator();
		menu_editar.add(item_rechacer);
		menu_editar.addSeparator();
		menu_editar.add(item_resolver);

		menu_historial.add(item_estadistica);
		menu_historial.addSeparator();
		menu_historial.add(item_ficheroEstadistica);

		item_estadistica.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				estadistica();
			}
		});

		item_ficheroEstadistica.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				setEstadistica();
			}
		});

		JMenuItem item_ayuda =  new JMenuItem("Informacion");
		menu_ayuda.add(item_ayuda);
		item_ayuda.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				ayuda();
			}
		});

		JScrollPane jsp = new JScrollPane(fondo);

		JPanel bp = new JPanel();
		bp.setLayout(new BorderLayout());

		JButton mazo = new JButton();
		mazo.setBounds(10, 30, 75, 98);
		mazo.addActionListener(e -> {
			if(baraja.size() > 0) {
				JLayeredPane pilaNueva = new JLayeredPane();
				pilaNueva.setPreferredSize(new Dimension(100, getHeight()));
				pilaNueva.setBorder(BorderFactory.createLineBorder(Color.BLACK, 1));
				Carta pedida = baraja.pedirCarta();
				pedida.setBounds(15,30, pedida.getWidth(), pedida.getHeight());
				pilaNueva.add(pedida, 0);
				fondo.add(pilaNueva);
				((GridLayout)fondo.getLayout()).setColumns(fondo.getComponentCount());
				repaint();
				revalidate();
			} else {
				mazo.setBackground(Color.RED);
			}
		});

		JLayeredPane jlp = new JLayeredPane();
		jlp.setPreferredSize(new Dimension(100, getHeight()));
		jlp.add(mazo, 0);

		bp.setBackground(new Color(4, 114, 77));
		fondo.setBackground(new Color(4, 114, 77));
		bp.add(jlp, BorderLayout.WEST);
		bp.add(jsp, BorderLayout.CENTER);

		setContentPane(bp);
		setSize(1024,768);
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);

		Saltos ventana = this;
		addWindowListener(new java.awt.event.WindowAdapter() {
			@Override
			public void windowClosing(java.awt.event.WindowEvent windowEvent) {
				if (JOptionPane.showConfirmDialog(ventana, "¿Quieres guardar la partida?", "Close Window?", JOptionPane.YES_NO_OPTION, JOptionPane.QUESTION_MESSAGE) == JOptionPane.YES_OPTION){
					guardar();
				}
				intento();
				ventana.dispose();
			}
		});
	}

	private File estadistica = null;

	private void estadistica() {
		int intentos = 0, ganadas = 0;
		if(estadistica == null) {
			JFileChooser fc = new JFileChooser();
			FileNameExtensionFilter filter = new FileNameExtensionFilter("txt", "txt", "text");
			fc.setFileFilter(filter);
			try {
				if(fc.showOpenDialog(this) == JFileChooser.APPROVE_OPTION){
					estadistica = fc.getSelectedFile();
					FileReader fr = new FileReader(estadistica);
					BufferedReader reader = new BufferedReader(fr);
					String linea = new String();
					int nLinea = 0;
					while( (linea=reader.readLine()) != null ) {
						if(nLinea == 1) {
							try {
								intentos = Integer.parseInt(linea);
							}catch(Exception e) {}
						} else if(nLinea == 2) {
							try {
								ganadas = Integer.parseInt(linea);
							}catch(Exception e) {}
						}
						nLinea++;
					}
					reader.close();
				}
			}catch(Exception event) {
				event.printStackTrace();
				JOptionPane.showMessageDialog(null,"Error leyendo las estadisticas.","Error",JOptionPane.ERROR_MESSAGE);
			}
			JOptionPane.showMessageDialog(this, "Solitario Saltos\nNumero de Intentos: " + intentos +"\nNumero de partidas ganadas: " + ganadas, "Estadisticas", JOptionPane.INFORMATION_MESSAGE);
		} else {
			try {
				FileReader fr = new FileReader(estadistica);
				BufferedReader reader = new BufferedReader(fr);
				String linea = new String();
				int nLinea = 0;
				while( (linea=reader.readLine()) != null ) {
					if(nLinea == 1) {
						try {
							intentos = Integer.parseInt(linea);
						}catch(Exception e) {}
					} else if(nLinea == 2) {
						try {
							ganadas = Integer.parseInt(linea);
						}catch(Exception e) {}
					}
					nLinea++;
				}
				reader.close();
			}catch(Exception event) {
				JOptionPane.showMessageDialog(null,"Error leyendo las estadisticas.","Error",JOptionPane.ERROR_MESSAGE);
			}
			JOptionPane.showMessageDialog(this, "Solitario Saltos\nNumero de Intentos: " + intentos +"\nNumero de partidas ganadas: " + ganadas, "Estadisticas", JOptionPane.INFORMATION_MESSAGE);
		}
	}

	private void setEstadistica() {
		JFileChooser fc = new JFileChooser();
		FileNameExtensionFilter filter = new FileNameExtensionFilter("txt", "txt", "text");
		fc.setFileFilter(filter);
		try {
			if(fc.showOpenDialog(this) == JFileChooser.APPROVE_OPTION){
				estadistica = fc.getSelectedFile();
			}
		}catch(Exception event) {
			JOptionPane.showMessageDialog(null,"Error leyendo las estadisticas.","Error",JOptionPane.ERROR_MESSAGE);
		}
	}

	private File partida = null;

	public void guardar(){
		if(partida == null) {
			String name = new String();
			JFileChooser fc = new JFileChooser();
			fc.setFileSelectionMode(JFileChooser.DIRECTORIES_ONLY);
			name = JOptionPane.showInputDialog("Nombre del Archivo");

			try{
				if(fc.showSaveDialog(this) == JFileChooser.APPROVE_OPTION){
					partida = new File(fc.getSelectedFile().getAbsolutePath() + "/"  + name + ".txt");
					FileWriter fw =  new FileWriter(partida);

					fw.write("Solitario saltos\n");

					//Mazo
					fw.write(baraja.toString());
					fw.write("\n");

					//Sacadas
					for (int i = 0; i < fondo.getComponentCount(); i++) {
						JLayeredPane panel = (JLayeredPane)fondo.getComponents()[i];
						for (int j = 0; j < panel.getComponentCount(); j++) {
							fw.write( ((Carta)panel.getComponents()[j]).toString() + " ");
						}
						fw.write("\n");
					}
					fw.close();
				}
			}catch(Exception e){
				JOptionPane.showMessageDialog(null,"Error guardando la partida.","Error",JOptionPane.ERROR_MESSAGE);
			}
		}else {
			try {
				FileWriter fw =  new FileWriter(partida);

				fw.write("Solitario saltos\n");

				//Mazo
				fw.write(baraja.toString());
				fw.write("\n");

				//Sacadas
				for (int i = 0; i < fondo.getComponentCount(); i++) {
					JLayeredPane panel = (JLayeredPane)fondo.getComponents()[i];
					for (int j = 0; j < panel.getComponentCount(); j++) {
						fw.write( ((Carta)panel.getComponents()[j]).toString() + " ");
					}
					fw.write("\n");
				}
				fw.close();
			}catch(Exception e){
				JOptionPane.showMessageDialog(null,"Error guardando la partida.","Error",JOptionPane.ERROR_MESSAGE);
			}
		}
	}

	public void guardar_como(){
		String name = new String();
		JFileChooser fc = new JFileChooser();
		fc.setFileSelectionMode(JFileChooser.DIRECTORIES_ONLY);
		name = JOptionPane.showInputDialog("Nombre del Archivo");

		try{
			if(fc.showSaveDialog(this) == JFileChooser.APPROVE_OPTION){
				partida = new File(fc.getSelectedFile().getAbsolutePath() + "/"  + name + ".txt");
				FileWriter fw =  new FileWriter(partida);

				fw.write("Solitario saltos\n");

				//Mazo
				fw.write(baraja.toString());
				fw.write("\n");

				//Sacadas
				for (int i = 0; i < fondo.getComponentCount(); i++) {
					JLayeredPane panel = (JLayeredPane)fondo.getComponents()[i];
					for (int j = 0; j < panel.getComponentCount(); j++) {
						fw.write( ((Carta)panel.getComponents()[j]).toString() + " ");
					}
					fw.write("\n");
				}
				fw.close();
			}
		}catch(Exception e){
			JOptionPane.showMessageDialog(null,"Error guardando la partida.","Error",JOptionPane.ERROR_MESSAGE);
		}
	}

	@SuppressWarnings("resource")
	public void cargar() {
		JFileChooser fc = new JFileChooser();
		FileNameExtensionFilter filter = new FileNameExtensionFilter("txt", "txt", "text");
		fc.setFileFilter(filter);
		try {
			if(fc.showOpenDialog(this) == JFileChooser.APPROVE_OPTION){
				partida = fc.getSelectedFile();
				FileReader fr = new FileReader(partida);
				BufferedReader reader = new BufferedReader(fr);
				String linea = new String();
				int nLinea = 0;
				while( (linea=reader.readLine()) != null ) {
					String[] cartas;
					if(nLinea == 0) {
						if(!linea.equals("Solitario saltos")) {
							JOptionPane.showMessageDialog(this, "Partida invalida", "Partida invalida", JOptionPane.ERROR_MESSAGE);
							return;
						}
						fondo.removeAll();
						fondo.setLayout(new GridLayout());
					} else if(nLinea == 1) {
						baraja.clear();
						cartas = linea.split("\\s+");
						for (int i = 0; i < cartas.length; i++) {
							Carta nueva = new Carta( ""+cartas[i].charAt(0), ""+cartas[i].charAt(1) );
							ImageIcon image = new ImageIcon(getClass().getResource("/imagen_espanola/" + cartas[i].charAt(0) + "_" + cartas[i].charAt(1) + ".JPG"));
							nueva.setIcon(image);
							nueva.addActionListener(this);
							baraja.addCarta(nueva);
						}
					} else {
						cartas = linea.split("\\s+");
						Point p = new Point(15, 30);
						JLayeredPane pilaNueva = new JLayeredPane();
						pilaNueva.setPreferredSize(new Dimension(100, getHeight()));
						pilaNueva.setBorder(BorderFactory.createLineBorder(Color.BLACK, 1));
						for (int i = cartas.length-1; i >= 0; i--) {
							Carta nueva = new Carta( ""+cartas[i].charAt(0), ""+cartas[i].charAt(1) );
							ImageIcon image = new ImageIcon(getClass().getResource("/imagen_espanola/" + cartas[i].charAt(0) + "_" + cartas[i].charAt(1) + ".JPG"));
							nueva.setIcon(image);
							nueva.setBounds(p.x, p.y, nueva.getWidth(), nueva.getHeight());
							nueva.addActionListener(this);
							pilaNueva.add(nueva, 0);
							p.y += 15;
						}
						fondo.add(pilaNueva);
						((GridLayout)fondo.getLayout()).setColumns(fondo.getComponentCount());
						repaint();
						revalidate();
					}
					nLinea++;
				}
				repaint();
				revalidate();
				reader.close();
			}
		}catch(Exception event) {
			JOptionPane.showMessageDialog(null,"No es una partida valida.","Error",JOptionPane.ERROR_MESSAGE);
		}
	}

	public void ayuda() {
		JOptionPane.showMessageDialog(null, "Deberas mover las cartas horizontalmente, hacia la izquierda, hay movimientos de distancia 1 y 3."
				+ "\n" + "Los movimientos seran con cartas con el mismo numero o del mismo palo.", "Ayuda", JOptionPane.INFORMATION_MESSAGE);
	}

	private Carta anterior = null;

	@Override
	public void actionPerformed(ActionEvent e) {
		if(e.getSource().getClass().toString().equals("class controller.Carta")) {
			Carta selected = (Carta)e.getSource();
			if(anterior == null) {
				selected.setBorder(BorderFactory.createLineBorder(Color.RED, 2));
				anterior = selected;
			} else {
				if(emparejan(anterior, selected) && anterior.getParent() != selected.getParent()) {
					JLayeredPane padre = ((JLayeredPane)anterior.getParent());

					Point punto = new Point(selected.getBounds().x, selected.getBounds().y+15);
					padre.remove(anterior);
					anterior.setBounds(punto.x, punto.y, anterior.getWidth(), anterior.getHeight());
					((JLayeredPane)selected.getParent()).add(anterior,0);

					if(padre.getComponentCount() == 0) {
						fondo.remove(padre);
						((GridLayout)fondo.getLayout()).setColumns(fondo.getComponentCount());
						repaint();
					}

					victory(selected);				
				}
				anterior.setBorder(null);
				anterior = null;
				repaint();
			}
		}
	}

	public void victory(Carta selected) {
		if( ((JLayeredPane)selected.getParent()).getComponentCount() == 40 && baraja.size() == 0 ) {
			if(estadistica != null) {
				int iSaltos = 0, vSaltos = 0, iFrances = 0, vFrances = 0;
				try {
					FileReader fr = new FileReader(estadistica);
					BufferedReader reader = new BufferedReader(fr);
					String linea = new String();
					int nLinea = 0;
					while( (linea=reader.readLine()) != null ) {
						if(nLinea == 1) {
							try {
								iSaltos = Integer.parseInt(linea);
							}catch(Exception e) {}
						} else if(nLinea == 2) {
							try {
								vSaltos = Integer.parseInt(linea);
							}catch(Exception e) {}
						}else if(nLinea == 4) {
							try {
								iFrances = Integer.parseInt(linea);
							}catch(Exception e) {}
						} else if(nLinea == 5) {
							try{
								vFrances = Integer.parseInt(linea);
							}catch(Exception e) {}
						}
						nLinea++;
					}
					reader.close();

					FileWriter fw =  new FileWriter(estadistica);

					fw.write("Solitario saltos\n");
					fw.write( (iSaltos+1) +"\n");
					fw.write( (vSaltos+1) +"\n");

					fw.write("Solitario clásico\n");
					fw.write( (iFrances) +"\n");
					fw.write( (vFrances) +"\n");

					fw.close();
				}catch(Exception e) {
					JOptionPane.showMessageDialog(null,"Error leyendo las estadisticas.","Error",JOptionPane.ERROR_MESSAGE);
				}
			}
			JOptionPane.showMessageDialog(this, "Has ganado!", "Victoria", JOptionPane.OK_OPTION);
		}	
	}

	public void intento() {
		if(estadistica != null) {
			int iSaltos = 0, vSaltos = 0, iFrances = 0, vFrances = 0;
			try {
				FileReader fr = new FileReader(estadistica);
				BufferedReader reader = new BufferedReader(fr);
				String linea = new String();
				int nLinea = 0;
				while( (linea=reader.readLine()) != null ) {
					if(nLinea == 1) {
						try {
							iSaltos = Integer.parseInt(linea);
						}catch(Exception e) {}
					} else if(nLinea == 2) {
						try {
							vSaltos = Integer.parseInt(linea);
						}catch(Exception e) {}
					}else if(nLinea == 4) {
						try {
							iFrances = Integer.parseInt(linea);
						}catch(Exception e) {}
					} else if(nLinea == 5) {
						try {
							vFrances = Integer.parseInt(linea);
						}catch(Exception e) {}
					}
					nLinea++;
				}
				reader.close();

				FileWriter fw =  new FileWriter(estadistica);

				fw.write("Solitario saltos\n");
				fw.write( (iSaltos+1) +"\n");
				fw.write( (vSaltos) +"\n");

				fw.write("Solitario clásico\n");
				fw.write( (iFrances) +"\n");
				fw.write( (vFrances) +"\n");

				fw.close();
			}catch(Exception e) {
				JOptionPane.showMessageDialog(null,"Error leyendo las estadisticas.","Error",JOptionPane.ERROR_MESSAGE);
			}
		}
	}

	private boolean emparejan(Carta carta1, Carta carta2) {
		if(carta1.getPalo().equals(carta2.getPalo()) || carta1.getNumero().equals(carta2.getNumero())) {
			JLayeredPane origen = (JLayeredPane)carta1.getParent();
			JLayeredPane destino = (JLayeredPane)carta2.getParent();

			for (int i = 0; i < origen.getComponentCount(); i++) {
				if(origen.getComponents()[i] == carta1)
					if(i!=0)
						return false;
			}

			for (int i = 0; i < destino.getComponentCount(); i++) {
				if(destino.getComponents()[i] == carta2)
					if(i!=0)
						return false;
			}

			if(origen != destino) {
				int posOrigen = 0, posDestino = 0;
				for (int i = 0; i < fondo.getComponentCount(); i++) {
					if(fondo.getComponents()[i] == origen)
						posOrigen = i;

					if(fondo.getComponents()[i] == destino)
						posDestino = i;
				}	
				if( (posOrigen - posDestino == 1) || (posOrigen - posDestino == 3) )
					return true;
			}
		}
		return false;
	}

}
