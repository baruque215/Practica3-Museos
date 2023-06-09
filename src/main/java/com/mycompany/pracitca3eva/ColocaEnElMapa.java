/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JFrame.java to edit this template
 */
package com.mycompany.pracitca3eva;

import static com.mycompany.pracitca3eva.Verdadero_Falso.conectarBD;
import java.awt.BorderLayout;
import java.awt.Image;
import java.awt.event.ComponentAdapter;
import java.awt.event.ComponentEvent;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import javax.swing.Timer;
import javax.swing.ImageIcon;
import javax.swing.JLabel;
import javax.swing.JOptionPane;

/**
 *
 * @author barperal
 */
public class ColocaEnElMapa extends javax.swing.JFrame {

    private GUI_Principal guiPrincipal; // Referencia a GUIPrincipal
    private static String urlBD = "jdbc:mysql://localhost:3306/dim_gf";
    private static String user = "root";
    private static String passwd = "";
    private static Connection con;

    //Contador de segundos LO PONGO PÚBLICO PARA PODER AÑADIRLO A LA GUI_PRINCIPAL
    public Timer contadorTimer;
    public int contadorSegundos = 0;

    private List<String> nombresObras = new ArrayList<>();
    private int indiceObras = 0;
    private int aciertos = 0;
    private int fallos = 0;
    private javax.swing.JLabel ObraActual;

