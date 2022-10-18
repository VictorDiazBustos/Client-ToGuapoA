/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package toguapoaclient;

/**
 *
 * @author vdiazbus
 */
public class WriteException extends Exception{
    public WriteException(){
        super("Exception writting message.");
    }
    
    public WriteException(String message){
        super(message);
    }
}
