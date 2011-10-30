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

import Entidades.Estados.Accion;
import Demo.Demo1;
import Demo.Demo2;
import Demo.Demo3;
import Demo.IDemo;
import Entidades.*;
import EntradaSalida.ManejadorDeArchivos;
import EntradaSalida.ProyectoES;
import java.io.File;
import java.util.ArrayList;
import java.util.List;
import java.util.Locale;
import java.util.ResourceBundle;
import javax.help.HelpBroker;
import javax.swing.JFileChooser;
import javax.swing.JOptionPane;
import javax.swing.SwingConstants;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.DefaultTableModel;

/**
 *
 * @author Manuel Lorenze
 */
public class VentanaProyecto extends javax.swing.JFrame {
  
    private Locale lugarConfigurado;
    private ResourceBundle etiquetas;   
    private HelpBroker helpBroker;
    private String pathDeArchivoDelProyecto;
    
    private DefaultTableModel modeloDeTablaDeTareas;
    
    private Proyecto proyecto;    
    
    /** Creates new form VentanaProyecto */
    public VentanaProyecto(Locale lugarConfigurado, HelpBroker helpBroker) {
        initComponents();   
        this.lugarConfigurado = lugarConfigurado;
        this.etiquetas = ResourceBundle.getBundle("Idiomas.MessagesBundlePert", lugarConfigurado);
        this.helpBroker = helpBroker;       
        this.pathDeArchivoDelProyecto = null;        
        crearProyectoInicial();
        habilitarAyuda();                
        inicializarTabla();
        setearEtiquetas();  
        setearDatosEnLosCampos();
    }  

    private void crearProyectoInicial(){
        FabricaDeTareas.getInstance().reset();
        String nombre = this.etiquetas.getString("proyectoSugerenciaNombreProyecto");        
        String descripcion = this.etiquetas.getString("proyectoSugerenciaDescripcionProyecto");
        RedDeTareas redDeTareas = new RedDeTareas(new ArrayList<Tarea>());
        String unidadDeTiempo = this.etiquetas.getString("proyectoSugerenciaUnidadDeTiempo"); 
        this.proyecto = new Proyecto(nombre, descripcion, redDeTareas, unidadDeTiempo);         
    }
    
    private void habilitarAyuda(){
        if (this.helpBroker != null){
            this.helpBroker.enableHelpOnButton(this.subMenu_AyudaContenidos, "aplicacion", this.helpBroker.getHelpSet());
            this.helpBroker.enableHelpKey(this.getContentPane(), "aplicacion", this.helpBroker.getHelpSet());
        }
    }
    
    private void inicializarTabla(){
        DefaultTableCellRenderer dtcr = new DefaultTableCellRenderer();
        dtcr.setHorizontalAlignment(SwingConstants.CENTER);      
        
        this.modeloDeTablaDeTareas = new DefaultTableModel(0, 6);
        this.tabla_TareasProyecto.setModel(this.modeloDeTablaDeTareas);                  
        this.tabla_TareasProyecto.getColumnModel().getColumn(0).setMaxWidth(100);
        this.tabla_TareasProyecto.getColumnModel().getColumn(0).setResizable(false); 
        this.tabla_TareasProyecto.getColumnModel().getColumn(0).setCellRenderer(dtcr);
        this.tabla_TareasProyecto.getColumnModel().getColumn(1).setCellRenderer(dtcr);        
        this.tabla_TareasProyecto.getColumnModel().getColumn(2).setCellRenderer(dtcr);        
        this.tabla_TareasProyecto.getColumnModel().getColumn(3).setCellRenderer(dtcr);        
        this.tabla_TareasProyecto.getColumnModel().getColumn(4).setCellRenderer(dtcr);        
        this.tabla_TareasProyecto.getColumnModel().getColumn(5).setCellRenderer(dtcr);
    }
    
