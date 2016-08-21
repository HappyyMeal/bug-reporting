package upwork.sample.mvc;

import org.junit.Before;
import org.junit.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import upwork.sample.core.models.entities.Author;
import upwork.sample.core.models.entities.Bug;
import upwork.sample.core.models.entities.Tag;
import upwork.sample.core.services.BugService;
import upwork.sample.core.services.exceptions.BugAlreadyExistsException;
import upwork.sample.core.services.exceptions.BugNotFoundException;
import upwork.sample.core.services.util.BugList;
import upwork.sample.rest.mvc.BugController;

import java.util.*;

import static org.hamcrest.Matchers.hasItems;
import static org.hamcrest.Matchers.is;
import static org.hamcrest.Matchers.nullValue;
import static org.mockito.Mockito.when;
import static org.mockito.Matchers.any;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.put;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

public class BugControllerTest {

    @InjectMocks
    private BugController bugController;

    @Mock
    private BugService bugService;

    private MockMvc mockMvc;

    @Before
    public void setup() {
        MockitoAnnotations.initMocks(this);
        mockMvc = MockMvcBuilders.standaloneSetup(bugController).build();
    }

    @Test
    public void readAllBugs() throws Exception {
        List<Bug> bugs = new ArrayList<Bug>(1);

        Bug bug = new Bug();
        bug.setBugDescription("bug description");
        bug.setCreationDate(new Date());

        Author author = initializeDefaultAuthor();
        List<Tag> tags = initializeDefaultTagSet();

        bug.setAuthor(author);
        bug.setTags(tags);
        bugs.add(bug);

        BugList bugList = new BugList(bugs);

        when(bugService.readAllBugs()).thenReturn(bugList);

        mockMvc.perform(get("/rest/reporting/bugs"))
//                .andDo(print())
                .andExpect(jsonPath("$.bugs[*].bugId", hasItems(is(nullValue()))))
                .andExpect(jsonPath("$.bugs[*].bugDescription", hasItems(is("bug description"))))
                .andExpect(jsonPath("$.bugs[*].author.name", hasItems(is("author name"))))
                .andExpect(jsonPath("$.bugs[*].tags[*].title", hasItems(is("tag title"))))
                .andExpect(status().isOk());
    }

    @Test
    public void createAlreadyExistingBug() throws Exception {
        when(bugService.create(any(Bug.class))).thenThrow(new BugAlreadyExistsException());

        mockMvc.perform(post("/rest/reporting/bugs")
                .content("{\"bugDescription\":\"Test Description\"}")
                .contentType(MediaType.APPLICATION_JSON))
//                .andDo(print())
                .andExpect(status().isConflict());
    }

    @Test
    public void createIllegalBug() throws Exception {
        when(bugService.create(any(Bug.class))).thenThrow(new IllegalArgumentException());

        mockMvc.perform(post("/rest/reporting/bugs")
                .content("{\"bugDescription\":\"Test Description\"}")
                .contentType(MediaType.APPLICATION_JSON))
//                .andDo(print())
                .andExpect(status().isBadRequest());
    }

    @Test
    public void updateNotExistingBug() throws Exception {
        when(bugService.update(any(Bug.class))).thenThrow(new BugNotFoundException());

        mockMvc.perform(put("/rest/reporting/bugs/1")
                .content("{\"bugDescription\":\"Test Description\"}")
                .contentType(MediaType.APPLICATION_JSON))
//                .andDo(print())
                .andExpect(status().isNotFound());
    }

    @Test
    public void updateIllegalBug() throws Exception {
        when(bugService.update(any(Bug.class))).thenThrow(new IllegalArgumentException());

        mockMvc.perform(put("/rest/reporting/bugs/1")
                .content("{\"bugDescription\":\"Test Description\"}")
                .contentType(MediaType.APPLICATION_JSON))
//                .andDo(print())
                .andExpect(status().isBadRequest());
    }

    private Author initializeDefaultAuthor() {
        Author author = new Author();
        author.setName("author name");
        return author;
    }

    private List<Tag> initializeDefaultTagSet() {
        List<Tag> tags = new ArrayList<Tag>(1);

        Tag tag = new Tag();
        tag.setTitle("tag title");
        tags.add(tag);

        return tags;
    }
}
