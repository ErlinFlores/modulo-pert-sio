/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

/*
 * VentanaAcercaDe.java
 *
 * Created on 21/09/2011, 00:26:15
 */
package Ventanas;

import java.util.ResourceBundle;

/**
 *
 * @author Manuel Lorenze
 */
public class VentanaAcercaDe extends javax.swing.JDialog {

    private ResourceBundle etiquetas;
    
    /** Creates new form VentanaAcercaDe */
    public VentanaAcercaDe(java.awt.Frame parent, boolean modal, ResourceBundle etiquetas) {
        super(parent, modal);
        initComponents();
        this.etiquetas = etiquetas;
        setearEtiquetas();
    }
    
    private void setearEtiquetas(){
        this.setTitle(etiquetas.getString("acercaDeTitulo"));
        this.label_Titulo.setText(etiquetas.getString("acercaDeLabelTitulo"));
        this.label_Herramienta.setText(etiquetas.getString("acercaDeLabelHerramienta"));
        this.label_version.setText(etiquetas.getString("acercaDeVersion"));
        this.label_Desarrollado.setText(etiquetas.getString("acercaDeLabelDesarrollado"));
        this.label_Tutoriado.setText(etiquetas.getString("acercaDeLabelTutoriado"));
        this.boton_Cerrar.setText(etiquetas.getString("acercaDeBotonCerrar"));
    }

    /** This method is called from within the constructor to
     * initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is
     * always regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        label_Titulo = new javax.swing.JLabel();
        boton_Cerrar = new javax.swing.JButton();
        label_Herramienta = new javax.swing.JLabel();
        label_Desarrollado = new javax.swing.JLabel();
        label_Tutoriado = new javax.swing.JLabel();
        label_version = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        setResizable(false);

        label_Titulo.setFont(new java.awt.Font("Comic Sans MS", 1, 18));
        label_Titulo.setText("MÓDULO PERT - INVESTIGACIÓN OPERATIVA");

        boton_Cerrar.setText("Cerrar");
        boton_Cerrar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                boton_CerrarActionPerformed(evt);
            }
        });

        label_Herramienta.setText("Aplicación propiedad de la Universidad Católica del Uruguay");

        label_Desarrollado.setText("Desarrollado por: Lic. Manuel Lorenze (manuel.lorenze@gmail.com)");

        label_Tutoriado.setText("Tutoriado por: Ing. Daniel Paolillo");

        label_version.setForeground(new java.awt.Color(0, 0, 255));
        label_version.setText("Version 1.2 beta");

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(label_Titulo)
                    .addComponent(label_Herramienta)
                    .addComponent(label_Desarrollado)
                    .addComponent(label_Tutoriado))
                .addContainerGap(12, Short.MAX_VALUE))
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addContainerGap(384, Short.MAX_VALUE)
                .addComponent(boton_Cerrar)
                .addContainerGap())
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(label_version)
                .addContainerGap(368, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(label_Titulo)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(label_Herramienta)
                .addGap(18, 18, 18)
                .addComponent(label_version)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 26, Short.MAX_VALUE)
                .addComponent(label_Desarrollado)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(label_Tutoriado)
                .addGap(18, 18, 18)
                .addComponent(boton_Cerrar)
                .addContainerGap())
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void boton_CerrarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_boton_CerrarActionPerformed
        this.dispose();
}//GEN-LAST:event_boton_CerrarActionPerformed

    // Este main se deja sin efecto dado que el inicio del programa se maneja desde la clase pert/Main.java
    /**
     * @param args the command line arguments
     */
    /*public static void main(String args[]) {
        java.awt.EventQueue.invokeLater(new Runnable() {

            public void run() {
                VentanaAcercaDe dialog = new VentanaAcercaDe(new javax.swing.JFrame(), true);
                dialog.addWindowListener(new java.awt.event.WindowAdapter() {

                    public void windowClosing(java.awt.event.WindowEvent e) {
                        System.exit(0);
                    }
                });
                dialog.setVisible(true);
            }
        });
    }*/
    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton boton_Cerrar;
    private javax.swing.JLabel label_Desarrollado;
    private javax.swing.JLabel label_Herramienta;
    private javax.swing.JLabel label_Titulo;
    private javax.swing.JLabel label_Tutoriado;
    private javax.swing.JLabel label_version;
    // End of variables declaration//GEN-END:variables
}
