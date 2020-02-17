/**
 * 
 */
package com.purefun.fams.core.bo;

import com.purefun.fams.core.bo.tool.fstpbo;

/**
 * @author jiang
 *
 */
@fstpbo(boid = 3L, destination = "fams.core.rpc.testtwo")
public class TestBO2 extends BaseBO {
	private static final long serialVersionUID = 8124112870144768L;

	public String workid;

	public String homeaddress;
}
