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
import java.util.Locale;
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;

/**
 *
 * @author Manuel Lorenze
 */
public class FormProyecto extends javax.swing.JFrame {

    private Locale lugarConfigurado;    
    private Accion tipoAccion;
    private String nombre;
    private String descripcion;
    private RedDeTareas redDeTareas;
    private UnidadDeTiempo unidadDeTiempo;
    
    /** Creates new form FormProyecto */
    public FormProyecto(String paisIdioma, String lenguajeIdioma) {
        lugarConfigurado = new Locale(paisIdioma, lenguajeIdioma);
        initComponents();
        FabricaDeProyectos.getInstance().reset();
        FabricaDeTareas.getInstance().reset();
        setearEtiquetas();        
        this.tipoAccion = Accion.crear;
        this.nombre = "";
        this.descripcion = "";
        this.redDeTareas = new RedDeTareas(new ArrayList<Tarea>());
        this.unidadDeTiempo = UnidadDeTiempo.dias;
        setearOpcionesDeUnidadDeTiempo(2);
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
        this.btnSalir.setText("Salir");
        this.btnRealizarAnalisisDePERT.setText("Realizar cálculos de tiempos");        
    }

    private void setearOpcionesDeUnidadDeTiempo(int valor){
        if (!(valor == 0)){          
            switch (valor){
                case 1:{
                    this.jrbHoras.setSelected(true);
                    this.jrbDias.setSelected(false);
                    this.jrbMeses.setSelected(false);
                    break;
                }
                case 2:{
                    this.jrbHoras.setSelected(false);
                    this.jrbDias.setSelected(true);
                    this.jrbMeses.setSelected(false);
                    break;
                }
                case 3:{
                    this.jrbHoras.setSelected(false);
                    this.jrbDias.setSelected(false);
                    this.jrbMeses.setSelected(true);
                    break;
                }
                default:{
                    setearOpcionesDeUnidadDeTiempo(2);
                    break;
                }
            }
        }else{
            switch (unidadDeTiempo){
                case horas:{
                    setearOpcionesDeUnidadDeTiempo(1);
                    break;
                }
                case dias:{
                    setearOpcionesDeUnidadDeTiempo(2);
                    break;
                }
                case meses:{
                    setearOpcionesDeUnidadDeTiempo(3);
                    break;
                }                
            } 
        }
    }

    private void setearUnidadDeTiempo(int indice){
        switch (indice){
            case 1:{
                this.unidadDeTiempo = UnidadDeTiempo.horas;
                break;
            }
            case 2:{
                this.unidadDeTiempo = UnidadDeTiempo.dias;
                break;
            }
            case 3:{
                this.unidadDeTiempo = UnidadDeTiempo.meses;
                break;
            }
        }
    }

    /**
     * Previo al almacenamiento en la estructura de datos de los datos ingresados por el usuario,
     * se verifica que los mismos sean correctos.
     * @return (si son válidos o no los datos ingresados por el usuario).
     */
    private boolean controlarDatosDeEntradaDelUsuario(){
        if ((txtNombreProyecto.getText().equals("") && (txtNombreProyecto.getText().length() > 50))){ 
            return false;
        }
        if (redDeTareas.obtenerCantidadDeTareas()==0){
            return false;
        }
        return true;
    } 
    
