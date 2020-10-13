package io.sl.ex.groovyspring.service;

import io.sl.ex.groovyspring.model.GDepartment;
import io.sl.ex.groovyspring.model.GPerson;

import java.util.List;

public interface DepartmentService {
    List<GPerson> getPersons();
}
