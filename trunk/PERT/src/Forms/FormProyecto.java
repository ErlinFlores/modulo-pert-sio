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

import Entidades.*;
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
    private RedDeTareas redDeTareas;
    
    /** Creates new form FormProyecto */
    public FormProyecto(FormInicio formularioInicio) { //Para crear proyecto nuevo.
        initComponents();
        FabricaDeProyectos.getInstance().reset();
        FabricaDeTareas.getInstance().reset();
        setearEtiquetas();
        this.formularioInicio = formularioInicio;
        this.tipoAccion = Accion.crear;
        this.nombre = "";
        this.redDeTareas = new RedDeTareas(new ArrayList<Tarea>());
    }

    /** Creates new form FormProyecto */
    public FormProyecto(FormInicio formularioInicio, Proyecto proyecto) { //Para abrir proyecto existente.
        initComponents();
        FabricaDeProyectos.getInstance().reset();
        FabricaDeTareas.getInstance().reset();
        setearEtiquetas();
        this.formularioInicio = formularioInicio;
        this.tipoAccion = Accion.modificar;
        this.nombre = proyecto.obtenerNombre();
        this.redDeTareas = proyecto.obtenerRedDeTareas();
        setearCampos();
    }    

    /**
     * Se setean las etiquetas de la pantalla según el idioma configurado.
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
    
    /**
     * Previo al almacenamiento en la estructura de datos de los datos ingresados por el usuario,
     * se verifica que los mismos sean correctos.
     * @return (si son válidos o no los datos ingresados por el usuario).
     */
    private boolean controlarDatosDeEntradaDelUsuario(){
        if (txtNombreProyecto.getText().equals("")){ 
            return false;
        }
        if (redDeTareas.obtenerCantidadDeTareas()==0){
            return false;
        }
        return true;
    } 
    
    /**
     * Se setean los campos de la pantalla con los datos correspondientes (solo en caso de ser una modificación).
     */
    private void setearCampos(){        
        if (tipoAccion.equals(tipoAccion.modificar)){            
            txtNombreProyecto.setText(nombre);
            int fila = 0;
            for (Tarea tarea : redDeTareas.obtenerTareas()){
                actualizarTablaDeDatosIngresados(fila, true, tarea);
                fila += 1;
            }
        }
    }    
    
    /**
     * Se modifica la tabla de tareas del proyecto en la cual se muestran los datos ingresados por el usuario
     * acerca de cada una de ellas (nombre, descripción, precedencia y tiempos estimados).
     * @param fila (fila de la tabla a agregar o eliminar).
     * @param nuevaFila (determina si se trata de una nueva fila a agregar o de eliminar una existente).
     * @param tarea (tarea que forma parte de la modificación).
     */
    private void actualizarTablaDeDatosIngresados(int fila, boolean nuevaFila, Tarea tarea){
        DefaultTableModel modeloDeTablaDeDatosIngresados = (DefaultTableModel)tblTareasProyecto.getModel();
        if (nuevaFila){
            modeloDeTablaDeDatosIngresados.addRow(new Object[fila]);
            tblTareasProyecto.setValueAt(tarea.obtenerNombre(), fila, 0);
            tblTareasProyecto.setValueAt(tarea.obtenerDescripcion(), fila, 1);
            tblTareasProyecto.setValueAt(tarea.obtenerPrecedencia().obtenerTareasConcatenadas(), fila, 2);
            tblTareasProyecto.setValueAt(tarea.obtenerTiempoEstimado().obtenerTiempoOptimista(), fila, 3);
            tblTareasProyecto.setValueAt(tarea.obtenerTiempoEstimado().obtenerTiempoMasProbable(), fila, 4);
            tblTareasProyecto.setValueAt(tarea.obtenerTiempoEstimado().obtenerTiempoPesimista(), fila, 5);
        }else{
            modeloDeTablaDeDatosIngresados.removeRow(fila);
        }      
        tblTareasProyecto.updateUI();
    }
    
    /**
     * Se modifica la tabla de tareas del proyecto en la cual se muestran los resultados calculados
     * en base a los datos ingresados por el usuario (duración esperada, tiempos tempranos y tardíos,
     * holgura y si es tarea crítica).
     */
    private void actualizarTablaDeCalculosRealizados(){
        DefaultTableModel modeloDeTablaDeResultadosCalculados = (DefaultTableModel)tblResultadoDeCalculos.getModel();
        int cantidadDeFilasActual = modeloDeTablaDeResultadosCalculados.getRowCount();
        for (int i = 0; i < cantidadDeFilasActual; i++){//Se eliminan todas las filas actuales. O sea, se limpia la tabla.
            modeloDeTablaDeResultadosCalculados.removeRow(0);
        }
        int fila = 0;
        for (Tarea tarea : redDeTareas.obtenerTareas()){//Se ingresan las filas con los datos actuales.            
            modeloDeTablaDeResultadosCalculados.addRow(new Object[fila]);
            tblResultadoDeCalculos.setValueAt(tarea.obtenerNombre(), fila, 0);
            tblResultadoDeCalculos.setValueAt(tarea.obtenerDuracionEsperada(), fila, 1);
            tblResultadoDeCalculos.setValueAt(tarea.obtenerPrecedencia().obtenerTareasConcatenadas(), fila, 2);
            tblResultadoDeCalculos.setValueAt(tarea.obtenerComienzoTemprano(), fila, 3);
            tblResultadoDeCalculos.setValueAt(tarea.obtenerFinTemprano(), fila, 4);
            tblResultadoDeCalculos.setValueAt(tarea.obtenerComienzoTardio(), fila, 5);
            tblResultadoDeCalculos.setValueAt(tarea.obtenerFinTardio(), fila, 6);
            tblResultadoDeCalculos.setValueAt(tarea.obtenerHolgura(), fila, 7);
            tblResultadoDeCalculos.setValueAt(tarea.esTareaCritica(), fila, 8);
            fila += 1;            
        }
        tblResultadoDeCalculos.updateUI();
    }     

    /**
     * Al modificar una tarea especifica, también se actualiza dicha tarea
     * en la tabla de datos ingresados por el usuario.
     * @param id
     * @param descripcion
     */
    public void actualizarTarea(int id, String descripcion){
        Tarea tareaModificada = redDeTareas.obtenerTareaPorID(id);
        tareaModificada.setearDescripcion(descripcion);
        for (int i = 0; i < tblTareasProyecto.getRowCount(); i++){
            String nombreTarea = (String)tblTareasProyecto.getValueAt(i, 0);
            int idTarea = FabricaDeTareas.getInstance().getIdTareaByNombre(nombreTarea);
            if (idTarea == id){
                tblTareasProyecto.setValueAt(descripcion, i, 1);
                tblTareasProyecto.setValueAt(tareaModificada.obtenerPrecedencia().obtenerTareasConcatenadas(), i, 2);
                tblTareasProyecto.setValueAt(tareaModificada.obtenerTiempoEstimado().obtenerTiempoOptimista(), i, 3);
                tblTareasProyecto.setValueAt(tareaModificada.obtenerTiempoEstimado().obtenerTiempoMasProbable(), i, 4);
                tblTareasProyecto.setValueAt(tareaModificada.obtenerTiempoEstimado().obtenerTiempoPesimista(), i, 5);
            }
        }
        realizarCalculosPERT();
    }
    
    /**
     * Se almacena una tarea en el conjunto de tareas del proyecto y
     * también se actualiza la tabla de datos ingresados por el usuario (de tareas).
     * @param tarea (nueva tarea del proyecto).
     */
    public void agregarTareaEnRedDeTareas(Tarea tarea){        
        redDeTareas.agregarTarea(tarea);
        actualizarTablaDeDatosIngresados(redDeTareas.obtenerCantidadDeTareas()-1, true, tarea);
    }
    
    /**
     * Se devuelve la lista de tareas del conjunto de tareas del proyecto.
     * @return 
     */
    public List<Tarea> obtenerListaDeTareasDelProyecto(){
        return redDeTareas.obtenerTareas();
    }       
    
    /**
     * Se obtiene una lista de tareas candidatas a ser precedentes de una tarea determinada, teniendo
     * en cuenta que dichas tareas candidatas no sean sucesoras de dicha tarea (evitando asi un ciclo en la red,
     * los cuales no estan permitidos).
     * @param tarea
     * @return 
     */
    public List<Tarea> obtenerPosiblesTareasPrecedentes(Tarea tarea){
        return redDeTareas.obtenerPosiblesTareasPrecedentes(tarea);
    }    
   
    /**
     * Método que ejecuta el algoritmo de cálculos de duración esperada de las tareas,
     * tiempos tempranos, tiempos tardíos, holguras y tareas críticas.
     */
    private void realizarCalculosPERT(){
        GestorDeCalculos gestorDeCalculos = new GestorDeCalculos(redDeTareas);
        if (gestorDeCalculos.realizarCalculosPERT()){
            actualizarTablaDeCalculosRealizados();
        }else{
            JOptionPane.showMessageDialog(this, "Hubo error en los cálculos sobre la red de tareas");
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
        btnGuardar = new javax.swing.JButton();
        btnCancelar = new javax.swing.JButton();
        btnCalculoDeProbabilidades = new javax.swing.JButton();
        jScrollPane2 = new javax.swing.JScrollPane();
        tblResultadoDeCalculos = new javax.swing.JTable();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        setResizable(false);

        lblNombreProyecto.setText("lblNombreProyecto");

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
        tblTareasProyecto.getColumnModel().getColumn(1).setResizable(false);
        tblTareasProyecto.getColumnModel().getColumn(2).setResizable(false);
        tblTareasProyecto.getColumnModel().getColumn(3).setResizable(false);
        tblTareasProyecto.getColumnModel().getColumn(4).setResizable(false);
        tblTareasProyecto.getColumnModel().getColumn(5).setResizable(false);

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

        tblResultadoDeCalculos.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "Nombre", "Duración", "Precedencia", "tComienzoTemp", "tFinTemprano", "tComienzoTardio", "tFinTardio", "Holgura", "Critica"
            }
        ) {
            Class[] types = new Class [] {
                java.lang.String.class, java.lang.Double.class, java.lang.String.class, java.lang.Double.class, java.lang.Double.class, java.lang.Double.class, java.lang.Double.class, java.lang.Double.class, java.lang.Boolean.class
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
        tblResultadoDeCalculos.setSelectionMode(javax.swing.ListSelectionModel.SINGLE_SELECTION);
        tblResultadoDeCalculos.getTableHeader().setReorderingAllowed(false);
        jScrollPane2.setViewportView(tblResultadoDeCalculos);
        tblResultadoDeCalculos.getColumnModel().getColumn(0).setResizable(false);
        tblResultadoDeCalculos.getColumnModel().getColumn(1).setResizable(false);
        tblResultadoDeCalculos.getColumnModel().getColumn(2).setResizable(false);
        tblResultadoDeCalculos.getColumnModel().getColumn(3).setResizable(false);
        tblResultadoDeCalculos.getColumnModel().getColumn(4).setResizable(false);
        tblResultadoDeCalculos.getColumnModel().getColumn(5).setResizable(false);
        tblResultadoDeCalculos.getColumnModel().getColumn(6).setResizable(false);
        tblResultadoDeCalculos.getColumnModel().getColumn(7).setResizable(false);
        tblResultadoDeCalculos.getColumnModel().getColumn(8).setResizable(false);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(btnRealizarCalculosTiempos, javax.swing.GroupLayout.DEFAULT_SIZE, 191, Short.MAX_VALUE)
                        .addGap(646, 646, 646))
                    .addGroup(layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jScrollPane2, javax.swing.GroupLayout.DEFAULT_SIZE, 718, Short.MAX_VALUE)
                            .addGroup(layout.createSequentialGroup()
                                .addComponent(lblNombreProyecto)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addComponent(txtNombreProyecto, javax.swing.GroupLayout.PREFERRED_SIZE, 200, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addComponent(lblTareasProyecto)
                            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                                .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 584, Short.MAX_VALUE)
                                .addGap(18, 18, 18)
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                    .addComponent(btnAgregar, javax.swing.GroupLayout.DEFAULT_SIZE, 116, Short.MAX_VALUE)
                                    .addComponent(btnModificar, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                    .addComponent(btnBorrar, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                    .addComponent(btnBorrarTodas, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))
                            .addComponent(btnCalculoDeProbabilidades))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addComponent(btnCancelar, javax.swing.GroupLayout.DEFAULT_SIZE, 99, Short.MAX_VALUE)
                            .addComponent(btnGuardar, javax.swing.GroupLayout.DEFAULT_SIZE, 99, Short.MAX_VALUE))
                        .addGap(10, 10, 10))))
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
                .addContainerGap(40, Short.MAX_VALUE))
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addContainerGap(542, Short.MAX_VALUE)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(btnGuardar)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(btnCancelar))
                    .addComponent(btnCalculoDeProbabilidades))
                .addContainerGap())
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void btnAgregarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnAgregarActionPerformed
        if (FabricaDeTareas.getInstance().esPosibleCrearNuevaTarea()){
            FormTarea ft = new FormTarea(this);
            ft.setVisible(true);
        }else{
            JOptionPane.showMessageDialog(this, "No se pueden crear más tareas (límite = 26)");
        }
    }//GEN-LAST:event_btnAgregarActionPerformed

    private void btnModificarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnModificarActionPerformed
        int filaSeleccionada = tblTareasProyecto.getSelectedRow();
        if (filaSeleccionada >= 0){
            String nombreTarea = (String)tblTareasProyecto.getValueAt(filaSeleccionada, 0);
            Tarea tarea = redDeTareas.obtenerTareaPorID(FabricaDeTareas.getInstance().getIdTareaByNombre(nombreTarea));
            FormTarea ft = new FormTarea(this, tarea);
            ft.setVisible(true);            
        }else{
            JOptionPane.showMessageDialog(this, "Debe seleccionar una fila");
        }
    }//GEN-LAST:event_btnModificarActionPerformed

    private void btnBorrarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnBorrarActionPerformed
        int filaSeleccionada = tblTareasProyecto.getSelectedRow();
        if (filaSeleccionada >= 0){
            String nombreTarea = (String)tblTareasProyecto.getValueAt(filaSeleccionada, 0);
            Tarea tarea = redDeTareas.obtenerTareaPorID(FabricaDeTareas.getInstance().getIdTareaByNombre(nombreTarea));
            redDeTareas.borrarTareaDePrecedencias(tarea);
            redDeTareas.borrarTarea(tarea);
            actualizarTablaDeDatosIngresados(filaSeleccionada, false, tarea);
            realizarCalculosPERT();
        }else{
            JOptionPane.showMessageDialog(this, "Debe seleccionar una fila");
        }        
    }//GEN-LAST:event_btnBorrarActionPerformed

    private void btnBorrarTodasActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnBorrarTodasActionPerformed
        for (int fila = 0; fila < redDeTareas.obtenerCantidadDeTareas(); fila++){
            actualizarTablaDeDatosIngresados(0, false, null);
        }
        redDeTareas = new RedDeTareas(new ArrayList<Tarea>());
        FabricaDeTareas.getInstance().reset();
    }//GEN-LAST:event_btnBorrarTodasActionPerformed

    private void btnRealizarCalculosTiemposActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnRealizarCalculosTiemposActionPerformed
        realizarCalculosPERT();
    }//GEN-LAST:event_btnRealizarCalculosTiemposActionPerformed

    private void btnGuardarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnGuardarActionPerformed
        try{
            if (controlarDatosDeEntradaDelUsuario()){
                nombre = txtNombreProyecto.getText();
                switch (tipoAccion){
                    case crear:
                        Proyecto nuevoProyecto = FabricaDeProyectos.getInstance().crearProyecto(nombre, redDeTareas);
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
    private javax.swing.JLabel lblNombreProyecto;
    private javax.swing.JLabel lblTareasProyecto;
    private javax.swing.JTable tblResultadoDeCalculos;
    private javax.swing.JTable tblTareasProyecto;
    private javax.swing.JTextField txtNombreProyecto;
    // End of variables declaration//GEN-END:variables

}
