package net.eisele.example.resourcebundletricks.view;

import java.io.Serializable;
import java.util.Collections;
import java.util.LinkedHashMap;
import java.util.Locale;
import java.util.Map;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.enterprise.context.SessionScoped;
import javax.faces.context.FacesContext;
import javax.faces.event.ValueChangeEvent;
import javax.inject.Named;

/**
 * Switch Locale with JSF Context
 *
 * @author eiselem
 */
@Named
@SessionScoped
public class LanguageSwitch implements Serializable {

    private static final long serialVersionUID = 1L;
    private String localeCode;
    private final static Map<String, Object> countries;
    static final Logger LOGGER = Logger.getLogger(LanguageSwitch.class.getName());

    static {
        Map<String, Object> buildCountries = new LinkedHashMap<String, Object>();
        buildCountries.put("English", Locale.ENGLISH);
        buildCountries.put("German", Locale.GERMAN);
        buildCountries.put("Italian", Locale.ITALIAN);
        countries = Collections.unmodifiableMap(buildCountries);
    }

    /**
     * Countries Map
     *
     * @return
     */
    public Map<String, Object> getCountriesInMap() {
        return countries;
    }

    /**
     * Get Locale
     *
     * @return String
     */
    public String getLocaleCode() {
        return localeCode;
    }

    /**
     * Set new Locale
     *
     * @param localeCode
     */
    public void setLocaleCode(String localeCode) {
        LOGGER.log(Level.INFO, "setLocaleCode {0}", localeCode.toString());
        this.localeCode = localeCode;
        changeLocaleCode(localeCode);
    }

    /**
     * ValueChangeListener
     *
     * @param e
     */
    public void countryLocaleCodeChanged(ValueChangeEvent e) {
        String newLocaleValue = e.getNewValue().toString();
        LOGGER.log(Level.INFO, "New Locale {0}", newLocaleValue);
        changeLocaleCode(newLocaleValue);

    }

    private void changeLocaleCode(String localCode) {
        //loop country map to compare the locale code
        for (Map.Entry<String, Object> entry : countries.entrySet()) {
            if (entry.getValue().toString().equals(localCode)) {
                FacesContext.getCurrentInstance()
                        .getViewRoot().setLocale((Locale) entry.getValue());
            }
        }

    }
}
