package GraphGame.Model;

import javafx.scene.control.Label;
import javafx.scene.layout.Pane;
import javafx.scene.shape.Line;

public class Game {


    private int[][] adjacencyMatrix;
    private Base[] bases;
    private Pane world;


    public Game(Pane world) {
        this.world = world;

    }

    public void inintialize(int numberOfBases) {

        bases = new Base[numberOfBases];
        adjacencyMatrix = crateEmptyAdjacencyMatrix(numberOfBases);
        for (int i = 0; i < numberOfBases; i++) {

            double x = 60 * i + Math.random() * 60;
            double y = 20 + Math.random() * 300;
            Base b = new Base(i, x, y);
            bases[i] = b;
            world.getChildren().add(b.getBaseShape());
            Label l = b.getPopulationLablel();
            world.getChildren().add(l);
            l.setTranslateX(b.getXCoordinates() - 5);
            l.setTranslateY(b.getYCoordinates() + 20);

        }
    }

    private int[][] crateEmptyAdjacencyMatrix(int numberOfBases) {
        return new int[numberOfBases][numberOfBases];
    }

    public Base loopThroghBasesAndFindClickedOne(double x, double y) {

        for (Base basis : bases) {
            if (basis.isClikedOn(x, y)) {
                return basis;
            }
        }
        return null;
    }

    public void connectBases(Base first, Base second) {
        adjacencyMatrix[first.getIndex()][second.getIndex()] = 1;
        first.decrementDegree();
        second.incrementDegree();
//        Temporary line
        world.getChildren().add(new Line(first.getXCoordinates(),first.getYCoordinates(),
                second.getXCoordinates(),second.getYCoordinates()));
    }


    public void step(){
        for (Base b: bases) {
            b.updatePopulationCount();
        }
    }



}
