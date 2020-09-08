package GraphGame.Model;

import javafx.scene.paint.Color;
import java.util.ArrayList;
import java.util.Arrays;

public class Player {
    private ArrayList<Base> bases = new ArrayList<>();
    private Color color;
    private String description;
    int[][] adjacencyMatrix;

    public Player(Base base, Color color, String description, int[][] adjacencyMatrix){
        this.color = color;
        this.description = description;
        this.adjacencyMatrix = adjacencyMatrix;
        addBase(base);
    }

    public Color getColor() {
        return color;
    }

    public void removeBase(Base base){
        if (bases.contains(base)){
            bases.remove(base);
        }

        for (int i = 0; i <adjacencyMatrix.length ; i++) {
            adjacencyMatrix[base.getIndex()][i] = 0;
        }
    }

    public void addBase(Base base){
        if (!bases.contains(base)){
            bases.add(base);
            base.setOwner(this);
            base.setColor(color);
            adjacencyMatrix[base.getIndex()][base.getIndex()] = 1;
        }
    }
    int getInDegInCertainBase(Base base){
        int index = base.getIndex();
        int count = 0;

        for (int i = 0; i <adjacencyMatrix.length ; i++) {
            if (adjacencyMatrix[i][index] == 1){
                count   ++;
            }
        }

        return count;
    }

    int getOutDegInCertainBase(Base base ) {

        int index = base.getIndex();
        int count = 0;
        for (int i = 0; i <adjacencyMatrix.length ; i++) {
            if (i == index) continue;
            if (adjacencyMatrix[index][i] == 1){
                count   ++;
            }
        }
        return  count;
    }

    public void connectBase(Base first, Base second){
        adjacencyMatrix[first.getIndex()][second.getIndex()] = 1;
    }

    void disconnectBase(Base first , Base second){
        adjacencyMatrix[first.getIndex()][second.getIndex()] =0;
    }

    int getNumberOfBases(){
        return bases.size();
    }

    ArrayList<Base> getBases(){
        return bases;
    }

    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append(description + '\n');
        for (int[] arr : adjacencyMatrix) {
            sb.append(Arrays.toString(arr) + '\n');
        }
        return sb.toString();
    }


}
