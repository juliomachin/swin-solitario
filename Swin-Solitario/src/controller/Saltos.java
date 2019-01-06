package controller;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JPanel;
import javax.swing.SpringLayout;

@SuppressWarnings("serial")
public class Saltos extends JFrame implements ActionListener {
	
	public Saltos() {
		
		setTitle("Solitario Saltos");
		setFont(new Font("Goudy Stout", Font.BOLD, 12));
		JPanel fondo = new JPanel();
		fondo.setBackground(new Color(4, 114, 77));
		setContentPane(fondo);

		getContentPane().setLayout(null);

		Image icon = new ImageIcon(getClass().getResource("/imagen_española/icono_espanola.jpg")).getImage();
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

		
		getContentPane().setLayout(new BorderLayout());  
        getContentPane().setLayout(new FlowLayout());
		setSize(1024,768);
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		
	}

	public void guardar() {
		// TODO Auto-generated method stub
		
	}

	public void ayuda() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		
	}

}
