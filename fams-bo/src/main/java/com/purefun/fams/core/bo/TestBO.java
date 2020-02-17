/**
 * 
 */
package com.purefun.fams.core.bo;

import com.purefun.fams.core.bo.tool.fstpbo;

/**
 * @author jiang
 *
 */
@fstpbo(boid = 2L, destination = "fams.core.rpc.testone")
public class TestBO extends BaseBO {
	private static final long serialVersionUID = 8124112870144768L;

	public String username;

	public String age;
}
