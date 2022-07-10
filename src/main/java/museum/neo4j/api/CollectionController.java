package museum.neo4j.api;

import org.neo4j.driver.Driver;
import org.neo4j.driver.Session;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * @ClassName CollectionController
 * @Author ruary
 * @Version 1.0
 * @Describe
 **/
@RestController
public class CollectionController {
    private final Driver driver;

    public CollectionController(Driver driver) {
        this.driver = driver;
    }

    private static final Logger LOGGER = LoggerFactory.getLogger(CollectionController.class.getName());

    @RequestMapping("/collections")
    public List<String> getObjects(){
        try (Session session = driver.session()) {
            return session.run("MATCH (n:`ns0__E22_Man-Made_Object`) RETURN n")
                    .list(r -> r.get("n").asNode().get("uri").asString());
        }
    }
}
