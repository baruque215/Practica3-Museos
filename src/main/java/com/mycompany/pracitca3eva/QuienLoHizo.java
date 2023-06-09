/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JFrame.java to edit this template
 */
package com.mycompany.pracitca3eva;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import javax.swing.ImageIcon;
import javax.swing.JOptionPane;
import javax.swing.Timer;

/**
 *
 * @author canmonal
 */
public class QuienLoHizo extends javax.swing.JFrame {

    private GUI_Principal guiPrincipal; // Referencia a GUI_Principal

    private List<ImageIcon> imagenes;
    private List<String> autores = Arrays.asList("Pablo Picasso", "Leonardo Da Vinci", "Vicent Van Gogh", "Salvador Dali", "Diego Velazquez", "Frida Kahlo");
    private int indiceActual = 0;

    //Contador de segundos LO PONGO PÚBLICO PARA PODER AÑADIRLO A LA GUI_PRINCIPAL
    public Timer contadorTimer;
    public int contadorSegundos = 0;
    
    private int contadorAutorMAL = 0; //Contador para que cada vez que falle, se sume para darle como máximo 3 intentos por cuadro

    public QuienLoHizo(GUI_Principal guiPrincipal) {
        initComponents();
        this.guiPrincipal = guiPrincipal; // Asignar referencia a GUI_Principal
        setFrame();
    }

    public void setFrame() {
        mensajeInformacion();
        setDefaultCloseOperation(DO_NOTHING_ON_CLOSE);
        setTitle("¿Quien Lo hizo?");
        this.setResizable(false);
        this.setLocationRelativeTo(null);

        cargarImagenes();
        imagenLabel.setIcon(imagenes.get(0));
        indiceActual = 0;
        setVisible(true);
        iniciarContador();
    }

    private void mensajeInformacion() {
        //Creo un mensaje de información para dar las pistas antes de comenzar al usuario.
        JOptionPane.showMessageDialog(null, "<html><font color=\"blue\">Antes de empezar quiero decirte un par de <b>pistas</b></font></html>");
        String mensaje = "<html>Antes de comenzar, debes saber varias cosas:"
                + "<ol>"
                + "<li>No uses nombres simplificados, tendrás que poner el nombre del autor completo."
                + "<li>Los nombres no llevan tíldes."
                + "<li>Ejemplo de autor <b>Salvador Dali</b>."
                + "<li>Por cada fallo mal introducido, se te sumaran 3 segundos."
                + "<li>Vas a tener <b>3</b> intentos para adivinar cada autor. "
                + "<li>Si no adivinas en 3 intentos se sumarán a mayores 10 segundos más y pasaras al siguiente cuadro."
                + "</ol></html>";
        JOptionPane.showMessageDialog(rootPane, mensaje);
    }

    //Métodop para cargar las imágenes en un ArrayList
    private void cargarImagenes() { //Cargo la imágenes en el ArrayList
        imagenes = new ArrayList<>();
        imagenes.add(new ImageIcon("src/main/java/com/mycompany/pracitca3eva/imagenes/Guernica.jpg"));
        imagenes.add(new ImageIcon("src/main/java/com/mycompany/pracitca3eva/imagenes/LaGioconda.jpg"));
        imagenes.add(new ImageIcon("src/main/java/com/mycompany/pracitca3eva/imagenes/LaNocheEstrellada.jpg"));
        imagenes.add(new ImageIcon("src/main/java/com/mycompany/pracitca3eva/imagenes/LaPersistenciaDeLaMemoria.jpg"));
        imagenes.add(new ImageIcon("src/main/java/com/mycompany/pracitca3eva/imagenes/LasMeninas.jpg"));
        imagenes.add(new ImageIcon("src/main/java/com/mycompany/pracitca3eva/imagenes/LasDosFridas.jpg"));
    }

    //Método para iniciar contador
    private void iniciarContador() {
        contadorSegundos = 0;
        contadorTimer = new Timer(1000, e -> {
            contadorSegundos++;
            jLabelContador.setText("Contador: " + contadorSegundos + " segundos");
        });
        contadorTimer.start();
    }

