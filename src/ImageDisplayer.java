import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

public class ImageDisplayer {
    JFrame frame;
    public ImageDisplayer(){
        SwingUtilities.invokeLater(new Runnable(){
            public void run(){
                frame = new JFrame("Image Displayer");
                frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
                BufferedImage img = null;

                try{
                    img = ImageIO.read(getClass().getResource("/image.jpg"));
                }catch (IOException e){
                    e.printStackTrace();
                    System.exit(-1);
                }
                ImageIcon imgIcon  = new ImageIcon(img);
                JLabel lbl = new JLabel();
                lbl.setIcon(imgIcon);
                frame.getContentPane().add(lbl, BorderLayout.CENTER);
                frame.pack();
                frame.setLocationRelativeTo(null);
                frame.setVisible(true);
            }
        });
    }

    /**
     * Is this code bad? Yes. Am I happy with it? Almost. ./
     * @param image
     */
    public void updateImage(BufferedImage image){
        ImageIcon imgIcon  = new ImageIcon(image);
        JLabel lbl = new JLabel();
        lbl.setIcon(imgIcon);
        frame.getContentPane().removeAll();
        frame.getContentPane().add(lbl, BorderLayout.CENTER);
        frame.revalidate();
    }
    public static void main(String[] args)throws InterruptedException{
        ImageDisplayer i = new ImageDisplayer() ;
        Thread.sleep(1000);
        BufferedImage img = null;
        try{
            File f = new File("src/image2.jpg");
            img = ImageIO.read(f);
        }catch (IOException e){
            e.printStackTrace();
            System.exit(-1);
        }
        System.out.println("e");
        i.updateImage(img);
    }
}