    /**
     * Creates new form ColocaEnElMapa
     *
     * @param guiPrincipal
     */
    public ColocaEnElMapa(GUI_Principal guiPrincipal) {
        initComponents();
        setFrame();
        con = conectarBD();
        this.guiPrincipal = guiPrincipal;
        mostrarObrasDeArte();
        ObraActual = new javax.swing.JLabel();

        String mapaPath = "src/main/java/com/mycompany/pracitca3eva/imagenes/mapa_europa.jpg"; // Ruta del mapa de Europa
        ImageIcon mapaIcon = new ImageIcon(mapaPath);
        Image mapaImage = mapaIcon.getImage();

// Establecer el mapa de Europa como fondo del panel
        Panel_Mapa.setOpaque(true);
        Panel_Mapa.setBorder(null);
        Panel_Mapa.setLayout(new BorderLayout());
        Panel_Mapa.add(new JLabel(new ImageIcon(mapaImage)));

        Panel_Mapa.addComponentListener(new ComponentAdapter() {
            @Override
            public void componentResized(ComponentEvent e) {

                int width = Panel_Mapa.getWidth();
                int height = Panel_Mapa.getHeight();

                Image resizedMapImage = mapaImage.getScaledInstance(width, height, Image.SCALE_SMOOTH);

                ImageIcon resizedMapIcon = new ImageIcon(resizedMapImage);

                ((JLabel) Panel_Mapa.getComponent(0)).setIcon(resizedMapIcon);
            }
        });

        Panel_Mapa.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                super.mouseClicked(e);
                verificarUbicacion(e.getX(), e.getY());
            }
        });
    }

    public void setFrame() {

        setDefaultCloseOperation(DO_NOTHING_ON_CLOSE); 
        setTitle("Coloca en el mapa");
        this.setResizable(false);
        this.setLocationRelativeTo(null);
        setVisible(true);
        iniciarContador();

    }

    public static Connection conectarBD() {
        try {
            con = DriverManager.getConnection(urlBD, user, passwd);
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
        return con;
    }

    public static void desconectarBD() {
        try {
            if (con != null) {
                con.close();
            }
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
    }

    //Método para iniciar contador
    private void iniciarContador() {
        contadorSegundos = 0;
        contadorTimer = new Timer(1000, e -> {
            contadorSegundos++;
            Contador.setText("Contador: " + contadorSegundos + " segundos");
        });
        contadorTimer.start();
    }

    //Método para detener el contador
    private void detenerContador() {
        if (contadorTimer != null) {
            contadorTimer.stop();
            this.guiPrincipal.aumentarContadorTotal(contadorSegundos);
        }
    }

    private void mostrarObrasDeArte() {

        try {
            Statement stmt = con.createStatement();
            ResultSet rs = stmt.executeQuery("SELECT nombre_obra FROM obras ORDER BY RAND()");

            while (rs.next()) {
                String nombreObra = rs.getString("nombre_obra");
                nombresObras.add(nombreObra);
            }

            if (!nombresObras.isEmpty()) {
                ObradeArte.setText(nombresObras.get(indiceObras));
                indiceObras++;
            } else {
                ObradeArte.setText("No hay obras de arte disponibles.");
            }
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
    }

    private void mostrarSiguienteObra() {
        if (indiceObras < nombresObras.size()) {
            ObraActual.setText("Obra actual: " + nombresObras.get(indiceObras));
        } else {
            mostrarResultadoFinal();            
        }

    }

    
    //Método para verificar si la obra esta en X-Y del país
    private void verificarUbicacion(int x, int y) {
        String nombreObra = nombresObras.get(indiceObras);
        String query = "SELECT * FROM obras WHERE nombre_obra = '" + nombreObra + "'";

        try {
            Statement stmt = con.createStatement();
            ResultSet rs = stmt.executeQuery(query);

            if (rs.next()) {
                int xCorrecta = rs.getInt("coordenada_x");
                int yCorrecta = rs.getInt("coordenada_y");

                if (x == xCorrecta && y == yCorrecta) {
                    aciertos++;
                    JOptionPane.showMessageDialog(this, "¡Correcto! Has ubicado la obra de arte correctamente.");
                } else {
                    fallos++;
                    JOptionPane.showMessageDialog(this, "Incorrecto. La ubicación correcta es (" + xCorrecta + ", " + yCorrecta + ").");
                }

                indiceObras++;

                if (indiceObras == nombresObras.size()) {
                    JOptionPane.showMessageDialog(this, "Has completado todas las obras de arte.");
                    contadorTimer.stop();
                    mostrarResultadoFinal();
                } else {
                    mostrarSiguienteObra();
                }
            }

            rs.close();
            stmt.close();

        } catch (SQLException ex) {
            ex.printStackTrace();
        }
    }

    //Método que aumentaría Fallos
    private void aumentarFallos() {
        // Incrementar el contador de fallos
        fallos++;

        // Pasar a la siguiente obra de arte si hay más
        if (indiceObras < nombresObras.size()) {
            ObradeArte.setText(nombresObras.get(indiceObras));
            indiceObras++;
        } else {
            ObradeArte.setText("No hay más obras de arte.");
            Enviar.setEnabled(false);
            mostrarResultadoFinal();
        }

        // Sumar 10 segundos al contador
        contadorSegundos += 10;
    }

    public void mostrarResultadoFinal() {
        int totalPreguntas = nombresObras.size();
        int porcentajeAciertos = (int) ((double) aciertos / totalPreguntas * 100);
        int porcentajeFallos = (int) ((double) fallos / totalPreguntas * 100);

        String mensaje = "Has completado todas las obras de arte.\n\n";
        mensaje += "Resultados:\n";
        mensaje += "Tiempo transcurrido: " + Contador.getText() + "\n";
        mensaje += "Aciertos: " + aciertos + " (" + porcentajeAciertos + "%)\n";
        mensaje += "Fallos: " + fallos + " (" + porcentajeFallos + "%)\n";

        JOptionPane.showMessageDialog(this, mensaje);
        // Detener el contador
        detenerContador();       
        dispose(); //Cierra la ventana
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
        ObradeArte = new javax.swing.JLabel();
        Enviar = new javax.swing.JButton();
        Contador = new javax.swing.JLabel();
        Panel_Mapa = new javax.swing.JPanel();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        Enviar.setText("Enviar");
        Enviar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                EnviarActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout Panel_MapaLayout = new javax.swing.GroupLayout(Panel_Mapa);
        Panel_Mapa.setLayout(Panel_MapaLayout);
        Panel_MapaLayout.setHorizontalGroup(
            Panel_MapaLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 0, Short.MAX_VALUE)
        );
        Panel_MapaLayout.setVerticalGroup(
            Panel_MapaLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 451, Short.MAX_VALUE)
        );

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addComponent(Panel_Mapa, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addGap(17, 17, 17))
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addComponent(ObradeArte, javax.swing.GroupLayout.PREFERRED_SIZE, 282, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 94, Short.MAX_VALUE)
                        .addComponent(Enviar)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(Contador, javax.swing.GroupLayout.PREFERRED_SIZE, 156, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addContainerGap())))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(Panel_Mapa, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGap(18, 18, 18)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(Enviar, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(Contador, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(ObradeArte, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addContainerGap())
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void EnviarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_EnviarActionPerformed
        if (indiceObras < nombresObras.size()) {
            ObradeArte.setText(nombresObras.get(indiceObras));
            indiceObras++;
        } else {
            ObradeArte.setText("No hay más obras de arte.");
            Enviar.setEnabled(false); // Desactivar el botón si no hay más obras            
            mostrarResultadoFinal();
        }
    }//GEN-LAST:event_EnviarActionPerformed

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
            java.util.logging.Logger.getLogger(ColocaEnElMapa.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(ColocaEnElMapa.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(ColocaEnElMapa.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(ColocaEnElMapa.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                GUI_Principal guiPrincipal = new GUI_Principal();
                ColocaEnElMapa ceem = new ColocaEnElMapa(guiPrincipal);
                ceem.setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JLabel Contador;
    private javax.swing.JButton Enviar;
    private javax.swing.JLabel ObradeArte;
    private javax.swing.JPanel Panel_Mapa;
    private javax.swing.JPanel jPanel1;
    // End of variables declaration//GEN-END:variables
}
