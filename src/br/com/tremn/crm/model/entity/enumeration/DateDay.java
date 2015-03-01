package br.com.tremn.crm.model.entity.enumeration;

/**
 * Enum para os dias
 * @author Solkam
 * @since 28 FEV 2015
 */
public enum DateDay {
	
	D1(1)	,	D2(2)	,	D3(3)	,	D4(4)	,	D5(5)	,	D6(6)	,	D7(7)	,	D8(8)	,	D9(9)	,	D10(10)	,
	D11(11)	,	D12(12)	,	D13(13)	,	D14(14)	,	D15(15)	,	D16(16)	,	D17(17)	,	D18(18)	,	D19(19)	,	D20(20)	,
	D21(21)	,	D22(22)	,	D23(23)	,	D24(24)	,	D25(25)	,	D26(26)	,	D27(27)	,	D28(28)	,	D29(29)	,	D30(30)	,
	D31(31)
	;
	
	private final Integer index;
	
	private DateDay(Integer i) {
		this.index = i;
	}

	public Integer getIndex() {
		return index;
	}
	
	

}
