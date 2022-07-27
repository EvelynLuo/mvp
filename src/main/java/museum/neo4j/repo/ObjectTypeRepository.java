package museum.neo4j.repo;

import museum.neo4j.entity.ObjectType;
import org.springframework.data.neo4j.repository.Neo4jRepository;
import org.springframework.data.neo4j.repository.query.Query;

import java.net.URI;
import java.util.List;

/**
 * @Author ruary
 * @Version 1.0
 * @Describe
 **/
public interface ObjectTypeRepository extends Neo4jRepository<ObjectType, URI> {

    @Query("MATCH (n:ns0__E17_Type_Assignment) RETURN n")
    List<ObjectType> getAll();

}
