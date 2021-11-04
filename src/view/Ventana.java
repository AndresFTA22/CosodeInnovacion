package view;

import javax.swing.*;
import java.awt.BorderLayout;
import java.io.*;
import java.util.*;

public class Ventana extends JFrame{

    private String ruta = "./src/view/palabras.txt";
    private File archivo;
    private ArrayList<String> palabras;
    private PanelTexto panelTexto;
    private PanelOpciones panelOpciones;

    public Ventana(){
        cargar();

        setLayout(new BorderLayout());
        setSize(1000,650);
        setDefaultCloseOperation( JFrame.EXIT_ON_CLOSE );
        setResizable( true );
        setLocationRelativeTo(null);
        setTitle( "Proyecto de innovacion" );

        panelTexto = new PanelTexto();
        add(panelTexto,BorderLayout.CENTER);

        panelOpciones = new PanelOpciones(this);
        add(panelOpciones,BorderLayout.SOUTH);
    }

    public void cargar(){
        this.archivo = new File(ruta);
        if(!this.archivo.exists())
            System.out.println("El archivo no existe");
        else
            this.palabras = leerArchivo(archivo);
    }

    public ArrayList<String> leerArchivo(File ar){
        ArrayList<String> respuesta = new ArrayList<String>();
        try {
            Scanner sc = new Scanner(ar);
            while(sc.hasNextLine()){
                String info = sc.nextLine();
                respuesta.add(info);
            }
            sc.close();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
            respuesta = null;
        }
        return respuesta;
    }

    public void verificacion( ){
        String texto = panelTexto.getCorreo().toLowerCase(),link="";
        boolean bueno = true;
        if(texto.contains("http")&&!texto.contains("https"))
            link += "\nLos enlaces que contiene podrian no ser seguros.";
        for(String s:this.palabras)
            if(texto.contains(s.toLowerCase())) {
                JOptionPane.showMessageDialog(this, "Este correo contiene informacion sospechosa."+link, "Resultado verificacion", JOptionPane.ERROR_MESSAGE);
                bueno = false;
                break;
            }
        if(bueno)
            JOptionPane.showMessageDialog(this, "Este correo es seguro."+link, "Resultado verificacion", JOptionPane.INFORMATION_MESSAGE);
    }

    public void agregar( ){
        String nuevo = JOptionPane.showInputDialog("Ingrese la nueva palabra:");
        if(nuevo!=null) {
            nuevo.toLowerCase();
            if (palabras.contains(nuevo))
                JOptionPane.showMessageDialog(this, "Esta palabra ya esta siendo monitoreada.", "Agregar nuevo", JOptionPane.WARNING_MESSAGE);
            else {
                try {
                    FileWriter wr = new FileWriter(ruta, true);
                    wr.write("\n" + nuevo);
                    wr.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
            cargar();
        }
    }

    public void listar( ){
        String todas = "";
        for(String s:palabras) {
            todas += "- ";
            todas += s;
            todas += "\n";
        }
        JOptionPane.showMessageDialog(this, "Las palabras restringidas son:\n"+todas, "Lista", JOptionPane.INFORMATION_MESSAGE);
    }

    public static void main(String[] args) {
        Ventana ventana = new Ventana();
        ventana.setVisible(true);
        for(String s:ventana.palabras)
            System.out.println(s);
    }
}
