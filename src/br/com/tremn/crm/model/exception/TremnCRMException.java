package br.com.tremn.crm.model.exception;


/**
 * Exception pai na hierarquia do sistema
 * @author Solkam
 * @since 01 FEV 2015
 */
public class TremnCRMException extends RuntimeException {
	
	public TremnCRMException(String msg) {
		super(msg);
	}
	
	public TremnCRMException(String msg, Throwable t) {
		super(msg, t);
	}
	
	public TremnCRMException(Throwable t) {
		super(t);
	}

	
	
	private static final long serialVersionUID = -8213636800004619805L;
}
