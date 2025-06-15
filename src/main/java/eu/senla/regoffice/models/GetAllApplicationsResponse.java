package eu.senla.regoffice.models;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Getter;
import lombok.Setter;

@JsonIgnoreProperties(ignoreUnknown = true)
@Getter
@Setter
public class GetAllApplicationsResponse {

    @JsonProperty("total")
    private String total;

    @JsonProperty("requestId")
    private String requestId;
}
