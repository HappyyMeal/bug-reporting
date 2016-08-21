package upwork.sample.rest.mvc;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import upwork.sample.core.services.TagService;
import upwork.sample.core.services.util.TagList;

@Controller
@RequestMapping("/rest/reporting/tags")
public class TagController {

    @Autowired
    private TagService tagService;

    @RequestMapping(method = RequestMethod.GET)
    public ResponseEntity<TagList> readAllTags() {
        TagList tagList = tagService.readAllTags();
        return new ResponseEntity<TagList>(tagList, HttpStatus.OK);
    }

    @RequestMapping(value = "/10MostUsed/{title}", method = RequestMethod.GET)
    public ResponseEntity<TagList> read10MostUsedTagsMatchedTitle(@PathVariable String title) {
        TagList tagList = tagService.read10MostUsedTagsMatchedTitle(title);
        return new ResponseEntity<TagList>(tagList, HttpStatus.OK);
    }
}
