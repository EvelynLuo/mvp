package museum.neo4j.entity;

import org.springframework.data.neo4j.core.schema.Node;
import org.springframework.data.neo4j.core.schema.Relationship;

/**
 * @ClassName Production
 * @Data 2022/7/25 0:03
 * @Author ruary
 * @Version 1.0
 * @Describe
 **/
@Node("ns0__E12_Production")
public class Production extends BaseUri {
    @Relationship(direction = Relationship.Direction.INCOMING)
    private ManMadeObject manMadeObject;
}