    /**
     * Se setean los campos de la pantalla por primera vez, con los datos correspondientes (solo en caso de ser una modificación).
     */
    private void setearCampos(){        
        if (tipoAccion.equals(tipoAccion.modificar)){            
            txtNombreProyecto.setText(nombre);
            int fila = 0;
            for (Tarea tarea : redDeTareas.obtenerTareas()){
                actualizarTablaDeDatosIngresados(fila, Accion.crear, tarea);
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
    private void actualizarTablaDeDatosIngresados(int fila, Accion accion, Tarea tarea){
        DefaultTableModel modeloDeTablaDeDatosIngresados = (DefaultTableModel)tblTareasProyecto.getModel();
        if (accion.equals(Accion.eliminar)){
            modeloDeTablaDeDatosIngresados.removeRow(fila);            
        }else{ 
            if (accion.equals(Accion.crear)) {
                modeloDeTablaDeDatosIngresados.addRow(new Object[fila]);
                tblTareasProyecto.setValueAt(tarea.obtenerNombre(), fila, 0);
            }            
            tblTareasProyecto.setValueAt(tarea.obtenerDescripcion(), fila, 1);
            tblTareasProyecto.setValueAt(tarea.obtenerPrecedencia().obtenerTareasConcatenadas(), fila, 2);
            tblTareasProyecto.setValueAt(tarea.obtenerTiempoEstimado().obtenerTiempoOptimista(), fila, 3);
            tblTareasProyecto.setValueAt(tarea.obtenerTiempoEstimado().obtenerTiempoMasProbable(), fila, 4);
            tblTareasProyecto.setValueAt(tarea.obtenerTiempoEstimado().obtenerTiempoPesimista(), fila, 5);
        }      
        tblTareasProyecto.updateUI();
    }        

    /**
     * Al modificar una tarea especifica, también se actualiza dicha tarea
     * en la tabla de datos ingresados por el usuario.
     * @param idTarea
     * @param descripcion
     */    
    public void modificarTarea(int idTarea, String descripcion){
        Tarea tareaModificada = redDeTareas.modificarTarea(idTarea, descripcion);
        for (int i = 0; i < tblTareasProyecto.getRowCount(); i++){
            String nombreTareaAux = (String)tblTareasProyecto.getValueAt(i, 0);
            int idTareaAux = FabricaDeTareas.getInstance().getIdTareaByNombre(nombreTareaAux);
            if (idTareaAux == idTarea){
                actualizarTablaDeDatosIngresados(i, Accion.modificar, tareaModificada);
                break;
            }
        }        
    }
    
    /**
     * Se almacena una tarea en el conjunto de tareas del proyecto y
     * también se actualiza la tabla de datos ingresados por el usuario (de tareas).
     * @param tarea (nueva tarea del proyecto).
     */
    public void agregarTarea(Tarea tarea){        
        redDeTareas.agregarTarea(tarea);
        actualizarTablaDeDatosIngresados(redDeTareas.obtenerCantidadDeTareas()-1, Accion.crear, tarea);
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
        if (!redDeTareas.realizarCalculosPERT()){
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
        btnRealizarAnalisisDePERT = new javax.swing.JButton();
        btnSalir = new javax.swing.JButton();
        jPanel1 = new javax.swing.JPanel();
        jrbHoras = new javax.swing.JRadioButton();
        jrbDias = new javax.swing.JRadioButton();
        jrbMeses = new javax.swing.JRadioButton();
        jScrollPane2 = new javax.swing.JScrollPane();
        jTADescripcionDelProyecto = new javax.swing.JTextArea();
        lblNombreProyecto1 = new javax.swing.JLabel();
        jMenuBar = new javax.swing.JMenuBar();
        jMenuNuevo = new javax.swing.JMenu();
        jMenuAbrir = new javax.swing.JMenu();
        jMenuGuardar = new javax.swing.JMenu();
        jMenuAyuda = new javax.swing.JMenu();

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
                java.lang.String.class, java.lang.String.class, java.lang.String.class, java.lang.String.class, java.lang.String.class, java.lang.String.class
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
        tblTareasProyecto.setCursor(new java.awt.Cursor(java.awt.Cursor.DEFAULT_CURSOR));
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

        btnRealizarAnalisisDePERT.setText("btnRealizarAnalisisDePERT");
        btnRealizarAnalisisDePERT.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnRealizarAnalisisDePERTActionPerformed(evt);
            }
        });

        btnSalir.setText("btnSalir");
        btnSalir.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnSalirActionPerformed(evt);
            }
        });

        jPanel1.setBorder(javax.swing.BorderFactory.createTitledBorder("UnidadDeTiempo"));

        jrbHoras.setText("jRadioButton1");
        jrbHoras.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jrbHorasActionPerformed(evt);
            }
        });

        jrbDias.setText("jRadioButton2");
        jrbDias.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jrbDiasActionPerformed(evt);
            }
        });

        jrbMeses.setText("jRadioButton3");
        jrbMeses.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jrbMesesActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jrbHoras)
                .addGap(18, 18, 18)
                .addComponent(jrbDias)
                .addGap(18, 18, 18)
                .addComponent(jrbMeses)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jrbHoras)
                    .addComponent(jrbDias)
                    .addComponent(jrbMeses))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        jTADescripcionDelProyecto.setColumns(20);
        jTADescripcionDelProyecto.setRows(5);
        jScrollPane2.setViewportView(jTADescripcionDelProyecto);

        lblNombreProyecto1.setText("lblDescripcionProyecto");

        jMenuBar.setCursor(new java.awt.Cursor(java.awt.Cursor.DEFAULT_CURSOR));

        jMenuNuevo.setText("Nuevo");
        jMenuNuevo.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jMenuNuevoMouseClicked(evt);
            }
        });
        jMenuNuevo.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jMenuNuevoActionPerformed(evt);
            }
        });
        jMenuBar.add(jMenuNuevo);

        jMenuAbrir.setText("Abrir");
        jMenuBar.add(jMenuAbrir);

        jMenuGuardar.setText("Guardar");
        jMenuBar.add(jMenuGuardar);

        jMenuAyuda.setText("Ayuda");
        jMenuBar.add(jMenuAyuda);

        setJMenuBar(jMenuBar);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(layout.createSequentialGroup()
                                .addComponent(lblNombreProyecto)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addComponent(txtNombreProyecto, javax.swing.GroupLayout.DEFAULT_SIZE, 222, Short.MAX_VALUE)
                                .addGap(18, 18, 18)
                                .addComponent(lblNombreProyecto1))
                            .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 323, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(layout.createSequentialGroup()
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                                    .addComponent(jScrollPane1, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, 658, Short.MAX_VALUE)
                                    .addGroup(layout.createSequentialGroup()
                                        .addComponent(lblTareasProyecto)
                                        .addGap(572, 572, 572)))
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED))
                            .addGroup(layout.createSequentialGroup()
                                .addComponent(btnRealizarAnalisisDePERT, javax.swing.GroupLayout.DEFAULT_SIZE, 177, Short.MAX_VALUE)
                                .addGap(487, 487, 487)))
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                .addComponent(btnAgregar, javax.swing.GroupLayout.DEFAULT_SIZE, 116, Short.MAX_VALUE)
                                .addComponent(btnModificar, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addComponent(btnBorrar, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addComponent(btnBorrarTodas, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                            .addComponent(btnSalir, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, 116, Short.MAX_VALUE))))
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addGroup(layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(lblNombreProyecto)
                            .addComponent(txtNombreProyecto, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(lblNombreProyecto1))
                        .addGap(18, 18, 18)
                        .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addComponent(jScrollPane2, 0, 0, Short.MAX_VALUE))
                .addGap(20, 20, 20)
                .addComponent(lblTareasProyecto)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                        .addComponent(btnAgregar)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(btnModificar)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(btnBorrar)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(btnBorrarTodas)
                        .addGap(100, 100, 100))
                    .addComponent(jScrollPane1, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 234, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(btnRealizarAnalisisDePERT)
                    .addComponent(btnSalir))
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
            redDeTareas.borrarTarea(FabricaDeTareas.getInstance().getIdTareaByNombre(nombreTarea));
            actualizarTablaDeDatosIngresados(filaSeleccionada, Accion.eliminar, null);
        }else{
            JOptionPane.showMessageDialog(this, "Debe seleccionar una fila");
        }        
    }//GEN-LAST:event_btnBorrarActionPerformed

    private void btnBorrarTodasActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnBorrarTodasActionPerformed
        for (int fila = 0; fila < redDeTareas.obtenerCantidadDeTareas(); fila++){
            actualizarTablaDeDatosIngresados(0, Accion.eliminar, null);
        }
        redDeTareas = new RedDeTareas(new ArrayList<Tarea>());
        FabricaDeTareas.getInstance().reset();
    }//GEN-LAST:event_btnBorrarTodasActionPerformed

    private void btnRealizarAnalisisDePERTActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnRealizarAnalisisDePERTActionPerformed
        if (redDeTareas.obtenerCantidadDeTareas() > 0){
            if (!redDeTareas.elUltimoCalculoPERTesCorrecto()){
                realizarCalculosPERT();
            }
            FormResultado formResultado = new FormResultado(redDeTareas, unidadDeTiempo);
            formResultado.setVisible(true);
        }
    }//GEN-LAST:event_btnRealizarAnalisisDePERTActionPerformed

    private void btnSalirActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnSalirActionPerformed
        this.dispose();
    }//GEN-LAST:event_btnSalirActionPerformed

    private void jrbHorasActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jrbHorasActionPerformed
        this.setearOpcionesDeUnidadDeTiempo(1);
        this.setearUnidadDeTiempo(1);
    }//GEN-LAST:event_jrbHorasActionPerformed

    private void jrbDiasActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jrbDiasActionPerformed
        this.setearOpcionesDeUnidadDeTiempo(2);
        this.setearUnidadDeTiempo(2);
    }//GEN-LAST:event_jrbDiasActionPerformed

    private void jrbMesesActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jrbMesesActionPerformed
        this.setearOpcionesDeUnidadDeTiempo(3);
        this.setearUnidadDeTiempo(3);
    }//GEN-LAST:event_jrbMesesActionPerformed

    private void jMenuNuevoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jMenuNuevoActionPerformed

    }//GEN-LAST:event_jMenuNuevoActionPerformed

    private void jMenuNuevoMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jMenuNuevoMouseClicked
        this.nombre = "";
        this.txtNombreProyecto.setText("");
        this.descripcion = "";
        this.jTADescripcionDelProyecto.setText("");
        this.setearOpcionesDeUnidadDeTiempo(2);
        this.setearUnidadDeTiempo(2);
        this.btnBorrarTodasActionPerformed(null);
    }//GEN-LAST:event_jMenuNuevoMouseClicked

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
    private javax.swing.JButton btnModificar;
    private javax.swing.JButton btnRealizarAnalisisDePERT;
    private javax.swing.JButton btnSalir;
    private javax.swing.JMenu jMenuAbrir;
    private javax.swing.JMenu jMenuAyuda;
    private javax.swing.JMenuBar jMenuBar;
    private javax.swing.JMenu jMenuGuardar;
    private javax.swing.JMenu jMenuNuevo;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JTextArea jTADescripcionDelProyecto;
    private javax.swing.JRadioButton jrbDias;
    private javax.swing.JRadioButton jrbHoras;
    private javax.swing.JRadioButton jrbMeses;
    private javax.swing.JLabel lblNombreProyecto;
    private javax.swing.JLabel lblNombreProyecto1;
    private javax.swing.JLabel lblTareasProyecto;
    private javax.swing.JTable tblTareasProyecto;
    private javax.swing.JTextField txtNombreProyecto;
    // End of variables declaration//GEN-END:variables

}
