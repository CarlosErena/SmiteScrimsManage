
import Modelo.Sesion;
import com.google.gson.Gson;
import java.io.BufferedReader;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.Scanner;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.text.Document;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import org.xml.sax.SAXException;


/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author Usuario1
 */
public class pruebas {
    public static void main (String[] args) throws NoSuchAlgorithmException, MalformedURLException, IOException{
        GregorianCalendar gc = new GregorianCalendar();
        gc.add (GregorianCalendar.HOUR, -2);
        String timeStamp = new SimpleDateFormat("yyyyMMddHHmmss").format(gc.getTime());
        //timestamp
        System.out.println(timeStamp);
        String method;
        System.out.println("Cual metodo quieres usar");
        Scanner sc = new Scanner(System.in);
        method = sc.next(); //leemos el metodo que queremos usar
        String signature = signature(timeStamp,method); //obtenemos el array de bytes MD5
        Sesion sesion =null;
        sesion = obtenerSesion(signature, timeStamp);

        
        Gson gson = new Gson();
    }

    private static String signature(String timeStamp, String method) throws NoSuchAlgorithmException {
            MessageDigest md = MessageDigest.getInstance("MD5");
            String signature = "2844"+method+"F78D5CC5FEA64890A98C81B6988EDB61"+timeStamp;
            md.update(signature.getBytes());
            byte[]digest = md.digest();
            StringBuilder sb = new StringBuilder();
            for (byte b : digest){
                sb.append(String.format("%02x", b & 0xff));
            }
            System.out.println(sb);
            return sb.toString();
    }
    
    static public Sesion obtenerSesion(String signature, String timestamp) throws SAXException {
        Sesion sesion=null;
        try {
            HttpURLConnection request = (HttpURLConnection) new URL("http://api.smitegame.com/smiteapi.svc/createsessionXML/2844/"+signature+"//"+timestamp).openConnection();
            request.connect();
            
            
        } catch (MalformedURLException ex) {
            Logger.getLogger(Sesion.class.getName()).log(Level.SEVERE, null, ex);
        } catch (IOException ex) {
            Logger.getLogger(Sesion.class.getName()).log(Level.SEVERE, null, ex);
        } catch (ParserConfigurationException ex) {
            Logger.getLogger(pruebas.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        return sesion;
    }
}   

