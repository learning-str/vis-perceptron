Perceptron PERCEPTRON;
float MAX = 5;
float SCALE = 1;

void setup() {
  size(500, 500, P2D);
  SCALE = width / MAX;
  background(255 / 2);
  smooth();

  PERCEPTRON = new Perceptron(0.5, 0.5, 0.5);
}

void draw() {
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

void drawPerceptron(float x1, float x2, float size) {
  if (PERCEPTRON.run(x1, x2)) {
    fill(0);
  } else {
    fill(255);
  }
  ellipse(x1 * SCALE, -x2 * SCALE, size, size);
}

void drawAxis() {
  stroke(255, 0, 0);
  line(-width / 2, 0, width / 2, 0);
  line(0, -height / 2, 0, height / 2);
  textSize(10);
  text("x1", 0, 0);
}

void drawParameter() {

}

void keyPressed() {
  if (key == ' ') {
    background(255 / 2);
  }
}
