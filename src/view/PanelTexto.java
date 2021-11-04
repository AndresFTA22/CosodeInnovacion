package view;

import javax.swing.*;
import java.awt.*;

public class PanelTexto extends JPanel {

    private JTextArea correo;

    public PanelTexto( ){
        setLayout(new BorderLayout());
        correo = new JTextArea();
        add(correo);
    }

    public String getCorreo(){
        return correo.getText();
    }
}
