package dto;

import java.util.HashSet;
import java.util.Set;

public class IdGenerator {

    private static IdGenerator instance = null;
    public static IdGenerator getInstance() {
        if (instance == null) {
            instance = new IdGenerator();
        }
        return instance;
    }
    private long id = 0;
    private final Set<Long> idSet = new HashSet<>();

    public void zeroingId() {
        id = 0;
    }

    public void zeroingIdSet() {
        idSet.clear();
    }

    public long addId() {
        while (!idSet.add(id)) {
            increaseId();
        }
        return id;
    }

    public void increaseId() {
        id += 1;
    }

    public long getId() {
        return id;
    }

    public Set<Long> getIdSet() {
        return idSet;
    }

}
