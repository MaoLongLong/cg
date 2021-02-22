package com.csl.game.core;

import java.io.Serializable;
import java.util.List;

/**
 * @author MaoLongLong
 * @date 2021-02-22 15:45:17
 */
public abstract class BaseState implements Serializable {

    private static final long serialVersionUID = -7551124301970733503L;

    protected final int nextMove;

    public BaseState(int nextMove) {
        this.nextMove = nextMove;
    }

    public int getNextMove() {
        return nextMove;
    }

    /**
     * 通过动作改变棋盘状态
     *
     * @param o 尝试的动作
     * @return 执行后的状态
     */
    public abstract Object move(Object o);

    /**
     * 判断游戏是否结束
     *
     * @return 游戏是否结束 true(结束) false(没结束)
     */
    public abstract boolean isGameOver();

    /**
     * 获取所有可行的动作
     *
     * @return 动作集
     */
    public abstract List<? extends BaseAction> getLegalActions();

    /**
     * 返回博弈结果
     *
     * @return int
     */
    public abstract int getGameResult();
}
