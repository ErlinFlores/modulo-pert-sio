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
            jrbSuma.setEnabled(true);
            jrbPromedio.setEnabled(true);
            jrbMayor.setEnabled(true); 
            jrbSuma.setSelected(false);
            jrbPromedio.setSelected(true);
            jrbMayor.setSelected(false);
            return EstrategiaDeSeleccionDeDesvEst.promedio;
        }else{         
            jrbSuma.setSelected(false);
            jrbPromedio.setSelected(false);
            jrbMayor.setSelected(false);
            jrbSuma.setEnabled(false);
            jrbPromedio.setEnabled(false);
            jrbMayor.setEnabled(false);       
            return EstrategiaDeSeleccionDeDesvEst.ninguna;
        }        
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
            tblResultadoDeCalculos.setValueAt(Math.round(tarea.obtenerDesviacionEstandar()*100)/100.0, fila, 9);
            fila += 1;            
        }
        tblResultadoDeCalculos.updateUI();
    }
    
    private void actualizarInformacionDelProyecto(){
        String duracionDelProyectoStr = String.valueOf(redDeTareas.obtenerDuracionDelProyecto())+" "+unidadDeTiempo;
        this.txtDuracionDelProyecto.setText(duracionDelProyectoStr);
        DefaultTableModel modeloDeTablaDeCaminosCriticos = (DefaultTableModel)tblCaminosCriticos.getModel();
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
        tblResultadoDeCalculos = new javax.swing.JTable();
        jPanel1 = new javax.swing.JPanel();
        lblDuracionDelProyecto = new javax.swing.JLabel();
        txtDuracionDelProyecto = new javax.swing.JTextField();
        jScrollPane3 = new javax.swing.JScrollPane();
        tblCaminosCriticos = new javax.swing.JTable();
        panelEstrategia = new javax.swing.JPanel();
        jrbSuma = new javax.swing.JRadioButton();
        jrbPromedio = new javax.swing.JRadioButton();
        jrbMayor = new javax.swing.JRadioButton();
        jtpDuraciones = new javax.swing.JTabbedPane();
        jPanel2 = new javax.swing.JPanel();
        txtTiempoParaProbabilidad = new javax.swing.JTextField();
        lblInfoUnidadDeTiempo = new javax.swing.JLabel();
        lblProbabilidades = new javax.swing.JLabel();
        btnCalcularProbabilidad = new javax.swing.JButton();
        txtProbabilidadSegunDuracion = new javax.swing.JTextField();
        jPanel3 = new javax.swing.JPanel();
        jLabel2 = new javax.swing.JLabel();
        btnCalcularDuracion = new javax.swing.JButton();
        txtDuracionSegunProbabilidad = new javax.swing.JTextField();
        txtProbabilidadParaDuracion = new javax.swing.JTextField();
        jLabel1 = new javax.swing.JLabel();
        btnSalir = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        setResizable(false);

        tblResultadoDeCalculos.setModel(new javax.swing.table.DefaultTableModel(
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
        tblResultadoDeCalculos.setColumnSelectionAllowed(true);
        tblResultadoDeCalculos.setSelectionMode(javax.swing.ListSelectionModel.MULTIPLE_INTERVAL_SELECTION);
        tblResultadoDeCalculos.getTableHeader().setReorderingAllowed(false);
        jScrollPane2.setViewportView(tblResultadoDeCalculos);
        tblResultadoDeCalculos.getColumnModel().getSelectionModel().setSelectionMode(javax.swing.ListSelectionModel.MULTIPLE_INTERVAL_SELECTION);

        jPanel1.setBorder(javax.swing.BorderFactory.createTitledBorder("Información del proyecto"));

        lblDuracionDelProyecto.setFont(new java.awt.Font("Times New Roman", 1, 14));
        lblDuracionDelProyecto.setText("Duración del proyecto: ");

        txtDuracionDelProyecto.setEditable(false);
        txtDuracionDelProyecto.setFont(new java.awt.Font("Times New Roman", 1, 14));
        txtDuracionDelProyecto.setHorizontalAlignment(javax.swing.JTextField.CENTER);

        tblCaminosCriticos.setFont(new java.awt.Font("Times New Roman", 1, 12));
        tblCaminosCriticos.setModel(new javax.swing.table.DefaultTableModel(
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
        tblCaminosCriticos.setColumnSelectionAllowed(true);
        tblCaminosCriticos.setSelectionMode(javax.swing.ListSelectionModel.MULTIPLE_INTERVAL_SELECTION);
        tblCaminosCriticos.getTableHeader().setReorderingAllowed(false);
        jScrollPane3.setViewportView(tblCaminosCriticos);
        tblCaminosCriticos.getColumnModel().getSelectionModel().setSelectionMode(javax.swing.ListSelectionModel.MULTIPLE_INTERVAL_SELECTION);

        panelEstrategia.setBorder(javax.swing.BorderFactory.createTitledBorder("Estrategia de selección de Desviacion Estandar"));

        jrbSuma.setText("Suma");
        jrbSuma.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jrbSumaActionPerformed(evt);
            }
        });

        jrbPromedio.setText("Promedio");
        jrbPromedio.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jrbPromedioActionPerformed(evt);
            }
        });

        jrbMayor.setText("Mayor");
        jrbMayor.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jrbMayorActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout panelEstrategiaLayout = new javax.swing.GroupLayout(panelEstrategia);
        panelEstrategia.setLayout(panelEstrategiaLayout);
        panelEstrategiaLayout.setHorizontalGroup(
            panelEstrategiaLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(panelEstrategiaLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jrbSuma)
                .addGap(18, 18, 18)
                .addComponent(jrbPromedio)
                .addGap(18, 18, 18)
                .addComponent(jrbMayor)
                .addContainerGap(132, Short.MAX_VALUE))
        );
        panelEstrategiaLayout.setVerticalGroup(
            panelEstrategiaLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(panelEstrategiaLayout.createSequentialGroup()
                .addGroup(panelEstrategiaLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jrbSuma)
                    .addComponent(jrbPromedio)
                    .addComponent(jrbMayor))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jScrollPane3, javax.swing.GroupLayout.DEFAULT_SIZE, 361, Short.MAX_VALUE)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addComponent(lblDuracionDelProyecto)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(txtDuracionDelProyecto, javax.swing.GroupLayout.PREFERRED_SIZE, 143, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addComponent(panelEstrategia, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addContainerGap())
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(lblDuracionDelProyecto)
                    .addComponent(txtDuracionDelProyecto, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addComponent(jScrollPane3, javax.swing.GroupLayout.PREFERRED_SIZE, 95, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(panelEstrategia, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        txtTiempoParaProbabilidad.setHorizontalAlignment(javax.swing.JTextField.CENTER);

        lblInfoUnidadDeTiempo.setText("dias o menos?");

        lblProbabilidades.setText("¿Cual es la probabilidad de terminar en");

        btnCalcularProbabilidad.setText("Calcular probabilidad");
        btnCalcularProbabilidad.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnCalcularProbabilidadActionPerformed(evt);
            }
        });

        txtProbabilidadSegunDuracion.setFont(new java.awt.Font("Times New Roman", 1, 14));
        txtProbabilidadSegunDuracion.setHorizontalAlignment(javax.swing.JTextField.CENTER);

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addComponent(lblProbabilidades)
                        .addGap(6, 6, 6)
                        .addComponent(txtTiempoParaProbabilidad, javax.swing.GroupLayout.PREFERRED_SIZE, 45, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(lblInfoUnidadDeTiempo))
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addComponent(btnCalcularProbabilidad, javax.swing.GroupLayout.PREFERRED_SIZE, 146, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(txtProbabilidadSegunDuracion)))
                .addContainerGap(230, Short.MAX_VALUE))
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(lblProbabilidades)
                    .addComponent(txtTiempoParaProbabilidad, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(lblInfoUnidadDeTiempo))
                .addGap(18, 18, 18)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(btnCalcularProbabilidad)
                    .addComponent(txtProbabilidadSegunDuracion, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(32, Short.MAX_VALUE))
        );

        jtpDuraciones.addTab("Cálculo de Probabilidades", jPanel2);

        jLabel2.setText("¿Cual es la duración según una probabilidad de ");

        btnCalcularDuracion.setText("Calcular duración");
        btnCalcularDuracion.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnCalcularDuracionActionPerformed(evt);
            }
        });

        txtDuracionSegunProbabilidad.setFont(new java.awt.Font("Times New Roman", 1, 14));
        txtDuracionSegunProbabilidad.setHorizontalAlignment(javax.swing.JTextField.CENTER);

        txtProbabilidadParaDuracion.setHorizontalAlignment(javax.swing.JTextField.CENTER);

        jLabel1.setText("?");

        javax.swing.GroupLayout jPanel3Layout = new javax.swing.GroupLayout(jPanel3);
        jPanel3.setLayout(jPanel3Layout);
        jPanel3Layout.setHorizontalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel3Layout.createSequentialGroup()
                        .addComponent(jLabel2)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(txtProbabilidadParaDuracion, javax.swing.GroupLayout.PREFERRED_SIZE, 58, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jLabel1)
                        .addContainerGap(239, Short.MAX_VALUE))
                    .addGroup(jPanel3Layout.createSequentialGroup()
                        .addComponent(btnCalcularDuracion, javax.swing.GroupLayout.PREFERRED_SIZE, 145, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(txtDuracionSegunProbabilidad, javax.swing.GroupLayout.DEFAULT_SIZE, 147, Short.MAX_VALUE)
                        .addGap(239, 239, 239))))
        );
        jPanel3Layout.setVerticalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel2)
                    .addComponent(txtProbabilidadParaDuracion, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel1))
                .addGap(18, 18, 18)
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(btnCalcularDuracion)
                    .addComponent(txtDuracionSegunProbabilidad, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(32, Short.MAX_VALUE))
        );

        jtpDuraciones.addTab("Cálculo de Duraciones", jPanel3);

        btnSalir.setText("btnSalir");
        btnSalir.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnSalirActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jScrollPane2, javax.swing.GroupLayout.DEFAULT_SIZE, 951, Short.MAX_VALUE)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addGroup(layout.createSequentialGroup()
                                .addGap(451, 451, 451)
                                .addComponent(btnSalir, javax.swing.GroupLayout.PREFERRED_SIZE, 107, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(layout.createSequentialGroup()
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(jtpDuraciones)))))
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 227, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(jPanel1, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(jtpDuraciones, javax.swing.GroupLayout.PREFERRED_SIZE, 133, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 104, Short.MAX_VALUE)
                        .addComponent(btnSalir)))
                .addContainerGap())
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void jrbSumaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jrbSumaActionPerformed
        jrbSuma.setSelected(true);
        jrbPromedio.setSelected(false);
        jrbMayor.setSelected(false);
        estrategia = EstrategiaDeSeleccionDeDesvEst.suma;
        gestorProbabilistico.setearDesviacionEstandarDelProyecto(redDeTareas.obtenerDesviacionEstandarDelProyecto(estrategia));
}//GEN-LAST:event_jrbSumaActionPerformed

    private void jrbPromedioActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jrbPromedioActionPerformed
        jrbSuma.setSelected(false);
        jrbPromedio.setSelected(true);
        jrbMayor.setSelected(false);
        estrategia = EstrategiaDeSeleccionDeDesvEst.promedio;
        gestorProbabilistico.setearDesviacionEstandarDelProyecto(redDeTareas.obtenerDesviacionEstandarDelProyecto(estrategia));
}//GEN-LAST:event_jrbPromedioActionPerformed

    private void jrbMayorActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jrbMayorActionPerformed
        jrbSuma.setSelected(false);
        jrbPromedio.setSelected(false);
        jrbMayor.setSelected(true);
        estrategia = EstrategiaDeSeleccionDeDesvEst.mayor;
        gestorProbabilistico.setearDesviacionEstandarDelProyecto(redDeTareas.obtenerDesviacionEstandarDelProyecto(estrategia));
}//GEN-LAST:event_jrbMayorActionPerformed

    private void btnCalcularDuracionActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnCalcularDuracionActionPerformed
        double probabilidad = 0;
        boolean probabilidadCorrecta = false;
        try{
            probabilidad = Double.parseDouble(txtProbabilidadParaDuracion.getText());
            if ((0 <= probabilidad) && (probabilidad <= 1)){
                probabilidadCorrecta = true;
            }
            if (probabilidadCorrecta){
                this.txtDuracionSegunProbabilidad.setText(String.valueOf(gestorProbabilistico.calcularDuracion(probabilidad)));
            }else{
                JOptionPane.showMessageDialog(this, "Probabilidad incorrecta (debe estar entre 0 y 1)");
            }
        }catch(Exception e){
            JOptionPane.showMessageDialog(this, "Caracteres incorrectos en el ingreso de la probabilidad");
        }
}//GEN-LAST:event_btnCalcularDuracionActionPerformed

    private void btnCalcularProbabilidadActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnCalcularProbabilidadActionPerformed
        double tiempo = 0;
        boolean tiempoCorrecto = false;
        try{
            tiempo = Double.parseDouble(txtTiempoParaProbabilidad.getText());
            if (tiempo > 0){
                tiempoCorrecto = true;
            }
            if (tiempoCorrecto){
                this.txtProbabilidadSegunDuracion.setText(String.valueOf(gestorProbabilistico.calcularProbabilidad(tiempo)));
            }else{
                JOptionPane.showMessageDialog(this, "Tiempo incorrecto (debe ser mayor que 0)");
            }
        }catch(Exception e){
            JOptionPane.showMessageDialog(this, "Caracteres incorrectos en el ingreso del tiempo");
        }
}//GEN-LAST:event_btnCalcularProbabilidadActionPerformed

    private void btnSalirActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnSalirActionPerformed
        this.dispose();
}//GEN-LAST:event_btnSalirActionPerformed

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
    private javax.swing.JButton btnCalcularDuracion;
    private javax.swing.JButton btnCalcularProbabilidad;
    private javax.swing.JButton btnSalir;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JPanel jPanel3;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JScrollPane jScrollPane3;
    private javax.swing.JRadioButton jrbMayor;
    private javax.swing.JRadioButton jrbPromedio;
    private javax.swing.JRadioButton jrbSuma;
    private javax.swing.JTabbedPane jtpDuraciones;
    private javax.swing.JLabel lblDuracionDelProyecto;
    private javax.swing.JLabel lblInfoUnidadDeTiempo;
    private javax.swing.JLabel lblProbabilidades;
    private javax.swing.JPanel panelEstrategia;
    private javax.swing.JTable tblCaminosCriticos;
    private javax.swing.JTable tblResultadoDeCalculos;
    private javax.swing.JTextField txtDuracionDelProyecto;
    private javax.swing.JTextField txtDuracionSegunProbabilidad;
    private javax.swing.JTextField txtProbabilidadParaDuracion;
    private javax.swing.JTextField txtProbabilidadSegunDuracion;
    private javax.swing.JTextField txtTiempoParaProbabilidad;
    // End of variables declaration//GEN-END:variables
}
