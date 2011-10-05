/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

/*
 * VentanaResultados.java
 *
 * Created on 21/09/2011, 00:31:09
 */
package Ventanas;

import Entidades.CaminoCritico;
import Entidades.Estados.EstrategiaDeSeleccionDeDesvEst;
import Entidades.GestorProbabilistico;
import Entidades.RedDeTareas;
import Entidades.Tarea;
import java.util.ResourceBundle;
import javax.help.HelpBroker;
import javax.swing.JOptionPane;
import javax.swing.SwingConstants;
import javax.swing.border.TitledBorder;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.DefaultTableModel;

/**
 *
 * @author Manuel Lorenze
 */
public class VentanaResultados extends javax.swing.JDialog {

    private ResourceBundle etiquetas;
    private HelpBroker helpBroker;
    
    private DefaultTableModel modeloDeTablaDeResultadosCalculados;
    private DefaultTableModel modeloDeTablaDeCaminosCriticos;   
    
    private String unidadDeTiempo;
    private EstrategiaDeSeleccionDeDesvEst estrategia;
    private RedDeTareas redDeTareas;    
    
    private GestorProbabilistico gestorProbabilistico;
    
    /** Creates new form VentanaResultados */
    public VentanaResultados(java.awt.Frame parent, boolean modal, RedDeTareas redDeTareas, String unidadDeTiempo, ResourceBundle etiquetas, HelpBroker helpBroker) {
        super(parent, modal);
        initComponents();
        this.etiquetas = etiquetas;
        this.helpBroker = helpBroker;
        habilitarAyuda();        
        this.unidadDeTiempo = unidadDeTiempo;
        this.redDeTareas = redDeTareas;
        this.estrategia = resetearEstrategiaDeSeleccionDeDesvEst();        
        this.gestorProbabilistico = new GestorProbabilistico(redDeTareas.obtenerDuracionDelProyecto(), redDeTareas.obtenerDesviacionEstandarDelProyecto(estrategia));
        inicializarTablas();
        actualizarTablaDeCalculosRealizados();
        actualizarInformacionDelProyecto();        
        setearEtiquetas();
    }

