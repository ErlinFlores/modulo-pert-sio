/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package Entidades;

import org.junit.Ignore;
import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;

/**
 *
 * @author Manuel Lorenze
 */
public class CalculoDeProbabilidadesTest {
    
    public CalculoDeProbabilidadesTest() {
    }

    @BeforeClass
    public static void setUpClass() throws Exception {
    }

    @AfterClass
    public static void tearDownClass() throws Exception {
    }
    
    @Before
    public void setUp() {
    }
    
    @After
    public void tearDown() {
    }

    /**
     * Test of calcularProbabilidad method, of class GestorProbabilistico.
     */
    @Test
    public void testCalcularProbabilidad_1() {
        System.out.println("Calcular probabilidad en el problema 1");
        double duracionExtendida = 124;
        GestorProbabilistico instance = new GestorProbabilistico(133, 10.05);
        Double expResult = 0.184;
        Double result = instance.calcularProbabilidad(duracionExtendida);
        assertEquals(expResult, result);
    }
    
    /**
     * Test of calcularProbabilidad method, of class GestorProbabilistico.
     */
    @Test
    public void testCalcularProbabilidad_2() {
        System.out.println("Calcular probabilidad en el problema 2");
        double duracionExtendida = 156;
        GestorProbabilistico instance = new GestorProbabilistico(138, 9.0);
        Double expResult = 0.977;
        Double result = instance.calcularProbabilidad(duracionExtendida);
        assertEquals(expResult, result);
    }
    
    /**
     * Test of calcularProbabilidad method, of class GestorProbabilistico.
     */
    @Test
    public void testCalcularProbabilidad_3() {
        System.out.println("Calcular probabilidad en el problema 3");
        double duracionExtendida = 18;
        GestorProbabilistico instance = new GestorProbabilistico(17, 3.0);
        Double expResult = 0.629;
        Double result = instance.calcularProbabilidad(duracionExtendida);
        assertEquals(expResult, result);
    }
    
    /**
     * Test of calcularProbabilidad method, of class GestorProbabilistico.
     */
    @Test
    public void testCalcularProbabilidad_4() {
        System.out.println("Calcular probabilidad en el problema 4");
        double duracionExtendida = 31;
        GestorProbabilistico instance = new GestorProbabilistico(40, 6.708);
        Double expResult = 0.09;
        Double result = instance.calcularProbabilidad(duracionExtendida);
        assertEquals(expResult, result);
    }

    /**
     * Test of calcularProbabilidad method, of class GestorProbabilistico.
     */
    @Test
    public void testCalcularProbabilidad_5() {
        System.out.println("Calcular probabilidad en el problema 5");
        double duracionExtendida = 27;
        GestorProbabilistico instance = new GestorProbabilistico(39, 5.963);
        Double expResult = 0.022;
        Double result = instance.calcularProbabilidad(duracionExtendida);
        assertEquals(expResult, result);
    }
    
    /**
     * Test of calcularProbabilidad method, of class GestorProbabilistico.
     */
    @Test
    public void testCalcularProbabilidad_6() {
        System.out.println("Calcular probabilidad en el problema 6");
        double duracionExtendida = 78;
        GestorProbabilistico instance = new GestorProbabilistico(83, 13.051);
        Double expResult = 0.352;
        Double result = instance.calcularProbabilidad(duracionExtendida);
        assertEquals(expResult, result);
    }
    
    /**
     * Test of calcularProbabilidad method, of class GestorProbabilistico.
     */
    @Test
    public void testCalcularProbabilidad_7() {
        System.out.println("Calcular probabilidad en el problema 7");
        double duracionExtendida = 33;
        GestorProbabilistico instance = new GestorProbabilistico(41, 7.703);
        Double expResult = 0.149;
        Double result = instance.calcularProbabilidad(duracionExtendida);
        assertEquals(expResult, result);
    }
    
    /**
     * Test of calcularProbabilidad method, of class GestorProbabilistico.
     */
    @Test
    public void testCalcularProbabilidad_8() {
        System.out.println("Calcular probabilidad en el problema 8");
        double duracionExtendida = 32;
        GestorProbabilistico instance = new GestorProbabilistico(47, 10.677);
        Double expResult = 0.081;
        Double result = instance.calcularProbabilidad(duracionExtendida);
        assertEquals(expResult, result);
    }
    
