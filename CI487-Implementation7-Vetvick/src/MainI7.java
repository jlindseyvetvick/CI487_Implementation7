public class MainI7 {

    public static void main(String [] args){

        /*
         * +-+              +-+              +-+
         * |A|----7---+-----+B+--------8-----|C|
         * +++              +-+              +++
         *  |         +------+                |
         *  5         9                       |
         *  |         |                       |
         *  +---------+                +---5--+
         * +++              +-+        |
         * |D|-------15-----|E|--------+
         * +++              +++        |
         *  |                |         +---9---+
         *  |                8                 |
         *  +-----+          |                 |
         *        6         +++               +++
         *        +---------|F|------11-------|G|
         *                  +-+               +-+
         */
        Graph<String> map = new Graph<>();

        //Step 1: Setup vertecies
        map.addVertex("A");
        map.addVertex("B");
        map.addVertex("C");
        map.addVertex("D");
        map.addVertex("E");
        map.addVertex("F");
        map.addVertex("G");

        //Step 2: Setup edges
        map.addEdgeWeighted("A", "B", 7);
        map.addEdgeWeighted("A", "D", 5);
        map.addEdgeWeighted("D", "B", 9);
        map.addEdgeWeighted("D", "E", 15);
        map.addEdgeWeighted("B", "E", 7);
        map.addEdgeWeighted("B", "C", 8);
        map.addEdgeWeighted("D", "F", 6);
        map.addEdgeWeighted("F", "E", 8);
        map.addEdgeWeighted("F", "G", 11);
        map.addEdgeWeighted("E", "G", 9);
        map.addEdgeWeighted("E", "C", 5);


        System.out.println("\nPrim's Edges (Source=D):");
        map.primsMST("D");

        System.out.println("\nDijkstra's Edges (Source=D, Dest=G):");
        map.dijkstraShortestPath("D", "G");

    }

}
