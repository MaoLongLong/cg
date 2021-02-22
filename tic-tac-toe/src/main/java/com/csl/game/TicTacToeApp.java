package com.csl.game;

import cn.hutool.core.io.resource.ResourceUtil;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

/**
 * @author MaoLongLong
 * @date 2021-02-22 15:40:43
 */
public class TicTacToeApp extends Application {

    @Override
    public void start(Stage stage) throws Exception {
        Parent root = FXMLLoader.load(ResourceUtil.getResource("com/csl/game/tic-tac-toe.fxml"));
        stage.setScene(new Scene(root));
        stage.setResizable(false);
        stage.setTitle("Tic-Tac-Toe");
        stage.show();
    }

    public static void main(String[] args) {
        launch(args);
    }
}
