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

package pathfinder.specTest;

import graph.Graph;
import pathfinder.datastructures.Path;
import pathfinder.parser.Dijkstra;

import java.io.*;
import java.util.*;

/**
 * This class implements a test driver that uses a script file format
 * to test an implementation of Dijkstra's algorithm on a graph.
 */
public class PathfinderTestDriver {

  public static void main(String args[]) {
    try {
      if (args.length > 1) {
        printUsage();
        return;
      }

      PathfinderTestDriver td;

      if (args.length == 0) {
        td = new PathfinderTestDriver(new InputStreamReader(System.in),
                new OutputStreamWriter(System.out));
      } else {

        String fileName = args[0];
        File tests = new File (fileName);

        if (tests.exists() || tests.canRead()) {
          td = new PathfinderTestDriver(new FileReader(tests),
                  new OutputStreamWriter(System.out));
        } else {
          System.err.println("Cannot read from " + tests.toString());
          printUsage();
          return;
        }
      }

      td.runTests();

    } catch (IOException e) {
      System.err.println(e.toString());
      e.printStackTrace(System.err);
    }
  }

  private static void printUsage() {
    System.err.println("Usage:");
    System.err.println("to read from a file: java marvel.specTest.MarvelTestDriver <name of input script>");
    System.err.println("to read from standard in: java marvel.specTest.MarvelTestDriver");
  }

  /** String -> Graph: maps the names of graphs to the actual graph **/
  private final Map<String, Graph<String, Double>> graphs = new HashMap<>();

  private final PrintWriter output;
  private final BufferedReader input;

  public PathfinderTestDriver(Reader r, Writer w) {
    input = new BufferedReader(r);
    output = new PrintWriter(w);
  }

  /**
   * @effects Executes the commands read from the input and writes results to the output
   * @throws IOException if the input or output sources encounter an IOException
   **/
  public void runTests() throws IOException {
    String inputLine;
    while ((inputLine = input.readLine()) != null) {
      if ((inputLine.trim().length() == 0) ||
              (inputLine.charAt(0) == '#')) {
        // echo blank and comment lines
        output.println(inputLine);
      } else {
        // separate the input line on white space
        StringTokenizer st = new StringTokenizer(inputLine);
        if (st.hasMoreTokens()) {
          String command = st.nextToken();

          List<String> arguments = new ArrayList<String>();
          while (st.hasMoreTokens()) {
            arguments.add(st.nextToken());
          }

          executeCommand(command, arguments);
        }
      }
      output.flush();
    }
  }

  private void executeCommand(String command, List<String> arguments) {
    try {
      if (command.equals("CreateGraph")) {
        createGraph(arguments);
      } else if (command.equals("FindPath")) {
        findPath(arguments);
      } else if (command.equals("AddNode")) {
        addNode(arguments);
      } else if (command.equals("AddEdge")) {
        addEdge(arguments);
      } else if (command.equals("ListNodes")) {
        listNodes(arguments);
      } else if (command.equals("ListChildren")) {
        listChildren(arguments);
      } else {
        output.println("Unrecognized command: " + command);
      }
    } catch (Exception e) {
      output.println("Exception: " + e.toString());
    }
  }

  private void createGraph(List<String> arguments) {
    if (arguments.size() != 1) {
      throw new PathfinderTestDriver.CommandException("Bad arguments to CreateGraph: " + arguments);
    }

    String graphName = arguments.get(0);
    createGraph(graphName);
  }

  private void createGraph(String graphName) {
    graphs.put(graphName, new Graph<>());
    output.println("created graph " + graphName);
  }

  private void findPath(List<String> arguments) {
    if (arguments.size() != 3) {
      throw new PathfinderTestDriver.CommandException("Bad arguments to findPath: " + arguments);
    }

    String graphName = arguments.get(0);
    String node_1 = arguments.get(1);
    String node_n = arguments.get(2);
    findPath(graphName, node_1, node_n);
  }

