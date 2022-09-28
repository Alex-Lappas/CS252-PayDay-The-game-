package mvc.view;
import java.awt.Graphics;
import java.awt.Image;
import javax.swing.JLayeredPane;

/**
 *
 * @author AlexL
 */
public class JLayeredPaneExtension extends JLayeredPane {

      Image image;

        public JLayeredPaneExtension(Image img) {
            image=img;
        }

        @Override
        public void paintComponent(Graphics g) {
            super.paintComponent(g);
            g.drawImage(image, 0, 0, this);
        }
    }