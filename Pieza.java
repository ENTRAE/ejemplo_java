/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ajedrez;

/**
 *
 * @author Jose
 */
public class Pieza {
    public static enum tipoPieza { torre, alfil, peon, rey, reina, caballo};
    
    private tipoPieza forma;
    private boolean color;  // 0 = negro, 1 = blanco
    private String imageFile;
    private boolean estaEnJuego = true;

    public boolean isEstaEnJuego() {
        return estaEnJuego;
    }

    public void setEstaEnJuego(boolean estaEnJuego) {
        this.estaEnJuego = estaEnJuego;
    }

    public tipoPieza getForma() {
        return forma;
    }

    public boolean isColor() {
        return color;
    }

    public String getImageFile() {
        return imageFile;
    }
    
    public Pieza(tipoPieza forma, boolean color)
    {
        this.forma = forma;
        this.color = color;
        this.imageFile = DALPieza.LeerImagen(getFormaString(), color);
    }
    
    public String toString()
    {
        return getFormaString() + " " + (color ? "Blanca" : "Negra");
    }
    
    private String getFormaString()
    {
        switch(this.forma)
        {
            case torre: return "TORRE";
            case alfil: return "ALFIL";
            case rey: return "REY";
            case reina: return "REINA";
            case peon: return "PEON";
            case caballo: return "CABALLO";
            default: return "";
        }
    }
}
