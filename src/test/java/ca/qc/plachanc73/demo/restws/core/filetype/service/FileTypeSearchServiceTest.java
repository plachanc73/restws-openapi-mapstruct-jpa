package ca.qc.plachanc73.demo.restws.core.filetype.service;

import ca.qc.plachanc73.demo.restws.core.filetype.repository.FileTypeSearchRepository;
import ca.qc.plachanc73.demo.restws.core.filetype.service.dto.FileTypeSearchResultDto;
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
        FileTypeDtoMapperImpl.class})
class FileTypeSearchServiceTest extends BaseFileTypeSearchServiceTest {

    private FileTypeSearchService fileTypeSearchService;
    @Autowired
    private FileTypeSearchRepository fileTypeSearchRepository;
    @Autowired
    private FileTypeDtoMapper fileTypeDtoMapper;

    public static Stream<Arguments> prepareScenarios() {
        return Stream.of(
                Arguments.of("All visible",
                        FileTypeSearchResultDto.builder()
                                .totalResults(3L)
                                .results(FILE_TYPE_ALL_NAME_ASC)
                                .build())
        );
    }

    @BeforeEach
    void beforeEach() {
        fileTypeSearchService = new FileTypeSearchService(fileTypeSearchRepository, fileTypeDtoMapper);
    }

    @DisplayName("Given a list of File Types, when the service searches for file types then it returns only visible file types.")
    @ParameterizedTest(name = "{0}")
    @MethodSource("prepareScenarios")
    void scenarioGood2(String description, FileTypeSearchResultDto expectedResult) {
        //Given
        //See basic_scenario_file_type.sql

        //When
        FileTypeSearchResultDto fileTypeSearchResultDto = fileTypeSearchService.searchFileType();

        //Then
        assertThat(fileTypeSearchResultDto).usingRecursiveComparison().isEqualTo(expectedResult);
    }
}