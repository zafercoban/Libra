package Library.Management.repository;

import Library.Management.domain.Book;
import org.springframework.data.mongodb.repository.MongoRepository;


public interface BookRepository extends MongoRepository<Book, String> {

    Book findById(String id);

}