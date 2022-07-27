package museum.neo4j.service;

import museum.neo4j.entity.ManMadeObject;
import museum.neo4j.repo.ObjectRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @ClassName ObjectService
 * @Data 2022/7/24 20:17
 * @Author ruary
 * @Version 1.0
 * @Describe
 **/
@Service
public class ObjectService {
    @Autowired
    private ObjectRepository repository;

    public List<ManMadeObject> getAll() {
        return repository.getAll();
    }

    public ManMadeObject getTypeById(Integer id) {
        String baseUri = "http://localhost:8989/object/";
        return repository.getById(baseUri + id);
        /*
        List<String> values = new ArrayList<>();
        values.add(repository.getById(id).getUri().toString());
        if(repository.getById(id).getObjectTypes()==null) return values;

        List<ObjectType> objectTypes = repository.getById(id).getObjectTypes();

        for (ObjectType o: objectTypes
             ) {
            values.add(o.getValue());
        }
        return values;
         */
    }
}
