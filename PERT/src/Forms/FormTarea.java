/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

/*
 * FormTarea.java
 *
 * Created on 02/03/2011, 13:18:42
 */

package Forms;

import Entidades.*;
import java.util.List;
import javax.swing.JOptionPane;

/**
 *
 * @author Usuario
 */
public class FormTarea extends javax.swing.JFrame {

    /** Creates new form FormTarea */
    public FormTarea(Proyecto p) { // Para cuando se quiere crear una nueva tarea.
        initComponents();
        motivo = Accion.crear;
        tarea = null;
    }

    public FormTarea(Tarea t) { // Para cuando se quiere modificar una tarea existente.
        initComponents();
        motivo = Accion.modificar;
        tarea = t;
        proyecto = null;
    }

    private Accion motivo;
    private Proyecto proyecto; // Proyecto al que pertenece la tarea que se gestiona en este form.
    private Tarea tarea; // La tarea que se gestiona en este form al ser utilizado.
    private String descripcionTarea;
    private int tiempoOptimista;
    private int tiempoMasProbable;
    private int tiempoPesimista;
    private List<Tarea> tareasPredecesoras;
    

    private boolean controlarDatosDeEntradaDelUsuario(){
        descripcionTarea = txtDescripcionTarea.getText();
        if (descripcionTarea.equals("")){
            return false;
        }
        tiempoOptimista = Integer.parseInt(txtTiempoOptimista.getText());
        tiempoMasProbable = Integer.parseInt(txtTiempoMasProbable.getText());
        tiempoPesimista = Integer.parseInt(txtTiempoPesimista.getText());
        if (!((tiempoOptimista > 0) && (tiempoOptimista < tiempoMasProbable) && (tiempoMasProbable < tiempoPesimista) && (tiempoPesimista < 256))){
            return false;
        }
        return true;
    }

