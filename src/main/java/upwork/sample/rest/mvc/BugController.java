package upwork.sample.rest.mvc;

import javassist.tools.web.BadHttpRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import upwork.sample.core.models.entities.Author;
import upwork.sample.core.models.entities.Bug;
import upwork.sample.core.models.entities.Tag;
import upwork.sample.core.services.BugService;
import upwork.sample.core.services.exceptions.BugAlreadyExistsException;
import upwork.sample.core.services.exceptions.BugNotFoundException;
import upwork.sample.core.services.util.BugList;
import upwork.sample.rest.exceptions.BadRequestException;
import upwork.sample.rest.exceptions.ConflictException;
import upwork.sample.rest.exceptions.NotFoundException;

import java.util.ArrayList;
import java.util.List;

@Controller
@RequestMapping("/rest/reporting/bugs")
public class BugController {

    @Autowired
    private BugService bugService;

    @RequestMapping(method = RequestMethod.GET)
    public ResponseEntity<BugList> readAllBugs() {
        BugList bugs = bugService.readAllBugs();
        return new ResponseEntity<BugList>(bugs, HttpStatus.OK);
    }

    @RequestMapping(method = RequestMethod.POST)
    public ResponseEntity<Bug> createBug(@RequestBody Bug sentBug) {
        Bug createdBugEntry = null;

        try {
            createdBugEntry = bugService.create(sentBug);
        } catch (IllegalArgumentException e) {
            throw new BadRequestException();
        } catch (BugAlreadyExistsException e) {
            throw new ConflictException();
        }

        return new ResponseEntity<Bug>(createdBugEntry, HttpStatus.CREATED);
    }

            @RequestMapping(value = "/{bugId}", method = RequestMethod.PUT)
            public ResponseEntity<Bug> updateBug(@PathVariable Integer bugId, @RequestBody Bug sentBug) throws BadHttpRequest {
                Bug updatedBugEntry = null;
                if (sentBug != null && sentBug.getBugId() == null)
                    sentBug.setBugId(bugId);

                try {
                    updatedBugEntry = bugService.update(sentBug);
                } catch (IllegalArgumentException e) {
                    throw new BadRequestException();
                } catch (BugNotFoundException e) {
                    throw new NotFoundException();
        }

        return new ResponseEntity<Bug>(updatedBugEntry, HttpStatus.OK);
    }

}
