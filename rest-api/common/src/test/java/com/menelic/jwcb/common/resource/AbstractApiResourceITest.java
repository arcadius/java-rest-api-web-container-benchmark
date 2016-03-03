package com.menelic.jwcb.common.resource;

import com.menelic.jwcb.common.AbstractApplication;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.test.SpringApplicationConfiguration;
import org.springframework.boot.test.WebIntegrationTest;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.web.client.RestTemplate;

import javax.ws.rs.core.MediaType;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.containsString;
import static org.hamcrest.core.Is.is;

@RunWith(SpringJUnit4ClassRunner.class)
@SpringApplicationConfiguration(AbstractApplication.class)
@WebIntegrationTest(randomPort = true)
public abstract class AbstractApiResourceITest {

    @Value("${local.server.port}")
    private int port;

    private RestTemplate restTemplate;

    @Before
    public void setUpAbstractApiResourceITest() {
        restTemplate = new RestTemplate();
    }

    ;

    @Test
    public void testResponse() {
        String url = String.format("http://127.0.0.1:%s/api", this.port);
        ResponseEntity<String> entity = restTemplate.getForEntity(url, String.class);
        assertThat(entity.getStatusCode(), is(HttpStatus.OK));
        assertThat(entity.getBody(), is(ApiResource.RESPONSE));
        assertThat(entity.getHeaders().getContentType().toString(), containsString(MediaType.APPLICATION_JSON));

        String actualServerSignature = entity.getHeaders().getFirst("Server");
        if (expectedServerSignature() == null) {
            assertThat(actualServerSignature, is(expectedServerSignature()));
        } else {
            assertThat(actualServerSignature, containsString(expectedServerSignature()));
        }

    }

    protected abstract String expectedServerSignature();
}
