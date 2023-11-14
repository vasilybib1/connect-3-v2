package persistence;

import model.Grid;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.PrintWriter;

public class JsonWriter {
  private static final int TAB = 4;
  private PrintWriter writer;
  private String path;

  public JsonWriter(String path) {
    this.path = path;
  }

  public void open() throws FileNotFoundException {
    writer = new PrintWriter(new File(path));
  }

  public void write(Grid g, int score) throws JSONException {
    JSONObject json = g.toJson(score);
    saveToFile(json.toString(TAB));
  }

  public void close() {
    writer.close();
  }

  private void saveToFile(String json) {
    writer.print(json);
  }
}
