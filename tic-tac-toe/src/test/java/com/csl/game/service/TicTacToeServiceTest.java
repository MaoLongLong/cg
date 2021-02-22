package com.csl.game.service;

import com.csl.game.core.TicTacToeAction;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertNotEquals;

/**
 * @author MaoLongLong
 * @date 2021-02-22 16:34:32
 */
class TicTacToeServiceTest {

    @Test
    void mctsTest() {
        TicTacToeService service = new TicTacToeService();
        service.init();

        service.set(1, 1);
        service.set(2, 1);
        TicTacToeAction action = service.get();

        assertNotEquals(1, action.getRow());
        assertNotEquals(1, action.getCol());
    }
}