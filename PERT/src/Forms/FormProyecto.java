/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

/*
 * FormProyecto.java
 *
 * Created on 02/03/2011, 11:26:21
 */

package Forms;

import Entidades.Accion;
import Entidades.FabricaDeProyectos;
import Entidades.Precedencia;
import Entidades.Proyecto;
import Entidades.Tarea;
import java.util.ArrayList;
import java.util.List;
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;

/**
 *
 * @author Manuel Lorenze
 */
public class FormProyecto extends javax.swing.JFrame {

    private FormInicio formularioInicio;
    private Accion tipoAccion;
    private String nombre;
    private List<Tarea> tareas;
    
    /** Creates new form FormProyecto */
    public FormProyecto(FormInicio formularioInicio) { //Crear proyecto nuevo
        initComponents();
        setearEtiquetas();
        this.formularioInicio = formularioInicio;
        this.tipoAccion = Accion.crear;
        this.nombre = "";
        this.tareas = new ArrayList<Tarea>();
    }

    public FormProyecto(FormInicio formularioInicio, Proyecto proyecto) { //Abrir proyecto existente
        initComponents();
        setearEtiquetas();        
        this.formularioInicio = formularioInicio;
        this.tipoAccion = Accion.modificar;
        this.nombre = proyecto.getNombre();
        this.tareas = proyecto.getTareas();
        setearCampos();
    }    

    /**
     * Se setean las etiquetas de la pantalla.
     */
    private void setearEtiquetas(){
        setTitle("Proyecto"); // Manejo de idioma!!!
        this.lblNombreProyecto.setText("Nombre del proyecto: ");
        this.lblTareasProyecto.setText("Tareas del proyecto: ");
        this.btnAgregar.setText("Agregar");
        this.btnBorrar.setText("Borrar");
        this.btnBorrarTodas.setText("Borrar todas");
        this.btnModificar.setText("Modificar");
        this.btnGuardar.setText("Guardar");
        this.btnCancelar.setText("Cancelar");
        this.btnRealizarCalculosTiempos.setText("Realizar cálculos de tiempos");
        this.btnCalculoDeProbabilidades.setText("Cálculo de probabilidades");
    }
    
    private void setearCampos(){
        txtNombreProyecto.setText(nombre);
        if (tipoAccion.equals(tipoAccion.modificar)){            
            int fila = 0;
            for (Tarea tarea : tareas){
                agregarTareaEnLaTablaDeTareas(tarea, fila);
                fila += 1;
            }
        }
    }

    private String precedenciaConcatenada(Tarea tarea){             
        return tarea.getPrecedencia().getTareasConcatenadas();
    }
    
    private void agregarTareaEnLaTablaDeTareas(Tarea tarea, int fila){
        DefaultTableModel modeloTablaTareas = (DefaultTableModel)tblTareasProyecto.getModel();
        modeloTablaTareas.addRow(new Object[fila]);
        tblTareasProyecto.setValueAt(tarea.getId(), fila, 0);
        tblTareasProyecto.setValueAt(tarea.getDescripcion(), fila, 1);
        tblTareasProyecto.setValueAt(tarea.getTiempoEstimado().getTiempoOptimista(), fila, 2);
        tblTareasProyecto.setValueAt(tarea.getTiempoEstimado().getTiempoMasProbable(), fila, 3);
        tblTareasProyecto.setValueAt(tarea.getTiempoEstimado().getTiempoPesimista(), fila, 4);
        tblTareasProyecto.setValueAt(precedenciaConcatenada(tarea), fila, 5);
    }
    
    /**
     * Antes de ingresar los datos ingresados por el usuario a la estructura de datos interna del programa, se verifican que cumplan las restricciones.
     * @return
     */
    private boolean controlarDatosDeEntradaDelUsuario(){
        if (txtNombreProyecto.getText().equals("")){ // Se puede agregar más restricciones en cuanto a los caracteres permitidos!!!
            return false;
        }
        if (tareas.isEmpty()){
            return false;
        }
        return true;
    }   

    public void agregarTareaEnListaDeTareas(Tarea tarea){
        tareas.add(tarea);
        agregarTareaEnLaTablaDeTareas(tarea, tareas.size() - 1);
    }    
    
    /**
     * Dada una tarea, se determina si forma parte de un camino en la red en el cual existen otras tareas sucesoras.
     * @param lt
     * @param i
     * @return
     */
  /*  private boolean tieneSucesores(int i){
        Tarea tareaDeAnalisis = tareas.get(i);
        for (int j = 0; j < tareas.size(); j++){
            if (j != i){
                Tarea tAux = tareas.get(j);
                List<Tarea> precedencias = tAux.getPrecedencia();
                for (int h = 0; h < precedencias.size(); h++){
                    Tarea tPrec = precedencias.get(h);
                    if (tareaDeAnalisis.equals(tPrec)){
                        return true;
                    }
                }
            }
        }
        return false;
    }*/

