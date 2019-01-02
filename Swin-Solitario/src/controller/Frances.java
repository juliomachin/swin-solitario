package controller;


import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.EventQueue;

import java.awt.Font;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.Point;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
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
import javax.swing.SpringLayout;
import javax.swing.filechooser.FileNameExtensionFilter;

import com.sun.prism.paint.Color;

@SuppressWarnings("serial")
public class Frances extends JFrame{

	private Baraja baraja = new Baraja(false);
	
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
		setTitle("Solitario Frances");
		setFont(new Font("Goudy Stout", Font.BOLD, 12));
		setResizable(false);
		
		Image scaled = new ImageIcon(getClass().getResource("/imagen/fondo.jpg")).getImage().getScaledInstance(1024, 768, Image.SCALE_SMOOTH);
		
		JPanel fondo = new JPanel() {
			private static final long serialVersionUID = 1L;

			@Override protected void paintComponent(Graphics g)
			{
				super.paintComponent(g);
				g.drawImage(scaled, 0, 0, this);
			}
		};
		setContentPane(fondo);

		SpringLayout springLayout = new SpringLayout();
		getContentPane().setLayout(new BorderLayout());
		
		
		
		JLayeredPane pila4 = new JLayeredPane();
		pila4.setSize(new Dimension(500, 400));
		
		Point punto = new Point(15,30);
		for (int i = 0; i < 3; i++) {
			Carta pedida = baraja.pedirCarta();
			pedida.setIcon(new ImageIcon(getClass().getResource("/imagen/reverse.png")));
			pedida.setBounds(punto.x, punto.y, pedida.getWidth(), pedida.getHeight());
			
			punto.x += 3;
			punto.y += 3;
			
			pila4.add(pedida, 0);
		}
		
		getContentPane().add(pila4, BorderLayout.CENTER);

		Image icon = new ImageIcon(getClass().getResource("/imagen/icon.jpeg")).getImage();
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

				//to-Do

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
					if(ancho == -1){ // PRIMERA LINEA DEL DOCUMENTO
						String[] cortes = linea.split("\\s+");
						ancho = cortes.length;
						int[] vector = new int[cortes.length];
						for (int i = 0; i < cortes.length; i++) {
							vector[i] = Integer.parseInt(cortes[i]);

							if(vector[i] != 0 && vector[i] != 1){
								throw new Exception();
							}
						}

						lineas.add(vector);
					} else { // RESTO DE LINEAS DE LA MATRIZ
						String[] cortes = linea.split("\\s+");
						if(cortes.length != ancho)
							throw new Exception();

						int[] vector = new int[cortes.length];
						for (int i = 0; i < cortes.length; i++) {
							vector[i] = Integer.parseInt(cortes[i]);

							if(vector[i] != 0 && vector[i] != 1){
								throw new Exception();
							}
						}

						lineas.add(vector);
					}
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

}
