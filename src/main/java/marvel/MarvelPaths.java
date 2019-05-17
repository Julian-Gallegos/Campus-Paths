package marvel;

import graph.Graph;
import java.io.FileNotFoundException;
import java.util.*;
import static marvel.MarvelParser.parseData;

public class MarvelPaths {

    // Not an ADT, so no rep invariant or abstraction function.

    public static Graph CreateAndLoadGraph(String filename) throws FileNotFoundException {
        Graph graph = new Graph();
        Iterator<HeroModel> heroIt = parseData(filename);
        Map<String, List<String>> heroSet = new HashMap<String, List<String>>();
         while(heroIt.hasNext()) {
             HeroModel model = heroIt.next();
             heroSet.put(model.getHero(), new ArrayList<String>());
             graph.addNode(model.getHero());
             heroSet.get(model.getHero()).add(model.getBook());
         }
         List tmpList = graph.listNodes();
         for (int i = 0; i < tmpList.size(); i++) {
             for (int k = i; k < tmpList.size(); k++) {
                 for (String book : heroSet.get(tmpList.get(i))) {
                     for (String book2 : heroSet.get(tmpList.get(k))) {
                         if (book == book2) {
                             graph.addEdge(tmpList.get(i).toString(), tmpList.get(k).toString(), book);
                             graph.addEdge(tmpList.get(k).toString(), tmpList.get(i).toString(), book);
                         }
                     }
                 }
             }
         }
         return graph;
    }

    public static List<Path> ShortestPathBFS(Graph graph, String starting_node, String destination_node) {
        String start = starting_node;
        String dest = destination_node;
        Queue<String> worklist = new LinkedList<String>();  // queue, or "worklist", of nodes to visit: initially empty
        Map<String, List<Path>> map = new HashMap<String, List<Path>>();  // map from nodes to paths: initially empty.
        // Each key in map is a visited node.
        // Each value is a path from start to that node.
        // A path is a list; you decide whether it is a list of nodes, or edges,
        // or node data, or edge data, or nodes and edges, or something else.

        worklist.add(start);  // Add start to worklist
        map.put(start, new ArrayList<Path>());  // Add start->[] to map (start mapped to an empty list)
        while (!worklist.isEmpty()) {  // Q is not empty:
            String next = worklist.remove();  // dequeue next node in worklist
            if (next == dest) {  // If node "next" is node "dest"
                return map.get(next); // the path associated with next in map
            }
            for (String childNode : graph.listChildren(next)) {
                if (!map.containsKey(childNode)) {  // if childNode is not in map, i.e. it has not been visited
                    List<String> edges = graph.listOutEdges(next).get(childNode);
                    edges.sort(String::compareTo);
                    String edge = edges.get(0);
                    List<Path> path = map.get(next);  // let path be the path next maps to in map
                    path.add(new Path(next, childNode, edge));
                    map.put(childNode, path);  // add childNode->path to map
                    worklist.add(childNode);  // add childNode to worklist
                }
            }
        }
        System.out.println("Could not find path between nodes, returning null");
        // If the loop terminates, then no path exists from start to dest.
        // The implementation should indicate this to the client. Note that
        // BFS returns the path with the fewest number of edges.
        return null;
    }



    public static void main(String[] args) {

        if (args.length != 3) {
            System.out.println("Error. Incorrect input count. Usage:");
            System.out.println("java marvel.MarvelPaths <file to read from> <start of path character> <character end path>");
            return;
        }

        String file = "src/test/resources/marvel/data/" + args[0];


    }

}
