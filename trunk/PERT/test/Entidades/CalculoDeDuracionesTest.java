/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package Entidades;

import junit.framework.TestSuite;
import junit.framework.Test;
import junit.framework.TestCase;
import static org.junit.Assert.*;

/**
 *
 * @author Manuel Lorenze
 */
public class CalculoDeDuracionesTest extends TestCase {
    
    public CalculoDeDuracionesTest() {
    }

    public void testCalcularDuracion_1() {
        System.out.println("Calcular duración en el problema 1");
        double probabilidadExtendida = 0.184;
        GestorProbabilistico instance = new GestorProbabilistico(133, 10.050);
        Double expResult = 123.955;
        Double result = instance.calcularDuracion(probabilidadExtendida);
        assertEquals(expResult, result);
    }
    
    public void testCalcularDuracion_2() {
        System.out.println("Calcular duración en el problema 2");
        double probabilidadExtendida = 0.977;
        GestorProbabilistico instance = new GestorProbabilistico(138, 9.000);
        Double expResult = 156.000;
        Double result = instance.calcularDuracion(probabilidadExtendida);
        assertEquals(expResult, result);
    }
    
    public void testCalcularDuracion_3() {
        System.out.println("Calcular duración en el problema 3");
        double probabilidadExtendida = 0.629;
        GestorProbabilistico instance = new GestorProbabilistico(17, 3.000);
        Double expResult = 17.990;
        Double result = instance.calcularDuracion(probabilidadExtendida);
        assertEquals(expResult, result);
    }
    
    public void testCalcularDuracion_4() {
        System.out.println("Calcular duración en el problema 4");
        double probabilidadExtendida = 0.090;
        GestorProbabilistico instance = new GestorProbabilistico(40, 6.708);
        Double expResult = 31.011;
        Double result = instance.calcularDuracion(probabilidadExtendida);
        assertEquals(expResult, result);
    }
    
    public void testCalcularDuracion_5() {
        System.out.println("Calcular duración en el problema 5");
        double probabilidadExtendida = 0.022;
        GestorProbabilistico instance = new GestorProbabilistico(39, 5.963);
        Double expResult = 27.014;
        Double result = instance.calcularDuracion(probabilidadExtendida);
        assertEquals(expResult, result);
    }
    
    public void testCalcularDuracion_6() {
        System.out.println("Calcular duración en el problema 6");
        double probabilidadExtendida = 0.352;
        GestorProbabilistico instance = new GestorProbabilistico(83, 13.051);
        Double expResult = 78.041;
        Double result = instance.calcularDuracion(probabilidadExtendida);
        assertEquals(expResult, result);
    }
    
    public void testCalcularDuracion_7() {
        System.out.println("Calcular duración en el problema 7");
        double probabilidadExtendida = 0.149;
        GestorProbabilistico instance = new GestorProbabilistico(41, 7.703);
        Double expResult = 32.989;
        Double result = instance.calcularDuracion(probabilidadExtendida);
        assertEquals(expResult, result);
    }
    
    public void testCalcularDuracion_8() {
        System.out.println("Calcular duración en el problema 8");
        double probabilidadExtendida = 0.081;
        GestorProbabilistico instance = new GestorProbabilistico(47, 10.677);
        Double expResult = 32.052;
        Double result = instance.calcularDuracion(probabilidadExtendida);
        assertEquals(expResult, result);
    }
    
    public void testCalcularDuracion_9() {
        System.out.println("Calcular duración en el problema 9");
        double probabilidadExtendida = 0.281;
        GestorProbabilistico instance = new GestorProbabilistico(37, 6.839);
        Double expResult = 33.033;
        Double result = instance.calcularDuracion(probabilidadExtendida);
        assertEquals(expResult, result);
    }
    
    public void testCalcularDuracion_10() {
        System.out.println("Calcular duración en el problema 10");
        double probabilidadExtendida = 0.089;
        GestorProbabilistico instance = new GestorProbabilistico(40, 7.394);
        Double expResult = 30.018;
        Double result = instance.calcularDuracion(probabilidadExtendida);
        assertEquals(expResult, result);
    }
    
    public void testCalcularDuracion_11() {
        System.out.println("Calcular duración en el problema 11");
        double probabilidadExtendida = 0.092;
        GestorProbabilistico instance = new GestorProbabilistico(45, 9.055);
        Double expResult = 32.957; 
        Double result = instance.calcularDuracion(probabilidadExtendida);
        assertEquals(expResult, result);
    }
    
    public void testCalcularDuracion_12() {
        System.out.println("Calcular duración en el problema 12");
        double probabilidadExtendida = 0.029;
        GestorProbabilistico instance = new GestorProbabilistico(39, 6.856);
        Double expResult = 25.974;
        Double result = instance.calcularDuracion(probabilidadExtendida);
        assertEquals(expResult, result);
    }

    public void testCalcularDuracion_13() {
        System.out.println("Calcular duración en el problema 13");
        double probabilidadExtendida = 0.058;
        GestorProbabilistico instance = new GestorProbabilistico(38, 6.377);
        Double expResult = 27.988;
        Double result = instance.calcularDuracion(probabilidadExtendida);
        assertEquals(expResult, result);
    }

    public void testCalcularDuracion_14() {
        System.out.println("Calcular duración en el problema 14");
        double probabilidadExtendida = 0.099;
        GestorProbabilistico instance = new GestorProbabilistico(50, 8.557);
        Double expResult = 38.961;
        Double result = instance.calcularDuracion(probabilidadExtendida);
        assertEquals(expResult, result);
    }

    public void testCalcularDuracion_15() {
        System.out.println("Calcular duración en el problema 15");
        double probabilidadExtendida = 0.644;
        GestorProbabilistico instance = new GestorProbabilistico(40, 5.344);
        Double expResult = 41.977;
        Double result = instance.calcularDuracion(probabilidadExtendida);
        assertEquals(expResult, result);
    }

    public void testCalcularDuracion_16() {
        System.out.println("Calcular duración en el problema 16");
        double probabilidadExtendida = 0.082;
        GestorProbabilistico instance = new GestorProbabilistico(42, 5.754);
        Double expResult = 34.002;
        Double result = instance.calcularDuracion(probabilidadExtendida);
        assertEquals(expResult, result);
    }
    
    public void testCalcularDuracion_17() {
        System.out.println("Calcular duración en el problema 17");
        double probabilidadExtendida = 0.274;
        GestorProbabilistico instance = new GestorProbabilistico(12.5, 4.167);
        Double expResult = 10.000;
        Double result = instance.calcularDuracion(probabilidadExtendida);
        assertEquals(expResult, result);
    }
    
    public static Test suite() {
        return new TestSuite(CalculoDeDuracionesTest.class);
    }
}