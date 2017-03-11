package uk.co.umbaska.skript;

import ch.njol.util.Kleenean;

/**
 * @author Andrew Tran
 */
public enum Delay {
    FALSE, TRUE, UNKNOWN;
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
