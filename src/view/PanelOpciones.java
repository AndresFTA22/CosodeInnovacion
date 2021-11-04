package view;

import javax.swing.*;
import javax.swing.border.TitledBorder;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class PanelOpciones extends JPanel implements ActionListener {

    public static final String VERIFICAR = "Verificar correo";
    public static final String AGREGAR = "Agregar palabras busqueda";
    public static final String LISTAR = "Listar palabras bloqueadas";

    private Ventana principal;

    private JButton btnVerificar;
    private JButton btnAgregar;
    private JButton btnListar;

    public PanelOpciones(Ventana pVentana){
        principal = pVentana;
        setBorder( new TitledBorder( "Opciones" ) );
        setLayout( new GridLayout( 1, 3 ) );

        btnVerificar = new JButton( "Verificar seguridad de mi correo" );
        btnVerificar.setActionCommand( VERIFICAR );
        btnVerificar.addActionListener( this );

        btnAgregar = new JButton( "Agregar palabras restringidas" );
        btnAgregar.setActionCommand( AGREGAR );
        btnAgregar.addActionListener( this );

        btnListar = new JButton( "Ver palabras restringidas" );
        btnListar.setActionCommand( LISTAR );
        btnListar.addActionListener( this );

        add(btnVerificar);
        add(btnAgregar);
        add(btnListar);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        String comando = e.getActionCommand();
        if(comando.equals(VERIFICAR))
            principal.verificacion();
        else if(comando.equals(AGREGAR))
            principal.agregar();
        else if(comando.equals(LISTAR))
            principal.listar( );
    }
}
