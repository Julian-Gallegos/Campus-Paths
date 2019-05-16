package marvel;

import graph.Graph;
import graph.Node;

import java.io.FileNotFoundException;
import java.lang.reflect.Array;
import java.util.*;

import static marvel.MarvelParser.parseData;
import static marvel.MarvelParser.parseData;

public class MarvelPaths {

    public void LoadGraph(String filename) throws FileNotFoundException {
        Graph graph = new Graph();
        Iterator<HeroModel> heroIt = parseData(filename);
        Map<String, List<String>> heroSet = new HashMap<String, List<String>>();
         while(heroIt.hasNext()) {
             HeroModel model = heroIt.next();
             if (!graph.nodeExists(model.getHero())) {
                 heroSet.put(model.getHero(), new ArrayList<String>());
                 graph.addNode(model.getHero());
             }
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
    }

    public static void main(String[] args) throws  FileNotFoundException {

    }

}
