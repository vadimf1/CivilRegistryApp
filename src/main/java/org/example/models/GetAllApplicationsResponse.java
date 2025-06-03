package org.example.models;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
public class GetAllApplicationsResponse {

    @JsonProperty("total")
    private String total;

    @JsonProperty("data")
    private List<Data> data;

    @JsonProperty("requestId")
    private String requestId;

    @Getter
    @Setter
    public static class Data {
        @JsonProperty("applicationid")
        private Integer applicationId;
        @JsonProperty("citizenid")
        private Integer citizenId;
        @JsonProperty("applicantid")
        private Integer applicantId;
        @JsonProperty("staffid")
        private Integer staffId;
        @JsonProperty("dateofapplication")
        private String dateOfApplication;
        @JsonProperty("kindofapplication")
        private String kindOfApplication;
        @JsonProperty("statusofapplication")
        private String statusOfApplication;
        @JsonProperty("channel")
        private String channel;
        @JsonProperty("image")
        private String image;
    }
}
