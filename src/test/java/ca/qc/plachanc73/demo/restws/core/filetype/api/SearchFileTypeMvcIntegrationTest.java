package ca.qc.plachanc73.demo.restws.core.filetype.api;

import ca.qc.plachanc73.demo.restws.api.FileTypeApiController;
import ca.qc.plachanc73.demo.restws.config.json.DefaultObjectMapper;
import ca.qc.plachanc73.demo.restws.core.filetype.service.FileTypeSearchService;
import ca.qc.plachanc73.demo.restws.core.filetype.service.dto.FileTypeDto;
import ca.qc.plachanc73.demo.restws.core.filetype.service.dto.FileTypeSearchResultDto;
import ca.qc.plachanc73.demo.restws.dto.FileTypeSearchResult;
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
        FileTypeApiController.class,
        FileTypeApiDelegateImpl.class,
        FileTypeSearchResultMapperImpl.class,
})
@WebMvcTest
class SearchFileTypeMvcIntegrationTest {

    public static final String UTF_8 = StandardCharsets.UTF_8.name();

    @Autowired
    MockMvc mockMvc;

    @MockitoBean
    FileTypeSearchService fileTypeSearchService;

    @BeforeAll
    static void beforeAllTests() {
    }

    protected FileTypeDto generateFileTypeDto() {
        return FileTypeDto.builder()
                .name("ABC")
                .documentTypeCode("GENX")
                .build();
    }

    private FileTypeSearchResultDto generateFileTypeSearchResultDtoFromRequest() {
        FileTypeDto fileTypeDto = generateFileTypeDto();
        return FileTypeSearchResultDto.builder()
                .totalResults(1L)
                .results(List.of(
                        fileTypeDto
                ))
                .build();
    }

    @BeforeEach
    void beforeEachTest() {
        // No generic setup for now
    }

    @DisplayName(
            "Given a valid request, when the service Search File Type is called then it returns the File Types found. (HTTP Code 200)")
    @Test
    void scenarioGood1() throws Exception {
        //Given - Mock the service
        FileTypeSearchResultDto fileTypeSearchResultDto = generateFileTypeSearchResultDtoFromRequest();
        when(fileTypeSearchService.searchFileType()).thenReturn(fileTypeSearchResultDto);

        //Given - Build the expected response
        FileTypeSearchResult expectedFileTypeSearchResult =
                new FileTypeSearchResultMapperImpl().fileTypeSearchResultDtoToFileTypeSearchResult(
                        fileTypeSearchResultDto);
        String expectedJson = DefaultObjectMapper.getInstance().writeValueAsString(expectedFileTypeSearchResult);

        //When
        mockMvc.perform(MockMvcRequestBuilders
                        .get("/demo/v1/file-types")
                        .characterEncoding(UTF_8)
                        .contentType(MediaType.APPLICATION_JSON)
                )
                .andExpect(MockMvcResultMatchers.status().isOk()) //Then verify the http status code
                .andExpect(MockMvcResultMatchers.content().json(expectedJson));  //Then verify the http response body
    }
}
