/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
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

    @Inject
    @MessageBundle
    private ResourceBundle messages;

    public String greet(String name) {
        String pattern = messages.getString("welcome.name");
        return MessageFormat.format(pattern, name);
    }
}
