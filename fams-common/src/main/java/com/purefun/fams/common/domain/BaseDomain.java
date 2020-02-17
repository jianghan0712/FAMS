/**
 * 
 */
package com.purefun.fams.common.domain;

import java.io.Serializable;

import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;

/**
 * @author jiang
 *
 */
public class BaseDomain implements Serializable {

	private static final long serialVersionUID = -5348153513979265868L;

	@Override
    public String toString() {
        return ToStringBuilder.reflectionToString(this, ToStringStyle.SHORT_PREFIX_STYLE);
    }
}
