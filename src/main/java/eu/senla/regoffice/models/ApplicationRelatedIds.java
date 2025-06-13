package eu.senla.regoffice.models;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Builder
public class ApplicationRelatedIds {
    private int applicantId;
    private int citizenId;
}
