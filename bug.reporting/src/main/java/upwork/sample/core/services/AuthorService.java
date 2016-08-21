package upwork.sample.core.services;

import upwork.sample.core.models.entities.Author;
import upwork.sample.core.services.util.AuthorList;

public interface AuthorService extends CRUDGenericService<Author> {
    //might consider to define the service specific behavior

    public AuthorList readAllAuthors();
}
