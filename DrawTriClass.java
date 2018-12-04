import java.util.*;
import java.io.IOException;
import java.awt.*;
import javax.swing.*;

public class DrawTriClass extends JFrame {
    public Integer REFEC_Y = 1;
    public Integer REFEC_X = 2;
    public Integer REFEC_Y_EQUAL_MINUX_X = 3;
    public Integer REFEC_Y_EQUAL_A = 4;
    public Integer REFEC_X_EQUAL_A = 5;

    private Color COLOR;

    private int WIDTH = 800;
    private int HEIGHT = 800;
    
    private Integer[] point1 = new Integer[2];
    private Integer[] point2 = new Integer[2];
    private Integer[] point3 = new Integer[2];

    private boolean drawText = true;
    private Integer countText = 1;

    DrawTriClass(int width,int height){
        //เก็บค่า width height ไว้ในตัวแปร global WIDTH,HEIGTH
        this.WIDTH = width;
        this.HEIGHT = height;

        //ตั้งค่าต่างๆของ JFrame
        setTitle("Rotation Triangle");
        setSize(this.WIDTH,this.HEIGHT);
        setVisible(true);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
    }

    public void setDefaultTriangle(Integer[] point1,Integer[] point2,Integer[] point3){
        this.point1 = point1;
        this.point2 = point2;
        this.point3 = point3;

        this.setColor(this.COLOR);
        this.drawTriangle(
            point1[0],
            point1[1],

            point2[0],
            point2[1],

            point3[0],
            point3[1]
        );
        if(this.drawText){
           this.drawBageColor("Default");
        }
    }
    /*
        อันนี้เป็น Override Method ของ JFrame อันนี้มันจะถูกเรียกตอน new class นี้ขึ้นมาใหม่
        ซึ่งในนี้เขียนไว้ให้ไปเรียก Method drawDash อีกทีหนึ่ง (เป็นการวาดเส้นประ)
    */
    @Override
    public void paint(Graphics g){
        this.drawDash(g);
    }

    public void moveTriangle(Integer forceX,Integer forceY){
        Graphics g = getGraphics();

        this.setColor(Color.green);
        this.drawTriangle(
            this.point1[0] + forceX,
            this.point1[1] + forceY,

            this.point2[0] + forceX,
            this.point2[1] + forceY,

            this.point3[0] + forceX,
            this.point3[1] + forceY
        );                    

        if(this.drawText){
            this.drawBageColor("Move x=>" + forceX + ",y=>" + forceY);
         }

    }

    public void scale(double value){
        double[] d_point1 = Arrays.stream(this.point1).mapToDouble(i->(double)i).toArray();
        double[] d_point2 = Arrays.stream(this.point2).mapToDouble(i->(double)i).toArray();
        double[] d_point3 = Arrays.stream(this.point3).mapToDouble(i->(double)i).toArray();

        double[] old_center = {
            (d_point1[0] + d_point2[0] + d_point3[0]) / 3,
            (d_point1[1] + d_point2[1] + d_point3[1]) / 3
        };
    
        double[][] m_scale = {
            {value,0},
            {0,value}
        };
        
        d_point1 = Matrix.multiply(d_point1, m_scale);
        d_point2 = Matrix.multiply(d_point2, m_scale);
        d_point3 = Matrix.multiply(d_point3, m_scale);
        this.moveCenterTo(d_point1, d_point2, d_point3, old_center);
        this.setColor(Color.red);
        this.drawTriangle(
            (int) (d_point1[0]),
            (int) (d_point1[1]),
            
            (int) (d_point2[0]),
            (int) (d_point2[1]),

            (int) (d_point3[0]),
            (int) (d_point3[1])
        );                    
        if(this.drawText)
            this.drawBageColor("Scale : " + value + "x");
    }

