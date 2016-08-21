package upwork.sample.core.services.util;

import upwork.sample.core.models.entities.Tag;

import java.util.ArrayList;
import java.util.List;

public class TagList {

    private List<Tag> tags = new ArrayList<Tag>();

    public TagList(List<Tag> tags) {
        this.tags = tags;
    }

    public List<Tag> getTags() {
        return tags;
    }

    public void setTags(List<Tag> tags) {
        this.tags = tags;
    }
}
