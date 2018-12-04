import java.util.*;
import java.io.IOException;

public class Main {
    public static void main(String[] args) throws IOException {
            DrawImageClass img = new DrawImageClass(500,500);

            img.drawTriangle(0, 0, 50, 50, 100, 0, false);
            img.dispose();
            img.saveToFile("img.jpg");
    }
}