    public void refection(Integer type,double a) {
        double[] d_point1 = new double[3];
        double[] d_point2 = new double[3];
        double[] d_point3 = new double[3];
        for(int i=0;i<3;i++){
            if(i==2){
                d_point1[i] = 1;
                d_point2[i] = 1;
                d_point3[i] = 1;
                break;
            }
            d_point1[i] = (double) this.point1[i]; 
            d_point2[i] = (double) this.point2[i]; 
            d_point3[i] = (double) this.point3[i]; 
        }
        double[][] refec;
        String type_str = "";
        if(type==this.REFEC_X){
            type_str = "X";
            refec =  new double[][] {
                {1.0, 0.0,  0.0},
                {0.0, -1.0, 0.0},
                {0.0, 0.0,  1.0}
            };
        }else if(type ==this.REFEC_Y){
            type_str = "Y";
            refec = new double[][] {
                {-1.0,  0.0, 0.0},
                {0.0,   1.0, 0.0},
                {0.0,   0.0, 1.0}
            };
        }else if(type==this.REFEC_Y_EQUAL_MINUX_X){
            type_str = "Y = -X";
            refec = new double[][] {
                {-1.0,  0.0,  0.0},
                {0.0,  -1.0,  0.0},
                {0.0,   0.0,  1.0}
            };

        }else if (type ==this.REFEC_Y_EQUAL_A){
            type_str = "Y = ";
            double[][] tmp1 = new double[][] {
                {1.0,  0.0,  0.0},
                {0.0,  1.0,  a},
                {0.0,   0.0,  1.0}
            };
            double[][] tmp2 = new double[][] {
                {1.0,  0.0,  0.0},
                {0.0,  -1.0,  0.0},
                {0.0,   0.0,  1.0}
            };
            double[][] tmp3 = new double[][] {
                {1.0,  0.0,  0.0},
                {0.0,  1.0,  -a},
                {0.0,   0.0,  1.0}
            };
            refec = Matrix.multiply(tmp1, tmp2);
            refec = Matrix.multiply(refec, tmp3);
        }else if (type == this.REFEC_X_EQUAL_A){
            type_str = "X = ";
            double[][] tmp1 = new double[][] {
                {1.0,  0.0,  a},
                {0.0,  1.0,  0.0},
                {0.0,   0.0,  1.0}
            };
            double[][] tmp2 = new double[][] {
                {-1.0,  0.0,  0.0},
                {0.0,  1.0,  0.0},
                {0.0,   0.0,  1.0}
            };
            double[][] tmp3 = new double[][] {
                {1.0,  0.0,  -a},
                {0.0,  1.0,  0.0},
                {0.0,   0.0,  1.0}
            };
            refec = Matrix.multiply(tmp1, tmp2);            
            refec = Matrix.multiply(refec, tmp3);
        }else{
            type_str = "X";
            refec =  new double[][] {
                {1.0, 0.0,  0.0},
                {0.0, -1.0, 0.0},
                {0.0, 0.0,  1.0}
            };
            System.out.println("Wrong refection type");
        }

        d_point1 = Matrix.multiply(refec, d_point1);
        d_point2 = Matrix.multiply(refec, d_point2);
        d_point3 = Matrix.multiply(refec, d_point3);

        this.setColor(Color.MAGENTA);
        this.drawTriangle(
            (int) (d_point1[0]),
            (int) (d_point1[1]),
            
            (int) (d_point2[0]),
            (int) (d_point2[1]),

            (int) (d_point3[0]),
            (int) (d_point3[1])
        );                    
        if(this.drawText)
            this.drawBageColor("Refection : " + type_str + (type==5||type==4  ? a: "") );
    }

    public void rotate(Integer angle) {
        double[] d_point1 = Arrays.stream(this.point1).mapToDouble(i->(double)i).toArray();
        double[] d_point2 = Arrays.stream(this.point2).mapToDouble(i->(double)i).toArray();
        double[] d_point3 = Arrays.stream(this.point3).mapToDouble(i->(double)i).toArray();
        angle *= -1;
        double[] old_center = {
            (d_point1[0] + d_point2[0] + d_point3[0]) / 3,
            (d_point1[1] + d_point2[1] + d_point3[1]) / 3
        };
        double[][] m_rotate = {
            { Math.cos(Math.toRadians(angle)) , -(Math.sin(Math.toRadians(angle)))} ,
            { Math.sin(Math.toRadians(angle)) , Math.cos(Math.toRadians(angle)) }
        };
        d_point1 = Matrix.multiply(m_rotate, d_point1);
        d_point2 = Matrix.multiply(m_rotate, d_point2);
        d_point3 = Matrix.multiply(m_rotate, d_point3);

        this.setColor(Color.ORANGE);
        this.drawTriangle(
            (int) (d_point1[0]),
            (int) (d_point1[1]),
            
            (int) (d_point2[0]),
            (int) (d_point2[1]),

            (int) (d_point3[0]),
            (int) (d_point3[1])
        );                    
        if(this.drawText)
            this.drawBageColor(String.format("Rotation : %d " , angle*-1));
    }

     //อันนี้ก็วาดสามเหลี่ยมจากเส้นตรงสามเส้นตามปกติ
     public void drawTriangle(int p1X,int p1Y,int p2X,int p2Y,int p3X,int p3Y){
        /*
            อันนี้เป็นการดึงค่า Graphics ออกมาเนื่องจากการจะวาดรูปลงไปในรูปเดิมมันจำเป็นจำต้องใช้ ตัวแปร Graphics ตัวเดิมซึ่งใน JFrame มันมี Method
            ให้เรียกค่า Graphics นั้นออกมา ทาง getGraphics()
        */
        Graphics g = getGraphics();
        g.setColor(this.COLOR);
        g.drawLine(this.getX(p1X), this.getY(p1Y), this.getX(p2X), this.getY(p2Y) ); 
        g.drawLine(this.getX(p2X), this.getY(p2Y), this.getX(p3X), this.getY(p3Y) ); 
        g.drawLine(this.getX(p3X), this.getY(p3Y), this.getX(p1X), this.getY(p1Y) ); 
    }

