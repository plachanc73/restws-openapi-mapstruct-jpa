package ca.qc.plachanc73.demo.restws.core.document.service.dto;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonValue;
import lombok.*;

@Builder
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class DocumentSearchCriteriaDto {

    public enum SortEnum {
        DOCUMENT_NAME_ASC("DOCUMENT_NAME_ASC"),

        DOCUMENT_NAME_DESC("DOCUMENT_NAME_DESC");

        private String value;

        SortEnum(String value) {
            this.value = value;
        }

        @JsonCreator
        public static SortEnum fromValue(String value) {
            for (SortEnum b : SortEnum.values()) {
                if (b.value.equals(value)) {
                    return b;
                }
            }
            throw new IllegalArgumentException("Unexpected value '" + value + "'");
        }

        @JsonValue
        public String getValue() {
            return value;
        }

        @Override
        public String toString() {
            return String.valueOf(value);
        }
    }

    private Long page;
    private Long size;
    private SortEnum sort;
    private DocumentSearchCriteriaFiltersDto filters;
}
