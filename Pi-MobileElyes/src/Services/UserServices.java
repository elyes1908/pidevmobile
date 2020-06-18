/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Services;

import Entities.Personne;
import Entities.User;
import Utils.Statics;
import com.codename1.db.Cursor;
import com.codename1.db.Row;
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
    Statics cn = new Statics();
    private ConnectionRequest req;
    ArrayList<User> Users = new ArrayList<>();

    public UserServices() {
        req = new ConnectionRequest();
    }
    public static User userC;

    public static UserServices getInstance() {
        if (instance == null) {
            instance = new UserServices();
        }
        return instance;
    }

    public ArrayList<User> getAll() {
        ArrayList<User> Users = new ArrayList<>();
        ConnectionRequest con = new ConnectionRequest();
        String url = Statics.BASE_URL + "findpass";
        con.setUrl(url);
        con.addResponseListener((NetworkEvent evt) -> {

            JSONParser jsonp = new JSONParser();
            try {
                Map<String, Object> MapUser = jsonp.parseJSON(new CharArrayReader(new String(con.getResponseData()).toCharArray()));

                List<Map<String, Object>> list = (List<Map<String, Object>>) MapUser.get("root");

                for (Map<String, Object> obj : list) {

                    User u = new User();
                    u.setId((int) Float.parseFloat(obj.get("id").toString()));
                    u.setUsername(obj.get("username").toString());
                    u.setEmail(obj.get("email").toString());
                    String psw = obj.get("password").toString();
                    u.setPassword(psw);
                    u.setPrenom(obj.get("prenom").toString());
                    u.setNom(obj.get("nom").toString());

                    u.setRoles(obj.get("roles").toString());

                    Users.add(u);

                }

            } catch (IOException ex) {
            }
        });
        NetworkManager.getInstance().addToQueueAndWait(con);

        return Users;

    }

    public boolean getUser(String username, String pass) {
        for (int i = 0; i < getAll().size(); i++) {
            if ((username.equals(getAll().get(i).getUsername())) && ((Password.checkPassword(pass, getAll().get(i).getPassword())))) {
                userC = getAll().get(i);
                return true;
            }
        }
        return false;
    }

    public ArrayList<Personne> getAllPersonne() {
        ArrayList<Personne> Personnes = new ArrayList<>();
        try {

            Cursor c = cn.getdb().executeQuery("select * from Personne where UserID=" + userC.getId());
            while (c.next()) {
                Row r = c.getRow();
                int id = r.getInteger(0);
                int iduser = r.getInteger(1);
                Personne personne = new Personne(iduser);
                Personnes.add(personne);
            }

            c.close();
        } catch (Exception ex) {
            ex.getMessage();
        }
        return Personnes;
    }

    public void createPersonne() {

        try {
            if (getAllPersonne().size() == 0) {
                cn.getdb().execute("insert into Personne (UserID) values (?);", new String[]{userC.getId() + ""});
                cn.getdb().close();
            }
        } catch (IOException x) {

            x.getMessage();
        }

    }

}
