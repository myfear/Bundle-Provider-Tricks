package net.eisele.example.resourcebundletricks.bundles;

import java.util.Locale;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.enterprise.inject.Produces;
import javax.faces.context.FacesContext;

/**
 *
 * @author eiselem
 */
public class MessageBundleProvider {

    static final Logger LOGGER = Logger.getLogger(MessageBundleProvider.class.getName());

    @Produces
    public @MessageBundle
    ResourceBundle getMesssageBundle() {
        FacesContext context = FacesContext.getCurrentInstance();
        Locale locale = context.getViewRoot().getLocale();
        LOGGER.log(Level.INFO, "getMesssageBundle() {0}", locale);
        return ResourceBundle.getBundle("Messages", locale);
    }

    @Produces
    public @ErrorBundle
    ResourceBundle getErrorBundle() {
        FacesContext context = FacesContext.getCurrentInstance();
        Locale locale = context.getViewRoot().getLocale();
        LOGGER.log(Level.INFO, "getErrorBundle() {0}", locale);
        return ResourceBundle.getBundle("Errors", locale);

    }
}
