package net.eisele.example.resourcebundletricks.business;

import java.text.MessageFormat;
import java.util.ResourceBundle;
import javax.ejb.Stateless;
import javax.inject.Inject;
import net.eisele.example.resourcebundletricks.bundles.MessageBundle;

/**
 *
 * @author eiselem
 */
@Stateless
public class Greeter {

    // Does this work? 
    @Inject
    @MessageBundle
    private transient ResourceBundle messages;

    public String greet(String name) {
        String pattern = messages.getString("welcome.name");
        return MessageFormat.format(pattern, name);
    }
}
