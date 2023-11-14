package persistence;

import model.Cell;
import model.Grid;
import main.Main;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.stream.Stream;

public class JsonReader {
  private String path;

  public JsonReader(String path) {
    this.path = path;
  }

  public Grid read() throws IOException, JSONException {
    String jsonData = readFile(path);
    JSONObject jsonObject = new JSONObject(jsonData);
    return parseGrid(jsonObject);
  }

  public int readScore() throws IOException, JSONException {
    String jsonData = readFile(path);
    JSONObject jsonObject = new JSONObject(jsonData);
    return jsonObject.getInt("score");
  }

  private String readFile(String source) throws IOException, JSONException {
    StringBuilder contentBuilder = new StringBuilder();
    try (Stream<String> stream = Files.lines(Paths.get(path), StandardCharsets.UTF_8)) {
      stream.forEach(s -> contentBuilder.append(s));
    }
    return contentBuilder.toString();
  }

  private Grid parseGrid(JSONObject jsonObject) throws JSONException {
    JSONArray cellArray = jsonObject.getJSONArray("grid");
    Cell[][] cells = new Cell[cellArray.length()][cellArray.getJSONArray(0).length()];
    for (int i = 0; i < cellArray.length(); i++) {
      JSONArray cellArrayCol = cellArray.getJSONArray(i);
      Cell[] cellColumns = new Cell[cellArrayCol.length()];
      for (int x = 0; x < cellArrayCol.length(); x++) {
        JSONObject cell = cellArrayCol.getJSONObject(x);
        Cell c = new Cell(cell.getDouble("red"), cell.getDouble("green"), cell.getDouble("blue"));
        cellColumns[x] = c;
      }
      cells[i] = cellColumns;
    }
    
    return new Grid(cells, Main.COLOR_SCHEME);
  }

}
