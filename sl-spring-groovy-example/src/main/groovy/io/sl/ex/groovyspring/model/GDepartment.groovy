package io.sl.ex.groovyspring.model

import groovy.transform.Canonical
import org.springframework.stereotype.Component

@Component
@Canonical
class GDepartment {
    int id
    List persons = new ArrayList()
}
