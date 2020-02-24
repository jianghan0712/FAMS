/**
 * 
 */
package com.purefun.fams.common.domain;

import java.io.Serializable;

import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;

/**
 * 
 * @Classname: BaseDomain
 * @Description: 实体基础类
 * @author jianghan
 * @date 2020-02-21 22:40:36
 */
public class BaseDomain implements Serializable {

	private static final long serialVersionUID = -5348153513979265868L;

	@Override
	public String toString() {
		return ToStringBuilder.reflectionToString(this, ToStringStyle.SHORT_PREFIX_STYLE);
	}
}
