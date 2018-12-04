import java.util.Arrays;
import java.util.Scanner;
public class LU{
    public static double[] blackWard(double[][] A,double[] b){
        int n = b.length;
        double[] x = new double[n];
        for (int i = n - 1; i >= 0; i--) {
            double sum = 0.0;
            for (int j = i + 1; j < n; j++)
                sum += A[i][j] * x[j];
            x[i] = (b[i] - sum) / A[i][i];
        }
        return x;
    }
    public static void main(String args[]){
        Scanner sc = new Scanner(System.in);
        int n = 3;
        double [][]mat = {
            {1.0,0.0,1.0},
            {2.0,1.0,0.0},
            {1.0,-1.0,1.0}
        };
        double[] b = {1.0,2.0,3.0};
        double [][]l = new double[n][n];
        l[0][0] = l[1][1] = l[2][2] = 1;
        l[0][1] = l[0][2] = l[1][2] = 0;
        double [][]u = new double[n][n];
        u[1][0] = u[2][0] = u[2][1] = 0;
        u[0][0] = mat[0][0];
        u[0][1] = mat[0][1];
        u[0][2] = mat[0][2];
        l[1][0] = mat[1][0]/mat[0][0];
        u[1][1] = mat[1][1] - (l[1][0]*u[0][1]);
        u[1][2] = mat[1][2] - (l[1][0]*u[0][2]);
        l[2][0] = mat[2][0]/u[0][0];
        l[2][1] = (mat[2][1] - l[2][1]*u[0][1])/u[1][1];
        u[2][2] = mat[2][2] - (l[2][0]*u[0][2]) - (l[2][1]*u[1][2]);
        double[] y = new double[3];
        y[0] = b[0] /  l[0][0];
        y[1] = (b[1] - ( y[0] * l[1][0] )) / l[1][1];
        y[2] = (b[2] - (y[0] * l[2][0]) + (y[1] * l[2][1])) / l[2][2];
        double[] ans = blackWard(u, y);
        System.out.println("The L Component is:");
        for(int i=0; i<n; i++){
            for(int j=0; j<n; j++)
                System.out.print(" "+l[i][j]);
            System.out.println();
        }
        System.out.println("The U Component is:");
        for(int i=0; i<n; i++){
            for(int j=0; j<n; j++)
                System.out.print(" "+u[i][j]);
            System.out.println();
        }
        System.out.println("Answer : " + Arrays.toString(ans));
    }
}