import java.util.*; 
import java.lang.*; 
import java.io.*; 

public class ShortestPath {
    static boolean[] unvisited;

    public static int find_min_path(int[][] table){
        int min = Integer.MAX_VALUE;
        int index = -1;
        for(int i=0;i<table.length;i++){
            if(table[i][0] < min && unvisited[i] == true){
                min = table[i][0];
                index = i;
            }
        }
        return index;
    }

    public static void printTable(int[][] table){
        System.out.println("____________________");
        for(int i=0;i<table.length;i++){
            System.out.printf("| %c  ==> | %3d | %c | \n", 'A' + i ,table[i][0], 'A' + table[i][1]);
        }
        System.out.println("---------------------");
        System.out.println(Arrays.toString(unvisited));
    }

    

    public static void Dijkstra(int[][] table,int i_min_path,Graph[] graphs_list){
        Graph graph = graphs_list[i_min_path];
        for(Direction direction : graph.direction_to){
            Graph g = direction.to;
            int length = direction.length;
            if(table[g.index][0] > table[i_min_path][0] + length  && unvisited[i_min_path] == true || i_min_path == 3){
                table[g.index][0] = table[i_min_path][0] + length;
                table[g.index][1] = i_min_path;
            }
        }

        unvisited[i_min_path] = false;
    }

    public static void find_shortest(Graph[] graphs_list,int start){
        unvisited = new boolean[graphs_list.length];
        Arrays.fill(unvisited, true);
        int[][] table = new int[graphs_list.length][2];

        for(int i = 0;i<graphs_list.length;i++){
            if(i==start) {
                table[i][0] = 0;
            }else{
                table[i][0] = Integer.MAX_VALUE;
            }
            table[i][1] =-1;
        }

        int min_path = find_min_path(table);
        while(min_path != -1){  
            Dijkstra(table, min_path, graphs_list);
            min_path = find_min_path(table);
        }
        printTable(table);


    }


    public static void main (String[] args){
        Graph A = new Graph("A", 1 ,0);
        Graph B = new Graph("B", 1 ,1);
        Graph C = new Graph("C", 1 ,2);
        Graph D = new Graph("D", 1 ,3);
        Graph E = new Graph("E", 1 ,4);

        A.add_direction(new Direction(B, 6));
        A.add_direction(new Direction(D, 1));

        B.add_direction(new Direction(C, 5));
        B.add_direction(new Direction(D, 2));
        B.add_direction(new Direction(E, 2));

        C.add_direction(new Direction(B, 5));
        C.add_direction(new Direction(E, 5));

        D.add_direction(new Direction(A, 1));
        D.add_direction(new Direction(B, 2));
        D.add_direction(new Direction(E, 1));

        E.add_direction(new Direction(B, 2));
        E.add_direction(new Direction(C, 5));

        Graph[] graphs_list = {A,B,C,D,E};

        find_shortest(graphs_list, 0);

        } 
}