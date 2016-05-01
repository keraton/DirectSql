package com.github.keraton.web.controller;

import com.github.keraton.DirectSqlApplication;
import com.github.keraton.model.QueryBuilder;
import com.github.keraton.services.InitializerService;
import com.github.keraton.services.QueryListService;
import com.github.keraton.services.QueryResultService;
import com.github.keraton.web.dto.Detail;
import com.google.common.collect.ImmutableMap;
import org.json.JSONObject;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.boot.test.SpringApplicationConfiguration;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import java.util.Arrays;

import static com.google.common.collect.ImmutableMap.of;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@RunWith(SpringJUnit4ClassRunner.class)
@SpringApplicationConfiguration(DirectSqlApplication.class)
public class QueryRestControllerIT {

    private MockMvc mockMvc;

    @Mock
    private QueryListService queryListService;

    @Mock
    private InitializerService initializerService;

    @Mock
    private QueryResultService queryResultService;

    @InjectMocks
    private QueryRestController queryRestController;


    @Before
    public void setup() {
        MockitoAnnotations.initMocks(this);
        this.mockMvc = MockMvcBuilders.standaloneSetup(queryRestController).build();
    }

    @Test
    public void should_show_list () throws Exception {
        // Given
        when(queryListService.list()).thenReturn(Arrays.asList("query1", "query2"));

        // When
        mockMvc.perform(get("/list"))

        // Then
            .andExpect(status().isOk())
            .andExpect(content().json("[\"query1\", \"query2\"]"));
    }

    @Test
    public void should_init () throws Exception {
        // Given

        // When
        mockMvc.perform(get("/init"))

        // Then
            .andExpect(status().isOk());

        verify(initializerService).init();
    }

    @Test
    public void should_search () throws Exception {
        // Given
        when(queryListService.search("query1"))
                .thenReturn(new QueryBuilder()
                                .setName("query1")
                                .setDatasource("datasource")
                                .setDescription("description")
                                .setMainSql("mainsql")
                                .createQuery());

        // When
        mockMvc.perform(get("/search?name=query1"))

        // Then
            .andExpect(status().isOk())
            .andExpect(content().json("{\"name\":\"query1\"," +
                                       "\"description\":\"description\"," +
                                        "\"mainSql\":\"mainsql\"," +
                                        "\"datasource\":\"datasource\"," +
                                        "\"detailSql\":null," +
                                        "\"keys\":[]}"));
    }

    @Test
    public void should_getResult () throws Exception {
        // Given
        when(queryResultService.getResult("query1"))
                .thenReturn(Arrays.asList(
                        of("NAME", "TEST1", "ID", "1"),
                        of("NAME", "TEST2", "ID", "2")
                ));
        when(queryListService.search("query1"))
                .thenReturn(new QueryBuilder()
                                .setName("query1")
                                .createQuery());

        // When
        mockMvc.perform(get("/getResult?name=query1"))

        // Then
            .andExpect(status().isOk())
            .andExpect(content().json("{\"hasDetail\":false,\"" +
                                        "rows\":[{\"NAME\":TEST1,\"ID\":\"1\"}," +
                                                "{\"NAME\":TEST2,\"ID\":\"2\"}]}"));
    }

    @Test
    public void should_getResult_filtered () throws Exception {
        // Given
        when(queryResultService.getResult("query1", "queryFilter", 5, null))
                .thenReturn(Arrays.asList(
                        of("NAME", "TEST1", "ID", "1"),
                        of("NAME", "TEST2", "ID", "2")));

        when(queryListService.search("query1"))
                .thenReturn(new QueryBuilder()
                                .setName("query1")
                                .createQuery());

        // When
        mockMvc.perform(get("/executeFilter?name=query1" +
                                            "&queryFilter=queryFilter" +
                                            "&limit=5"))

        // Then
            .andExpect(status().isOk())
            .andExpect(content().json("{\"hasDetail\":false,\"" +
                                        "rows\":[{\"NAME\":TEST1,\"ID\":\"1\"}," +
                                                "{\"NAME\":TEST2,\"ID\":\"2\"}]}"));
    }

    @Test
    public void should_get_details () throws Exception {
        // Given
        when(queryResultService.getDetailResult("query1", of("NAME", "TEST1", "ID", "1")))
                .thenReturn(
                        of("NAME", "TEST1", "ID", "1", "DETAIL", "DETAIL"));


        // When
        mockMvc.perform(post("/detail")
                .contentType(MediaType.APPLICATION_JSON)
                .content("{\"selectedQuery\":\"query1\"," +
                           "\"map\":{\"NAME\":\"TEST1\",\"ID\":\"1\"}}"))

        // Then
            .andExpect(status().isOk())
            .andExpect(content().json("{\"NAME\":TEST1,\"ID\":\"1\",\"DETAIL\":\"DETAIL\"}"));
    }


}