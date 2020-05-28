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
import com.codename1.ui.Form;
import com.codename1.ui.Image;
import com.codename1.ui.Label;
import com.codename1.ui.Slider;
import com.codename1.ui.Toolbar;
import com.codename1.ui.geom.Dimension;
import com.codename1.ui.layouts.BorderLayout;
import com.codename1.ui.layouts.BoxLayout;
import com.codename1.ui.plaf.Border;
import com.codename1.ui.plaf.Style;

import com.codename1.ui.util.Resources;

/**
 *
 * @author admin
 */
public class ListPlatsForm extends SideMenuBaseForm {

    ServicePlat sp = new ServicePlat();

    private void initStarRankStyle(Style s, Image star) {
        s.setBackgroundType(Style.BACKGROUND_IMAGE_TILE_BOTH);
        s.setBorder(Border.createEmpty());
        s.setBgImage(star);
        s.setBgTransparency(0);

    }

    public ListPlatsForm(Resources res) {

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
        Image etoileImage1 = res.getImage("rsz_star.png");
      
        ImageViewer cantine = new ImageViewer(amal);
    
        ImageViewer etoile1 = new ImageViewer(etoileImage1);
        ImageViewer etoile2 = new ImageViewer(etoileImage1);
        ImageViewer etoile5 = new ImageViewer(etoileImage1);
        ImageViewer etoile3 = new ImageViewer(etoileImage1);
        ImageViewer etoile4 = new ImageViewer(etoileImage1);

        //  labelContainer.add(cantine);
        Label labelRate = new Label();
        Label votreVote = new Label();

        Slider starRank = new Slider();

        starRank.setProgress(Integer.valueOf(sp.parseEtoile()));

        Button confirmerVote = new Button("Evaluer ! ");
        confirmerVote.setUIID("LoginButton");
        starRank.setEditable(true);
        starRank.setMinValue(0);
        starRank.setMaxValue(5);
        Label starValue = new Label();
        starRank.addActionListener((evt) -> {

            // starValue.setText(String.valueOf(starRank.getProgress()));
            confirmerVote.addActionListener((evtt) -> {

                starRank.setProgress(starRank.getProgress());
                Dialog.show("Merci pour votre evaluation ", "Vous avez attribué  " + String.valueOf(starRank.getProgress()) + " "
                        + " etoiles . Vous pouvez changer votre vote à tout moment ! ", "ok", null);
                votreVote.setText(String.valueOf(starRank.getProgress()));

                // System.out.println(sp.parseEtoile());
                sp.addRate(starRank.getProgress());
                new ListPlatsForm(res).show();

            });

        });

        Font fnt = Font.createSystemFont(Font.FACE_PROPORTIONAL, Font.STYLE_PLAIN, Font.SIZE_SMALL).
                derive(Display.getInstance().convertToPixels(9, true), Font.STYLE_PLAIN);
        Style st = new Style(0xffff33, 0, fnt, (byte) 0);
        Image fullStar = FontImage.createMaterial(FontImage.MATERIAL_STAR, st).toImage();
        st.setOpacity(100);
        st.setFgColor(0);
        Image emptyStar = FontImage.createMaterial(FontImage.MATERIAL_STAR, st).toImage();
        initStarRankStyle(starRank.getSliderEmptySelectedStyle(), emptyStar);
        initStarRankStyle(starRank.getSliderEmptyUnselectedStyle(), emptyStar);
        initStarRankStyle(starRank.getSliderFullSelectedStyle(), fullStar);
        initStarRankStyle(starRank.getSliderFullUnselectedStyle(), fullStar);
        starRank.setPreferredSize(new Dimension(fullStar.getWidth() * 5, fullStar.getHeight()));

        Container x = new Container(BoxLayout.y());
        Container y = new Container(BoxLayout.y());
          Container xx = new Container(BoxLayout.x());
          Container xxx = new Container(BoxLayout.x());
         
          
        Label rate = new Label("Evaluez la qualité du service cantine ");

        x.add(cantine);
        x.add(rate);
        x.add(starRank);
        //     x.add(starValue);
        x.add(confirmerVote);
        
        x.add(votreVote);
            tb.addCommandToRightBar("", etoileImage1 , (evt) -> {
                   new Likes(res).show();
                
            });
        //  labelRate.setText("Moyenne des votes"+sp.parseMoyenneEtoile());
x.add(xxx);
        // Label votreVote = new Label ("Vous avez voté "+starRank.getProgress());
        Label platsListe = new Label("moyenne des votes:" );
        
        xxx.add(platsListe);
        xxx.add(xx);
           
          
            if(Float.valueOf(sp.parseMoyenneEtoile())==1)
                    {
                         xx.add(etoile1);
                    }
              if(Float.valueOf(sp.parseMoyenneEtoile())==2)
                    {
                         xx.add(etoile1);
                          xx.add(etoile2);
                    }
                if(Float.valueOf(sp.parseMoyenneEtoile())==3)
                    {
                         xx.add(etoile1);
                          xx.add(etoile2);  xx.add(etoile3);
                    }
                  if(Float.valueOf(sp.parseMoyenneEtoile())==4)
                    {
                        xx.add(etoile1);
                          xx.add(etoile2);  xx.add(etoile3); xx.add(etoile4);
                    }
                    if(Float.valueOf(sp.parseMoyenneEtoile())==1)
                    {
                          xx.add(etoile1);
                          xx.add(etoile2);  xx.add(etoile3); xx.add(etoile5);
                    }
                     if(Float.valueOf(sp.parseMoyenneEtoile())==3.5)
                    {
                          xx.add(etoile1);
                          xx.add(etoile2);  xx.add(etoile3); xx.add(etoile5);
                    }
        //  x.add(labelRate);
        
        
        //  
        add(x);
    }

}
