package uk.co.umbaska.registrations;

import uk.co.umbaska.skript.ExpressionManager;

/**
 * Interface that specifies that the class implementing can provide an {@link ExpressionManager}
 * @author Andrew Tran
 */
public interface ExpressionManagerProvider {
    ExpressionManager getExpressionManager();
    ExpressionManager exp();
    Boolean isUsingExpressionManager();
}