  private void findPath(String graphName, String node_1, String node_n) {
    String node1 = node_1.replace("_", " ");
    String nodeN = node_n.replace("_", " ");
    boolean bool1 = graphs.get(graphName).nodeExists(node1);
    boolean bool2 = graphs.get(graphName).nodeExists(nodeN);
    output.println("path from " + node1 + " to " + nodeN + ":");
    if (bool1 && bool2) {
      Path<String> path = Dijkstra.findPath(graphs.get(graphName), node1, nodeN);
      if (path != null) {
        for (Path<String>.Segment segment : path) {
          output.println(segment.getStart() + " to " + segment.getEnd() + String.format(" with weight %.3f", segment.getCost()));
        }
        output.println(String.format("total cost: %.3f", path.getCost()));
      }
      else {
        output.println("no path found");
      }
    } else {
      if (!bool1) {
        output.println("unknown node " + node1);
      }
      if (!bool2) {
        output.println("unknown node " + nodeN);
      }
    }
  }

  private void addNode(List<String> arguments) {
    if (arguments.size() != 2) {
      throw new PathfinderTestDriver.CommandException("Bad arguments to addNode: " + arguments);
    }

    String graphName = arguments.get(0);
    String nodeName = arguments.get(1);

    addNode(graphName, nodeName);
  }

  private void addNode(String graphName, String nodeName) {
    graphs.get(graphName).addNode(nodeName);
    output.println("added node " + nodeName + " to " + graphName);
  }

  private void addEdge(List<String> arguments) {
    if (arguments.size() != 4) {
      throw new PathfinderTestDriver.CommandException("Bad arguments to addEdge: " + arguments);
    }

    String graphName = arguments.get(0);
    String parentName = arguments.get(1);
    String childName = arguments.get(2);
    String edgeLabel = arguments.get(3);

    addEdge(graphName, parentName, childName, edgeLabel);
  }

  private void addEdge(String graphName, String parentName, String childName,
                       String edgeLabel) {
    graphs.get(graphName).addEdge(parentName, childName, Double.valueOf(edgeLabel));
    output.println(String.format("added edge %.3f", Double.valueOf(edgeLabel)) + " from " + parentName + " to " + childName + " in " + graphName);
  }

  private void listNodes(List<String> arguments) {
    if (arguments.size() != 1) {
      throw new PathfinderTestDriver.CommandException("Bad arguments to listNodes: " + arguments);
    }

    String graphName = arguments.get(0);
    listNodes(graphName);
  }

  private void listNodes(String graphName) {
    List<String> list = graphs.get(graphName).listNodes();
    output.print(graphName + " contains:");
    for (String node : list) {
      output.print(" " + node);
    }
    output.println("");
  }

  private void listChildren(List<String> arguments) {
    if (arguments.size() != 2) {
      throw new PathfinderTestDriver.CommandException("Bad arguments to listChildren: " + arguments);
    }

    String graphName = arguments.get(0);
    String parentName = arguments.get(1);
    listChildren(graphName, parentName);
  }

  private void listChildren(String graphName, String parentName) {
    Graph<String, Double> g = graphs.get(graphName);
    Map<String, List<Double>> list = g.listOutEdges(parentName);
    List<String> nodeList = g.listChildren(parentName);
    output.print("the children of " + parentName + " in " + graphName + " are:");
    for (String node : nodeList) {
      list.get(node).sort(Double::compareTo);
      for (Double edge : list.get(node)) {
        output.print(" " + node + String.format("(%.3f)", edge));
      }
    }
    output.println("");
  }

  /**
   * This exception results when the input file cannot be parsed properly
   **/
  static class CommandException extends RuntimeException {

    public CommandException() {
      super();
    }
    public CommandException(String s) {
      super(s);
    }

    public static final long serialVersionUID = 3495;
  }

}
