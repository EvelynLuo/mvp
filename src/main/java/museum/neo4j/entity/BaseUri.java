package museum.neo4j.entity;

import org.springframework.data.neo4j.core.schema.Id;
import org.springframework.data.neo4j.core.schema.Property;

import java.io.Serializable;
import java.net.URI;

/**
 * @ClassName BaseUri
 * @Data 2022/7/25 0:04
 * @Author ruary
 * @Version 1.0
 * @Describe
 **/
public abstract class BaseUri {


    @Id
    @Property
    private URI uri;

    @Override
    public String toString() {
        return getUri().toString();
    }

    public URI getUri() {
        return uri;
    }

    public void setUri(URI uri) {
        this.uri = uri;
    }
}
