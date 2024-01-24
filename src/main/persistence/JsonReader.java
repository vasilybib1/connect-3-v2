package persistence;

import model.Cell;
import model.Grid;
import org.json.JSONArray;
import org.json.JSONObject;
import ui.Main;

import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.stream.Stream;

public class JsonReader {
    /* CLASS LEVEL COMMENT
    this class is responsible for reading the file and retrieving data from it

    CREDIT: inspired and modified from https://github.students.cs.ubc.ca/CPSC210/JsonSerializationDemo
     */
    private String path;

    // EFFECTS: constructs reader to read from source file
    public JsonReader(String path) {
        this.path = path;
    }

    // EFFECTS: reads grid from file and returns it;
    // throws IOException if an error occurs reading data from file
    public Grid read() throws IOException {
        String jsonData = readFile(path);
        JSONObject jsonObject = new JSONObject(jsonData);
        return parseGrid(jsonObject);
    }

    // EFFECT: reads the score thats save on the file
    // throws IOException if an error occurs reading data from file
    public int readScore() throws IOException {
        String jsonData = readFile(path);
        JSONObject jsonObject = new JSONObject(jsonData);
        return jsonObject.getInt("score");
    }

    // EFFECTS: reads source file as string and returns it
    private String readFile(String source) throws IOException {
        StringBuilder contentBuilder = new StringBuilder();
        try (Stream<String> stream = Files.lines(Paths.get(path), StandardCharsets.UTF_8)) {
            stream.forEach(s -> contentBuilder.append(s));
        }
        return contentBuilder.toString();
    }


    // EFFECTS: parses grid from JSON object and returns it
    private Grid parseGrid(JSONObject jsonObject) {
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
