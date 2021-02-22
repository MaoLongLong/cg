package com.csl.game.core;

import cn.hutool.core.collection.CollUtil;
import cn.hutool.core.lang.Assert;
import cn.hutool.core.util.ObjectUtil;
import com.csl.game.model.Consts;
import lombok.Getter;

import java.util.List;

/**
 * @author MaoLongLong
 * @date 2021-02-22 15:59:13
 */
@SuppressWarnings("AlibabaUndefineMagicConstant")
public class TicTacToeState extends BaseState {

    private final int[][] board;

    @Getter
    private TicTacToeAction currentAction;

    public TicTacToeState(int[][] board, int nextMove, TicTacToeAction currentAction) {
        super(nextMove);
        this.currentAction = currentAction;
        // deep copy
        this.board = ObjectUtil.cloneByStream(board);
    }

    public TicTacToeState(int nextMove) {
        super(nextMove);
        this.board = new int[3][3];
    }

    @Override
    public TicTacToeState move(Object o) {
        Assert.isInstanceOf(TicTacToeAction.class, o);
        TicTacToeAction action = (TicTacToeAction) o;
        int[][] tempBoard = ObjectUtil.cloneByStream(board);
        tempBoard[action.getRow()][action.getCol()] = action.getValue();
        return new TicTacToeState(tempBoard, TicTacToeAction.changeHands(nextMove), action);
    }

    @Override
    public boolean isGameOver() {
        return getGameResult() != Consts.NONE;
    }

    @Override
    public List<TicTacToeAction> getLegalActions() {
        List<TicTacToeAction> legalActions = CollUtil.newArrayList();
        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 3; j++) {
                if (board[i][j] == 0) {
                    legalActions.add(new TicTacToeAction(i, j, nextMove));
                }
            }
        }
        return legalActions;
    }

    @Override
    public int getGameResult() {
        boolean flag = false;
        for (int i = 0; i < 3; i++) {
            int rowSum = 0;
            int colSum = 0;
            for (int j = 0; j < 3; j++) {
                if (board[i][j] == 0) {
                    flag = true;
                }
                rowSum += board[i][j];
                colSum += board[j][i];
            }
            if (anyEquals(3 * Consts.X, rowSum, colSum)) {
                return Consts.X;
            }
            if (anyEquals(3 * Consts.O, rowSum, colSum)) {
                return Consts.O;
            }
        }
        int trSum = board[0][0] + board[1][1] + board[2][2];
        int tlSum = board[0][2] + board[1][1] + board[2][0];
        if (anyEquals(3 * Consts.X, trSum, tlSum)) {
            return Consts.X;
        }
        if (anyEquals(3 * Consts.O, trSum, tlSum)) {
            return Consts.O;
        }
        return flag ? Consts.NONE : Consts.DRAW;
    }

    private boolean anyEquals(int expected, int... nums) {
        for (int num : nums) {
            if (expected == num) {
                return true;
            }
        }
        return false;
    }

    public boolean isVisited(int row, int col) {
        return board[row][col] != 0;
    }
}
