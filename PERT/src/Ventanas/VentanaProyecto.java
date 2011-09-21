/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

/*
 * VentanaProyecto.java
 *
 * Created on 02/03/2011, 11:26:21
 */

package Ventanas;

import Demo.Demo1;
import Entidades.*;
import java.io.File;
import java.util.ArrayList;
import java.util.List;
import java.util.Locale;
import java.util.ResourceBundle;
import javax.swing.JFileChooser;
import javax.swing.JOptionPane;
import javax.swing.border.TitledBorder;
import javax.swing.table.DefaultTableModel;

/**
 *
 * @author Manuel Lorenze
 */
public class VentanaProyecto extends javax.swing.JFrame {
  
    Locale lugarConfigurado;
    ResourceBundle etiquetas;
    private Accion tipoAccion;
    private String nombre;
    private String descripcion;
    private RedDeTareas redDeTareas;
    private UnidadDeTiempo unidadDeTiempo;
    
    /** Creates new form VentanaProyecto */
    public VentanaProyecto(String lenguajeIdioma, String paisIdioma) {
        initComponents();
        lugarConfigurado = new Locale(lenguajeIdioma, paisIdioma);
        etiquetas = ResourceBundle.getBundle("Idiomas.MessagesBundle", lugarConfigurado);
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
        setTitle(etiquetas.getString("titulo"));
        this.jMenuArchivo.setText(etiquetas.getString("archivo"));
        this.jmiNuevo.setText(etiquetas.getString("nuevo"));
        this.jmiAbrir.setText(etiquetas.getString("abrir"));
        this.jmiGuardar.setText(etiquetas.getString("guardar"));
        this.jMenuIdioma.setText(etiquetas.getString("idioma"));
        this.jmiSalir.setText(etiquetas.getString("salir"));
        this.jMenuAyuda.setText(etiquetas.getString("ayuda"));
        this.lblNombreProyecto.setText(etiquetas.getString("nombreProyecto"));
        this.lblTareasProyecto.setText(etiquetas.getString("tareasProyecto"));
        this.lblDescripcionProyecto.setText(etiquetas.getString("descripcionProyecto"));
        ((TitledBorder)jplUnidadDeTiempo.getBorder()).setTitle(etiquetas.getString("unidadDeTiempo"));
        this.jrbHoras.setText(etiquetas.getString("horas"));
        this.jrbDias.setText(etiquetas.getString("dias"));
        this.jrbMeses.setText(etiquetas.getString("meses"));        
        this.btnAgregar.setText(etiquetas.getString("agregar"));
        this.btnModificar.setText(etiquetas.getString("modificar"));
        this.btnBorrar.setText(etiquetas.getString("borrar"));
        this.btnBorrarTodas.setText(etiquetas.getString("borrarTodas"));        
        this.btnSalir.setText(etiquetas.getString("salir"));
        this.btnAnalisisDePERT.setText(etiquetas.getString("analisisDePERT")); 
        /**String[] columnasDeTablaDeTareas = {
            etiquetas.getString("nombreTarea"),
            etiquetas.getString("descripcionTarea"),
            etiquetas.getString("precedenciaTarea"),
            etiquetas.getString("tiempoOptimistaTarea"),
            etiquetas.getString("tiempoMasProbableTarea"),
            etiquetas.getString("tiempoPesimistaTarea")
        };
        Object[][] datos = {{"1", "2", "3", "4", "5", "6"}};
        DefaultTableModel modeloDeTablaDeTareas = new DefaultTableModel(datos, columnasDeTablaDeTareas);
        this.tblTareasProyecto= new JTable(modeloDeTablaDeTareas);**/
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
        txtNombreProyecto.setText(nombre);
        setearOpcionesDeUnidadDeTiempo(0);
        this.jTADescripcionDelProyecto.setText(descripcion);
        int fila = 0;
        for (Tarea tarea : redDeTareas.obtenerTareas()){
            actualizarTablaDeDatosIngresados(fila, Accion.crear, tarea);
            fila += 1;
        }
    }    
    
    private void limpiarPantallaProyecto(){
        btnBorrarTodasActionPerformed(null);
        this.txtNombreProyecto.setText("");
        this.jTADescripcionDelProyecto.setText("");        
        setearUnidadDeTiempo(2);
        setearOpcionesDeUnidadDeTiempo(2);
    }
    
