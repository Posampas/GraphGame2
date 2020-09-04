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
    private int degree;
    private int popGrowth;

    public Base(int index, double x, double y) {
        this.index = index;
        popGrowth = 1;
        degree = 0;
        population = 10;
        baseShape = new Circle(x, y, 15, Color.GRAY);
        populationLablel = new Label("" + population);

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

    int getIndex(){
        return index;
    }

    void decrementDegree(){
        degree--;
    }
    void  incrementDegree(){
        degree++;
    }
    Label getPopulationLablel(){
        return populationLablel;
    }

    void updatePopulationCount(){
        population = population +  popGrowth + degree;
        populationLablel.setText("" + population);
    }
}
