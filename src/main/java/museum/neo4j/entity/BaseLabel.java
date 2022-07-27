package museum.neo4j.entity;

import org.springframework.data.neo4j.core.schema.Id;
import org.springframework.data.neo4j.core.schema.Property;

import java.io.Serializable;
import java.net.URI;
import java.util.Objects;

/**
 * @ClassName BaseLabel
 * @Data 2022/7/24 20:08
 * @Author ruary
 * @Version 1.0
 * @Describe
 **/
public abstract class BaseLabel extends BaseUri {
    @Property(name = "rdfs__label")
    private String value;

    /*
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        BaseLabel that = ;
        return Objects.equals(this.value, that.value);
    }

     */

    @Override
    public String toString() {
        return getValue();
    }

    public String getValue() {
        return value;
    }

    public void setValue(String value) {
        this.value = value;
    }
}
