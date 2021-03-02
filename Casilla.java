/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ajedrez;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.RenderingHints;
import java.awt.image.*;
import javax.swing.ImageIcon;
import javax.swing.JLabel;

/**
 *
 * @author Jose
 */
public class Casilla {
    
    // para el control de los movimientos
    private static Move moveEnCurso = new Move();
    
    // atributos
    private boolean color;                  // 0 = negro, 1 = blanco
    private Pieza piezaQueOcupa;
    private int posX, posY;                 // posición en el tablero
    private int ancho;                      // ancho en pixeles
    private JLabel representacionVisual;    // casilla física/visual
    
    public Casilla(int x, int y, boolean color, int ancho)
    {
        this.posX = x;
        this.posY = y;
        this.ancho = ancho;
        this.color = color;
        
        representacionVisual = new JLabel();
        representacionVisual.setOpaque(true);
        representacionVisual.setPreferredSize(new Dimension(ancho, ancho));
        representacionVisual.setLocation(x*ancho, y*ancho);  
        representacionVisual.setBackground(color ? Color.WHITE : Color.GRAY);
        
        // intercepción del evento de click sobre la casilla
        // permite marcar casilla de origen y de destino de un movimiento de pieza
        representacionVisual.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jLabelMouseClicked(evt);
            }
        });
    }
    
    private void jLabelMouseClicked(java.awt.event.MouseEvent evt) {                                     
        moveEnCurso.RealizarMovimiento(this);
    } 
    
    public void setPiezaQueOcupa(Pieza piezaQueOcupa) {
        this.piezaQueOcupa = piezaQueOcupa;
        
        if(piezaQueOcupa!=null)
        {
            // mostramos la imagen de la nueva pieza
            ImageIcon  i = new ImageIcon (piezaQueOcupa.getImageFile());       
            representacionVisual.setIcon(getScaledImage(i ,this.ancho, this.ancho));
        }
        else    // casilla vacia
            representacionVisual.setIcon(null);
    }
    
    private ImageIcon getScaledImage(ImageIcon srcImg, int w, int h)
    {
        // redimensionar imagen al tamaño de la casilla
        BufferedImage resizedImg = new BufferedImage(w, h, BufferedImage.TYPE_INT_ARGB);
        Graphics2D g2 = resizedImg.createGraphics();

        g2.setRenderingHint(RenderingHints.KEY_INTERPOLATION, RenderingHints.VALUE_INTERPOLATION_BILINEAR);
        g2.drawImage(srcImg.getImage(), 0, 0, w, h, null);
        g2.dispose();

        return new ImageIcon(resizedImg);
    }
    
    public Pieza getPiezaQueOcupa() {
        return piezaQueOcupa;
    }

    public int getAncho() {
        return ancho;
    }
    
    public boolean isColor() {
        return color;
    }

    public int getPosX() {
        return posX;
    }

    public int getPosY() {
        return posY;
    }

    public JLabel getRepresentacionVisual() {
        return representacionVisual;
    }
    
}
