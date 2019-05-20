package wolfdriver.util.web.filter.cache;

public interface CachedStreamEntity {
    CachedStream getCachedStream();
    void flushStream();
}
