package org.example.models;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Builder
public class ChangeApplicationStatusRequest {

    @JsonProperty("action")
    private String action;

    @JsonProperty("applId")
    private Integer appId;

    @JsonProperty("staffid")
    private Integer staffId;
}
