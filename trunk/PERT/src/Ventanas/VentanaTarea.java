/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

/*
 * VentanaTarea.java
 *
 * Created on 21/09/2011, 00:45:26
 */
package Ventanas;

import Entidades.Accion;
import Entidades.FabricaDeTareas;
import Entidades.Precedencia;
import Entidades.Tarea;
import Entidades.TiempoEstimado;
import java.util.ArrayList;
import java.util.List;
import javax.swing.JOptionPane;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;

/**
 *
 * @author Manuel Lorenze
 */
public class VentanaTarea extends javax.swing.JDialog {

    private VentanaProyecto formularioProyecto;
    private FabricaDeTareas fabricaDeTareas = FabricaDeTareas.getInstance();
    private List<Tarea> posiblesTareasPrecedentes;
    private Accion tipoAccion;
    private int id;
    private String nombre;
    private String descripcion;
    private TiempoEstimado tiemposEstimados;
    private Precedencia tareasPrecedentes;
    
    /** Creates new form VentanaTarea */
    public VentanaTarea(java.awt.Frame parent, boolean modal) {
        super(parent, modal);
        initComponents();
        this.formularioProyecto = (VentanaProyecto)parent;        
        this.tipoAccion = Accion.crear;
        this.id = -1;
        this.nombre = fabricaDeTareas.getNombreDeProximaTarea();
        this.descripcion = "";
        this.tiemposEstimados = null;
        this.tareasPrecedentes = new Precedencia(new ArrayList<Tarea>());
        this.posiblesTareasPrecedentes = new ArrayList<Tarea>(formularioProyecto.obtenerListaDeTareasDelProyecto());
        setearDatosDeTarea();
    }

     /** Creates new form VentanaTarea */
    public VentanaTarea(java.awt.Frame parent, boolean modal, Tarea tarea) {
        super(parent, modal);
        initComponents();
        this.formularioProyecto = (VentanaProyecto)parent;        
        this.tipoAccion = Accion.modificar;
        this.id = tarea.obtenerId();
        this.nombre = tarea.obtenerNombre();
        this.descripcion = tarea.obtenerDescripcion();
        this.tiemposEstimados = tarea.obtenerTiempoEstimado();
        this.tareasPrecedentes = tarea.obtenerPrecedencia();
        this.posiblesTareasPrecedentes = formularioProyecto.obtenerPosiblesTareasPrecedentes(tarea);
        setearDatosDeTarea();
    }
    
    /**
     * Se setean las etiquetas de la pantalla.
     */
    private void setearEtiquetas(){
        setTitle("Tarea"); // Manejo de idioma!!!
        this.lblIdTarea.setText("Nombre: ");
        this.lblTareasDisponiblesComoPrecedencia.setText("Tareas disponibles");
        this.lblTareasPrecedentes.setText("Tareas precedentes");
        this.lblDescripciónTarea.setText("Descripción de la tarea: ");
        this.lblTiempoOptimista.setText("Tiempo optimista");
        this.lblTiempoMasProbable.setText("Tiempo más probable");
        this.lblTiempoPesimista.setText("Tiempo pesimista");
        this.btnGuardar.setText("Guardar");
        this.btnCancelar.setText("Cancelar");
    }
    
    private boolean controlarDatosDeEntradaDelUsuario(){
        if (txtDescripcionTarea.getText().equals("")){
            return false;
        }
        double to = Double.parseDouble(txtTiempoOptimista.getText());
        double tmp = Double.parseDouble(txtTiempoMasProbable.getText());
        double tp = Double.parseDouble(txtTiempoPesimista.getText());
        if (!((to > 0) && (to < tmp) && (tmp < tp) && (tp < 256))){
            return false;
        }
        return true;
    }
    
    private void setearDatosDeTarea(){
        int indiceFila = 0;        
        for (Tarea tarea : posiblesTareasPrecedentes){      
            modificarTabla(tblPosiblesPrecedencias, indiceFila, true, tarea);
            indiceFila += 1;
        }
        txtNombreTarea.setText(nombre);
        if (tipoAccion == Accion.modificar){
            indiceFila = 0;
            for (Tarea tarea : tareasPrecedentes.obtenerTareas()){
                modificarTabla(tblPrecedencia, indiceFila, true, tarea);
                indiceFila += 1;
            }            
            txtDescripcionTarea.setText(descripcion);
            txtTiempoOptimista.setText(Double.toString(tiemposEstimados.obtenerTiempoOptimista()));
            txtTiempoMasProbable.setText(Double.toString(tiemposEstimados.obtenerTiempoMasProbable()));
            txtTiempoPesimista.setText(Double.toString(tiemposEstimados.obtenerTiempoPesimista()));
        }
    }
    
