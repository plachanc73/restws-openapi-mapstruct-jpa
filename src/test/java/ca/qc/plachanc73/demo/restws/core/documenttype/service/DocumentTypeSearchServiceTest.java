package ca.qc.plachanc73.demo.restws.core.documenttype.service;

import ca.qc.plachanc73.demo.restws.core.documenttype.repository.DocumentTypeSearchRepository;
import ca.qc.plachanc73.demo.restws.core.documenttype.service.dto.DocumentTypeSearchResultDto;
import io.zonky.test.db.AutoConfigureEmbeddedDatabase;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.context.annotation.ComponentScan;

import java.util.stream.Stream;

import static org.assertj.core.api.Assertions.assertThat;

@DataJpaTest
@AutoConfigureEmbeddedDatabase
@ComponentScan(basePackageClasses = {
        DocumentTypeDtoMapperImpl.class})
class DocumentTypeSearchServiceTest extends BaseDocumentTypeSearchServiceTest {

    private DocumentTypeSearchService documentTypeSearchService;
    @Autowired
    private DocumentTypeSearchRepository documentTypeSearchRepository;
    @Autowired
    private DocumentTypeDtoMapper documentTypeDtoMapper;

    public static Stream<Arguments> prepareScenarios() {
        return Stream.of(
                Arguments.of("All visible",
                        DocumentTypeSearchResultDto.builder()
                                .totalResults(2L)
                                .results(DOCUMENT_TYPE_ALL_NAME_ASC)
                                .build())
        );
    }

    @BeforeEach
    void beforeEach() {
        documentTypeSearchService = new DocumentTypeSearchService(documentTypeSearchRepository, documentTypeDtoMapper);
    }

    @DisplayName("Given a list of Document Types, when the service searches for document types then it returns only visible document types.")
    @ParameterizedTest(name = "{0}")
    @MethodSource("prepareScenarios")
    void scenarioGood1(String description, DocumentTypeSearchResultDto expectedResult) {
        //Given
        //See basic_scenario_file_type.sql

        //When
        DocumentTypeSearchResultDto documentTypeSearchResultDto = documentTypeSearchService.searchDocumentType();

        //Then
        assertThat(documentTypeSearchResultDto).usingRecursiveComparison().isEqualTo(expectedResult);
    }
}