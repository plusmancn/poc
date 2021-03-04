/*
 * Copyright (c) 2021. <plusmancn@gmail.com> All Rights Reversed.
 * DO NOT ALTER OR REMOVE COPYRIGHT NOTICES OR THIS FILE HEADER.
 */

package cn.plusman.poc.httpclient.agent;

import lombok.extern.slf4j.Slf4j;

import java.lang.instrument.Instrumentation;

/**
 * @author plusman
 * @since 2021/3/3 10:56 AM
 */
@Slf4j
public class SimpleHttpClientAgent {
    public static void premain(String agentArgs, Instrumentation ins) {
        log.info("In premain method");
        ins.addTransformer(new BasicHttpResponseInterceptor(), true);
    }
    
    public static void agentmain(String agentArgs, Instrumentation inst) {
        log.info("In agentmain method");
    }
}
