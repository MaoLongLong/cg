package com.csl.game.ui.service;

import com.csl.game.core.MonteCarloTreeNode;
import com.csl.game.core.MonteCarloTreeSearch;
import com.csl.game.core.TicTacToeAction;
import com.csl.game.core.TicTacToeState;
import com.csl.game.model.Consts;

/**
 * @author MaoLongLong
 * @date 2021-02-22 16:16:02
 */
public class TicTacToeService {

    private TicTacToeState state;

    public void init() {
        state = new TicTacToeState(Consts.X);
    }

    public int getGameResult() {
        return state.getGameResult();
    }

    public boolean isVisited(int row, int col) {
        return state.getBoard()[row][col] != 0;
    }

    public void set(int row, int col) {
        state = state.move(new TicTacToeAction(row, col, state.getNextMove()));
    }

    public TicTacToeAction get() {
        MonteCarloTreeSearch mcts = new MonteCarloTreeSearch(state);
        MonteCarloTreeNode node = mcts.bestAction(2000);
        TicTacToeAction action = ((TicTacToeState) node.getState()).getCurrentAction();
        state = state.move(action);
        return action;
    }
}