    /**
     * Test of calcularProbabilidad method, of class GestorProbabilistico.
     */
    @Test
    public void testCalcularProbabilidad_9() {
        System.out.println("Calcular probabilidad en el problema 9");
        double duracionExtendida = 33;
        GestorProbabilistico instance = new GestorProbabilistico(37, 6.839);
        Double expResult = 0.281;
        Double result = instance.calcularProbabilidad(duracionExtendida);
        assertEquals(expResult, result);
    }
    
    /**
     * Test of calcularProbabilidad method, of class GestorProbabilistico.
     */
    @Test
    public void testCalcularProbabilidad_10() {
        System.out.println("Calcular probabilidad en el problema 10");
        double duracionExtendida = 30;
        GestorProbabilistico instance = new GestorProbabilistico(40, 7.394);
        Double expResult = 0.089;
        Double result = instance.calcularProbabilidad(duracionExtendida);
        assertEquals(expResult, result);
    }
    
    /**
     * Test of calcularProbabilidad method, of class GestorProbabilistico.
     */
    @Test
    public void testCalcularProbabilidad_11() {
        System.out.println("Calcular probabilidad en el problema 11");
        double duracionExtendida = 33;
        GestorProbabilistico instance = new GestorProbabilistico(45, 9.055);
        Double expResult = 0.092;
        Double result = instance.calcularProbabilidad(duracionExtendida);
        assertEquals(expResult, result);
    }
    
    /**
     * Test of calcularProbabilidad method, of class GestorProbabilistico.
     */
    @Test
    public void testCalcularProbabilidad_12() {
        System.out.println("Calcular probabilidad en el problema 12");
        double duracionExtendida = 26;
        GestorProbabilistico instance = new GestorProbabilistico(39, 6.856);
        Double expResult = 0.029;
        Double result = instance.calcularProbabilidad(duracionExtendida);
        assertEquals(expResult, result);
    }
        
    /**
     * Test of calcularProbabilidad method, of class GestorProbabilistico.
     */
    @Test
    public void testCalcularProbabilidad_13() {
        System.out.println("Calcular probabilidad en el problema 13");
        double duracionExtendida = 28;
        GestorProbabilistico instance = new GestorProbabilistico(38, 6.377);
        Double expResult = 0.058;
        Double result = instance.calcularProbabilidad(duracionExtendida);
        assertEquals(expResult, result);
    }
    
    /**
     * Test of calcularProbabilidad method, of class GestorProbabilistico.
     */
    @Test
    public void testCalcularProbabilidad_14() {
        System.out.println("Calcular probabilidad en el problema 14");
        double duracionExtendida = 39;
        GestorProbabilistico instance = new GestorProbabilistico(50, 8.557);
        Double expResult = 0.099;
        Double result = instance.calcularProbabilidad(duracionExtendida);
        assertEquals(expResult, result);
    }
    
    /**
     * Test of calcularProbabilidad method, of class GestorProbabilistico.
     */
    @Test
    public void testCalcularProbabilidad_15() {
        System.out.println("Calcular probabilidad en el problema 13");
        double duracionExtendida = 42;
        GestorProbabilistico instance = new GestorProbabilistico(40, 5.344);
        Double expResult = 0.644;
        Double result = instance.calcularProbabilidad(duracionExtendida);
        assertEquals(expResult, result);
    }
    
    /**
     * Test of calcularProbabilidad method, of class GestorProbabilistico.
     */
    @Test
    public void testCalcularProbabilidad_16() {
        System.out.println("Calcular probabilidad en el problema 16");
        double duracionExtendida = 34;
        GestorProbabilistico instance = new GestorProbabilistico(42, 5.754);
        Double expResult = 0.082;
        Double result = instance.calcularProbabilidad(duracionExtendida);
        assertEquals(expResult, result);
    }
    
    /**
     * Test of calcularProbabilidad method, of class GestorProbabilistico.
     */
    @Test
    public void testCalcularProbabilidad_17() {
        System.out.println("Calcular probabilidad en el problema 17");
        double duracionExtendida = 10;
        GestorProbabilistico instance = new GestorProbabilistico(12.5, 4.167);
        Double expResult = 0.274;
        Double result = instance.calcularProbabilidad(duracionExtendida);
        assertEquals(expResult, result);
    }
}
