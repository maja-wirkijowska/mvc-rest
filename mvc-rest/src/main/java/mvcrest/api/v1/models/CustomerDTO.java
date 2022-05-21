package mvcrest.api.v1.models;

import lombok.Data;

@Data
public class CustomerDTO {

    private Long id;
    private String firstname;
    private String lastname;
    private String customerUrl;
}
