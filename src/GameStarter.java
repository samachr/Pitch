import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

/**
 * Created by sam on 11/26/14.
 */
public class GameStarter extends JFrame implements ActionListener, ItemListener{
    private JComboBox<String> cbxKey;
    private JButton btnStart, btnExit;
    private JLabel lblKeyPic;
    private JSpinner spnSize;

    public GameStarter() throws HeadlessException {
        this.setTitle("Pitch");
        this.setSize(300, 200);
        this.setLayout(new BoxLayout(this.getContentPane(), BoxLayout.Y_AXIS));

        JPanel keyChooser = new JPanel();
        keyChooser.setLayout(new FlowLayout());
        keyChooser.setSize(400,60);

        cbxKey = new JComboBox<String>(Music.getCommonKeys());
//        cbxKey.setSize(150, 30);
//        cbxKey.setLocation(60, 10);
        cbxKey.addItemListener(this);
        //this.getContentPane().add(cbxKey);

        JLabel temp = new JLabel("Key:");
//        temp.setSize(30, 20);
//        temp.setLocation(20, 13);
        //this.getContentPane().add(temp);

        lblKeyPic = new JLabel();
//        lblKeyPic.setSize(110, 52);
//        lblKeyPic.setLocation(250, 10);
        //this.getContentPane().add(lblKeyPic);

        keyChooser.add(temp);
        keyChooser.add(cbxKey);
        keyChooser.add(lblKeyPic);

        this.add(keyChooser);

        BufferedImage image;
        try {
            image = ImageIO.read(new File("./data/images/staff-" + cbxKey.getSelectedItem() + ".png"));
            lblKeyPic.setIcon(new ImageIcon(image));
        } catch (IOException e) {
            e.printStackTrace();
            lblKeyPic.setText(cbxKey.getSelectedItem().toString());
        }


        JPanel sizeChooser = new JPanel();
        sizeChooser.setLayout(new FlowLayout());
        sizeChooser.setSize(400,60);

        spnSize = new JSpinner(new SpinnerNumberModel(5, 2, 50, 5));
        spnSize.setLocation(150, 120);
        //spnSize.setSize(150, 30);
//        this.getContentPane().add(spnSize);

        JLabel temp2 = new JLabel("Grid Size:");
        temp2.setSize(100,20);
        temp2.setLocation(10,120);
//        this.getContentPane().add(temp2);

        sizeChooser.add(temp2);
        sizeChooser.add(spnSize);
        this.add(sizeChooser);

        JPanel pnlStartExit = new JPanel();
        pnlStartExit.setLayout(new FlowLayout());
        pnlStartExit.setSize(400,60);

        btnStart = new JButton("Start");
        btnStart.addActionListener(this);

        btnExit = new JButton("Exit");
        btnExit.addActionListener(this);

        pnlStartExit.add(btnStart);
        pnlStartExit.add(btnExit);

        this.add(pnlStartExit);

        this.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        this.setLocationRelativeTo(null);
//        this.setLayout(null);
        this.setVisible(true);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource().equals(btnStart)) {
            new GameGui((Integer)spnSize.getValue(), cbxKey.getSelectedItem().toString());
            this.setVisible(false);
        } else if (e.getSource().equals(btnExit)) {
            System.exit(0);
        }
    }

    @Override
    public void itemStateChanged(ItemEvent e) {
//        System.out.println(this.getSize());
        BufferedImage image;
        try {
            image = ImageIO.read(new File("./data/images/staff-" + cbxKey.getSelectedItem() + ".png"));
            lblKeyPic.setIcon(new ImageIcon(image));
        } catch(Exception ex) {
            System.out.println("the file didn't load...");
        }
    }
}
