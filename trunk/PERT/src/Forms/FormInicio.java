/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

/*
 * FormInicio.java
 *
 * Created on 02/03/2011, 05:14:32
 */

package Forms;

import Entidades.Proyecto;
import java.awt.Component;
import java.util.ArrayList;
import java.util.List;
import java.util.Locale;
import javax.swing.DefaultListModel;
import javax.swing.JList;

/**
 *
 * @author Usuario
 */
public class FormInicio extends javax.swing.JFrame {

    /** Creates new form FormInicio */
    public FormInicio(String paisIdioma, String lenguajeIdioma) {
        lugarConfigurado = new Locale(paisIdioma, lenguajeIdioma);
        initComponents();
        setearEtiquetas();
        lstProyectos = new JList(new DefaultListModel());
        obtenerProyectosAlmacenados();
    }

    private Locale lugarConfigurado;
    private List<Proyecto> listaDeProyectos;

    private void setearEtiquetas(){
        this.setTitle("Módulo PERT"); // Manejo de idioma!!!
        this.lblListaDeProyectos.setText("Lista de proyectos almacenados:"); // Manejo de idioma!!!
        this.btnNuevo.setText("Nuevo"); // Manejo de idioma!!!
        this.btnAbrir.setText("Abrir"); // Manejo de idioma!!!
        this.btnBorrar.setText("Borrar"); // Manejo de idioma!!!
        this.btnSalir.setText("Salir"); // Manejo de idioma!!!
    }

    private void obtenerProyectosAlmacenados(){
        listaDeProyectos = new ArrayList<Proyecto>();
        for(int i = 0; i < listaDeProyectos.size(); i++){
            Proyecto p = listaDeProyectos.get(i);
            lstProyectos.add(null, p); 
        }
        lstProyectos.updateUI();
        // Lógica de acceso a los archivos.
    }

    public void agregarProyectoEnLista(Proyecto p){
        //lstProyectos.add(p);
        lstProyectos.updateUI();
    }

    /** This method is called from within the constructor to
     * initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is
     * always regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        btnNuevo = new javax.swing.JButton();
        btnAbrir = new javax.swing.JButton();
        btnBorrar = new javax.swing.JButton();
        jScrollPane1 = new javax.swing.JScrollPane();
        lstProyectos = new javax.swing.JList();
        lblListaDeProyectos = new javax.swing.JLabel();
        btnSalir = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        btnNuevo.setText("btnNuevo");
        btnNuevo.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnNuevoActionPerformed(evt);
            }
        });

        btnAbrir.setText("btnAbrir");
        btnAbrir.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnAbrirActionPerformed(evt);
            }
        });

        btnBorrar.setText("btnBorrar");
        btnBorrar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnBorrarActionPerformed(evt);
            }
        });

        lstProyectos.setSelectionMode(javax.swing.ListSelectionModel.SINGLE_SELECTION);
        jScrollPane1.setViewportView(lstProyectos);

        lblListaDeProyectos.setText("lblListadoDeProyectos");

        btnSalir.setText("btnSalir");
        btnSalir.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnSalirActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(lblListaDeProyectos)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                        .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 264, Short.MAX_VALUE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(btnNuevo, javax.swing.GroupLayout.DEFAULT_SIZE, 80, Short.MAX_VALUE)
                            .addComponent(btnAbrir, javax.swing.GroupLayout.DEFAULT_SIZE, 80, Short.MAX_VALUE)
                            .addComponent(btnBorrar, javax.swing.GroupLayout.DEFAULT_SIZE, 80, Short.MAX_VALUE)
                            .addComponent(btnSalir, javax.swing.GroupLayout.PREFERRED_SIZE, 80, javax.swing.GroupLayout.PREFERRED_SIZE))))
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(lblListaDeProyectos)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(btnNuevo)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(btnAbrir)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(btnBorrar))
                    .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 156, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(btnSalir)
                .addContainerGap())
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void btnNuevoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnNuevoActionPerformed
        FormProyecto fp = new FormProyecto(this, listaDeProyectos);
        fp.setVisible(true);
    }//GEN-LAST:event_btnNuevoActionPerformed

    private void btnAbrirActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnAbrirActionPerformed
        Proyecto p = listaDeProyectos.get(lstProyectos.getSelectedIndex());
        FormProyecto fp = new FormProyecto(this, listaDeProyectos, p);
        fp.setVisible(true);
    }//GEN-LAST:event_btnAbrirActionPerformed

    private void btnBorrarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnBorrarActionPerformed
        int i = lstProyectos.getSelectedIndex();
        listaDeProyectos.remove(i);
        lstProyectos.remove(i);
    }//GEN-LAST:event_btnBorrarActionPerformed

    private void btnSalirActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnSalirActionPerformed
        this.dispose();
    }//GEN-LAST:event_btnSalirActionPerformed

    // Este main se deja sin efecto dado que el inicio del programa se maneja desde la clase pert/Main.java
    /**
    * @param args the command line arguments
    */
    /*public static void main(String args[]) {
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new FormInicio().setVisible(true);
            }
        });
    }*/

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnAbrir;
    private javax.swing.JButton btnBorrar;
    private javax.swing.JButton btnNuevo;
    private javax.swing.JButton btnSalir;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JLabel lblListaDeProyectos;
    private javax.swing.JList lstProyectos;
    // End of variables declaration//GEN-END:variables

}
