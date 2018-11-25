package com.hl.service.impl;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import com.hl.service.IService;

@Service
public class ServilceImpl implements IService {

    private static final Logger logger = LoggerFactory.getLogger(ServilceImpl.class);

    @Override
    public void action() {
        logger.info("--------------------action------------------------");

    }

}
