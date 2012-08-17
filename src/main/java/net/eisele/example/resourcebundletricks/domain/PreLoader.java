package net.eisele.example.resourcebundletricks.domain;

import java.util.Locale;
import javax.annotation.PostConstruct;
import javax.ejb.Singleton;
import javax.ejb.Startup;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

/**
 *
 * @author eiselem
 */
@Singleton
@Startup
public class PreLoader {

    @PersistenceContext
    EntityManager em;

    @PostConstruct
    public void preLoadData() {

        ResourceEntity de = new ResourceEntity();
        de.setKey("welcome.db");
        de.setValue("Hallo aus der Datenbank");
        de.setLocale(Locale.GERMAN);

        ResourceEntity en = new ResourceEntity();
        en.setKey("welcome.db");
        en.setValue("Hello from DB");
        en.setLocale(Locale.ENGLISH);

        em.persist(de);
        em.persist(en);




    }
}
