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
import Entidades.Proyecto;
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
    
    private Proyecto proyecto;
    private EstrategiaDeSeleccionDeDesvEst estrategia;  
    
    private GestorProbabilistico gestorProbabilistico;
    
    /** Creates new form VentanaResultados */
    public VentanaResultados(java.awt.Frame parent, boolean modal, Proyecto proyecto, ResourceBundle etiquetas, HelpBroker helpBroker) {
        super(parent, modal);
        initComponents();
        this.etiquetas = etiquetas;
        this.helpBroker = helpBroker;
        habilitarAyuda();        
        this.proyecto = proyecto;
        this.estrategia = resetearEstrategiaDeSeleccionDeDesvEst();        
        this.gestorProbabilistico = new GestorProbabilistico(this.proyecto.obtenerRedDeTareas().obtenerDuracionDelProyecto(), this.proyecto.obtenerRedDeTareas().obtenerDesviacionEstandarDelProyecto(this.estrategia));
        inicializarTablas();                
        setearEtiquetas();
        actualizarTablaDeCalculosRealizados();
        actualizarInformacionDelProyecto();
    }

    private void habilitarAyuda(){
        if (this.helpBroker != null){            
            this.helpBroker.enableHelpKey(this.getContentPane(), "resultados", this.helpBroker.getHelpSet());
        } 
    }
    
    private EstrategiaDeSeleccionDeDesvEst resetearEstrategiaDeSeleccionDeDesvEst(){
        if (this.proyecto.obtenerRedDeTareas().obtenerCantidadDeCaminosCriticos() > 1){
            this.checkBox_Suma.setEnabled(true);
            this.checkBox_Promedio.setEnabled(true);
            this.checkBox_Mayor.setEnabled(true); 
            this.checkBox_Suma.setSelected(false);
            this.checkBox_Promedio.setSelected(true);
            this.checkBox_Mayor.setSelected(false);
            return EstrategiaDeSeleccionDeDesvEst.promedio;
        }else{         
            this.checkBox_Suma.setSelected(false);
            this.checkBox_Promedio.setSelected(false);
            this.checkBox_Mayor.setSelected(false);
            this.checkBox_Suma.setEnabled(false);
            this.checkBox_Promedio.setEnabled(false);
            this.checkBox_Mayor.setEnabled(false);       
            return EstrategiaDeSeleccionDeDesvEst.ninguna;
        }        
    }  
    
    private void inicializarTablas(){
        DefaultTableCellRenderer dtcr = new DefaultTableCellRenderer();
        dtcr.setHorizontalAlignment(SwingConstants.CENTER);  
        
        this.modeloDeTablaDeResultadosCalculados = new DefaultTableModel(0, 10);
        this.tabla_ResultadoDeCalculos.setModel(this.modeloDeTablaDeResultadosCalculados);                      
        this.tabla_ResultadoDeCalculos.getColumnModel().getColumn(0).setMaxWidth(100);
        this.tabla_ResultadoDeCalculos.getColumnModel().getColumn(0).setResizable(false); 
        this.tabla_ResultadoDeCalculos.getColumnModel().getColumn(0).setCellRenderer(dtcr);
        this.tabla_ResultadoDeCalculos.getColumnModel().getColumn(1).setCellRenderer(dtcr);        
        this.tabla_ResultadoDeCalculos.getColumnModel().getColumn(2).setCellRenderer(dtcr);        
        this.tabla_ResultadoDeCalculos.getColumnModel().getColumn(3).setCellRenderer(dtcr);        
        this.tabla_ResultadoDeCalculos.getColumnModel().getColumn(4).setCellRenderer(dtcr);        
        this.tabla_ResultadoDeCalculos.getColumnModel().getColumn(5).setCellRenderer(dtcr);
        this.tabla_ResultadoDeCalculos.getColumnModel().getColumn(6).setCellRenderer(dtcr);        
        this.tabla_ResultadoDeCalculos.getColumnModel().getColumn(7).setCellRenderer(dtcr);        
        this.tabla_ResultadoDeCalculos.getColumnModel().getColumn(8).setCellRenderer(dtcr);        
        this.tabla_ResultadoDeCalculos.getColumnModel().getColumn(9).setCellRenderer(dtcr);
        
        this.modeloDeTablaDeCaminosCriticos = new DefaultTableModel(0, 3);
        this.tabla_CaminosCriticos.setModel(this.modeloDeTablaDeCaminosCriticos);
        this.tabla_CaminosCriticos.getColumnModel().getColumn(0).setMaxWidth(50);
        this.tabla_CaminosCriticos.getColumnModel().getColumn(0).setResizable(false); 
        this.tabla_CaminosCriticos.getColumnModel().getColumn(0).setCellRenderer(dtcr);        
        this.tabla_CaminosCriticos.getColumnModel().getColumn(1).setCellRenderer(dtcr);        
        this.tabla_CaminosCriticos.getColumnModel().getColumn(2).setCellRenderer(dtcr);       
    }
    
    /**
     * Se setean las etiquetas de la pantalla según el idioma configurado.
     */
    private void setearEtiquetas(){
        setTitle(this.etiquetas.getString("resultadosTitulo"));
        this.tabla_ResultadoDeCalculos.getColumnModel().getColumn(0).setHeaderValue(this.etiquetas.getString("resultadosTablaNombre"));
        this.tabla_ResultadoDeCalculos.getColumnModel().getColumn(1).setHeaderValue(this.etiquetas.getString("resultadosTablaDuracion"));
        this.tabla_ResultadoDeCalculos.getColumnModel().getColumn(2).setHeaderValue(this.etiquetas.getString("resultadosTablaPrecedencia"));
        this.tabla_ResultadoDeCalculos.getColumnModel().getColumn(3).setHeaderValue(this.etiquetas.getString("resultadosTablaComienzoTemprano"));
        this.tabla_ResultadoDeCalculos.getColumnModel().getColumn(4).setHeaderValue(this.etiquetas.getString("resultadosTablaFinTemprano"));
        this.tabla_ResultadoDeCalculos.getColumnModel().getColumn(5).setHeaderValue(this.etiquetas.getString("resultadosTablaComienzoTardio"));
        this.tabla_ResultadoDeCalculos.getColumnModel().getColumn(6).setHeaderValue(this.etiquetas.getString("resultadosTablaFinTardio"));
        this.tabla_ResultadoDeCalculos.getColumnModel().getColumn(7).setHeaderValue(this.etiquetas.getString("resultadosTablaHolgura"));
        this.tabla_ResultadoDeCalculos.getColumnModel().getColumn(8).setHeaderValue(this.etiquetas.getString("resultadosTablaCritica"));
        this.tabla_ResultadoDeCalculos.getColumnModel().getColumn(9).setHeaderValue(this.etiquetas.getString("resultadosTablaDesviacionEstandar"));
        
        this.tabla_CaminosCriticos.getColumnModel().getColumn(0).setHeaderValue(this.etiquetas.getString("resultadosTablaNumero"));
        this.tabla_CaminosCriticos.getColumnModel().getColumn(1).setHeaderValue(this.etiquetas.getString("resutladosTablaCaminoCritico"));
        this.tabla_CaminosCriticos.getColumnModel().getColumn(2).setHeaderValue(this.etiquetas.getString("resultadosTablaDesviacionEstandar"));
        
        ((TitledBorder)this.panel_InformacionDelProyecto.getBorder()).setTitle(this.etiquetas.getString("resultadosLabelInformacionDelProyecto"));
        this.label_DuracionDelProyecto.setText(this.etiquetas.getString("resultadosLabelDuracionDelProyecto"));
        ((TitledBorder)this.panel_Estrategia.getBorder()).setTitle(this.etiquetas.getString("resultadosLabelEstrategia"));
        this.checkBox_Suma.setText(this.etiquetas.getString("resultadosLabelSuma"));
        this.checkBox_Promedio.setText(this.etiquetas.getString("resultadosLabelPromedio"));
        this.checkBox_Mayor.setText(this.etiquetas.getString("resultadosLabelMayor"));
        this.panelDeTab_Estadistica.setTitleAt(0, this.etiquetas.getString("resultadosLabelPanelDeTabCalculoDeProbabilidad"));
        this.panelDeTab_Estadistica.setTitleAt(1, this.etiquetas.getString("resultadosLabelPanelDeTabCalculoDeDuracion"));
        this.label_PreguntaProbabilidad.setText(this.etiquetas.getString("resultadosLabelPreguntaProbabilidad"));
        this.label_PreguntaDuracion.setText(this.etiquetas.getString("resultadosLabelPreguntaDuracion"));
        this.label_Probabilidad.setText(this.etiquetas.getString("resultadosLabelProbabilidad"));
        this.label_Duracion.setText(this.etiquetas.getString("resultadosLabelDuracion"));
        this.label_UnidadDeTiempoEnCalculoDeProbabilidades.setText(this.proyecto.obtenerUnidadDeTiempo());               
        this.boton_CalcularProbabilidad.setText(this.etiquetas.getString("resultadosBotonCalcularProbabilidad"));
        this.boton_CalcularDuracion.setText(this.etiquetas.getString("resultadosBotonCalcularDuracion")); 
        this.boton_Salir.setText(this.etiquetas.getString("resultadosBotonSalir"));
    }       
    
    /**
     * Se modifica la tabla de tareas del proyecto en la cual se muestran los resultados calculados
     * en base a los datos ingresados por el usuario (duración esperada, tiempos tempranos y tardíos,
     * holgura y si es tarea crítica).
     */
    private void actualizarTablaDeCalculosRealizados(){
        this.modeloDeTablaDeResultadosCalculados = (DefaultTableModel)this.tabla_ResultadoDeCalculos.getModel();
        int cantidadDeFilasActual = this.modeloDeTablaDeResultadosCalculados.getRowCount();
        for (int i = 0; i < cantidadDeFilasActual; i++){//Se eliminan todas las filas actuales. O sea, se limpia la tabla.
            this.modeloDeTablaDeResultadosCalculados.removeRow(0);
        }
        int fila = 0;
        for (Tarea tarea : this.proyecto.obtenerRedDeTareas().obtenerTareas()){//Se ingresan las filas con los datos actuales.            
            this.modeloDeTablaDeResultadosCalculados.addRow(new Object[fila]);
            this.tabla_ResultadoDeCalculos.setValueAt(tarea.obtenerNombre(), fila, 0);
            this.tabla_ResultadoDeCalculos.setValueAt(tarea.obtenerDuracionEsperada(), fila, 1);
            this.tabla_ResultadoDeCalculos.setValueAt(tarea.obtenerPrecedencia().obtenerTareasConcatenadas(), fila, 2);
            this.tabla_ResultadoDeCalculos.setValueAt(tarea.obtenerComienzoTemprano(), fila, 3);
            this.tabla_ResultadoDeCalculos.setValueAt(tarea.obtenerFinTemprano(), fila, 4);
            this.tabla_ResultadoDeCalculos.setValueAt(tarea.obtenerComienzoTardio(), fila, 5);
            this.tabla_ResultadoDeCalculos.setValueAt(tarea.obtenerFinTardio(), fila, 6);
            this.tabla_ResultadoDeCalculos.setValueAt(tarea.obtenerHolgura(), fila, 7);
            this.tabla_ResultadoDeCalculos.setValueAt(esCritica(tarea.esTareaCritica()), fila, 8);
            this.tabla_ResultadoDeCalculos.setValueAt(tarea.obtenerDesviacionEstandar(), fila, 9);
            fila += 1;            
        }
        this.tabla_ResultadoDeCalculos.updateUI();
    }
    
    private String esCritica(boolean esCritica){
        if (esCritica){
            return etiquetas.getString("resultadosSi");
        }else{
            return etiquetas.getString("resultadosNo");
        }
    }
    
    private void actualizarInformacionDelProyecto(){
        String duracionDelProyectoStr = String.valueOf(this.proyecto.obtenerRedDeTareas().obtenerDuracionDelProyecto()+" "+this.proyecto.obtenerUnidadDeTiempo());
        this.campoTexto_DuracionDelProyecto.setText(duracionDelProyectoStr);
        this.modeloDeTablaDeCaminosCriticos = (DefaultTableModel)this.tabla_CaminosCriticos.getModel();
        int fila = 0;
        for (CaminoCritico caminoCritico : this.proyecto.obtenerRedDeTareas().obtenerCaminosCriticos()){
            this.modeloDeTablaDeCaminosCriticos.addRow(new Object[fila]);
            int numeroDeCaminoCritico = fila + 1;
            this.modeloDeTablaDeCaminosCriticos.setValueAt(numeroDeCaminoCritico,fila,0);
            this.modeloDeTablaDeCaminosCriticos.setValueAt(caminoCritico.obtenerTareasConcatenadas(),fila,1);
            this.modeloDeTablaDeCaminosCriticos.setValueAt(caminoCritico.obtenerDesviacionEstandar(), fila, 2);
            fila += 1;
        }
        this.tabla_CaminosCriticos.updateUI();
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
        label_NoExisteDuracion = new javax.swing.JLabel();
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
        campoTexto_DuracionDelProyecto.setFont(new java.awt.Font("Times New Roman", 1, 14));
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
        campoTexto_ProbabilidadCalculada.setFont(new java.awt.Font("Times New Roman", 1, 14));
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
        campoTexto_DuracionCalculada.setFont(new java.awt.Font("Times New Roman", 1, 14));
        campoTexto_DuracionCalculada.setHorizontalAlignment(javax.swing.JTextField.CENTER);

        campoTexto_ProbabilidadParaDuracion.setHorizontalAlignment(javax.swing.JTextField.CENTER);

        label_Probabilidad.setText("Probabilidad:");

        label_NoExisteDuracion.setForeground(new java.awt.Color(204, 0, 0));
        label_NoExisteDuracion.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);

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
                        .addComponent(campoTexto_ProbabilidadParaDuracion, javax.swing.GroupLayout.PREFERRED_SIZE, 58, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addComponent(label_NoExisteDuracion))
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
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 30, Short.MAX_VALUE)
                .addComponent(label_NoExisteDuracion)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
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
        this.checkBox_Suma.setSelected(true);
        this.checkBox_Promedio.setSelected(false);
        this.checkBox_Mayor.setSelected(false);
        this.estrategia = EstrategiaDeSeleccionDeDesvEst.suma;
        this.gestorProbabilistico.setearDesviacionEstandarDelProyecto(this.proyecto.obtenerRedDeTareas().obtenerDesviacionEstandarDelProyecto(estrategia));
}//GEN-LAST:event_checkBox_SumaActionPerformed

    private void checkBox_PromedioActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_checkBox_PromedioActionPerformed
        this.checkBox_Suma.setSelected(false);
        this.checkBox_Promedio.setSelected(true);
        this.checkBox_Mayor.setSelected(false);
        this.estrategia = EstrategiaDeSeleccionDeDesvEst.promedio;
        this.gestorProbabilistico.setearDesviacionEstandarDelProyecto(this.proyecto.obtenerRedDeTareas().obtenerDesviacionEstandarDelProyecto(estrategia));
}//GEN-LAST:event_checkBox_PromedioActionPerformed

    private void checkBox_MayorActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_checkBox_MayorActionPerformed
        this.checkBox_Suma.setSelected(false);
        this.checkBox_Promedio.setSelected(false);
        this.checkBox_Mayor.setSelected(true);
        this.estrategia = EstrategiaDeSeleccionDeDesvEst.mayor;
        this.gestorProbabilistico.setearDesviacionEstandarDelProyecto(this.proyecto.obtenerRedDeTareas().obtenerDesviacionEstandarDelProyecto(estrategia));
}//GEN-LAST:event_checkBox_MayorActionPerformed

    private void boton_CalcularDuracionActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_boton_CalcularDuracionActionPerformed
        this.label_NoExisteDuracion.setText("");
        this.campoTexto_DuracionCalculada.setText("");        
        double probabilidad = 0;
        try{
            probabilidad = Double.parseDouble(this.campoTexto_ProbabilidadParaDuracion.getText());
            if ((0 <= probabilidad) && (probabilidad <= 1)){                     
                Double duracion = this.gestorProbabilistico.calcularDuracion(probabilidad);                
                if (duracion != null){
                    if (duracion <= 0){
                        this.label_NoExisteDuracion.setText(this.etiquetas.getString("resultadosNoExisteDuracionConProbabilidadDada"));                    
                    }else{                        
                        this.campoTexto_DuracionCalculada.setText(String.valueOf(duracion)+" "+this.proyecto.obtenerUnidadDeTiempo());                    
                    }                    
                }else{
                    JOptionPane.showMessageDialog(this, this.etiquetas.getString("mensajeProblemaAlRealizarCalculos"));
                }                             
            }else{
                JOptionPane.showMessageDialog(this, this.etiquetas.getString("mensajeProbabilidadIncorrecta"));
            }
        }catch(Exception e){
            JOptionPane.showMessageDialog(this, this.etiquetas.getString("mensajeDatosIncorrectos"));
        }
}//GEN-LAST:event_boton_CalcularDuracionActionPerformed

    private void boton_CalcularProbabilidadActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_boton_CalcularProbabilidadActionPerformed
        this.campoTexto_ProbabilidadCalculada.setText("");
        double duracion = 0;
        try{
            duracion = Double.parseDouble(this.campoTexto_DuracionParaProbabilidad.getText());
            if (duracion > 0){
                Double probabilidad = this.gestorProbabilistico.calcularProbabilidad(duracion);
                if (probabilidad != null){
                    this.campoTexto_ProbabilidadCalculada.setText(String.valueOf(probabilidad));
                }else{
                    JOptionPane.showMessageDialog(this, this.etiquetas.getString("mensajeProblemaAlRealizarCalculos"));
                }          
            }else{
                JOptionPane.showMessageDialog(this, this.etiquetas.getString("mensajeDuracionIncorrecta"));
            } 
        }catch(Exception e){
            JOptionPane.showMessageDialog(this, this.etiquetas.getString("mensajeDatosIncorrectos"));
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
    private javax.swing.JLabel label_NoExisteDuracion;
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
