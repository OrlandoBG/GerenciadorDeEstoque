package io.github.OrlandoBG.demo.controller;


import com.fasterxml.jackson.databind.ObjectMapper;
import io.github.OrlandoBG.demo.model.entities.ItemDeEstoque;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.ResultActions;

import javax.transaction.Transactional;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
@AutoConfigureMockMvc
@Transactional
public class ItemDeEstoqueControllerIT {

    @Autowired
    private MockMvc mockMvc;


    @Test
    public void obterTodosDeveriaRetornarUmaListaComItensDeEstoque()throws Exception{
        ResultActions result =
                mockMvc.perform(get("/itens-de-Estoque")
                        .contentType(MediaType.APPLICATION_JSON));

        result.andExpect(status().isOk());
        result.andExpect(jsonPath("$[0].id").value(1L));
        result.andExpect(jsonPath("$[0].nome").value("coca cola"));
        result.andExpect(jsonPath("$[1].id").value(2L));
        result.andExpect(jsonPath("$[1].nome").value("café"));
    }

    @Test
    public void obterPorIdDeveriaRetornarItemDeEstoque()throws Exception{

        ResultActions result =
                mockMvc.perform(get("/itens-de-Estoque/{id}",1)
                        .contentType(MediaType.APPLICATION_JSON));

        result.andExpect(status().isOk());
        result.andExpect(jsonPath("$.id").value(1L));
        result.andExpect(jsonPath("$.nome").value("coca cola"));

    }

    @Test
    public void salvarDeveriaRetornarItemDeEstoque()throws Exception{
        String nome = "coca cola";
        String unidadeDeMedida = "Packs";
        ItemDeEstoque itemDeEstoque = new ItemDeEstoque(1L,"coca cola","Packs",6,20,3);

        ObjectMapper objectMapper = new ObjectMapper();
        String jsonBody = objectMapper.writeValueAsString(itemDeEstoque);

        ResultActions result =
                mockMvc.perform(post("/itens-de-Estoque")
                        .content(jsonBody)
                        .contentType(MediaType.APPLICATION_JSON));
        result.andExpect(status().isCreated());
        result.andExpect(jsonPath("$.id").isNotEmpty());
        result.andExpect(jsonPath("$.nome").value(nome));
        result.andExpect(jsonPath("$.unidadeDeMedida").value(unidadeDeMedida));
    }

}
