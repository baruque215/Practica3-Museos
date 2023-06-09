/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JFrame.java to edit this template
 */
package com.mycompany.pracitca3eva;

import com.mycompany.pracitca3eva.GUI_Principal;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import javax.swing.JOptionPane;
import javax.swing.Timer;

/**
 *
 * @author barperal
 */
public class Verdadero_Falso extends javax.swing.JFrame {

    private GUI_Principal guiPrincipal; // Referencia a GUIPrincipal
    private static String urlBD = "jdbc:mysql://localhost:3306/dim_gf";
    private static String user = "root";
    private static String passwd = "";
    private static Connection con;

    //Contador de segundos LO PONGO PÚBLICO PARA PODER AÑADIRLO A LA GUI_PRINCIPAL
    public Timer contadorTimer;
    public int contadorSegundos = 0;
    private int indiceMuseoActual = 0;
    private int aciertos;
    private int fallos;
    private List<String> nombresMuseos;
    private List<Boolean> esFalsoMuseos;

    /**
     * Creates new form Verdadero_Falso
     */
    public Verdadero_Falso(GUI_Principal guiPrincipal) {
        nombresMuseos = new ArrayList<String>();
        initComponents();
        con = conectarBD();
        this.guiPrincipal = guiPrincipal;
        cargarNombresMuseos();
        mostrarSiguienteMuseo();
        setFrame();
    }

