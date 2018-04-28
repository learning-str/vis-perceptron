class Perceptron {
  private float weight1;
  private float weight2;
  private float threshold;
  Perceptron(float weight1, float weight2, float threshold) {
    this.weight1 = weight1;
    this.weight2 = weight2;
    this.threshold = threshold;
  }

  boolean run(float x1, float x2) {
    if (weight1 * x1 + weight2 * x2 <= threshold) {
      return false;
    }
    return true;
  }

  void threshold(float threshold) { this.threshold = threshold; }
  float weight1() { return weight1; }
  float weight2() { return weight2; }
  float threshold() { return threshold; }
}
