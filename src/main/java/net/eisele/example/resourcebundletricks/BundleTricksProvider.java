package net.eisele.example.resourcebundletricks;

import javax.faces.event.PhaseId;
import javax.servlet.ServletContext;
import org.ocpsoft.rewrite.config.Configuration;
import org.ocpsoft.rewrite.config.ConfigurationBuilder;
import org.ocpsoft.rewrite.el.El;
import org.ocpsoft.rewrite.faces.config.PhaseBinding;
import org.ocpsoft.rewrite.servlet.config.HttpConfigurationProvider;
import org.ocpsoft.rewrite.servlet.config.Response;
import org.ocpsoft.rewrite.servlet.config.rule.Join;

/**
 *
 * @author eiselem
 */
public class BundleTricksProvider extends HttpConfigurationProvider {

    @Override
    public Configuration getConfiguration(ServletContext context) {
        return ConfigurationBuilder.begin()
                .addRule(Join.path("/{locale}/{page}.xhtml").to("/{page}.xhtml")
                .where("page").matches(".*")
                .where("locale").bindsTo(PhaseBinding.to(El.property("#{languageSwitch.localeCode}")).after(PhaseId.RESTORE_VIEW)))
                .addRule(Join.path("/404").to("/404.xhtml").perform(Response.setCode(404)));


    }

    /**
     *
     * @return
     */
    @Override
    public int priority() {
        return 10;
    }
}