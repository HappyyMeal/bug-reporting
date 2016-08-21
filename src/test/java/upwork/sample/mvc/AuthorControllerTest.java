package upwork.sample.mvc;

import org.junit.Before;
import org.junit.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import upwork.sample.core.models.entities.Author;
import upwork.sample.core.services.AuthorService;
import upwork.sample.core.services.util.AuthorList;
import upwork.sample.rest.mvc.AuthorController;

import java.util.ArrayList;
import java.util.List;

import static org.hamcrest.Matchers.endsWith;
import static org.hamcrest.Matchers.hasItems;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

public class AuthorControllerTest {

    @InjectMocks
    private AuthorController authorController;

    @Mock
    private AuthorService authorService;

    private MockMvc mockMvc;

    @Before
    public void setup(){
        MockitoAnnotations.initMocks(this);
        mockMvc = MockMvcBuilders.standaloneSetup(authorController).build();
    }

    @Test
    public void readAllAuthors() throws Exception{
        List<Author> authors = new ArrayList<Author>(2);

        Author authorA = new Author();
        authorA.setAuthorId(1);
        authorA.setName("authorA nameA");
        authorA.setEmail("authorA@gmailA.com");
        authors.add(authorA);

        Author authorB = new Author();
        authorB.setAuthorId(2);
        authorB.setName("authorB nameB");
        authorB.setEmail("authorB@gmailB.com");
        authors.add(authorB);

        AuthorList authorList = new AuthorList(authors);

        when(authorService.readAllAuthors()).thenReturn(authorList);

        mockMvc.perform(get("/rest/reporting/authors"))
//                .andDo(print())
                .andExpect(jsonPath("$.authors[*].name", hasItems(endsWith("nameA"), endsWith("nameB"))))
                .andExpect(jsonPath("$.authors[*].email", hasItems(endsWith("@gmailA.com"), endsWith("@gmailB.com"))))
                .andExpect(status().isOk());
    }


}
