package dsi.javanaise;


import com.fasterxml.jackson.databind.ObjectMapper;
import dsi.javanaise.controllers.JavanaiseController;
import dsi.javanaise.model.Javanaise;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.restdocs.AutoConfigureRestDocs;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;

import static org.springframework.restdocs.mockmvc.MockMvcRestDocumentation.document;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@RunWith(SpringRunner.class)
@WebMvcTest(JavanaiseController.class)
@AutoConfigureRestDocs(outputDir = "target/snippets")
public class ControllerTest  {

    @Autowired
    private MockMvc mockMvc;

    @Test
    public void shouldReturnEmptyDictionary() throws Exception {
        this.mockMvc.perform(get("/api/dictionary/javanais/v1.0/javanaises/dictionary")).andDo(print()).andExpect(status().isOk())
                .andDo(document("dictionary"));
    }


    @Test
    public void shouldReturnJavanaisAfterPost() throws Exception {

        Javanaise jav = new Javanaise("salut");
        jav.translateToJavanaise();

        ObjectMapper objectMapper = new ObjectMapper();
        String jsonJav = objectMapper.writeValueAsString(jav);

        this.mockMvc.perform(post("/api/dictionary/javanais/v1.0/javanaises").contentType(MediaType.APPLICATION_JSON).content(jsonJav).characterEncoding("utf-8"))
                .andExpect(status().isCreated())
                .andDo(document("createJavanaise"));
    }

    @Test
    public void shouldReturn404AfterGet() throws Exception {

        this.mockMvc.perform(get("/api/dictionary/javanais/v1.0/javanaises/salut"))
                .andExpect(status().isNotFound())
                .andDo(document("getJavanaise404"));
    }

    @Test
    public void shouldReturnJavanaiseAfterGet() throws Exception {

        Javanaise jav = new Javanaise("salut");
        jav.translateToJavanaise();

        ObjectMapper objectMapper = new ObjectMapper();
        String jsonJav = objectMapper.writeValueAsString(jav);
        this.mockMvc.perform(post("/api/dictionary/javanais/v1.0/javanaises").contentType(MediaType.APPLICATION_JSON)
                .content(jsonJav)
                .characterEncoding("utf-8"));

        this.mockMvc.perform(get("/api/dictionary/javanais/v1.0/javanaises/salut"))
                .andExpect(status().isOk())
                .andDo(document("getJavanaise"));
    }
}