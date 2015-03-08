package br.com.tremn.crm.model.entity.enumeration;

/**
 * Enum com as profissoes segundo Codigo Brasileiro de Ocupacoes
 * @author Solkam
 * @since 01 MAR 2014
 */
public enum Profession {

	ADM("1. Administrador")
	,ADV("2. Advogado")
	,AER("3. Aeronauta")
	,ARQ("4. Arquivista / Técnico de Arquivo")
	,ART("5. Artista/Técnico em espetáculos de diversões")
	,ASS("6. Assistente Social")
	,ATL("7. Atleta Profissional de Futebol")
	,ATU("8. Atuário")
	
	,BIB("9 . Bibliotecário")
	,BIM("10. Biomédico")
	,BIO("11. Biólogo")
	,BOM("12. Bombeiro Civil")
	
	,COM("13. Comerciário")
	,CONT("14. Contabilista")
	,CORI("15. Corretor de Imóveis")
	,CORS("16. Corretor de Seguros")
	,DES("17. Despachante Aduaneiro")
	,ENG("18. Engenheiro/ Arquiteto/ Agrônomo")
	,ECD("19. Economista Doméstico")
	,ECO("20. Economista")
	,EDF("21. Educação Física")
	,EMD("22. Empregado Doméstico")
	,ENF("23. Enfermagem")
	,ENO("24. Enélogo")
	,ENS("25. Engenharia de Segurança")
	,EST("26. Estatástico")
	,FIS("27. Fisioterapeuta e Terapeuta Ocupacional")
	,FAR("28. Farmacêutico")
	,FON("29. Fonoaudiálogo")
	,GAR("30. Garimpeiro")
	,GEG("31. Geógrafo")
	,GEO("32. Geólogo")
	,GUA("33. Guardador e Lavador de Veículos")
	,INS("34. Instrutor de Trânsito")
	,JOR("35. Jornalista")
	,LEI("36. Leiloeiro")
	,LER("37. Leiloeiro Rural")
	,MAES("38. Mãe Social")
	,MASS("39. Massagista")
	,MED("40. Médico")
	,MEDV("41. Medicina Veterinária")
	,MOT("42. Mototaxista e Motoboy")
	,MUSE("43. Museólogo")
	,MUSI("44. Músico")
	,NUT("45. Nutricionista")
	,OCE("46. Oceanógrafo")
	,ODO("47. Odontologia")
	,ORI("48. Orientador Educacional")
	,PEC("49. Peão de Rodeio")
	,PES("50. Pescador Profissional")
	,PSI("51. Psicologia")
	,PUB("52. Publicitário/Agenciador de Propaganda")
	,QUI("53. Químico")
	,RAD("54. Radialista")
	,REL("55. Relações Públicas")
	,REPC("56. Representantes Comerciais Autônomos")
	,REPE("57. Repentista")
	,SEC("58. Secretário - Secretário Executivo e Técnico em Secretariado")
	,SOC("59. Sociólogo")
	,SOMM("60. Sommelier")
	,TAX("61. Taxista")
	,TRA("62. Tradutor e Intérprete da Língua Brasileira de Sinais - LIBRAS")
	,TADM("63. Técnico em Administração")
	,TIND("64. Técnico Industrial")
	,TPRO("65. Técnico em Prótese Dentária")
	,TRAD("66. Técnico em Radiologia")
	,TUR("67. Turismólogo")
	,ZOO("68. Zootecnista")
	;
	

	private final String description;

	private Profession(String d) {
		this.description = d;
	}

	public String getDescription() {
		return description;
	}

}
