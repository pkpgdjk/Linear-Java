import java.util.*;
import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.geom.*;
import java.awt.Shape;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;

public class DrawImageClass {
    private int WIDTH = 300;
    private int HEIGHT = 300;

    private Graphics2D g2d;
    private BufferedImage bufferedImage;

    DrawImageClass(int w,int h)  {
        this.WIDTH = w;
        this.HEIGHT = h;
        this.bufferedImage = new BufferedImage(WIDTH, HEIGHT, BufferedImage.TYPE_INT_RGB);
        this.g2d = bufferedImage.createGraphics();

        this.g2d.setColor(Color.white);                                         // ตั้งค่าสี
	    this.g2d.fillRect(0, 0, WIDTH, HEIGHT);                                 // วาดสี่เหลี่ยมขนาด WIDTH,HEIGHT ( สร้างพื้นหลัง )
    }

    public void drawLine(int sourceX,int sourceY,int destX,int destY,boolean showNuber){
        this.g2d.setColor(Color.black);                                         // ตั้งค่าสี                 
        this.g2d.drawLine(this.getX(sourceX), this.getY(sourceY), this.getX(destX), this.getY(destY) ); // วาดเส้นตรงจาก 0,0 -> 50,0

        if(showNuber){
            this.g2d.setColor(Color.red);
            this.g2d.drawString("1", this.getX(sourceX), this.getY(sourceY));

            this.g2d.setColor(Color.red);
            this.g2d.drawString("2", this.getX(destX), this.getY(destY));

        }
    }

    public void drawTriangle(int p1X,int p1Y,int p2X,int p2Y,int p3X,int p3Y,boolean showNuber){
        this.g2d.setColor(Color.black);                                         // ตั้งค่าสี                 
        this.g2d.drawLine(this.getX(p1X), this.getY(p1Y), this.getX(p2X), this.getY(p2Y) ); 
        this.g2d.drawLine(this.getX(p2X), this.getY(p2Y), this.getX(p3X), this.getY(p3Y) ); 
        this.g2d.drawLine(this.getX(p3X), this.getY(p3Y), this.getX(p1X), this.getY(p1Y) ); 

        if(showNuber){
            this.g2d.setColor(Color.red);
            this.g2d.drawString("1", this.getX(p1X), this.getY(p1Y));

            this.g2d.setColor(Color.red);
            this.g2d.drawString("2", this.getX(p2X), this.getY(p2Y));

            this.g2d.setColor(Color.red);
            this.g2d.drawString("3", this.getX(p3X), this.getY(p3Y));

        }
    }

    public void saveToFile(String fileName) throws IOException {
        File file = new File(fileName);
	    ImageIO.write(this.bufferedImage, "jpg", file);
    }

    public void dispose(){
        this.g2d.dispose();
    }

    /*
        เนื่องจาก ปกติ รูปพวกมันนับเป็น pixel ซึ่งก็คือ จุด 0,0 จะอยู่มุมซ้ายบน
        แต่ในระบบการฟ 0,0 จะเป็นจุดศูนย์กลาง 
        ฟังชั่นข้างล่างเลยเป็นการรับค่าแบบ ระบบกราฟมาแล้วแปลงเป็นระบบปกติ
        สมมุต อยากได้จุด X=0 มันก็คือ เอาความยาวรูปมาหาจุดกึ่งกลาง Y=0 ก็เหมือนกัน
        แล้วทีนี้ถ้าอยากได้จุด X=10 ก็คือหาจาก 0 แล้ว + 10 
        ก็จะได้ฟังชั่นข้างล่าง
    */

    private int getX(int px) {
        return (int) ( this.WIDTH / 2 ) + px;
    }

    private int getY(int px) {
        return (int) ( this.HEIGHT / 2 ) - px;
    }
}