package upwork.sample.rest.mvc;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import upwork.sample.core.models.entities.Author;
import upwork.sample.core.services.AuthorService;
import upwork.sample.core.services.util.AuthorList;

import java.util.List;

@Controller
@RequestMapping("/rest/reporting/authors")
public class AuthorController {

    @Autowired
    private AuthorService authorService;

    @RequestMapping(method = RequestMethod.GET)
    public ResponseEntity<AuthorList> readAllAuthors() {
        AuthorList authors = authorService.readAllAuthors();
        return new ResponseEntity<AuthorList>(authors, HttpStatus.OK);
    }
}
