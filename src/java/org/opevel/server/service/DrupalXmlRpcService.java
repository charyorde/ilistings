/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package org.opevel.server.service;

/**
 *
 * @author Administrator
 */

import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;
import java.util.logging.Logger;
import javax.crypto.spec.SecretKeySpec;
import javax.crypto.Mac;
import java.nio.charset.Charset;
import com.sun.net.ssl.internal.ssl.Debug;
import java.net.URL;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Vector;
import org.apache.xmlrpc.client.XmlRpcClient;
import org.apache.xmlrpc.client.XmlRpcClientConfigImpl;

/**
*
* @author Aaron Moline <Aaron.Moline@molinesoftware.com>
*/
public class DrupalXmlRpcService {

        public String ServiceURL;
        public String ServiceDomain;
        public String ApiKey;
        public String Nonce;
        public long TimeStamp;
        public String APIHash;
        public XmlRpcClient XmlService;
        public boolean APIKey_Active;
        
        final Logger logger = Logger.getLogger(DrupalXmlRpcService.class.getName());

        public DrupalXmlRpcService(String serviceDomain, String apiKey, String serviceURL,boolean api_active)  {
            this(serviceDomain, apiKey, serviceURL);
            this.APIKey_Active = api_active;

        }

        public DrupalXmlRpcService(String serviceDomain, String apiKey, String serviceURL) {
            this.ServiceDomain = serviceDomain;
            this.ApiKey = apiKey;
            this.ServiceURL = serviceURL;
            this.APIKey_Active = true;
        }
   
    protected String SessionID;

    
    private String GetNonce()/*(int length)*/   {
            /*
             * //TODO:Get None Generator Working
            String allowedCharacters = "abcdefghijkmnopqrstuvwxyzABCDEFGHJKLMNPQRSTUVWXYZ23456789";
            StringBuilder password = new StringBuilder();

            Random rand = new Random();
            for (int i = 0; i < length; i++)
            {
                password.append()
                //password.append(append);
            }

            return password.toString();
             * */
            return ""+System.currentTimeMillis();
        }
    /*
    *
    * @drupalServiceCommand
    *
    */
    private void IntializeHash(DrupalServiceCommands drupalServiceCommand) {
        this.Nonce = GetNonce();
        this.TimeStamp = System.currentTimeMillis();
        String hashstring = GetHashString(drupalServiceCommand.toString());
        this.APIHash = GetHMAC(hashstring);
    }

    private void IntializeService() {
        try {

            XmlRpcClientConfigImpl config = new XmlRpcClientConfigImpl();
            config.setServerURL(new URL(this.ServiceURL));
            XmlService = new XmlRpcClient();
            //XmlService.setConfig(XmlService.getClientConfig());
            XmlService.setConfig(config);

        } catch (Exception e) {
             e.printStackTrace();
        }
    }

    private String GetHashString(String serviccmd) {
        StringBuilder sb = new StringBuilder();
        sb.append(""+System.currentTimeMillis()); // Time stamp
        sb.append(";");
        sb.append(this.ServiceDomain);//Service Domain
        sb.append(";");
        sb.append(""+System.currentTimeMillis()); //Nonce
        sb.append(";");
        sb.append(serviccmd); //Service command
        //Debug.println("Created GetHashString: ",sb.toString());
        logger.warning("Created GetHashString: " + sb.toString());
        return sb.toString();
    }

    private List GetDefaultParams() {
        //Vector params = new Vector();
        //Map<Object, String> params = new HashMap<Object, String>();
        List params = new ArrayList();
        //Drupal is setup to use Service Keys, then add the following.
        if(this.APIKey_Active)
        {
            params.add(this.APIHash);
            params.add(this.ServiceDomain);
            params.add(""+this.TimeStamp);
            params.add(this.Nonce);
            params.add(this.SessionID);
        }
        return params;
    }

