package museum.neo4j.api;

import org.neo4j.driver.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.*;

import org.springframework.http.MediaType;
import org.springframework.lang.Nullable;
import org.springframework.web.bind.annotation.*;

/**
 * @ClassName ObjectController
 * @Author ruary
 * @Version 1.0
 * @Describe
 **/
@RestController
@CrossOrigin
public class ObjectController {
    private final Driver driver;

    public ObjectController(Driver driver) {
        this.driver = driver;
    }

    private static final Logger LOGGER = LoggerFactory.getLogger(ObjectController.class.getName());

    /**
     * @Description: Return all collections in database
     * @Return:
     **/
    @GetMapping(path = "/objects", produces = MediaType.APPLICATION_JSON_VALUE)
    public List<String> getObjects() {
        try (Session session = driver.session()) {
            return session.readTransaction(tx -> {
                List<String> uriLists = new ArrayList<String>();
                Result result = tx.run("MATCH (n:`ns0__E22_Man-Made_Object`) RETURN n.uri ORDER BY n.uri");
                while (result.hasNext()) {
                    uriLists.add(result.next().get(0).asString());
                }
                return uriLists;
            });
        }
    }

    /**
     * @return
     * @Description: Return objects by keywords in all fields
     * @Params: [keyword]
     * @Return: java.util.List<java.lang.String>
     */
    @RequestMapping(value = "/search", produces = MediaType.APPLICATION_JSON_VALUE)
    public List<Map<String, Object>> searchByKeyword(@RequestParam(required = false, name = "actor") String actor,
                                                     @RequestParam(required = false, name = "place") String place,
                                                     @RequestParam(required = false, name = "type") String type,
                                                     @RequestParam(name = "keyword", required = false) String keyword) {
        try (Session session = driver.session()) {
            return session.readTransaction(tx -> {
                LOGGER.info("actor: " + actor + "\t" + " place: " + place + "\n" +
                        "type: " + type + "\t");
                        /*
                        + "keyword: " + keyword.replaceAll("\\s","/") + "\t"
                        +"lenOfKey: " + keyword.split("\\s").length);

                         */
                List<Map<String, Object>> resultList = new ArrayList<>();
                Result result = tx.run(searchKeyQuery(actor, place, type, keyword));
                while (result.hasNext()) {
                    resultList.add(result.next().get(0).asNode().asMap());    //∩
                }


                return resultList;
            });
        }
    }

    /**
     * @Description: Compose the Cypher query according to keywords input
     * @Params: [k]
     * @Return: java.lang.String
     **/
    public String searchKeyQuery(@Nullable String actor, @Nullable String place, @Nullable String type, @Nullable String keys) {
        StringBuilder queryBuilder = new StringBuilder("MATCH (object:`ns0__E22_Man-Made_Object`)\n");
        StringBuilder whereBlock = new StringBuilder("WHERE  object.uri is NOT NULL\n");
        if (actor != null) {
            queryBuilder.append("\t,pa=(object:`ns0__E22_Man-Made_Object`)-[a0*2]->(actor:ns0__E39_Actor)");
            queryBuilder.append(System.getProperty("line.separator"));
            whereBlock.append("AND actor.rdfs__label =~ '.*(?i)" + actor + ".*'\n");
        }
        if (place != null) {
            queryBuilder.append("\t,pp=(object:`ns0__E22_Man-Made_Object`)-[r_place0*2..4]->(place:ns0__E53_Place)");
            queryBuilder.append(System.getProperty("line.separator"));
            whereBlock.append("AND place.rdfs__label =~ '.*(?i)" + place + ".*'\n");
        }
        if (type != null) {
            queryBuilder.append("\t,pt=(object:`ns0__E22_Man-Made_Object`)-[r_type0]->(type:ns0__E17_Type_Assignment)");
            queryBuilder.append(System.getProperty("line.separator"));
            whereBlock.append("AND type.rdfs__label =~ '.*(?i)" + type + ".*'\n");
        }
        if (keys != null) {
            String[] k = keys.split("\\s");
            StringBuilder keyMatchBlock = new StringBuilder();
            keyMatchBlock.append("\t,p =(object)-->(description:ns0__E33_Linguistic_Object)" + System.getProperty("line.separator"));
            StringBuilder labelBuilder = new StringBuilder("AND (");
            StringBuilder descBuilder = new StringBuilder("OR ( ");
            for (int i = 0; i < k.length; i++) {
                keyMatchBlock.append("\t,p" + i + "=(object:`ns0__E22_Man-Made_Object`)-[r" + i + "*1..2]->(n" + i + ")");
                keyMatchBlock.append(System.getProperty("line.separator"));
                String dQuery0 = "description.rdf__value contains '";  //"\tdescription.rdf__value=~'.*(?i)";
                String dQuery1 = "'";                  //".*'";
                String dQueryEnd = ".*'";
                String label = ".rdfs__label =~ '.*(?i)";
                if (i > 0) {
                    descBuilder.append("And ");
                    labelBuilder.append("And ");
                }
                //descBuilder.append(dQuery0+k[i]+dQuery1 +System.getProperty("line.separator"));
                descBuilder.append("\n\t(" + dQuery0 + k[i].toLowerCase() + dQuery1 + " OR " + dQuery0 + k[i].substring(0, 1).toUpperCase() + k[i].substring(1) + dQuery1 + ")");
                descBuilder.append(System.getProperty("line.separator"));
                labelBuilder.append("n" + i + label + k[i] + dQueryEnd + System.getProperty("line.separator"));
            }
            queryBuilder.append(keyMatchBlock.toString());
            queryBuilder.append(System.getProperty("line.separator"));

            whereBlock.append(labelBuilder.toString() + ")");
            whereBlock.append(descBuilder.toString() + ")");

        }
        queryBuilder.append(whereBlock.toString());
        queryBuilder.append("RETURN DISTINCT object ORDER BY object.uri");
        return queryBuilder.toString();
    }

