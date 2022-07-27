package museum.neo4j.entity;

import org.springframework.data.neo4j.core.schema.Node;
import org.springframework.data.neo4j.core.schema.Relationship;

import java.util.List;

/**
 * @ClassName ObjectType
 * @Data 2022/7/24 20:24
 * @Author ruary
 * @Version 1.0
 * @Describe
 **/
@Node("ns0__E17_Type_Assignment")
public class ObjectType extends BaseLabel {

    @Relationship(direction = Relationship.Direction.INCOMING)
    private List<ManMadeObject> objectList;
}
