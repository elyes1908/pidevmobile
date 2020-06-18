/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package GUI;

import Services.CoursServices;
import Services.UserServices;
import com.codename1.io.FileSystemStorage;
import com.codename1.io.Util;
import com.codename1.ui.Button;
import com.codename1.ui.Container;
import com.codename1.ui.Dialog;
import com.codename1.ui.Display;
import com.codename1.ui.FontImage;
import com.codename1.ui.Form;
import com.codename1.ui.List;
import com.codename1.ui.Toolbar;
import com.codename1.ui.layouts.BorderLayout;
import com.codename1.ui.layouts.BoxLayout;
import com.codename1.ui.util.Resources;

/**
 *
 * @author Sammy Guergachi <sguergachi at gmail.com>
 */
public class CoursGUI extends SideMenuBaseForm {

    CoursServices cs = new CoursServices();

    public CoursGUI(Resources res) {

        super(BoxLayout.y());

        Toolbar tb = getToolbar();

        Button menuButton = new Button();
        setupSideMenu(res);
        menuButton.setUIID("Title");
        FontImage.setMaterialIcon(menuButton, FontImage.MATERIAL_MENU);
       
        menuButton.addActionListener(e -> getToolbar().openSideMenu());
        Container titleComponent = BorderLayout.north(BorderLayout.west(menuButton));

        tb.setTitleComponent(titleComponent);
    List list = new List();
        for (int i = 0; i < cs.AfficheCours().size(); i++) {
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

        
            c.add(list);
            c.add(devGuide);
            list.addItem(cs.AfficheCours().get(i).getM() + " " + cs.AfficheCours().get(i).getLibelle() + " ");

            list.addActionListener(l -> {
                if (l.isLongEvent()) {
                    if (Dialog.show("Ajouter a mes cours", "Voulez vous vraiment ajouter " + cs.AfficheCours().get(list.getSelectedIndex()).getLibelle() + " à votre liste de cours préferée ?", "Oui", "Non")) {
                        if (cs.AjouterCours(cs.AfficheCours().get(list.getSelectedIndex()), UserServices.userC)) {
                            new CoursGUI(res).show();

                            if (Dialog.show(cs.AfficheCours().get(list.getSelectedIndex()).getLibelle(), "Ce cour a été bien placé dans votre liste voulez vous l'afficher ?", "Oui", "Non")) {

                                new Favoris(res).show();

                            }
                        } else {
                            if (Dialog.show(cs.AfficheCours().get(list.getSelectedIndex()).getLibelle(), "Ce cour est déja placé dans votre liste voulez vous l'afficher ?", "Oui", "Non")) {
                                new Favoris(res).show();
                            }
                        }
                    }
                }
            });

            add(c);
        }

    }

}
