PFont HIRAGINO10;
PFont HIRAGINO20;
Perceptron PERCEPTRON;
float MAX = 5;
float SCALE = 1;
color COLOR_TRUE = #CCFF90;
color COLOR_FALSE = #FF8A80;
String MOUSE_X_ASSIGN = "weight1";
String MOUSE_Y_ASSIGN = "weight2";
int MOUSE_MODE = 0;

void setup() {
  size(500, 500, P2D);
  SCALE = width / MAX;
  background(255);
  smooth();
  HIRAGINO10 = loadFont("HiraginoSans-W0-10.vlw");
  HIRAGINO20 = loadFont("HiraginoSans-W0-20.vlw");

  PERCEPTRON = new Perceptron(0.5, 0.5, 0.5);
}

void draw() {
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

void drawPerceptron(float x1, float x2, float size) {
  if (PERCEPTRON.run(x1, x2)) {
    fill(COLOR_TRUE);
  } else {
    fill(COLOR_FALSE);
  }
  ellipse(x1 * SCALE, -x2 * SCALE, size, size);
}

void drawAxis() {
  stroke(150);
  line(-width / 2, 0, width / 2, 0);
  line(0, -height / 2, 0, height / 2);
  textFont(HIRAGINO20);
  textSize(20);
  fill(100);
  text("x1", width / 2 - 30, -5);
  text("x2", 5, -height / 2 + 30);
}

void drawParameters() {
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

void mouseMoved() {
  switch (MOUSE_X_ASSIGN) {
    case "weight1":
      PERCEPTRON.weight1(5 * (mouseX - width / 2) / float(width));
      break;
    case "threshold":
      PERCEPTRON.threshold(5 * (mouseX - width / 2) / float(width));
      break;
    default:
      break;
  }
  switch (MOUSE_Y_ASSIGN) {
    case "weight2":
      PERCEPTRON.weight2(-5 * (mouseY - height / 2) / float(height));
      break;
    default:
      break;
  }
}

void keyPressed() {
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
