/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package Entidades;

import junit.framework.Test;
import junit.framework.TestSuite;

/**
 *
 * @author Manuel Lorenze
 */
public class CalculosProbabilisticosTest extends TestSuite{

    public static Test suite() {
        TestSuite suite = new TestSuite();
        suite.addTest(CalculoDeDuracionesTest.suite());
        suite.addTest(CalculoDeProbabilidadesTest.suite());

        return suite;
    }

    public static void main(String args[]) {
        junit.textui.TestRunner.run(suite());
    }
}
