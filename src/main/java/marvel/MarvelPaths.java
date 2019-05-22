package marvel;

import graph.Graph;
import java.io.FileNotFoundException;
import java.io.IOException;
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
             if (!heroSet.containsKey(model.getBook())) {
                 heroSet.put(model.getBook(), new ArrayList<String>());
             }
             graph.addNode(model.getHero());
             heroSet.get(model.getBook()).add(model.getHero());
         }
         for (String book : heroSet.keySet()) {
             for (String hero1 : heroSet.get(book)) {
                 for (String hero2 : heroSet.get(book)) {
                     if (!hero1.equals(hero2)) {
                         graph.addEdge(hero1, hero2, book);
                         graph.addEdge(hero2, hero1, book);
                     }
                 }
             }
         }
         return graph;
    }

    public static Queue<Path> ShortestPathBFS(Graph graph, String starting_node, String destination_node) {
        Queue<String> worklist = new LinkedList<>();  // queue, or "worklist", of nodes to visit: initially empty
        Map<String, Queue<Path>> map = new HashMap<>();  // map from nodes to paths: initially empty.
        // Each key in map is a visited node.
        // Each value is a path from start to that node.
        // A path is a list; you decide whether it is a list of nodes, or edges,
        // or node data, or edge data, or nodes and edges, or something else.
        worklist.add(starting_node);  // Add start to worklist
        map.put(starting_node, new LinkedList<>());  // Add start->[] to map (start mapped to an empty list)
        while (!worklist.isEmpty()) {  // Q is not empty:
            String next = worklist.remove();  // dequeue next node in worklist
            if (next.equals(destination_node)) {  // If node "next" is node "dest"
                return map.get(next); // the path associated with next in map
            }
            for (String childNode : graph.listChildren(next)) {
                if (!map.containsKey(childNode)) {  // if childNode is not in map, i.e. it has not been visited
                    List<String> edges = graph.listOutEdges(next).get(childNode);
                    edges.sort(String::compareTo);
                    String edge = edges.get(0);
                    Queue<Path> path = new LinkedList<>();
                    for (Path aPath : map.get(next)) {
                        path.add(aPath);  // let path be the path next maps to in map
                    }
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



    public static void main(String[] args) throws IOException {

        String file = "src/test/resources/marvel/data/marvel.tsv";

        Graph newGraph = CreateAndLoadGraph(file);


        System.out.println("MarvelPaths allows the user to request a path from one comic book character to another");
        System.out.println("This path uses comic books each character has been in to traverse through a graph");
        System.out.println("The graph connects characters together through the issues they both appeared in\n");

        System.out.println("Enter a Marvel comic book character to get started, or type 'done' when finished");

        Scanner console = new Scanner(System.in);
        boolean isDone = false;
        String stream = "";
        String character1;
        String character2;

        while (!stream.equals("done")){
            stream = console.nextLine();
            if (!stream.equals("done")) {
                System.out.println(stream + " Set as the starting Marvel Character");
                character1 = stream;
                System.out.println("Now set the destination character, or type 'done' to finish");
                System.out.println("MarvelPaths will then begin creating a path between the two characters\n");
                stream = console.nextLine();
                if (!stream.equals("done")) {
                    character2 = stream;

                    boolean bool1 = newGraph.nodeExists(character1);
                    boolean bool2 = newGraph.nodeExists(character2);

                    System.out.println("Creating path...\n");

                    System.out.println("path from " + character1 + " to " + character2 + ":");
                    if ((bool1 && bool2)) {
                        Queue<Path> paths = ShortestPathBFS(newGraph, character1, character2);
                         if (paths != null) {
                            for (Path path : paths) {
                                System.out.println(path.getParent() + " to " + path.getChild() + " via " + path.getEdge());
                            }
                        } else {
                            System.out.println("no path found");
                        }
                    } else {
                        if (!bool1) {
                            System.out.println("unknown character " + character1);
                        }
                        if (!bool2) {
                            System.out.println("unknown character " + character2);
                        }
                        System.out.println();
                    }
                    System.out.println("Type another character to start from if you would like to create another path");
                    System.out.println("Or just type 'done' if finished");
                }
            }
        }
        console.close();
        return;

    }

}
