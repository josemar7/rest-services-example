package org.pepo.dev.service2;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponentsBuilder;

import java.net.URI;
import java.net.URISyntaxException;
import java.util.Arrays;

@RestController
@SpringBootApplication
public class Service2Application {

    @Autowired
    private RestTemplate restTemplate;

    public static void main(String[] args) {
        SpringApplication.run(Service2Application.class, args);
    }

    @GetMapping(path = "/service2")
    public String service2() {
        return "Hello service 2";
    }

//    @GetMapping(path = "/service1")
//    public String service1() throws JsonProcessingException {
//        Foo foo = new Foo();
//        foo.setId(1);
//        foo.setName("Foooooooooo pepito!!!");
//        ObjectMapper mapper = new ObjectMapper();
//        String jsonString = mapper.writeValueAsString(foo);
//        HttpEntityEnclosingRequestBase httpEntity = new HttpEntityEnclosingRequestBase() {
//            @Override
//            public String getMethod() {
//                return HttpMethod.GET.name();
//            }
//        };
//        httpEntity.addHeader(HttpHeaders.ACCEPT, MediaType.APPLICATION_JSON_VALUE);
//        httpEntity.addHeader(HttpHeaders.CONTENT_TYPE, MediaType.APPLICATION_JSON_VALUE);
//        BufferedReader bufferedReader;
//        String responseBody = "";
//        try (CloseableHttpClient client = HttpClientBuilder.create().build()) {
//            URI uri = UriComponentsBuilder.fromUri(new URI("http://localhost:8080/service-body")).build().encode().toUri();
//            httpEntity.setURI(uri);
//            httpEntity.setEntity(new StringEntity(jsonString, ContentType.APPLICATION_JSON));
//            HttpResponse response = client.execute(httpEntity);
//            bufferedReader = new BufferedReader(new InputStreamReader(response.getEntity().getContent()));
//            responseBody = bufferedReader.readLine();
//            bufferedReader.close();
//            Header authHeader = response.getFirstHeader(HttpHeaders.AUTHORIZATION);
//            if (authHeader != null) {
//                return authHeader.getValue();
//            } else {
//            }
//        } catch (URISyntaxException e) {
//        } catch (IOException e) {
//        }
//        return responseBody;
//    }
    @GetMapping(path = "/service1")
    public String service1() throws URISyntaxException {
        final HttpHeaders httpHeaders = new HttpHeaders();
        httpHeaders.setContentType(MediaType.APPLICATION_JSON);
        httpHeaders.setAccept(Arrays.asList(MediaType.APPLICATION_JSON));
        Foo foo = new Foo();
        foo.setId(1);
        foo.setName("This is the real service1");
        HttpEntity<Foo> request = new HttpEntity<>(foo, httpHeaders);
        URI uri = UriComponentsBuilder.fromUri(new URI("http://localhost:8080/service-body")).build().encode().toUri();
        return restTemplate
                .exchange(uri, HttpMethod.GET, request, String.class).getBody();
    }

    @GetMapping(path = "/service11")
    public String service11() {
        RestTemplate restTemplate = new RestTemplate();
        String fooResourceUrl
                = "http://localhost:8080/service1";
        return restTemplate.getForEntity(fooResourceUrl , String.class).getBody();
    }

    @GetMapping(path = "/service12")
    public String service12() {
        RestTemplate restTemplate = new RestTemplate();
        Foo foo = new Foo();
        foo.setId(1);
        foo.setName("Foooooooooo");
        String fooResourceUrl
                = "http://localhost:8080/service-body";
        return restTemplate.getForEntity(fooResourceUrl , String.class, foo).getBody();
    }


}
