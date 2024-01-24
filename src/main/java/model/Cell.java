package model;

public class Cell{
  private double red;
  private double green;
  private double blue;

  public Cell(double r, double g, double b){
    this.red = r;
    this.green = g;
    this.blue = b;
  }

  public double getRed(){ return red; }
  public double getGreen(){ return green; }
  public double getBlue(){ return blue; }

  public String toString(){
    return red+" "+green+" "+blue;
  }
}
