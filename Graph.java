import java.util.LinkedList;

class Graph {
    String store_name = "";
    LinkedList<Direction> direction_to = new LinkedList<Direction>();
    int floor;
    int index;
    Graph(String name,int floor,int i){
        this.store_name = name;
        this.floor = floor;
        this.index = i;
    }

    public void add_direction(Direction d){
        this.direction_to.push(d);
    }
}
