package net.eisele.example.resourcebundletricks.view;

import java.util.ResourceBundle;
import javax.ejb.EJB;
import javax.enterprise.context.RequestScoped;
import javax.faces.context.FacesContext;
import javax.inject.Named;
import net.eisele.example.resourcebundletricks.business.Greeter;
import net.eisele.example.resourcebundletricks.domain.ResourceEntity;

/**
 *
 * @author eiselem
 */
@Named
@RequestScoped
public class Greeting {

    @EJB
    Greeter g;
    
    protected ResourceEntity re = new ResourceEntity();

    public String getWelcome() {
        FacesContext context = FacesContext.getCurrentInstance();
        ResourceBundle bundle = context.getApplication().getResourceBundle(context, "msgs");
        return bundle.getString("welcome.message");
    }

    public String getPersonalizedGreeting() {
        return g.greet("Markus");
    }
    
    public ResourceEntity getResourceEntity() {
      return re;
    }

    public void setResourceEntity(ResourceEntity re) {
      this.re = re;
    }
}
