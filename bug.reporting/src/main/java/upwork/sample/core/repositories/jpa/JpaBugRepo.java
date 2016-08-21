package upwork.sample.core.repositories.jpa;

import org.hibernate.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import upwork.sample.core.models.entities.Author;
import upwork.sample.core.models.entities.Bug;
import upwork.sample.core.repositories.BugRepo;
import upwork.sample.core.repositories.exceptions.DataAccessLayerException;

import java.util.List;

@Repository
public class JpaBugRepo implements BugRepo {
    private static final int FIRST = 0;

    private static final String READ_ALL_BUGS_HQL = "FROM Bug";
    private static final String READ_BUG_BY_DESCRIPTION_HQL = "FROM Bug b WHERE b.bugDescription= :description";

    @Autowired
    private SessionFactory sessionFactory;

    @Override
    public Bug create(Bug bug) {
        Transaction transaction = null;
        try (Session session = sessionFactory.openSession()) {
            transaction = session.beginTransaction();
            session.save(bug);
            transaction.commit();
        } catch (HibernateException e) {
            if (transaction != null)
                transaction.rollback();

            throw new DataAccessLayerException();
        }
        return bug;
    }

    @Override
    public Bug read(int entityId) {
        Transaction transaction = null;
        Bug bug = null;
        try (Session session = sessionFactory.openSession()) {
            transaction = session.beginTransaction();
            bug = session.get(Bug.class, entityId);
            transaction.commit();
        } catch (HibernateException e) {
            if (transaction != null)
                transaction.rollback();

            throw new DataAccessLayerException();
        }

        return bug;
    }

    @Override
    public List<Bug> readAll() {
        Transaction transaction = null;
        List<Bug> bugs = null;
        try (Session session = sessionFactory.openSession()) {
            transaction = session.beginTransaction();
            bugs = session.createQuery(READ_ALL_BUGS_HQL).list();
            transaction.commit();
        } catch (HibernateException e) {
            if (transaction != null)
                transaction.rollback();

            throw new DataAccessLayerException();
        }

        return bugs;
    }

    @Override
    public Bug update(Bug data) {
        Transaction transaction = null;
        try (Session session = sessionFactory.openSession()) {
            transaction = session.beginTransaction();
            session.saveOrUpdate(data);
            transaction.commit();
        } catch (HibernateException e) {
            if (transaction != null)
                transaction.rollback();

            throw new DataAccessLayerException();
        }
        return data;
    }

    @Override
    public Bug delete(int entityId) {
        return null;
    }

    @Override
    public Bug readBugByDescription(String description) {
        Query query =  null;
        Transaction transaction = null;
        List<Bug> bugs = null;
        try (Session session = sessionFactory.openSession()) {
            transaction = session.beginTransaction();
            query = session.createQuery(READ_BUG_BY_DESCRIPTION_HQL);
            query.setParameter("description", description);

            bugs = query.list();
            transaction.commit();
        } catch (HibernateException e) {
            if (transaction != null)
                transaction.rollback();

            throw new DataAccessLayerException();
        }

        if(bugs.isEmpty())
            return null;
        else
            return  bugs.get(FIRST);
    }
}
