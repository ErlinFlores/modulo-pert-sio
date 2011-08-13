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
import java.util.ArrayList;
import java.util.List;
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;

/**
 *
 * @author Manuel Lorenze
 */
public class FormTarea extends javax.swing.JFrame {

    private FormProyecto formularioProyecto;
    private List<Tarea> posiblesTareasPrecedentes;
    private Accion tipoAccion;
    private String nombre;
    private String descripcion;
    private TiempoEstimado tiemposEstimados;
    private Precedencia tareasPrecedentes;
    
    /** Creates new form FormTarea */
    public FormTarea(FormProyecto formularioProyecto) { // Para cuando se quiere crear una nueva tarea.
        initComponents();
        setearEtiquetas();
        this.formularioProyecto = formularioProyecto;        
        this.posiblesTareasPrecedentes = new ArrayList<Tarea>(formularioProyecto.obtenerListaDeTareasDelProyecto());
        this.tipoAccion = Accion.crear;
        this.nombre = "";
        this.descripcion = "";
        this.tiemposEstimados = null;
        this.tareasPrecedentes = new Precedencia(new ArrayList<Tarea>());
        setearDatosDeTarea();
    }

    public FormTarea(FormProyecto formularioProyecto, Tarea tarea) { // Para cuando se quiere modificar una tarea existente.
        initComponents();
        setearEtiquetas();
        this.formularioProyecto = formularioProyecto;
        this.posiblesTareasPrecedentes = obtenerPosiblesTareasPrecedentes(tarea, formularioProyecto.obtenerListaDeTareasDelProyecto());
        this.tipoAccion = Accion.modificar;
        this.nombre = "";
        this.descripcion = tarea.getDescripcion();
        this.tiemposEstimados = tarea.getTiempoEstimado();
        this.tareasPrecedentes = tarea.getPrecedencia();
        setearDatosDeTarea();
    }    

    private List<Tarea> obtenerPosiblesTareasPrecedentes(Tarea tareaEnEdicion, List<Tarea> tareasDelProyecto){
        List<Tarea> posiblesTareas = new ArrayList<Tarea>();
        for (Tarea tarea : tareasDelProyecto){
            int idTarea = tarea.getId();
            if ((!tareasPrecedentes.esPrecedente(idTarea)) && (idTarea != tareaEnEdicion.getId())){
                if (!hayCamino(tareaEnEdicion, tarea)){
                    posiblesTareas.add(tarea);
                }
            }
        }       
        return posiblesTareas;
    }
    
    private boolean hayCamino(Tarea tareaInicio, Tarea tareaDestino){
        Precedencia
        
        return false;
    }
    
    /**
     * Se setean las etiquetas de la pantalla.
     */
    private void setearEtiquetas(){
        setTitle("Tarea"); // Manejo de idioma!!!
        this.lblIdTarea.setText("Id: ");
        this.lblTareasDisponiblesComoPrecedencia.setText("Tareas disponibles");
        this.lblTareasPrecedentes.setText("Tareas precedentes");
        this.lblDescripciónTarea.setText("Descripción de la tarea: ");
        this.lblTiempoOptimista.setText("Tiempo optimista");
        this.lblTiempoMasProbable.setText("Tiempo más probable");
        this.lblTiempoPesimista.setText("Tiempo pesimista");
        this.btnGuardar.setText("Guardar");
        this.btnCancelar.setText("Cancelar");
    }

    private void setearDatosDeTarea(){
        int indiceFila = 0;
        DefaultTableModel modeloTablaPosiblesPrecedencias = (DefaultTableModel)tblPosiblesPrecedencias.getModel();
        for (Tarea tarea : posiblesTareasPrecedentes){      
            modeloTablaPosiblesPrecedencias.addRow(new Object[indiceFila]);
            tblPosiblesPrecedencias.setValueAt(tarea.getNombre(), indiceFila, 0);
            tblPosiblesPrecedencias.setValueAt(tarea.getDescripcion(), indiceFila, 1);
            indiceFila += 1;
        }
        if (tipoAccion == Accion.modificar){
            DefaultTableModel modeloTablaPrecedencias = (DefaultTableModel)tblPrecedencia.getModel();
            indiceFila = 0;
            for (Tarea tarea : tareasPrecedentes.getTareas()){      
                modeloTablaPrecedencias.addRow(new Object[indiceFila]);
                tblPrecedencia.setValueAt(tarea.getNombre(), indiceFila, 0);
                tblPrecedencia.setValueAt(tarea.getDescripcion(), indiceFila, 1);
                indiceFila += 1;
            }
            txtNombreTarea.setText(nombre);
            txtDescripcionTarea.setText(descripcion);
            txtTiempoOptimista.setText(Integer.toString(tiemposEstimados.getTiempoOptimista()));
            txtTiempoMasProbable.setText(Integer.toString(tiemposEstimados.getTiempoMasProbable()));
            txtTiempoPesimista.setText(Integer.toString(tiemposEstimados.getTiempoPesimista()));
        }
    }

