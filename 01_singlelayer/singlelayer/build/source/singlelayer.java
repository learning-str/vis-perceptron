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

public class singlelayer extends PApplet {

PFont HIRAGINO10;
PFont HIRAGINO20;
Perceptron PERCEPTRON;
float MAX = 5;
float SCALE = 1;
int COLOR_TRUE = 0xffCCFF90;
int COLOR_FALSE = 0xffFF8A80;
String MOUSE_X_ASSIGN = "weight1";
String MOUSE_Y_ASSIGN = "weight2";
int MOUSE_MODE = 0;

public void setup() {
  
  SCALE = width / MAX;
  background(255);
  
  HIRAGINO10 = loadFont("HiraginoSans-W0-10.vlw");
  HIRAGINO20 = loadFont("HiraginoSans-W0-20.vlw");

  PERCEPTRON = new Perceptron(0.5f, 0.5f, 0.5f);
}

public void draw() {
  translate(width / 2, height / 2);
  for (int i = 0; i < 10000; i++) {
    float x1 = random(-MAX, MAX);
    float x2 = random(-MAX, MAX);
    noStroke();
    drawPerceptron(x1, x2, 2);
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
  text("mouseX assign: " + MOUSE_X_ASSIGN, 5, 70);
  text("mouseY assign: " + MOUSE_Y_ASSIGN, 5, 85);
}

public void mouseMoved() {
  switch (MOUSE_X_ASSIGN) {
    case "weight1":
      PERCEPTRON.weight1(5 * (mouseX - width / 2) / PApplet.parseFloat(width));
      break;
    case "threshold":
      PERCEPTRON.threshold(5 * (mouseX - width / 2) / PApplet.parseFloat(width));
      break;
    default:
      break;
  }
  switch (MOUSE_Y_ASSIGN) {
    case "weight2":
      PERCEPTRON.weight2(-5 * (mouseY - height / 2) / PApplet.parseFloat(height));
      break;
    default:
      break;
  }
}

public void keyPressed() {
  switch (key) {
    case ' ':
      background(255);
      break;
    case 'c':
      if (MOUSE_MODE == 0) {
        MOUSE_X_ASSIGN = "threshold";
        MOUSE_Y_ASSIGN = "undefined";
        MOUSE_MODE = 1;
      } else if (MOUSE_MODE == 1) {
        MOUSE_X_ASSIGN = "weight1";
        MOUSE_Y_ASSIGN = "weight2";
        MOUSE_MODE = 0;
      }
      break;
    default:
      break;
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

  public void weight1(float weight1) { this.weight1 = weight1; }
  public void weight2(float weight2) { this.weight2 = weight2; }
  public void threshold(float threshold) { this.threshold = threshold; }
  public float weight1() { return weight1; }
  public float weight2() { return weight2; }
  public float threshold() { return threshold; }
}
  public void settings() {  size(500, 500, P2D);  smooth(); }
  static public void main(String[] passedArgs) {
    String[] appletArgs = new String[] { "singlelayer" };
    if (passedArgs != null) {
      PApplet.main(concat(appletArgs, passedArgs));
    } else {
      PApplet.main(appletArgs);
    }
  }
}