    @RequestMapping(value = "/test", produces = MediaType.APPLICATION_JSON_VALUE)
    public String test(@RequestParam(required = false) String actor,
                       @RequestParam(required = false) String place,
                       @RequestParam(required = false) String type,
                       @RequestParam(required = false) String keyword) {
        //return searchKeyQuery(actor, keyword);
        return searchKeyQuery(actor, place, type, keyword);
    }


    @RequestMapping(value = "/object/{Id}", produces = MediaType.APPLICATION_JSON_VALUE)
    public List<Map<String, Object>> getObjectById(@PathVariable Integer Id) {
        String baseURI = "http://localhost:8989/object/";
        String uri = baseURI + Id;
        LOGGER.info(uri);
        Map<String, Object> paraMap = new HashMap<>();
        paraMap.put("uri", uri);
        String query =
                "MATCH p=(n:`ns0__E22_Man-Made_Object`)-[r*..2]->(other)" + "\n" +
                        "WHERE n.uri = $uri" + "\n" +
                        "AND (other.rdf__value is not NULL or other.rdfs__label IS NOT NULL)"
                        + "\n" + "RETURN other.uri as URI,other.rdf__value as value,other.rdfs__label as label";
        Result result = driver.session().run(query, paraMap);

        StringBuilder sb = new StringBuilder();
        List<Map<String, Object>> resultMap = result.list(r -> r.asMap());
        for (Map<String, Object> map : resultMap
        ) {
            for (Map.Entry<String, Object> entry : map.entrySet()
            ) {
                sb.append("Key = " + entry.getKey() +
                        ", Value = " + entry.getValue());
                sb.append(System.getProperty("line.separator"));
            }
        }
        return resultMap;
        //return sb.toString();
    }

    @RequestMapping(value = "/object/location/{place}", produces = MediaType.APPLICATION_JSON_VALUE)
    public List<String> getObjectByLocation(@PathVariable String place) {
        Map<String, Object> paraMap = new HashMap<>();
        paraMap.put("place", place);
        String query =
                "MATCH (o:`ns0__E22_Man-Made_Object`)-->()-->(n:ns0__E53_Place)" + "\n" +
                        "WHERE n.rdfs__label =  $place" + "\n" +
                        "RETURN o";
        Result result = driver.session().run(query, paraMap);
        return result.list(r -> r.get("o").asNode().get("uri").asString());
    }

    @RequestMapping(value = "/object/material/{material}", produces = MediaType.APPLICATION_JSON_VALUE)
    public List<String> getObjectByMaterial(@PathVariable String material) {
        Map<String, Object> paraMap = new HashMap<>();
        paraMap.put("para1", material);
        String query =
                "MATCH (o:`ns0__E22_Man-Made_Object`)-->(n:ns0__E57_Material)" + "\n" +
                        "WHERE n.rdfs__label =  $para1" + "\n" +
                        "RETURN o";
        Result result = driver.session().run(query, paraMap);
        return result.list(r -> r.get("o").asNode().get("uri").asString());
    }

}
