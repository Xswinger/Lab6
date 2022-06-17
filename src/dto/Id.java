package dto;

import java.util.HashSet;
import java.util.Set;

public class Id {
    private static long id = 0;
    private static final Set<Long> idSet = new HashSet<>();

    public static void zeroingId() {
        Id.id = 0;
    }

    public static void zeroingIdSet() {
        Id.idSet.clear();
    }

    public static long addId() {
        while (!idSet.add(id)) {
            Id.increaseId();
        }
        return id;
    }

    public static void increaseId() {
        Id.id += 1;
    }

    public static long getId() {
        return id;
    }

    public static Set<Long> getIdSet() {
        return idSet;
    }
}
