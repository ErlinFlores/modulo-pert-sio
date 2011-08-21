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
import Entidades.FabricaDeTareas;
import Entidades.ModeloDeRed.GestorDeRed;
import Entidades.Precedencia;
import Entidades.Proyecto;
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
public class FormProyecto extends javax.swing.JFrame {

    private FormInicio formularioInicio;
    private Accion tipoAccion;
    private String nombre;
    private List<Tarea> tareas;
    
    /** Creates new form FormProyecto */
    public FormProyecto(FormInicio formularioInicio) { //Crear proyecto nuevo
        initComponents();
        FabricaDeProyectos.getInstance().reset();
        FabricaDeTareas.getInstance().reset();
        setearEtiquetas();
        this.formularioInicio = formularioInicio;
        this.tipoAccion = Accion.crear;
        this.nombre = "";
        this.tareas = new ArrayList<Tarea>();
    }

    public FormProyecto(FormInicio formularioInicio, Proyecto proyecto) { //Abrir proyecto existente
        initComponents();
        FabricaDeProyectos.getInstance().reset();
        FabricaDeTareas.getInstance().reset();
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
        if (tipoAccion.equals(tipoAccion.modificar)){            
            txtNombreProyecto.setText(nombre);
            int fila = 0;
            for (Tarea tarea : tareas){
                modificarTabla(tblTareasProyecto, fila, true, tarea);
                fila += 1;
            }
        }
    }    
    
    private void modificarTabla(JTable tabla, int fila, boolean nuevaFila, Tarea tarea){
        DefaultTableModel modeloTabla = (DefaultTableModel)tabla.getModel();
        if (nuevaFila){
            modeloTabla.addRow(new Object[fila]);
            tabla.setValueAt(tarea.getNombre(), fila, 0);
            tabla.setValueAt(tarea.getDescripcion(), fila, 1);
            tabla.setValueAt(tarea.getPrecedencia().getTareasConcatenadas(), fila, 2);
            tabla.setValueAt(tarea.getTiempoEstimado().getTiempoOptimista(), fila, 3);
            tabla.setValueAt(tarea.getTiempoEstimado().getTiempoMasProbable(), fila, 4);
            tabla.setValueAt(tarea.getTiempoEstimado().getTiempoPesimista(), fila, 5);            
        }else{
            modeloTabla.removeRow(fila);
        }      
        tabla.updateUI();
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
        modificarTabla(tblTareasProyecto, tareas.size(), true, tarea);
        tareas.add(tarea);
    }    
    
    public List<Tarea> obtenerPosiblesTareasPrecedentes(Tarea tarea){
        Precedencia tareasPrecedentes = tarea.getPrecedencia();
        List<Tarea> posiblesTareas = new ArrayList<Tarea>();
        for (Tarea posibleTareaPrecedente : tareas){
            int idPosibleTareaPrecedente = posibleTareaPrecedente.getId();
            if ((!tareasPrecedentes.esPrecedente(idPosibleTareaPrecedente)) && (idPosibleTareaPrecedente != tarea.getId())){
                if (!hayCamino(tarea, posibleTareaPrecedente)){
                    posiblesTareas.add(posibleTareaPrecedente);
                }
            }
        }       
        return posiblesTareas;
    }
    
    /*private boolean hayCamino(Tarea tareaInicio, Tarea tareaDestino){
        return false;
    }*/
    
