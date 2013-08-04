package net.eisele.example.resourcebundletricks.domain;

import javax.annotation.PostConstruct;
import javax.ejb.Singleton;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

/**
 *
 * @author eiselem
 */
@Singleton
//@Startup 
public class PreLoader {

  @PersistenceContext
  EntityManager em;

  @PostConstruct
  public void preLoadData() {
    String key;
    String de = "de";
    String en = "en";
    String it = "it";

    key = "welcome.db";
    createResourceEntity(de, key, "Hallo aus der Datenbank");
    createResourceEntity(en, key, "Hello from DB");
    createResourceEntity(it, key, "Ciao da DB");

    key = "welcome.message";
    createResourceEntity(de, key, "Hallo mondo tedesco...");
    createResourceEntity(en, key, "Hello World");
    createResourceEntity(it, key, "Ciao mondo");

    key = "welcome.name";
    createResourceEntity(de, key, "Hello {0} tedesco!");
    createResourceEntity(en, key, "Hello {0}!");
    createResourceEntity(it, key, "Ciao {0}!");

    key = "welcome.language";
    createResourceEntity(de, key, "Lingua in tedesco");
    createResourceEntity(en, key, "Language");
    createResourceEntity(it, key, "Lingua");

    key = "welcome.404";
    createResourceEntity(de, key, "Page not found in tedesco");
    createResourceEntity(en, key, "Page not found");
    createResourceEntity(it, key, "Pagina non trovata");

    key = "welcome.title";
    createResourceEntity(de, key, "Welcome to i18n example in tedesco");
    createResourceEntity(en, key, "Welcome to i18n example");
    createResourceEntity(it, key, "Benvenuto nell'esempio i18n");
  }

  protected void createResourceEntity(String locale, String key, String value) {
    ResourceEntity re = new ResourceEntity();
    re.setKey(key);
    re.setValue(value);
    re.setLocale(locale);
    em.persist(re);
  }
}
