package uk.co.umbaska.skript;

import ch.njol.skript.lang.Expression;
import ch.njol.skript.lang.SkriptParser;
import uk.co.umbaska.registrations.UExpression;

import java.util.Arrays;
import java.util.List;
import java.util.function.Function;
import java.util.regex.MatchResult;

/**
 * Class that represents the results of a parsing in {@link ch.njol.skript.Skript}
 * @author Andrew Tran
 */
public class ParseResult {
    SkriptParser.ParseResult parseResult;

    /**
     * Constructs an {@link ParseResult} from a {@link ch.njol.skript.Skript} {@link ch.njol.skript.lang.SkriptParser.ParseResult}
     * @param parseResult the constructed ParseResult
     */
    public ParseResult(SkriptParser.ParseResult parseResult) {
        this.parseResult = parseResult;
    }

    /**
     * Gets the expr in a ParseResult
     * @return the expr in a ParseResult
     */
    public String getExpr(){
        return parseResult.expr;
    }

    /**
     * Gets the expressions in a ParseResult
     * @return the expressions in a ParseResult
     */
    public UExpression<?>[] getExpressions(){
        return Arrays.stream(parseResult.exprs).map((Function<Expression<?>, ? extends UExpression<?>>) UExpression::new).toArray(UExpression[]::new);
    }

    /**
     * Gets the matched pattern in a ParseResult
     * @return the matched pattern in a ParseResult
     */
    public int getMark(){
        return parseResult.mark;
    }

    /**
     * Gets the MatchResults in a ParseResult
     * @return the MathResults in a ParseResult
     */
    public List<MatchResult> getMatchResults(){
        return parseResult.regexes;
    }

    /**
     * Gets the specific {@link MatchResult} for an {@code index}
     * @param index the index to get the MatchResult of
     * @return the MatchResult for the index
     */
    public MatchResult getMatchResult(int index){
        return getMatchResults().get(index);
    }
}
