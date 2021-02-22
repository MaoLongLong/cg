package com.csl.game.controller;

import com.csl.game.core.TicTacToeAction;
import com.csl.game.model.Consts;
import com.csl.game.service.TicTacToeService;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.TextArea;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.GridPane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;
import javafx.scene.shape.Polyline;

import java.text.DecimalFormat;

/**
 * @author MaoLongLong
 * @date 2021-02-22 15:51:56
 */
public class TicTacToeController {

    @FXML
    private GridPane board;

    @FXML
    private Button choiceBtn;

    @FXML
    private Button startBtn;

    @FXML
    private Button confirmBtn;

    @FXML
    private Button cancelBtn;

    @FXML
    private Button endBtn;

    @FXML
    private TextArea log;

    private boolean aiFirstHand = true;

    private final TicTacToeService service = new TicTacToeService();

    private int tempRow;
    private int tempCol;

    private DecimalFormat df = new DecimalFormat("#.##%");

    public void initialize() {
        log.appendText("Hello World!\n");
        board.setDisable(true);
        confirmBtn.setDisable(true);
        cancelBtn.setDisable(true);
        endBtn.setDisable(true);
    }

    public void clearLog() {
        log.clear();
    }

    public void changeHands() {
        aiFirstHand = !aiFirstHand;
        if (aiFirstHand) {
            choiceBtn.setText("AI先手");
        } else {
            choiceBtn.setText("AI后手");
        }
    }

    public void startGame() {
        board.getChildren().remove(1, board.getChildren().size());
        service.init();
        if (aiFirstHand) {
            TicTacToeAction action = service.get();
            drawX(action.getRow(), action.getCol());
            log.appendText("AI当前胜率：" + df.format(service.getWinRate()) + "\n");
        }
        choiceBtn.setDisable(true);
        startBtn.setDisable(true);
        endBtn.setDisable(false);
        board.setDisable(false);
    }

    public void endGame() {
        choiceBtn.setDisable(false);
        startBtn.setDisable(false);
        board.setDisable(true);
        confirmBtn.setDisable(true);
        cancelBtn.setDisable(true);
        endBtn.setDisable(true);
    }

    public void handleBoardClick(MouseEvent event) {
        int row = (int) (event.getY() / 240);
        int col = (int) (event.getX() / 240);
        if (service.isVisited(row, col)) {
            return;
        }
        tempRow = row;
        tempCol = col;
        if (aiFirstHand) {
            drawO(row, col);
        } else {
            drawX(row, col);
        }
        board.setDisable(true);
        confirmBtn.setDisable(false);
        cancelBtn.setDisable(false);
    }

    private void drawO(int row, int col) {
        Circle circle = new Circle();
        circle.setFill(null);
        circle.setStroke(Color.SKYBLUE);
        circle.setRadius(100);
        circle.setStrokeWidth(6);
        board.add(circle, col, row);
    }

    private void drawX(int row, int col) {
        Polyline polyline = new Polyline();
        polyline.setStrokeWidth(6);
        polyline.setStroke(Color.PINK);
        polyline.getPoints().addAll(0.0, 0.0, 200.0, 200.0,
                100.0, 100.0, 0.0, 200.0, 200.0, 0.0);
        board.add(polyline, col, row);
    }

    public void cancel() {
        board.getChildren().remove(board.getChildren().size() - 1);
        board.setDisable(false);
        confirmBtn.setDisable(true);
        cancelBtn.setDisable(true);
    }

    public void confirm() {
        service.set(tempRow, tempCol);
        if (checkGameResult()) {
            endGame();
            return;
        }
        TicTacToeAction action = service.get();
        if (aiFirstHand) {
            drawX(action.getRow(), action.getCol());
        } else {
            drawO(action.getRow(), action.getCol());
        }
        log.appendText("AI当前胜率：" + df.format(service.getWinRate()) + "\n");
        if (checkGameResult()) {
            endGame();
            return;
        }
        board.setDisable(false);
        confirmBtn.setDisable(true);
        cancelBtn.setDisable(true);
    }

    private boolean checkGameResult() {
        int result = service.getGameResult();
        if (result != Consts.NONE) {
            if (result == Consts.X) {
                log.appendText("先手方胜\n");
            } else if (result == Consts.O) {
                log.appendText("后手方胜\n");
            } else {
                log.appendText("平局\n");
            }
            return true;
        }
        return false;
    }
}
