/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package giates.validation;

import java.util.Locale;
import java.util.Map;
import javax.validation.MessageInterpolator;
import net.eisele.example.resourcebundletricks.bundles.DatabaseResourceBundle;

public class DatabaseMessageInterpolator implements MessageInterpolator {
  protected final String BRACE_OPEN = "\\{";
  protected final String BRACE_CLOSE = "\\}";
  
  @Override
  public String interpolate(String message, Context context) {
    return interpolate(message, context, Locale.ITALIAN);
  }

  @Override
  public String interpolate(String message, Context context, Locale locale) {
    DatabaseResourceBundle bundle = new DatabaseResourceBundle(locale);
    String messageKey = context.getConstraintDescriptor().getAttributes().get("message").toString();
    message = bundle.getString(messageKey.replaceAll(BRACE_OPEN, "").replaceAll(BRACE_CLOSE, ""));
    Map<String, Object> attributes = context.getConstraintDescriptor().getAttributes();
    for (String key : attributes.keySet()) {
      String value = attributes.get(key).toString();
      key = BRACE_OPEN + key + BRACE_CLOSE;
      message = message.replaceAll(key, value);
    }
    return message;
  }
}