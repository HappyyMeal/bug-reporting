package upwork.sample.core.services;

import upwork.sample.core.models.entities.Bug;
import upwork.sample.core.services.util.BugList;

public interface BugService extends CRUDGenericService<Bug> {
    //might consider to define the service specific behavior

    public BugList readAllBugs();
    public Bug readBugByDescription(String description);
}
