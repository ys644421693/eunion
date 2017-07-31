package com.app.base.service.impl;

import com.app.base.service.MessageManage;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * Created by BF100365 on 2016/12/22.
 */

class MessageManageImpl implements MessageManage {

    private Logger logger = LoggerFactory.getLogger(MessageManageImpl.class);

    @Override
    public void sendMessage(String sessionId) {
        logger.info("--------------sessionId----------------");
    }
}