    public List<Tarea> obtenerListaDeTareasDelProyecto(){
        return tareas;
    }

    /** This method is called from within the constructor to
     * initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is
     * always regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        lblNombreProyecto = new javax.swing.JLabel();
        txtNombreProyecto = new javax.swing.JTextField();
        lblTareasProyecto = new javax.swing.JLabel();
        jScrollPane1 = new javax.swing.JScrollPane();
        tblTareasProyecto = new javax.swing.JTable();
        btnAgregar = new javax.swing.JButton();
        btnModificar = new javax.swing.JButton();
        btnBorrar = new javax.swing.JButton();
        btnBorrarTodas = new javax.swing.JButton();
        btnRealizarCalculosTiempos = new javax.swing.JButton();
        jScrollPane2 = new javax.swing.JScrollPane();
        jTable1 = new javax.swing.JTable();
        btnGuardar = new javax.swing.JButton();
        btnCancelar = new javax.swing.JButton();
        btnCalculoDeProbabilidades = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        setResizable(false);

        lblNombreProyecto.setText("lblNombreProyecto");

        txtNombreProyecto.setText("Proyecto prueba");

        lblTareasProyecto.setText("lblTareasProyecto");

        tblTareasProyecto.setBorder(javax.swing.BorderFactory.createCompoundBorder());
        tblTareasProyecto.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "ID", "Descripción", "tO", "tM", "tP", "Precedencia"
            }
        ) {
            Class[] types = new Class [] {
                java.lang.String.class, java.lang.String.class, java.lang.Integer.class, java.lang.Integer.class, java.lang.Integer.class, java.lang.String.class
            };
            boolean[] canEdit = new boolean [] {
                false, false, false, false, false, false
            };

            public Class getColumnClass(int columnIndex) {
                return types [columnIndex];
            }

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        tblTareasProyecto.setPreferredSize(new java.awt.Dimension(450, 200));
        tblTareasProyecto.setSelectionMode(javax.swing.ListSelectionModel.SINGLE_SELECTION);
        tblTareasProyecto.getTableHeader().setReorderingAllowed(false);
        jScrollPane1.setViewportView(tblTareasProyecto);
        tblTareasProyecto.getColumnModel().getColumn(0).setResizable(false);
        tblTareasProyecto.getColumnModel().getColumn(2).setResizable(false);
        tblTareasProyecto.getColumnModel().getColumn(3).setResizable(false);
        tblTareasProyecto.getColumnModel().getColumn(4).setResizable(false);

        btnAgregar.setText("btnAgregar");
        btnAgregar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnAgregarActionPerformed(evt);
            }
        });

        btnModificar.setText("btnModificar");
        btnModificar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnModificarActionPerformed(evt);
            }
        });

        btnBorrar.setText("btnBorrar");
        btnBorrar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnBorrarActionPerformed(evt);
            }
        });

        btnBorrarTodas.setText("btnBorrarTodas");
        btnBorrarTodas.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnBorrarTodasActionPerformed(evt);
            }
        });

        btnRealizarCalculosTiempos.setText("btnRealizarCalculosTiempos");
        btnRealizarCalculosTiempos.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnRealizarCalculosTiemposActionPerformed(evt);
            }
        });

        jTable1.setBorder(javax.swing.BorderFactory.createCompoundBorder());
        jTable1.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null, null, null, null, null, null}
            },
            new String [] {
                "ID", "Duración", "Precedencia", "tComTemp", "tComTardío", "tFinTemp", "tFinTardío", "Holgura", "¿C.C.?"
            }
        ) {
            Class[] types = new Class [] {
                java.lang.Byte.class, java.lang.Integer.class, java.lang.String.class, java.lang.Integer.class, java.lang.Integer.class, java.lang.Integer.class, java.lang.Integer.class, java.lang.Integer.class, java.lang.Boolean.class
            };
            boolean[] canEdit = new boolean [] {
                false, false, false, false, false, false, false, false, false
            };

            public Class getColumnClass(int columnIndex) {
                return types [columnIndex];
            }

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        jTable1.setPreferredSize(new java.awt.Dimension(675, 200));
        jTable1.setSelectionMode(javax.swing.ListSelectionModel.SINGLE_SELECTION);
        jScrollPane2.setViewportView(jTable1);
        jTable1.getColumnModel().getColumn(0).setResizable(false);
        jTable1.getColumnModel().getColumn(1).setResizable(false);
        jTable1.getColumnModel().getColumn(3).setResizable(false);
        jTable1.getColumnModel().getColumn(4).setResizable(false);
        jTable1.getColumnModel().getColumn(5).setResizable(false);
        jTable1.getColumnModel().getColumn(6).setResizable(false);
        jTable1.getColumnModel().getColumn(7).setResizable(false);
        jTable1.getColumnModel().getColumn(8).setResizable(false);

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

        btnCalculoDeProbabilidades.setText("btnCalculoDeProbabilidades");

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addGroup(javax.swing.GroupLayout.Alignment.LEADING, layout.createSequentialGroup()
                                .addComponent(lblNombreProyecto)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addComponent(txtNombreProyecto, javax.swing.GroupLayout.PREFERRED_SIZE, 200, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addComponent(lblTareasProyecto, javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(javax.swing.GroupLayout.Alignment.LEADING, layout.createSequentialGroup()
                                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                    .addComponent(btnAgregar, javax.swing.GroupLayout.DEFAULT_SIZE, 116, Short.MAX_VALUE)
                                    .addComponent(btnModificar, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                    .addComponent(btnBorrar, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                    .addComponent(btnBorrarTodas, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))
                            .addComponent(jScrollPane2, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.PREFERRED_SIZE, 662, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addComponent(btnCancelar, javax.swing.GroupLayout.DEFAULT_SIZE, 99, Short.MAX_VALUE)
                            .addComponent(btnGuardar, javax.swing.GroupLayout.DEFAULT_SIZE, 99, Short.MAX_VALUE))
                        .addContainerGap())
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(btnRealizarCalculosTiempos, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addGap(18, 18, 18)
                        .addComponent(btnCalculoDeProbabilidades)
                        .addGap(433, 433, 433))))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(lblNombreProyecto)
                    .addComponent(txtNombreProyecto, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addComponent(lblTareasProyecto)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(btnGuardar)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(btnCancelar))
                    .addGroup(layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 227, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addGroup(layout.createSequentialGroup()
                                .addComponent(btnAgregar)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(btnModificar)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(btnBorrar)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(btnBorrarTodas)))
                        .addGap(13, 13, 13)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(btnRealizarCalculosTiempos)
                            .addComponent(btnCalculoDeProbabilidades))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 227, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void btnAgregarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnAgregarActionPerformed
        FormTarea ft = new FormTarea(this);
        ft.setVisible(true);
    }//GEN-LAST:event_btnAgregarActionPerformed

    private void btnModificarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnModificarActionPerformed
        int i = tblTareasProyecto.getSelectedRow();
        Tarea t = tareas.get(i);
        FormTarea ft = new FormTarea(this, t);
        ft.setVisible(true);
    }//GEN-LAST:event_btnModificarActionPerformed

    private void btnBorrarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnBorrarActionPerformed
        int i = tblTareasProyecto.getSelectedRow();
/*        if (!(tieneSucesores(i))){
            tareas.remove(i);
            tblTareasProyecto.remove(i);
            tblTareasProyecto.updateUI();
        }*/
    }//GEN-LAST:event_btnBorrarActionPerformed

    private void btnBorrarTodasActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnBorrarTodasActionPerformed
        tareas = new ArrayList<Tarea>();
        tblTareasProyecto.removeAll();
        tblTareasProyecto.updateUI();
    }//GEN-LAST:event_btnBorrarTodasActionPerformed

    private void btnRealizarCalculosTiemposActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnRealizarCalculosTiemposActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_btnRealizarCalculosTiemposActionPerformed

    private void btnGuardarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnGuardarActionPerformed
        try{
            if (controlarDatosDeEntradaDelUsuario()){
                nombre = txtNombreProyecto.getText();
                switch (tipoAccion){
                    case crear:
                        Proyecto nuevoProyecto = FabricaDeProyectos.getInstance().crearProyecto(nombre, tareas);
                        formularioInicio.agregarProyectoEnListaDeProyectos(nuevoProyecto);
                        break;
                    case modificar:
                        // En teoría no hay que modificar nada en este sector. Modificación por referencia.
                        break;
                }
            }else{
                JOptionPane.showMessageDialog(this, "ERROR: Hay campos con valores no válidos.");
            }
        }catch(Exception ex){
            JOptionPane.showMessageDialog(this, ex);
        }
        this.dispose();
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
                new FormProyecto().setVisible(true);
            }
        });
    }*/

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnAgregar;
    private javax.swing.JButton btnBorrar;
    private javax.swing.JButton btnBorrarTodas;
    private javax.swing.JButton btnCalculoDeProbabilidades;
    private javax.swing.JButton btnCancelar;
    private javax.swing.JButton btnGuardar;
    private javax.swing.JButton btnModificar;
    private javax.swing.JButton btnRealizarCalculosTiempos;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JTable jTable1;
    private javax.swing.JLabel lblNombreProyecto;
    private javax.swing.JLabel lblTareasProyecto;
    private javax.swing.JTable tblTareasProyecto;
    private javax.swing.JTextField txtNombreProyecto;
    // End of variables declaration//GEN-END:variables

}
