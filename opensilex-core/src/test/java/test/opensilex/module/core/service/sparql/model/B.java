/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package test.opensilex.module.core.service.sparql.model;

import java.net.URI;
import java.util.List;
import org.opensilex.module.core.service.sparql.annotations.SPARQLCache;
import org.opensilex.module.core.service.sparql.annotations.SPARQLCacheOption;
import org.opensilex.module.core.service.sparql.annotations.SPARQLProperty;
import org.opensilex.module.core.service.sparql.annotations.SPARQLResource;
import org.opensilex.module.core.service.sparql.annotations.SPARQLResourceURI;
import org.opensilex.module.core.service.sparql.cache.SPARQLTimeCacheManager;

/**
 *
 * @author vincent
 */
@SPARQLResource(
        ontology = TEST_ONTOLOGY.class,
        resource = "B"
)
@SPARQLCache(
    implementation = SPARQLTimeCacheManager.class,
    value = {
        @SPARQLCacheOption(key = SPARQLTimeCacheManager.CACHE_DURATION_VALUE_KEY, value = "1"),
        @SPARQLCacheOption(key = SPARQLTimeCacheManager.CACHE_DURATION_UNIT_KEY, value = "SECONDS")
    }
)
public class B {
    
    @SPARQLResourceURI()
    private URI uri;
    
    @SPARQLProperty(
        ontology = TEST_ONTOLOGY.class,
        property = "hasRelationToB",
        inverse = true
    )
    private A a;

    @SPARQLProperty(
            ontology = TEST_ONTOLOGY.class,
            property = "hasInt"
    )
    private Integer integer;

    @SPARQLProperty(
            ontology = TEST_ONTOLOGY.class,
            property = "hasLong"
    )
    private Long longVar;

    @SPARQLProperty(
            ontology = TEST_ONTOLOGY.class,
            property = "hasBoolean"
    )
    private Boolean bool;

    @SPARQLProperty(
            ontology = TEST_ONTOLOGY.class,
            property = "hasFloat"
    )
    private Float floatVar;

    @SPARQLProperty(
            ontology = TEST_ONTOLOGY.class,
            property = "hasDouble"
    )
    private Double doubleVar;

    @SPARQLProperty(
            ontology = TEST_ONTOLOGY.class,
            property = "hasChar"
    )
    private Character charVar;

    @SPARQLProperty(
            ontology = TEST_ONTOLOGY.class,
            property = "hasShort"
    )
    private Short shortVar;

    @SPARQLProperty(
            ontology = TEST_ONTOLOGY.class,
            property = "hasByte"
    )
    private Byte byteVar;

    @SPARQLProperty(
            ontology = TEST_ONTOLOGY.class,
            property = "hasStringList"
    )
    private List<String> stringList;
    
    public URI getUri() {
        return uri;
    }

    public void setUri(URI uri) {
        this.uri = uri;
    }

    public A getA() {
        return a;
    }

    public void setA(A a) {
        this.a = a;
    }

    public Integer getInteger() {
        return integer;
    }

    public void setInteger(Integer integer) {
        this.integer = integer;
    }

    public Long getLongVar() {
        return longVar;
    }

    public void setLongVar(Long longVar) {
        this.longVar = longVar;
    }

    public Boolean getBool() {
        return bool;
    }

    public void setBool(Boolean bool) {
        this.bool = bool;
    }

    public Float getFloatVar() {
        return floatVar;
    }

    public void setFloatVar(Float floatVar) {
        this.floatVar = floatVar;
    }

    public Double getDoubleVar() {
        return doubleVar;
    }

    public void setDoubleVar(Double doubleVar) {
        this.doubleVar = doubleVar;
    }

    public Character getCharVar() {
        return charVar;
    }

    public void setCharVar(Character charVar) {
        this.charVar = charVar;
    }

    public Short getShortVar() {
        return shortVar;
    }

    public void setShortVar(Short shortVar) {
        this.shortVar = shortVar;
    }

    public Byte getByteVar() {
        return byteVar;
    }

    public void setByteVar(Byte byteVar) {
        this.byteVar = byteVar;
    }

    public List<String> getStringList() {
        return stringList;
    }

    public void setStringList(List<String> stringList) {
        this.stringList = stringList;
    }
}
