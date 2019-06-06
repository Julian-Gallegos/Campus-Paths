package campuspaths;

import campuspaths.utils.CORSFilter;
import com.google.gson.Gson;
import pathfinder.ModelConnector;
import pathfinder.datastructures.Path;
import pathfinder.datastructures.Point;
import spark.Request;
import spark.Response;
import spark.Route;
import spark.Spark;

import java.util.Map;

public class SparkServer {

  public static void main(String[] args) {
    CORSFilter corsFilter = new CORSFilter();
    corsFilter.apply();
    // The above two lines help set up some settings that allow the
    // React application to make requests to the Spark server, even though it
    // comes from a different server.
    // You should leave these two lines at the very beginning of main().


    ModelConnector model = new ModelConnector();

    Spark.get("/find-path", new Route() {
      @Override
      public Object handle(Request request, Response response) throws Exception {
        String startString = request.queryParams("start");
        String endString = request.queryParams("end");
        if(startString == null || endString == null) {
          Spark.halt(400, "must have start and end");
        }
        Path<Point> path = model.findShortestPath(startString, endString);
        Gson gson = new Gson();
        return gson.toJson(path);
      }
    });

    Spark.get("/buildings", new Route() {
      @Override
      public Object handle(Request request, Response response) throws Exception {
        Map<String, String> buildings = model.buildingNames();
        Gson gson = new Gson();
        return gson.toJson(buildings);
      }
    });


  }

}