    /** This method is called from within the constructor to
     * initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is
     * always regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        lblIdTarea = new javax.swing.JLabel();
        txtIdTarea = new javax.swing.JTextField();
        jLabel1 = new javax.swing.JLabel();
        txtDescripcionTarea = new javax.swing.JTextField();
        jPanel1 = new javax.swing.JPanel();
        jLabel2 = new javax.swing.JLabel();
        txtTiempoOptimista = new javax.swing.JTextField();
        lblTiempoMasProbable = new javax.swing.JLabel();
        txtTiempoMasProbable = new javax.swing.JTextField();
        jLabel3 = new javax.swing.JLabel();
        txtTiempoPesimista = new javax.swing.JTextField();
        jPanel2 = new javax.swing.JPanel();
        jScrollPane1 = new javax.swing.JScrollPane();
        tblPrecedencia = new javax.swing.JTable();
        jScrollPane2 = new javax.swing.JScrollPane();
        tblPosiblesPrecedencias = new javax.swing.JTable();
        btnAgregarPrecedencia = new javax.swing.JButton();
        btnSacarPrecedencia = new javax.swing.JButton();
        lblTareasPrecedentes = new javax.swing.JLabel();
        lblTareasDisponiblesComoPrecedencia = new javax.swing.JLabel();
        btnAceptar = new javax.swing.JButton();
        btnCancelar = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);

        lblIdTarea.setText("lblIdTarea");

        txtIdTarea.setEditable(false);
        txtIdTarea.setFont(new java.awt.Font("Tahoma", 1, 11));
        txtIdTarea.setHorizontalAlignment(javax.swing.JTextField.CENTER);

        jLabel1.setText("lblDescripcionTarea");

        jPanel1.setBorder(javax.swing.BorderFactory.createTitledBorder("lblTiemposEstimados"));

        jLabel2.setText("lblTiempoOptimista");

        lblTiempoMasProbable.setText("lblTiempoMasProbable");

        jLabel3.setText("lblTiempoPesimista");

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel2)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(txtTiempoOptimista, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(lblTiempoMasProbable)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(txtTiempoMasProbable, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(jLabel3)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(txtTiempoPesimista, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(14, Short.MAX_VALUE))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel2)
                    .addComponent(txtTiempoOptimista, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(lblTiempoMasProbable)
                    .addComponent(txtTiempoMasProbable, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel3)
                    .addComponent(txtTiempoPesimista, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        jPanel2.setBorder(javax.swing.BorderFactory.createTitledBorder("lblPrecedencia"));

        tblPrecedencia.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null}
            },
            new String [] {
                "ID", "Descripción"
            }
        ) {
            Class[] types = new Class [] {
                java.lang.String.class, java.lang.String.class
            };
            boolean[] canEdit = new boolean [] {
                false, false
            };

            public Class getColumnClass(int columnIndex) {
                return types [columnIndex];
            }

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        jScrollPane1.setViewportView(tblPrecedencia);

        tblPosiblesPrecedencias.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null}
            },
            new String [] {
                "ID", "Descripción"
            }
        ) {
            Class[] types = new Class [] {
                java.lang.String.class, java.lang.String.class
            };
            boolean[] canEdit = new boolean [] {
                false, false
            };

            public Class getColumnClass(int columnIndex) {
                return types [columnIndex];
            }

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        jScrollPane2.setViewportView(tblPosiblesPrecedencias);

        btnAgregarPrecedencia.setText("<");
        btnAgregarPrecedencia.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnAgregarPrecedenciaActionPerformed(evt);
            }
        });

        btnSacarPrecedencia.setText(">");
        btnSacarPrecedencia.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnSacarPrecedenciaActionPerformed(evt);
            }
        });

        lblTareasPrecedentes.setText("lblTareasPrecedentes");

        lblTareasDisponiblesComoPrecedencia.setText("lblTareasDisponiblesComoPrecedencias");

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 202, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(10, 10, 10)
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(btnAgregarPrecedencia, javax.swing.GroupLayout.PREFERRED_SIZE, 48, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(btnSacarPrecedencia, javax.swing.GroupLayout.PREFERRED_SIZE, 48, javax.swing.GroupLayout.PREFERRED_SIZE)))
                    .addComponent(lblTareasPrecedentes))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 10, Short.MAX_VALUE)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 202, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(lblTareasDisponiblesComoPrecedencia))
                .addContainerGap())
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(lblTareasPrecedentes)
                    .addComponent(lblTareasDisponiblesComoPrecedencia))
                .addGap(8, 8, 8)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addGap(32, 32, 32)
                        .addComponent(btnAgregarPrecedencia)
                        .addGap(18, 18, 18)
                        .addComponent(btnSacarPrecedencia))
                    .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 118, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 118, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        btnAceptar.setText("btnAceptar");
        btnAceptar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnAceptarActionPerformed(evt);
            }
        });

        btnCancelar.setText("btnCancelar");
        btnCancelar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnCancelarActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jPanel2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(lblIdTarea)
                        .addGap(10, 10, 10)
                        .addComponent(txtIdTarea, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addComponent(jLabel1)
                        .addGap(10, 10, 10)
                        .addComponent(txtDescripcionTarea, javax.swing.GroupLayout.PREFERRED_SIZE, 200, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                        .addComponent(btnAceptar, javax.swing.GroupLayout.PREFERRED_SIZE, 80, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(btnCancelar, javax.swing.GroupLayout.PREFERRED_SIZE, 80, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(txtIdTarea, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel1)
                    .addComponent(txtDescripcionTarea, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(lblIdTarea))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jPanel2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(btnCancelar)
                    .addComponent(btnAceptar))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void btnAgregarPrecedenciaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnAgregarPrecedenciaActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_btnAgregarPrecedenciaActionPerformed

    private void btnSacarPrecedenciaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnSacarPrecedenciaActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_btnSacarPrecedenciaActionPerformed

    private void btnAceptarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnAceptarActionPerformed
        try{
            if (controlarDatosDeEntradaDelUsuario()){
                String dt = txtDescripcionTarea.getText();
                Tiempo t = new Tiempo(Integer.parseInt(txtTiempoOptimista.getText()), Integer.parseInt(txtTiempoMasProbable.getText()), Integer.parseInt(txtTiempoPesimista.getText()));
                switch (motivo){
                    case crear:                      
                        tarea = new Tarea(Byte.parseByte(txtIdTarea.getText()), dt, t, null);
                        proyecto.agregarTarea(tarea);
                        break;
                    case modificar:
                        tarea.setDescripcion(txtDescripcionTarea.getText());
                        tarea.setTiempos(t);
                        tarea.setPrecedencias(null);
                        break;
                }
                this.dispose();
            }else{
                JOptionPane.showMessageDialog(this, "ERROR: Hay campos con valores no válidos.");
            }
        }catch(Exception ex){
            JOptionPane.showMessageDialog(this, ex);
        }
    }//GEN-LAST:event_btnAceptarActionPerformed

    private void btnCancelarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnCancelarActionPerformed
        this.dispose();
    }//GEN-LAST:event_btnCancelarActionPerformed

    // Este main se deja sin efecto dado que el inicio del programa se maneja desde la clase pert/Main.java
    /**
    * @param args the command line arguments
    */
    /*public static void main(String args[]) {
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new FormTarea().setVisible(true);
            }
        });
    }*/

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnAceptar;
    private javax.swing.JButton btnAgregarPrecedencia;
    private javax.swing.JButton btnCancelar;
    private javax.swing.JButton btnSacarPrecedencia;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JLabel lblIdTarea;
    private javax.swing.JLabel lblTareasDisponiblesComoPrecedencia;
    private javax.swing.JLabel lblTareasPrecedentes;
    private javax.swing.JLabel lblTiempoMasProbable;
    private javax.swing.JTable tblPosiblesPrecedencias;
    private javax.swing.JTable tblPrecedencia;
    private javax.swing.JTextField txtDescripcionTarea;
    private javax.swing.JTextField txtIdTarea;
    private javax.swing.JTextField txtTiempoMasProbable;
    private javax.swing.JTextField txtTiempoOptimista;
    private javax.swing.JTextField txtTiempoPesimista;
    // End of variables declaration//GEN-END:variables

}
