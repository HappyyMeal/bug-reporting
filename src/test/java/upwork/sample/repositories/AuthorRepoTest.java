package upwork.sample.repositories;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.annotation.Rollback;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.transaction.annotation.Transactional;
import upwork.sample.core.models.entities.Author;
import upwork.sample.core.repositories.AuthorRepo;

import java.util.List;

import static org.junit.Assert.assertEquals;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration("classpath:spring/spring.db.config.xml")
public class AuthorRepoTest {

    @Autowired
    private AuthorRepo authorRepo;

    @Test
    @Transactional
    public void createAuthor() {
        Author authorToCreate = getAuthorTemplate();
        Author createdAuthor = authorRepo.create(authorToCreate);

        assertEquals(authorToCreate.getName(), createdAuthor.getName());
        assertEquals(authorToCreate.getEmail(), createdAuthor.getEmail());
    }

    @Test
    @Transactional
    public void readAuthor() {
        Author authorToCreate = getAuthorTemplate();
        Author createdAuthor = authorRepo.create(authorToCreate);
        Author readAuthor = authorRepo.read(createdAuthor.getAuthorId());

        assertEquals(authorToCreate.getName(), createdAuthor.getName());
        assertEquals(authorToCreate.getEmail(), createdAuthor.getEmail());
    }

    @Test
    @Transactional
    public void readAllAuthors() {
        List<Author> initialAuthors = authorRepo.readAll();

        Author author = getAuthorTemplate();
        authorRepo.create(author);

        List<Author> followupAuthors = authorRepo.readAll();

        int expectedSizeDiff = 1;
        int actualSizeDiff = followupAuthors.size() - initialAuthors.size();

        assertEquals(expectedSizeDiff, actualSizeDiff);
    }

    private Author getAuthorTemplate() {
        Author author = new Author();
        author.setName("Author name");
        author.setEmail("author@email.com");
        return author;
    }
}
