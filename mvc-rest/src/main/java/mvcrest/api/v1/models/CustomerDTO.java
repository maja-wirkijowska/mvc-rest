package mvcrest.api.v1.models;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

@Data
public class CustomerDTO {

    private Long id;
    private String firstname;
    private String lastname;

    @JsonProperty("customer_url")
    private String customerUrl;
}
