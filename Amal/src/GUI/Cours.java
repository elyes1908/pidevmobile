/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package GUI;

import Services.CoursServices;
import com.codename1.ui.Button;
import com.codename1.ui.Container;
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
public class Cours extends SideMenuBaseForm {

    List list = new List();
    CoursServices cs = new CoursServices();

    public Cours(Resources res) {

        super(BoxLayout.y());

        Toolbar tb = getToolbar();

        Button menuButton = new Button();
        setupSideMenu(res);
        menuButton.setUIID("Title");
        FontImage.setMaterialIcon(menuButton, FontImage.MATERIAL_MENU);
        menuButton.addActionListener(e -> getToolbar().openSideMenu());
        Container titleComponent = BorderLayout.north(BorderLayout.west(menuButton));
        titleComponent.setUIID("BottomPaddingContainer");
        tb.setTitleComponent(titleComponent);
         
        for (int i = 0; i < cs.AfficheCours().size(); i++) {
            list.addItem(cs.AfficheCours().get(i).getM()+" "+cs.AfficheCours().get(i).getLibelle());
           
        
        }
      
            

        
     
        add(list);

    }

}
