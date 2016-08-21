package upwork.sample.core.repositories;

import upwork.sample.core.models.entities.Author;
import upwork.sample.core.services.CRUDGenericService;

public interface AuthorRepo extends CRUDGenericRepo<Author> {
    //might consider to define the repository specific behavior
}
