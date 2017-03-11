package uk.co.umbaska.registrations;

import uk.co.umbaska.skript.ExpressionManager;

/**
 * @author Andrew Tran
 */
public interface ExpressionManagerProvider {
    ExpressionManager getExpressionManager();
    ExpressionManager exp();
    Boolean isUsingExpressionManager();
}
