package com.tiger.order.command.api.constants.enums;

import lombok.Getter;

@Getter
public enum AccountState {
    PENDING("pending", "Mfa email"),
    LOCK("lock", "Mfa sms"),
    ACTIVE("active", ""),
    DEACTIVATE("deactivate", "");

    private final String code;
    private final String description;

    AccountState(String code, String description) {
        this.code = code;
        this.description = description;
    }
}
