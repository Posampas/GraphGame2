package GraphGame.Model;

import javafx.scene.control.Label;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;

import java.util.ArrayList;
import java.util.Arrays;

public class Game {


    private int[][] adjacencyMatrix;
    private Base[] bases;
    private Pane world;
    private Player playerA;
    private Player playerB;
    private Player winner;
    private ArrayList<Edge> edgeList = new ArrayList<>();
    private ArrayList<Edge> edgetsToRemove = new ArrayList<>();
    private boolean isGameStilOn = true;


    public Game(Pane world) {
        this.world = world;


    }

    public void inintialize(int numberOfBases) {
        isGameStilOn = true;
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
        addPlayers(bases[0], bases[numberOfBases - 1], numberOfBases);
    }

    private void addEdge(Edge edge) {
        edgeList.add(edge);
        world.getChildren().add(edge.getEdgeShape());
    }

    private void removeEdge(Edge edge) {
        adjacencyMatrix[edge.getHead().getIndex()][edge.getTail().getIndex()] = 0;
        edgeList.remove(edge);
        world.getChildren().remove(edge.getEdgeShape());

    }


    private void addPlayers(Base first, Base second, int numberOfBases) {
        playerA = new Player(first, Color.BLUE, "BLUE", crateEmptyAdjacencyMatrix(numberOfBases));
        playerB = new Player(second, Color.RED, "RED", crateEmptyAdjacencyMatrix(numberOfBases));
    }

    private int[][] crateEmptyAdjacencyMatrix(int numberOfBases) {
        return new int[numberOfBases][numberOfBases];
    }

    public Base loopThroghBasesAndFindClickedOne(double x, double y) {

        for (Base b : bases) {
            if (b.isClikedOn(x, y)) {
                return b;
            }
        }
        return null;
    }

    public void connectBases(Base first, Base second) {

        if ((first.getOwner() != null) && adjacencyMatrix[first.getIndex()][second.getIndex()] == 0) {
            first.getOwner().connectBase(first, second);
            adjacencyMatrix[first.getIndex()][second.getIndex()] = 1;
            Edge edge = new Edge(first, second, first.getOwner().getColor());
            addEdge(edge);
        }
    }


    public void step() {

        endGameConditionCheck();
        updateGameStatus();
        artificialIntelligence();


    }


    private void updateGameStatus() {
        for (Base b : bases) {

            calculatePopulationChange(b);
            if (b.getPopulation() <= 0) {

                decideWhatToDoWhenThePopulationOfTheBaseDropsBelowZero(b);


            }

        }
    }

    private void decideWhatToDoWhenThePopulationOfTheBaseDropsBelowZero(Base b) {

        if (b.getOwner() != null) {
            for (Edge e : edgeList) {
                if (e.getHead().equals(b)) {
                    edgetsToRemove.add(e);
                    b.getOwner().disconnectBase(b, e.getTail());
                }
            }

            for (Edge e : edgetsToRemove) {
                removeEdge(e);
            }
        }

        Player player = withPlayerShouldGetTheseBase(b.getIndex());
        if (player != null) {
            Player currentOwener = b.getOwner();
            if (currentOwener != null) {
                currentOwener.removeBase(b);
            }
            player.addBase(b);
        }
    }

    private void calculatePopulationChange(Base b) {

        int playerAInDeg = playerA.getInDegInCertainBase(b);
        int playerAOutDeg = playerA.getOutDegInCertainBase(b);

        int playerBInDeg = playerB.getInDegInCertainBase(b);
        int playerBOutDeg = playerB.getOutDegInCertainBase(b);

        int populationChange = 0;


        if (b.getOwner() == (null)) {
            populationChange = -playerAInDeg - playerBInDeg;
        } else if (b.getOwner().equals(playerB)) {
            populationChange = playerBInDeg - playerBOutDeg - playerAInDeg;
        } else {
            populationChange = playerAInDeg - playerAOutDeg - playerBInDeg;
        }
        System.out.println(b.getOwner() + " " + populationChange);

        b.updatePopulationCount(populationChange);

    }


    private void endGameConditionCheck() {

        if (playerA.getNumberOfBases() == 0) {
            isGameStilOn = false;
            winner = playerB;
        } else if (playerB.getNumberOfBases() == 0) {
            isGameStilOn = false;
            winner = playerA;
        }
    }

    private Player withPlayerShouldGetTheseBase(int baseIndex) {
        int playerACount = 0;
        int playerBCount = 0;
        for (int i = 0; i < adjacencyMatrix.length; i++) {
            if (adjacencyMatrix[i][baseIndex] == 1) {
                if (bases[i].getOwner().equals(playerA)) {
                    playerACount++;
                } else if (bases[i].getOwner().equals(playerB)) {
                    playerBCount++;
                }
            }
        }

        if (playerACount < playerBCount) {
            return playerB;
        } else if (playerACount > playerBCount) {
            return playerA;
        } else {
            return null;
        }
    }

    public boolean isGameStilOn() {
        return isGameStilOn;
    }

    public Player getWinner() {
        return winner;
    }

    public String toString() {
        StringBuilder sb = new StringBuilder();
        for (int[] arr : adjacencyMatrix) {
            sb.append(Arrays.toString(arr) + '\n');
        }
        return sb.toString();
    }

    public void artificialIntelligence() {

        
    }

}
