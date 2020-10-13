package io.sl.ex.groovyspring.component

import io.sl.ex.groovyspring.model.GDepartment
import io.sl.ex.groovyspring.model.GPerson
import io.sl.ex.groovyspring.service.DepartmentService
import org.springframework.stereotype.Component

@Component
class GDepartmentServiceImpl implements DepartmentService {

    private static GDepartment department = new GDepartment()

    static {
        department = new GDepartment();
        department.persons.add(new GPerson("Bill", "Gates"));
        department.persons.add(new GPerson("Steve", "Jobs"));
    }

    GDepartmentServiceImpl() {
    }

    @Override
    List<GPerson> getPersons() {
        println "getting persons"
        return department.persons;
    }
}
