package com.example.consuming_rest.model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@JsonIgnoreProperties(ignoreUnknown = true) // To indicate that any properties not bound in this type should be ignored
public record Quote (String type, Value value) {
}
