package ca.qc.plachanc73.demo.restws.core.documenttype.api;

import ca.qc.plachanc73.demo.restws.api.DocumentTypeApiController;
import ca.qc.plachanc73.demo.restws.config.json.DefaultObjectMapper;
import ca.qc.plachanc73.demo.restws.core.documenttype.service.DocumentTypeSearchService;
import ca.qc.plachanc73.demo.restws.core.documenttype.service.dto.DocumentTypeDto;
import ca.qc.plachanc73.demo.restws.core.documenttype.service.dto.DocumentTypeSearchResultDto;
import ca.qc.plachanc73.demo.restws.dto.DocumentTypeSearchResult;
import org.junit.jupiter.api.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.bean.override.mockito.MockitoBean;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

import java.nio.charset.StandardCharsets;
import java.util.List;

import static org.mockito.Mockito.when;

@AutoConfigureMockMvc
@ContextConfiguration(classes = {
        DocumentTypeApiController.class,
        DocumentTypeApiDelegateImpl.class,
        DocumentTypeSearchResultMapperImpl.class,
})
@WebMvcTest
class SearchDocumentTypeMvcIntegrationTest {

    public static final String UTF_8 = StandardCharsets.UTF_8.name();

    @Autowired
    MockMvc mockMvc;

    @MockitoBean
    DocumentTypeSearchService documentTypeSearchService;

    @BeforeAll
    static void beforeAllTests() {
    }

    protected DocumentTypeDto generateDocumentTypeDto() {
        return DocumentTypeDto.builder()
                .code("GENX")
                .name("Name GENX")
                .build();
    }

    private DocumentTypeSearchResultDto generateDocumentTypeSearchResultDtoFromRequest() {
        DocumentTypeDto documentTypeDto = generateDocumentTypeDto();
        return DocumentTypeSearchResultDto.builder()
                .totalResults(1L)
                .results(List.of(
                        documentTypeDto
                ))
                .build();
    }

    @BeforeEach
    void beforeEachTest() {
        // No generic setup for now
    }

    @DisplayName(
            "Given a valid request, when the service Search Document Type is called then it returns the Document Types found. (HTTP Code 200)")
    @Test
    void scenarioGood1() throws Exception {
        //Given - Mock the service
        DocumentTypeSearchResultDto documentTypeSearchResultDto = generateDocumentTypeSearchResultDtoFromRequest();
        when(documentTypeSearchService.searchDocumentType()).thenReturn(documentTypeSearchResultDto);

        //Given - Build the expected response
        DocumentTypeSearchResult expectedDocumentTypeSearchResult =
                new DocumentTypeSearchResultMapperImpl().documentTypeSearchResultDtoToDocumentTypeSearchResult(
                        documentTypeSearchResultDto);
        String expectedJson = DefaultObjectMapper.getInstance().writeValueAsString(expectedDocumentTypeSearchResult);

        //When
        mockMvc.perform(MockMvcRequestBuilders
                        .get("/demo/v1/document-types")
                        .characterEncoding(UTF_8)
                        .contentType(MediaType.APPLICATION_JSON)
                )
                .andExpect(MockMvcResultMatchers.status().isOk()) //Then verify the http status code
                .andExpect(MockMvcResultMatchers.content().json(expectedJson));  //Then verify the http response body
    }
}
