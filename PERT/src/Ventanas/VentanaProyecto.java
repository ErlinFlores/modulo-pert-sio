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
import java.util.ArrayList;
import java.util.List;
import java.util.Locale;
import java.util.ResourceBundle;
import javax.help.HelpBroker;
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
    
    private DefaultTableModel modeloDeTablaDeTareas;
    
    private String nombre;
    private String unidadDeTiempo;
    private String descripcion;    
    private RedDeTareas redDeTareas;
    
    
    /** Creates new form VentanaProyecto */
    public VentanaProyecto(Locale lugarConfigurado, HelpBroker helpBroker) {
        initComponents();   
        this.lugarConfigurado = lugarConfigurado;
        this.helpBroker = helpBroker;
        habilitarAyuda();
        etiquetas = ResourceBundle.getBundle("Idiomas.MessagesBundle", lugarConfigurado);
        FabricaDeProyectos.getInstance().reset();
        FabricaDeTareas.getInstance().reset();  
        inicializarTabla();
        setearEtiquetas();  
        setearCampos();
        this.nombre = etiquetas.getString("proyectoSugerenciaNombreProyecto");
        this.unidadDeTiempo = etiquetas.getString("proyectoSugerenciaUnidadDeTiempo"); 
        this.descripcion = etiquetas.getString("proyectoSugerenciaDescripcionProyecto");
        this.redDeTareas = new RedDeTareas(new ArrayList<Tarea>());         
    }  

    /**
     * Se setean las etiquetas de la pantalla según el idioma configurado.
     */
    private void setearEtiquetas(){
        setTitle(etiquetas.getString("proyectoTitulo"));
        this.menu_Archivo.setText(etiquetas.getString("proyectoMenuArchivo"));
        this.subMenu_Nuevo.setText(etiquetas.getString("proyectoSubMenuNuevo"));
        this.subMenu_Abrir.setText(etiquetas.getString("proyectoSubMenuAbrir"));
        this.subMenu_Guardar.setText(etiquetas.getString("proyectoSubMenuGuardar"));
        this.subMenu_GuardarComo.setText(etiquetas.getString("proyectoSubMenuGuardarComo"));
        this.subMenu_Salir.setText(etiquetas.getString("proyectoSubMenuSalir"));
        this.menu_Idioma.setText(etiquetas.getString("proyectoMenuIdioma"));
        this.subMenu_Español.setText(etiquetas.getString("proyectoSubMenuEspañol"));
        this.subMenu_Español.setText(etiquetas.getString("proyectoSubMenuEspañol"));
        this.subMenu_Español.setText(etiquetas.getString("proyectoSubMenuEspañol"));
        this.subMenu_Ingles.setText(etiquetas.getString("proyectoSubMenuIngles"));
        this.subMenu_Portugues.setText(etiquetas.getString("proyectoSubMenuPortugues"));
        this.menu_Demos.setText(etiquetas.getString("proyectoMenuDemos"));
        this.subMenu_Demo1.setText(etiquetas.getString("proyectoSubMenuDemo1"));
        this.subMenu_Demo2.setText(etiquetas.getString("proyectoSubMenuDemo2"));
        this.subMenu_Demo3.setText(etiquetas.getString("proyectoSubMenuDemo3"));
        this.menu_Ayuda.setText(etiquetas.getString("proyectoMenuAyuda"));
        this.subMenu_AyudaContenidos.setText(etiquetas.getString("proyectoSubMenuAyudaContenidos"));
        this.subMenu_AcercaDe.setText(etiquetas.getString("proyectoSubMenuAcercaDe"));
        
        this.label_NombreProyecto.setText(etiquetas.getString("proyectoLabelNombreProyecto"));
        this.label_UnidadDeTiempo.setText(etiquetas.getString("proyectoLabelUnidadDeTiempo"));
        this.label_CantidadTareas.setText(etiquetas.getString("proyectoLabelCantidadTareas"));        
        this.label_DescripcionProyecto.setText(etiquetas.getString("proyectoLabelDescripcionProyecto"));
        this.label_TareasProyecto.setText(etiquetas.getString("proyectoLabelTareasProyecto"));
        
        this.boton_AgregarTarea.setText(etiquetas.getString("proyectoBotonAgregarTarea"));
        this.boton_ModificarTarea.setText(etiquetas.getString("proyectoBotonModificarTarea"));
        this.boton_BorrarTarea.setText(etiquetas.getString("proyectoBotonBorrarTarea"));
        this.boton_BorrarTodasLasTareas.setText(etiquetas.getString("proyectoBotonBorrarTodasLasTareas"));        
        this.boton_Salir.setText(etiquetas.getString("proyectoBotonSalir"));
        this.boton_AnalisisPERT.setText(etiquetas.getString("proyectoBotonAnalisisPERT")); 
        
        tabla_TareasProyecto.getColumnModel().getColumn(0).setHeaderValue(etiquetas.getString("proyectoTablaColumnaNombreTarea"));
        tabla_TareasProyecto.getColumnModel().getColumn(1).setHeaderValue(etiquetas.getString("proyectoTablaColumnaDescripcionTarea"));
        tabla_TareasProyecto.getColumnModel().getColumn(2).setHeaderValue(etiquetas.getString("proyectoTablaColumnaPrecedenciaTarea"));
        tabla_TareasProyecto.getColumnModel().getColumn(3).setHeaderValue(etiquetas.getString("proyectoTablaColumnaTiempoOptimistaTarea"));
        tabla_TareasProyecto.getColumnModel().getColumn(4).setHeaderValue(etiquetas.getString("proyectoTablaColumnaTiempoMasProbableTarea"));
        tabla_TareasProyecto.getColumnModel().getColumn(5).setHeaderValue(etiquetas.getString("proyectoTablaColumnaTiempoPesimistaTarea"));
    }   
    
    private void inicializarTabla(){
        DefaultTableCellRenderer dtcr = new DefaultTableCellRenderer();
        dtcr.setHorizontalAlignment(SwingConstants.CENTER);      
        
        modeloDeTablaDeTareas = new DefaultTableModel(0, 6);
        tabla_TareasProyecto.setModel(modeloDeTablaDeTareas);                  
        tabla_TareasProyecto.getColumnModel().getColumn(0).setMaxWidth(100);
        tabla_TareasProyecto.getColumnModel().getColumn(0).setResizable(false); 
        tabla_TareasProyecto.getColumnModel().getColumn(0).setCellRenderer(dtcr);
        tabla_TareasProyecto.getColumnModel().getColumn(1).setCellRenderer(dtcr);        
        tabla_TareasProyecto.getColumnModel().getColumn(2).setCellRenderer(dtcr);        
        tabla_TareasProyecto.getColumnModel().getColumn(3).setCellRenderer(dtcr);        
        tabla_TareasProyecto.getColumnModel().getColumn(4).setCellRenderer(dtcr);        
        tabla_TareasProyecto.getColumnModel().getColumn(5).setCellRenderer(dtcr);
    }

    private void habilitarAyuda(){
        if (helpBroker != null){
            helpBroker.enableHelpOnButton(this.subMenu_AyudaContenidos, "aplicacion", helpBroker.getHelpSet());
            helpBroker.enableHelpKey(this.getContentPane(), "aplicacion", helpBroker.getHelpSet());
        }
    }
    
    /**
     * Previo al almacenamiento en la estructura de datos de los datos ingresados por el usuario,
     * se verifica que los mismos sean correctos.
     * @return (si son válidos o no los datos ingresados por el usuario).
     */
    private boolean validarDatosDeEntradaDelUsuario(){
        if ((campoTexto_NombreProyecto.getText().equals("") && (campoTexto_NombreProyecto.getText().length() > 40))){ 
            return false;
        }
        if (areaTexto_DescripcionProyecto.getText().length() > 500){ 
            return false;
        }
        if ((campoTexto_UnidadDeTiempo.getText().equals("") && (campoTexto_UnidadDeTiempo.getText().length() > 15))){
            return false;
        }    
        if (redDeTareas.obtenerCantidadDeTareas()==0){
            return false;
        }
        return true;
    } 
    
    /**
     * Se setean los campos de la pantalla, con los datos correspondientes.
     */
    private void setearCampos(){                                              
        campoTexto_NombreProyecto.setText(nombre);
        campoTexto_UnidadDeTiempo.setText(unidadDeTiempo);
        campoTexto_CantidadTareas.setText(String.valueOf(redDeTareas.obtenerCantidadDeTareas()));
        areaTexto_DescripcionProyecto.setText(descripcion);
        int fila = 0;
        for (Tarea tarea : redDeTareas.obtenerTareas()){
            actualizarTablaDeDatosIngresados(fila, Accion.crear, tarea);
            fila += 1;
        }
    }    
    
    /**
     * Método que resetea la aplicación dejando el sistema en limpio (sin proyecto activo).
     */
    private void limpiarPantallaProyecto(){
        this.campoTexto_NombreProyecto.setText("");        
        this.areaTexto_DescripcionProyecto.setText("");
        this.campoTexto_UnidadDeTiempo.setText("");
        boton_BorrarTodasLasTareasActionPerformed(null);        
        this.campoTexto_CantidadTareas.setText("0");
    }
    
    /**
     * Se modifica la tabla de tareas del proyecto en la cual se muestran los datos ingresados por el usuario
     * acerca de cada una de ellas (nombre, descripción, precedencia y tiempos estimados).
     * @param fila (fila de la tabla a agregar o eliminar).
     * @param nuevaFila (determina si se trata de una nueva fila a agregar o de eliminar una existente).
     * @param tarea (tarea que forma parte de la modificación).
     */
    private void actualizarTablaDeDatosIngresados(int fila, Accion accion, Tarea tarea){
        if (accion.equals(Accion.eliminar)){
            modeloDeTablaDeTareas.removeRow(fila);            
        }else{ 
            if (accion.equals(Accion.crear)) {
                modeloDeTablaDeTareas.addRow(new Object[fila]);
                tabla_TareasProyecto.setValueAt(tarea.obtenerNombre(), fila, 0);
            }            
            tabla_TareasProyecto.setValueAt(tarea.obtenerDescripcion(), fila, 1);
            tabla_TareasProyecto.setValueAt(tarea.obtenerPrecedencia().obtenerTareasConcatenadas(), fila, 2);
            tabla_TareasProyecto.setValueAt(tarea.obtenerTiempoEstimado().obtenerTiempoOptimista(), fila, 3);
            tabla_TareasProyecto.setValueAt(tarea.obtenerTiempoEstimado().obtenerTiempoMasProbable(), fila, 4);
            tabla_TareasProyecto.setValueAt(tarea.obtenerTiempoEstimado().obtenerTiempoPesimista(), fila, 5);
        }      
        tabla_TareasProyecto.updateUI();
    }        

    /**
     * Se almacena una tarea en el conjunto de tareas del proyecto y
     * también se actualiza la tabla de datos ingresados por el usuario (de tareas).
     * @param tarea (nueva tarea del proyecto).
     */
    public void agregarTarea(Tarea tarea){        
        this.redDeTareas.agregarTarea(tarea);
        actualizarTablaDeDatosIngresados(redDeTareas.obtenerCantidadDeTareas()-1, Accion.crear, tarea);
        this.campoTexto_CantidadTareas.setText(String.valueOf(redDeTareas.obtenerCantidadDeTareas()));
    }
    
    /**
     * Al modificar una tarea especifica, también se actualiza dicha tarea
     * en la tabla de datos ingresados por el usuario.
     * @param idTarea
     * @param descripcion
     */    
    public void modificarTarea(int idTarea, String descripcion){
        Tarea tareaModificada = redDeTareas.modificarTarea(idTarea, descripcion);
        for (int i = 0; i < tabla_TareasProyecto.getRowCount(); i++){
            String nombreTareaAux = (String)tabla_TareasProyecto.getValueAt(i, 0);
            int idTareaAux = FabricaDeTareas.getInstance().getIdTareaByNombre(nombreTareaAux);
            if (idTareaAux == idTarea){
                actualizarTablaDeDatosIngresados(i, Accion.modificar, tareaModificada);
                break;
            }
        }        
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
   
    
    private void salirDelSistema(){
        int seleccion = JOptionPane.showOptionDialog(
           this,
           etiquetas.getString("mensajeSalirProyecto"), 
           etiquetas.getString("mensajeTituloSalirProyecto"), 
           JOptionPane.OK_CANCEL_OPTION,
           JOptionPane.WARNING_MESSAGE,
           null,    
           new Object[] { etiquetas.getString("mensajeOk"), etiquetas.getString("mensajeCancelar") },   
           "OK");
        if (seleccion == 0){
            this.dispose();
        }
    }
    
    private void limpiarTablaDeTareas(){
        for (int fila = 0; fila < redDeTareas.obtenerCantidadDeTareas(); fila++){
            actualizarTablaDeDatosIngresados(0, Accion.eliminar, null);
        }
    }
    
    private void cargarProyectoDemo(IDemo demo){
        limpiarTablaDeTareas();
        this.nombre = demo.obtenerNombre();
        this.unidadDeTiempo = demo.obtenerUnidadDeTiempo();
        this.descripcion = demo.obtenerDescripcion();        
        this.redDeTareas = demo.obtenerRedDeTareas();
        setearCampos();
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
            VentanaTarea ventanaTarea = new VentanaTarea(this, true, etiquetas, helpBroker);
            ventanaTarea.setVisible(true);
        }else{
            JOptionPane.showMessageDialog(this, etiquetas.getString("mensajeNoSePuedenCrearMasTareas"));
        }
    }//GEN-LAST:event_boton_AgregarTareaActionPerformed

    private void boton_ModificarTareaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_boton_ModificarTareaActionPerformed
        int filaSeleccionada = tabla_TareasProyecto.getSelectedRow();
        if (filaSeleccionada >= 0){
            String nombreTarea = (String)tabla_TareasProyecto.getValueAt(filaSeleccionada, 0);
            Tarea tarea = redDeTareas.obtenerTareaPorID(FabricaDeTareas.getInstance().getIdTareaByNombre(nombreTarea));
            VentanaTarea ventanaTarea = new VentanaTarea(this, true, tarea, etiquetas, helpBroker);
            ventanaTarea.setVisible(true);            
        }else{
            JOptionPane.showMessageDialog(this, etiquetas.getString("mensajeDebeSeleccionarUnaFila"));
        }
    }//GEN-LAST:event_boton_ModificarTareaActionPerformed

    private void boton_BorrarTareaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_boton_BorrarTareaActionPerformed
        int filaSeleccionada = tabla_TareasProyecto.getSelectedRow();
        if (filaSeleccionada >= 0){            
            String nombreTarea = (String)tabla_TareasProyecto.getValueAt(filaSeleccionada, 0);
            int idTarea = FabricaDeTareas.getInstance().getIdTareaByNombre(nombreTarea);
            limpiarTablaDeTareas();                        
            redDeTareas.borrarTarea(idTarea);            
            int fila = 0;
            for (Tarea tarea : redDeTareas.obtenerTareas()){
                this.actualizarTablaDeDatosIngresados(fila, Accion.crear, tarea);
                fila++;
            }
            campoTexto_CantidadTareas.setText(String.valueOf(redDeTareas.obtenerCantidadDeTareas()));
            FabricaDeTareas.getInstance().restaurarIdTarea(idTarea);
        }else{
            JOptionPane.showMessageDialog(this, etiquetas.getString("mensajeDebeSeleccionarUnaFila"));
        }        
    }//GEN-LAST:event_boton_BorrarTareaActionPerformed

    private void boton_BorrarTodasLasTareasActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_boton_BorrarTodasLasTareasActionPerformed
        limpiarTablaDeTareas();
        redDeTareas = new RedDeTareas(new ArrayList<Tarea>());
        campoTexto_CantidadTareas.setText(String.valueOf(redDeTareas.obtenerCantidadDeTareas()));
        FabricaDeTareas.getInstance().reset();
    }//GEN-LAST:event_boton_BorrarTodasLasTareasActionPerformed

    private void boton_AnalisisPERTActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_boton_AnalisisPERTActionPerformed
        if (redDeTareas.obtenerCantidadDeTareas() > 0){
            boolean calculoValido = true;
            if (!redDeTareas.elUltimoCalculoPERTesCorrecto()){
                calculoValido = redDeTareas.realizarCalculosPERT();                    
            }
            if (!redDeTareas.tieneAlMenosUnCaminoCriticoDefinido()){
                calculoValido = false;
            }
            if (calculoValido){
                VentanaResultados ventanaResultados = new VentanaResultados(this, true, redDeTareas, unidadDeTiempo, etiquetas, helpBroker);
                ventanaResultados.setVisible(true);
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
        lugarConfigurado = new Locale("es", "UY");
        etiquetas = ResourceBundle.getBundle("Idiomas.MessagesBundle", lugarConfigurado);
        this.setearEtiquetas();
    }//GEN-LAST:event_subMenu_EspañolActionPerformed

    private void subMenu_InglesActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_subMenu_InglesActionPerformed
        lugarConfigurado = new Locale("en", "US");
        etiquetas = ResourceBundle.getBundle("Idiomas.MessagesBundle", lugarConfigurado);
        this.setearEtiquetas();
    }//GEN-LAST:event_subMenu_InglesActionPerformed

    private void subMenu_PortuguesActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_subMenu_PortuguesActionPerformed
        lugarConfigurado = new Locale("po", "BR");
        etiquetas = ResourceBundle.getBundle("Idiomas.MessagesBundle", lugarConfigurado);
        this.setearEtiquetas();
    }//GEN-LAST:event_subMenu_PortuguesActionPerformed

    private void subMenu_AcercaDeActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_subMenu_AcercaDeActionPerformed
        VentanaAcercaDe ventanaAcercaDe = new VentanaAcercaDe(this, true, etiquetas);
        ventanaAcercaDe.setVisible(true);
    }//GEN-LAST:event_subMenu_AcercaDeActionPerformed

    private void subMenu_NuevoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_subMenu_NuevoActionPerformed
        int seleccion = JOptionPane.showOptionDialog(
           this,
           etiquetas.getString("mensajeNuevoProyecto"), 
           etiquetas.getString("mensajeTituloNuevoProyecto"), 
           JOptionPane.OK_CANCEL_OPTION,
           JOptionPane.WARNING_MESSAGE,
           null,    
           new Object[] { etiquetas.getString("mensajeOk"), etiquetas.getString("mensajeCancelar") },   
           "OK");
        if (seleccion == 0){
            limpiarPantallaProyecto();
        }  
    }//GEN-LAST:event_subMenu_NuevoActionPerformed

    private void subMenu_AbrirActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_subMenu_AbrirActionPerformed
        /**JFileChooser fileChooser = new JFileChooser();
        fileChooser.setLocale(lugarConfigurado);
        int seleccion = fileChooser.showOpenDialog(subMenu_Abrir);
        if (seleccion == JFileChooser.APPROVE_OPTION)
        {
           File fichero = fileChooser.getSelectedFile();
           // y a trabajar con fichero ....
        }**/
        JOptionPane.showMessageDialog(this, etiquetas.getString("mensajeFuncionalidadAunNoDisponible"));
    }//GEN-LAST:event_subMenu_AbrirActionPerformed

    private void subMenu_GuardarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_subMenu_GuardarActionPerformed
        JOptionPane.showMessageDialog(this, etiquetas.getString("mensajeFuncionalidadAunNoDisponible"));
    }//GEN-LAST:event_subMenu_GuardarActionPerformed

    private void subMenu_GuardarComoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_subMenu_GuardarComoActionPerformed
        JOptionPane.showMessageDialog(this, etiquetas.getString("mensajeFuncionalidadAunNoDisponible"));
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
