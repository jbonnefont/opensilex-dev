/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.opensilex.core.project.dal;

import java.net.URI;
import java.time.LocalDate;
import java.util.List;
import javax.mail.internet.InternetAddress;
import org.apache.jena.sparql.vocabulary.FOAF;
import org.apache.jena.vocabulary.DCTerms;
import org.apache.jena.vocabulary.RDFS;
import org.opensilex.core.ontology.Oeso;
import org.opensilex.sparql.annotations.SPARQLProperty;
import org.opensilex.sparql.annotations.SPARQLResource;
import org.opensilex.sparql.model.SPARQLResourceModel;
import org.opensilex.sparql.utils.ClassURIGenerator;


@SPARQLResource(
        ontology = Oeso.class,
        resource = "Project",
        graph = "project"
)
public class ProjectModel extends SPARQLResourceModel implements ClassURIGenerator<ProjectModel> {

    @SPARQLProperty(
            ontology = RDFS.class,
            property = "label",
            required = true
    )
    private String name;

    @SPARQLProperty(
            ontology = Oeso.class,
            property = "hasShortname"
    )
    private String shortname;

    @SPARQLProperty(
            ontology = DCTerms.class,
            property = "description"
    )
    private String description;

    @SPARQLProperty(
            ontology = Oeso.class,
            property = "hasObjective"
    )
    private String objective;

    @SPARQLProperty(
            ontology = Oeso.class,
            property = "startDate"
    )
    private LocalDate startDate;

    @SPARQLProperty(
            ontology = Oeso.class,
            property = "endDate"
    )
    private LocalDate endDate;

    @SPARQLProperty(
            ontology = Oeso.class,
            property = "hasKeyword"
    )
    private List<String> keywords;

    @SPARQLProperty(
            ontology = FOAF.class,
            property = "homepage"
    )
    private URI homePage;

    @SPARQLProperty(
            ontology = Oeso.class,
            property = "hasAdministrativeContact"
    )
    private List<InternetAddress> administrativeContacts;

    @SPARQLProperty(
            ontology = Oeso.class,
            property = "hasCoordinator"
    )
    private List<InternetAddress> coordinators;

    @SPARQLProperty(
            ontology = Oeso.class,
            property = "hasScientificContact"
    )
    private List<InternetAddress> scientificContacts;

    @SPARQLProperty(
            ontology = Oeso.class,
            property = "hasRelatedProject"
    )
    private List<ProjectModel> relatedProjects;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getShortname() {
        return shortname;
    }

    public void setShortname(String shortname) {
        this.shortname = shortname;
    }

    public List<ProjectModel> getRelatedProjects() {
        return relatedProjects;
    }

    public void setRelatedProjects(List<ProjectModel> relatedProjects) {
        this.relatedProjects = relatedProjects;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getObjective() {
        return objective;
    }

    public void setObjective(String objective) {
        this.objective = objective;
    }

    public LocalDate getStartDate() {
        return startDate;
    }

    public void setStartDate(LocalDate startDate) {
        this.startDate = startDate;
    }

    public LocalDate getEndDate() {
        return endDate;
    }

    public void setEndDate(LocalDate endDate) {
        this.endDate = endDate;
    }

    public List<String> getKeywords() {
        return keywords;
    }

    public void setKeywords(List<String> keywords) {
        this.keywords = keywords;
    }

    public URI getHomePage() {
        return homePage;
    }

    public void setHomePage(URI homePage) {
        this.homePage = homePage;
    }

    public List<InternetAddress> getAdministrativeContacts() {
        return administrativeContacts;
    }

    public void setAdministrativeContacts(List<InternetAddress> administrativeContacts) {
        this.administrativeContacts = administrativeContacts;
    }

    public List<InternetAddress> getCoordinators() {
        return coordinators;
    }

    public void setCoordinators(List<InternetAddress> coordinators) {
        this.coordinators = coordinators;
    }

    public List<InternetAddress> getScientificContacts() {
        return scientificContacts;
    }

    public void setScientificContacts(List<InternetAddress> scientificContacts) {
        this.scientificContacts = scientificContacts;
    }

    @Override
    public String[] getUriSegments(ProjectModel instance) {
        return new String[]{
            "project",
            instance.getName()
        };
    }

}