    private void modificarTabla(JTable tabla, int fila, boolean nuevaFila, Tarea tarea){
        DefaultTableModel modeloTabla = (DefaultTableModel)tabla.getModel();
        if (nuevaFila){
            modeloTabla.addRow(new Object[fila]);
            tabla.setValueAt(tarea.obtenerNombre(), fila, 0);
            tabla.setValueAt(tarea.obtenerDescripcion(), fila, 1);
        }else{
            modeloTabla.removeRow(fila);
        }      
        tabla.updateUI();
    }  
    
    private Tarea quitarTareaDePosiblesPrecedenciasSegunSeleccion(int filaSeleccionada){
        String nombreTarea = (String)tblPosiblesPrecedencias.getValueAt(filaSeleccionada, 0);
        int idTarea = fabricaDeTareas.getIdTareaByNombre(nombreTarea);
        for (Tarea tarea : posiblesTareasPrecedentes){
            if (tarea.obtenerId() == idTarea){
                Tarea nuevaTareaPrecedente = tarea;
                posiblesTareasPrecedentes.remove(tarea);
                modificarTabla(tblPosiblesPrecedencias, filaSeleccionada, false, null);
                return nuevaTareaPrecedente;
            }
        }
        return null;
    }
    
    private Tarea quitarTareaDePrecedenciasSegunSeleccion(int filaSeleccionada){
        String nombreTarea = (String)tblPrecedencia.getValueAt(filaSeleccionada, 0);
        int idTarea = fabricaDeTareas.getIdTareaByNombre(nombreTarea);
        Tarea nuevaPosibleTareaPrecedente = tareasPrecedentes.obtenerTareaPorID(idTarea);
        tareasPrecedentes.borrarTarea(nuevaPosibleTareaPrecedente);
        modificarTabla(tblPrecedencia, filaSeleccionada, false, null);
        return nuevaPosibleTareaPrecedente;
    }
    
