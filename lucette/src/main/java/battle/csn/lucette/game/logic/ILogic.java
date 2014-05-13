package battle.csn.lucette.game.logic;

import battle.csn.lucette.game.board.IBoard;

import com.google.common.base.Function;

public interface ILogic
{
    int solve( IBoard<Integer> plateau, int alpha, int beta, Function<IBoard<Integer>, Integer> evaluate,
            boolean findMax, int deep );
}
