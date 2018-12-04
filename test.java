
import java.util.*;

public class test {
    public static int hash_fn(int num) {
        if(num<10){
            return num % 4;
        }else if(num<100){
            return (((num % 10 ) % 7) % 3 ) + 4;
        }else{
            return (((num % 10 ) % 7) % 3 ) + 7;
        }
        // return (sum) >=10 ? hash_fn(sum):sum;
    }

    
    public static void main(String[] args) {
        int[] table = new int[11];
        int[] arr = {8,18,118,9,19,119,6,16,116,1};
        
       for(int i =0;i<arr.length;i++){
           System.out.printf("%d  hashed => %d\n", arr[i],hash_fn(arr[i]));
           table[hash_fn(arr[i])]++;
       }
       System.out.println(Arrays.toString(table));
    }
}