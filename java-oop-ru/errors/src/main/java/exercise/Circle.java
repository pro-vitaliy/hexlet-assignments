package exercise;

// BEGIN
class Circle {
    private final Point point;
    private final int radius;

    Circle(Point point, int radius) {
        this.point = point;
        this.radius = radius;
    }

    public double getSquare() throws NegativeRadiusException {
        if (radius < 0) {
            throw new NegativeRadiusException("Radius is negative");
        }
        return Math.PI * radius * radius;
    }

    public int getRadius() {
        return radius;
    }
}
// END
