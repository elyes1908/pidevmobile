
package Services;
import com.codename1.io.CharArrayReader;
import com.codename1.io.ConnectionRequest;
import com.codename1.io.JSONParser;
import com.codename1.io.NetworkEvent;
import com.codename1.io.NetworkManager;
import Entities.Cours;
import Entities.Matiere;
import Utils.Statics;

import java.io.IOException;
import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

public class CoursServices {
  public ArrayList<Cours> Courss;
    public static CoursServices instance=null;
    public boolean resultOK;
    private   ConnectionRequest req;
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
      con.setUrl(Statics.BASE_URL+"cours/AfficherCoursM");
      con.addResponseListener((NetworkEvent evt) -> {
       
          JSONParser jsonp = new JSONParser();
          try {
              Map<String, Object> MapCours = jsonp.parseJSON(new CharArrayReader(new String(con.getResponseData()).toCharArray()));
              
             
              List<Map<String, Object>> list = (List<Map<String, Object>>) MapCours.get("root");
              
              for ( Map<String, Object> obj : list) {
                  
                  Cours t = new Cours();
                  float id = Float.parseFloat(obj.get("id").toString());
                  t.setId((int)id);
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

}