package net.eisele.example.resourcebundletricks.bundles;

import java.io.IOException;
import java.util.Enumeration;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.ListResourceBundle;
import java.util.Locale;
import java.util.Map;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.faces.context.FacesContext;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.persistence.TypedQuery;
import net.eisele.example.resourcebundletricks.domain.ResourceEntity;

/**
 * Simple DatabaseResourceBundle
 *
 * @author eiselem
 */
public class DatabaseResourceBundle extends ResourceBundle {

    private static EntityManagerFactory factory = Persistence.createEntityManagerFactory("resources");
    private EntityManager _entityManager = factory.createEntityManager();
    private Map<String, String> _values = new HashMap<String, String>();
    protected final static String BUNDLE_NAME = "net.eisele.example.resourcebundletricks.bundles";
    private final static Logger LOGGER = Logger.getLogger(DatabaseResourceBundle.class.getName());
    private Locale locale = FacesContext.getCurrentInstance().getViewRoot().getLocale();
    protected Control DB_CONTROL = new DBControl();

    /**
     * Public constructor setting the parent bundle
     */
    public DatabaseResourceBundle() {
        LOGGER.log(Level.FINE, "DatabaseResourceBundle()");
        setParent(ResourceBundle.getBundle(BUNDLE_NAME,
                FacesContext.getCurrentInstance().getViewRoot().getLocale(), DB_CONTROL));
    }

    public DatabaseResourceBundle(Locale locale) {
        LOGGER.log(Level.FINE, "DatabaseResourceBundle(Locale locale)");
        setParent(ResourceBundle.getBundle(BUNDLE_NAME, locale, DB_CONTROL));
    }

    @Override
    protected Object handleGetObject(String key) {
        LOGGER.log(Level.FINE, "handleGetObject() Locale {0} Key: {1} ", new Object[]{locale.toString(), key});
        return _values != null ? _values.get(key) : parent.getObject(key);



    }

    @Override
    public Enumeration<String> getKeys() {
        LOGGER.log(Level.FINE, "getKeys() Parent Locale {0} ", parent.getLocale());
        return parent.getKeys();
    }

    /**
     * The Control Callback.
     *
     * @see
     * http://docs.oracle.com/javase/6/docs/api/java/util/ResourceBundle.Control.html
     */
    protected class DBControl extends Control {

        @Override
        public ResourceBundle newBundle(String baseName, Locale locale, String format, ClassLoader loader, boolean reload)
                throws IllegalAccessException, InstantiationException, IOException {
            LOGGER.log(Level.INFO, "reload {0} ", reload);
            return new MyResources(locale);
        }

        /**
         * A simple ListResourceBundle
         */
        protected class MyResources extends ListResourceBundle {

            private Locale locale;

            /**
             * ResourceBundle constructor with locale
             *
             * @param locale
             */
            public MyResources(Locale locale) {
                this.locale = locale;
            }

            @Override
            protected Object[][] getContents() {
                TypedQuery<ResourceEntity> query = _entityManager.createNamedQuery("ResourceEntity.findForLocale", ResourceEntity.class);
                query.setParameter("locale", locale.getLanguage());

                List<ResourceEntity> resources = query.getResultList();

                Object[][] all = new Object[resources.size()][2];
                int i = 0;
                for (Iterator<ResourceEntity> it = resources.iterator(); it.hasNext();) {
                    ResourceEntity resource = it.next();
                    all[i] = new Object[]{resource.getKey(), resource.getValue()};
                    _values.put(resource.getKey(), resource.getValue());
                    i++;
                }
                return all;
            }
        }
    }
}
