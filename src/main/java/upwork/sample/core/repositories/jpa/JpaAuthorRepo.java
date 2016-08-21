package upwork.sample.core.repositories.jpa;

import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import upwork.sample.core.models.entities.Author;
import upwork.sample.core.repositories.AuthorRepo;
import upwork.sample.core.repositories.exceptions.DataAccessLayerException;

import java.util.List;

@Repository
public class JpaAuthorRepo implements AuthorRepo {
    private static final String READ_ALL_AUTHORS_HQL = "FROM Author";

    @Autowired
    private SessionFactory sessionFactory;

    @Override
    public Author create(Author author) {
        Transaction transaction = null;
        try (Session session = sessionFactory.openSession()) {
            transaction = session.beginTransaction();
            session.save(author);
            transaction.commit();
        } catch (HibernateException e) {
            if (transaction != null)
                transaction.rollback();

            throw new DataAccessLayerException();
        }
        return author;
    }

    @Override
    public Author read(int entityId) {
        Transaction transaction = null;
        Author author = null;
        try (Session session = sessionFactory.openSession()) {
            transaction = session.beginTransaction();
            author = session.get(Author.class, entityId);
            transaction.commit();
        } catch (HibernateException e) {
            if (transaction != null)
                transaction.rollback();

            throw new DataAccessLayerException();
        }

        return author;
    }


    public List<Author> readAll() {
        Transaction transaction = null;
        List<Author> authors = null;
        try (Session session = sessionFactory.openSession()) {
            transaction = session.beginTransaction();
            authors = session.createQuery(READ_ALL_AUTHORS_HQL).list();
            transaction.commit();
        } catch (HibernateException e) {
            if (transaction != null)
                transaction.rollback();

            throw new DataAccessLayerException();
        }

        return authors;
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
