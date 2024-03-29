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

import Entidades.Estados.Accion;
import Entidades.FabricaDeTareas;
import Entidades.Precedencia;
import Entidades.Tarea;
import Entidades.TiempoEstimado;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;
import javax.help.HelpBroker;
import javax.swing.JOptionPane;
import javax.swing.JTable;
import javax.swing.SwingConstants;
import javax.swing.border.TitledBorder;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.DefaultTableModel;

/**
 *
 * @author Manuel Lorenze
 */
public class VentanaTarea extends javax.swing.JDialog {

    private VentanaProyecto formularioProyecto;
    private ResourceBundle etiquetas;
    private HelpBroker helpBroker;
    
    private FabricaDeTareas fabricaDeTareas = FabricaDeTareas.getInstance();
    private List<Tarea> posiblesTareasPrecedentes;
    
    private DefaultTableModel modeloTablaDeTareasPrecedentes;
    private DefaultTableModel modeloTablaDePosiblesTareasPrecedentes;
    
    private Accion tipoAccion;
    private int id;
    private String nombre;
    private String descripcion;    
    private TiempoEstimado tiemposEstimados;    
    private Precedencia tareasPrecedentes;    
    
    private boolean posibleInconsistenciaEnElOrdenDeTareasDelProyecto;
    
    /** Creates new form VentanaTarea */
    public VentanaTarea(java.awt.Frame parent, boolean modal, ResourceBundle etiquetas, HelpBroker helpBroker) {
        super(parent, modal);
        initComponents();
        this.formularioProyecto = (VentanaProyecto)parent;      
        this.etiquetas = etiquetas;
        this.helpBroker = helpBroker;
        habilitarAyuda();
        this.tipoAccion = Accion.crear;
        this.id = -1;
        this.nombre = fabricaDeTareas.getNombreDeProximaTarea();
        this.descripcion = "";
        this.tiemposEstimados = null;
        this.tareasPrecedentes = new Precedencia(new ArrayList<Tarea>());
        this.posiblesTareasPrecedentes = new ArrayList<Tarea>(this.formularioProyecto.obtenerListaDeTareasDelProyecto());
        inicializarTablas();
        setearEtiquetas();
        setearDatosDeTarea();
        this.posibleInconsistenciaEnElOrdenDeTareasDelProyecto = false;
    }

     /** Creates new form VentanaTarea */
    public VentanaTarea(java.awt.Frame parent, boolean modal, Tarea tarea, ResourceBundle etiquetas, HelpBroker helpBroker) {
        super(parent, modal);
        initComponents();
        this.formularioProyecto = (VentanaProyecto)parent;        
        this.etiquetas = etiquetas;
        this.helpBroker = helpBroker;
        habilitarAyuda();
        this.tipoAccion = Accion.modificar;
        this.id = tarea.obtenerId();
        this.nombre = tarea.obtenerNombre();
        this.descripcion = tarea.obtenerDescripcion();
        this.tiemposEstimados = tarea.obtenerTiempoEstimado();
        this.tareasPrecedentes = tarea.obtenerPrecedencia();
        this.posiblesTareasPrecedentes = this.formularioProyecto.obtenerPosiblesTareasPrecedentes(tarea);
        inicializarTablas();
        setearEtiquetas();
        setearDatosDeTarea();
        this.posibleInconsistenciaEnElOrdenDeTareasDelProyecto = false;
    }
    
    private void habilitarAyuda(){
        if (helpBroker != null){            
            helpBroker.enableHelpKey(this.getContentPane(), "tarea", helpBroker.getHelpSet());
        }
    }
    
