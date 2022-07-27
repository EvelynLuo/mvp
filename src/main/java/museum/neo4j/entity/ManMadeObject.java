package museum.neo4j.entity;

import org.springframework.data.neo4j.core.schema.Node;
import org.springframework.data.neo4j.core.schema.Relationship;

import java.io.Serializable;
import java.net.URI;
import java.util.ArrayList;
import java.util.List;

/**
 * @ClassName ManMadeObject
 * @Data 2022/7/24 19:22
 * @Author ruary
 * @Version 1.0
 * @Describe
 **/
@Node("ns0__E22_Man-Made_Object")
public class ManMadeObject extends BaseUri {

    //classification
    //@JsonProperty("ObjectType")


    @Relationship(type = "ns0__P41i_was_classified_by", direction = Relationship.Direction.OUTGOING)
    private List<ObjectType> objectTypes;

    @Relationship(type = "ns0__P108i_was_produced_by")
    private Production production;

    public List<ObjectType> getObjectTypes() {
        return objectTypes;
    }

    public void setObjectTypes(List<ObjectType> objectTypes) {
        this.objectTypes = objectTypes;
    }

    public Production getProduction() {
        return production;
    }

    public void setProduction(Production production) {
        this.production = production;
    }

    public ManMadeObject(List<ObjectType> objectTypes, Production production) {
        this.objectTypes = objectTypes;
        this.production = production;
    }
}
