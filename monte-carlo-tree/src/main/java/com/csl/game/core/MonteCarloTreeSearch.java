package com.csl.game.core;

/**
 * @author MaoLongLong
 * @date 2021-02-22 15:46:44
 */
public class MonteCarloTreeSearch {

    private final MonteCarloTreeNode root;

    public MonteCarloTreeSearch(MonteCarloTreeNode root) {
        this.root = root;
    }

    public MonteCarloTreeSearch(BaseState state) {
        this.root = new MonteCarloTreeNode(state, null);
    }

    /**
     * @param simulationsNumber 模拟次数
     * @return 最优节点
     */
    public MonteCarloTreeNode bestAction(int simulationsNumber) {
        for (int i = 0; i < simulationsNumber; i++) {
            MonteCarloTreeNode node = treePolicy();
            node.back(node.rollOut());
        }
        return root.bestChild(0);
    }

    protected MonteCarloTreeNode treePolicy() {
        MonteCarloTreeNode current = root;
        while (!current.isTerminalNode()) {
            if (!current.isFullyExpanded()) {
                return current.expand();
            } else {
                current = current.bestChild();
            }
        }
        return current;
    }
}
