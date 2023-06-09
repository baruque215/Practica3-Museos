/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JFrame.java to edit this template
 */
package com.mycompany.pracitca3eva;

import java.util.Random;
import javax.swing.ImageIcon;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.Timer;

/**
 *
 * @author canmonal
 */
public class GregorioFernandez extends javax.swing.JFrame {

    private GUI_Principal guiPrincipal; // Referencia a GUI_Principal

    private ImageIcon gf = new ImageIcon("src/main/java/com/mycompany/pracitca3eva/imagenes/gf.png");

    //Contador de segundos LO PONGO PÚBLICO PARA PODER AÑADIRLO A LA GUI_PRINCIPAL
    public Timer contadorTimer;
    public int contadorSegundos = 0;
    private int contadorAciertos = 0;
    private int contadorFallos = 0;

    //Contador para saber qué imagen hay que escoger
    private int indiceActual = 0;

    //Creo Listas de imágenes de GF y otros autores
    private ImageIcon[] imagenesGregorioFernandez = {
        new ImageIcon("src/main/java/com/mycompany/pracitca3eva/imagenes/ObrasGF/1_CristoYacenteGF.jpg"),
        new ImageIcon("src/main/java/com/mycompany/pracitca3eva/imagenes/ObrasGF/2_LaPiedadGF.jpg"),
        new ImageIcon("src/main/java/com/mycompany/pracitca3eva/imagenes/ObrasGF/3_DescendimientoGF.jpg"),
        new ImageIcon("src/main/java/com/mycompany/pracitca3eva/imagenes/ObrasGF/4_SantaTeresaJesusGF.jpg"),
        new ImageIcon("src/main/java/com/mycompany/pracitca3eva/imagenes/ObrasGF/5_DolorosaVeraCruzGF.jpg")
    };

    private ImageIcon[] imagenesOtrosAutores = {
        new ImageIcon("src/main/java/com/mycompany/pracitca3eva/imagenes/ObrasOtras/1_CristoMilagrosOtro.jpg"),
        new ImageIcon("src/main/java/com/mycompany/pracitca3eva/imagenes/ObrasOtras/2_LaPieta.jpg"),
        new ImageIcon("src/main/java/com/mycompany/pracitca3eva/imagenes/ObrasOtras/3_DescendimientoDeLaCruzOTRO.jpg"),
        new ImageIcon("src/main/java/com/mycompany/pracitca3eva/imagenes/ObrasOtras/4_SantaTeresaEnExtasisOTRO.jpg"),
        new ImageIcon("src/main/java/com/mycompany/pracitca3eva/imagenes/ObrasOtras/5_LaDolorosaOTRO.jpg")
    };

    public GregorioFernandez(GUI_Principal guiPrincipal) {
        initComponents();
        this.guiPrincipal = guiPrincipal; // Asignar referencia a GUI_Principal
        setFrame();
        mostrarImagenes(indiceActual);
    }

    private void setFrame() {
        mensajeInformacion();
        setDefaultCloseOperation(DO_NOTHING_ON_CLOSE);
        setTitle("¿Gregorio Fernandez?");
        this.setResizable(false);
        this.setLocationRelativeTo(null);

        //Añado el icono del gf a la pantalla
        imagenGF.setIcon(gf);
        imagenGF.setText("");

        //Iniciamos el contador del juego 2
        iniciarContador();        
    }
    
    private void mensajeInformacion() {
        //Creo un mensaje de información para dar las pistas antes de comenzar al usuario.        
        String mensaje = "<html>Antes de comenzar, debes saber varias cosas:"
                + "<ol>"
                + "<li>Para responder, basta con hacer clic en la imagen correspondiente.</li>"
                + "<li>Si seleccionas la imagen correcta, se incrementará tu contador de aciertos.</li>"
                + "<li>Si seleccionas la imagen incorrecta, se incrementará tu contador de fallos y se "
                + "sumarán 15 segundos a tu contador de tiempo.</li>"
                + "</ol>"
                + "¡Buena suerte!</html>";
        JOptionPane.showMessageDialog(this, mensaje, "Información", JOptionPane.INFORMATION_MESSAGE);
    }

    //Cotrolo el tiempo con iniciar y detener
    private void iniciarContador() {
        contadorSegundos = 0;
        contadorTimer = new Timer(1000, e -> {
            contadorSegundos++;
            jLabelContador.setText("Contador: " + contadorSegundos + " segundos");
        });
        contadorTimer.start();
    }