    /**
     * Se setean las etiquetas de la pantalla según el idioma configurado.
     */
    private void setearEtiquetas(){
        setTitle(this.etiquetas.getString("proyectoTitulo"));
        this.menu_Archivo.setText(this.etiquetas.getString("proyectoMenuArchivo"));
        this.subMenu_Nuevo.setText(this.etiquetas.getString("proyectoSubMenuNuevo"));
        this.subMenu_Abrir.setText(this.etiquetas.getString("proyectoSubMenuAbrir"));
        this.subMenu_Guardar.setText(this.etiquetas.getString("proyectoSubMenuGuardar"));
        this.subMenu_GuardarComo.setText(this.etiquetas.getString("proyectoSubMenuGuardarComo"));
        this.subMenu_Salir.setText(this.etiquetas.getString("proyectoSubMenuSalir"));
        this.menu_Idioma.setText(this.etiquetas.getString("proyectoMenuIdioma"));
        this.subMenu_Español.setText(this.etiquetas.getString("proyectoSubMenuEspañol"));
        this.subMenu_Español.setText(this.etiquetas.getString("proyectoSubMenuEspañol"));
        this.subMenu_Español.setText(this.etiquetas.getString("proyectoSubMenuEspañol"));
        this.subMenu_Ingles.setText(this.etiquetas.getString("proyectoSubMenuIngles"));
        this.subMenu_Portugues.setText(this.etiquetas.getString("proyectoSubMenuPortugues"));
        this.menu_Demos.setText(this.etiquetas.getString("proyectoMenuDemos"));
        this.subMenu_Demo1.setText(this.etiquetas.getString("proyectoSubMenuDemo1"));
        this.subMenu_Demo2.setText(this.etiquetas.getString("proyectoSubMenuDemo2"));
        this.subMenu_Demo3.setText(this.etiquetas.getString("proyectoSubMenuDemo3"));
        this.menu_Ayuda.setText(this.etiquetas.getString("proyectoMenuAyuda"));
        this.subMenu_AyudaContenidos.setText(this.etiquetas.getString("proyectoSubMenuAyudaContenidos"));
        this.subMenu_AcercaDe.setText(this.etiquetas.getString("proyectoSubMenuAcercaDe"));
        
        this.label_NombreProyecto.setText(this.etiquetas.getString("proyectoLabelNombreProyecto"));
        this.label_UnidadDeTiempo.setText(this.etiquetas.getString("proyectoLabelUnidadDeTiempo"));
        this.label_CantidadTareas.setText(this.etiquetas.getString("proyectoLabelCantidadTareas"));        
        this.label_DescripcionProyecto.setText(this.etiquetas.getString("proyectoLabelDescripcionProyecto"));
        this.label_TareasProyecto.setText(this.etiquetas.getString("proyectoLabelTareasProyecto"));
        
        this.boton_AgregarTarea.setText(this.etiquetas.getString("proyectoBotonAgregarTarea"));
        this.boton_ModificarTarea.setText(this.etiquetas.getString("proyectoBotonModificarTarea"));
        this.boton_BorrarTarea.setText(this.etiquetas.getString("proyectoBotonBorrarTarea"));
        this.boton_BorrarTodasLasTareas.setText(this.etiquetas.getString("proyectoBotonBorrarTodasLasTareas"));        
        this.boton_Salir.setText(this.etiquetas.getString("proyectoBotonSalir"));
        this.boton_AnalisisPERT.setText(this.etiquetas.getString("proyectoBotonAnalisisPERT")); 
        
        this.tabla_TareasProyecto.getColumnModel().getColumn(0).setHeaderValue(this.etiquetas.getString("proyectoTablaColumnaNombreTarea"));
        this.tabla_TareasProyecto.getColumnModel().getColumn(1).setHeaderValue(this.etiquetas.getString("proyectoTablaColumnaDescripcionTarea"));
        this.tabla_TareasProyecto.getColumnModel().getColumn(2).setHeaderValue(this.etiquetas.getString("proyectoTablaColumnaPrecedenciaTarea"));
        this.tabla_TareasProyecto.getColumnModel().getColumn(3).setHeaderValue(this.etiquetas.getString("proyectoTablaColumnaTiempoOptimistaTarea"));
        this.tabla_TareasProyecto.getColumnModel().getColumn(4).setHeaderValue(this.etiquetas.getString("proyectoTablaColumnaTiempoMasProbableTarea"));
        this.tabla_TareasProyecto.getColumnModel().getColumn(5).setHeaderValue(this.etiquetas.getString("proyectoTablaColumnaTiempoPesimistaTarea"));
    }   
    
    /**
     * Se setean los campos de la pantalla, con los datos correspondientes.
     */
    private void setearDatosEnLosCampos(){                                              
        this.campoTexto_NombreProyecto.setText(this.proyecto.obtenerNombre());     
        this.areaTexto_DescripcionProyecto.setText(this.proyecto.obtenerDescripcion());
        this.campoTexto_CantidadTareas.setText(String.valueOf(this.proyecto.obtenerRedDeTareas().obtenerCantidadDeTareas()));        
        this.campoTexto_UnidadDeTiempo.setText(this.proyecto.obtenerUnidadDeTiempo());
        int fila = 0;
        for (Tarea tarea : this.proyecto.obtenerRedDeTareas().obtenerTareas()){
            actualizarTablaDeTareas(fila, Accion.crear, tarea);
            fila += 1;
        }
    }    
    
    /**
     * Previo al almacenamiento en la estructura de datos de los datos ingresados por el usuario,
     * se verifica que los mismos sean correctos.
     * @return (si son válidos o no los datos ingresados por el usuario).
     */
    private boolean obtenerDatosDeLosCampos(){
        if ((this.campoTexto_NombreProyecto.getText().equals("") && (this.campoTexto_NombreProyecto.getText().length() > 40))){ 
            return false;
        }else{
            this.proyecto.setearNombre(this.campoTexto_NombreProyecto.getText());
        }
        if (this.areaTexto_DescripcionProyecto.getText().length() > 500){ 
            return false;
        }else{
            this.proyecto.setearDescripcion(this.areaTexto_DescripcionProyecto.getText());
        }
        if ((this.campoTexto_UnidadDeTiempo.getText().equals("") && (this.campoTexto_UnidadDeTiempo.getText().length() > 15))){
            return false;
        }else{
            this.proyecto.setearUnidadDeTiempo(this.campoTexto_UnidadDeTiempo.getText());
        }    
        if (this.proyecto.obtenerRedDeTareas().obtenerCantidadDeTareas()==0){
            return false;
        }
        return true;
    }            
    
