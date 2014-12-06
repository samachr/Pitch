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
 * Created by sam on 11/26/14
 */
public class GameStarter extends JFrame implements ActionListener, ItemListener{
    private JComboBox<String> cbxKey;
    private JButton btnStart, btnExit;
    private JLabel lblKeyPic;
    private JSpinner spnSize;

    public GameStarter() {
        this.setTitle("Pitch Game Starter");
        this.setSize(300, 200);
        this.setLayout(new BoxLayout(this.getContentPane(), BoxLayout.Y_AXIS));

        JPanel keyChooser = new JPanel();
        keyChooser.setLayout(new FlowLayout());
        keyChooser.setSize(400,60);

        cbxKey = new JComboBox<String>(Music.getCommonKeys());
        cbxKey.addItemListener(this);

        lblKeyPic = new JLabel();

        keyChooser.add(new JLabel("Key:"));
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

        sizeChooser.add(new JLabel("Grid Size:"));
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
        this.setVisible(true);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource().equals(btnStart)) {
            new GameGui((Integer)spnSize.getValue(), cbxKey.getSelectedItem().toString());
            this.dispose();
        } else if (e.getSource().equals(btnExit)) {
            System.exit(0);
        }
    }

    @Override
    public void itemStateChanged(ItemEvent e) {
        BufferedImage image;
        try {
            image = ImageIO.read(new File("./data/images/staff-" + cbxKey.getSelectedItem() + ".png"));
            lblKeyPic.setIcon(new ImageIcon(image));
        } catch(Exception ex) {
            System.out.println("the file didn't load...");
        }
    }
}
