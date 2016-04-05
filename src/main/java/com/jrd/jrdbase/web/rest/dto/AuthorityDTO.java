package com.jrd.jrdbase.web.rest.dto;

/**
 * Created by jakub on 05.04.16.
 */

import com.jrd.jrdbase.domain.Authority;

import javax.validation.constraints.Size;

/**
 * A dto represents only authorities (for manage)
 */
public class AuthorityDTO {
    @Size(min = 0, max = 50)
    private String name;

    public AuthorityDTO(Authority authority) {
        name = authority.getName();
    }

    public AuthorityDTO(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }

    @Override
    public String toString() {
        return "AuthorityDTO{" +
            "name='" + name + '\'' +
            '}';
    }
}
