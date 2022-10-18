/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/UnitTests/JUnit4TestClass.java to edit this template
 */
package toguapoaclient;

import org.junit.Test;
import static org.junit.Assert.*;

/**
 *
 * @author vdiazbus
 */
public class ClientManagerTest {
    
    public ClientManagerTest() {
    }

    /**
     * Test of connect method, of class ClientManager.
     */
    @Test
    public void testConnect() {
        ClientManager manager = mock(ClientManager.class);
        boolean expResult = false;
        boolean result = instance.connect();
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of write method, of class ClientManager.
     */
    @Test
    public void testWrite() throws Exception {
        System.out.println("write");
        String message = "";
        ClientManager instance = new ClientManager();
        instance.write(message);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of read method, of class ClientManager.
     */
    @Test
    public void testRead() {
        System.out.println("read");
        ClientManager instance = new ClientManager();
        String expResult = "";
        String result = instance.read();
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }
    
}