    private void inicializarTablas(){
        DefaultTableCellRenderer dtcr = new DefaultTableCellRenderer();
        dtcr.setHorizontalAlignment(SwingConstants.CENTER);  
        
        this.modeloTablaDeTareasPrecedentes = new DefaultTableModel(0, 2);
        this.tabla_TareasPrecedentes.setModel(this.modeloTablaDeTareasPrecedentes);        
        this.tabla_TareasPrecedentes.getColumnModel().getColumn(0).setMaxWidth(100);
        this.tabla_TareasPrecedentes.getColumnModel().getColumn(0).setResizable(false);
        this.tabla_TareasPrecedentes.getColumnModel().getColumn(0).setCellRenderer(dtcr);        
        this.tabla_TareasPrecedentes.getColumnModel().getColumn(1).setCellRenderer(dtcr);

        this.modeloTablaDePosiblesTareasPrecedentes = new DefaultTableModel(0, 2);
        this.tabla_TareasDisponiblesComoPrecedentes.setModel(this.modeloTablaDePosiblesTareasPrecedentes);
        this.tabla_TareasDisponiblesComoPrecedentes.getColumnModel().getColumn(0).setMaxWidth(100);
        this.tabla_TareasDisponiblesComoPrecedentes.getColumnModel().getColumn(0).setResizable(false);
        this.tabla_TareasDisponiblesComoPrecedentes.getColumnModel().getColumn(0).setCellRenderer(dtcr);        
        this.tabla_TareasDisponiblesComoPrecedentes.getColumnModel().getColumn(1).setCellRenderer(dtcr);
    }
    
    /**
     * Se setean las etiquetas de la pantalla.
     */
    private void setearEtiquetas(){
        if (this.tipoAccion == Accion.crear){
            setTitle(etiquetas.getString("tareaTituloCrearTarea"));
        }else{
            if (this.tipoAccion == Accion.modificar){
                setTitle(this.etiquetas.getString("tareaTituloModificarTarea"));
            }else{
                setTitle(this.etiquetas.getString("tareaTituloTarea"));
            }
        }        
        this.label_NombreTarea.setText(this.etiquetas.getString("tareaLabelNombreTarea"));
        this.label_DescripcionTarea.setText(etiquetas.getString("tareaLabelDescripcionTarea"));        
        ((TitledBorder)this.panel_TiemposEstimados.getBorder()).setTitle(this.etiquetas.getString("tareaLabelTiemposEstimados"));
        this.label_TiempoOptimista.setText(this.etiquetas.getString("tareaLabelTiempoOptimista"));
        this.label_TiempoMasProbable.setText(this.etiquetas.getString("tareaLabelTiempoMasProbable"));
        this.label_TiempoPesimista.setText(this.etiquetas.getString("tareaLabelTiempoPesimista"));        
        ((TitledBorder)this.panel_Precedencias.getBorder()).setTitle(this.etiquetas.getString("tareaLabelPrecedencias"));
        this.label_TareasPrecedentes.setText(this.etiquetas.getString("tareaLabelTareasPrecedentes"));
        this.label_TareasDisponiblesComoPrecedentes.setText(this.etiquetas.getString("tareaLabelTareasDisponiblesComoPrecedentes"));    
        this.tabla_TareasPrecedentes.getColumnModel().getColumn(0).setHeaderValue(this.etiquetas.getString("tareaTablaColumnaNombre"));
        this.tabla_TareasPrecedentes.getColumnModel().getColumn(1).setHeaderValue(this.etiquetas.getString("tareaTablaColumnaDescripcion"));
        this.tabla_TareasDisponiblesComoPrecedentes.getColumnModel().getColumn(0).setHeaderValue(this.etiquetas.getString("tareaTablaColumnaNombre"));
        this.tabla_TareasDisponiblesComoPrecedentes.getColumnModel().getColumn(1).setHeaderValue(this.etiquetas.getString("tareaTablaColumnaDescripcion"));
        this.boton_Guardar.setText(this.etiquetas.getString("tareaBotonGuardar"));
        this.boton_Cancelar.setText(this.etiquetas.getString("tareaBotonCancelar"));
    }    
    
