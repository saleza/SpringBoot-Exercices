package com.openclassrooms.webapp.repository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

import com.openclassrooms.webapp.CustomProperties;
import com.openclassrooms.webapp.model.Employee;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@Component
public class EmployeeProxy {

    @Autowired
    private CustomProperties props;

    /**
     * Get all employees
     * @return An iterable of all employees
     */

    public Iterable<Employee> getEmployees() {
        String baseApiUrl = props.getApiUrl(); // Grâce à notre objet CustomProperties, on récupère l’URL de l’API
        String getEmployeesUrl = baseApiUrl + "/employees"; // On complète l’URL de l’API par le path de l'endpoint à joindre

        RestTemplate restTemplate = new RestTemplate(); // on instancie notre objet RestTemplate.
        ResponseEntity<Iterable<Employee>> response = restTemplate.exchange( // on appelle la méthode exchange en transmettant :
                getEmployeesUrl, // l’URL
                HttpMethod.GET, // la méthode HTTP (grâce à l’enum HttpMethod)
                null, // Null en lieu et place d’un objet HttpEntity, ainsi on laisse un comportement par défaut
                new ParameterizedTypeReference<Iterable<Employee>>() {} // le type retour, ici je suis obligé d’utiliser un objet ParameterizedTypeReference
                // car /employees renvoie un objet Iterable<Employee>.
                // Mais si l’endpoint renvoie un objet simple, alors il suffira d’indiquer <Object>.class
        );

        log.debug("Get Employees call " + response.getStatusCode().toString());

        return response.getBody(); // on récupère notre objet Iterable<Employee> grâce à la méthode getBody() de l’objet Response.
    }

    /**
     * Get an employee by the id
     * @param id The id of the employee
     * @return The employee which matches the id
     */
    public Employee getEmployee(int id) {
        String baseApiUrl = props.getApiUrl();
        String getEmployeeUrl = baseApiUrl + "/employee/" + id;

        RestTemplate restTemplate = new RestTemplate();
        ResponseEntity<Employee> response = restTemplate.exchange(
                getEmployeeUrl,
                HttpMethod.GET,
                null,
                Employee.class
        );

        log.debug("Get Employee call " + response.getStatusCode().toString());

        return response.getBody();
    }


    /**
     * Delete an employee using exchange method of RestTemplate
     * instead of delete method in order to log the response status code.
     * @param e The employee to delete
     */
    public void deleteEmployee(int id) {
        String baseApiUrl = props.getApiUrl();
        String deleteEmployeeUrl = baseApiUrl + "/employee/" + id;

        RestTemplate restTemplate = new RestTemplate();
        ResponseEntity<Void> response = restTemplate.exchange(
                deleteEmployeeUrl,
                HttpMethod.DELETE,
                null,
                Void.class);

        log.debug("Delete Employee call " + response.getStatusCode().toString());
    }

}