    private boolean controlarDatosDeEntradaDelUsuario(){
        if (txtDescripcionTarea.getText().equals("")){
            return false;
        }
        int to = Integer.parseInt(txtTiempoOptimista.getText());
        int tmp = Integer.parseInt(txtTiempoMasProbable.getText());
        int tp = Integer.parseInt(txtTiempoPesimista.getText());
        if (!((to > 0) && (to < tmp) && (tmp < tp) && (tp < 256))){
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
        txtNombreTarea = new javax.swing.JTextField();
        lblDescripciónTarea = new javax.swing.JLabel();
        txtDescripcionTarea = new javax.swing.JTextField();
        jPanelTiemposEstimados = new javax.swing.JPanel();
        lblTiempoOptimista = new javax.swing.JLabel();
        txtTiempoOptimista = new javax.swing.JTextField();
        lblTiempoMasProbable = new javax.swing.JLabel();
        txtTiempoMasProbable = new javax.swing.JTextField();
        lblTiempoPesimista = new javax.swing.JLabel();
        txtTiempoPesimista = new javax.swing.JTextField();
        jPanelPrecedencias = new javax.swing.JPanel();
        jScrollPane1 = new javax.swing.JScrollPane();
        tblPrecedencia = new javax.swing.JTable();
        jScrollPane2 = new javax.swing.JScrollPane();
        tblPosiblesPrecedencias = new javax.swing.JTable();
        btnAgregarPrecedencia = new javax.swing.JButton();
        btnSacarPrecedencia = new javax.swing.JButton();
        lblTareasPrecedentes = new javax.swing.JLabel();
        lblTareasDisponiblesComoPrecedencia = new javax.swing.JLabel();
        btnGuardar = new javax.swing.JButton();
        btnCancelar = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);

        lblIdTarea.setText("lblNombreTarea");

        txtNombreTarea.setEditable(false);
        txtNombreTarea.setFont(new java.awt.Font("Tahoma", 1, 11));
        txtNombreTarea.setHorizontalAlignment(javax.swing.JTextField.CENTER);

        lblDescripciónTarea.setText("lblDescripcionTarea");

        txtDescripcionTarea.setText("Tarea ");

        jPanelTiemposEstimados.setBorder(javax.swing.BorderFactory.createTitledBorder("Tiempos estimados"));

        lblTiempoOptimista.setText("lblTiempoOptimista");

        txtTiempoOptimista.setText("1");

        lblTiempoMasProbable.setText("lblTiempoMasProbable");

        txtTiempoMasProbable.setText("3");

        lblTiempoPesimista.setText("lblTiempoPesimista");

        txtTiempoPesimista.setText("9");

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
                    .addComponent(txtTiempoOptimista, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(lblTiempoMasProbable)
                    .addComponent(txtTiempoMasProbable, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(lblTiempoPesimista)
                    .addComponent(txtTiempoPesimista, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        jPanelPrecedencias.setBorder(javax.swing.BorderFactory.createTitledBorder("Precedencias"));

        tblPrecedencia.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "ID", "Descripción"
            }
        ) {
            Class[] types = new Class [] {
                java.lang.Integer.class, java.lang.String.class
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
                "ID", "Descripción"
            }
        ) {
            Class[] types = new Class [] {
                java.lang.Integer.class, java.lang.String.class
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

        btnGuardar.setText("btnGuardar");
        btnGuardar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnGuardarActionPerformed(evt);
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

        jPanelTiemposEstimados.getAccessibleContext().setAccessibleName("");

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void btnAgregarPrecedenciaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnAgregarPrecedenciaActionPerformed
        int aux = tblPosiblesPrecedencias.getSelectedRow();
        if (aux != -1){
            DefaultTableModel modeloTablaPrecedencias = (DefaultTableModel)tblPrecedencia.getModel();
            int nuevaFila = tareasPrecedentes.size();
            modeloTablaPrecedencias.addRow(new Object[nuevaFila]);
            Tarea nuevaTareaPredecesora = posiblesTareasPrecedentes.get(aux);
            tblPosiblesPrecedencias.remove(tblPosiblesPrecedencias.getSelectedRow());
            tareasPrecedentes.add(nuevaTareaPredecesora);
            tblPrecedencia.setValueAt(nuevaTareaPredecesora.getId(), nuevaFila, 0);
            tblPrecedencia.setValueAt(nuevaTareaPredecesora.getDescripcion(), nuevaFila, 1);
        }
        
    }//GEN-LAST:event_btnAgregarPrecedenciaActionPerformed

    private void btnSacarPrecedenciaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnSacarPrecedenciaActionPerformed
        tareasPredecesoras.remove(tblPrecedencia.getSelectedRow());
    }//GEN-LAST:event_btnSacarPrecedenciaActionPerformed

    private void btnGuardarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnGuardarActionPerformed
        try{
            if (controlarDatosDeEntradaDelUsuario()){
                descripcionTarea = txtDescripcionTarea.getText();
                int tiempoOptimista = Integer.parseInt(txtTiempoOptimista.getText());
                int tiempoMasProbable = Integer.parseInt(txtTiempoMasProbable.getText());
                int tiempoPesimista = Integer.parseInt(txtTiempoPesimista.getText());
                switch (tipoAccion){
                    case crear:
                        Tarea t = new Tarea(idTarea, descripcionTarea, new TiempoEstimado(tiempoOptimista, tiempoMasProbable, tiempoPesimista), tareasPredecesoras);
                        formularioProyecto.agregarTareaEnListaDeTareas(t);
                        FabricaDeTarea.getInstance().confirmarUltimoId();
                        break;
                    case modificar:
                        tiemposEstimados.setTiempoEstimado(tiempoOptimista, tiempoMasProbable, tiempoPesimista);
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
