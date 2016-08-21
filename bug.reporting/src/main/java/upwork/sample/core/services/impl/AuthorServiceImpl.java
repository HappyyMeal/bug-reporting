package upwork.sample.core.services.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import upwork.sample.core.models.entities.Author;
import upwork.sample.core.repositories.AuthorRepo;
import upwork.sample.core.services.AuthorService;
import upwork.sample.core.services.exceptions.AuthorAlreadyExistsException;
import upwork.sample.core.services.exceptions.AuthorNotFoundException;
import upwork.sample.core.services.util.AuthorList;

@Service
@Transactional
public class AuthorServiceImpl implements AuthorService {

    @Autowired
    private AuthorRepo authorRepo;

    @Override
    public Author create(Author data) {
        Author author = authorRepo.read(data.getAuthorId());
        if(author != null)
            throw new AuthorAlreadyExistsException();

        return authorRepo.create(data);
    }

    @Override
    public Author read(int entityId) {
        Author author = authorRepo.read(entityId);
        if(author == null)
            throw new AuthorNotFoundException();

        return author;
    }

    @Override
    public AuthorList readAllAuthors() {
        return new AuthorList(authorRepo.readAll());
    }

    @Override
    public Author update(Author author) {
        return null;
    }

    @Override
    public Author delete(int entityId) {
        return null;
    }


}
