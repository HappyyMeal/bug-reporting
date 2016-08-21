package upwork.sample.mvc;


import org.junit.Before;
import org.junit.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import upwork.sample.core.models.entities.Tag;
import upwork.sample.core.services.TagService;
import upwork.sample.core.services.util.TagList;
import upwork.sample.rest.mvc.TagController;

import java.util.ArrayList;
import java.util.List;

import static org.hamcrest.Matchers.endsWith;
import static org.hamcrest.Matchers.hasItems;
import static org.mockito.Matchers.anyString;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

public class TagControllerTest {

    @InjectMocks
    private TagController tagController;

    @Mock
    private TagService tagService;

    private MockMvc mockMvc;

    @Before
    public void setup() {
        MockitoAnnotations.initMocks(this);
        mockMvc = MockMvcBuilders.standaloneSetup(tagController).build();
    }

    @Test
    public void readAllTags() throws Exception{
        TagList tagList = predefineDefaultеTagList();

        when(tagService.readAllTags()).thenReturn(tagList);

        mockMvc.perform(get("/rest/reporting/tags"))
//                .andDo(print())
                .andExpect(jsonPath("$.tags[*].title", hasItems(endsWith("titleA"), endsWith("titleB"))))
                .andExpect(status().isOk());
    }

    @Test
    public void read10MostUsedTagsMatchedTitle() throws Exception{
        TagList tagList = predefineDefaultеTagList();
        when(tagService.read10MostUsedTagsMatchedTitle(anyString())).thenReturn(tagList);

        mockMvc.perform(get("/rest/reporting/tags//10MostUsed/tag"))
//                .andDo(print())
                .andExpect(jsonPath("$.tags[*].title", hasItems(endsWith("titleA"), endsWith("titleB"))))
                .andExpect(status().isOk());
    }

    private TagList predefineDefaultеTagList() {
        List<Tag> tags = new ArrayList<Tag>(2);

        Tag tagA = new Tag();
        tagA.setTagId(1);
        tagA.setTitle("tagA titleA");
        tags.add(tagA);

        Tag tagB = new Tag();
        tagB.setTagId(2);
        tagB.setTitle("tagB titleB");
        tags.add(tagB);

        TagList tagList = new TagList(tags);
        return tagList;
    }
}
