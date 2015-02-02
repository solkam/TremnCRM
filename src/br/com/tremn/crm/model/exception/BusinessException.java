package br.com.tremn.crm.model.exception;

import javax.ejb.ApplicationException;

/**
 * Hierarquia de exception de negocio
 * @author Solkam
 * @since 01 FEV 2015
 */
@ApplicationException(rollback=false)
public class BusinessException extends TremnCRMException {

	public BusinessException(String msg) {
		super(msg);
	}
	
	

	private static final long serialVersionUID = -737466541972315213L;
}
