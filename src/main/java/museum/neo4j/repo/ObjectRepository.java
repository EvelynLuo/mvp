package museum.neo4j.repo;

import museum.neo4j.entity.ManMadeObject;
import museum.neo4j.entity.Production;
import org.springframework.data.neo4j.repository.Neo4jRepository;
import org.springframework.data.neo4j.repository.query.Query;
import org.springframework.data.repository.query.Param;

import java.net.URI;
import java.util.List;

/**
 * @Author ruary
 * @Version 1.0
 * @Describe
 **/
public interface ObjectRepository extends Neo4jRepository<ManMadeObject, URI> {

    @Query("MATCH (n:`ns0__E22_Man-Made_Object`)-->(x) RETURN n,x")
    List<ManMadeObject> getAll();

    @Query("MATCH p=(n:`ns0__E22_Man-Made_Object`)-[r]->(a) WHERE n.uri = $uri RETURN n LIMIT 1")
    ManMadeObject getById(@Param("uri") String uri);

}
