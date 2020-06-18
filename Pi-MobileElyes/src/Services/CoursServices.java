package Services;

import com.codename1.io.CharArrayReader;
import com.codename1.io.ConnectionRequest;
import com.codename1.io.JSONParser;
import com.codename1.io.NetworkEvent;
import com.codename1.io.NetworkManager;
import Entities.Cours;
import Entities.Jeu;

import Entities.Matiere;
import Entities.Progress;
import Entities.User;
import Entities.Wish;
import Utils.Statics;
import com.codename1.ui.events.ActionListener;

import java.io.IOException;
import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

public class CoursServices {

    public ArrayList<Cours> Courss;
    public ArrayList<Progress> Progress;
    public ArrayList<Wish> Wishs;
    public static CoursServices instance = null;
    public boolean resultOK;
    private ConnectionRequest req;

    public CoursServices() {
        req = new ConnectionRequest();
    }

    public static CoursServices getInstance() {
        if (instance == null) {
            instance = new CoursServices();
        }
        return instance;
    }

    public ArrayList<Cours> AfficheCours() {

        ArrayList<Cours> Courss = new ArrayList<>();
        ConnectionRequest con = new ConnectionRequest();
        con.setUrl(Statics.BASE_URL + "cours/AfficherCoursM");
        con.addResponseListener((NetworkEvent evt) -> {

            JSONParser jsonp = new JSONParser();
            try {
                Map<String, Object> MapCours = jsonp.parseJSON(new CharArrayReader(new String(con.getResponseData()).toCharArray()));

                List<Map<String, Object>> list = (List<Map<String, Object>>) MapCours.get("root");

                for (Map<String, Object> obj : list) {

                    Cours t = new Cours();
                    float id = Float.parseFloat(obj.get("id").toString());
                    t.setId((int) id);
                    t.setContract(obj.get("contract").toString());
                    t.setLibelle(obj.get("libelle").toString());
                    t.setNiveau(obj.get("niveau").toString());

                    LinkedHashMap<String, Object> obgtype = (LinkedHashMap<String, Object>) obj.get("matiere");
                    Matiere u = new Matiere();
                    u.setId((int) Float.parseFloat(obgtype.get("id").toString()));
                    u.setLibelle(obgtype.get("libelle").toString());

                    t.setM((Matiere) u);
                    Courss.add(t);
                }

            } catch (IOException ex) {
            }
        });
        NetworkManager.getInstance().addToQueueAndWait(con);

        return Courss;
    }

    public boolean AjouterCours(Cours c, User u) {

        String url = Statics.BASE_URL + "cours/AddM/" + c.getId() + "/" + u.getId();
        req.setUrl(url);

        req.addResponseListener(new ActionListener<NetworkEvent>() {
            @Override
            public void actionPerformed(NetworkEvent evt) {

                if (!new String(req.getResponseData()).equals("1")) {
                    resultOK = req.getResponseCode() == 200; //Code HTTP 200 OK
                    req.removeResponseListener(this);
                }

            }

        });
        NetworkManager.getInstance().addToQueueAndWait(req);
        return resultOK;
    }

    public ArrayList<Wish> AfficheWish() {

        ArrayList<Wish> Wishss = new ArrayList<>();
        ConnectionRequest con = new ConnectionRequest();
        con.setUrl(Statics.BASE_URL + "cours/AfficherMesCours");
        con.addResponseListener((NetworkEvent evt) -> {

            JSONParser jsonp = new JSONParser();
            try {
                Map<String, Object> MapWish = jsonp.parseJSON(new CharArrayReader(new String(con.getResponseData()).toCharArray()));

                List<Map<String, Object>> list = (List<Map<String, Object>>) MapWish.get("root");

                for (Map<String, Object> obj : list) {

                    Wish t = new Wish();
                    float id = Float.parseFloat(obj.get("id").toString());
                    t.setId((int) id);

                    LinkedHashMap<String, Object> obgtype = (LinkedHashMap<String, Object>) obj.get("cours");
                    Cours u = new Cours();
                    u.setId((int) Float.parseFloat(obgtype.get("id").toString()));
                    u.setLibelle(obgtype.get("libelle").toString());
                    LinkedHashMap<String, Object> obgtypeUser = (LinkedHashMap<String, Object>) obj.get("user");
                    User user = new User();
                    user.setId((int) Float.parseFloat(obgtype.get("id").toString()));

                    t.setId_c((Cours) u);

                    Wishss.add(t);
                }

            } catch (IOException ex) {
            }
        });
        NetworkManager.getInstance().addToQueueAndWait(con);

        return Wishss;
    }

