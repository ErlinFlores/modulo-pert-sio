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
import Entidades.EstrategiaDeSeleccionDeDesvEst;
import Entidades.GestorProbabilistico;
import Entidades.RedDeTareas;
import Entidades.Tarea;
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;

/**
 *
 * @author Manuel Lorenze
 */
public class VentanaResultados extends javax.swing.JDialog {

    private RedDeTareas redDeTareas;
    private String unidadDeTiempo;
    private EstrategiaDeSeleccionDeDesvEst estrategia;
    private GestorProbabilistico gestorProbabilistico;
    
    /** Creates new form VentanaResultados */
    public VentanaResultados(java.awt.Frame parent, boolean modal, RedDeTareas redDeTareas, String unidadDeTiempo) {
        super(parent, modal);
        initComponents();
        this.redDeTareas = redDeTareas;
        this.unidadDeTiempo = unidadDeTiempo;
        this.estrategia = resetearEstrategiaDeSeleccionDeDesvEst();
        this.gestorProbabilistico = new GestorProbabilistico(redDeTareas.obtenerDuracionDelProyecto(), redDeTareas.obtenerDesviacionEstandarDelProyecto(estrategia));
        actualizarTablaDeCalculosRealizados();
        actualizarInformacionDelProyecto();
    }

    private EstrategiaDeSeleccionDeDesvEst resetearEstrategiaDeSeleccionDeDesvEst(){
        if (redDeTareas.obtenerCantidadDeCaminosCriticos() > 1){
            chekBox_Suma.setEnabled(true);
            checkBox_Promedio.setEnabled(true);
            checkBox_Mayor.setEnabled(true); 
            chekBox_Suma.setSelected(false);
            checkBox_Promedio.setSelected(true);
            checkBox_Mayor.setSelected(false);
            return EstrategiaDeSeleccionDeDesvEst.promedio;
        }else{         
            chekBox_Suma.setSelected(false);
            checkBox_Promedio.setSelected(false);
            checkBox_Mayor.setSelected(false);
            chekBox_Suma.setEnabled(false);
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
        DefaultTableModel modeloDeTablaDeResultadosCalculados = (DefaultTableModel)tabla_ResultadoDeCalculos.getModel();
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
            tabla_ResultadoDeCalculos.setValueAt(Math.round(tarea.obtenerDesviacionEstandar()*100)/100.0, fila, 9);
            fila += 1;            
        }
        tabla_ResultadoDeCalculos.updateUI();
    }
    
