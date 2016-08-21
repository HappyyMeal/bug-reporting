package upwork.sample.core.services.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import upwork.sample.core.models.entities.Tag;
import upwork.sample.core.repositories.TagRepo;
import upwork.sample.core.services.TagService;
import upwork.sample.core.services.util.TagList;

import java.util.List;

@Service
@Transactional
public class TagServiceImpl implements TagService {

    @Autowired
    private TagRepo tagRepo;

    @Override
    public Tag create(Tag tag) {
        return null;
    }

    @Override
    public Tag read(int entityId) {
        return null;
    }

    @Override
    public TagList readAllTags() {
        return new TagList(tagRepo.readAll());
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
    public TagList read10MostUsedTagsMatchedTitle(String title) {
        return new TagList(tagRepo.read10MostUsedTagsMatchedTitle(title));
    }
}
