package uk.co.umbaska.skript;

import ch.njol.skript.lang.Expression;
import ch.njol.skript.lang.SkriptParser;
import uk.co.umbaska.registrations.UExpression;

import java.util.Arrays;
import java.util.List;
import java.util.function.Function;
import java.util.regex.MatchResult;

/**
 * @author Andrew Tran
 */
public class ParseResult {
    SkriptParser.ParseResult parseResult;

    public ParseResult(SkriptParser.ParseResult parseResult) {
        this.parseResult = parseResult;
    }

    public String getExpr(){
        return parseResult.expr;
    }

    public UExpression<?>[] getExpressions(){
        return Arrays.stream(parseResult.exprs).map((Function<Expression<?>, ? extends UExpression<?>>) UExpression::new).toArray(UExpression[]::new);
    }

    public int getMark(){
        return parseResult.mark;
    }

    public List<MatchResult> getMatchResults(){
        return parseResult.regexes;
    }

    public MatchResult getMatchResult(int index){
        return getMatchResults().get(index);
    }
}
