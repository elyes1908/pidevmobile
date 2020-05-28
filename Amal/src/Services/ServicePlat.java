/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Services;

import com.codename1.io.CharArrayReader;
import com.codename1.io.ConnectionRequest;
import com.codename1.io.JSONParser;
import com.codename1.io.NetworkEvent;
import com.codename1.io.NetworkManager;
import com.codename1.ui.events.ActionListener;
import Entities.Plat;

import Utils.Statics;
import com.codename1.messaging.Message;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 *
 * @author admin
 */
public class ServicePlat {

    public ArrayList<Plat> plats;
    public int etoile;

    public static ServicePlat instance = null;
    public boolean resultOK;
    private ConnectionRequest req;
    private ConnectionRequest req2;

    public ServicePlat() {
        req = new ConnectionRequest();
    }

    public static ServicePlat getInstance() {
        if (instance == null) {
            instance = new ServicePlat();
        }
        return instance;
    }

    /*  public boolean addPlat(Plat t) {
        String url = Statics.BASE_URL + "/tasks/" + t.getName() + "/" + t.getStatus();
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
     */
    public ArrayList<Plat> parsePlats(String jsonText) {
        try {
            plats = new ArrayList<>();
            JSONParser j = new JSONParser();
            Map<String, Object> platsListJson = j.parseJSON(new CharArrayReader(jsonText.toCharArray()));

            List<Map<String, Object>> list = (List<Map<String, Object>>) platsListJson.get("root");
            for (Map<String, Object> obj : list) {
                Plat t = new Plat();
                float id = Float.parseFloat(obj.get("id").toString());
                t.setId((int) id);
                t.setCalories(((int) Float.parseFloat(obj.get("calories").toString())));
                t.setLikes(((int) Float.parseFloat(obj.get("likes").toString())));
                t.setRes(((int) Float.parseFloat(obj.get("res").toString())));
                t.setDislikes(((int) Float.parseFloat(obj.get("dislikes").toString())));
                t.setLibelle(obj.get("libelle").toString());
                t.setDescription(obj.get("description").toString());
                plats.add(t);
            }

        } catch (IOException ex) {

        }
        return plats;
    }

    public String parseEtoile() {
        String url = Statics.BASE_URL + "api/setEtoile?user=" + UserServices.userC.getId();
        req.setUrl(url);
        req.addResponseListener(new ActionListener<NetworkEvent>() {
            @Override
            public void actionPerformed(NetworkEvent evt) {

                resultOK = req.getResponseCode() == 200; //Code HTTP 200 OK
                req.removeResponseListener(this);

            }
        });
        NetworkManager.getInstance().addToQueueAndWait(req);
        return new String(req.getResponseData());
   }

     public String parseMoyenneEtoile() {
        String url = Statics.BASE_URL + "api/rateFinal" ;
        req.setUrl(url);
        req.addResponseListener(new ActionListener<NetworkEvent>() {
            @Override
            public void actionPerformed(NetworkEvent evt) {

                resultOK = req.getResponseCode() == 200; //Code HTTP 200 OK
                req.removeResponseListener(this);
                

            }
        });
        NetworkManager.getInstance().addToQueueAndWait(req);
        return new String(req.getResponseData());
   }

    public ArrayList<Plat> getAllPlats() {
        String url = Statics.BASE_URL + "api/plats/all";
        req.setUrl(url);
        req.setPost(false);
        req.addResponseListener(new ActionListener<NetworkEvent>() {
            @Override
            public void actionPerformed(NetworkEvent evt) {
                plats = parsePlats(new String(req.getResponseData()));
                req.removeResponseListener(this);
            }
        });
        NetworkManager.getInstance().addToQueueAndWait(req);
        return plats;
    }

    public boolean addlike(Plat t) {

        String url = Statics.BASE_URL + "api/aimerMobile?plat=" + t.getId() + "&user=" + UserServices.userC.getId();
        req.setUrl(url);
        req.addResponseListener(new ActionListener<NetworkEvent>() {
            @Override
            public void actionPerformed(NetworkEvent evt) {

                if (!new String(req.getResponseData()).equals("1")) {
                    resultOK = req.getResponseCode() == 200; //Code HTTP 200 OK
                    req.removeResponseListener(this);
                    System.out.println("like persist");

                }
                else 
                {
                    System.out.println("like no persist");
                     resultOK = req.getResponseCode() != 200;
                }

            }
        });
        NetworkManager.getInstance().addToQueueAndWait(req);
        return resultOK;
    }

    public boolean addDislike(Plat t) {
        //   api/aimerMobile?id=2&?plat=2

        String url = Statics.BASE_URL + "api/dislikeMobile?plat=" + t.getId() + "&user=" + UserServices.userC.getId();
        req.setUrl(url);
        req.addResponseListener(new ActionListener<NetworkEvent>() {
            @Override
            public void actionPerformed(NetworkEvent evt) {
                if (!new String(req.getResponseData()).equals("1")) {
                    resultOK = req.getResponseCode() == 200; //Code HTTP 200 OK
                    req.removeResponseListener(this);
                }
                else {
                     resultOK = req.getResponseCode() != 200;
                }
            }
        });
        NetworkManager.getInstance().addToQueueAndWait(req);
        return resultOK;
    }

    public boolean addRate(int etoile) {
        //   api/aimerMobile?id=2&?plat=2

        String url = Statics.BASE_URL + "api/ajouterRateMobile?eleve=" + UserServices.userC.getId() + "&etoile=" + etoile;
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

}
