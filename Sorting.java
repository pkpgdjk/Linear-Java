import java.lang.reflect.Array;
import java.util.*;
import java.util.function.Function;


public class Sorting {

    public static void main(String[] args) {

        timer("Bubble Sort",Sorting::bubble_srt);
        timer("Selection Sort",Sorting::selection_srt);
        timer("Insertion Sort",Sorting::insertion_srt);
        timer("Heap Sort",Sorting::heap_srt);
        timer("Tree Sort",Sorting::tree_srt);
    }

    static void timer(String algor_name ,Function<Integer[],Integer[]> fun){
        Integer[] unsort = {29,391,23,29,47,8,18,283,84,92,3,2,18,23,4,1,28,46,99,92,165,35,27,45};
        Integer[] sorted = new Integer[unsort.length];
        Integer[] arr_temp = new Integer[unsort.length];
        long start,end,diff;

        System.out.println("Unsort Array : " + Arrays.toString(unsort));
        arr_temp = Arrays.copyOf(unsort, unsort.length); 
        start = System.currentTimeMillis();
        System.out.println("Start " + algor_name + " at " + start);

        sorted =  fun.apply(arr_temp);
        
        end = System.currentTimeMillis();
        System.out.println("Sorted at " + end);
        diff = end - start;
        System.out.printf("time used : %d ms \n",diff);        
        System.out.println("Sorted Array : " + Arrays.toString(sorted));
        System.out.println();
        System.out.println();

    }

    public static Integer[] bubble_srt(Integer array[]) {
        Integer n = array.length;
        Integer k;
        for (Integer m = n; m >= 0; m--) {
            for (Integer i = 0; i < n - 1; i++) {
                k = i + 1;
                if (array[i] > array[k]) {
                    swapNumbers(i, k, array);
                }
            }
        }
        return array;
    }
    
    private static void swapNumbers(Integer i, Integer j, Integer[] array) {
        Integer temp;
        temp = array[i];
        array[i] = array[j];
        array[j] = temp;
    }

    public static Integer[] selection_srt(Integer[] arr){
         
        for (Integer i = 0; i < arr.length - 1; i++)
        {
            Integer index = i;
            for (Integer j = i + 1; j < arr.length; j++)
                if (arr[j] < arr[index]) 
                    index = j;
      
                    Integer smallerNumber = arr[index];  
            arr[index] = arr[i];
            arr[i] = smallerNumber;
        }
        return arr;
    }

    public static Integer[] insertion_srt(Integer[] input){
         
        Integer temp;
        for (Integer i = 1; i < input.length; i++) {
            for(Integer j = i ; j > 0 ; j--){
                if(input[j] < input[j-1]){
                    temp = input[j];
                    input[j] = input[j-1];
                    input[j-1] = temp;
                }
            }
        }
        return input;
    }

    private static void buildheap(Integer []arr) {
        for(Integer i=(arr.length-1)/2; i>=0; i--){
         heapify(arr,i,arr.length-1);
          }
       }

    public static void heapify(Integer[] arr, Integer i,Integer size) { 
        Integer left = 2*i+1;
        Integer right = 2*i+2;
        Integer max;
        if(left <= size && arr[left] > arr[i]){
            max=left;
        } else {
            max=i;
        }
     
        if(right <= size && arr[right] > arr[max]) {
            max=right;
        }
        if(max!=i) {
            exchange(arr,i, max);
            heapify(arr, max,size);
          }
    }
     
    private static void exchange(Integer[] arr,Integer i, Integer j) {
        Integer t = arr[i];
        arr[i] = arr[j];
        arr[j] = t; 
    }
     
    public static Integer[] heap_srt(Integer[] arr) {
        
        buildheap(arr);
        Integer sizeOfHeap=arr.length-1;
        for(Integer i=sizeOfHeap; i>0; i--) {
            exchange(arr,0, i);
            sizeOfHeap=sizeOfHeap-1;
            heapify(arr, 0,sizeOfHeap);
        }
        return arr;
    }


    static List<Integer> ListForTreeSort = new ArrayList<Integer>();
    public static Integer[] tree_srt(Integer[] arr) {
        Node root;
        root = new Node(arr[0]);
        for (Integer i = 1; i < arr.length; i++) {
            insert(root, new Integer(arr[i]));
        }
        inOrder(root);
        Integer[] sorted = ListForTreeSort.stream().toArray( Integer[]::new );
        return sorted;
    }

    private static void inOrder(Node node) {
        if (node != null) {
            inOrder(node.left);
            ListForTreeSort.add(((Integer) node.element).intValue());
            inOrder(node.right);
        }
    }

    private static Node insert(Node node, Integer x) {
        if (node == null) {
            return node = new Node(x);
        }

        if (x < (Integer) node.element) {
            node.left = insert(node.left, x);
        } else {
            node.right = insert(node.right, x);
        }
        return node;
    }


}


class Node {
    public Object element;
    public Node left;
    public Node right;
   
    // CONSTRUCTORS 
    public Node(Object theElement) {
     this(theElement, null, null);
    }
   
    public Node(Object theElement, Node lLink, Node rLink) {
     element = theElement;
     this.left = lLink;
     this.right = rLink;
    }
   }