    private void detenerContador() {
        if (contadorTimer != null) {
            contadorTimer.stop();
            guiPrincipal.aumentarContadorTotal(contadorSegundos);
            String mensaje = "Juego terminado\n\n";
        mensaje += "Tiempo total: " + contadorSegundos + " segundos\n";
        mensaje += "Aciertos: " + contadorAciertos + "\n";
        mensaje += "Fallos: " + contadorFallos;
        
        JOptionPane.showMessageDialog(this, mensaje, "Resultado", JOptionPane.INFORMATION_MESSAGE);
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

        jPanel1 = new javax.swing.JPanel();
        labelImagenIzquierda = new javax.swing.JLabel();
        jPanel2 = new javax.swing.JPanel();
        labelImagenDerecha = new javax.swing.JLabel();
        jLabelContador = new javax.swing.JLabel();
        TITULO = new java.awt.Label();
        panel1 = new java.awt.Panel();
        imagenGF = new javax.swing.JLabel();
        labelcontadorFallos = new javax.swing.JLabel();
        labelcontadorAciertos = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setMaximumSize(new java.awt.Dimension(658, 350));
        setPreferredSize(new java.awt.Dimension(658, 350));
        setSize(new java.awt.Dimension(658, 350));

        jPanel1.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));
        jPanel1.setMaximumSize(new java.awt.Dimension(200, 220));
        jPanel1.setMinimumSize(new java.awt.Dimension(200, 220));
        jPanel1.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jPanel1MouseClicked(evt);
            }
        });
        jPanel1.setLayout(new javax.swing.BoxLayout(jPanel1, javax.swing.BoxLayout.LINE_AXIS));
        jPanel1.add(labelImagenIzquierda);

        jPanel2.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));
        jPanel2.setMaximumSize(new java.awt.Dimension(200, 220));
        jPanel2.setMinimumSize(new java.awt.Dimension(200, 220));
        jPanel2.setPreferredSize(new java.awt.Dimension(200, 220));
        jPanel2.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jPanel2MouseClicked(evt);
            }
        });
        jPanel2.setLayout(new javax.swing.BoxLayout(jPanel2, javax.swing.BoxLayout.LINE_AXIS));
        jPanel2.add(labelImagenDerecha);

        jLabelContador.setText("Contador");

        TITULO.setFont(new java.awt.Font("Segoe Print", 1, 18)); // NOI18N
        TITULO.setText("Selecciona la imagen correcta del autor GREGORIO FERNÁNDEZ");

        panel1.setPreferredSize(new java.awt.Dimension(200, 100));

        imagenGF.setText("jLabel1");
        panel1.add(imagenGF);

        labelcontadorFallos.setText("Fallos: 0");

        labelcontadorAciertos.setText("Aciertos: 0");

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(18, 18, 18)
                .addComponent(TITULO, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
            .addGroup(layout.createSequentialGroup()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addGap(56, 56, 56)
                        .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, 200, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(layout.createSequentialGroup()
                        .addContainerGap()
                        .addComponent(jLabelContador)))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(labelcontadorAciertos, javax.swing.GroupLayout.PREFERRED_SIZE, 58, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(jPanel2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addComponent(labelcontadorFallos))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(panel1, javax.swing.GroupLayout.PREFERRED_SIZE, 83, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addComponent(TITULO, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addGroup(layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addGroup(layout.createSequentialGroup()
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, 220, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(jPanel2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED))
                            .addGroup(layout.createSequentialGroup()
                                .addComponent(labelcontadorAciertos)
                                .addGap(4, 4, 4)))
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabelContador)
                            .addComponent(labelcontadorFallos)))
                    .addComponent(panel1, javax.swing.GroupLayout.PREFERRED_SIZE, 77, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(16, Short.MAX_VALUE))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    //Método para mostrar imágenes
    private void mostrarImagenes(int indiceActual) {
        Random random = new Random();
        boolean mostrarEnIzquierda = random.nextBoolean(); // Generar aleatoriamente si se muestra en la izquierda o derecha

        if (mostrarEnIzquierda) {
            labelImagenIzquierda.setIcon(imagenesOtrosAutores[indiceActual]);
            labelImagenDerecha.setIcon(imagenesGregorioFernandez[indiceActual]);
        } else {
            labelImagenIzquierda.setIcon(imagenesGregorioFernandez[indiceActual]);
            labelImagenDerecha.setIcon(imagenesOtrosAutores[indiceActual]);
        }
    }

    //Métodop para verificar respuesta
    private void verificarRespuesta(JLabel label) {
    if (indiceActual < imagenesGregorioFernandez.length) {
        ImageIcon imagenSeleccionada = (ImageIcon) label.getIcon();
        ImageIcon imagenCorrecta = imagenesGregorioFernandez[indiceActual];

        if (imagenSeleccionada.equals(imagenCorrecta)) {
            contadorAciertos++; // Incrementar contador de aciertos
            labelcontadorAciertos.setText("Aciertos: " + contadorAciertos);
        } else {
            contadorSegundos += 15; // Sumar 15 segundos al contador de tiempo
            contadorFallos++; // Incrementar contador de fallos
            labelcontadorFallos.setText("Fallos: " + contadorFallos);
        }

        indiceActual++; // Incrementar el índice actual
               
        if (indiceActual < imagenesGregorioFernandez.length) { //Hace que no se muestre la primera imagen dos veces y que salgan en orden
            mostrarImagenes(indiceActual);
        } else {
            detenerContador();
            dispose();
        }
    }
}


    private void jPanel1MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jPanel1MouseClicked
       verificarRespuesta(labelImagenIzquierda);
    }//GEN-LAST:event_jPanel1MouseClicked

    private void jPanel2MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jPanel2MouseClicked
       verificarRespuesta(labelImagenDerecha);
    }//GEN-LAST:event_jPanel2MouseClicked

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
            java.util.logging.Logger.getLogger(GregorioFernandez.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(GregorioFernandez.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(GregorioFernandez.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(GregorioFernandez.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                GUI_Principal guiPrincipal = new GUI_Principal();
                GregorioFernandez gf = new GregorioFernandez(guiPrincipal);
                gf.setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private java.awt.Label TITULO;
    private javax.swing.JLabel imagenGF;
    private javax.swing.JLabel jLabelContador;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JLabel labelImagenDerecha;
    private javax.swing.JLabel labelImagenIzquierda;
    private javax.swing.JLabel labelcontadorAciertos;
    private javax.swing.JLabel labelcontadorFallos;
    private java.awt.Panel panel1;
    // End of variables declaration//GEN-END:variables
}