    /**
     * Se setean las etiquetas de la pantalla según el idioma configurado.
     */
    private void setearEtiquetas(){
        setTitle(etiquetas.getString("resultadosTitulo"));
        tabla_ResultadoDeCalculos.getColumnModel().getColumn(0).setHeaderValue(etiquetas.getString("resultadosTablaNombre"));
        tabla_ResultadoDeCalculos.getColumnModel().getColumn(1).setHeaderValue(etiquetas.getString("resultadosTablaDuracion"));
        tabla_ResultadoDeCalculos.getColumnModel().getColumn(2).setHeaderValue(etiquetas.getString("resultadosTablaPrecedencia"));
        tabla_ResultadoDeCalculos.getColumnModel().getColumn(3).setHeaderValue(etiquetas.getString("resultadosTablaComienzoTemprano"));
        tabla_ResultadoDeCalculos.getColumnModel().getColumn(4).setHeaderValue(etiquetas.getString("resultadosTablaFinTemprano"));
        tabla_ResultadoDeCalculos.getColumnModel().getColumn(5).setHeaderValue(etiquetas.getString("resultadosTablaComienzoTardio"));
        tabla_ResultadoDeCalculos.getColumnModel().getColumn(6).setHeaderValue(etiquetas.getString("resultadosTablaFinTardio"));
        tabla_ResultadoDeCalculos.getColumnModel().getColumn(7).setHeaderValue(etiquetas.getString("resultadosTablaHolgura"));
        tabla_ResultadoDeCalculos.getColumnModel().getColumn(8).setHeaderValue(etiquetas.getString("resultadosTablaCritica"));
        tabla_ResultadoDeCalculos.getColumnModel().getColumn(9).setHeaderValue(etiquetas.getString("resultadosTablaDesviacionEstandar"));
        
        tabla_CaminosCriticos.getColumnModel().getColumn(0).setHeaderValue(etiquetas.getString("resultadosTablaNumero"));
        tabla_CaminosCriticos.getColumnModel().getColumn(1).setHeaderValue(etiquetas.getString("resutladosTablaCaminoCritico"));
        tabla_CaminosCriticos.getColumnModel().getColumn(2).setHeaderValue(etiquetas.getString("resultadosTablaDesviacionEstandar"));
        
        ((TitledBorder)this.panel_InformacionDelProyecto.getBorder()).setTitle(etiquetas.getString("resultadosLabelInformacionDelProyecto"));
        this.label_DuracionDelProyecto.setText(etiquetas.getString("resultadosLabelDuracionDelProyecto"));
        ((TitledBorder)this.panel_Estrategia.getBorder()).setTitle(etiquetas.getString("resultadosLabelEstrategia"));
        this.checkBox_Suma.setText(etiquetas.getString("resultadosLabelSuma"));
        this.checkBox_Promedio.setText(etiquetas.getString("resultadosLabelPromedio"));
        this.checkBox_Mayor.setText(etiquetas.getString("resultadosLabelMayor"));
        this.panelDeTab_Estadistica.setTitleAt(0, etiquetas.getString("resultadosLabelPanelDeTabCalculoDeProbabilidad"));
        this.panelDeTab_Estadistica.setTitleAt(1, etiquetas.getString("resultadosLabelPanelDeTabCalculoDeDuracion"));
        this.label_PreguntaProbabilidad.setText(etiquetas.getString("resultadosLabelPreguntaProbabilidad"));
        this.label_PreguntaDuracion.setText(etiquetas.getString("resultadosLabelPreguntaDuracion"));
        this.label_Probabilidad.setText(etiquetas.getString("resultadosLabelProbabilidad"));
        this.label_Duracion.setText(etiquetas.getString("resultadosLabelDuracion"));
        this.label_UnidadDeTiempoEnCalculoDeProbabilidades.setText(unidadDeTiempo);               
        this.boton_CalcularProbabilidad.setText(etiquetas.getString("resultadosBotonCalcularProbabilidad"));
        this.boton_CalcularDuracion.setText(etiquetas.getString("resultadosBotonCalcularDuracion")); 
        this.boton_Salir.setText(etiquetas.getString("resultadosBotonSalir"));
    }
    
    private void inicializarTablas(){
        DefaultTableCellRenderer dtcr = new DefaultTableCellRenderer();
        dtcr.setHorizontalAlignment(SwingConstants.CENTER);  
        
        modeloDeTablaDeResultadosCalculados = new DefaultTableModel(0, 10);
        tabla_ResultadoDeCalculos.setModel(modeloDeTablaDeResultadosCalculados);                      
        tabla_ResultadoDeCalculos.getColumnModel().getColumn(0).setMaxWidth(100);
        tabla_ResultadoDeCalculos.getColumnModel().getColumn(0).setResizable(false); 
        tabla_ResultadoDeCalculos.getColumnModel().getColumn(0).setCellRenderer(dtcr);
        tabla_ResultadoDeCalculos.getColumnModel().getColumn(1).setCellRenderer(dtcr);        
        tabla_ResultadoDeCalculos.getColumnModel().getColumn(2).setCellRenderer(dtcr);        
        tabla_ResultadoDeCalculos.getColumnModel().getColumn(3).setCellRenderer(dtcr);        
        tabla_ResultadoDeCalculos.getColumnModel().getColumn(4).setCellRenderer(dtcr);        
        tabla_ResultadoDeCalculos.getColumnModel().getColumn(5).setCellRenderer(dtcr);
        tabla_ResultadoDeCalculos.getColumnModel().getColumn(6).setCellRenderer(dtcr);        
        tabla_ResultadoDeCalculos.getColumnModel().getColumn(7).setCellRenderer(dtcr);        
        tabla_ResultadoDeCalculos.getColumnModel().getColumn(8).setCellRenderer(dtcr);        
        tabla_ResultadoDeCalculos.getColumnModel().getColumn(9).setCellRenderer(dtcr);
        
        modeloDeTablaDeCaminosCriticos = new DefaultTableModel(0, 3);
        tabla_CaminosCriticos.setModel(modeloDeTablaDeCaminosCriticos);
        tabla_CaminosCriticos.getColumnModel().getColumn(0).setMaxWidth(50);
        tabla_CaminosCriticos.getColumnModel().getColumn(0).setResizable(false); 
        tabla_CaminosCriticos.getColumnModel().getColumn(0).setCellRenderer(dtcr);        
        tabla_CaminosCriticos.getColumnModel().getColumn(1).setCellRenderer(dtcr);        
        tabla_CaminosCriticos.getColumnModel().getColumn(2).setCellRenderer(dtcr);       
    }
    
