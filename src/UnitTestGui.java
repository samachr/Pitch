import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

/**
 * Created by sam on 11/12/14.
 */
public class UnitTestGui extends JFrame{
    private MapPanel anim;
    public UnitTestGui() {
        Container pane = this.getContentPane();

        anim = new MapPanel(20);
        anim.setSize(201, 201);
        anim.setLocation(10,10);
        //anim.setSize(300, 200);
        //anim.setLocation(50, 50);
        pane.add(anim);

        MapOverviewPanel anim2 = new MapOverviewPanel(anim.gameMap);
        anim2.setSize(110, 110);
        anim2.setLocation(221, 5);
        pane.add(anim2);
        anim.setOverviewPanel(anim2);

        this.setLayout(null);
        //pane.add(sprite);
        //pane.addMouseMotionListener(this);
        this.setSize(600, 480);
        this.setVisible(true);
        this.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        anim.requestFocus();
    }
}
