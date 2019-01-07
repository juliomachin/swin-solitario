package controller;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.EventQueue;
import java.awt.Font;
import java.awt.Image;
import java.awt.Point;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.util.ArrayList;

import javax.swing.BorderFactory;
import javax.swing.ImageIcon;
import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JLayeredPane;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.filechooser.FileNameExtensionFilter;

@SuppressWarnings("serial")
public class Frances extends JFrame implements ActionListener, MouseListener{

	private Baraja baraja = new Baraja(false);
	private ArrayList<JLayeredPane> superiores = new ArrayList<JLayeredPane>();
	private ArrayList<JLayeredPane> inferiores = new ArrayList<JLayeredPane>();

	public static void main(String[] args) {
		try {
			javax.swing.UIManager.setLookAndFeel( "javax.swing.plaf.nimbus.NimbusLookAndFeel" );
		} catch (Exception e) {}

		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Frances n = new Frances();
					n.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});

	}

	public Frances() {
		baraja.setControladorFran(this);
		setTitle("Solitario Frances");
		setFont(new Font("Goudy Stout", Font.BOLD, 12));
		setResizable(false);

		JPanel fondo = new JPanel();
		fondo.setBackground(new Color(4, 114, 77));
		setContentPane(fondo);

		getContentPane().setLayout(null);

		//Pila 1
		JLayeredPane pila1 = new JLayeredPane();
		pila1.setLocation(0, 257);
		pila1.setSize(new Dimension(140, 459));
		pila1.setBorder(BorderFactory.createLineBorder(Color.WHITE));

		Point punto = new Point(15,30);
		for (int i = 0; i < 1; i++) {
			Carta pedida = baraja.pedirCarta();
			pedida.setBounds(punto.x, punto.y, pedida.getWidth(), pedida.getHeight());

			punto.y += 15;

			pila1.add(pedida, 0);
		}

		getContentPane().add(pila1);

		//Pila 2	
		JLayeredPane pila2 = new JLayeredPane();
		pila2.setLocation(146, 257);
		pila2.setSize(new Dimension(140, 459));
		pila2.setBorder(BorderFactory.createLineBorder(Color.WHITE));

		punto.setLocation(15, 30);
		for (int i = 0; i < 2; i++) {
			Carta pedida = baraja.pedirCarta();
			pedida.setBounds(punto.x, punto.y, pedida.getWidth(), pedida.getHeight());

			if(i != 1) {
				pedida.setDisabledIcon(new ImageIcon(getClass().getResource("/imagen_francesa/reverse.png")));
				pedida.setEnabled(false);
			}

			punto.y += 15;

			pila2.add(pedida, 0);
		}

		getContentPane().add(pila2); 

		//Pï¿½la 3
		JLayeredPane pila3 = new JLayeredPane();
		pila3.setLocation(291, 257);
		pila3.setSize(new Dimension(140, 459));
		pila3.setBorder(BorderFactory.createLineBorder(Color.WHITE));

		punto.setLocation(15, 30);
		for (int i = 0; i < 3; i++) {
			Carta pedida = baraja.pedirCarta();
			pedida.setBounds(punto.x, punto.y, pedida.getWidth(), pedida.getHeight());

			if(i != 2) {
				pedida.setDisabledIcon(new ImageIcon(getClass().getResource("/imagen_francesa/reverse.png")));
				pedida.setEnabled(false);
			}

			punto.y += 15;

			pila3.add(pedida, 0);
		}

		getContentPane().add(pila3);

		//Pila 4
		JLayeredPane pila4 = new JLayeredPane();
		pila4.setLocation(439, 257);
		pila4.setSize(new Dimension(140, 459));
		pila4.setBorder(BorderFactory.createLineBorder(Color.WHITE));

		punto.setLocation(15, 30);
		for (int i = 0; i < 4; i++) {
			Carta pedida = baraja.pedirCarta();
			pedida.setBounds(punto.x, punto.y, pedida.getWidth(), pedida.getHeight());

			if(i != 3) {
				pedida.setDisabledIcon(new ImageIcon(getClass().getResource("/imagen_francesa/reverse.png")));
				pedida.setEnabled(false);
			}

			punto.y += 15;

			pila4.add(pedida, 0);
		}

		getContentPane().add(pila4);


		//Pila 5
		JLayeredPane pila5 = new JLayeredPane();
		pila5.setLocation(585, 257);
		pila5.setSize(new Dimension(140, 459));
		pila5.setBorder(BorderFactory.createLineBorder(Color.WHITE));

		punto.setLocation(15, 30);
		for (int i = 0; i < 5; i++) {
			Carta pedida = baraja.pedirCarta();
			pedida.setBounds(punto.x, punto.y, pedida.getWidth(), pedida.getHeight());

			if(i != 4) {
				pedida.setDisabledIcon(new ImageIcon(getClass().getResource("/imagen_francesa/reverse.png")));
				pedida.setEnabled(false);
			}

			punto.y += 15;

			pila5.add(pedida, 0);
		}

		getContentPane().add(pila5);


		//Pila 6	
		JLayeredPane pila6 = new JLayeredPane();
		pila6.setLocation(730, 257);
		pila6.setSize(new Dimension(140, 459));
		pila6.setBorder(BorderFactory.createLineBorder(Color.WHITE));

		punto.setLocation(15, 30);
		for (int i = 0; i < 6; i++) {
			Carta pedida = baraja.pedirCarta();
			pedida.setBounds(punto.x, punto.y, pedida.getWidth(), pedida.getHeight());

			if(i != 5) {
				pedida.setDisabledIcon(new ImageIcon(getClass().getResource("/imagen_francesa/reverse.png")));
				pedida.setEnabled(false);
			}

			punto.y += 15;

			pila6.add(pedida, 0);
		}

		getContentPane().add(pila6);


		//Pila 7	
		JLayeredPane pila7 = new JLayeredPane();
		pila7.setLocation(878, 257);
		pila7.setSize(new Dimension(140, 459));
		pila7.setBorder(BorderFactory.createLineBorder(Color.WHITE));

		punto.setLocation(15, 30);
		for (int i = 0; i < 7; i++) {
			Carta pedida = baraja.pedirCarta();
			pedida.setBounds(punto.x, punto.y, pedida.getWidth(), pedida.getHeight());

			if(i != 6) {
				pedida.setDisabledIcon(new ImageIcon(getClass().getResource("/imagen_francesa/reverse.png")));
				pedida.setEnabled(false);
			}

			punto.y += 15;

			pila7.add(pedida, 0);
		}

		getContentPane().add(pila7);

		inferiores.add(pila1);
		inferiores.add(pila2);
		inferiores.add(pila3);
		inferiores.add(pila4);
		inferiores.add(pila5);
		inferiores.add(pila6);
		inferiores.add(pila7);

		for (JLayeredPane pila : inferiores) {
			pila.addMouseListener(this);
		}

		JLayeredPane superior1 = new JLayeredPane();
		superior1.setSize(new Dimension(140, 459));
		superior1.setBorder(BorderFactory.createLineBorder(Color.WHITE));
		superior1.setBounds(439, 0, 140, 252);
		fondo.add(superior1);

		JLayeredPane superior2 = new JLayeredPane();
		superior2.setSize(new Dimension(140, 459));
		superior2.setBorder(BorderFactory.createLineBorder(Color.WHITE));
		superior2.setBounds(585, 0, 140, 252);
		fondo.add(superior2);

		JLayeredPane superior3 = new JLayeredPane();
		superior3.setSize(new Dimension(140, 459));
		superior3.setBorder(BorderFactory.createLineBorder(Color.WHITE));
		superior3.setBounds(730, 0, 140, 252);
		fondo.add(superior3);

		JLayeredPane superior4 = new JLayeredPane();
		superior4.setSize(new Dimension(140, 459));
		superior4.setBorder(BorderFactory.createLineBorder(Color.WHITE));
		superior4.setBounds(878, 0, 140, 252);
		fondo.add(superior4);

		superiores.add(superior1);
		superiores.add(superior2);
		superiores.add(superior3);
		superiores.add(superior4);

		for (JLayeredPane pila : superiores) {
			pila.addMouseListener(this);
		}

		JLayeredPane bar = new JLayeredPane();
		bar.setSize(new Dimension(140, 459));
		bar.setBorder(BorderFactory.createLineBorder(Color.WHITE));
		bar.setBounds(0, 0, 140, 250);
		fondo.add(bar);

		Point p = new Point(15,30);

		JLayeredPane sacadas = new JLayeredPane();
		sacadas.setSize(new Dimension(140, 459));
		sacadas.setBorder(BorderFactory.createLineBorder(Color.WHITE));
		sacadas.setBounds(146, 0, 140, 250);
		fondo.add(sacadas);

		Carta mazo = new Carta("", "");
		mazo.setBounds(15, 30, mazo.getWidth(), mazo.getHeight());
		mazo.setBackground(Color.WHITE);
		bar.add(mazo, 0);
		mazo.addActionListener(e -> {
			if(baraja.size() > 0) {
				Carta pedida = baraja.pedirCarta();
				pedida.setBounds(p.x, p.y, mazo.getWidth(), mazo.getHeight());
				sacadas.add(pedida,0);

				if(baraja.size() == 0)
					mazo.setBackground(Color.RED);
			} else {
				mazo.setBackground(Color.WHITE);

				for (int i = 0; i < sacadas.getComponentCount(); i++) {
					baraja.addCarta( (Carta)sacadas.getComponents()[i] );
				}

				sacadas.removeAll();
			}
			repaint();
		});

		Image icon = new ImageIcon(getClass().getResource("/imagen_francesa/icon.jpeg")).getImage();
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
		JMenuItem item_guardar = new JMenuItem("Guardar...");
		item_guardar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				guardar();
			}
		});
		JMenuItem item_guardarComo = new JMenuItem("Guardar como...");
		JMenuItem salir = new JMenuItem("Salir");
		salir.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				System.exit(1);
			}
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


		JMenuItem item_ayuda =  new JMenuItem("Informacion");
		menu_ayuda.add(item_ayuda);
		item_guardar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				ayuda();
			}
		});

		setSize(1024, 768);
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
	}

	public void guardar(){
		//To-Do
		String name = new String();
		JFileChooser fc = new JFileChooser();
		fc.setFileSelectionMode(JFileChooser.DIRECTORIES_ONLY);
		name = JOptionPane.showInputDialog("Nombre del Archivo");

		try{
			if(fc.showSaveDialog(null) == JFileChooser.APPROVE_OPTION){
				File aux = new File(fc.getSelectedFile().getAbsolutePath() + "/"  + name + ".txt");
				FileWriter fw =  new FileWriter(aux);

				fw.write("Solitario clásico");
				fw.write("\n");
				//Mazo
				//fw.write();
				//Sacadas
				//fw.write("\n")


				fw.close();
			}
		}catch(Exception ei){
			ei.printStackTrace();
			JOptionPane.showMessageDialog(null,"Error guardando la partida.","Error",JOptionPane.ERROR_MESSAGE);
		}
	}

	public void cargar() {

		JFileChooser fc = new JFileChooser();
		FileNameExtensionFilter filter = new FileNameExtensionFilter("txt", "txt", "text");
		fc.setFileFilter(filter);
		File archivo;


		try {
			if(fc.showOpenDialog(null) == JFileChooser.APPROVE_OPTION){
				archivo = fc.getSelectedFile();
				FileReader fr;

				fr = new FileReader(archivo.toString());

				BufferedReader reader = new BufferedReader(fr);

				int ancho = -1;
				String linea;
				ArrayList<int[]> lineas = new ArrayList<int[]>();
				while((linea=reader.readLine())!=null) {

				}

				reader.close();


			}
		}catch(Exception event) {
			JOptionPane.showMessageDialog(null,"No es una partida valida.","Error",JOptionPane.ERROR_MESSAGE);
		}


	}

	public void ayuda() {
		JOptionPane.showMessageDialog(null, "Resuelve el progama ", "Ayuda", JOptionPane.INFORMATION_MESSAGE);
	}

	private Carta anterior = null;

	@Override
	public void actionPerformed(ActionEvent e) {
		if(e.getSource().getClass().toString().equals("class controller.Carta")) {
			Carta selected = (Carta)e.getSource();
			if(anterior == null)
				anterior = selected;
			else {
				if(emparejan(anterior, selected) && anterior.getParent() != selected.getParent()) {
					//destapa la carta anterior
					JLayeredPane padre = ((JLayeredPane)anterior.getParent());
					if(padre.getComponentCount() > 1) {
						for (int i = 0; i < padre.getComponentCount(); i++) {
							try{
								if(padre.getComponents()[i] == anterior) {
									padre.getComponents()[i+1].setEnabled(true);
								}
							} catch (IndexOutOfBoundsException ex) {
								System.out.println(ex.getMessage());
							}
						}
					}
					int cantidad = 0 ;

					for (int i = 0; i <padre.getComponentCount(); i++) {
						if(padre.getComponents()[i] == anterior) {
							cantidad = (padre.getComponentCount() - (padre.getComponentCount() - i))+1;
						}
					}
					Carta [] cartas = new Carta[cantidad];
					for (int i = 0; i < cartas.length; i++) {
						cartas[i] = (Carta)padre.getComponents()[i];
					}

					Point punto = new Point(selected.getBounds().x, selected.getBounds().y+15);
					for (int i = cartas.length-1; i >= 0; i--) {
						Carta carta = cartas[i];
						padre.remove(carta);
						carta.setBounds(punto.x, punto.y, anterior.getWidth(), anterior.getHeight());
						((JLayeredPane)selected.getParent()).add(carta,0);

						punto.y += 15;
					}
					repaint();
				}

				anterior = null;
				repaint();
			}
		}
	}

	@Override
	public void mouseClicked(MouseEvent e) {
		if(inferiores.contains(((JLayeredPane)e.getSource()))) {
			if(anterior != null)
				if(((JLayeredPane)e.getSource()).getComponentCount() == 0 && anterior.getNumero().equals("K")) {
					//destapa la carta anterior
					JLayeredPane padre = ((JLayeredPane)anterior.getParent());
					if(padre.getComponentCount() > 1) {
						for (int i = 0; i < padre.getComponentCount(); i++) {
							if(padre.getComponents()[i] == anterior) {
								padre.getComponents()[i+1].setEnabled(true);
							}
						}
					}

					anterior.setBounds(15, 30, anterior.getWidth(), anterior.getHeight());
					((JLayeredPane)anterior.getParent()).remove(anterior);
					((JLayeredPane)e.getSource()).add(anterior,0);

					anterior.removeActionListener(this);
					Carta n = anterior;
					n.addActionListener(event -> {
						if(anterior != null)
							if(emparejan(n, anterior)) {
								anterior.setBounds(n.getBounds().x, n.getBounds().y+15, anterior.getWidth(), anterior.getHeight());
								((JLayeredPane)anterior.getParent()).remove(anterior);
								((JLayeredPane)n.getParent()).add(anterior,0);
							}

						anterior = null;
						repaint();
					});

					anterior = null;
					repaint();
				}
		}

		if(superiores.contains(((JLayeredPane)e.getSource()))) {
			if(anterior != null)
				if(((JLayeredPane)e.getSource()).getComponentCount() == 0 && anterior.getNumero().equals("A")) {
					//destapa la carta anterior
					JLayeredPane padre = ((JLayeredPane)anterior.getParent());
					if(padre.getComponentCount() > 1) {
						for (int i = 0; i < padre.getComponentCount(); i++) {
							if(padre.getComponents()[i] == anterior) {
								padre.getComponents()[i+1].setEnabled(true);
							}
						}
					}

					anterior.setBounds(15, 30, anterior.getWidth(), anterior.getHeight());
					((JLayeredPane)anterior.getParent()).remove(anterior);
					((JLayeredPane)e.getSource()).add(anterior,0);

					anterior.removeActionListener(this);
					Carta n = anterior;
					n.addActionListener(event -> {
						if(anterior != null)
							if(emparejanReves(anterior,n) && anterior.getParent() != n.getParent()) {
								anterior.setBounds(n.getBounds().x, n.getBounds().y+15, anterior.getWidth(), anterior.getHeight());
								((JLayeredPane)anterior.getParent()).remove(anterior);
								((JLayeredPane)n.getParent()).add(anterior,0);
							}

						anterior = null;
						repaint();
					});

					anterior = null;
					repaint();
				}
		}
	}

	@Override
	public void mouseEntered(MouseEvent arg0) {}

	@Override
	public void mouseExited(MouseEvent arg0) {}

	@Override
	public void mousePressed(MouseEvent arg0) {}

	@Override
	public void mouseReleased(MouseEvent arg0) {}

	private boolean emparejan(Carta carta1, Carta carta2) {
		int cart1, cart2;

		if (carta1.getNumero().equals("T"))
			cart1 = 10;
		else if(carta1.getNumero().equals("J"))
			cart1 = 11;
		else if(carta1.getNumero().equals("Q"))
			cart1 = 12;
		else if(carta1.getNumero().equals("K"))
			cart1 = 13;
		else if(carta1.getNumero().equals("A"))
			cart1 = 1;
		else 
			cart1 = Integer.parseInt(carta1.getNumero());		

		if (carta2.getNumero().equals("T"))
			cart2 = 10;
		else if(carta2.getNumero().equals("J"))
			cart2 = 11;
		else if(carta2.getNumero().equals("Q"))
			cart2 = 12;
		else if(carta2.getNumero().equals("K"))
			cart2 = 13;
		else if(carta2.getNumero().equals("A"))
			cart2 = 1;
		else 
			cart2 = Integer.parseInt(carta2.getNumero());

		if(cart1 < cart2 && (carta2.getColor() != carta1.getColor()))
			return true;

		return false;
	}

	private boolean emparejanReves(Carta carta1, Carta carta2) {
		int cart1, cart2;

		if (carta1.getNumero().equals("T"))
			cart1 = 10;
		else if(carta1.getNumero().equals("J"))
			cart1 = 11;
		else if(carta1.getNumero().equals("Q"))
			cart1 = 12;
		else if(carta1.getNumero().equals("K"))
			cart1 = 13;
		else if(carta1.getNumero().equals("A"))
			cart1 = 1;
		else 
			cart1 = Integer.parseInt(carta1.getNumero());		

		if (carta2.getNumero().equals("T"))
			cart2 = 10;
		else if(carta2.getNumero().equals("J"))
			cart2 = 11;
		else if(carta2.getNumero().equals("Q"))
			cart2 = 12;
		else if(carta2.getNumero().equals("K"))
			cart2 = 13;
		else if(carta2.getNumero().equals("A"))
			cart2 = 1;
		else 
			cart2 = Integer.parseInt(carta2.getNumero());

		if(cart2 == cart1 - 1 && carta1.getPalo().equals(carta2.getPalo()))
			return true;

		return false;
	}
}
