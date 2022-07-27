package museum.neo4j.entity;

import org.springframework.data.neo4j.core.schema.Id;
import org.springframework.data.neo4j.core.schema.Property;

import java.net.URI;
import java.util.Objects;

/**
 * @ClassName BaseValue
 * @Data 2022/7/24 19:59
 * @Author ruary
 * @Version 1.0
 * @Describe
 **/

public abstract class BaseValue extends BaseUri {

    @Property("rdf__value")
    private String value;
    /*
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        BaseValue that = (BaseValue) o;
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
