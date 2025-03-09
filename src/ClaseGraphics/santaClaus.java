package ClaseGraphics;

import java.awt.EventQueue;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import javax.swing.*;
import java.awt.*;

public class santaClaus extends JPanel {
	public santaClaus() {
	}

    public void paintComponent(Graphics g) {
        super.paintComponent(g);

        // Cabeza (óvalo rojo)
        g.setColor(Color.PINK);
        g.fillOval(190, 100, 200, 200);
     // Establecemos el color del borde
        g.setColor(Color.BLACK);
        // Dibujamos el borde del óvalo
        g.drawOval(190, 100, 200, 200);
        

        // Cuerpo (rectángulo rojo)
        g.setColor(Color.RED);
        g.fillRect(215, 300, 150, 150);
        g.setColor(Color.BLACK);
        g.drawRect(215, 300, 150, 150);

        // Barba (arco blanco)
        g.setColor(Color.WHITE);
        g.fillArc(222, 233, 135, 30, 180, 180);
        g.setColor(Color.BLACK);
        g.drawArc(222, 233, 135, 30, 180, 180);
        
        // Barba Mentón (arco blanco)
        g.setColor(Color.WHITE);
        g.fillArc(223, 195, 135, 160, 180, 180);
        g.setColor(Color.BLACK);
        g.drawArc(223, 195, 135, 160, 180, 180);
        
        // Cejas
        g.setColor(Color.WHITE);
        g.fillArc(241, 150, 35, 15, 0, 180);
        g.fillArc(301, 150, 35, 15, 0, 180);
        g.setColor(Color.BLACK);
        g.drawArc(241, 150, 35, 15, 0, 180);
        g.drawArc(301, 150, 35, 15, 0, 180);
        
        
        // Ojos (óvalos negros)
        g.setColor(Color.BLACK);
        g.fillOval(250, 170, 20, 20);
        g.fillOval(310, 170, 20, 20);
        g.setColor(Color.BLACK);
        g.drawOval(250, 170, 20, 20);
        g.drawOval(310, 170, 20, 20);

        // Ojos (óvalos blancos)
        g.setColor(Color.WHITE);
        g.fillOval(255, 170, 10, 10);
        g.fillOval(315, 170, 10, 10);
        g.setColor(Color.BLACK);
        g.drawOval(255, 170, 10, 10);
        g.drawOval(315, 170, 10, 10);
        
        // Gorro (triángulo rojo)
        g.setColor(Color.RED);
        int[] xPoints = {410, 364, 216};
        int[] yPoints = {30, 120, 120};
        g.fillPolygon(xPoints, yPoints, 3);
        g.setColor(Color.BLACK);
        g.drawPolygon(xPoints, yPoints, 3);
        
        // Sombrero (rectángulo blanco encima de la cabeza)
        g.setColor(Color.WHITE);
        g.fillRect(216, 120, 148, 20);
        g.setColor(Color.BLACK);
        g.drawRect(216, 120, 148, 20);
        
        // Bola gorro (óvalo blanco)
        g.setColor(Color.WHITE);
        g.fillOval(410, 18, 45, 45);
        g.setColor(Color.BLACK);
        g.drawOval(410, 18, 45, 45);
        
        
        
        //Mensaje
        g.setColor(Color.BLACK);
        g.setFont(new Font("Arial", Font.BOLD, 16));
        g.drawString("¡Una feliz navidad les desea Santa Claus!", 135, 500);
    }

    public static void main(String[] args) {
        JFrame frame = new JFrame("Papá Noel");
        santaClaus panel = new santaClaus();
        frame.getContentPane().add(panel);
        frame.setSize(600, 700);
        frame.setVisible(true);
    }
}