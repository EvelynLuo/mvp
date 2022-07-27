package museum.neo4j.api;

import museum.neo4j.service.ObjectService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.data.neo4j.AutoConfigureDataNeo4j;
import org.springframework.boot.test.context.SpringBootTest;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
@AutoConfigureDataNeo4j
class TestControllerTest {

    @Autowired
    ObjectService service;

    @Test
    void getById() {
        service.getTypeById(742);

    }
}