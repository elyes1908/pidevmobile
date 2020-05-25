/*
 * Copyright (c) 2016, Codename One
 *
 * Permission is hereby granted, free of charge, to any person obtaining a copy of this software and associated 
 * documentation files (the "Software"), to deal in the Software without restriction, including without limitation 
 * the rights to use, copy, modify, merge, publish, distribute, sublicense, and/or sell copies of the Software, 
 * and to permit persons to whom the Software is furnished to do so, subject to the following conditions:
 *
 * The above copyright notice and this permission notice shall be included in all copies or substantial portions 
 * of the Software.
 *
 * THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND, EXPRESS OR IMPLIED, 
 * INCLUDING BUT NOT LIMITED TO THE WARRANTIES OF MERCHANTABILITY, FITNESS FOR A 
 * PARTICULAR PURPOSE AND NONINFRINGEMENT. IN NO EVENT SHALL THE AUTHORS OR COPYRIGHT 
 * HOLDERS BE LIABLE FOR ANY CLAIM, DAMAGES OR OTHER LIABILITY, WHETHER IN AN ACTION OF 
 * CONTRACT, TORT OR OTHERWISE, ARISING FROM, OUT OF OR IN CONNECTION WITH THE SOFTWARE 
 * OR THE USE OR OTHER DEALINGS IN THE SOFTWARE. 
 */
package GUI;

import Services.UserServices;
import com.codename1.ui.Container;
import com.codename1.ui.FontImage;
import com.codename1.ui.Form;
import com.codename1.ui.Image;
import com.codename1.ui.Label;
import com.codename1.ui.layouts.BorderLayout;
import com.codename1.ui.layouts.BoxLayout;
import com.codename1.ui.layouts.Layout;
import com.codename1.ui.util.Resources;

/**
 * Common code that can setup the side menu
 *
 * @author Shai Almog
 */
public abstract class SideMenuBaseForm extends Form {

    public SideMenuBaseForm(String title, Layout contentPaneLayout) {
        super(title, contentPaneLayout);
    }

    public SideMenuBaseForm(String title) {
        super(title);
    }

    public SideMenuBaseForm() {
    }

    public SideMenuBaseForm(Layout contentPaneLayout) {
        super(contentPaneLayout);
    }

    public void setupSideMenu(Resources res) {

       
        Container sidemenuTop = new Container(BoxLayout.y());
        sidemenuTop.setUIID("SidemenuTop");

        getToolbar().addComponentToSideMenu(sidemenuTop);
        if (UserServices.userC.getRoles().equals("[ELEVE]")) {
            getToolbar().addMaterialCommandToSideMenu("  Logout", FontImage.MATERIAL_EXIT_TO_APP, e -> {
                UserServices.userC = null;
                new LoginForm(res).show();
            });
            getToolbar().addMaterialCommandToSideMenu("  Cours", FontImage.MATERIAL_BOOK, e -> new CoursGUI(res).show());
            getToolbar().addMaterialCommandToSideMenu("  Favoris", FontImage.MATERIAL_FAVORITE_BORDER, e -> {
                new Favoris(res).show();
                try{
                    JeuGui.music.pause();
                }catch(NullPointerException x){
                    
                }
                    
                
            });
            getToolbar().addMaterialCommandToSideMenu("  Jeu", FontImage.MATERIAL_GAMES, e -> {
                new JeuGui(res);
                JeuGui.music.play();

            });
        } else {
            getToolbar().addMaterialCommandToSideMenu("  Dashboard", FontImage.MATERIAL_DASHBOARD, e -> new CoursGUI(res).show());

            getToolbar().addMaterialCommandToSideMenu("  Reclamation", FontImage.MATERIAL_WARNING, e -> {
                if (JeuGui.music.isPlaying()) {
                    JeuGui.music.pause();
                }
            });
            getToolbar().addMaterialCommandToSideMenu("  Ajouter Reclamation", FontImage.MATERIAL_NOTE_ADD, e -> {
                if (JeuGui.music.isPlaying()) {
                    JeuGui.music.pause();
                }
            });
            getToolbar().addMaterialCommandToSideMenu("  Account Settings", FontImage.MATERIAL_SETTINGS, e -> {
                if (JeuGui.music.isPlaying()) {
                    JeuGui.music.pause();
                }
            });
            getToolbar().addMaterialCommandToSideMenu("  Logout", FontImage.MATERIAL_EXIT_TO_APP, e -> {
                if (JeuGui.music.isPlaying()) {
                    JeuGui.music.pause();
                }
                new LoginForm(res).show();
            });
        }
    }
    //protected abstract void showOtherForm(Resources res);

}
