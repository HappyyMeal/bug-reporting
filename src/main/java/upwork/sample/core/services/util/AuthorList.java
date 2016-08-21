package upwork.sample.core.services.util;

import upwork.sample.core.models.entities.Author;

import java.util.ArrayList;
import java.util.List;

public class AuthorList {

    private List<Author> authors = new ArrayList<Author>();

    public AuthorList(List<Author> authors) {
        this.authors = authors;
    }

    public List<Author> getAuthors() {
        return authors;
    }

    public void setAuthors(List<Author> authors) {
        this.authors = authors;
    }
}