    /**
     * Se modifica la tabla de tareas del proyecto en la cual se muestran los datos ingresados por el usuario
     * acerca de cada una de ellas (nombre, descripción, precedencia y tiempos estimados).
     * @param fila (fila de la tabla a agregar o eliminar).
     * @param nuevaFila (determina si se trata de una nueva fila a agregar o de eliminar una existente).
     * @param tarea (tarea que forma parte de la modificación).
     */
    private void actualizarTablaDeTareas(int fila, Accion accion, Tarea tarea){
        if (accion != null){
            if (accion.equals(Accion.eliminar)){
                this.modeloDeTablaDeTareas.removeRow(fila);            
            }else{ 
                if (accion.equals(Accion.crear)) {
                    this.modeloDeTablaDeTareas.addRow(new Object[fila]);
                    this.tabla_TareasProyecto.setValueAt(tarea.obtenerNombre(), fila, 0);
                }            
                this.tabla_TareasProyecto.setValueAt(tarea.obtenerDescripcion(), fila, 1);
                this.tabla_TareasProyecto.setValueAt(tarea.obtenerPrecedencia().obtenerTareasConcatenadas(), fila, 2);
                this.tabla_TareasProyecto.setValueAt(tarea.obtenerTiempoEstimado().obtenerTiempoOptimista(), fila, 3);
                this.tabla_TareasProyecto.setValueAt(tarea.obtenerTiempoEstimado().obtenerTiempoMasProbable(), fila, 4);
                this.tabla_TareasProyecto.setValueAt(tarea.obtenerTiempoEstimado().obtenerTiempoPesimista(), fila, 5);
            }      
            this.tabla_TareasProyecto.updateUI();
        }
    }        
    
    /**
     * Se almacena una tarea en el conjunto de tareas del proyecto y
     * también se actualiza la tabla de datos ingresados por el usuario (de tareas).
     * @param tarea (nueva tarea del proyecto).
     */
    public void agregarTarea(Tarea tarea){        
        this.proyecto.obtenerRedDeTareas().agregarTarea(tarea);
        actualizarTablaDeTareas(this.proyecto.obtenerRedDeTareas().obtenerCantidadDeTareas()-1, Accion.crear, tarea);
        this.campoTexto_CantidadTareas.setText(String.valueOf(this.proyecto.obtenerRedDeTareas().obtenerCantidadDeTareas()));
    }
    
    /**
     * Al modificar una tarea especifica, también se actualiza dicha tarea
     * en la tabla de datos ingresados por el usuario.
     * @param idTarea
     * @param descripcion
     */    
    public void modificarTarea(int idTarea, String descripcion){
        Tarea tareaModificada = this.proyecto.obtenerRedDeTareas().modificarTarea(idTarea, descripcion);
        for (int i = 0; i < this.tabla_TareasProyecto.getRowCount(); i++){
            String nombreTareaAux = (String)this.tabla_TareasProyecto.getValueAt(i, 0);
            int idTareaAux = FabricaDeTareas.getInstance().getIdTareaByNombre(nombreTareaAux);
            if (idTareaAux == idTarea){
                actualizarTablaDeTareas(i, Accion.modificar, tareaModificada);
                break;
            }
        }        
    }   
        
    public void establecerConsistenciaEnElOrdenDeTareas(){
        List<Tarea> tareasDelProyectoOrdenadas = new ArrayList<Tarea>();
        List<Tarea> tareasDelProyectoDesordenadas = this.proyecto.obtenerRedDeTareas().obtenerTareas();
        for(int i = 0; i < tareasDelProyectoDesordenadas.size(); i++){
            Tarea tareaAUbicar = tareasDelProyectoDesordenadas.get(i);
            boolean seUbico = false;
            for(int j = 0; j < tareasDelProyectoOrdenadas.size(); j++){
                Tarea unaTareaUbicada = tareasDelProyectoOrdenadas.get(j);                
                if (this.proyecto.obtenerRedDeTareas().hayCamino(tareaAUbicar,unaTareaUbicada)){
                    tareasDelProyectoOrdenadas.add(j,tareaAUbicar);
                    seUbico = true;
                    break;
                }
            }
            if (!seUbico){
                tareasDelProyectoOrdenadas.add(tareaAUbicar);
            }
        }
        this.proyecto.obtenerRedDeTareas().setearTareas(tareasDelProyectoOrdenadas);
        resetearTablaDeTareas();
        int fila = 0;
        for (Tarea tarea : proyecto.obtenerRedDeTareas().obtenerTareas()){
            actualizarTablaDeTareas(fila, Accion.crear, tarea);
            fila += 1;
        }        
    }
    
    /**
     * Se devuelve la lista de tareas del conjunto de tareas del proyecto.
     * @return 
     */
    public List<Tarea> obtenerListaDeTareasDelProyecto(){
        return this.proyecto.obtenerRedDeTareas().obtenerTareas();
    }       
    
    /**
     * Se obtiene una lista de tareas candidatas a ser precedentes de una tarea determinada, teniendo
     * en cuenta que dichas tareas candidatas no sean sucesoras de dicha tarea (evitando asi un ciclo en la red,
     * los cuales no estan permitidos).
     * @param tarea
     * @return 
     */
    public List<Tarea> obtenerPosiblesTareasPrecedentes(Tarea tarea){
        return this.proyecto.obtenerRedDeTareas().obtenerPosiblesTareasPrecedentes(tarea);
    }    
   
     /**
     * Método que resetea la aplicación dejando el sistema en limpio (sin proyecto activo).
     */
    private void resetearProyecto(){                
        resetearTablaDeTareas();   
        proyecto = new Proyecto("", "", new RedDeTareas(new ArrayList<Tarea>()), "");
        this.campoTexto_NombreProyecto.setText(this.proyecto.obtenerNombre());        
        this.areaTexto_DescripcionProyecto.setText(this.proyecto.obtenerDescripcion());
        this.campoTexto_UnidadDeTiempo.setText(this.proyecto.obtenerUnidadDeTiempo());
        this.campoTexto_CantidadTareas.setText("0");
        FabricaDeTareas.getInstance().reset();
        this.pathDeArchivoDelProyecto = null;
    }
    
