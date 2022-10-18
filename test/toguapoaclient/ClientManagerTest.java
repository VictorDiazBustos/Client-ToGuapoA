/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/UnitTests/JUnit4TestClass.java to edit this template
 */
package toguapoaclient;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.PrintStream;
import java.net.Socket;
import org.junit.Test;
import static org.junit.Assert.*;
import org.mockito.Mockito;
import org.mockito.Mock;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

/**
 *
 * @author vdiazbus
 */
public class ClientManagerTest {

    public ClientManagerTest() {
    }

    /**
     * Test of write method, of class ClientManager.
     */
    @Test
    public void testWrite() throws IOException {
        // Given
        // Mock creation
        Socket sc = Mockito.mock(Socket.class);
        PrintStream toServer = Mockito.mock(PrintStream.class);
        BufferedReader fromServer = Mockito.mock(BufferedReader.class);
        ClientManager manager = new ClientManager(sc, toServer, fromServer);

        // Then
        manager.write("Test message");

        // Expect
        Mockito.verify(toServer, Mockito.times(1)).println("Test message");
    }

    /**
     * Test of read method, of class ClientManager.
     */
    @Test
    public void testRead() throws IOException {
        // Given
        //Mock creation
        Socket sc = Mockito.mock(Socket.class);
        PrintStream toServer = Mockito.mock(PrintStream.class);
        BufferedReader fromServer = Mockito.mock(BufferedReader.class);
        ClientManager manager = new ClientManager(sc, toServer, fromServer);

        // Then
        when(manager.read()).thenReturn("Test message");
        String ReturnMessage = manager.read();


        Mockito.verify(fromServer, Mockito.times(1)).readLine();

        assertEquals(ReturnMessage, "Test message");
    }

}