    //Método para detener el contador
    private void detenerContador() {
        if (contadorTimer != null) {
            contadorTimer.stop();
            guiPrincipal.aumentarContadorTotal(contadorSegundos);
        }
    }

    //Método para mostrar la siguiente imagen
    private void mostrarSiguienteImagen() {
        if (indiceActual < imagenes.size() - 1) {
            indiceActual++; // Incrementar el índice antes de mostrar la imagen siguiente
            imagenLabel.setIcon(imagenes.get(indiceActual)); //Añade al label la imagen que toque
        } else {
            detenerContador();
            JOptionPane.showMessageDialog(null, "¡Felicidades! has terminado el primer juego");
            dispose(); //Cierra ventana
        }
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        panelImagen = new javax.swing.JPanel();
        imagenLabel = new javax.swing.JLabel();
        respuestaTextField = new javax.swing.JTextField();
        enviarButton = new javax.swing.JButton();
        jLabelContador = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        panelImagen.setLayout(new javax.swing.BoxLayout(panelImagen, javax.swing.BoxLayout.LINE_AXIS));
        panelImagen.add(imagenLabel);

        enviarButton.setText("Enviar");
        enviarButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                enviarButtonActionPerformed(evt);
            }
        });

        jLabelContador.setText("Contador");

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addGap(98, 98, 98)
                        .addComponent(respuestaTextField, javax.swing.GroupLayout.PREFERRED_SIZE, 254, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addComponent(enviarButton)
                        .addGap(49, 49, 49)
                        .addComponent(jLabelContador))
                    .addGroup(layout.createSequentialGroup()
                        .addContainerGap()
                        .addComponent(panelImagen, javax.swing.GroupLayout.PREFERRED_SIZE, 610, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(panelImagen, javax.swing.GroupLayout.DEFAULT_SIZE, 451, Short.MAX_VALUE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(respuestaTextField, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(enviarButton)
                    .addComponent(jLabelContador))
                .addContainerGap())
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void enviarButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_enviarButtonActionPerformed
        String respuestaUsuario = respuestaTextField.getText(); //Recojo la respuesta del usuario
        String respuestaCorrecta = autores.get(indiceActual); //Se comprueba la respuesta con el array de autores
        if (respuestaUsuario.equalsIgnoreCase(respuestaCorrecta)) {
            mostrarSiguienteImagen(); //Muestro la imágen siguiente
            respuestaTextField.setText("");
        } else {
            respuestaTextField.setText("");
            JOptionPane.showMessageDialog(this, "Autor incorrecto. Inténtalo de nuevo.");            
            contadorSegundos = contadorSegundos + 3; //Sumo 3 por error            
            contadorAutorMAL++;
                if(contadorAutorMAL==3){
                    contadorAutorMAL=0;
                    contadorSegundos=contadorSegundos +10;
                    mostrarSiguienteImagen();
                }
        }
    }//GEN-LAST:event_enviarButtonActionPerformed

    /**
     * @param args the command line arguments
     */
    public static void main(String args[]) {
        /* Set the Nimbus look and feel */
        //<editor-fold defaultstate="collapsed" desc=" Look and feel setting code (optional) ">
        /* If Nimbus (introduced in Java SE 6) is not available, stay with the default look and feel.
         * For details see http://download.oracle.com/javase/tutorial/uiswing/lookandfeel/plaf.html 
         */
        try {
            for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
                if ("Nimbus".equals(info.getName())) {
                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        } catch (ClassNotFoundException ex) {
            java.util.logging.Logger.getLogger(QuienLoHizo.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(QuienLoHizo.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(QuienLoHizo.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(QuienLoHizo.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                GUI_Principal guiPrincipal = new GUI_Principal();
                QuienLoHizo quienLoHizo = new QuienLoHizo(guiPrincipal);
                quienLoHizo.setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton enviarButton;
    private javax.swing.JLabel imagenLabel;
    private javax.swing.JLabel jLabelContador;
    private javax.swing.JPanel panelImagen;
    private javax.swing.JTextField respuestaTextField;
    // End of variables declaration//GEN-END:variables
}