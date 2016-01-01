package com.menelic.jwcb.common.resource;

import com.menelic.jwcb.common.AbstractApplication;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.test.SpringApplicationConfiguration;
import org.springframework.boot.test.TestRestTemplate;
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
    protected int port;

    protected RestTemplate restTemplate = new TestRestTemplate();

    @Test
    public void testResponse() {
        ResponseEntity<String> entity = this.restTemplate
                .getForEntity("http://localhost:" + this.port + "/api", String.class);
        assertThat(entity.getStatusCode(), is(HttpStatus.OK));
        assertThat(entity.getBody(), is(ApiResource.RESPONSE));
        assertThat(entity.getHeaders().getContentType().toString(), containsString(MediaType.APPLICATION_JSON));

        if (expectedServerSignature() == null) {
            assertThat(entity.getHeaders().getFirst("Server"), is(expectedServerSignature()));
        } else {
            assertThat(entity.getHeaders().getFirst("Server"), containsString(expectedServerSignature()));
        }

    }

    protected abstract String expectedServerSignature();
}
