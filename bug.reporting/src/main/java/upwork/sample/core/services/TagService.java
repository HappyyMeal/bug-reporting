package upwork.sample.core.services;

import upwork.sample.core.models.entities.Tag;
import upwork.sample.core.services.util.TagList;


public interface TagService extends CRUDGenericService<Tag> {
    //might consider to define the service specific behavior

    public TagList readAllTags();
    public TagList read10MostUsedTagsMatchedTitle(String title);
}
