package pathfinder.parser;

import graph.Graph;
import pathfinder.datastructures.Path;

import java.util.*;

/** Contains a single method "findPath"
 *  findPath finds the smallest weighted path from a starting point to a destination point
 */
public class Dijkstra {

    // Not does not represent an ADT

    /** findPath returns the shortest path from a starting node to a destination node, evaluating the shortest path
     * based on the weighted double values of the edges between each node
     *
     * @param graph - The graph to search through, note that the graph edges must be weighted as Doubles
     * @param starting_node - Where to start the path
     * @param destination_node - Where to end the path
     * @param <E> - The type parameter for our graph nodes
     * @return a Path (of a type defined by the client) that is the smallest cost path from
     *         starting_node to destination_node
     *         Returns null if no path exists from starting node to destination node
     */
    public static <E> Path<E> findPath(Graph<E,Double> graph, E starting_node, E destination_node) {

        PriorityQueue<Path<E>> active = new PriorityQueue<>((Path<E> p1, Path<E> p2) -> {
            Double cost1 = p1.getCost();
            Double cost2 = p2.getCost();
            return cost1.compareTo(cost2);
        });
        // Each element is a path from start to a given node.
        // A path's 'priority' in the queue is the total cost of that path.
        // Nodes for which no path is known yet are not in the queue.

        // finished = set of nodes for which we know the minimum-cost path from start.
        Set<E> finished = new HashSet<>();

        // Initially we only know of the path from start to itself, which has
        // a cost of zero because it contains no edges.

        // Add a path from start to itself to active
        active.add(new Path<E>(starting_node));

        //while active is non-empty:
        while(!active.isEmpty()) {
            // minPath is the lowest-cost path in active and,
            // if minDest isn't already 'finished,' is the
            // minimum-cost path to the node minDest
            Path<E> minPath = active.poll();  // remove min in active
            E minDest = minPath.getEnd();  // destination node in minPath

            if (minDest.equals(destination_node)) {  // if minDest is dest:
                return minPath;
            }

            if (finished.contains(minDest)) {  // if minDest is in finished:
                continue;
            }
            Map<E, List<Double>> children = graph.listOutEdges(minDest);
            for (E child : children.keySet()) {  // For all children of minDest

                // If we don't know the minimum-cost path from start to child,
                // examine the path we've just found
                if (!finished.contains(child)) {  // if child is not in finished:
                    children.get(child).sort(Double::compareTo);
                    Path<E> newPath = minPath.extend(child, children.get(child).get(0));
                    active.add(newPath);  // add newPath to active
                }
            }
            finished.add(minDest);  // add minDest to finished
        }
        // If the loop terminates, then no path exists from start to dest.
        // so just return null.
        return null;
    }

}
