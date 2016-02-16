package Library.Management.service;

import Library.Management.domain.Book;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import java.util.UUID;

/**
 * Service for initializing MongoDB with sample data using {@link MongoTemplate}
 */
@Component
public class InitMongoService {

    @Autowired
    private MongoTemplate mongoTemplate;

    @PostConstruct
    public void init() {

        mongoTemplate.dropCollection("book");


        Book java8 = new Book();
        java8.setId(UUID.randomUUID().toString());
        java8.setAuthor("JAVA 8");
        java8.setBookName("java8");

        Book collins = new Book();
        collins.setId(UUID.randomUUID().toString());
        collins.setAuthor("Suzanne Collins");
        collins.setBookName("The Hunger Games");


        mongoTemplate.insert(java8, "book");
        mongoTemplate.insert(collins, "book");
    }
}
