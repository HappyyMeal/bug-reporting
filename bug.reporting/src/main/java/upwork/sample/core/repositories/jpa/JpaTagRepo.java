package upwork.sample.core.repositories.jpa;

import org.hibernate.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import upwork.sample.core.models.entities.Tag;
import upwork.sample.core.repositories.TagRepo;
import upwork.sample.core.repositories.exceptions.DataAccessLayerException;
import upwork.sample.core.repositories.utils.QueryBuilderUtils;

import java.util.List;

@Repository
public class JpaTagRepo implements TagRepo {
    private static final String READ_ALL_TAG_HQL = "FROM Tag";

    @Autowired
    private SessionFactory sessionFactory;

    @Override
    public Tag create(Tag tag) {
        return null;
    }

    @Override
    public Tag read(int entityId) {
        Transaction transaction = null;
        Tag tag = null;
        try (Session session = sessionFactory.openSession()) {
            transaction = session.beginTransaction();
            tag = session.get(Tag.class, entityId);
            transaction.commit();
        } catch (HibernateException e) {
            if (transaction != null)
                transaction.rollback();

            throw new DataAccessLayerException();
        }

        return tag;
    }

    @Override
    public List<Tag> readAll() {
        Transaction transaction = null;
        List<Tag> tags = null;
        try (Session session = sessionFactory.openSession()) {
            transaction = session.beginTransaction();
            tags = session.createQuery(READ_ALL_TAG_HQL).list();
            transaction.commit();
        } catch (HibernateException e) {
            if (transaction != null)
                transaction.rollback();

            throw new DataAccessLayerException();
        }

        return tags;
    }

    @Override
    public Tag update(Tag tag) {
        return null;
    }

    @Override
    public Tag delete(int entityId) {
        return null;
    }

    @Override
    public List<Tag> read10MostUsedTagsMatchedTitle(String title) {
        Transaction transaction = null;
        List<Tag> tags = null;
        try (Session session = sessionFactory.openSession()) {
            transaction = session.beginTransaction();
            tags = QueryBuilderUtils.buildCriteriaForSearching10MostUsedTagsMatchedTitle(session, title).list();
            transaction.commit();
        } catch (HibernateException e) {
            if (transaction != null)
                transaction.rollback();

            throw new DataAccessLayerException();
        }

        return tags;
    }
}
