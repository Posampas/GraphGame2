package GraphGame.Model;

import GraphGame.MathFunctions.Mathematics;
import javafx.scene.control.Label;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;

public class Base {
    private Circle baseShape;
    private Label populationLablel;
    private int index;
    private int population;
    private int popGrowth;
    private Color baseColor;
    private Player owner;

    public Base(int index, double x, double y) {
        this.index = index;
        popGrowth = 1;
        population = 10;
        baseShape = new Circle(x, y, 15, Color.GRAY);
        populationLablel = new Label("" + population);
        owner = null;

    }

    public void setOwner(Player owner) {
        this.owner = owner;
        setColor(owner.getColor());
    }

    public Circle getBaseShape() {
        return baseShape;
    }

    double getXCoordinates() {
        return baseShape.getCenterX();
    }

    double getYCoordinates() {
        return baseShape.getCenterY();
    }

    boolean isClikedOn(double x, double y) {

        return Mathematics.calculateDistance(getXCoordinates(), getYCoordinates(), x, y) < 15;
    }

    int getIndex() {
        return index;
    }


    Label getPopulationLablel() {
        return populationLablel;
    }

    void updatePopulationCount(int popChange) {

        population += popChange;
        if (population < 0) population = -1;
        populationLablel.setText("" + population);

    }

    public void setColor(Color color) {
        baseShape.setFill(color);

    }

    public Player getOwner() {
        return owner;
    }

    int getPopulation() {
        return population;
    }

    public String toString() {
        return " Base " + index + " " + owner;
    }
}