    private boolean hayCamino(Tarea tareaInicio, Tarea tareaDestino){
        Precedencia tareasPrecedentesDeTareaDestino = tareaDestino.getPrecedencia();
        boolean existeCamino = false;
        for (Tarea tarea : tareasPrecedentesDeTareaDestino.getTareas()){
            if (!(tarea.getId() == tareaInicio.getId())){
                existeCamino = hayCamino(tareaInicio, tarea);
            }else{
                return true;
            }
            if (existeCamino){
                break;
            }
        }
        return existeCamino;
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
    
    public void actualizarTareaEnTabla(int id, String descripcion, TiempoEstimado tiemposEstimados, Precedencia tareasPrecedentes){
        for (int i = 0; i < tblTareasProyecto.getRowCount(); i++){
            String nombreTarea = (String)tblTareasProyecto.getValueAt(i, 0);
            int idTarea = FabricaDeTareas.getInstance().getIdTareaByNombre(nombreTarea);
            if (idTarea == id){
                tblTareasProyecto.setValueAt(descripcion, i, 1);
                tblTareasProyecto.setValueAt(tareasPrecedentes.getTareasConcatenadas(), i, 2);
                tblTareasProyecto.setValueAt(tiemposEstimados.getTiempoOptimista(), i, 3);
                tblTareasProyecto.setValueAt(tiemposEstimados.getTiempoMasProbable(), i, 4);
                tblTareasProyecto.setValueAt(tiemposEstimados.getTiempoPesimista(), i, 5);
            }
        }
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

        tblTareasProyecto.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "Nombre", "Descripción", "Precedencia", "tO", "tM", "tP"
            }
        ) {
            Class[] types = new Class [] {
                java.lang.String.class, java.lang.String.class, java.lang.String.class, java.lang.Integer.class, java.lang.Integer.class, java.lang.Integer.class
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

            },
            new String [] {
                "Nombre", "Duración", "tComTemp", "tComTardío", "tFinTemp", "tFinTardío", "Holgura", "¿C.C.?"
            }
        ) {
            Class[] types = new Class [] {
                java.lang.String.class, java.lang.Integer.class, java.lang.Integer.class, java.lang.Integer.class, java.lang.Integer.class, java.lang.Integer.class, java.lang.Integer.class, java.lang.Boolean.class
            };
            boolean[] canEdit = new boolean [] {
                false, false, false, false, false, false, false, false
            };

            public Class getColumnClass(int columnIndex) {
                return types [columnIndex];
            }

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        jTable1.setPreferredSize(new java.awt.Dimension(675, 0));
        jTable1.setSelectionMode(javax.swing.ListSelectionModel.SINGLE_SELECTION);
        jScrollPane2.setViewportView(jTable1);
        jTable1.getColumnModel().getColumn(0).setResizable(false);
        jTable1.getColumnModel().getColumn(1).setResizable(false);
        jTable1.getColumnModel().getColumn(2).setResizable(false);
        jTable1.getColumnModel().getColumn(3).setResizable(false);
        jTable1.getColumnModel().getColumn(4).setResizable(false);
        jTable1.getColumnModel().getColumn(5).setResizable(false);
        jTable1.getColumnModel().getColumn(6).setResizable(false);
        jTable1.getColumnModel().getColumn(7).setResizable(false);

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
                        .addComponent(btnRealizarCalculosTiempos, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addGap(642, 642, 642))
                    .addGroup(layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(layout.createSequentialGroup()
                                .addComponent(lblNombreProyecto)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addComponent(txtNombreProyecto, javax.swing.GroupLayout.PREFERRED_SIZE, 200, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addComponent(lblTareasProyecto)
                            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                                .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 546, Short.MAX_VALUE)
                                .addGap(18, 18, 18)
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                    .addComponent(btnAgregar, javax.swing.GroupLayout.DEFAULT_SIZE, 116, Short.MAX_VALUE)
                                    .addComponent(btnModificar, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                    .addComponent(btnBorrar, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                    .addComponent(btnBorrarTodas, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))
                            .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 662, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addGroup(layout.createSequentialGroup()
                                .addComponent(btnCalculoDeProbabilidades)
                                .addGap(515, 515, 515)))
                        .addGap(18, 18, 18)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addComponent(btnCancelar, javax.swing.GroupLayout.DEFAULT_SIZE, 99, Short.MAX_VALUE)
                            .addComponent(btnGuardar, javax.swing.GroupLayout.DEFAULT_SIZE, 99, Short.MAX_VALUE))
                        .addContainerGap())))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(btnGuardar)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(btnCancelar))
                    .addGroup(layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(lblNombreProyecto)
                            .addComponent(txtNombreProyecto, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(18, 18, 18)
                        .addComponent(lblTareasProyecto)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 234, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addGroup(layout.createSequentialGroup()
                                .addComponent(btnAgregar)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(btnModificar)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(btnBorrar)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(btnBorrarTodas)))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(btnRealizarCalculosTiempos)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 227, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(btnCalculoDeProbabilidades)))
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
        for (int fila = 0; fila < tareas.size(); fila++){
            modificarTabla(tblTareasProyecto, 0, false, null);
        }
        tareas = new ArrayList<Tarea>();
        FabricaDeTareas.getInstance().reset();
    }//GEN-LAST:event_btnBorrarTodasActionPerformed

    private void btnRealizarCalculosTiemposActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnRealizarCalculosTiemposActionPerformed
        GestorDeRed gestorDeRed = new GestorDeRed(tareas);
        gestorDeRed.realizarCalculos();
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
