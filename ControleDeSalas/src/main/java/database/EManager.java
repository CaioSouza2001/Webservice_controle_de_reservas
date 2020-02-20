package database;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

public class EManager implements java.io.Serializable {

    // Para inicializar corertamente o Emanager, na hora de configurar a PU
    // colocar ?useTimezone=true&serverTimezone=UTC na conexão   
    // this.em = Persistence.createEntityManagerFactory("ControleSalasPU").createEntityManager();

    private static EManager instance = null;
    private static EntityManager em = null;

    public EManager() {
    }

    public static EntityManager getInstance() {
        if (em == null) {
            em = Persistence.createEntityManagerFactory("ControleSalasPU").createEntityManager();
        }

        return em;
    }
}
