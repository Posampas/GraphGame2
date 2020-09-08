package GraphGame.GUI;

import GraphGame.Model.Base;
import GraphGame.Model.Game;
import javafx.animation.AnimationTimer;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.control.MenuItem;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.Pane;


public class ApplicationController {

    private ProgamState state;
    private Game game;


    @FXML
    MenuItem newGameButton;

    @FXML
    public void newGameButtonMenuFuction(){
        newGame();
    }


    @FXML
    MenuItem conectBasesMenuItem;

    @FXML
    public void test() {
        System.out.println(game.toString());
    }

    @FXML
    Pane pane;
    Movement timer;

    //    private Game game;
    private class Movement extends AnimationTimer {
        private long FRAMES_PER_SECOND = 1L;

        private long INTERVAL = 1000000000L / FRAMES_PER_SECOND;

        private long last = 0L;
        private int tick = 0;


        @Override
        public void handle(long now) {
            if (now - last > INTERVAL) {
                step();
                last = now;
            }

        }

        public void setTick() {
            tick++;
        }

        public int getTick() {
            return tick;
        }

        public void resetTick() {
            tick = 0;
        }

    }


    @FXML
    public void initialize() {
        timer = new Movement();
        newGame();

    }


    public void newGame() {
        pane.getChildren().clear();
        state = ProgamState.SIMULATION;
        timer.start();
        game = new Game(pane);
        game.inintialize(10);
    }

    @FXML
    public void getClick(MouseEvent event) {
        double x = event.getX();
        double y = event.getY();

        if (baseFirst == null) {
            baseFirst = game.loopThroghBasesAndFindClickedOne(x, y);
//            System.out.println(baseFirst.getOwner() != null ? baseFirst.getOwner(): "Neutral");
        } else {
            if (baseSecond == null) {
                baseSecond = game.loopThroghBasesAndFindClickedOne(x, y);

            }
        }

        if (baseSecond != null && baseFirst != null) {
            if (baseSecond.equals(baseFirst)) {
                baseSecond = null;

            } else {
                game.connectBases(baseFirst, baseSecond);
                baseFirst = null;
                baseSecond = null;
            }

        }


    }

    Base baseFirst = null;
    Base baseSecond = null;

    public void mouseDragged(MouseEvent event) {
        double x = event.getX();
        double y = event.getY();


    }

    public void step() {
        if (game.isGameStilOn()) {
            game.step();
        } else {
            Label winnerLabel = new Label("Winner is " + game.getWinner());
            pane.getChildren().add(winnerLabel);

        }
    }


}
