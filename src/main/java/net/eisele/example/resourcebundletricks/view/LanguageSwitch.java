package net.eisele.example.resourcebundletricks.view;

import java.io.Serializable;
import java.util.Collections;
import java.util.LinkedHashMap;
import java.util.Locale;
import java.util.Map;
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

    static {
        Map<String, Object> buildCountries = new LinkedHashMap<String, Object>();
        buildCountries.put("English", Locale.ENGLISH);
        buildCountries.put("German", Locale.GERMAN);
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
        this.localeCode = localeCode;
    }

    /**
     * ValueChangeListener
     *
     * @param e
     */
    public void countryLocaleCodeChanged(ValueChangeEvent e) {
        String newLocaleValue = e.getNewValue().toString();
        //loop country map to compare the locale code
        for (Map.Entry<String, Object> entry : countries.entrySet()) {
            if (entry.getValue().toString().equals(newLocaleValue)) {
                FacesContext.getCurrentInstance()
                        .getViewRoot().setLocale((Locale) entry.getValue());
            }
        }
    }
}