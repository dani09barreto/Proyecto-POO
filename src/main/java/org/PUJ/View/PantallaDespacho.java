package org.PUJ.View;

import org.PUJ.Control.ControlDespacho;

public class PantallaDespacho {
    private ControlDespacho centralDespacho = new ControlDespacho();

    public static void main( String[] args ) {
        PantallaDespacho pantalla = new PantallaDespacho();
        System.out.println( "como fua" );
    }

    public ControlDespacho getCentralDespacho() {
        return centralDespacho;
    }
}
