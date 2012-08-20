package net.eisele.example.resourcebundletricks.domain;

import java.io.Serializable;
import java.util.Locale;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;

/**
 *
 * @author eiselem
 */
@NamedQueries({
    @NamedQuery(name = "ResourceEntity.findForLocale", query = "SELECT re from ResourceEntity re WHERE re.locale = :locale"),
    @NamedQuery(name = "ResourceEntity.findAll", query = "SELECT re from ResourceEntity re")
})
@Entity
public class ResourceEntity implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    @Column(name = "i18n_key")
    private String key;
    @Column(name = "i18n_value")
    private String value;
    @Column(name = "i18n_locale")
    private Locale locale;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getKey() {
        return key;
    }

    public void setKey(String key) {
        this.key = key;
    }

    public String getValue() {
        return value;
    }

    public void setValue(String value) {
        this.value = value;
    }

    public Locale getLocale() {
        return locale;
    }

    public void setLocale(Locale locale) {
        this.locale = locale;
    }

    @Override
    public int hashCode() {
        return id == null ? 0 : id.hashCode();
    }

    @Override
    public boolean equals(Object object) {
        boolean result = object == this;
        if (!result && object != null && this.getClass().isAssignableFrom(object.getClass())) {
            result = this.id != null && ((ResourceEntity) object).id != null
                    && this.id.equals(((ResourceEntity) object).id);
        }
        return result;
    }

    @Override
    public String toString() {
        return "net.eisele.example.resourcebundletricks.domain.ResourceEntity[ id=" + id + " ]";
    }
}