    private void actualizarInformacionDelProyecto(){
        String duracionDelProyectoStr = String.valueOf(redDeTareas.obtenerDuracionDelProyecto())+" "+unidadDeTiempo;
        this.campoTexto_DuracionDelProyecto.setText(duracionDelProyectoStr);
        DefaultTableModel modeloDeTablaDeCaminosCriticos = (DefaultTableModel)tabla_CaminosCriticos.getModel();
        int fila = 0;
        for (CaminoCritico caminoCritico : redDeTareas.obtenerCaminosCriticos()){
            modeloDeTablaDeCaminosCriticos.addRow(new Object[fila]);
            int numeroDeCaminoCritico = fila + 1;
            modeloDeTablaDeCaminosCriticos.setValueAt(numeroDeCaminoCritico,fila,0);
            modeloDeTablaDeCaminosCriticos.setValueAt(caminoCritico.obtenerTareasConcatenadas(),fila,1);
            modeloDeTablaDeCaminosCriticos.setValueAt(Math.round(caminoCritico.obtenerDesviacionEstandar()*100)/100.0, fila, 2);
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
        chekBox_Suma = new javax.swing.JRadioButton();
        checkBox_Promedio = new javax.swing.JRadioButton();
        checkBox_Mayor = new javax.swing.JRadioButton();
        panelDeTab_Estadistica = new javax.swing.JTabbedPane();
        panel_CalculoProbabilidad = new javax.swing.JPanel();
        campoTexto_DuracionParaProbabilidad = new javax.swing.JTextField();
        label_PreguntaProbabilidad2 = new javax.swing.JLabel();
        label_PreguntaProbabilidad1 = new javax.swing.JLabel();
        boton_CalcularProbabilidad = new javax.swing.JButton();
        campoTexto_ProbabilidadCalculada = new javax.swing.JTextField();
        panel_CalculoDuracion = new javax.swing.JPanel();
        label_PreguntaDuracion = new javax.swing.JLabel();
        boton_CalcularDuracion = new javax.swing.JButton();
        campoTexto_DuracionCalculada = new javax.swing.JTextField();
        campoTexto_ProbabilidadParaDuracion = new javax.swing.JTextField();
        jLabel1 = new javax.swing.JLabel();
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

        label_DuracionDelProyecto.setFont(new java.awt.Font("Times New Roman", 1, 14)); // NOI18N
        label_DuracionDelProyecto.setText("Duración del proyecto: ");

        campoTexto_DuracionDelProyecto.setEditable(false);
        campoTexto_DuracionDelProyecto.setFont(new java.awt.Font("Times New Roman", 1, 14));
        campoTexto_DuracionDelProyecto.setHorizontalAlignment(javax.swing.JTextField.CENTER);

        tabla_CaminosCriticos.setFont(new java.awt.Font("Times New Roman", 1, 12)); // NOI18N
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

        chekBox_Suma.setText("Suma");
        chekBox_Suma.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                chekBox_SumaActionPerformed(evt);
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
                .addComponent(chekBox_Suma)
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
                    .addComponent(chekBox_Suma)
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
                        .addComponent(campoTexto_DuracionDelProyecto, javax.swing.GroupLayout.PREFERRED_SIZE, 143, javax.swing.GroupLayout.PREFERRED_SIZE))
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
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        campoTexto_DuracionParaProbabilidad.setHorizontalAlignment(javax.swing.JTextField.CENTER);

        label_PreguntaProbabilidad2.setText("dias o menos?");

        label_PreguntaProbabilidad1.setText("¿Cual es la probabilidad de terminar en");

        boton_CalcularProbabilidad.setText("Calcular probabilidad");
        boton_CalcularProbabilidad.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                boton_CalcularProbabilidadActionPerformed(evt);
            }
        });

        campoTexto_ProbabilidadCalculada.setFont(new java.awt.Font("Times New Roman", 1, 14));
        campoTexto_ProbabilidadCalculada.setHorizontalAlignment(javax.swing.JTextField.CENTER);

        javax.swing.GroupLayout panel_CalculoProbabilidadLayout = new javax.swing.GroupLayout(panel_CalculoProbabilidad);
        panel_CalculoProbabilidad.setLayout(panel_CalculoProbabilidadLayout);
        panel_CalculoProbabilidadLayout.setHorizontalGroup(
            panel_CalculoProbabilidadLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(panel_CalculoProbabilidadLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(panel_CalculoProbabilidadLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addGroup(panel_CalculoProbabilidadLayout.createSequentialGroup()
                        .addComponent(label_PreguntaProbabilidad1)
                        .addGap(6, 6, 6)
                        .addComponent(campoTexto_DuracionParaProbabilidad, javax.swing.GroupLayout.PREFERRED_SIZE, 45, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(label_PreguntaProbabilidad2))
                    .addGroup(panel_CalculoProbabilidadLayout.createSequentialGroup()
                        .addComponent(boton_CalcularProbabilidad, javax.swing.GroupLayout.PREFERRED_SIZE, 176, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(campoTexto_ProbabilidadCalculada)))
                .addContainerGap(115, Short.MAX_VALUE))
        );
        panel_CalculoProbabilidadLayout.setVerticalGroup(
            panel_CalculoProbabilidadLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(panel_CalculoProbabilidadLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(panel_CalculoProbabilidadLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(label_PreguntaProbabilidad1)
                    .addComponent(campoTexto_DuracionParaProbabilidad, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(label_PreguntaProbabilidad2))
                .addGap(18, 18, 18)
                .addGroup(panel_CalculoProbabilidadLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(boton_CalcularProbabilidad)
                    .addComponent(campoTexto_ProbabilidadCalculada, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(32, Short.MAX_VALUE))
        );

        panelDeTab_Estadistica.addTab("Cálculo de Probabilidades", panel_CalculoProbabilidad);

        label_PreguntaDuracion.setText("¿Cual es la duración según una probabilidad de ");

        boton_CalcularDuracion.setText("Calcular duración");
        boton_CalcularDuracion.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                boton_CalcularDuracionActionPerformed(evt);
            }
        });

        campoTexto_DuracionCalculada.setFont(new java.awt.Font("Times New Roman", 1, 14));
        campoTexto_DuracionCalculada.setHorizontalAlignment(javax.swing.JTextField.CENTER);

        campoTexto_ProbabilidadParaDuracion.setHorizontalAlignment(javax.swing.JTextField.CENTER);

        jLabel1.setText("?");

        javax.swing.GroupLayout panel_CalculoDuracionLayout = new javax.swing.GroupLayout(panel_CalculoDuracion);
        panel_CalculoDuracion.setLayout(panel_CalculoDuracionLayout);
        panel_CalculoDuracionLayout.setHorizontalGroup(
            panel_CalculoDuracionLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(panel_CalculoDuracionLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(panel_CalculoDuracionLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addGroup(panel_CalculoDuracionLayout.createSequentialGroup()
                        .addComponent(label_PreguntaDuracion)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(campoTexto_ProbabilidadParaDuracion, javax.swing.GroupLayout.PREFERRED_SIZE, 58, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(panel_CalculoDuracionLayout.createSequentialGroup()
                        .addComponent(boton_CalcularDuracion, javax.swing.GroupLayout.PREFERRED_SIZE, 180, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(campoTexto_DuracionCalculada)))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jLabel1)
                .addContainerGap(124, Short.MAX_VALUE))
        );
        panel_CalculoDuracionLayout.setVerticalGroup(
            panel_CalculoDuracionLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(panel_CalculoDuracionLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(panel_CalculoDuracionLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(label_PreguntaDuracion)
                    .addComponent(campoTexto_ProbabilidadParaDuracion, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel1))
                .addGap(18, 18, 18)
                .addGroup(panel_CalculoDuracionLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(boton_CalcularDuracion)
                    .addComponent(campoTexto_DuracionCalculada, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(32, Short.MAX_VALUE))
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
                            .addComponent(panelDeTab_Estadistica, javax.swing.GroupLayout.DEFAULT_SIZE, 437, Short.MAX_VALUE)
                            .addComponent(boton_Salir, javax.swing.GroupLayout.PREFERRED_SIZE, 107, javax.swing.GroupLayout.PREFERRED_SIZE))))
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
                    .addGroup(javax.swing.GroupLayout.Alignment.LEADING, layout.createSequentialGroup()
                        .addComponent(panelDeTab_Estadistica, javax.swing.GroupLayout.PREFERRED_SIZE, 133, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 104, Short.MAX_VALUE)
                        .addComponent(boton_Salir)))
                .addContainerGap())
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void chekBox_SumaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_chekBox_SumaActionPerformed
        chekBox_Suma.setSelected(true);
        checkBox_Promedio.setSelected(false);
        checkBox_Mayor.setSelected(false);
        estrategia = EstrategiaDeSeleccionDeDesvEst.suma;
        gestorProbabilistico.setearDesviacionEstandarDelProyecto(redDeTareas.obtenerDesviacionEstandarDelProyecto(estrategia));
}//GEN-LAST:event_chekBox_SumaActionPerformed

    private void checkBox_PromedioActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_checkBox_PromedioActionPerformed
        chekBox_Suma.setSelected(false);
        checkBox_Promedio.setSelected(true);
        checkBox_Mayor.setSelected(false);
        estrategia = EstrategiaDeSeleccionDeDesvEst.promedio;
        gestorProbabilistico.setearDesviacionEstandarDelProyecto(redDeTareas.obtenerDesviacionEstandarDelProyecto(estrategia));
}//GEN-LAST:event_checkBox_PromedioActionPerformed

    private void checkBox_MayorActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_checkBox_MayorActionPerformed
        chekBox_Suma.setSelected(false);
        checkBox_Promedio.setSelected(false);
        checkBox_Mayor.setSelected(true);
        estrategia = EstrategiaDeSeleccionDeDesvEst.mayor;
        gestorProbabilistico.setearDesviacionEstandarDelProyecto(redDeTareas.obtenerDesviacionEstandarDelProyecto(estrategia));
}//GEN-LAST:event_checkBox_MayorActionPerformed

    private void boton_CalcularDuracionActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_boton_CalcularDuracionActionPerformed
        double probabilidad = 0;
        boolean probabilidadCorrecta = false;
        try{
            probabilidad = Double.parseDouble(campoTexto_ProbabilidadParaDuracion.getText());
            if ((0 <= probabilidad) && (probabilidad <= 1)){
                probabilidadCorrecta = true;
            }
            if (probabilidadCorrecta){
                this.campoTexto_DuracionCalculada.setText(String.valueOf(gestorProbabilistico.calcularDuracion(probabilidad)));
            }else{
                JOptionPane.showMessageDialog(this, "Probabilidad incorrecta (debe estar entre 0 y 1)");
            }
        }catch(Exception e){
            JOptionPane.showMessageDialog(this, "Caracteres incorrectos en el ingreso de la probabilidad");
        }
}//GEN-LAST:event_boton_CalcularDuracionActionPerformed

    private void boton_CalcularProbabilidadActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_boton_CalcularProbabilidadActionPerformed
        double tiempo = 0;
        boolean tiempoCorrecto = false;
        try{
            tiempo = Double.parseDouble(campoTexto_DuracionParaProbabilidad.getText());
            if (tiempo > 0){
                tiempoCorrecto = true;
            }
            if (tiempoCorrecto){
                this.campoTexto_ProbabilidadCalculada.setText(String.valueOf(gestorProbabilistico.calcularProbabilidad(tiempo)));
            }else{
                JOptionPane.showMessageDialog(this, "Tiempo incorrecto (debe ser mayor que 0)");
            }
        }catch(Exception e){
            JOptionPane.showMessageDialog(this, "Caracteres incorrectos en el ingreso del tiempo");
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
    private javax.swing.JRadioButton chekBox_Suma;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JScrollPane jScrollPane3;
    private javax.swing.JLabel label_DuracionDelProyecto;
    private javax.swing.JLabel label_PreguntaDuracion;
    private javax.swing.JLabel label_PreguntaProbabilidad1;
    private javax.swing.JLabel label_PreguntaProbabilidad2;
    private javax.swing.JTabbedPane panelDeTab_Estadistica;
    private javax.swing.JPanel panel_CalculoDuracion;
    private javax.swing.JPanel panel_CalculoProbabilidad;
    private javax.swing.JPanel panel_Estrategia;
    private javax.swing.JPanel panel_InformacionDelProyecto;
    private javax.swing.JTable tabla_CaminosCriticos;
    private javax.swing.JTable tabla_ResultadoDeCalculos;
    // End of variables declaration//GEN-END:variables
}
