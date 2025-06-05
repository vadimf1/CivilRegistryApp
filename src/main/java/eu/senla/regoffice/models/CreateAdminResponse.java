package eu.senla.regoffice.models;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class CreateAdminResponse {
    @JsonProperty("data")
    private Data data;
    @JsonProperty("requestId")
    private String requestId;

    @Getter
    @Setter
    public static class Data {
        @JsonProperty("staffid")
        private Integer staffId;
    }
}