    /**
     * Se modifica la tabla de tareas del proyecto en la cual se muestran los datos ingresados por el usuario
     * acerca de cada una de ellas (nombre, descripción, precedencia y tiempos estimados).
     * @param fila (fila de la tabla a agregar o eliminar).
     * @param nuevaFila (determina si se trata de una nueva fila a agregar o de eliminar una existente).
     * @param tarea (tarea que forma parte de la modificación).
     */
    private void actualizarTablaDeDatosIngresados(int fila, Accion accion, Tarea tarea){
        DefaultTableModel modeloDeTablaDeDatosIngresados = (DefaultTableModel)this.tblTareasProyecto.getModel();
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
    
    private void salirDelSistema(){
        int seleccion = JOptionPane.showOptionDialog(
           this,
           etiquetas.getString("mensajeSalirProyecto"), 
           etiquetas.getString("tituloSalirProyecto"), 
           JOptionPane.OK_CANCEL_OPTION,
           JOptionPane.WARNING_MESSAGE,
           null,    
           new Object[] { etiquetas.getString("ok"), etiquetas.getString("cancelar") },   
           "OK");
        if (seleccion == 0){
            this.dispose();
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
        btnAnalisisDePERT = new javax.swing.JButton();
        btnSalir = new javax.swing.JButton();
        jplUnidadDeTiempo = new javax.swing.JPanel();
        jrbHoras = new javax.swing.JRadioButton();
        jrbDias = new javax.swing.JRadioButton();
        jrbMeses = new javax.swing.JRadioButton();
        jScrollPane2 = new javax.swing.JScrollPane();
        jTADescripcionDelProyecto = new javax.swing.JTextArea();
        lblDescripcionProyecto = new javax.swing.JLabel();
        jMenuBar = new javax.swing.JMenuBar();
        jMenuArchivo = new javax.swing.JMenu();
        jmiNuevo = new javax.swing.JMenuItem();
        jmiAbrir = new javax.swing.JMenuItem();
        jmiGuardar = new javax.swing.JMenuItem();
        jmiGuardarComo = new javax.swing.JMenuItem();
        jSeparator1 = new javax.swing.JPopupMenu.Separator();
        jmiSalir = new javax.swing.JMenuItem();
        jMenuIdioma = new javax.swing.JMenu();
        jmiEspañol = new javax.swing.JMenuItem();
        jmiInglés = new javax.swing.JMenuItem();
        jmiPortugues = new javax.swing.JMenuItem();
        jMenuDemos = new javax.swing.JMenu();
        jmiDemo1 = new javax.swing.JMenuItem();
        jMenuItem1 = new javax.swing.JMenuItem();
        jMenuItem2 = new javax.swing.JMenuItem();
        jMenuAyuda = new javax.swing.JMenu();
        jmiAyudaContenidos = new javax.swing.JMenuItem();
        jSeparator2 = new javax.swing.JPopupMenu.Separator();
        jmiAcercaDe = new javax.swing.JMenuItem();

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
                java.lang.String.class, java.lang.String.class, java.lang.String.class, java.lang.Double.class, java.lang.Double.class, java.lang.Double.class
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

        btnAnalisisDePERT.setText("btnAnalisisDePERT");
        btnAnalisisDePERT.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnAnalisisDePERTActionPerformed(evt);
            }
        });

