package eu.senla.regoffice.constants;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@Getter
public enum ApplicationStatus {
    APPROVED("approved"),
    REJECTED("rejected"),
    APPROVED_RUS("Одобрена"),
    REJECTED_RUS("Отклонена");

    private final String name;
}
