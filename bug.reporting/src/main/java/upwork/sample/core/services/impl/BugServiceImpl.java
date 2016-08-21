package upwork.sample.core.services.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import upwork.sample.core.models.entities.Bug;
import upwork.sample.core.repositories.AuthorRepo;
import upwork.sample.core.repositories.BugRepo;
import upwork.sample.core.repositories.TagRepo;
import upwork.sample.core.services.BugService;
import upwork.sample.core.services.exceptions.BugAlreadyExistsException;
import upwork.sample.core.services.exceptions.BugNotFoundException;
import upwork.sample.core.services.util.BugList;

@Service
@Transactional
public class BugServiceImpl implements BugService {

    @Autowired
    private BugRepo bugRepo;

    @Override
    public Bug create(Bug passedBug) {
        Bug bug = null;
        if (passedBug != null && passedBug.getBugDescription() != null) {
            bug = bugRepo.readBugByDescription(passedBug.getBugDescription());
        } else {
            throw new IllegalArgumentException();
        }

        if (bug != null)
            throw new BugAlreadyExistsException();

        bug = bugRepo.create(passedBug);
        return bugRepo.read(bug.getBugId());
    }

    @Override
    public Bug read(int entityId) {
        return null;
    }

    @Override
    public BugList readAllBugs() {
        return new BugList(bugRepo.readAll());
    }

    @Override
    public Bug update(Bug passedBug) {
        Bug bug = null;
        if (passedBug != null && passedBug.getBugId() != null) {
            bug = bugRepo.read(passedBug.getBugId());
        } else {
            throw new IllegalArgumentException();
        }

        if(bug == null)
            throw new BugNotFoundException();

        bug = bugRepo.update(passedBug);
        return bugRepo.read(bug.getBugId());
    }

    @Override
    public Bug delete(int entityId) {
        return null;
    }

    @Override
    public Bug readBugByDescription(String description) {
        return bugRepo.readBugByDescription(description);
    }
}