    public void setFrame() {
        setDefaultCloseOperation(DO_NOTHING_ON_CLOSE);
        setTitle("Verdadero o Falso");
        this.setResizable(false);
        this.setLocationRelativeTo(null);
        setVisible(true);
        iniciarContador();
        mostrarSiguienteMuseo();
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
            guiPrincipal.aumentarContadorTotal(contadorSegundos);
        }
    }

    private void cargarNombresMuseos() {
        nombresMuseos = new ArrayList<>();
        esFalsoMuseos = new ArrayList<>();

        try ( Connection connection = DriverManager.getConnection(urlBD, user, passwd)) {
            String query = "SELECT nombre_museo, es_falso FROM museos";
            PreparedStatement statement = connection.prepareStatement(query);
            ResultSet resultSet = statement.executeQuery();

            while (resultSet.next()) {
                String nombreMuseo = resultSet.getString("nombre_museo");
                boolean esFalso = resultSet.getBoolean("es_falso");
                nombresMuseos.add(nombreMuseo);
                esFalsoMuseos.add(esFalso);
            }

            resultSet.close();
            statement.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }

        // Desordenar los museos
        List<Integer> indices = new ArrayList<>();
        for (int i = 0; i < nombresMuseos.size(); i++) {
            indices.add(i);
        }
        Collections.shuffle(indices);

        List<String> nombresDesordenados = new ArrayList<>();
        List<Boolean> esFalsoDesordenados = new ArrayList<>();

        for (int i : indices) {
            nombresDesordenados.add(nombresMuseos.get(i));
            esFalsoDesordenados.add(esFalsoMuseos.get(i));
        }
        nombresMuseos = nombresDesordenados;
        esFalsoMuseos = esFalsoDesordenados;
    }

    private boolean consultarBaseDeDatos(String nombreMuseo) {

        try ( Connection connection = DriverManager.getConnection(urlBD, user, passwd)) {
            String query = "SELECT es_falso FROM museos WHERE nombre_museo = ?";
            PreparedStatement statement = connection.prepareStatement(query);
            statement.setString(1, nombreMuseo);
            ResultSet resultSet = statement.executeQuery();

            if (resultSet.next()) {
                return !resultSet.getBoolean("es_falso");
            }

            resultSet.close();
            statement.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return false; // Se devuelve el valor predeterminado si no se encontró ningún resultado en la base de datos
    }

    private void mostrarSiguienteMuseo() {
    if (indiceMuseoActual >= nombresMuseos.size()) {
        detenerContador();
        JOptionPane.showMessageDialog(null, "Felicidades! has terminado el juego");
        dispose(); // Cierra la ventana actual
        return;
    }

    String nombreMuseo = nombresMuseos.get(indiceMuseoActual);
    NombreMuseo.setText(nombreMuseo);
    indiceMuseoActual++;
}


    private void aumentarAciertos() {
    aciertos++;
    jLabelAciertos.setText("Aciertos: " + aciertos);
}

private void aumentarFallos() {
    fallos++;
    jLabelFallos.setText("Fallos: " + fallos);
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
        Verdadero = new javax.swing.JButton();
        Falso = new javax.swing.JButton();
        NombreMuseo = new javax.swing.JLabel();
        Contador = new javax.swing.JLabel();
        jLabelAciertos = new javax.swing.JLabel();
        jLabelFallos = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        Verdadero.setText("Verdadero");
        Verdadero.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                VerdaderoActionPerformed(evt);
            }
        });

        Falso.setText("Falso");
        Falso.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                FalsoActionPerformed(evt);
            }
        });

        jLabelAciertos.setText("Aciertos: ");

        jLabelFallos.setText("Fallos: ");

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                .addContainerGap(25, Short.MAX_VALUE)
                .addComponent(jLabelAciertos)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(NombreMuseo, javax.swing.GroupLayout.PREFERRED_SIZE, 255, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jLabelFallos)
                .addGap(48, 48, 48))
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addComponent(Contador, javax.swing.GroupLayout.PREFERRED_SIZE, 225, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(0, 0, Short.MAX_VALUE))
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addComponent(Verdadero, javax.swing.GroupLayout.PREFERRED_SIZE, 132, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(Falso, javax.swing.GroupLayout.PREFERRED_SIZE, 126, javax.swing.GroupLayout.PREFERRED_SIZE))))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(Contador, javax.swing.GroupLayout.DEFAULT_SIZE, 16, Short.MAX_VALUE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(NombreMuseo, javax.swing.GroupLayout.PREFERRED_SIZE, 200, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabelAciertos)
                    .addComponent(jLabelFallos))
                .addGap(18, 18, 18)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                    .addComponent(Falso, javax.swing.GroupLayout.DEFAULT_SIZE, 42, Short.MAX_VALUE)
                    .addComponent(Verdadero, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addContainerGap())
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
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

    private void VerdaderoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_VerdaderoActionPerformed
         String nombreMuseo = NombreMuseo.getText();
    boolean esFalso = consultarBaseDeDatos(nombreMuseo);

    if (!esFalso) {
        aumentarAciertos();
    } else {
        contadorSegundos += 10;
        aumentarFallos();
    }
    
    mostrarSiguienteMuseo();
    }//GEN-LAST:event_VerdaderoActionPerformed

    private void FalsoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_FalsoActionPerformed
        String nombreMuseo = NombreMuseo.getText();
    boolean esFalso = consultarBaseDeDatos(nombreMuseo);
    
    if (esFalso) {
        aumentarAciertos();
    } else {
        contadorSegundos += 10;
        aumentarFallos();
    }
    
    mostrarSiguienteMuseo();

    }//GEN-LAST:event_FalsoActionPerformed


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JLabel Contador;
    private javax.swing.JButton Falso;
    private javax.swing.JLabel NombreMuseo;
    private javax.swing.JButton Verdadero;
    private javax.swing.JLabel jLabelAciertos;
    private javax.swing.JLabel jLabelFallos;
    private javax.swing.JPanel jPanel1;
    // End of variables declaration//GEN-END:variables

    public static void main(String args[]) {
        /* Set the Nimbus look and feel */
        try {
            for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
                if ("Nimbus".equals(info.getName())) {
                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        } catch (ClassNotFoundException ex) {
            java.util.logging.Logger.getLogger(Verdadero_Falso.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(Verdadero_Falso.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(Verdadero_Falso.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(Verdadero_Falso.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }

        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                GUI_Principal guiPrincipal = new GUI_Principal();
                Verdadero_Falso vf = new Verdadero_Falso(guiPrincipal);
                guiPrincipal.setVisible(true);
            }
        });
    }

}
