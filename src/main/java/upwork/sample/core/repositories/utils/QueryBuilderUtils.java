package upwork.sample.core.repositories.utils;


import org.hibernate.Query;
import org.hibernate.Session;

public class QueryBuilderUtils {
    private static final int FIRST_POSITION = 0;
    private static final int MAX_TAG_RESULTS = 10;
    private static final String LIKE_ANYTHING_POSTFIX = "%";

    private static final String READ_MOST_USED_TAGS_MATCHED_TITLE_SQL =
            "select t.* from tag t\n" +
                    "left join bug_tag bt on t.tag_id = bt.tag_id\n" +
                    "where t.title like ?\n" +
                    "group by t.title\n" +
                    "order by count(*) desc, bt.tag_id is not null desc\n";

    public static Query buildCriteriaForSearching10MostUsedTagsMatchedTitle(Session session, String title) {
        Query query = session.createSQLQuery(READ_MOST_USED_TAGS_MATCHED_TITLE_SQL);
        query.setParameter(FIRST_POSITION, title + LIKE_ANYTHING_POSTFIX);
        query.setMaxResults(MAX_TAG_RESULTS);
        return query;
    }
}
