package uk.co.umbaska.skript;

import ch.njol.util.Kleenean;

/**
 * Represents the delay of something in a form similar to {@link Kleenean}
 * @author Andrew Tran
 */
public enum Delay {
    FALSE, TRUE, UNKNOWN;

    /**
     * Get the {@link Kleenean} from an {@link Delay}
     * @param kleenean the Kleenean to get a delay from
     * @return the Kleenean for a specified delay
     */
    public static Delay fromKleenean(Kleenean kleenean){
        if (kleenean.isTrue()){
            return TRUE;
        }else if (kleenean.isFalse()){
            return FALSE;
        }else{
            return UNKNOWN;
        }
    }
}
