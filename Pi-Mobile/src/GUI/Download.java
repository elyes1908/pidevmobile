/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package GUI;

import com.codename1.ui.Button;
import com.codename1.ui.Container;
import com.codename1.ui.Label;
import com.codename1.ui.layouts.BoxLayout;
import com.codename1.ui.util.Resources;

/**
 *
 * @author Sammy Guergachi <sguergachi at gmail.com>
 */
public class Download extends SideMenuBaseForm {

public Download(Resources res) {
      
        Container x = new Container(BoxLayout.y());

        for (int i = 1; i <= 5; i++) {
                
            Button b = new Button(res.getImage("l.png"));
            
            Button K = new Button(res.getImage("d.png"));
            Label L = new Label("Plat" + i);
            Container e = new Container(BoxLayout.x());
            e.add(L);
            e.add(b);
            e.add(K);
            x.add(e);
            b.addActionListener(l -> {
                
                System.out.println(L.getText());
            });
            K.addActionListener(k->{
            System.out.println(L.getText());
            });
        }
        //  
        add(x);
    }

}
