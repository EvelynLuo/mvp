package museum.neo4j.api;

import museum.neo4j.entity.ManMadeObject;
import museum.neo4j.service.ObjectService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * @ClassName TestController
 * @Data 2022/7/24 20:20
 * @Author ruary
 * @Version 1.0
 * @Describe
 **/
@RestController
@RequestMapping("/rest/test")
public class TestController {

    @Autowired
    ObjectService service;

    @RequestMapping("/queryAll")
    public List<ManMadeObject> getAll() {
        return service.getAll();
    }


    @RequestMapping("/query")
    public ManMadeObject getById(@RequestParam Integer id) {
        return service.getTypeById(id);
    }
}
