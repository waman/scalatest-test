package junit.tutorial.matcher;


import org.junit.Before;
import org.junit.Test;
import org.w3c.dom.Node;
import org.xml.sax.InputSource;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import java.io.StringReader;

import static org.hamcrest.CoreMatchers.*;
import static org.waman.junitutil.matchers.XmlMatchers.*;
import static org.junit.Assert.assertThat;

public class XmlMatcherTest {

    Node xml;

    @Before
    public void xmlを初期化()throws Exception{
        String xmlSource = "<document><node/><node name='waman'/></document>";
        InputSource source = new InputSource();
        source.setCharacterStream(new StringReader(xmlSource));

        DocumentBuilder builder = DocumentBuilderFactory.newInstance().newDocumentBuilder();
        xml = builder.parse(source);
    }

    // org.hamcrest.xml.HasXPath
    @Test
    public void hasXPathで指定したXPathに対応するノードがあることを検証する(){
        assertThat(xml, hasXPath("/document/node[1]"));
        assertThat(xml, not(hasXPath("/document/node[3]")));
    }

    // org.hamcrest.xml.HasXPath
    @Test
    public void hasXPathで指定したXPathに対応するノードが指定したMatcherにマッチする値を持つことを検証する(){
        assertThat(xml, hasXPath("/document/node[2]/@name", is("waman")));
    }
}
