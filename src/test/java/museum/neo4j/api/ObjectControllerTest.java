package museum.neo4j.api;

import org.junit.jupiter.api.Test;
import org.neo4j.driver.Driver;
import org.neo4j.driver.Result;
import org.springframework.boot.test.autoconfigure.data.neo4j.AutoConfigureDataNeo4j;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@SpringBootTest
@AutoConfigureDataNeo4j
class ObjectControllerTest {

    private final Driver driver;

    public ObjectControllerTest(Driver driver) {
        this.driver = driver;
    }

    @Test
    void getObjects() {
    }

    @Test
    void searchByKeyword() {
    }

    @Test
    void searchKeyQuery() {
    }

    @Test
    void test1() {
    }

    @Test
    void getObjectById() {
        String baseURI = "http://localhost:8989/object/";

        String uri = baseURI + 742;
        Map<String, Object> paraMap = new HashMap<>();
        paraMap.put("uri", uri);
        String query =
                "MATCH p=(n:`ns0__E22_Man-Made_Object`)-->(a)" + "\n" +
                        "WHERE n.uri = $uri" + "\n" +
                        "RETURN a";
        Result result = driver.session().run(query, paraMap);
        List<Map<String, Object>> resultMap = result.list(r -> r.get("a").asNode().asMap());
        for (Map<String, Object> map : resultMap
        ) {
            for (Map.Entry<String, Object> entry : map.entrySet()
            ) {
                System.out.println("Key = " + entry.getKey() +
                        ", Value = " + entry.getValue());
            }
        }
    }

    @Test
    void getObjectByLocation() {
    }

    @Test
    void getObjectByMaterial() {
    }
}