    public void moveCenterTo(double[] d_point1,double[] d_point2,double[] d_point3,double[] to_center){
        double[] center = {
            (d_point1[0] + d_point2[0] + d_point3[0]) / 3,
            (d_point1[1] + d_point2[1] + d_point3[1]) / 3
        };

        d_point1[0] = d_point1[0] - center[0];
        d_point1[1] = d_point1[1] - center[1];
        
        d_point2[0] = d_point2[0] - center[0];
        d_point2[1] = d_point2[1] - center[1];
        
        d_point3[0] = d_point3[0] - center[0];
        d_point3[1] = d_point3[1] - center[1];

        //////////////////////////////////

        d_point1[0] = d_point1[0] + to_center[0];
        d_point1[1] = d_point1[1] + to_center[1];
        
        d_point2[0] = d_point2[0] + to_center[0];
        d_point2[1] = d_point2[1] + to_center[1];
        
        d_point3[0] = d_point3[0] + to_center[0];
        d_point3[1] = d_point3[1] + to_center[1];
    }

    public void drawBageColor(String str){
        Graphics g = getGraphics();
        g.setColor(this.COLOR);
        g.drawString(" - " + str, 100,100 + (this.countText++ * 20));
    }

    //เอาค่า Color ไปเก็บไว้ที่ตัวแปร global COLOR แล้วค่าไปเรียกใช้จาก this.COLOR อีกที (บรรทัดที่ 42)
    public void setColor(Color c){
        this.COLOR = c;
    }

    public void setDrawText(boolean b){
        this.drawText = b;
    }
    /*
        เนื่องจาก ปกติ รูปพวกมันนับเป็น pixel ซึ่งก็คือ จุด 0,0 จะอยู่มุมซ้ายบน
        แต่ในระบบการฟ 0,0 จะเป็นจุดศูนย์กลาง 
        ฟังชั่นข้างล่างเลยเป็นการรับค่าแบบ ระบบกราฟมาแล้วแปลงเป็นระบบปกติ
        สมมุต อยากได้จุด X=0 มันก็คือ เอาความยาวรูปมาหาจุดกึ่งกลาง Y=0 ก็เหมือนกัน
        แล้วทีนี้ถ้าอยากได้จุด X=10 ก็คือหาจาก 0 แล้ว + 10 
        ก็จะได้ฟังชั่น getX getY
    */

    private int getX(int px) {
        return (int) ( this.WIDTH / 2 ) + px;
    }

    private int getY(int px) {
        return (int) ( this.HEIGHT / 2 ) - px;
    }

    //เป็น method วาดกราฟเส้นประ
    void drawDash(Graphics g){
        this.drawDashedLine(g,this.WIDTH/2,0,this.WIDTH/2,this.HEIGHT);
        this.drawDashedLine(g,0,this.HEIGHT/2,this.WIDTH,this.HEIGHT/2);
        int h = this.HEIGHT / 50 ;
        int w = this.WIDTH / 50 ;
        g.setColor(new Color(188, 188, 188));
        for(int i = h*-1 ;i<=h;i++){
            g.drawString(i*50 + "", this.WIDTH / 2, this.getY(i*50));
        }
        for(int i = w*-1 ;i<=w;i++){
            g.drawString(i*50 + "", this.getX(i*50),this.HEIGHT/2 );
        }
    }

    //เป็นการวาดเส้น แต่จะเป็นเส้นประแทน (ก็อบมาจากในเน็ตเลยอธิบายไม่ค่อยได้)
    public void drawDashedLine(Graphics g, int x1, int y1, int x2, int y2){
        //creates a copy of the Graphics instance
        //ก็อปปี้ Grapichs อันเดิม แล้วแปลงให้เป็น Graphics2D
        Graphics2D g2d = (Graphics2D) g.create();
        //set the stroke of the copy, not the original 
        //วาดเส้นประ จากการใช้ Stroke
        Stroke dashed = new BasicStroke(1, BasicStroke.CAP_ROUND, BasicStroke.JOIN_ROUND, 0, new float[]{2}, 0);
        g2d.setColor(new Color(188, 188, 188));
        g2d.setStroke(dashed);
        g2d.drawLine(x1, y1, x2, y2);
        g2d.dispose();
    }
}