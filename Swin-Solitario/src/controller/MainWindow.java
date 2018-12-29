package controller;

import java.awt.Color;
import java.awt.EventQueue;
import java.awt.Font;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.FileWriter;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.SpringLayout;

public class MainWindow implements ActionListener{
	public  JFrame ventana;

	public static void main(String[] args) {

		try {
			javax.swing.UIManager.setLookAndFeel( "javax.swing.plaf.nimbus.NimbusLookAndFeel" );
		} catch (Exception e) {

		}

		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					MainWindow n = new MainWindow();
					n.ventana.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});

	}

	@Override
	public void actionPerformed(ActionEvent e) {
		// TODO Auto-generated method stub

	}

	public MainWindow() {
		ventana = new JFrame("Solitario");
		ventana.setFont(new Font("Goudy Stout", Font.BOLD, 12));
		ventana.getContentPane().setBackground(new Color(255, 255, 255));
		SpringLayout springLayout = new SpringLayout();
		ventana.getContentPane().setLayout(springLayout);

		Image icon = new ImageIcon(getClass().getResource("/imagen/descarga.jpeg")).getImage();
		ventana.setIconImage(icon);


		JMenu menu_archivo , menu_editar, menu_historial;
		JButton menu_ayuda;

		//Item Archivo
		JMenu item_nuevo = new JMenu("Nuevo... ");
		JMenuItem solitario = new JMenuItem("Solitario ");
		JMenuItem solitario_saltos = new JMenuItem("Solitario Saltos");
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
		JMenuItem item_estadistica = new JMenuItem("Deshacer");
		JMenuItem item_ficheroEstadistica = new JMenuItem("Rehacer");


		JMenuBar subMenu = new JMenuBar();
		subMenu.setSize(50,50);
		menu_archivo = new JMenu("Archivo");
		menu_editar = new JMenu("Editar");
		menu_historial = new JMenu("Historial");
		menu_ayuda = new JButton("Ayuda");
		menu_ayuda.setBorder(null);
		menu_ayuda.setFocusable(true);
		menu_ayuda.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				ayuda();
			}
		});
		ventana.setJMenuBar(subMenu);
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


		//		JMenuItem item_ayuda =  new JMenuItem("Informacion");
		//		menu_ayuda.add(item_ayuda);
		//		item_guardar.addActionListener(new ActionListener() {
		//			public void actionPerformed(ActionEvent e) {
		//				ayuda();
		//			}
		//		});



		JPanel panel = new JPanel();
		springLayout.putConstraint(SpringLayout.NORTH, panel, 10, SpringLayout.NORTH, ventana.getContentPane());
		springLayout.putConstraint(SpringLayout.WEST, panel, 10, SpringLayout.WEST, ventana.getContentPane());
		springLayout.putConstraint(SpringLayout.SOUTH, panel, -10, SpringLayout.SOUTH, ventana.getContentPane());
		springLayout.putConstraint(SpringLayout.EAST, panel, -10, SpringLayout.EAST, ventana.getContentPane());
		panel.setBackground(Color.green);
		ventana.getContentPane().add(panel);
		ventana.setSize(798,524);
		ventana.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

	}

	public static void guardar(){

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



	public static void cargar() {

		//To-Do

	}

	public static void ayuda() {

		JOptionPane.showMessageDialog(null, "Resuelve el progama ", "Ayuda", 0);
	}

}
