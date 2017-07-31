package com.eunion.portals.service;

import com.eunion.portals.dto.MailDto;

/**
 * Created by ys on 2016/9/13.
 */
public interface SendMessageService {

    boolean sendEmail(MailDto mailDto);
}
