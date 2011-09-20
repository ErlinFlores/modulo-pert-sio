/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

/*
 * FormResultado.java
 *
 * Created on 27/08/2011, 10:44:08
 */
package Forms;

import Entidades.CaminoCritico;
import Entidades.GestorProbabilistico;
import Entidades.RedDeTareas;
import Entidades.Tarea;
import Entidades.UnidadDeTiempo;
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;

/**
 *
 * @author Manuel Lorenze
 */
public class FormResultado extends javax.swing.JFrame {

    private RedDeTareas redDeTareas;
    private UnidadDeTiempo unidadDeTiempo;
    private GestorProbabilistico gestorProbabilistico;
    
    /** Creates new form FormResultado */
    public FormResultado(RedDeTareas redDeTareas, UnidadDeTiempo unidadDeTiempo) {
        initComponents();
        this.redDeTareas = redDeTareas;
        this.unidadDeTiempo = unidadDeTiempo;
        this.gestorProbabilistico = new GestorProbabilistico(redDeTareas.obtenerDuracionDelProyecto(), redDeTareas.obtenerDesviacionEstandarDelProyecto());
        actualizarTablaDeCalculosRealizados();
        actualizarInformacionDelProyecto();
        actualizarEstrategiaDeSeleccionDeDesvEst();
    }
    
    private void actualizarEstrategiaDeSeleccionDeDesvEst(){
        if (redDeTareas.obtenerCantidadDeCaminosCriticos() > 1){
            jrbSumaP.setSelected(false);
            jrbPromedioP.setSelected(true);
            jrbMayorP.setSelected(false);
            jrbSumaD.setSelected(false);
            jrbPromedioD.setSelected(true);
            jrbMayorD.setSelected(false);
        }else{
            jrbSumaP.setEnabled(false);
            jrbPromedioP.setEnabled(false);
            jrbMayorP.setEnabled(false);
            jrbSumaD.setEnabled(false);
            jrbPromedioD.setEnabled(false);
            jrbMayorD.setEnabled(false);
        }        
    }

