package com.example.payroll;

import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;

public class URIHelper {
    public static URI getLocation(
            String key,
            String value
    ) {
        return ServletUriComponentsBuilder
                .fromCurrentRequest()
                .path(String.format("/{%s}", key))
                .buildAndExpand(value)
                .toUri();
    }
}
