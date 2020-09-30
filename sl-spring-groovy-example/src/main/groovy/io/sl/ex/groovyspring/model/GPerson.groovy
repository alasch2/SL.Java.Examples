package io.sl.ex.groovyspring.model

import groovy.transform.Canonical

@Canonical
class GPerson {
    String firstName
    String lastName

    GPerson(String firstName, String lastName) {
        this.firstName = firstName
        this.lastName = lastName
    }
}

