package net.eisele.example.resourcebundletricks.bundles;

import java.util.Locale;
import java.util.ResourceBundle;
import javax.enterprise.inject.Produces;
import javax.faces.context.FacesContext;

/**
 *
 * @author eiselem
 */
public class MessageBundleProvider {

    @Produces
    @MessageBundle
    public ResourceBundle getMesssageBundle() {

        FacesContext context = FacesContext.getCurrentInstance();
        Locale locale = context.getViewRoot().getLocale();
        return ResourceBundle.getBundle("Messages", locale);
    }

    @Produces
    @ErrorBundle
    public ResourceBundle getErrorBundle() {

        FacesContext context = FacesContext.getCurrentInstance();
        Locale locale = context.getViewRoot().getLocale();
        return ResourceBundle.getBundle("Errors", locale);

    }
}
