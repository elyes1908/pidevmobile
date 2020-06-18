/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package Services;

import Entities.User;
import Utils.Statics;
import com.codename1.io.CharArrayReader;
import com.codename1.io.ConnectionRequest;
import com.codename1.io.JSONParser;
import com.codename1.io.NetworkEvent;
import com.codename1.io.NetworkManager;
import com.codename1.ui.events.ActionListener;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 * 
 * @author Sammy Guergachi <sguergachi at gmail.com>
 */
public class UserServices {
  public static UserServices instance = null;
    public boolean resultOK;
    private ConnectionRequest req;
ArrayList<User> Users = new ArrayList<>();
    public UserServices() {
        req = new ConnectionRequest();
    }
    public static User userC ;
    
    public static UserServices getInstance() {
        if (instance == null) {
            instance = new UserServices();
        }
        return instance;
    }
 
        public ArrayList<User> getAll() {
            ArrayList<User> Users = new ArrayList<>();
        ConnectionRequest con = new ConnectionRequest();
          String url = Statics.BASE_URL + "findpass"  ;
          con.setUrl(url);
             con.addResponseListener((NetworkEvent evt) -> {

            JSONParser jsonp = new JSONParser();
            try {
                Map<String, Object> MapUser= jsonp.parseJSON(new CharArrayReader(new String(con.getResponseData()).toCharArray()));

                List<Map<String, Object>> list = (List<Map<String, Object>>) MapUser.get("root");

                for (Map<String, Object> obj : list) {

                    User u = new User();
                       u.setId((int) Float.parseFloat(obj.get("id").toString()));
                            u.setUsername(obj.get("username").toString());
                            u.setEmail(obj.get("email").toString());
                            String psw = obj.get("password").toString();
                            u.setPassword(psw);
                            u.setPrenom(obj.get("prenom").toString());
                         
                            u.setRoles(obj.get("roles").toString());
                            
                    Users.add(u);
                    
                }
                 

            } catch (IOException ex) {
            }
        });
        NetworkManager.getInstance().addToQueueAndWait(con);

        return Users;
       
         
        

    }
        
        public User getUser(String username, String pass) {
        for (int i = 0; i < getAll().size(); i++) {
            if ((username.equals(getAll().get(i).getUsername())) && ((Password.checkPassword(pass, getAll().get(i).getPassword())))) {
                userC=getAll().get(i);
                return getAll().get(i);
            }
        }
        return null;
        }
       
        
}
