
package database;

import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

public class DBUtil {
    private static final EntityManagerFactory emf =
        Persistence.createEntityManagerFactory("Week09LabPU");

    public static EntityManagerFactory getEmFactory() {
        return emf;
    }
}