    private void habilitarAyuda(){
        if (helpBroker != null){            
            helpBroker.enableHelpKey(this.getContentPane(), "resultados", helpBroker.getHelpSet());
        } 
    }
    
    private EstrategiaDeSeleccionDeDesvEst resetearEstrategiaDeSeleccionDeDesvEst(){
        if (redDeTareas.obtenerCantidadDeCaminosCriticos() > 1){
            checkBox_Suma.setEnabled(true);
            checkBox_Promedio.setEnabled(true);
            checkBox_Mayor.setEnabled(true); 
            checkBox_Suma.setSelected(false);
            checkBox_Promedio.setSelected(true);
            checkBox_Mayor.setSelected(false);
            return EstrategiaDeSeleccionDeDesvEst.promedio;
        }else{         
            checkBox_Suma.setSelected(false);
            checkBox_Promedio.setSelected(false);
            checkBox_Mayor.setSelected(false);
            checkBox_Suma.setEnabled(false);
            checkBox_Promedio.setEnabled(false);
            checkBox_Mayor.setEnabled(false);       
            return EstrategiaDeSeleccionDeDesvEst.ninguna;
        }        
    }  
    
    /**
     * Se modifica la tabla de tareas del proyecto en la cual se muestran los resultados calculados
     * en base a los datos ingresados por el usuario (duración esperada, tiempos tempranos y tardíos,
     * holgura y si es tarea crítica).
     */
    private void actualizarTablaDeCalculosRealizados(){
        modeloDeTablaDeResultadosCalculados = (DefaultTableModel)tabla_ResultadoDeCalculos.getModel();
        int cantidadDeFilasActual = modeloDeTablaDeResultadosCalculados.getRowCount();
        for (int i = 0; i < cantidadDeFilasActual; i++){//Se eliminan todas las filas actuales. O sea, se limpia la tabla.
            modeloDeTablaDeResultadosCalculados.removeRow(0);
        }
        int fila = 0;
        for (Tarea tarea : redDeTareas.obtenerTareas()){//Se ingresan las filas con los datos actuales.            
            modeloDeTablaDeResultadosCalculados.addRow(new Object[fila]);
            tabla_ResultadoDeCalculos.setValueAt(tarea.obtenerNombre(), fila, 0);
            tabla_ResultadoDeCalculos.setValueAt(tarea.obtenerDuracionEsperada(), fila, 1);
            tabla_ResultadoDeCalculos.setValueAt(tarea.obtenerPrecedencia().obtenerTareasConcatenadas(), fila, 2);
            tabla_ResultadoDeCalculos.setValueAt(tarea.obtenerComienzoTemprano(), fila, 3);
            tabla_ResultadoDeCalculos.setValueAt(tarea.obtenerFinTemprano(), fila, 4);
            tabla_ResultadoDeCalculos.setValueAt(tarea.obtenerComienzoTardio(), fila, 5);
            tabla_ResultadoDeCalculos.setValueAt(tarea.obtenerFinTardio(), fila, 6);
            tabla_ResultadoDeCalculos.setValueAt(tarea.obtenerHolgura(), fila, 7);
            tabla_ResultadoDeCalculos.setValueAt(tarea.esTareaCritica(), fila, 8);
            tabla_ResultadoDeCalculos.setValueAt(tarea.obtenerDesviacionEstandar(), fila, 9);
            fila += 1;            
        }
        tabla_ResultadoDeCalculos.updateUI();
    }
    
