/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ajedrez;

/**
 *
 * @author Docent
 */
public class Move {
    private boolean estaMoviendo = false;
    private Pieza piezaPorMover;
    private Casilla origen;
    private Casilla destino;

    public Casilla getOrigen() {
        return origen;
    }

    public void setOrigen(Casilla origen) {
        this.origen = origen;
    }

    public boolean isEstaMoviendo() {
        return estaMoviendo;
    }

    public void setEstaMoviendo(boolean estaMoviendo) {
        this.estaMoviendo = estaMoviendo;
    }

    public Pieza getPiezaPorMover() {
        return piezaPorMover;
    }

    public void setPiezaPorMover(Pieza piezaPorMover) {
        this.piezaPorMover = piezaPorMover;
        estaMoviendo = true;
    }

    public Casilla getDestino() {
        return destino;
    }

    public void setDestino(Casilla destino) {
        this.destino = destino;
    }
    
    public boolean hacerMove()
    {
        origen.setPiezaQueOcupa(null);
        destino.setPiezaQueOcupa(piezaPorMover);
        estaMoviendo = false;
        
        return true;
    }
    
    public void RealizarMovimiento(Casilla c)
    {
        Pieza piezaEnLaCasilla = c.getPiezaQueOcupa();
        
        if(!estaMoviendo)
        {
            // paso uno del move:
            // hay una pieza, la "cogemos"
            if(piezaEnLaCasilla!=null)
            {
                piezaPorMover = piezaEnLaCasilla;
                origen = c;
                estaMoviendo = true;
            }
        }
        else
        {
            // segundo paso del move:
            // "soltamos" la pieza en la nueva casilla
            if(piezaEnLaCasilla==null)
            {
                // casilla libre, movemos
                destino = c;
                hacerMove();
            }
            else if(piezaEnLaCasilla.isColor()==piezaPorMover.isColor())
            {
                // casilla ocupada por otra pieza mismo color
                // NO se puede
                estaMoviendo = false;
            }
            else
            {
                // casilla ocupada por otra pieza de otro color:               
                // tomar la otra pieza...
                piezaEnLaCasilla.setEstaEnJuego(false);
                destino = c;
                hacerMove();
            }
        }
    }
}
