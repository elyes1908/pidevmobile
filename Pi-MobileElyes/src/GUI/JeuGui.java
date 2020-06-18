/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package GUI;

import Services.CoursServices;
import com.codename1.media.Media;
import com.codename1.media.MediaManager;

import com.codename1.ui.Button;
import com.codename1.ui.Container;
import com.codename1.ui.Display;
import com.codename1.ui.FontImage;
import com.codename1.ui.Slider;
import com.codename1.ui.Toolbar;

import com.codename1.ui.layouts.BorderLayout;
import com.codename1.ui.layouts.BoxLayout;
import com.codename1.ui.util.Resources;

/**
 *
 * @author Sammy Guergachi <sguergachi at gmail.com>
 */
public class JeuGui extends SideMenuBaseForm {

    CoursServices cs = new CoursServices();
    public static Media music, correctS, incorrectS;

    public JeuGui(Resources res) {

        super(BoxLayout.y());

        Toolbar tb = getToolbar();

        Button menuButton = new Button();
        setupSideMenu(res);
        menuButton.setUIID("Title");
        FontImage.setMaterialIcon(menuButton, FontImage.MATERIAL_MENU);
        menuButton.addActionListener(e -> getToolbar().openSideMenu());
        Container titleComponent = BorderLayout.north(BorderLayout.west(menuButton));

        getToolbar().addMaterialCommandToOverflowMenu("", FontImage.MATERIAL_MUSIC_NOTE, (e) -> {
            JeuGui.music.play();
        });
        getToolbar().addMaterialCommandToOverflowMenu("", FontImage.MATERIAL_MUSIC_OFF, (e) -> {
            JeuGui.music.pause();
        });

        getToolbar().addMaterialCommandToOverflowMenu("", FontImage.MATERIAL_EQUALIZER, (e) -> {

            Slider s = new Slider();
            Button Confirmer = new Button("Confirmer");
            Container cnt = new Container(BoxLayout.xCenter());
            cnt.add(s);
            cnt.add(Confirmer);
            s.setEditable(true);
            add(cnt);
            revalidate();
            s.setProgress(music.getVolume());
            s.addActionListener(volume -> {

                music.setVolume(s.getProgress());
            });

            Confirmer.addActionListener(btnn -> {
                cnt.setVisible(false);
                revalidate();
            });
        });

        try {
            Media m, correct, incorrect;
            m = MediaManager.createMedia((Display.getInstance().getResourceAsStream(getClass(), "/mu.mp3")), "audio/mpeg");
            correct = MediaManager.createMedia((Display.getInstance().getResourceAsStream(getClass(), "/correct.mp3")), "audio/mpeg");
            incorrect = MediaManager.createMedia((Display.getInstance().getResourceAsStream(getClass(), "/incorrect.mp3")), "audio/mpeg");
            music = m;
            correctS = correct;
            incorrectS = incorrect;

        } catch (Exception ex) {

        }

        //cs.InitializeNiveau();
        // 
        if (cs.AfficheNiveauUser().size() == 0) {
            cs.InitializeNiveau();
            new Niveau1(res).show();
        } else {
            int niv = cs.AfficheNiveauUser().get(0).getJeu().getId();
            switch (niv) {
                case 1:
                    new Niveau1(res).show();
                    break;
                case 2:
                    new Niveau2(res).show();
                    break;
                case 3:
                    new Niveau3(res).show();
                    break;
                case 4:
                    new Niveau4(res).show();
                    break;
            }
        }

    }

}
