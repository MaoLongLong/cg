package com.csl.game.core;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.ToString;

import java.io.Serializable;

/**
 * @author MaoLongLong
 * @date 2021-02-22 15:56:53
 */
@Getter
@AllArgsConstructor
@ToString
public class TicTacToeAction extends BaseAction implements Serializable {

    private static final long serialVersionUID = -3084773267849613427L;

    private final int row;

    private final int col;

    private final int value;

    public static int changeHands(int current) {
        return -current;
    }
}
