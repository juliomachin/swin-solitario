package controller;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Image;

import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.SpringLayout;

public class Saltos extends JFrame{
	
	public Saltos() {
		
		setTitle("Solitario");
		setFont(new Font("Goudy Stout", Font.BOLD, 12));
		getContentPane().setBackground(new Color(255, 255, 255));
		SpringLayout springLayout = new SpringLayout();
		getContentPane().setLayout(springLayout);

		Image icon = new ImageIcon(getClass().getResource("/imagen/icon.jpeg")).getImage();
		setIconImage(icon);
		
		
		
		getContentPane().setLayout(new BorderLayout());  
        getContentPane().setLayout(new FlowLayout());
		setSize(798,524);
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		
	}

}
