/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package GUI;

import static GUI.JeuGui.correctS;
import static GUI.JeuGui.incorrectS;
import static GUI.JeuGui.music;
import Services.CoursServices;
import com.codename1.components.ImageViewer;
import com.codename1.l10n.SimpleDateFormat;
import com.codename1.ui.Button;
import static com.codename1.ui.Component.CENTER;
import static com.codename1.ui.Component.TOP;
import com.codename1.ui.Container;
import com.codename1.ui.FontImage;
import com.codename1.ui.Form;
import com.codename1.ui.Label;
import com.codename1.ui.Slider;
import com.codename1.ui.TextField;
import com.codename1.ui.Toolbar;
import com.codename1.ui.layouts.BorderLayout;
import com.codename1.ui.layouts.BoxLayout;
import com.codename1.ui.layouts.FlowLayout;
import com.codename1.ui.plaf.UIManager;
import com.codename1.ui.util.Resources;
import java.util.Date;

/**
 *
 * @author Sammy Guergachi <sguergachi at gmail.com>
 */
public class Niveau1 extends SideMenuBaseForm {

    CoursServices cs = new CoursServices();

    public Niveau1(Resources res) {
        super(new FlowLayout(TOP, CENTER));
        Resources theme = UIManager.initFirstTheme("/theme");
        Toolbar tb = getToolbar();

        Button menuButton = new Button();
        setupSideMenu(res);
        menuButton.setUIID("Title");
        FontImage.setMaterialIcon(menuButton, FontImage.MATERIAL_MENU);
        menuButton.addActionListener(e -> getToolbar().openSideMenu());
        Container titleComponent = BorderLayout.north(BorderLayout.west(menuButton));
        titleComponent.setUIID("BottomPaddingContainer");
        tb.setTitleComponent(titleComponent);

        tb.addMaterialCommandToOverflowMenu("", FontImage.MATERIAL_MUSIC_NOTE, (e) -> {
            JeuGui.music.play();
        });
        tb.addMaterialCommandToOverflowMenu("", FontImage.MATERIAL_MUSIC_OFF, (e) -> {
            JeuGui.music.pause();
        });

        tb.addMaterialCommandToOverflowMenu("", FontImage.MATERIAL_EQUALIZER, (e) -> {

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

         
        Container All = new Container(BoxLayout.y());
        Container c = new Container(new FlowLayout(CENTER, CENTER));
        ImageViewer time = new ImageViewer(theme.getImage("time.jpg"));
        TextField reponse = new TextField();
        
        reponse.setUIID("TextField1");
        Label etatrep = new Label("Quelle heure est-il ?");
        Button valider = new Button("Valider");

        valider.addActionListener(l -> {
            long yourmilliseconds = System.currentTimeMillis();
            SimpleDateFormat sdf = new SimpleDateFormat("HH:mm");
            Date resultdate = new Date(yourmilliseconds);
            if (reponse.getText().equals(sdf.format(resultdate))) {
                correctS.play();
                cs.NiveauSuivant(2);
                new Niveau2(res).show();
            } else {
                incorrectS.play();
                etatrep.setText("Non c'est faux, réessaie ");
            }

        });
       
        c.add(etatrep);
        c.add(time);
        c.add(reponse);
        c.add(valider);
        All.add(c);
        add(All);

    }
}