    public boolean SupprimerFavoris(Wish c) {
        ConnectionRequest con = new ConnectionRequest();
        String url = Statics.BASE_URL + "cours/deleteM/" + c.getId_c().getId() + "/" + UserServices.userC.getId();
        req.setUrl(url);

        req.addResponseListener(new ActionListener<NetworkEvent>() {
            @Override
            public void actionPerformed(NetworkEvent evt) {
                resultOK = req.getResponseCode() == 200; //Code HTTP 200 OK
                req.removeResponseListener(this);
            }

        });

        NetworkManager.getInstance().addToQueueAndWait(req);
        return resultOK;
    }

    public void InitializeNiveau() {
        ConnectionRequest con = new ConnectionRequest();
        String url = Statics.BASE_URL + "cours/Niveau/" + 1 + "/" + UserServices.userC.getId();
        req.setUrl(url);

        req.addResponseListener(new ActionListener<NetworkEvent>() {
            @Override
            public void actionPerformed(NetworkEvent evt) {
                resultOK = req.getResponseCode() == 200; //Code HTTP 200 OK
                req.removeResponseListener(this);
            }

        });

        NetworkManager.getInstance().addToQueueAndWait(req);
    }
     public void NiveauSuivant(int x) {
        ConnectionRequest con = new ConnectionRequest();
        String url = Statics.BASE_URL + "cours/FindNivUser/" + x + "/" + UserServices.userC.getId();
        req.setUrl(url);

        req.addResponseListener(new ActionListener<NetworkEvent>() {
            @Override
            public void actionPerformed(NetworkEvent evt) {
                resultOK = req.getResponseCode() == 200; //Code HTTP 200 OK
                req.removeResponseListener(this);
            }

        });

        NetworkManager.getInstance().addToQueueAndWait(req);
    }

    public ArrayList<Progress> AfficheNiveauUser() {

        ArrayList<Progress> Progresss = new ArrayList<>();
        ConnectionRequest con = new ConnectionRequest();
        con.setUrl(Statics.BASE_URL + "cours/FindNiveau/" + UserServices.userC.getId());
        con.addResponseListener((NetworkEvent evt) -> {

            JSONParser jsonp = new JSONParser();
            try {
                Map<String, Object> MapProgress = jsonp.parseJSON(new CharArrayReader(new String(con.getResponseData()).toCharArray()));

                List<Map<String, Object>> list = (List<Map<String, Object>>) MapProgress.get("root");

                for (Map<String, Object> obj : list) {

                    Progress t = new Progress();
                    float id = Float.parseFloat(obj.get("id").toString());
                    t.setId((int) id);

                    LinkedHashMap<String, Object> obgtype = (LinkedHashMap<String, Object>) obj.get("Jeu");
                    Jeu j = new Jeu();
                    j.setId((int) Float.parseFloat(obgtype.get("id").toString()));

                    t.setJeu((Jeu) j);
                     LinkedHashMap<String, Object> obgtypeUser = (LinkedHashMap<String, Object>) obj.get("user");
                    User user = new User();
                    user.setId((int) Float.parseFloat(obgtype.get("id").toString()));

                    t.setUser((User) user);
                    Progresss.add(t);
                }

            } catch (IOException ex) {
            }
        });
        NetworkManager.getInstance().addToQueueAndWait(con);

        return Progresss;
    }
}
