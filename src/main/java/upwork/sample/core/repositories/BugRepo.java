package upwork.sample.core.repositories;

import upwork.sample.core.models.entities.Bug;

public interface BugRepo extends CRUDGenericRepo<Bug> {
    //might consider to define the repository specific behavior

    public Bug readBugByDescription(String description);
}
