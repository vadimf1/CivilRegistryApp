package org.example.models;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class GetApplStatusResponse {

    @JsonProperty("data")
    private Data data;

    @JsonProperty("requestId")
    private String requestId;

    @Getter
    @Setter
    public static class Data {

        @JsonProperty("dateofapplication")
        private String dateOfApplication;

        @JsonProperty("kindofapplication")
        private String kindOfApplication;

        @JsonProperty("statusofapplication")
        private String statusOfApplication;
    }
}
