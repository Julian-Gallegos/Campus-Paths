/*
 * Copyright ©2019 Hal Perkins.  All rights reserved.  Permission is
 * hereby granted to students registered for University of Washington
 * CSE 331 for use solely during Spring Quarter 2019 for purposes of
 * the course.  No other use, copying, distribution, or modification
 * is permitted without prior written consent. Copyrights for
 * third-party components of this work must be honored.  Instructors
 * interested in reusing these course materials should contact the
 * author.
 */

package pathfinder;

import graph.Graph;
import pathfinder.datastructures.Path;
import pathfinder.datastructures.Point;
import pathfinder.parser.CampusBuilding;
import pathfinder.parser.CampusPath;
import pathfinder.parser.Dijkstra;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static pathfinder.parser.CampusPathsParser.parseCampusBuildings;
import static pathfinder.parser.CampusPathsParser.parseCampusPaths;

/*
In the pathfinder homework, the text user interface calls these methods to talk
to your model. In the campuspaths homework, your graphical user interface
will ultimately make class to these methods (through a web server) to
talk to your model the same way.

This is the power of the Model-View-Controller pattern, two completely different
user interfaces can use the same model to display and interact with data in
different ways, without requiring a lot of work to change things over.
*/

/**
 * This class represents the connection between the view and controller and the model
 * for the pathfinder and campus paths applications.
 */
public class ModelConnector {

  private Graph<Point, Double> graph;
  private Map<String ,CampusBuilding> buildings;
  private List<CampusPath> paths;

  // This does not represent an ADT (I think?). Honestly not sure as it specifically uses the campus tsv and jpg data.

  /**
   * Creates a new {@link ModelConnector} and initializes it to contain data about
   * pathways and buildings or locations of interest on the campus of the University
   * of Washington, Seattle. When this constructor completes, the dataset is loaded
   * and prepared, and any method may be called on this object to query the data.
   */
  public ModelConnector() {
    // Remember the tenets of design that you've learned. You shouldn't necessarily do everything
    // you need for the model in this one constructor, factor code out to helper methods or
    // classes to work with your design best. The only thing that needs to remain the
    // same is the name of this class and the four method signatures below, because the
    // Pathfinder application calls these methods in order to talk to your model.
    // Change and add anything else as you'd like.

    buildings = new HashMap<>();
    List<CampusBuilding> buildingData = parseCampusBuildings();
    for (CampusBuilding building : buildingData) {
      buildings.put(building.getShortName(), building);
    }
    paths = parseCampusPaths();
    graph = loadGraph();
  }

  /**
   * @param shortName The short name of a building to query.
   * @return {@literal true} iff the short name provided exists in this campus map.
   */
  public boolean shortNameExists(String shortName) {
    return buildings.containsKey(shortName);
  }

  /**
   * @param shortName The short name of a building to look up.
   * @return The long name of the building corresponding to the provided short name.
   * @throws IllegalArgumentException if the short name provided does not exist.
   */
  public String longNameForShort(String shortName) {
    if (!shortNameExists(shortName)) {
      throw new IllegalArgumentException();
    }
    return buildings.get(shortName).getLongName();
  }

  /**
   * @return The mapping from all the buildings' short names to their long names in this campus map.
   */
  public Map<String, String> buildingNames() {
    Map<String, String> retMap = new HashMap<>();
    for (CampusBuilding building : buildings.values()) {
      retMap.put(building.getShortName(), building.getLongName());
    }
    return retMap;
  }

  /**
   * Finds the shortest path, by distance, between the two provided buildings.
   *
   * @param startShortName The short name of the building at the beginning of this path.
   * @param endShortName   The short name of the building at the end of this path.
   * @return A path between {@code startBuilding} and {@code endBuilding}, or {@literal null}
   * if none exists.
   * @throws IllegalArgumentException if {@code startBuilding} or {@code endBuilding} are
   *                                  {@literal null}, or not valid short names of buildings in
   *                                  this campus map.
   */
  public Path<Point> findShortestPath(String startShortName, String endShortName) {

    if (startShortName == null || endShortName == null ||
            !shortNameExists(startShortName) || !shortNameExists(endShortName)) {
      throw new IllegalArgumentException();
    }

    Point startingPoint = new Point(buildings.get(startShortName).getX(), buildings.get(startShortName).getY());
    Point endingPoint = new Point(buildings.get(endShortName).getX(), buildings.get(endShortName).getY());

    return Dijkstra.findPath(graph, startingPoint, endingPoint);
  }


  /**
   * Constructs a graph of points related to paths and buildings on a map, creates edges between points based on the
   * information contained in the CampusPaths list "paths"
   * @return a graph containing all the (x, y) points from paths as well as the connections between those points.
   */
  private  Graph<Point, Double> loadGraph() {
    Graph<Point, Double> graph = new Graph<>();
    for(CampusPath path : paths) {
      Point point = new Point(path.getX1(), path.getY1());
      graph.addNode(point);
    }
    for(CampusPath path : paths) {
      Point point1 = new Point(path.getX1(), path.getY1());
      Point point2 = new Point(path.getX2(), path.getY2());
      graph.addEdge(point1, point2, path.getDistance());
    }
    return graph;
  }

}
