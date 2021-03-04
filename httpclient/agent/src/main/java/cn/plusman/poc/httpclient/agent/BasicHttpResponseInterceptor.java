/*
 * Copyright (c) 2021. <plusmancn@gmail.com> All Rights Reversed.
 * DO NOT ALTER OR REMOVE COPYRIGHT NOTICES OR THIS FILE HEADER.
 */

package cn.plusman.poc.httpclient.agent;

import javassist.*;
import lombok.extern.slf4j.Slf4j;

import java.io.IOException;
import java.lang.instrument.ClassFileTransformer;
import java.lang.instrument.IllegalClassFormatException;
import java.security.ProtectionDomain;

/**
 * @author plusman
 * @since 2021/3/3 10:54 AM
 */
@Slf4j
public class BasicHttpResponseInterceptor implements ClassFileTransformer {
    private final static String CLASS_NAME = "org/apache/http/message/BasicHttpResponse";
    private final static String HACK_METHOD = "getEntity";
    
    @Override
    public byte[] transform(ClassLoader loader, String className, Class<?> classBeingRedefined, ProtectionDomain protectionDomain, byte[] classfileBuffer) throws IllegalClassFormatException {
        if (className.equals(CLASS_NAME)) {
            String finalTargetClassName = CLASS_NAME.replaceAll("\\/", ".");
            log.info("Found {}", className);
            
            try {
                ClassPool cp = ClassPool.getDefault();
                CtClass cc = cp.get(finalTargetClassName);
                CtMethod method = cc.getDeclaredMethod(HACK_METHOD);
                
                
                StringBuilder sb = new StringBuilder();
                sb.append("org.apache.http.entity.BufferedHttpEntity newEntity = new org.apache.http.entity.BufferedHttpEntity(this.entity);");
                sb.append("org.apache.http.HttpEntity originalEntity = this.entity;");
                sb.append("this.entity = newEntity;");
                sb.append("return originalEntity;");
                method.insertBefore(sb.toString());
                
                cc.detach();
                
                return cc.toBytecode();
            } catch (NotFoundException | CannotCompileException | IOException e) {
                log.error("Transform {} fail", CLASS_NAME, e);
            }
        }
        
        return null;
    }
}