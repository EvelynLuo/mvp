package museum.neo4j.relation;

import museum.neo4j.entity.ObjectType;
import org.springframework.data.neo4j.core.schema.*;

/**
 * @ClassName P41i_was_classified_by
 * @Data 2022/7/25 19:36
 * @Author ruary
 * @Version 1.0
 * @Describe
 **/
@RelationshipProperties
public class ns0__P41i_was_classified_by {
    @Id
    @GeneratedValue
    private Long id;

    @TargetNode
    private ObjectType objectType;
}
