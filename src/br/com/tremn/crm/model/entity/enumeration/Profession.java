package br.com.tremn.crm.model.entity.enumeration;

/**
 * Enum com as profiss�es segundo C�digo Brasileiro de Ocupa��es
 * @author Solkam
 * @since 01 MAR 2014
 */
public enum Profession {

	ADM("1. Administrador")
	,ADV("2. Advogado")
	,AER("3. Aeronauta")
	,ARQ("4. Arquivista / T�cnico de Arquivo")
	,ART("5. Artista/T�cnico em espet�culos de divers�es")
	,ASS("6. Assistente Social")
	,ATL("7. Atleta Profissional de Futebol")
	,ATU("8. Atu�rio")
	
	,BIB("9 . Bibliotec�rio")
	,BIM("10. Biom�dico")
	,BIO("11. Bi�logo")
	,BOM("12. Bombeiro Civil")
	
	,COM("13. Comerci�rio")
	,CONT("14. Contabilista")
	,CORI("15. Corretor de Im�veis")
	,CORS("16. Corretor de Seguros")
	,DES("17. Despachante Aduaneiro")
	,ENG("18. Engenheiro/ Arquiteto/ Agr�nomo")
	,ECD("19. Economista Dom�stico")
	,ECO("20. Economista")
	,EDF("21. Educa��o F�sica")
	,EMD("22. Empregado Dom�stico")
	,ENF("23. Enfermagem")
	,ENO("24. En�logo")
	,ENS("25. Engenharia de Seguran�a")
	,EST("26. Estat�stico")
	,FIS("27. Fisioterapeuta e Terapeuta Ocupacional")
	,FAR("28. Farmac�utico")
	,FON("29. Fonoaudi�logo")
	,GAR("30. Garimpeiro")
	,GEG("31. Ge�grafo")
	,GEO("32. Ge�logo")
	,GUA("33. Guardador e Lavador de Ve�culos")
	,INS("34. Instrutor de Tr�nsito")
	,JOR("35. Jornalista")
	,LEI("36. Leiloeiro")
	,LER("37. Leiloeiro Rural")
	,MAES("38. M�e Social")
	,MASS("39. Massagista")
	,MED("40. M�dico")
	,MEDV("41. Medicina Veterin�ria")
	,MOT("42. Mototaxista e Motoboy")
	,MUSE("43. Muse�logo")
	,MUSI("44. M�sico")
	,NUT("45. Nutricionista")
	,OCE("46. Ocean�grafo")
	,ODO("47. Odontologia")
	,ORI("48. Orientador Educacional")
	,PEC("49. Pe�o de Rodeio")
	,PES("50. Pescador Profissional")
	,PSI("51. Psicologia")
	,PUB("52. Publicit�rio/Agenciador de Propaganda")
	,QUI("53. Qu�mico")
	,RAD("54. Radialista")
	,REL("55. Rela��es P�blicas")
	,REPC("56. Representantes Comerciais Aut�nomos")
	,REPE("57. Repentista")
	,SEC("58. Secret�rio - Secret�rio Executivo e T�cnico em Secretariado")
	,SOC("59. Soci�logo")
	,SOMM("60. Sommelier")
	,TAX("61. Taxista")
	,TRA("62. Tradutor e Int�rprete da L�ngua Brasileira de Sinais - LIBRAS")
	,TADM("63. T�cnico em Administra��o")
	,TIND("64. T�cnico Industrial")
	,TPRO("65. T�cnico em Pr�tese Dent�ria")
	,TRAD("66. T�cnico em Radiologia")
	,TUR("67. Turism�logo")
	,ZOO("68. Zootecnista")
	;
	

	private final String descrption;

	private Profession(String d) {
		this.descrption = d;
	}

	public String getDescrption() {
		return descrption;
	}
	
	
	
}
