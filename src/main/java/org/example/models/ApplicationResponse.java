package org.example.models;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class ApplicationResponse {

    @JsonProperty("data")
    private Data data;

    @JsonProperty("requestId")
    private String requestId;

    @Getter
    @Setter
    public static class Data {

        @JsonProperty("applicantid")
        private int applicantId;

        @JsonProperty("citizenid")
        private int citizenId;

        @JsonProperty("applicationid")
        private int applicationId;

        @JsonProperty("merrigecertificateid")
        private int marriageCertificateId;

        @JsonProperty("birthcertificateid")
        private int birthCertificateId;

        @JsonProperty("deathcertificateid")
        private int deathCertificateId;
    }
}
