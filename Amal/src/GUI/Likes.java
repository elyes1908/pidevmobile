/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package GUI;

import Entities.Plat;
import Services.ServicePlat;
import com.codename1.components.ImageViewer;
import com.codename1.messaging.Message;
import com.codename1.ui.Button;
import com.codename1.ui.Container;
import com.codename1.ui.Dialog;
import com.codename1.ui.Display;
import com.codename1.ui.Font;
import com.codename1.ui.FontImage;
import com.codename1.ui.Image;
import com.codename1.ui.Label;
import com.codename1.ui.Slider;
import com.codename1.ui.Toolbar;
import com.codename1.ui.geom.Dimension;
import com.codename1.ui.layouts.BorderLayout;
import com.codename1.ui.layouts.BoxLayout;
import com.codename1.ui.plaf.Style;
import com.codename1.ui.util.Resources;

/**
 *
 * @author admin
 */
public class Likes extends SideMenuBaseForm {
     ServicePlat sp = new ServicePlat();
       public Likes(Resources res) {

        Toolbar tb = getToolbar();
           

        Button menuButton = new Button();
        setupSideMenu(res);
        menuButton.setUIID("Title");
        FontImage.setMaterialIcon(menuButton, FontImage.MATERIAL_MENU);

        menuButton.addActionListener(e -> getToolbar().openSideMenu());
        Container titleComponent = BorderLayout.north(BorderLayout.west(menuButton));

        tb.setTitleComponent(titleComponent);
        Container labelContainer = new Container(BoxLayout.yCenter());

        Image amal = res.getImage("cantine.png");
       

        //  labelContainer.add(cantine);
     

    

   
       
       

       

     
        Image etoileImage1 = res.getImage("rsz_star.png");
      
        ImageViewer cantine = new ImageViewer(amal);
      
   

        Container x = new Container(BoxLayout.y());
        Container y = new Container(BoxLayout.y());
        
         
           

        x.add(cantine);
   
        
      
           
        //  x.add(labelRate);
        for (int i = 0; i < ServicePlat.getInstance().getAllPlats().size(); i++) {
            Button like = new Button();
            like.setName("" + i);
            Button dislike = new Button();

            FontImage.setMaterialIcon(like, FontImage.MATERIAL_THUMB_UP_ALT);

            FontImage.setMaterialIcon(dislike, FontImage.MATERIAL_THUMB_DOWN_ALT);

            Label L = new Label(ServicePlat.getInstance().getAllPlats().get(i).getLibelle());
            Label l1 = new Label(String.valueOf(ServicePlat.getInstance().getAllPlats().get(i).getLikes()) + " personnes aiment ça ");
            Container e = new Container(BoxLayout.x());

            e.add(L);

            e.add(l1);
            e.add(like);
            e.add(dislike);
            Label separation = new Label("__________________________________");
            System.getProperty("line.separator");
            separation.setTextPosition(l1.BOTTOM);
            //e.add(separation);
            //  e.add(y);

            x.add(e);
            // e.add(y);
            String s = ServicePlat.getInstance().getAllPlats().get(i).getLibelle();
            Plat p = ServicePlat.getInstance().getAllPlats().get(i);

            like.addActionListener(k -> {

                if (sp.addlike(p)) {

                    new ListPlatsForm(res).show();
                    //Dialog.show("alterte", "vous avez déja voté", "ok", null);

                } else {
                    Dialog.show("alterte", "vous avez déja aimé", "ok", null);

                }

            });

            dislike.addActionListener(bl -> {
                if (sp.addDislike(p)) {

                    new ListPlatsForm(res).show();
                    Message m = new Message("Je désapprouve ce plat pour la raison : ");

                    Display.getInstance().sendMessage(new String[]{"amal.essid@esprit.tn"}, "Désapprouvement plat", m);

                } else {
                    Dialog.show("alterte", "vous avez déja désapprouvé", "okkk", null);
                }
            });
        }
        //  
        add(x);
    }

}
    

