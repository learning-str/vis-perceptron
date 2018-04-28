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

PFont HIRAGINO10;
PFont HIRAGINO20;
Perceptron PERCEPTRON;
float MAX = 5;
float SCALE = 1;
int COLOR_TRUE = 0xffCCFF90;
int COLOR_FALSE = 0xffFF8A80;

public void setup() {
  
  SCALE = width / MAX;
  background(255);
  
  HIRAGINO10 = loadFont("HiraginoSans-W0-10.vlw");
  HIRAGINO20 = loadFont("HiraginoSans-W0-20.vlw");

  PERCEPTRON = new Perceptron(0.5f, 0.5f, 0.5f);
}

public void draw() {
  translate(width / 2, height / 2);
  for (int i = 0; i < 5000; i++) {
    float x1 = random(-MAX, MAX);
    float x2 = random(-MAX, MAX);
    noStroke();
    drawPerceptron(x1, x2, 1);
  }
  drawAxis();
  stroke(150);
  strokeWeight(1);
  drawPerceptron(0, 0, 15);
  drawPerceptron(0, 1, 15);
  drawPerceptron(1, 0, 15);
  drawPerceptron(1, 1, 15);
  drawParameters();
}

public void drawPerceptron(float x1, float x2, float size) {
  if (PERCEPTRON.run(x1, x2)) {
    fill(COLOR_TRUE);
  } else {
    fill(COLOR_FALSE);
  }
  ellipse(x1 * SCALE, -x2 * SCALE, size, size);
}

public void drawAxis() {
  stroke(150);
  line(-width / 2, 0, width / 2, 0);
  line(0, -height / 2, 0, height / 2);
  textFont(HIRAGINO20);
  textSize(20);
  fill(100);
  text("x1", width / 2 - 30, -5);
  text("x2", 5, -height / 2 + 30);
}

public void drawParameters() {
  translate(-width / 2, -height / 2);
  textFont(HIRAGINO10);
  textSize(10);
  fill(100);
  text("weight1: " + PERCEPTRON.weight1(), 5, 20);
  text("weight2: " + PERCEPTRON.weight2(), 5, 35);
  text("threshold: " + PERCEPTRON.threshold(), 5, 50);
}

public void keyPressed() {
  if (key == ' ') {
    background(255);
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
  public float weight1() { return weight1; }
  public float weight2() { return weight2; }
  public float threshold() { return threshold; }
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