        btnSalir.setText("btnSalir");
        btnSalir.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnSalirActionPerformed(evt);
            }
        });

        jplUnidadDeTiempo.setBorder(javax.swing.BorderFactory.createTitledBorder("UnidadDeTiempo"));

        jrbHoras.setText("jrbHoras");
        jrbHoras.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jrbHorasActionPerformed(evt);
            }
        });

        jrbDias.setText("jrbDias");
        jrbDias.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jrbDiasActionPerformed(evt);
            }
        });

        jrbMeses.setText("jrbMeses");
        jrbMeses.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jrbMesesActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jplUnidadDeTiempoLayout = new javax.swing.GroupLayout(jplUnidadDeTiempo);
        jplUnidadDeTiempo.setLayout(jplUnidadDeTiempoLayout);
        jplUnidadDeTiempoLayout.setHorizontalGroup(
            jplUnidadDeTiempoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jplUnidadDeTiempoLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jrbHoras)
                .addGap(18, 18, 18)
                .addComponent(jrbDias)
                .addGap(18, 18, 18)
                .addComponent(jrbMeses)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        jplUnidadDeTiempoLayout.setVerticalGroup(
            jplUnidadDeTiempoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jplUnidadDeTiempoLayout.createSequentialGroup()
                .addGroup(jplUnidadDeTiempoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jrbHoras)
                    .addComponent(jrbDias)
                    .addComponent(jrbMeses))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        jTADescripcionDelProyecto.setColumns(20);
        jTADescripcionDelProyecto.setRows(5);
        jScrollPane2.setViewportView(jTADescripcionDelProyecto);

        lblDescripcionProyecto.setText("lblDescripcionProyecto");

        jMenuBar.setBorder(null);
        jMenuBar.setCursor(new java.awt.Cursor(java.awt.Cursor.DEFAULT_CURSOR));

        jMenuArchivo.setText("Archivo");

        jmiNuevo.setAccelerator(javax.swing.KeyStroke.getKeyStroke(java.awt.event.KeyEvent.VK_N, java.awt.event.InputEvent.SHIFT_MASK | java.awt.event.InputEvent.CTRL_MASK));
        jmiNuevo.setText("Nuevo");
        jmiNuevo.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jmiNuevoActionPerformed(evt);
            }
        });
        jMenuArchivo.add(jmiNuevo);

        jmiAbrir.setAccelerator(javax.swing.KeyStroke.getKeyStroke(java.awt.event.KeyEvent.VK_O, java.awt.event.InputEvent.SHIFT_MASK | java.awt.event.InputEvent.CTRL_MASK));
        jmiAbrir.setText("Abrir");
        jmiAbrir.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jmiAbrirActionPerformed(evt);
            }
        });
        jMenuArchivo.add(jmiAbrir);

        jmiGuardar.setAccelerator(javax.swing.KeyStroke.getKeyStroke(java.awt.event.KeyEvent.VK_S, java.awt.event.InputEvent.CTRL_MASK));
        jmiGuardar.setText("Guardar");
        jMenuArchivo.add(jmiGuardar);

        jmiGuardarComo.setAccelerator(javax.swing.KeyStroke.getKeyStroke(java.awt.event.KeyEvent.VK_S, java.awt.event.InputEvent.SHIFT_MASK | java.awt.event.InputEvent.CTRL_MASK));
        jmiGuardarComo.setText("Guardar como");
        jMenuArchivo.add(jmiGuardarComo);
        jMenuArchivo.add(jSeparator1);

        jmiSalir.setText("Salir");
        jmiSalir.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jmiSalirActionPerformed(evt);
            }
        });
        jMenuArchivo.add(jmiSalir);

        jMenuBar.add(jMenuArchivo);

        jMenuIdioma.setText("Idioma");

        jmiEspañol.setAccelerator(javax.swing.KeyStroke.getKeyStroke(java.awt.event.KeyEvent.VK_E, java.awt.event.InputEvent.CTRL_MASK));
        jmiEspañol.setText("Español");
        jmiEspañol.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jmiEspañolActionPerformed(evt);
            }
        });
        jMenuIdioma.add(jmiEspañol);

        jmiInglés.setAccelerator(javax.swing.KeyStroke.getKeyStroke(java.awt.event.KeyEvent.VK_I, java.awt.event.InputEvent.CTRL_MASK));
        jmiInglés.setText("Inglés");
        jmiInglés.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jmiInglésActionPerformed(evt);
            }
        });
        jMenuIdioma.add(jmiInglés);

        jmiPortugues.setAccelerator(javax.swing.KeyStroke.getKeyStroke(java.awt.event.KeyEvent.VK_P, java.awt.event.InputEvent.CTRL_MASK));
        jmiPortugues.setText("Portugués");
        jmiPortugues.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jmiPortuguesActionPerformed(evt);
            }
        });
        jMenuIdioma.add(jmiPortugues);

        jMenuBar.add(jMenuIdioma);

        jMenuDemos.setText("Demos");

        jmiDemo1.setText("Demo 1 (10 tareas)");
        jmiDemo1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jmiDemo1ActionPerformed(evt);
            }
        });
        jMenuDemos.add(jmiDemo1);

        jMenuItem1.setText("Demo 2 ");
        jMenuDemos.add(jMenuItem1);

        jMenuItem2.setText("Demo 3");
        jMenuDemos.add(jMenuItem2);

        jMenuBar.add(jMenuDemos);

        jMenuAyuda.setText("Ayuda");

        jmiAyudaContenidos.setAccelerator(javax.swing.KeyStroke.getKeyStroke(java.awt.event.KeyEvent.VK_F1, 0));
        jmiAyudaContenidos.setText("Ayuda Contenidos");
        jmiAyudaContenidos.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jmiAyudaContenidosActionPerformed(evt);
            }
        });
        jMenuAyuda.add(jmiAyudaContenidos);
        jMenuAyuda.add(jSeparator2);

        jmiAcercaDe.setText("Acerca de");
        jmiAcercaDe.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jmiAcercaDeActionPerformed(evt);
            }
        });
        jMenuAyuda.add(jmiAcercaDe);

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
                                .addComponent(lblDescripcionProyecto))
                            .addComponent(jplUnidadDeTiempo, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
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
                                .addComponent(btnAnalisisDePERT, javax.swing.GroupLayout.DEFAULT_SIZE, 177, Short.MAX_VALUE)
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
                            .addComponent(lblDescripcionProyecto))
                        .addGap(18, 18, 18)
                        .addComponent(jplUnidadDeTiempo, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
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
                    .addComponent(btnAnalisisDePERT)
                    .addComponent(btnSalir))
                .addContainerGap())
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void btnAgregarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnAgregarActionPerformed
        if (FabricaDeTareas.getInstance().esPosibleCrearNuevaTarea()){
            VentanaTarea ventanaTarea = new VentanaTarea(this, true);
            ventanaTarea.setVisible(true);
        }else{
            JOptionPane.showMessageDialog(this, "No se pueden crear más tareas (límite = 26)");
        }
    }//GEN-LAST:event_btnAgregarActionPerformed

    private void btnModificarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnModificarActionPerformed
        int filaSeleccionada = tblTareasProyecto.getSelectedRow();
        if (filaSeleccionada >= 0){
            String nombreTarea = (String)tblTareasProyecto.getValueAt(filaSeleccionada, 0);
            Tarea tarea = redDeTareas.obtenerTareaPorID(FabricaDeTareas.getInstance().getIdTareaByNombre(nombreTarea));
            VentanaTarea ventanaTarea = new VentanaTarea(this, true, tarea);
            ventanaTarea.setVisible(true);            
        }else{
            JOptionPane.showMessageDialog(this, "Debe seleccionar una fila");
        }
    }//GEN-LAST:event_btnModificarActionPerformed

    private void btnBorrarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnBorrarActionPerformed
        int filaSeleccionada = tblTareasProyecto.getSelectedRow();
        if (filaSeleccionada >= 0){
            String nombreTarea = (String)tblTareasProyecto.getValueAt(filaSeleccionada, 0);
            int idTarea = FabricaDeTareas.getInstance().getIdTareaByNombre(nombreTarea);
            redDeTareas.borrarTarea(idTarea);
            actualizarTablaDeDatosIngresados(filaSeleccionada, Accion.eliminar, null);
            FabricaDeTareas.getInstance().restaurarIdTarea(idTarea);
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

    private void btnAnalisisDePERTActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnAnalisisDePERTActionPerformed
        if (redDeTareas.obtenerCantidadDeTareas() > 0){
            if (!redDeTareas.elUltimoCalculoPERTesCorrecto()){
                realizarCalculosPERT();
            }
            VentanaResultados ventanaResultados = new VentanaResultados(this, true, redDeTareas, unidadDeTiempo);
            ventanaResultados.setVisible(true);
        }
    }//GEN-LAST:event_btnAnalisisDePERTActionPerformed

    private void btnSalirActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnSalirActionPerformed
        salirDelSistema();
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

    private void jmiEspañolActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jmiEspañolActionPerformed
        lugarConfigurado = new Locale("es", "UY");
        etiquetas = ResourceBundle.getBundle("Idiomas.MessagesBundle", lugarConfigurado);
        this.setearEtiquetas();
    }//GEN-LAST:event_jmiEspañolActionPerformed

    private void jmiInglésActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jmiInglésActionPerformed
        lugarConfigurado = new Locale("en", "US");
        etiquetas = ResourceBundle.getBundle("Idiomas.MessagesBundle", lugarConfigurado);
        this.setearEtiquetas();
    }//GEN-LAST:event_jmiInglésActionPerformed

    private void jmiPortuguesActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jmiPortuguesActionPerformed
        lugarConfigurado = new Locale("po", "BR");
        etiquetas = ResourceBundle.getBundle("Idiomas.MessagesBundle", lugarConfigurado);
        this.setearEtiquetas();
    }//GEN-LAST:event_jmiPortuguesActionPerformed

    private void jmiNuevoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jmiNuevoActionPerformed
        int seleccion = JOptionPane.showOptionDialog(
           this,
           etiquetas.getString("mensajeNuevoProyecto"), 
           etiquetas.getString("tituloNuevoProyecto"), 
           JOptionPane.OK_CANCEL_OPTION,
           JOptionPane.WARNING_MESSAGE,
           null,    
           new Object[] { etiquetas.getString("ok"), etiquetas.getString("cancelar") },   
           "OK");
        if (seleccion == 0){
            this.nombre = "";
            this.txtNombreProyecto.setText("");
            this.descripcion = "";
            this.jTADescripcionDelProyecto.setText("");
            this.setearOpcionesDeUnidadDeTiempo(2);
            this.setearUnidadDeTiempo(2);
            this.btnBorrarTodasActionPerformed(null);
        }        
    }//GEN-LAST:event_jmiNuevoActionPerformed

    private void jmiSalirActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jmiSalirActionPerformed
        salirDelSistema();   
    }//GEN-LAST:event_jmiSalirActionPerformed

    private void jmiAbrirActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jmiAbrirActionPerformed
        JFileChooser fileChooser = new JFileChooser();
        int seleccion = fileChooser.showOpenDialog(jmiAbrir);
        if (seleccion == JFileChooser.APPROVE_OPTION)
        {
           File fichero = fileChooser.getSelectedFile();
           // y a trabajar con fichero ....
        }
    }//GEN-LAST:event_jmiAbrirActionPerformed

    private void jmiDemo1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jmiDemo1ActionPerformed
        limpiarPantallaProyecto();       
        Demo1 demo1 = new Demo1();
        this.tipoAccion = demo1.obtenerTipoAccion();
        this.nombre = demo1.obtenerNombre();
        this.descripcion = demo1.obtenerDescripcion();
        this.unidadDeTiempo = demo1.obtenerUnidadDeTiempo();
        this.redDeTareas = demo1.obtenerRedDeTareas();
        setearCampos();
    }//GEN-LAST:event_jmiDemo1ActionPerformed

    private void jmiAyudaContenidosActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jmiAyudaContenidosActionPerformed
        System.out.println("AYUDA!!!");
    }//GEN-LAST:event_jmiAyudaContenidosActionPerformed

    private void jmiAcercaDeActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jmiAcercaDeActionPerformed
        VentanaAcercaDe ventanaAcercaDe = new VentanaAcercaDe(this, true);
        ventanaAcercaDe.setVisible(true);
    }//GEN-LAST:event_jmiAcercaDeActionPerformed

    // Este main se deja sin efecto dado que el inicio del programa se maneja desde la clase pert/Main.java
    /**
    * @param args the command line arguments
    */
    /*public static void main(String args[]) {
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new VentanaProyecto().setVisible(true);
            }
        });
    }*/

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnAgregar;
    private javax.swing.JButton btnAnalisisDePERT;
    private javax.swing.JButton btnBorrar;
    private javax.swing.JButton btnBorrarTodas;
    private javax.swing.JButton btnModificar;
    private javax.swing.JButton btnSalir;
    private javax.swing.JMenu jMenuArchivo;
    private javax.swing.JMenu jMenuAyuda;
    private javax.swing.JMenuBar jMenuBar;
    private javax.swing.JMenu jMenuDemos;
    private javax.swing.JMenu jMenuIdioma;
    private javax.swing.JMenuItem jMenuItem1;
    private javax.swing.JMenuItem jMenuItem2;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JPopupMenu.Separator jSeparator1;
    private javax.swing.JPopupMenu.Separator jSeparator2;
    private javax.swing.JTextArea jTADescripcionDelProyecto;
    private javax.swing.JMenuItem jmiAbrir;
    private javax.swing.JMenuItem jmiAcercaDe;
    private javax.swing.JMenuItem jmiAyudaContenidos;
    private javax.swing.JMenuItem jmiDemo1;
    private javax.swing.JMenuItem jmiEspañol;
    private javax.swing.JMenuItem jmiGuardar;
    private javax.swing.JMenuItem jmiGuardarComo;
    private javax.swing.JMenuItem jmiInglés;
    private javax.swing.JMenuItem jmiNuevo;
    private javax.swing.JMenuItem jmiPortugues;
    private javax.swing.JMenuItem jmiSalir;
    private javax.swing.JPanel jplUnidadDeTiempo;
    private javax.swing.JRadioButton jrbDias;
    private javax.swing.JRadioButton jrbHoras;
    private javax.swing.JRadioButton jrbMeses;
    private javax.swing.JLabel lblDescripcionProyecto;
    private javax.swing.JLabel lblNombreProyecto;
    private javax.swing.JLabel lblTareasProyecto;
    private javax.swing.JTable tblTareasProyecto;
    private javax.swing.JTextField txtNombreProyecto;
    // End of variables declaration//GEN-END:variables

}
