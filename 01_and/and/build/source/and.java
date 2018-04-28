import processing.core.*; 
import processing.data.*; 
import processing.event.*; 
import processing.opengl.*; 

import java.util.HashMap; 
import java.util.ArrayList; 
import java.io.File; 
import java.io.BufferedReader; 
import java.io.PrintWriter; 
import java.io.InputStream; 
import java.io.OutputStream; 
import java.io.IOException; 

public class and extends PApplet {

Perceptron PERCEPTRON;
float MAX = 5;
float SCALE = 1;

public void setup() {
  
  SCALE = width / MAX;
  background(255 / 2);
  

  PERCEPTRON = new Perceptron(0.5f, 0.5f, 0.5f);
}

public void draw() {
  translate(width / 2, height / 2);
  for (int i = 0; i < 50; i++) {
    float x1 = random(-MAX, MAX);
    float x2 = random(-MAX, MAX);
    noStroke();
    drawPerceptron(x1, x2, 1);
  }
  drawAxis();
  stroke(255 / 2);
  drawPerceptron(0, 0, 10);
  drawPerceptron(0, 1, 10);
  drawPerceptron(1, 0, 10);
  drawPerceptron(1, 1, 10);
}

public void drawPerceptron(float x1, float x2, float size) {
  if (PERCEPTRON.run(x1, x2)) {
    fill(0);
  } else {
    fill(255);
  }
  ellipse(x1 * SCALE, -x2 * SCALE, size, size);
}

public void drawAxis() {
  stroke(255, 0, 0);
  line(-width / 2, 0, width / 2, 0);
  line(0, -height / 2, 0, height / 2);
  textSize(10);
  text("x1", 0, 0);
}

public void drawParameter() {

}

public void keyPressed() {
  if (key == ' ') {
    background(255 / 2);
  }
}
class Perceptron {
  private float weight1;
  private float weight2;
  private float threshold;
  Perceptron(float weight1, float weight2, float threshold) {
    this.weight1 = weight1;
    this.weight2 = weight2;
    this.threshold = threshold;
  }

  public boolean run(float x1, float x2) {
    if (weight1 * x1 + weight2 * x2 <= threshold) {
      return false;
    }
    return true;
  }

  public void threshold(float threshold) { this.threshold = threshold; }
}
  public void settings() {  size(500, 500, P2D);  smooth(); }
  static public void main(String[] passedArgs) {
    String[] appletArgs = new String[] { "and" };
    if (passedArgs != null) {
      PApplet.main(concat(appletArgs, passedArgs));
    } else {
      PApplet.main(appletArgs);
    }
  }
}
