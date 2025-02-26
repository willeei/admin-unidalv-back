package com.willeei.admin.unidalv.domain.exceptions;

import java.io.Serial;

import com.willeei.admin.unidalv.domain.validation.handler.Notification;

public class NotificationException extends DomainException {

    @Serial
    private static final long serialVersionUID = 2290132700855462795L;

    public NotificationException(final String aMessage, final Notification notification) {
        super(aMessage, notification.getErrors());
    }
}
