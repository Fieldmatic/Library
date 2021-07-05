package gui.pretragaKnjiga;

import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

public class PozadinskiPanel extends JPanel {

    private final String putanja;
    private BufferedImage image;

    public PozadinskiPanel(String putanjaSlike) {
        this.putanja = putanjaSlike;
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        try {
          image = ImageIO.read(new File(putanja));
       } catch (IOException ex) {
            System.out.println("PozadinskiPanel: Pozadinska slika nije ucitana!");
       }
        g.drawImage(image, 0, 0, this); // see javadoc for more info on the parameters
    }
}