    private void resetearTablaDeTareas(){
        for (int fila = 0; fila < this.proyecto.obtenerRedDeTareas().obtenerCantidadDeTareas(); fila++){
            actualizarTablaDeTareas(0, Accion.eliminar, null);
        }
    }
    
    private void cargarProyectoDemo(IDemo demo){
        resetearProyecto();
        demo.inicializar();
        proyecto.setearNombre(demo.obtenerNombre());
        proyecto.setearUnidadDeTiempo(demo.obtenerUnidadDeTiempo());
        proyecto.setearDescripcion(demo.obtenerDescripcion());        
        proyecto.setearRedDeTareas(demo.obtenerRedDeTareas());
        setearDatosEnLosCampos();
    }
    
    private void salirDelSistema(){
        int seleccion = JOptionPane.showOptionDialog(
           this,
           this.etiquetas.getString("mensajeSalirProyecto"), 
           this.etiquetas.getString("mensajeTituloSalirProyecto"), 
           JOptionPane.OK_CANCEL_OPTION,
           JOptionPane.WARNING_MESSAGE,
           null,    
           new Object[] { this.etiquetas.getString("mensajeOk"), this.etiquetas.getString("mensajeCancelar") },   
           "OK");
        if (seleccion == 0){
            System.exit(0);
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

        label_NombreProyecto = new javax.swing.JLabel();
        campoTexto_NombreProyecto = new javax.swing.JTextField();
        label_TareasProyecto = new javax.swing.JLabel();
        jScrollPane1 = new javax.swing.JScrollPane();
        tabla_TareasProyecto = new javax.swing.JTable();
        boton_AgregarTarea = new javax.swing.JButton();
        boton_ModificarTarea = new javax.swing.JButton();
        boton_BorrarTarea = new javax.swing.JButton();
        boton_BorrarTodasLasTareas = new javax.swing.JButton();
        boton_AnalisisPERT = new javax.swing.JButton();
        boton_Salir = new javax.swing.JButton();
        jScrollPane2 = new javax.swing.JScrollPane();
        areaTexto_DescripcionProyecto = new javax.swing.JTextArea();
        label_DescripcionProyecto = new javax.swing.JLabel();
        label_UnidadDeTiempo = new javax.swing.JLabel();
        campoTexto_UnidadDeTiempo = new javax.swing.JTextField();
        campoTexto_CantidadTareas = new javax.swing.JTextField();
        label_CantidadTareas = new javax.swing.JLabel();
        jMenuBar = new javax.swing.JMenuBar();
        menu_Archivo = new javax.swing.JMenu();
        subMenu_Nuevo = new javax.swing.JMenuItem();
        subMenu_Abrir = new javax.swing.JMenuItem();
        subMenu_Guardar = new javax.swing.JMenuItem();
        subMenu_GuardarComo = new javax.swing.JMenuItem();
        jSeparator1 = new javax.swing.JPopupMenu.Separator();
        subMenu_Salir = new javax.swing.JMenuItem();
        menu_Idioma = new javax.swing.JMenu();
        subMenu_Español = new javax.swing.JMenuItem();
        subMenu_Ingles = new javax.swing.JMenuItem();
        subMenu_Portugues = new javax.swing.JMenuItem();
        menu_Demos = new javax.swing.JMenu();
        subMenu_Demo1 = new javax.swing.JMenuItem();
        subMenu_Demo2 = new javax.swing.JMenuItem();
        subMenu_Demo3 = new javax.swing.JMenuItem();
        menu_Ayuda = new javax.swing.JMenu();
        subMenu_AyudaContenidos = new javax.swing.JMenuItem();
        jSeparator2 = new javax.swing.JPopupMenu.Separator();
        subMenu_AcercaDe = new javax.swing.JMenuItem();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        setResizable(false);

        label_NombreProyecto.setText("lblNombreProyecto");

        label_TareasProyecto.setText("lblTareasProyecto");

        tabla_TareasProyecto.setBorder(javax.swing.BorderFactory.createEtchedBorder());
        tabla_TareasProyecto.setModel(new javax.swing.table.DefaultTableModel(
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
        tabla_TareasProyecto.setCursor(new java.awt.Cursor(java.awt.Cursor.DEFAULT_CURSOR));
        tabla_TareasProyecto.setOpaque(false);
        tabla_TareasProyecto.setSelectionMode(javax.swing.ListSelectionModel.SINGLE_SELECTION);
        tabla_TareasProyecto.getTableHeader().setReorderingAllowed(false);
        jScrollPane1.setViewportView(tabla_TareasProyecto);
        tabla_TareasProyecto.getColumnModel().getColumn(0).setResizable(false);
        tabla_TareasProyecto.getColumnModel().getColumn(1).setResizable(false);
        tabla_TareasProyecto.getColumnModel().getColumn(2).setResizable(false);
        tabla_TareasProyecto.getColumnModel().getColumn(3).setResizable(false);
        tabla_TareasProyecto.getColumnModel().getColumn(4).setResizable(false);
        tabla_TareasProyecto.getColumnModel().getColumn(5).setResizable(false);

        boton_AgregarTarea.setText("btnAgregar");
        boton_AgregarTarea.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                boton_AgregarTareaActionPerformed(evt);
            }
        });

        boton_ModificarTarea.setText("btnModificar");
        boton_ModificarTarea.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                boton_ModificarTareaActionPerformed(evt);
            }
        });

        boton_BorrarTarea.setText("btnBorrar");
        boton_BorrarTarea.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                boton_BorrarTareaActionPerformed(evt);
            }
        });

        boton_BorrarTodasLasTareas.setText("btnBorrarTodas");
        boton_BorrarTodasLasTareas.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                boton_BorrarTodasLasTareasActionPerformed(evt);
            }
        });

        boton_AnalisisPERT.setText("btnAnalisisPERT");
        boton_AnalisisPERT.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                boton_AnalisisPERTActionPerformed(evt);
            }
        });

        boton_Salir.setText("btnSalir");
        boton_Salir.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                boton_SalirActionPerformed(evt);
            }
        });

        areaTexto_DescripcionProyecto.setColumns(20);
        areaTexto_DescripcionProyecto.setRows(5);
        jScrollPane2.setViewportView(areaTexto_DescripcionProyecto);

        label_DescripcionProyecto.setText("lblDescripcionProyecto");

        label_UnidadDeTiempo.setText("lblUnidadDeTiempo");

        campoTexto_CantidadTareas.setEditable(false);
        campoTexto_CantidadTareas.setFont(new java.awt.Font("Tahoma", 1, 11));
        campoTexto_CantidadTareas.setHorizontalAlignment(javax.swing.JTextField.CENTER);

        label_CantidadTareas.setText("lblCantidadTareas");

        jMenuBar.setBorder(null);
        jMenuBar.setCursor(new java.awt.Cursor(java.awt.Cursor.DEFAULT_CURSOR));

        menu_Archivo.setText("Archivo");

        subMenu_Nuevo.setAccelerator(javax.swing.KeyStroke.getKeyStroke(java.awt.event.KeyEvent.VK_N, java.awt.event.InputEvent.SHIFT_MASK | java.awt.event.InputEvent.CTRL_MASK));
        subMenu_Nuevo.setText("Nuevo");
        subMenu_Nuevo.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                subMenu_NuevoActionPerformed(evt);
            }
        });
        menu_Archivo.add(subMenu_Nuevo);

        subMenu_Abrir.setAccelerator(javax.swing.KeyStroke.getKeyStroke(java.awt.event.KeyEvent.VK_O, java.awt.event.InputEvent.SHIFT_MASK | java.awt.event.InputEvent.CTRL_MASK));
        subMenu_Abrir.setText("Abrir");
        subMenu_Abrir.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                subMenu_AbrirActionPerformed(evt);
            }
        });
        menu_Archivo.add(subMenu_Abrir);

        subMenu_Guardar.setAccelerator(javax.swing.KeyStroke.getKeyStroke(java.awt.event.KeyEvent.VK_S, java.awt.event.InputEvent.CTRL_MASK));
        subMenu_Guardar.setText("Guardar");
        subMenu_Guardar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                subMenu_GuardarActionPerformed(evt);
            }
        });
        menu_Archivo.add(subMenu_Guardar);

        subMenu_GuardarComo.setAccelerator(javax.swing.KeyStroke.getKeyStroke(java.awt.event.KeyEvent.VK_S, java.awt.event.InputEvent.SHIFT_MASK | java.awt.event.InputEvent.CTRL_MASK));
        subMenu_GuardarComo.setText("Guardar como");
        subMenu_GuardarComo.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                subMenu_GuardarComoActionPerformed(evt);
            }
        });
        menu_Archivo.add(subMenu_GuardarComo);
        menu_Archivo.add(jSeparator1);

        subMenu_Salir.setText("Salir");
        subMenu_Salir.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                subMenu_SalirActionPerformed(evt);
            }
        });
        menu_Archivo.add(subMenu_Salir);

        jMenuBar.add(menu_Archivo);

        menu_Idioma.setText("Idioma");

        subMenu_Español.setAccelerator(javax.swing.KeyStroke.getKeyStroke(java.awt.event.KeyEvent.VK_E, java.awt.event.InputEvent.CTRL_MASK));
        subMenu_Español.setText("Español");
        subMenu_Español.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                subMenu_EspañolActionPerformed(evt);
            }
        });
        menu_Idioma.add(subMenu_Español);

        subMenu_Ingles.setAccelerator(javax.swing.KeyStroke.getKeyStroke(java.awt.event.KeyEvent.VK_I, java.awt.event.InputEvent.CTRL_MASK));
        subMenu_Ingles.setText("Inglés");
        subMenu_Ingles.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                subMenu_InglesActionPerformed(evt);
            }
        });
        menu_Idioma.add(subMenu_Ingles);

        subMenu_Portugues.setAccelerator(javax.swing.KeyStroke.getKeyStroke(java.awt.event.KeyEvent.VK_P, java.awt.event.InputEvent.CTRL_MASK));
        subMenu_Portugues.setText("Portugués");
        subMenu_Portugues.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                subMenu_PortuguesActionPerformed(evt);
            }
        });
        menu_Idioma.add(subMenu_Portugues);

        jMenuBar.add(menu_Idioma);

        menu_Demos.setText("Demos");

        subMenu_Demo1.setText("Demo 1");
        subMenu_Demo1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                subMenu_Demo1ActionPerformed(evt);
            }
        });
        menu_Demos.add(subMenu_Demo1);

        subMenu_Demo2.setText("Demo 2 ");
        subMenu_Demo2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                subMenu_Demo2ActionPerformed(evt);
            }
        });
        menu_Demos.add(subMenu_Demo2);

        subMenu_Demo3.setText("Demo 3");
        subMenu_Demo3.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                subMenu_Demo3ActionPerformed(evt);
            }
        });
        menu_Demos.add(subMenu_Demo3);

        jMenuBar.add(menu_Demos);

        menu_Ayuda.setText("Ayuda");

        subMenu_AyudaContenidos.setAccelerator(javax.swing.KeyStroke.getKeyStroke(java.awt.event.KeyEvent.VK_F1, 0));
        subMenu_AyudaContenidos.setText("Ayuda Contenidos");
        menu_Ayuda.add(subMenu_AyudaContenidos);
        menu_Ayuda.add(jSeparator2);

        subMenu_AcercaDe.setText("Acerca de");
        subMenu_AcercaDe.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                subMenu_AcercaDeActionPerformed(evt);
            }
        });
        menu_Ayuda.add(subMenu_AcercaDe);

        jMenuBar.add(menu_Ayuda);

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
                            .addComponent(label_NombreProyecto)
                            .addComponent(label_UnidadDeTiempo)
                            .addComponent(label_CantidadTareas))
                        .addGap(18, 18, 18)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(campoTexto_CantidadTareas, javax.swing.GroupLayout.PREFERRED_SIZE, 43, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(campoTexto_UnidadDeTiempo, javax.swing.GroupLayout.PREFERRED_SIZE, 114, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(campoTexto_NombreProyecto, javax.swing.GroupLayout.PREFERRED_SIZE, 239, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(18, 18, 18)
                        .addComponent(label_DescripcionProyecto)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jScrollPane2, javax.swing.GroupLayout.DEFAULT_SIZE, 361, Short.MAX_VALUE))
                    .addComponent(label_TareasProyecto)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(layout.createSequentialGroup()
                                .addComponent(boton_AnalisisPERT, javax.swing.GroupLayout.DEFAULT_SIZE, 233, Short.MAX_VALUE)
                                .addGap(486, 486, 486))
                            .addGroup(layout.createSequentialGroup()
                                .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 713, Short.MAX_VALUE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)))
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addComponent(boton_ModificarTarea, 0, 0, Short.MAX_VALUE)
                            .addComponent(boton_AgregarTarea, javax.swing.GroupLayout.DEFAULT_SIZE, 118, Short.MAX_VALUE)
                            .addComponent(boton_BorrarTodasLasTareas, javax.swing.GroupLayout.DEFAULT_SIZE, 118, Short.MAX_VALUE)
                            .addComponent(boton_BorrarTarea, javax.swing.GroupLayout.DEFAULT_SIZE, 118, Short.MAX_VALUE)
                            .addComponent(boton_Salir))))
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(label_NombreProyecto)
                            .addComponent(campoTexto_NombreProyecto, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(label_DescripcionProyecto))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(campoTexto_UnidadDeTiempo, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(label_UnidadDeTiempo))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(campoTexto_CantidadTareas, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(label_CantidadTareas)))
                    .addComponent(jScrollPane2, 0, 0, Short.MAX_VALUE))
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(label_TareasProyecto)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 234, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(layout.createSequentialGroup()
                        .addGap(54, 54, 54)
                        .addComponent(boton_AgregarTarea)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(boton_ModificarTarea)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(boton_BorrarTarea)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(boton_BorrarTodasLasTareas)))
                .addGap(18, 18, 18)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(boton_AnalisisPERT)
                    .addComponent(boton_Salir))
                .addContainerGap())
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void boton_AgregarTareaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_boton_AgregarTareaActionPerformed
        if (FabricaDeTareas.getInstance().esPosibleCrearNuevaTarea()){
            VentanaTarea ventanaTarea = new VentanaTarea(this, true, this.etiquetas, this.helpBroker);
            ventanaTarea.setVisible(true);
        }else{
            JOptionPane.showMessageDialog(this, this.etiquetas.getString("mensajeNoSePuedenCrearMasTareas"));
        }
    }//GEN-LAST:event_boton_AgregarTareaActionPerformed

    private void boton_ModificarTareaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_boton_ModificarTareaActionPerformed
        int filaSeleccionada = this.tabla_TareasProyecto.getSelectedRow();
        if (filaSeleccionada >= 0){
            String nombreTarea = (String)this.tabla_TareasProyecto.getValueAt(filaSeleccionada, 0);
            Tarea tarea = this.proyecto.obtenerRedDeTareas().obtenerTareaPorID(FabricaDeTareas.getInstance().getIdTareaByNombre(nombreTarea));
            VentanaTarea ventanaTarea = new VentanaTarea(this, true, tarea, this.etiquetas, this.helpBroker);
            ventanaTarea.setVisible(true);            
        }else{
            JOptionPane.showMessageDialog(this, this.etiquetas.getString("mensajeDebeSeleccionarUnaFila"));
        }
    }//GEN-LAST:event_boton_ModificarTareaActionPerformed

    private void boton_BorrarTareaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_boton_BorrarTareaActionPerformed
        int filaSeleccionada = this.tabla_TareasProyecto.getSelectedRow();
        if (filaSeleccionada >= 0){            
            String nombreTarea = (String)this.tabla_TareasProyecto.getValueAt(filaSeleccionada, 0);
            int idTarea = FabricaDeTareas.getInstance().getIdTareaByNombre(nombreTarea);
            resetearTablaDeTareas();                        
            this.proyecto.obtenerRedDeTareas().borrarTarea(idTarea);            
            int fila = 0;
            for (Tarea tarea : this.proyecto.obtenerRedDeTareas().obtenerTareas()){
                this.actualizarTablaDeTareas(fila, Accion.crear, tarea);
                fila++;
            }
            this.campoTexto_CantidadTareas.setText(String.valueOf(this.proyecto.obtenerRedDeTareas().obtenerCantidadDeTareas()));            
        }else{
            JOptionPane.showMessageDialog(this, etiquetas.getString("mensajeDebeSeleccionarUnaFila"));
        }        
    }//GEN-LAST:event_boton_BorrarTareaActionPerformed

    private void boton_BorrarTodasLasTareasActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_boton_BorrarTodasLasTareasActionPerformed
        resetearTablaDeTareas();
        this.proyecto.setearRedDeTareas(new RedDeTareas(new ArrayList<Tarea>()));
        FabricaDeTareas.getInstance().reset();
        this.campoTexto_CantidadTareas.setText(String.valueOf(this.proyecto.obtenerRedDeTareas().obtenerCantidadDeTareas()));
    }//GEN-LAST:event_boton_BorrarTodasLasTareasActionPerformed

    private void boton_AnalisisPERTActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_boton_AnalisisPERTActionPerformed
        if (this.proyecto.obtenerRedDeTareas().obtenerCantidadDeTareas() > 0){
            boolean calculoValido = true;
            if (!this.proyecto.obtenerRedDeTareas().elUltimoCalculoPERTesCorrecto()){
                calculoValido = this.proyecto.obtenerRedDeTareas().realizarCalculosPERT();                    
            }
            if (!this.proyecto.obtenerRedDeTareas().tieneAlMenosUnCaminoCriticoDefinido()){
                calculoValido = false;
            }
            if (calculoValido){
                if (! (this.campoTexto_UnidadDeTiempo.getText().length() > 15)){
                    this.proyecto.setearUnidadDeTiempo(this.campoTexto_UnidadDeTiempo.getText());
                    VentanaResultados ventanaResultados = new VentanaResultados(this, true, this.proyecto, this.etiquetas, this.helpBroker);
                    ventanaResultados.setVisible(true);
                }else{ 
                    JOptionPane.showMessageDialog(this, this.etiquetas.getString("mensajeLargoDeUnidadDeTiempoExcedido"));
                }
            }else{
                JOptionPane.showMessageDialog(this, etiquetas.getString("mensajeErrorEnCalculosSobreRedDeTareas"));
            }            
        }else{
            JOptionPane.showMessageDialog(this, etiquetas.getString("mensajeNoHayTareasIngresadasParaRealizarAnalisisPERT"));
        }      
    }//GEN-LAST:event_boton_AnalisisPERTActionPerformed

    private void boton_SalirActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_boton_SalirActionPerformed
        salirDelSistema();
    }//GEN-LAST:event_boton_SalirActionPerformed

    private void subMenu_EspañolActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_subMenu_EspañolActionPerformed
        this.lugarConfigurado = new Locale("es", "UY");
        this.etiquetas = ResourceBundle.getBundle("Idiomas.MessagesBundlePert", this.lugarConfigurado);
        setearEtiquetas();
    }//GEN-LAST:event_subMenu_EspañolActionPerformed

    private void subMenu_InglesActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_subMenu_InglesActionPerformed
        this.lugarConfigurado = new Locale("en", "US");
        this.etiquetas = ResourceBundle.getBundle("Idiomas.MessagesBundlePert", this.lugarConfigurado);
        this.setearEtiquetas();
    }//GEN-LAST:event_subMenu_InglesActionPerformed

    private void subMenu_PortuguesActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_subMenu_PortuguesActionPerformed
        this.lugarConfigurado = new Locale("po", "BR");
        this.etiquetas = ResourceBundle.getBundle("Idiomas.MessagesBundlePert", this.lugarConfigurado);
        this.setearEtiquetas();
    }//GEN-LAST:event_subMenu_PortuguesActionPerformed

    private void subMenu_AcercaDeActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_subMenu_AcercaDeActionPerformed
        VentanaAcercaDe ventanaAcercaDe = new VentanaAcercaDe(this, true, this.etiquetas);
        ventanaAcercaDe.setVisible(true);
    }//GEN-LAST:event_subMenu_AcercaDeActionPerformed

    private void subMenu_NuevoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_subMenu_NuevoActionPerformed
        int seleccion = JOptionPane.showOptionDialog(
           this,
           this.etiquetas.getString("mensajeNuevoProyecto"), 
           this.etiquetas.getString("mensajeTituloNuevoProyecto"), 
           JOptionPane.OK_CANCEL_OPTION,
           JOptionPane.WARNING_MESSAGE,
           null,    
           new Object[] { this.etiquetas.getString("mensajeOk"), this.etiquetas.getString("mensajeCancelar") },   
           "OK");
        if (seleccion == 0){
            resetearProyecto();
        }  
    }//GEN-LAST:event_subMenu_NuevoActionPerformed

    private void subMenu_AbrirActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_subMenu_AbrirActionPerformed
        JFileChooser fileChooser = new JFileChooser();
        int seleccion = fileChooser.showOpenDialog(this.subMenu_Abrir);
        if (seleccion == JFileChooser.APPROVE_OPTION) {
           File archivo = fileChooser.getSelectedFile();
           ProyectoES proyectoES = (ProyectoES)ManejadorDeArchivos.ObtenerProyectoDeXML(archivo.getAbsolutePath());
           if (proyectoES != null){
               this.resetearProyecto();
               this.proyecto = GestorDeTransformacion.transformarProyectoESEnProyecto(proyectoES);
               setearDatosEnLosCampos();
               this.pathDeArchivoDelProyecto = archivo.getAbsolutePath();
           }else{
               JOptionPane.showMessageDialog(this, this.etiquetas.getString("mensajeErrorAlCargarProyecto"));
           }           
        }
    }//GEN-LAST:event_subMenu_AbrirActionPerformed

    private void subMenu_GuardarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_subMenu_GuardarActionPerformed
        if (obtenerDatosDeLosCampos()){
            if (pathDeArchivoDelProyecto == null){
                JFileChooser fileChooser = new JFileChooser();
                int seleccion = fileChooser.showSaveDialog(this.subMenu_Guardar);
                if (seleccion == JFileChooser.APPROVE_OPTION) {
                   File archivo = fileChooser.getSelectedFile();
                   ProyectoES proyectoES = GestorDeTransformacion.transformarProyectoEnProyectoES(this.proyecto);
                   ManejadorDeArchivos.GuardarProyectoEnXML(archivo.getAbsolutePath(), proyectoES);
                   this.pathDeArchivoDelProyecto = archivo.getAbsolutePath();
                }
            }else{
                ProyectoES proyectoES = GestorDeTransformacion.transformarProyectoEnProyectoES(this.proyecto);
                ManejadorDeArchivos.GuardarProyectoEnXML(this.pathDeArchivoDelProyecto, proyectoES);
            }
        }
    }//GEN-LAST:event_subMenu_GuardarActionPerformed

    private void subMenu_GuardarComoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_subMenu_GuardarComoActionPerformed
        if (obtenerDatosDeLosCampos()){
            JFileChooser fileChooser = new JFileChooser();
            int seleccion = fileChooser.showSaveDialog(this.subMenu_GuardarComo);
            if (seleccion == JFileChooser.APPROVE_OPTION) {
               File archivo = fileChooser.getSelectedFile();
               ProyectoES proyectoES = GestorDeTransformacion.transformarProyectoEnProyectoES(this.proyecto);
               ManejadorDeArchivos.GuardarProyectoEnXML(archivo.getAbsolutePath(), proyectoES);
               this.pathDeArchivoDelProyecto = archivo.getAbsolutePath();
            }
        }
    }//GEN-LAST:event_subMenu_GuardarComoActionPerformed

    private void subMenu_SalirActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_subMenu_SalirActionPerformed
        salirDelSistema();
    }//GEN-LAST:event_subMenu_SalirActionPerformed

    private void subMenu_Demo1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_subMenu_Demo1ActionPerformed
        cargarProyectoDemo(new Demo1());
    }//GEN-LAST:event_subMenu_Demo1ActionPerformed

    private void subMenu_Demo2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_subMenu_Demo2ActionPerformed
        cargarProyectoDemo(new Demo2());
    }//GEN-LAST:event_subMenu_Demo2ActionPerformed

    private void subMenu_Demo3ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_subMenu_Demo3ActionPerformed
        cargarProyectoDemo(new Demo3());
    }//GEN-LAST:event_subMenu_Demo3ActionPerformed

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
    private javax.swing.JTextArea areaTexto_DescripcionProyecto;
    private javax.swing.JButton boton_AgregarTarea;
    private javax.swing.JButton boton_AnalisisPERT;
    private javax.swing.JButton boton_BorrarTarea;
    private javax.swing.JButton boton_BorrarTodasLasTareas;
    private javax.swing.JButton boton_ModificarTarea;
    private javax.swing.JButton boton_Salir;
    private javax.swing.JTextField campoTexto_CantidadTareas;
    private javax.swing.JTextField campoTexto_NombreProyecto;
    private javax.swing.JTextField campoTexto_UnidadDeTiempo;
    private javax.swing.JMenuBar jMenuBar;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JPopupMenu.Separator jSeparator1;
    private javax.swing.JPopupMenu.Separator jSeparator2;
    private javax.swing.JLabel label_CantidadTareas;
    private javax.swing.JLabel label_DescripcionProyecto;
    private javax.swing.JLabel label_NombreProyecto;
    private javax.swing.JLabel label_TareasProyecto;
    private javax.swing.JLabel label_UnidadDeTiempo;
    private javax.swing.JMenu menu_Archivo;
    private javax.swing.JMenu menu_Ayuda;
    private javax.swing.JMenu menu_Demos;
    private javax.swing.JMenu menu_Idioma;
    private javax.swing.JMenuItem subMenu_Abrir;
    private javax.swing.JMenuItem subMenu_AcercaDe;
    private javax.swing.JMenuItem subMenu_AyudaContenidos;
    private javax.swing.JMenuItem subMenu_Demo1;
    private javax.swing.JMenuItem subMenu_Demo2;
    private javax.swing.JMenuItem subMenu_Demo3;
    private javax.swing.JMenuItem subMenu_Español;
    private javax.swing.JMenuItem subMenu_Guardar;
    private javax.swing.JMenuItem subMenu_GuardarComo;
    private javax.swing.JMenuItem subMenu_Ingles;
    private javax.swing.JMenuItem subMenu_Nuevo;
    private javax.swing.JMenuItem subMenu_Portugues;
    private javax.swing.JMenuItem subMenu_Salir;
    private javax.swing.JTable tabla_TareasProyecto;
    // End of variables declaration//GEN-END:variables

}