    /** This method is called from within the constructor to
     * initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is
     * always regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        txtNombreTarea = new javax.swing.JTextField();
        lblIdTarea = new javax.swing.JLabel();
        jPanelTiemposEstimados = new javax.swing.JPanel();
        lblTiempoOptimista = new javax.swing.JLabel();
        lblTiempoMasProbable = new javax.swing.JLabel();
        txtTiempoMasProbable = new javax.swing.JTextField();
        lblTiempoPesimista = new javax.swing.JLabel();
        txtTiempoPesimista = new javax.swing.JTextField();
        txtTiempoOptimista = new javax.swing.JTextField();
        btnCancelar = new javax.swing.JButton();
        txtDescripcionTarea = new javax.swing.JTextField();
        btnGuardar = new javax.swing.JButton();
        jPanelPrecedencias = new javax.swing.JPanel();
        jScrollPane1 = new javax.swing.JScrollPane();
        tblPrecedencia = new javax.swing.JTable();
        jScrollPane2 = new javax.swing.JScrollPane();
        tblPosiblesPrecedencias = new javax.swing.JTable();
        btnAgregarPrecedencia = new javax.swing.JButton();
        btnSacarPrecedencia = new javax.swing.JButton();
        lblTareasPrecedentes = new javax.swing.JLabel();
        lblTareasDisponiblesComoPrecedencia = new javax.swing.JLabel();
        lblDescripciónTarea = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);

        txtNombreTarea.setEditable(false);
        txtNombreTarea.setFont(new java.awt.Font("Tahoma", 1, 11));
        txtNombreTarea.setHorizontalAlignment(javax.swing.JTextField.CENTER);

        lblIdTarea.setText("lblNombreTarea");

        jPanelTiemposEstimados.setBorder(javax.swing.BorderFactory.createTitledBorder("Tiempos estimados"));

        lblTiempoOptimista.setText("lblTiempoOptimista");

        lblTiempoMasProbable.setText("lblTiempoMasProbable");

        txtTiempoMasProbable.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        txtTiempoMasProbable.setText("2");

        lblTiempoPesimista.setText("lblTiempoPesimista");

        txtTiempoPesimista.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        txtTiempoPesimista.setText("3");

        txtTiempoOptimista.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        txtTiempoOptimista.setText("1");

        javax.swing.GroupLayout jPanelTiemposEstimadosLayout = new javax.swing.GroupLayout(jPanelTiemposEstimados);
        jPanelTiemposEstimados.setLayout(jPanelTiemposEstimadosLayout);
        jPanelTiemposEstimadosLayout.setHorizontalGroup(
            jPanelTiemposEstimadosLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanelTiemposEstimadosLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(lblTiempoOptimista)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(txtTiempoOptimista, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(lblTiempoMasProbable)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(txtTiempoMasProbable, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(lblTiempoPesimista)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(txtTiempoPesimista, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        jPanelTiemposEstimadosLayout.setVerticalGroup(
            jPanelTiemposEstimadosLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanelTiemposEstimadosLayout.createSequentialGroup()
                .addGroup(jPanelTiemposEstimadosLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(lblTiempoOptimista)
                    .addComponent(lblTiempoMasProbable)
                    .addComponent(txtTiempoMasProbable, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(lblTiempoPesimista)
                    .addComponent(txtTiempoPesimista, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(txtTiempoOptimista, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(12, Short.MAX_VALUE))
        );

        btnCancelar.setText("btnCancelar");
        btnCancelar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnCancelarActionPerformed(evt);
            }
        });

        txtDescripcionTarea.setText("Tarea");

        btnGuardar.setText("btnGuardar");
        btnGuardar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnGuardarActionPerformed(evt);
            }
        });

        jPanelPrecedencias.setBorder(javax.swing.BorderFactory.createTitledBorder("Precedencias"));

        tblPrecedencia.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "Nombre", "Descripción"
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

            },
            new String [] {
                "Nombre", "Descripción"
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

        javax.swing.GroupLayout jPanelPrecedenciasLayout = new javax.swing.GroupLayout(jPanelPrecedencias);
        jPanelPrecedencias.setLayout(jPanelPrecedenciasLayout);
        jPanelPrecedenciasLayout.setHorizontalGroup(
            jPanelPrecedenciasLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanelPrecedenciasLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanelPrecedenciasLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanelPrecedenciasLayout.createSequentialGroup()
                        .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 202, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(10, 10, 10)
                        .addGroup(jPanelPrecedenciasLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(btnAgregarPrecedencia, javax.swing.GroupLayout.PREFERRED_SIZE, 48, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(btnSacarPrecedencia, javax.swing.GroupLayout.PREFERRED_SIZE, 48, javax.swing.GroupLayout.PREFERRED_SIZE)))
                    .addComponent(lblTareasPrecedentes))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanelPrecedenciasLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 202, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(lblTareasDisponiblesComoPrecedencia))
                .addContainerGap())
        );
        jPanelPrecedenciasLayout.setVerticalGroup(
            jPanelPrecedenciasLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanelPrecedenciasLayout.createSequentialGroup()
                .addGroup(jPanelPrecedenciasLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(lblTareasPrecedentes)
                    .addComponent(lblTareasDisponiblesComoPrecedencia))
                .addGap(8, 8, 8)
                .addGroup(jPanelPrecedenciasLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanelPrecedenciasLayout.createSequentialGroup()
                        .addGap(32, 32, 32)
                        .addComponent(btnAgregarPrecedencia)
                        .addGap(18, 18, 18)
                        .addComponent(btnSacarPrecedencia))
                    .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 118, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 118, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        lblDescripciónTarea.setText("lblDescripcionTarea");

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 530, Short.MAX_VALUE)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(lblIdTarea)
                        .addGap(10, 10, 10)
                        .addComponent(txtNombreTarea, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addComponent(lblDescripciónTarea)
                        .addGap(10, 10, 10)
                        .addComponent(txtDescripcionTarea, javax.swing.GroupLayout.PREFERRED_SIZE, 265, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                        .addComponent(btnGuardar)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(btnCancelar))
                    .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                        .addComponent(jPanelTiemposEstimados, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(jPanelPrecedencias, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 332, Short.MAX_VALUE)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(txtNombreTarea, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(lblDescripciónTarea)
                    .addComponent(txtDescripcionTarea, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(lblIdTarea))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jPanelTiemposEstimados, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jPanelPrecedencias, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(btnCancelar)
                    .addComponent(btnGuardar))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void btnCancelarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnCancelarActionPerformed
        this.dispose();
}//GEN-LAST:event_btnCancelarActionPerformed

    private void btnGuardarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnGuardarActionPerformed
        try{
            if (controlarDatosDeEntradaDelUsuario()){
                descripcion = txtDescripcionTarea.getText();
                double tiempoOptimista = Double.parseDouble(txtTiempoOptimista.getText());
                double tiempoMasProbable = Double.parseDouble(txtTiempoMasProbable.getText());
                double tiempoPesimista = Double.parseDouble(txtTiempoPesimista.getText());
                switch (tipoAccion){
                    case crear:
                        tiemposEstimados = new TiempoEstimado(tiempoOptimista, tiempoMasProbable, tiempoPesimista);
                        Tarea nuevaTarea = fabricaDeTareas.crearTarea(descripcion, tiemposEstimados, tareasPrecedentes);
                        formularioProyecto.agregarTarea(nuevaTarea);
                        break;
                    case modificar:
                        tiemposEstimados.setearTiempoEstimado(tiempoOptimista, tiempoMasProbable, tiempoPesimista);
                        formularioProyecto.modificarTarea(id, descripcion);
                        break;
                }
                this.dispose();
            }else{
                JOptionPane.showMessageDialog(this, "ERROR: Hay campos con valores no válidos.");
            }
        }catch(Exception ex){
            JOptionPane.showMessageDialog(this, ex);
        }
}//GEN-LAST:event_btnGuardarActionPerformed

    private void btnAgregarPrecedenciaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnAgregarPrecedenciaActionPerformed
        int filaSeleccionada = tblPosiblesPrecedencias.getSelectedRow();
        if (filaSeleccionada != -1){
            Tarea nuevaTareaPrecedente = quitarTareaDePosiblesPrecedenciasSegunSeleccion(filaSeleccionada);
            int nuevaFila = tareasPrecedentes.obtenerCantidadDeTareas();
            tareasPrecedentes.agregarTarea(nuevaTareaPrecedente);
            modificarTabla(tblPrecedencia, nuevaFila, true, nuevaTareaPrecedente);
        }
}//GEN-LAST:event_btnAgregarPrecedenciaActionPerformed

    private void btnSacarPrecedenciaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnSacarPrecedenciaActionPerformed
        int filaSeleccionada = tblPrecedencia.getSelectedRow();
        if (filaSeleccionada != -1){
            Tarea nuevaPosibleTareaPrecedente = quitarTareaDePrecedenciasSegunSeleccion(filaSeleccionada);
            int nuevaFila = posiblesTareasPrecedentes.size();
            posiblesTareasPrecedentes.add(nuevaPosibleTareaPrecedente);
            modificarTabla(tblPosiblesPrecedencias, nuevaFila, true, nuevaPosibleTareaPrecedente);
        }
}//GEN-LAST:event_btnSacarPrecedenciaActionPerformed

    // Este main se deja sin efecto dado que el inicio del programa se maneja desde la clase pert/Main.java
    /**
     * @param args the command line arguments
     */
    /*public static void main(String args[]) {
        java.awt.EventQueue.invokeLater(new Runnable() {

            public void run() {
                VentanaTarea dialog = new VentanaTarea(new javax.swing.JFrame(), true);
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
    private javax.swing.JButton btnAgregarPrecedencia;
    private javax.swing.JButton btnCancelar;
    private javax.swing.JButton btnGuardar;
    private javax.swing.JButton btnSacarPrecedencia;
    private javax.swing.JPanel jPanelPrecedencias;
    private javax.swing.JPanel jPanelTiemposEstimados;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JLabel lblDescripciónTarea;
    private javax.swing.JLabel lblIdTarea;
    private javax.swing.JLabel lblTareasDisponiblesComoPrecedencia;
    private javax.swing.JLabel lblTareasPrecedentes;
    private javax.swing.JLabel lblTiempoMasProbable;
    private javax.swing.JLabel lblTiempoOptimista;
    private javax.swing.JLabel lblTiempoPesimista;
    private javax.swing.JTable tblPosiblesPrecedencias;
    private javax.swing.JTable tblPrecedencia;
    private javax.swing.JTextField txtDescripcionTarea;
    private javax.swing.JTextField txtNombreTarea;
    private javax.swing.JTextField txtTiempoMasProbable;
    private javax.swing.JTextField txtTiempoOptimista;
    private javax.swing.JTextField txtTiempoPesimista;
    // End of variables declaration//GEN-END:variables
}