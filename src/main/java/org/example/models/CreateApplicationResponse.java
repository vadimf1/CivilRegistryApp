package org.example.models;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class CreateApplicationResponse {

    @JsonProperty("data")
    private Data data;

    @JsonProperty("requestId")
    private String requestId;

    @Getter
    @Setter
    public static class Data {

        @JsonProperty("applicantid")
        private Integer applicantId;

        @JsonProperty("citizenid")
        private Integer citizenId;

        @JsonProperty("applicationid")
        private Integer applicationId;

        @JsonProperty("merrigecertificateid")
        private Integer marriageCertificateId;

        @JsonProperty("birthcertificateid")
        private Integer birthCertificateId;

        @JsonProperty("deathcertificateid")
        private Integer deathCertificateId;
    }
}