    private void actualizarInformacionDelProyecto(){
        String duracionDelProyectoStr = String.valueOf(redDeTareas.obtenerDuracionDelProyecto()+" "+unidadDeTiempo);
        this.campoTexto_DuracionDelProyecto.setText(duracionDelProyectoStr);
        modeloDeTablaDeCaminosCriticos = (DefaultTableModel)tabla_CaminosCriticos.getModel();
        int fila = 0;
        for (CaminoCritico caminoCritico : redDeTareas.obtenerCaminosCriticos()){
            modeloDeTablaDeCaminosCriticos.addRow(new Object[fila]);
            int numeroDeCaminoCritico = fila + 1;
            modeloDeTablaDeCaminosCriticos.setValueAt(numeroDeCaminoCritico,fila,0);
            modeloDeTablaDeCaminosCriticos.setValueAt(caminoCritico.obtenerTareasConcatenadas(),fila,1);
            modeloDeTablaDeCaminosCriticos.setValueAt(caminoCritico.obtenerDesviacionEstandar(), fila, 2);
            fila += 1;
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

        jScrollPane2 = new javax.swing.JScrollPane();
        tabla_ResultadoDeCalculos = new javax.swing.JTable();
        panel_InformacionDelProyecto = new javax.swing.JPanel();
        label_DuracionDelProyecto = new javax.swing.JLabel();
        campoTexto_DuracionDelProyecto = new javax.swing.JTextField();
        jScrollPane3 = new javax.swing.JScrollPane();
        tabla_CaminosCriticos = new javax.swing.JTable();
        panel_Estrategia = new javax.swing.JPanel();
        checkBox_Suma = new javax.swing.JRadioButton();
        checkBox_Promedio = new javax.swing.JRadioButton();
        checkBox_Mayor = new javax.swing.JRadioButton();
        panelDeTab_Estadistica = new javax.swing.JTabbedPane();
        panel_CalculoProbabilidad = new javax.swing.JPanel();
        campoTexto_DuracionParaProbabilidad = new javax.swing.JTextField();
        label_PreguntaProbabilidad = new javax.swing.JLabel();
        boton_CalcularProbabilidad = new javax.swing.JButton();
        campoTexto_ProbabilidadCalculada = new javax.swing.JTextField();
        label_Duracion = new javax.swing.JLabel();
        label_UnidadDeTiempoEnCalculoDeProbabilidades = new javax.swing.JLabel();
        panel_CalculoDuracion = new javax.swing.JPanel();
        label_PreguntaDuracion = new javax.swing.JLabel();
        boton_CalcularDuracion = new javax.swing.JButton();
        campoTexto_DuracionCalculada = new javax.swing.JTextField();
        campoTexto_ProbabilidadParaDuracion = new javax.swing.JTextField();
        label_Probabilidad = new javax.swing.JLabel();
        boton_Salir = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        setResizable(false);

        tabla_ResultadoDeCalculos.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "Nombre", "Duración", "Precedencia", "tComienzoTemp", "tFinTemprano", "tComienzoTardio", "tFinTardio", "Holgura", "Critica", "Desv. Estand."
            }
        ) {
            Class[] types = new Class [] {
                java.lang.String.class, java.lang.String.class, java.lang.String.class, java.lang.String.class, java.lang.String.class, java.lang.String.class, java.lang.String.class, java.lang.String.class, java.lang.Boolean.class, java.lang.String.class
            };
            boolean[] canEdit = new boolean [] {
                false, false, false, false, false, false, false, false, false, false
            };

            public Class getColumnClass(int columnIndex) {
                return types [columnIndex];
            }

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        tabla_ResultadoDeCalculos.setColumnSelectionAllowed(true);
        tabla_ResultadoDeCalculos.setSelectionMode(javax.swing.ListSelectionModel.MULTIPLE_INTERVAL_SELECTION);
        tabla_ResultadoDeCalculos.getTableHeader().setReorderingAllowed(false);
        jScrollPane2.setViewportView(tabla_ResultadoDeCalculos);
        tabla_ResultadoDeCalculos.getColumnModel().getSelectionModel().setSelectionMode(javax.swing.ListSelectionModel.MULTIPLE_INTERVAL_SELECTION);

        panel_InformacionDelProyecto.setBorder(javax.swing.BorderFactory.createTitledBorder("Información del proyecto"));

        label_DuracionDelProyecto.setFont(new java.awt.Font("Times New Roman", 1, 14));
        label_DuracionDelProyecto.setText("Duración del proyecto: ");

        campoTexto_DuracionDelProyecto.setEditable(false);
        campoTexto_DuracionDelProyecto.setFont(new java.awt.Font("Times New Roman", 1, 14)); // NOI18N
        campoTexto_DuracionDelProyecto.setHorizontalAlignment(javax.swing.JTextField.CENTER);

        tabla_CaminosCriticos.setFont(new java.awt.Font("Times New Roman", 1, 12));
        tabla_CaminosCriticos.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "Nro.", "Camino Crítico", "Desv. Estand."
            }
        ) {
            Class[] types = new Class [] {
                java.lang.String.class, java.lang.String.class, java.lang.String.class
            };
            boolean[] canEdit = new boolean [] {
                false, false, false
            };

            public Class getColumnClass(int columnIndex) {
                return types [columnIndex];
            }

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        tabla_CaminosCriticos.setColumnSelectionAllowed(true);
        tabla_CaminosCriticos.setSelectionMode(javax.swing.ListSelectionModel.MULTIPLE_INTERVAL_SELECTION);
        tabla_CaminosCriticos.getTableHeader().setReorderingAllowed(false);
        jScrollPane3.setViewportView(tabla_CaminosCriticos);
        tabla_CaminosCriticos.getColumnModel().getSelectionModel().setSelectionMode(javax.swing.ListSelectionModel.MULTIPLE_INTERVAL_SELECTION);
        tabla_CaminosCriticos.getColumnModel().getColumn(0).setMaxWidth(50);
        tabla_CaminosCriticos.getColumnModel().getColumn(2).setMaxWidth(200);

        panel_Estrategia.setBorder(javax.swing.BorderFactory.createTitledBorder("Estrategia de selección de Desviacion Estandar"));

        checkBox_Suma.setText("Suma");
        checkBox_Suma.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                checkBox_SumaActionPerformed(evt);
            }
        });

        checkBox_Promedio.setText("Promedio");
        checkBox_Promedio.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                checkBox_PromedioActionPerformed(evt);
            }
        });

        checkBox_Mayor.setText("Mayor");
        checkBox_Mayor.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                checkBox_MayorActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout panel_EstrategiaLayout = new javax.swing.GroupLayout(panel_Estrategia);
        panel_Estrategia.setLayout(panel_EstrategiaLayout);
        panel_EstrategiaLayout.setHorizontalGroup(
            panel_EstrategiaLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(panel_EstrategiaLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(checkBox_Suma)
                .addGap(18, 18, 18)
                .addComponent(checkBox_Promedio)
                .addGap(18, 18, 18)
                .addComponent(checkBox_Mayor)
                .addContainerGap(151, Short.MAX_VALUE))
        );
        panel_EstrategiaLayout.setVerticalGroup(
            panel_EstrategiaLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(panel_EstrategiaLayout.createSequentialGroup()
                .addGroup(panel_EstrategiaLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(checkBox_Suma)
                    .addComponent(checkBox_Promedio)
                    .addComponent(checkBox_Mayor))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        javax.swing.GroupLayout panel_InformacionDelProyectoLayout = new javax.swing.GroupLayout(panel_InformacionDelProyecto);
        panel_InformacionDelProyecto.setLayout(panel_InformacionDelProyectoLayout);
        panel_InformacionDelProyectoLayout.setHorizontalGroup(
            panel_InformacionDelProyectoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(panel_InformacionDelProyectoLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(panel_InformacionDelProyectoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jScrollPane3, javax.swing.GroupLayout.DEFAULT_SIZE, 380, Short.MAX_VALUE)
                    .addGroup(panel_InformacionDelProyectoLayout.createSequentialGroup()
                        .addComponent(label_DuracionDelProyecto)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(campoTexto_DuracionDelProyecto, javax.swing.GroupLayout.DEFAULT_SIZE, 233, Short.MAX_VALUE))
                    .addComponent(panel_Estrategia, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addContainerGap())
        );
        panel_InformacionDelProyectoLayout.setVerticalGroup(
            panel_InformacionDelProyectoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(panel_InformacionDelProyectoLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(panel_InformacionDelProyectoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(label_DuracionDelProyecto)
                    .addComponent(campoTexto_DuracionDelProyecto, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addComponent(jScrollPane3, javax.swing.GroupLayout.PREFERRED_SIZE, 95, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(panel_Estrategia, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(13, Short.MAX_VALUE))
        );

        campoTexto_DuracionParaProbabilidad.setHorizontalAlignment(javax.swing.JTextField.CENTER);

        label_PreguntaProbabilidad.setText("¿Cual es la probabilidad dada la duración?");

        boton_CalcularProbabilidad.setText("Calcular probabilidad");
        boton_CalcularProbabilidad.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                boton_CalcularProbabilidadActionPerformed(evt);
            }
        });

        campoTexto_ProbabilidadCalculada.setEditable(false);
        campoTexto_ProbabilidadCalculada.setFont(new java.awt.Font("Times New Roman", 1, 14)); // NOI18N
        campoTexto_ProbabilidadCalculada.setHorizontalAlignment(javax.swing.JTextField.CENTER);

        label_Duracion.setText("Duración:");

        label_UnidadDeTiempoEnCalculoDeProbabilidades.setText("dias");

        javax.swing.GroupLayout panel_CalculoProbabilidadLayout = new javax.swing.GroupLayout(panel_CalculoProbabilidad);
        panel_CalculoProbabilidad.setLayout(panel_CalculoProbabilidadLayout);
        panel_CalculoProbabilidadLayout.setHorizontalGroup(
            panel_CalculoProbabilidadLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(panel_CalculoProbabilidadLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(panel_CalculoProbabilidadLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(label_PreguntaProbabilidad)
                    .addGroup(panel_CalculoProbabilidadLayout.createSequentialGroup()
                        .addComponent(boton_CalcularProbabilidad, javax.swing.GroupLayout.PREFERRED_SIZE, 176, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(campoTexto_ProbabilidadCalculada, javax.swing.GroupLayout.PREFERRED_SIZE, 174, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(panel_CalculoProbabilidadLayout.createSequentialGroup()
                        .addComponent(label_Duracion)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(campoTexto_DuracionParaProbabilidad, javax.swing.GroupLayout.PREFERRED_SIZE, 61, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(label_UnidadDeTiempoEnCalculoDeProbabilidades)))
                .addContainerGap(66, Short.MAX_VALUE))
        );
        panel_CalculoProbabilidadLayout.setVerticalGroup(
            panel_CalculoProbabilidadLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(panel_CalculoProbabilidadLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(label_PreguntaProbabilidad)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(panel_CalculoProbabilidadLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(label_Duracion)
                    .addComponent(campoTexto_DuracionParaProbabilidad, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(label_UnidadDeTiempoEnCalculoDeProbabilidades))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 40, Short.MAX_VALUE)
                .addGroup(panel_CalculoProbabilidadLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(boton_CalcularProbabilidad)
                    .addComponent(campoTexto_ProbabilidadCalculada, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(42, 42, 42))
        );

        panelDeTab_Estadistica.addTab("Cálculo de Probabilidades", panel_CalculoProbabilidad);

        label_PreguntaDuracion.setText("¿Cual es la duración dada la probabilidad?");

        boton_CalcularDuracion.setText("Calcular duración");
        boton_CalcularDuracion.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                boton_CalcularDuracionActionPerformed(evt);
            }
        });

        campoTexto_DuracionCalculada.setEditable(false);
        campoTexto_DuracionCalculada.setFont(new java.awt.Font("Times New Roman", 1, 14)); // NOI18N
        campoTexto_DuracionCalculada.setHorizontalAlignment(javax.swing.JTextField.CENTER);

        campoTexto_ProbabilidadParaDuracion.setHorizontalAlignment(javax.swing.JTextField.CENTER);

        label_Probabilidad.setText("Probabilidad:");

        javax.swing.GroupLayout panel_CalculoDuracionLayout = new javax.swing.GroupLayout(panel_CalculoDuracion);
        panel_CalculoDuracion.setLayout(panel_CalculoDuracionLayout);
        panel_CalculoDuracionLayout.setHorizontalGroup(
            panel_CalculoDuracionLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(panel_CalculoDuracionLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(panel_CalculoDuracionLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(label_PreguntaDuracion)
                    .addGroup(panel_CalculoDuracionLayout.createSequentialGroup()
                        .addComponent(boton_CalcularDuracion, javax.swing.GroupLayout.PREFERRED_SIZE, 180, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(campoTexto_DuracionCalculada, javax.swing.GroupLayout.PREFERRED_SIZE, 177, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(panel_CalculoDuracionLayout.createSequentialGroup()
                        .addComponent(label_Probabilidad)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(campoTexto_ProbabilidadParaDuracion, javax.swing.GroupLayout.PREFERRED_SIZE, 58, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap(59, Short.MAX_VALUE))
        );
        panel_CalculoDuracionLayout.setVerticalGroup(
            panel_CalculoDuracionLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(panel_CalculoDuracionLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(label_PreguntaDuracion)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(panel_CalculoDuracionLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(label_Probabilidad)
                    .addComponent(campoTexto_ProbabilidadParaDuracion, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 36, Short.MAX_VALUE)
                .addGroup(panel_CalculoDuracionLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(boton_CalcularDuracion)
                    .addComponent(campoTexto_DuracionCalculada, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(41, 41, 41))
        );

        panelDeTab_Estadistica.addTab("Cálculo de Duraciones", panel_CalculoDuracion);

        boton_Salir.setText("btnSalir");
        boton_Salir.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                boton_SalirActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 855, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(panel_InformacionDelProyecto, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addComponent(boton_Salir, javax.swing.GroupLayout.PREFERRED_SIZE, 107, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(panelDeTab_Estadistica, javax.swing.GroupLayout.DEFAULT_SIZE, 437, Short.MAX_VALUE))))
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 227, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(panel_InformacionDelProyecto, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(panelDeTab_Estadistica, javax.swing.GroupLayout.PREFERRED_SIZE, 185, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 54, Short.MAX_VALUE)
                        .addComponent(boton_Salir)))
                .addContainerGap())
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void checkBox_SumaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_checkBox_SumaActionPerformed
        checkBox_Suma.setSelected(true);
        checkBox_Promedio.setSelected(false);
        checkBox_Mayor.setSelected(false);
        estrategia = EstrategiaDeSeleccionDeDesvEst.suma;
        gestorProbabilistico.setearDesviacionEstandarDelProyecto(redDeTareas.obtenerDesviacionEstandarDelProyecto(estrategia));
}//GEN-LAST:event_checkBox_SumaActionPerformed

    private void checkBox_PromedioActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_checkBox_PromedioActionPerformed
        checkBox_Suma.setSelected(false);
        checkBox_Promedio.setSelected(true);
        checkBox_Mayor.setSelected(false);
        estrategia = EstrategiaDeSeleccionDeDesvEst.promedio;
        gestorProbabilistico.setearDesviacionEstandarDelProyecto(redDeTareas.obtenerDesviacionEstandarDelProyecto(estrategia));
}//GEN-LAST:event_checkBox_PromedioActionPerformed

    private void checkBox_MayorActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_checkBox_MayorActionPerformed
        checkBox_Suma.setSelected(false);
        checkBox_Promedio.setSelected(false);
        checkBox_Mayor.setSelected(true);
        estrategia = EstrategiaDeSeleccionDeDesvEst.mayor;
        gestorProbabilistico.setearDesviacionEstandarDelProyecto(redDeTareas.obtenerDesviacionEstandarDelProyecto(estrategia));
}//GEN-LAST:event_checkBox_MayorActionPerformed

    private void boton_CalcularDuracionActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_boton_CalcularDuracionActionPerformed
        double probabilidad = 0;
        try{
            probabilidad = Double.parseDouble(campoTexto_ProbabilidadParaDuracion.getText());
            if ((0 <= probabilidad) && (probabilidad <= 1)){                     
                double duracion = gestorProbabilistico.calcularDuracion(probabilidad);
                if (duracion != -1){
                    this.campoTexto_DuracionCalculada.setText(String.valueOf(duracion)+" "+unidadDeTiempo);                    
                }else{
                    JOptionPane.showMessageDialog(this, etiquetas.getString("mensajeProblemaAlRealizarCalculos"));
                }                             
            }else{
                JOptionPane.showMessageDialog(this, etiquetas.getString("mensajeProbabilidadIncorrecta"));
            }
        }catch(Exception e){
            JOptionPane.showMessageDialog(this, etiquetas.getString("mensajeDatosIncorrectos"));
        }
}//GEN-LAST:event_boton_CalcularDuracionActionPerformed

    private void boton_CalcularProbabilidadActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_boton_CalcularProbabilidadActionPerformed
        double tiempo = 0;
        try{
            tiempo = Double.parseDouble(campoTexto_DuracionParaProbabilidad.getText());
            if (tiempo > 0){
                double probabilidad = gestorProbabilistico.calcularProbabilidad(tiempo);
                if (probabilidad != -1){
                    this.campoTexto_ProbabilidadCalculada.setText(String.valueOf(probabilidad));
                }else{
                    JOptionPane.showMessageDialog(this, etiquetas.getString("mensajeProblemaAlRealizarCalculos"));
                }          
            }else{
                JOptionPane.showMessageDialog(this, etiquetas.getString("mensajeDuracionIncorrecta"));
            } 
        }catch(Exception e){
            JOptionPane.showMessageDialog(this, etiquetas.getString("mensajeDatosIncorrectos"));
        }                
}//GEN-LAST:event_boton_CalcularProbabilidadActionPerformed

    private void boton_SalirActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_boton_SalirActionPerformed
        this.dispose();
}//GEN-LAST:event_boton_SalirActionPerformed

    // Este main se deja sin efecto dado que el inicio del programa se maneja desde la clase pert/Main.java
    /**
     * @param args the command line arguments
     */
    /*public static void main(String args[]) {
        java.awt.EventQueue.invokeLater(new Runnable() {

            public void run() {
                VentanaResultados dialog = new VentanaResultados(new javax.swing.JFrame(), true, null, null);
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
    private javax.swing.JButton boton_CalcularDuracion;
    private javax.swing.JButton boton_CalcularProbabilidad;
    private javax.swing.JButton boton_Salir;
    private javax.swing.JTextField campoTexto_DuracionCalculada;
    private javax.swing.JTextField campoTexto_DuracionDelProyecto;
    private javax.swing.JTextField campoTexto_DuracionParaProbabilidad;
    private javax.swing.JTextField campoTexto_ProbabilidadCalculada;
    private javax.swing.JTextField campoTexto_ProbabilidadParaDuracion;
    private javax.swing.JRadioButton checkBox_Mayor;
    private javax.swing.JRadioButton checkBox_Promedio;
    private javax.swing.JRadioButton checkBox_Suma;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JScrollPane jScrollPane3;
    private javax.swing.JLabel label_Duracion;
    private javax.swing.JLabel label_DuracionDelProyecto;
    private javax.swing.JLabel label_PreguntaDuracion;
    private javax.swing.JLabel label_PreguntaProbabilidad;
    private javax.swing.JLabel label_Probabilidad;
    private javax.swing.JLabel label_UnidadDeTiempoEnCalculoDeProbabilidades;
    private javax.swing.JTabbedPane panelDeTab_Estadistica;
    private javax.swing.JPanel panel_CalculoDuracion;
    private javax.swing.JPanel panel_CalculoProbabilidad;
    private javax.swing.JPanel panel_Estrategia;
    private javax.swing.JPanel panel_InformacionDelProyecto;
    private javax.swing.JTable tabla_CaminosCriticos;
    private javax.swing.JTable tabla_ResultadoDeCalculos;
    // End of variables declaration//GEN-END:variables
}
