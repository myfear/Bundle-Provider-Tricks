package net.eisele.example.resourcebundletricks.util;

/**
 *
 * @author eiselem
 */
public enum ResourceBundleNames {

    MESSAGES("Messages"),
    ERRORS("Errors");
    private String bundleName;

    ResourceBundleNames(String bundleName) {
        this.bundleName = bundleName;
    }

    public String getBundleName() {
        return bundleName;
    }

    @Override
    public String toString() {
        return bundleName;
    }
}