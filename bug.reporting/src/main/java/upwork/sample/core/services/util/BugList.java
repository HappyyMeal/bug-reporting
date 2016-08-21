package upwork.sample.core.services.util;

import upwork.sample.core.models.entities.Bug;

import java.util.ArrayList;
import java.util.List;

public class BugList {

    private List<Bug> bugs = new ArrayList<Bug>();

    public BugList(List<Bug> bugs) {
        this.bugs = bugs;
    }

    public List<Bug> getBugs() {
        return bugs;
    }

    public void setBugs(List<Bug> bugs) {
        this.bugs = bugs;
    }
}
