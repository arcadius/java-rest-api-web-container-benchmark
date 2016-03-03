package com.menelic.jwcb.jetty.resource;


import com.menelic.jwcb.common.resource.AbstractApiResourceITest;

public class ApiResourceIITest extends AbstractApiResourceITest {
    @Override
    protected String expectedServerSignature() {
        return "Jetty";
    }
}
