import java.util.*;
import java.util.function.Function;
import java.awt.*;

public class DrawGUI { 
    public static void main(String[] args) {
        String[] spoint1 = "-20,10".split(",");
        String[] spoint2 = "20,50".split(",");
        String[] spoint3 = "100,10".split(",");
        String[] smove_to = "30,-50".split(",");

        double scale = 2.5;
        int rotate = 90;
        // int refection_type = 5;

        Integer[] point1 = Arrays.stream(spoint1).map(Integer::parseInt).toArray(Integer[]::new);
        Integer[] point2 = Arrays.stream(spoint2).map(Integer::parseInt).toArray(Integer[]::new);
        Integer[] point3 = Arrays.stream(spoint3).map(Integer::parseInt).toArray(Integer[]::new);
        Integer[] move_to = Arrays.stream(smove_to).map(Integer::parseInt).toArray(Integer[]::new);

        DrawTriClass windows = new DrawTriClass(800, 600);
        windows.setColor(Color.blue);
        windows.setDrawText(true);
        windows.setDefaultTriangle(point1, point2, point3);
        windows.moveTriangle( move_to[0], move_to[1]);
        //windows.scale(scale);
        windows.rotate(rotate);
        //windows.refection(windows.REFEC_X_EQUAL_A,-100.0);
        //windows.refection(windows.REFEC_Y_EQUAL_MINUX_X,0.0);
    }
}