    private boolean validarDatosDeEntradaDelUsuario(){
        double to = Double.parseDouble(this.campoTexto_TiempoOptimista.getText());
        double tmp = Double.parseDouble(this.campoTexto_TiempoMasProbable.getText());
        double tp = Double.parseDouble(this.campoTexto_TiempoPesimista.getText());
        if (!((0 <= to) && (to <= tmp) && (tmp < tp) && (tp < Double.MAX_VALUE))){
            return false;
        }
        return true;
    }
    
    private void setearDatosDeTarea(){
        int indiceFila = 0;        
        for (Tarea tarea : this.posiblesTareasPrecedentes){      
            modificarTabla(this.tabla_TareasDisponiblesComoPrecedentes, indiceFila, true, tarea);
            indiceFila += 1;
        }
        this.campoTexto_NombreTarea.setText(nombre);
        if (this.tipoAccion == Accion.modificar){
            indiceFila = 0;
            for (Tarea tareaPrecedente : this.tareasPrecedentes.obtenerPrecedentes()){
                modificarTabla(this.tabla_TareasPrecedentes, indiceFila, true, tareaPrecedente);
                indiceFila += 1;
            }            
            this.campoTexto_DescripcionTarea.setText(this.descripcion);
            this.campoTexto_TiempoOptimista.setText(Double.toString(this.tiemposEstimados.obtenerTiempoOptimista()));
            this.campoTexto_TiempoMasProbable.setText(Double.toString(this.tiemposEstimados.obtenerTiempoMasProbable()));
            this.campoTexto_TiempoPesimista.setText(Double.toString(this.tiemposEstimados.obtenerTiempoPesimista()));
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
        String nombreTarea = (String)this.tabla_TareasDisponiblesComoPrecedentes.getValueAt(filaSeleccionada, 0);
        int idTarea = this.fabricaDeTareas.getIdTareaByNombre(nombreTarea);
        for (Tarea tarea : this.posiblesTareasPrecedentes){
            if (tarea.obtenerId() == idTarea){
                Tarea nuevaTareaPrecedente = tarea;
                this.posiblesTareasPrecedentes.remove(tarea);
                modificarTabla(this.tabla_TareasDisponiblesComoPrecedentes, filaSeleccionada, false, null);
                return nuevaTareaPrecedente;
            }
        }
        return null;
    }
    
    private Tarea quitarTareaDePrecedenciasSegunSeleccion(int filaSeleccionada){
        String nombreTarea = (String)this.tabla_TareasPrecedentes.getValueAt(filaSeleccionada, 0);
        int idTarea = this.fabricaDeTareas.getIdTareaByNombre(nombreTarea);
        Tarea nuevaPosibleTareaPrecedente = this.tareasPrecedentes.obtenerTareaPorID(idTarea);
        this.tareasPrecedentes.borrarPrecedente(nuevaPosibleTareaPrecedente);
        modificarTabla(this.tabla_TareasPrecedentes, filaSeleccionada, false, null);
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

        campoTexto_NombreTarea = new javax.swing.JTextField();
        label_NombreTarea = new javax.swing.JLabel();
        panel_TiemposEstimados = new javax.swing.JPanel();
        label_TiempoOptimista = new javax.swing.JLabel();
        label_TiempoMasProbable = new javax.swing.JLabel();
        campoTexto_TiempoMasProbable = new javax.swing.JTextField();
        label_TiempoPesimista = new javax.swing.JLabel();
        campoTexto_TiempoPesimista = new javax.swing.JTextField();
        campoTexto_TiempoOptimista = new javax.swing.JTextField();
        boton_Cancelar = new javax.swing.JButton();
        campoTexto_DescripcionTarea = new javax.swing.JTextField();
        boton_Guardar = new javax.swing.JButton();
        panel_Precedencias = new javax.swing.JPanel();
        jScrollPane1 = new javax.swing.JScrollPane();
        tabla_TareasPrecedentes = new javax.swing.JTable();
        jScrollPane2 = new javax.swing.JScrollPane();
        tabla_TareasDisponiblesComoPrecedentes = new javax.swing.JTable();
        boton_AgregarPrecedente = new javax.swing.JButton();
        boton_QuitarPrecedente = new javax.swing.JButton();
        label_TareasPrecedentes = new javax.swing.JLabel();
        label_TareasDisponiblesComoPrecedentes = new javax.swing.JLabel();
        label_DescripcionTarea = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);

        campoTexto_NombreTarea.setEditable(false);
        campoTexto_NombreTarea.setFont(new java.awt.Font("Tahoma", 1, 11));
        campoTexto_NombreTarea.setHorizontalAlignment(javax.swing.JTextField.CENTER);

        label_NombreTarea.setText("lblNombreTarea");

        panel_TiemposEstimados.setBorder(javax.swing.BorderFactory.createTitledBorder("lblTiemposEstimados"));

        label_TiempoOptimista.setText("lblTiempoOptimista");

        label_TiempoMasProbable.setText("lblTiempoMasProbable");

        campoTexto_TiempoMasProbable.setHorizontalAlignment(javax.swing.JTextField.CENTER);

        label_TiempoPesimista.setText("lblTiempoPesimista");

        campoTexto_TiempoPesimista.setHorizontalAlignment(javax.swing.JTextField.CENTER);

        campoTexto_TiempoOptimista.setHorizontalAlignment(javax.swing.JTextField.CENTER);

        javax.swing.GroupLayout panel_TiemposEstimadosLayout = new javax.swing.GroupLayout(panel_TiemposEstimados);
        panel_TiemposEstimados.setLayout(panel_TiemposEstimadosLayout);
        panel_TiemposEstimadosLayout.setHorizontalGroup(
            panel_TiemposEstimadosLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(panel_TiemposEstimadosLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(label_TiempoOptimista)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(campoTexto_TiempoOptimista, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(label_TiempoMasProbable)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(campoTexto_TiempoMasProbable, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(label_TiempoPesimista)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(campoTexto_TiempoPesimista, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        panel_TiemposEstimadosLayout.setVerticalGroup(
            panel_TiemposEstimadosLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(panel_TiemposEstimadosLayout.createSequentialGroup()
                .addGroup(panel_TiemposEstimadosLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(label_TiempoOptimista)
                    .addComponent(label_TiempoMasProbable)
                    .addComponent(campoTexto_TiempoMasProbable, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(label_TiempoPesimista)
                    .addComponent(campoTexto_TiempoPesimista, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(campoTexto_TiempoOptimista, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(12, Short.MAX_VALUE))
        );

        boton_Cancelar.setText("btnCancelar");
        boton_Cancelar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                boton_CancelarActionPerformed(evt);
            }
        });

        boton_Guardar.setText("btnGuardar");
        boton_Guardar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                boton_GuardarActionPerformed(evt);
            }
        });

        panel_Precedencias.setBorder(javax.swing.BorderFactory.createTitledBorder("lblPrecedencias"));

        tabla_TareasPrecedentes.setModel(new javax.swing.table.DefaultTableModel(
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
        tabla_TareasPrecedentes.setOpaque(false);
        jScrollPane1.setViewportView(tabla_TareasPrecedentes);
        tabla_TareasPrecedentes.getColumnModel().getColumn(0).setMaxWidth(50);

        tabla_TareasDisponiblesComoPrecedentes.setModel(new javax.swing.table.DefaultTableModel(
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
        tabla_TareasDisponiblesComoPrecedentes.setOpaque(false);
        jScrollPane2.setViewportView(tabla_TareasDisponiblesComoPrecedentes);
        tabla_TareasDisponiblesComoPrecedentes.getColumnModel().getColumn(0).setMaxWidth(50);

        boton_AgregarPrecedente.setText("<");
        boton_AgregarPrecedente.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                boton_AgregarPrecedenteActionPerformed(evt);
            }
        });

        boton_QuitarPrecedente.setText(">");
        boton_QuitarPrecedente.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                boton_QuitarPrecedenteActionPerformed(evt);
            }
        });

        label_TareasPrecedentes.setText("lblTareasPrecedentes");

        label_TareasDisponiblesComoPrecedentes.setText("lblTareasDisponiblesComoPrecedentes");

        javax.swing.GroupLayout panel_PrecedenciasLayout = new javax.swing.GroupLayout(panel_Precedencias);
        panel_Precedencias.setLayout(panel_PrecedenciasLayout);
        panel_PrecedenciasLayout.setHorizontalGroup(
            panel_PrecedenciasLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(panel_PrecedenciasLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(panel_PrecedenciasLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(panel_PrecedenciasLayout.createSequentialGroup()
                        .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 256, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(panel_PrecedenciasLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(boton_AgregarPrecedente)
                            .addComponent(boton_QuitarPrecedente)))
                    .addComponent(label_TareasPrecedentes))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(panel_PrecedenciasLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(label_TareasDisponiblesComoPrecedentes)
                    .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 260, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap())
        );
        panel_PrecedenciasLayout.setVerticalGroup(
            panel_PrecedenciasLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(panel_PrecedenciasLayout.createSequentialGroup()
                .addGroup(panel_PrecedenciasLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(label_TareasPrecedentes)
                    .addComponent(label_TareasDisponiblesComoPrecedentes))
                .addGap(8, 8, 8)
                .addGroup(panel_PrecedenciasLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(panel_PrecedenciasLayout.createSequentialGroup()
                        .addGap(32, 32, 32)
                        .addComponent(boton_AgregarPrecedente)
                        .addGap(18, 18, 18)
                        .addComponent(boton_QuitarPrecedente))
                    .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 118, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 118, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        label_DescripcionTarea.setText("lblDescripcionTarea");

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(panel_Precedencias, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                        .addGroup(javax.swing.GroupLayout.Alignment.LEADING, layout.createSequentialGroup()
                            .addComponent(label_NombreTarea)
                            .addGap(10, 10, 10)
                            .addComponent(campoTexto_NombreTarea, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addGap(18, 18, 18)
                            .addComponent(label_DescripcionTarea)
                            .addGap(10, 10, 10)
                            .addComponent(campoTexto_DescripcionTarea))
                        .addComponent(panel_TiemposEstimados, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addContainerGap(427, Short.MAX_VALUE)
                .addComponent(boton_Guardar)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(boton_Cancelar)
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(campoTexto_NombreTarea, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(label_DescripcionTarea)
                    .addComponent(campoTexto_DescripcionTarea, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(label_NombreTarea))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(panel_TiemposEstimados, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(13, 13, 13)
                .addComponent(panel_Precedencias, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(boton_Cancelar)
                    .addComponent(boton_Guardar))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void boton_CancelarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_boton_CancelarActionPerformed
        this.dispose();
}//GEN-LAST:event_boton_CancelarActionPerformed

    private void boton_GuardarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_boton_GuardarActionPerformed
        try{
            if (validarDatosDeEntradaDelUsuario()){
                this.descripcion = this.campoTexto_DescripcionTarea.getText();
                double tiempoOptimista = Double.parseDouble(this.campoTexto_TiempoOptimista.getText());
                double tiempoMasProbable = Double.parseDouble(this.campoTexto_TiempoMasProbable.getText());
                double tiempoPesimista = Double.parseDouble(this.campoTexto_TiempoPesimista.getText());
                switch (this.tipoAccion){
                    case crear:
                        this.tiemposEstimados = new TiempoEstimado(tiempoOptimista, tiempoMasProbable, tiempoPesimista);
                        Tarea nuevaTarea = this.fabricaDeTareas.crearTarea(this.descripcion, this.tiemposEstimados, this.tareasPrecedentes);
                        this.formularioProyecto.agregarTarea(nuevaTarea);
                        break;
                    case modificar:
                        this.tiemposEstimados.setearTiempoEstimado(tiempoOptimista, tiempoMasProbable, tiempoPesimista);
                        this.formularioProyecto.modificarTarea(this.id, this.descripcion);
                        if (this.posibleInconsistenciaEnElOrdenDeTareasDelProyecto){
                            this.formularioProyecto.establecerConsistenciaEnElOrdenDeTareas();
                        }                        
                        break;
                }
                this.dispose();
            }else{
                JOptionPane.showMessageDialog(this, this.etiquetas.getString("mensajeValoresDeTiemposEstimadosIncorrectos"));
            }
        }catch(Exception ex){
            JOptionPane.showMessageDialog(this, this.etiquetas.getString("mensajeDatosIncorrectos"));
        }
}//GEN-LAST:event_boton_GuardarActionPerformed

    private void boton_AgregarPrecedenteActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_boton_AgregarPrecedenteActionPerformed
        int filaSeleccionada = this.tabla_TareasDisponiblesComoPrecedentes.getSelectedRow();
        if (filaSeleccionada != -1){
            Tarea nuevaTareaPrecedente = quitarTareaDePosiblesPrecedenciasSegunSeleccion(filaSeleccionada);
            int nuevaFila = this.tareasPrecedentes.obtenerCantidadDeTareas();
            this.tareasPrecedentes.agregarPrecedente(nuevaTareaPrecedente);
            this.posibleInconsistenciaEnElOrdenDeTareasDelProyecto = true;
            modificarTabla(this.tabla_TareasPrecedentes, nuevaFila, true, nuevaTareaPrecedente);            
        }
}//GEN-LAST:event_boton_AgregarPrecedenteActionPerformed

    private void boton_QuitarPrecedenteActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_boton_QuitarPrecedenteActionPerformed
        int filaSeleccionada = this.tabla_TareasPrecedentes.getSelectedRow();
        if (filaSeleccionada != -1){
            Tarea nuevaPosibleTareaPrecedente = quitarTareaDePrecedenciasSegunSeleccion(filaSeleccionada);
            int nuevaFila = this.posiblesTareasPrecedentes.size();
            this.posiblesTareasPrecedentes.add(nuevaPosibleTareaPrecedente);
            modificarTabla(this.tabla_TareasDisponiblesComoPrecedentes, nuevaFila, true, nuevaPosibleTareaPrecedente);
        }
}//GEN-LAST:event_boton_QuitarPrecedenteActionPerformed

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
    private javax.swing.JButton boton_AgregarPrecedente;
    private javax.swing.JButton boton_Cancelar;
    private javax.swing.JButton boton_Guardar;
    private javax.swing.JButton boton_QuitarPrecedente;
    private javax.swing.JTextField campoTexto_DescripcionTarea;
    private javax.swing.JTextField campoTexto_NombreTarea;
    private javax.swing.JTextField campoTexto_TiempoMasProbable;
    private javax.swing.JTextField campoTexto_TiempoOptimista;
    private javax.swing.JTextField campoTexto_TiempoPesimista;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JLabel label_DescripcionTarea;
    private javax.swing.JLabel label_NombreTarea;
    private javax.swing.JLabel label_TareasDisponiblesComoPrecedentes;
    private javax.swing.JLabel label_TareasPrecedentes;
    private javax.swing.JLabel label_TiempoMasProbable;
    private javax.swing.JLabel label_TiempoOptimista;
    private javax.swing.JLabel label_TiempoPesimista;
    private javax.swing.JPanel panel_Precedencias;
    private javax.swing.JPanel panel_TiemposEstimados;
    private javax.swing.JTable tabla_TareasDisponiblesComoPrecedentes;
    private javax.swing.JTable tabla_TareasPrecedentes;
    // End of variables declaration//GEN-END:variables
}
