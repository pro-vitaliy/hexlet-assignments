package exercise;

// BEGIN
public class Segment {
    private final Point beginPoint;
    private final Point endPoint;

    public Segment(Point beginPoint, Point endPoint) {
        this.beginPoint = beginPoint;
        this.endPoint = endPoint;
    }

    public Point getEndPoint() {
        return endPoint;
    }

    public Point getBeginPoint() {
        return beginPoint;
    }

    public Point getMidPoint() {
        int beginX = beginPoint.getX();
        int endX = endPoint.getX();

        int beginY = beginPoint.getY();
        int endY = endPoint.getY();

        return new Point((beginX + endX) / 2, (beginY + endY) / 2);
    }

}
// END