    private EstrategiaDeSeleccionDeDesvEst obtenerEstrategia(){
        switch (estrategia)
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
        String duracionDelProyectoStr = String.valueOf(redDeTareas.obtenerDuracionDelProyecto());
        switch(unidadDeTiempo){
            case horas:{
                if (redDeTareas.obtenerDuracionDelProyecto() == 1){
                    duracionDelProyectoStr += " hora";
                    break;
                }else{
                    duracionDelProyectoStr += " horas";
                }
                break;
            }
            case dias:{
                if (redDeTareas.obtenerDuracionDelProyecto() == 1){
                    duracionDelProyectoStr += " dia";
                    break;
                }else{
                    duracionDelProyectoStr += " dias";
                }
                break;
            }
            case meses:{
                if (redDeTareas.obtenerDuracionDelProyecto() == 1){
                    duracionDelProyectoStr += " mes";
                    break;
                }else{
                    duracionDelProyectoStr += " meses";
                }
                break;
            }
        }
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
        btnSalir = new javax.swing.JButton();
        jPanel1 = new javax.swing.JPanel();
        lblDuracionDelProyecto = new javax.swing.JLabel();
        txtDuracionDelProyecto = new javax.swing.JTextField();
        jScrollPane3 = new javax.swing.JScrollPane();
        tblCaminosCriticos = new javax.swing.JTable();
        jtpDuraciones = new javax.swing.JTabbedPane();
        jPanel2 = new javax.swing.JPanel();
        txtTiempoParaProbabilidad = new javax.swing.JTextField();
        lblInfoUnidadDeTiempo = new javax.swing.JLabel();
        lblProbabilidades = new javax.swing.JLabel();
        btnCalcularProbabilidad = new javax.swing.JButton();
        txtProbabilidadSegunDuracion = new javax.swing.JTextField();
        panelEstrategiaP = new javax.swing.JPanel();
        jrbSumaP = new javax.swing.JRadioButton();
        jrbPromedioP = new javax.swing.JRadioButton();
        jrbMayorP = new javax.swing.JRadioButton();
        jPanel3 = new javax.swing.JPanel();
        jLabel2 = new javax.swing.JLabel();
        btnCalcularDuracion = new javax.swing.JButton();
        txtDuracionSegunProbabilidad = new javax.swing.JTextField();
        txtProbabilidadParaDuracion = new javax.swing.JTextField();
        panelEstrategiaD = new javax.swing.JPanel();
        jrbSumaD = new javax.swing.JRadioButton();
        jrbPromedioD = new javax.swing.JRadioButton();
        jrbMayorD = new javax.swing.JRadioButton();

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
        tblResultadoDeCalculos.setSelectionMode(javax.swing.ListSelectionModel.SINGLE_SELECTION);
        tblResultadoDeCalculos.getTableHeader().setReorderingAllowed(false);
        jScrollPane2.setViewportView(tblResultadoDeCalculos);
        tblResultadoDeCalculos.getColumnModel().getSelectionModel().setSelectionMode(javax.swing.ListSelectionModel.SINGLE_SELECTION);

        btnSalir.setText("btnSalir");
        btnSalir.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnSalirActionPerformed(evt);
            }
        });

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
        tblCaminosCriticos.setSelectionMode(javax.swing.ListSelectionModel.MULTIPLE_INTERVAL_SELECTION);
        jScrollPane3.setViewportView(tblCaminosCriticos);
        tblCaminosCriticos.getColumnModel().getColumn(0).setMaxWidth(50);

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(jScrollPane3, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, 291, Short.MAX_VALUE)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addComponent(lblDuracionDelProyecto)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(txtDuracionDelProyecto, javax.swing.GroupLayout.DEFAULT_SIZE, 144, Short.MAX_VALUE)))
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
                .addComponent(jScrollPane3, javax.swing.GroupLayout.PREFERRED_SIZE, 103, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(61, Short.MAX_VALUE))
        );

        txtTiempoParaProbabilidad.setHorizontalAlignment(javax.swing.JTextField.CENTER);

        lblInfoUnidadDeTiempo.setText("dias o menos.");

        lblProbabilidades.setText("Cual es la probabilidad de terminar en");

        btnCalcularProbabilidad.setText("Calcular probabilidad");
        btnCalcularProbabilidad.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnCalcularProbabilidadActionPerformed(evt);
            }
        });

        txtProbabilidadSegunDuracion.setFont(new java.awt.Font("Times New Roman", 1, 14));
        txtProbabilidadSegunDuracion.setHorizontalAlignment(javax.swing.JTextField.CENTER);

        panelEstrategiaP.setBorder(javax.swing.BorderFactory.createTitledBorder("Estrategia de selección de Desv. Est."));

        jrbSumaP.setText("Suma");
        jrbSumaP.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jrbSumaPActionPerformed(evt);
            }
        });

        jrbPromedioP.setText("Promedio");
        jrbPromedioP.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jrbPromedioPActionPerformed(evt);
            }
        });

        jrbMayorP.setText("Mayor");
        jrbMayorP.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jrbMayorPActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout panelEstrategiaPLayout = new javax.swing.GroupLayout(panelEstrategiaP);
        panelEstrategiaP.setLayout(panelEstrategiaPLayout);
        panelEstrategiaPLayout.setHorizontalGroup(
            panelEstrategiaPLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(panelEstrategiaPLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(panelEstrategiaPLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jrbSumaP)
                    .addComponent(jrbPromedioP)
                    .addComponent(jrbMayorP))
                .addContainerGap(107, Short.MAX_VALUE))
        );
        panelEstrategiaPLayout.setVerticalGroup(
            panelEstrategiaPLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(panelEstrategiaPLayout.createSequentialGroup()
                .addComponent(jrbSumaP)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jrbPromedioP)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jrbMayorP)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(panelEstrategiaP, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                            .addGroup(javax.swing.GroupLayout.Alignment.LEADING, jPanel2Layout.createSequentialGroup()
                                .addComponent(btnCalcularProbabilidad, javax.swing.GroupLayout.PREFERRED_SIZE, 150, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(txtProbabilidadSegunDuracion))
                            .addGroup(javax.swing.GroupLayout.Alignment.LEADING, jPanel2Layout.createSequentialGroup()
                                .addComponent(lblProbabilidades)
                                .addGap(6, 6, 6)
                                .addComponent(txtTiempoParaProbabilidad, javax.swing.GroupLayout.PREFERRED_SIZE, 45, javax.swing.GroupLayout.PREFERRED_SIZE)))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(lblInfoUnidadDeTiempo)))
                .addContainerGap(28, Short.MAX_VALUE))
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(lblProbabilidades)
                    .addComponent(txtTiempoParaProbabilidad, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(lblInfoUnidadDeTiempo))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(btnCalcularProbabilidad)
                    .addComponent(txtProbabilidadSegunDuracion, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 35, Short.MAX_VALUE)
                .addComponent(panelEstrategiaP, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
        );

        jtpDuraciones.addTab("Cálculo de Probabilidades", jPanel2);

        jLabel2.setText("Cual es la duración según una probabilidad de ");

        btnCalcularDuracion.setText("Calcular duración");
        btnCalcularDuracion.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnCalcularDuracionActionPerformed(evt);
            }
        });

        txtDuracionSegunProbabilidad.setFont(new java.awt.Font("Times New Roman", 1, 14));
        txtDuracionSegunProbabilidad.setHorizontalAlignment(javax.swing.JTextField.CENTER);

        txtProbabilidadParaDuracion.setHorizontalAlignment(javax.swing.JTextField.CENTER);

        panelEstrategiaD.setBorder(javax.swing.BorderFactory.createTitledBorder("Estrategia de selección de Desv. Est."));

        jrbSumaD.setText("Suma");
        jrbSumaD.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jrbSumaDActionPerformed(evt);
            }
        });

        jrbPromedioD.setText("Promedio");
        jrbPromedioD.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jrbPromedioDActionPerformed(evt);
            }
        });

        jrbMayorD.setText("Mayor");
        jrbMayorD.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jrbMayorDActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout panelEstrategiaDLayout = new javax.swing.GroupLayout(panelEstrategiaD);
        panelEstrategiaD.setLayout(panelEstrategiaDLayout);
        panelEstrategiaDLayout.setHorizontalGroup(
            panelEstrategiaDLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(panelEstrategiaDLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(panelEstrategiaDLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jrbSumaD)
                    .addComponent(jrbPromedioD)
                    .addComponent(jrbMayorD))
                .addContainerGap(107, Short.MAX_VALUE))
        );
        panelEstrategiaDLayout.setVerticalGroup(
            panelEstrategiaDLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(panelEstrategiaDLayout.createSequentialGroup()
                .addComponent(jrbSumaD)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jrbPromedioD)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jrbMayorD)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        javax.swing.GroupLayout jPanel3Layout = new javax.swing.GroupLayout(jPanel3);
        jPanel3.setLayout(jPanel3Layout);
        jPanel3Layout.setHorizontalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel3Layout.createSequentialGroup()
                        .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(btnCalcularDuracion, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(jLabel2, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                        .addGap(6, 6, 6)
                        .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(txtProbabilidadParaDuracion)
                            .addComponent(txtDuracionSegunProbabilidad, javax.swing.GroupLayout.DEFAULT_SIZE, 91, Short.MAX_VALUE)))
                    .addComponent(panelEstrategiaD, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        jPanel3Layout.setVerticalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel2)
                    .addComponent(txtProbabilidadParaDuracion, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(btnCalcularDuracion)
                    .addComponent(txtDuracionSegunProbabilidad, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 35, Short.MAX_VALUE)
                .addComponent(panelEstrategiaD, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
        );

        jtpDuraciones.addTab("Cálculo de Duraciones", jPanel3);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jScrollPane2, javax.swing.GroupLayout.DEFAULT_SIZE, 921, Short.MAX_VALUE)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(layout.createSequentialGroup()
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 491, Short.MAX_VALUE)
                                .addComponent(btnSalir, javax.swing.GroupLayout.PREFERRED_SIZE, 107, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(layout.createSequentialGroup()
                                .addGap(56, 56, 56)
                                .addComponent(jtpDuraciones, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))))
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 227, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jtpDuraciones)
                    .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(btnSalir)
                .addContainerGap())
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void btnSalirActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnSalirActionPerformed
        this.dispose();
    }//GEN-LAST:event_btnSalirActionPerformed

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

    private void jrbSumaPActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jrbSumaPActionPerformed
        jrbSumaP.setSelected(true);
        jrbPromedioP.setSelected(false);
        jrbMayorP.setSelected(false);
        
    }//GEN-LAST:event_jrbSumaPActionPerformed

    private void jrbPromedioPActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jrbPromedioPActionPerformed
        jrbSumaP.setSelected(false);
        jrbPromedioP.setSelected(true);
        jrbMayorP.setSelected(false);
    }//GEN-LAST:event_jrbPromedioPActionPerformed

    private void jrbMayorPActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jrbMayorPActionPerformed
        jrbSumaP.setSelected(false);
        jrbPromedioP.setSelected(false);
        jrbMayorP.setSelected(true);
        
    }//GEN-LAST:event_jrbMayorPActionPerformed

    private void jrbSumaDActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jrbSumaDActionPerformed
        jrbSumaD.setSelected(true);
        jrbPromedioD.setSelected(false);
        jrbMayorD.setSelected(false);
    }//GEN-LAST:event_jrbSumaDActionPerformed

    private void jrbPromedioDActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jrbPromedioDActionPerformed
        jrbSumaD.setSelected(false);
        jrbPromedioD.setSelected(true);
        jrbMayorD.setSelected(false); 
    }//GEN-LAST:event_jrbPromedioDActionPerformed

    private void jrbMayorDActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jrbMayorDActionPerformed
        jrbSumaD.setSelected(false);
        jrbPromedioD.setSelected(false);
        jrbMayorD.setSelected(true);
    }//GEN-LAST:event_jrbMayorDActionPerformed

    // Este main se deja sin efecto dado que el inicio del programa se maneja desde la clase pert/Main.java
    /**
     * @param args the command line arguments
     */
    /*public static void main(String args[]) {
        java.awt.EventQueue.invokeLater(new Runnable() {

            public void run() {
                new FormResultado().setVisible(true);
            }
        });
    }*/
    
    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnCalcularDuracion;
    private javax.swing.JButton btnCalcularProbabilidad;
    private javax.swing.JButton btnSalir;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JPanel jPanel3;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JScrollPane jScrollPane3;
    private javax.swing.JRadioButton jrbMayorD;
    private javax.swing.JRadioButton jrbMayorP;
    private javax.swing.JRadioButton jrbPromedioD;
    private javax.swing.JRadioButton jrbPromedioP;
    private javax.swing.JRadioButton jrbSumaD;
    private javax.swing.JRadioButton jrbSumaP;
    private javax.swing.JTabbedPane jtpDuraciones;
    private javax.swing.JLabel lblDuracionDelProyecto;
    private javax.swing.JLabel lblInfoUnidadDeTiempo;
    private javax.swing.JLabel lblProbabilidades;
    private javax.swing.JPanel panelEstrategiaD;
    private javax.swing.JPanel panelEstrategiaP;
    private javax.swing.JTable tblCaminosCriticos;
    private javax.swing.JTable tblResultadoDeCalculos;
    private javax.swing.JTextField txtDuracionDelProyecto;
    private javax.swing.JTextField txtDuracionSegunProbabilidad;
    private javax.swing.JTextField txtProbabilidadParaDuracion;
    private javax.swing.JTextField txtProbabilidadSegunDuracion;
    private javax.swing.JTextField txtTiempoParaProbabilidad;
    // End of variables declaration//GEN-END:variables
}
