
package com.purefun.fams.ace.sop;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>anonymous complex type的 Java 类。
 * 
 * <p>以下模式片段指定包含在此类中的预期内容。
 * 
 * <pre>
 * &lt;complexType>
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="getStockImage_kByteByCodeResult" type="{http://www.w3.org/2001/XMLSchema}base64Binary" minOccurs="0"/>
 *       &lt;/sequence>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "", propOrder = {
    "getStockImageKByteByCodeResult"
})
@XmlRootElement(name = "getStockImage_kByteByCodeResponse")
public class GetStockImageKByteByCodeResponse {

    @XmlElement(name = "getStockImage_kByteByCodeResult")
    protected byte[] getStockImageKByteByCodeResult;

    /**
     * 获取getStockImageKByteByCodeResult属性的值。
     * 
     * @return
     *     possible object is
     *     byte[]
     */
    public byte[] getGetStockImageKByteByCodeResult() {
        return getStockImageKByteByCodeResult;
    }

    /**
     * 设置getStockImageKByteByCodeResult属性的值。
     * 
     * @param value
     *     allowed object is
     *     byte[]
     */
    public void setGetStockImageKByteByCodeResult(byte[] value) {
        this.getStockImageKByteByCodeResult = value;
    }

}
