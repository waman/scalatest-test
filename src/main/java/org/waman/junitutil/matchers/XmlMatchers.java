package org.waman.junitutil.matchers;

import org.hamcrest.Matcher;
import org.hamcrest.xml.HasXPath;
import org.w3c.dom.Node;

import javax.xml.namespace.NamespaceContext;

public class XmlMatchers {

    public static Matcher<Node> hasXPath(String xPath){
        return HasXPath.hasXPath(xPath);
    }

    public static Matcher<Node> hasXPath(String xPath, Matcher<String> valueMatcher){
        return HasXPath.hasXPath(xPath, valueMatcher);
    }

    public static Matcher<Node> hasXPath(String xPath, NamespaceContext namespaceContext){
        return HasXPath.hasXPath(xPath, namespaceContext);
    }

    public static Matcher<Node> hasXPath(String xPath, NamespaceContext namespaceContext, Matcher<String> valueMatcher){
        return HasXPath.hasXPath(xPath, namespaceContext, valueMatcher);
    }
}
