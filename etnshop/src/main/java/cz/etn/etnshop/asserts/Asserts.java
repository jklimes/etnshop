package cz.etn.etnshop.asserts;

import cz.etn.etnshop.exception.EtnShopException;

public final class Asserts {
    public static void notNull(Object o, String message) {
        if (o == null) {
            throw new EtnShopException(message);
        }
    }

    public static void notNullOrEmpty(String s, String message) {
        if (s == null || s.isEmpty()) {
            throw new EtnShopException(message);
        }
    }
}
