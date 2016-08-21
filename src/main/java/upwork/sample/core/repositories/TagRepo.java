package upwork.sample.core.repositories;

import upwork.sample.core.models.entities.Tag;

import java.util.List;

public interface TagRepo extends CRUDGenericRepo<Tag> {

    public List<Tag> read10MostUsedTagsMatchedTitle(String title);
}
