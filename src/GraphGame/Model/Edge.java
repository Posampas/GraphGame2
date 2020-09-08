package GraphGame.Model;

import javafx.scene.paint.Color;
import javafx.scene.shape.Line;

public class Edge {
    Base head;
    Base tail;
    Line edgeShape;
    Color edgeColor;

    public Edge(Base head, Base tail, Color color) {
        this.head = head;
        this.tail = tail;
        this.edgeColor = color;
        this.edgeShape = new Line(head.getXCoordinates(),head.getYCoordinates(),tail.getXCoordinates(),tail.getYCoordinates());
        setEdgeColor(color);

    }

    public Line getEdgeShape() {
        return edgeShape;
    }

    public void setEdgeColor(Color edgeColor) {
        this.edgeColor = edgeColor;
        edgeShape.setStroke(edgeColor);
    }

    public Base getHead() {
        return head;
    }

    public Base getTail() {
        return tail;
    }
}
