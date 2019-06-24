import javax.swing.*;
import java.awt.*;
import java.util.Random;

public class MatrixRain extends JFrame implements KeyListener {


    public static MatrixRain matrixRain;
    public int screenWidth;
    public int screenHeight;

    private JPanel panel;

    public  Random random = new Random();
    private String[] characters = {"a", "£", "&", "8", "H", "2", "@", "?","B","%","m","P","z","Y","7"}; // not sure which characters to use??


    public MatrixRain() {

        matrixRain = this;
        this.init();

    }

    public void init(){
        panel = new JPanel(null);
        panel.setBackground(Color.BLACK);

        //Add panel to Frame
        add(panel);
        setVisible(true);
        addKeyListener(this);
        setExtendedState(JFrame.MAXIMIZED_BOTH); // set to full screen
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

    }

    public void makeItRain(){
       screenWidth = getSize().width;
       screenHeight = getSize().height;

       new Thread(()-> {
           while (true){
               try{
                   int index = random.nextInt(characters.length);
                   int xPos = random.nextInt(screenWidth);
                   int yPos = 0;
                   int speed = random.nextInt(10) + 5;

                   RainDrop rd1 = new RainDrop(characters[index],xPos,yPos,speed);
                   index = random.nextInt(characters.length);
                   yPos+=20;

                   RainDrop rd2 = new RainDrop(characters[index],xPos,yPos,speed);
                   index = random.nextInt(characters.length);
                   yPos+=20;

                   RainDrop rd3 = new RainDrop(characters[index],xPos,yPos,speed);

                   panel.add(rd1);
                   panel.add(rd2);
                   panel.add(rd3);
                   panel.invalidate();

                   Thread.sleep(10);

               }catch (Exception ex){
                   System.err.println(ex);
               }
           }
       }).start();
    }

    public static void main(String[] args){
        MatrixRain mr = new MatrixRain();
        mr.makeItRain();
    }
}
