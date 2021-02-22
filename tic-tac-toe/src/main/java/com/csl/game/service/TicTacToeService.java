package com.csl.game.service;

import com.csl.game.core.MonteCarloTreeNode;
import com.csl.game.core.MonteCarloTreeSearch;
import com.csl.game.core.TicTacToeAction;
import com.csl.game.core.TicTacToeState;
import com.csl.game.model.Consts;
import lombok.Getter;

/**
 * @author MaoLongLong
 * @date 2021-02-22 16:16:02
 */
public class TicTacToeService {

    private TicTacToeState state;

    @Getter
    private double winRate;

    public void init() {
        state = new TicTacToeState(Consts.X);
    }

    public int getGameResult() {
        return state.getGameResult();
    }

    public boolean isVisited(int row, int col) {
        return state.isVisited(row, col);
    }

    public void set(int row, int col) {
        state = state.move(new TicTacToeAction(row, col, state.getNextMove()));
    }

    public TicTacToeAction get() {
        MonteCarloTreeSearch mcts = new MonteCarloTreeSearch(state);
        MonteCarloTreeNode node = mcts.bestAction(4000);
        winRate = node.winRate();
        TicTacToeAction action = ((TicTacToeState) node.getState()).getCurrentAction();
        state = state.move(action);
        return action;
    }
}