    public String GetHMAC(String message) {

        Mac mac;
        try {
            Charset csets = Charset.forName("US-ASCII");
            SecretKeySpec keySpec = new javax.crypto.spec.SecretKeySpec(csets.encode(this.ApiKey).array(), "HmacSHA256");
            mac = javax.crypto.Mac.getInstance("HmacSHA256");
            mac.init(keySpec);
            byte[] hash = mac.doFinal(csets.encode(message).array());

            String result = "";
            for (int i = 0; i < hash.length; i++) {
                result += Integer.toString((hash[i] & 0xff) + 0x100, 16).substring(1);
            }

            //Debug.println("Created HMAC: ", result);
            logger.warning("Created HMAC: " + result);
            return result;

        } catch (InvalidKeyException ex) {
            //Logger.getLogger(DrupalXmlRpcService.class.getName()).log(Level.SEVERE, null, ex);
            logger.info("InvalidKeyException" + ex);
            throw new RuntimeException(ex);
        } catch (NoSuchAlgorithmException ex) {
            //Logger.getLogger(DrupalXmlRpcService.class.getName()).log(Level.SEVERE, null, ex);
            logger.info("NoSuchAlgorithmException" + ex);
            throw new RuntimeException(ex);
        }


    }

    public boolean Connect() {
        try {

            //Intialize Hash
            IntializeHash(DrupalServiceCommands.SystemConnect);

            //Intialize Service
            IntializeService();

            //Debug.println("XmlService Service Intialized"," ");
            logger.info("XmlService Service Intialized" +" ");

            HashMap map = (HashMap)XmlService.execute(DrupalServiceCommands.SystemConnect.toString(), new Object[]{});

            this.SessionID = (String)map.get("sessid");

            //Debug.println("Conn SessionID: ", this.SessionID);
            logger.info("Conn SessionID: " + this.SessionID);
            System.out.println("Conn SessionID: "+this.SessionID);

            return true;

        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }

    public boolean Login(String username, String password)   {
        logger.info("calling Login");
        try {
            IntializeHash(DrupalServiceCommands.UserLogin);

            //confirmLogin();

            //Vector params = GetDefaultParams();
            List params = GetDefaultParams();
             //Add Login Paramaters
            params.add(username);
            params.add(password);

            logger.info("service parameter is: " + params);

            HashMap o = (HashMap)XmlService.execute(DrupalServiceCommands.UserLogin.toString(), params);
            if(o == null) {
                logger.info("Couldn't execute xml-rpc service");
            }
            else {
                logger.info(o.toString());
            }
            if(!o.isEmpty()) {
                if(o.containsKey(username) && o.containsKey(password)) {
                //confirmLogin(HashMap loginValue, String username, String password);
                }
            }
            this.SessionID = (String)o.get("sessid");

            logger.info("Getting sessionID: " + (String)o.get("sessid"));

            //Debug.println("Successfull Login:", o.toString());
            logger.info("Successfull Login:" + o.toString());
            return true;
        } catch (Exception e) {
            System.out.println(e.toString());
            logger.info("Login error: " + e.getMessage());
        }
        return false;
    }

    /*public boolean confirmLogin(String username, String password) {
        try {
            IntializeHash(DrupalServiceCommands.UserLogin);

        }catch (Exception e) {

        }
        return true;
    }*/

    public boolean Logout() {

        try {
             IntializeHash(DrupalServiceCommands.UserLogout);
             //Vector params = GetDefaultParams();
             List params = GetDefaultParams();
             params.add(this.SessionID);
             Object o = XmlService.execute(DrupalServiceCommands.UserLogout.toString(), params);

             //Debug.println("Logout Sucessfull:",o.toString());
             logger.info("Logout Sucessfull:" + o.toString());
             return true;
        } catch (Exception e) {
            System.out.println(e.toString());
            return false;
        }
    }

    //Testing New Things Not valid
    public void Test_FileSave() {
        try {

            IntializeHash(DrupalServiceCommands.FileSave);
            byte[] Filebyte = new byte[10];
            //Vector params = GetDefaultParams();
            List params = GetDefaultParams();
            params.add(Filebyte);
            Object o = XmlService.execute(DrupalServiceCommands.FileSave.toString(), params);
            //Debug.println("Test Sucessfull:", o.toString());
            logger.info("Test Sucessfull:" + o.toString());
        } catch (Exception e) {
            System.out.println(e.toString());
        }

    }
    // </editor-fold>
}
