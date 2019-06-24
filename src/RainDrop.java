import javax.swing.*;
import java.awt.*;

public class RainDrop extends JLabel {

    private int speed;

    public RainDrop(String s, int xPos, int yPos, int speed) {
        super(s);
        this.speed = speed;
        Rectangle rec = new Rectangle();
        rec.x = xPos;
        rec.y = yPos;
        rec.width = 15;
        rec.height = 15;
        setBackground(Color.BLACK);
        setForeground(Color.GREEN);
        setBounds(rec);

        fall();

    }

    private void fall() {
        new Thread(() -> {
            int screenHeight = MatrixRain.matrixRain.screenHeight;
            boolean isFinished = false;
            try{
                while(!isFinished){
                    Rectangle rec = this.getBounds();
                    rec.y += 2;
                    this.setBounds(rec);
                    Thread.sleep(speed);

                    if(rec.y >= screenHeight){
                        isFinished = true;
                    }
                }

                if(getParent() != null){
                    getParent().remove(this);
                }
            }catch(Exception ex){
                System.err.println(ex.getMessage());
            }
        }).start();
    }

}