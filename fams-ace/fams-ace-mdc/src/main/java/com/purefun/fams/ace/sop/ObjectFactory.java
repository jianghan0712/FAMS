
package com.purefun.fams.ace.sop;

import javax.xml.bind.JAXBElement;
import javax.xml.bind.annotation.XmlElementDecl;
import javax.xml.bind.annotation.XmlRegistry;
import javax.xml.namespace.QName;


/**
 * This object contains factory methods for each 
 * Java content interface and Java element interface 
 * generated in the com.purefun.fams.ace.sop package. 
 * <p>An ObjectFactory allows you to programatically 
 * construct new instances of the Java representation 
 * for XML content. The Java representation of XML 
 * content can consist of schema derived interfaces 
 * and classes representing the binding of schema 
 * type definitions, element declarations and model 
 * groups.  Factory methods for each of these are 
 * provided in this class.
 * 
 */
@XmlRegistry
public class ObjectFactory {

    private final static QName _Base64Binary_QNAME = new QName("http://WebXml.com.cn/", "base64Binary");
    private final static QName _ArrayOfString_QNAME = new QName("http://WebXml.com.cn/", "ArrayOfString");

    /**
     * Create a new ObjectFactory that can be used to create new instances of schema derived classes for package: com.purefun.fams.ace.sop
     * 
     */
    public ObjectFactory() {
    }

    /**
     * Create an instance of {@link GetStockImageByteByCode }
     * 
     */
    public GetStockImageByteByCode createGetStockImageByteByCode() {
        return new GetStockImageByteByCode();
    }

    /**
     * Create an instance of {@link GetStockImageKByCodeResponse }
     * 
     */
    public GetStockImageKByCodeResponse createGetStockImageKByCodeResponse() {
        return new GetStockImageKByCodeResponse();
    }

    /**
     * Create an instance of {@link GetStockImageByCodeResponse }
     * 
     */
    public GetStockImageByCodeResponse createGetStockImageByCodeResponse() {
        return new GetStockImageByCodeResponse();
    }

    /**
     * Create an instance of {@link ArrayOfString }
     * 
     */
    public ArrayOfString createArrayOfString() {
        return new ArrayOfString();
    }

    /**
     * Create an instance of {@link GetStockImageByteByCodeResponse }
     * 
     */
    public GetStockImageByteByCodeResponse createGetStockImageByteByCodeResponse() {
        return new GetStockImageByteByCodeResponse();
    }

    /**
     * Create an instance of {@link GetStockImageKByteByCodeResponse }
     * 
     */
    public GetStockImageKByteByCodeResponse createGetStockImageKByteByCodeResponse() {
        return new GetStockImageKByteByCodeResponse();
    }

    /**
     * Create an instance of {@link GetStockImageKByCode }
     * 
     */
    public GetStockImageKByCode createGetStockImageKByCode() {
        return new GetStockImageKByCode();
    }

    /**
     * Create an instance of {@link GetStockImageKByteByCode }
     * 
     */
    public GetStockImageKByteByCode createGetStockImageKByteByCode() {
        return new GetStockImageKByteByCode();
    }

    /**
     * Create an instance of {@link GetStockInfoByCode }
     * 
     */
    public GetStockInfoByCode createGetStockInfoByCode() {
        return new GetStockInfoByCode();
    }

    /**
     * Create an instance of {@link GetStockImageByCode }
     * 
     */
    public GetStockImageByCode createGetStockImageByCode() {
        return new GetStockImageByCode();
    }

    /**
     * Create an instance of {@link GetStockInfoByCodeResponse }
     * 
     */
    public GetStockInfoByCodeResponse createGetStockInfoByCodeResponse() {
        return new GetStockInfoByCodeResponse();
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link byte[]}{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://WebXml.com.cn/", name = "base64Binary")
    public JAXBElement<byte[]> createBase64Binary(byte[] value) {
        return new JAXBElement<byte[]>(_Base64Binary_QNAME, byte[].class, null, ((byte[]) value));
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link ArrayOfString }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://WebXml.com.cn/", name = "ArrayOfString")
    public JAXBElement<ArrayOfString> createArrayOfString(ArrayOfString value) {
        return new JAXBElement<ArrayOfString>(_ArrayOfString_QNAME, ArrayOfString.class, null, value);
    }

}
