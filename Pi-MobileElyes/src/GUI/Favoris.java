/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package GUI;

import Services.CoursServices;
import com.codename1.io.FileSystemStorage;
import com.codename1.io.Util;
import com.codename1.ui.Button;
import com.codename1.ui.Container;
import com.codename1.ui.Dialog;
import com.codename1.ui.Display;
import com.codename1.ui.FontImage;
import com.codename1.ui.List;
import com.codename1.ui.Toolbar;
import com.codename1.ui.layouts.BorderLayout;
import com.codename1.ui.layouts.BoxLayout;
import com.codename1.ui.util.Resources;

/**
 *
 * @author Sammy Guergachi <sguergachi at gmail.com>
 */
public class Favoris extends SideMenuBaseForm {

    
    CoursServices cs = new CoursServices();

    public Favoris(Resources res) {

        super(BoxLayout.y());
        super.setTitle("super");
        Toolbar tb = getToolbar();
        setTitle("Favoris");
        Button menuButton = new Button();
        setupSideMenu(res);
        menuButton.setUIID("Title");
        FontImage.setMaterialIcon(menuButton, FontImage.MATERIAL_MENU);
        menuButton.addActionListener(e -> getToolbar().openSideMenu());
        Container titleComponent = BorderLayout.north(BorderLayout.west(menuButton));
        titleComponent.setUIID("BottomPaddingContainer");
        tb.setTitleComponent(titleComponent);
        for (int i = 0; i < cs.AfficheWish().size(); i++) {
                    Container c = new Container(BoxLayout.y());
            Button devGuide = new Button("Telecharger !");
            devGuide.setUIID("LoginButton");
             FontImage.setMaterialIcon(devGuide, FontImage.MATERIAL_FILE_DOWNLOAD);
            String pdf = cs.AfficheCours().get(i).getContract();
            devGuide.addActionListener(e -> {
                FileSystemStorage fs = FileSystemStorage.getInstance();
                String fileName = fs.getAppHomePath() + pdf;
                if (!fs.exists(fileName)) {
                    Util.downloadUrlToFile("http://localhost/Pi-final/web/app_dev.php/cours/download/" + pdf, fileName, true);
                }
                Display.getInstance().execute(fileName);
            });

            List list = new List();
            c.add(list);
            c.add(devGuide);
           list.addItem(cs.AfficheWish().get(i).getId_c().getLibelle());
  add(c); 
        list.addActionListener(l -> {
            if (l.isLongEvent()) {

                if (Dialog.show("Supression", "Confirmer votre demande de suppression de" + cs.AfficheWish().get(list.getSelectedIndex()), "Confirmer", "Annuler")) {
                    CoursServices.getInstance().SupprimerFavoris(cs.AfficheWish().get(list.getSelectedIndex()));
                   
                    new Favoris(res).show();
                }
            }
        });
     
    }
        
                }

}
