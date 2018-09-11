/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Modelo;

/**
 *
 * @author Usuario1
 */
public class Sesion {
    private final String ret_message;
    private final String session_id;
    private final String timestamp;
    
    public Sesion(String ret_message, String session_id, String timestamp) {
        this.ret_message = ret_message;
        this.session_id = session_id;
        this.timestamp = timestamp;
    }

    public String getRet_message() {
        return ret_message;
    }

    public String getSession_id() {
        return session_id;
    }

    public String getTimestamp() {
        return timestamp;
    }
  
     

}
