package modulo05;

public class RepositorioEstados {
	
	public static final String AC = "AC";
	public static final String AL = "AL";
	public static final String AP = "AP";
	public static final String AM = "AM";
	public static final String BA = "BA";
	public static final String CE = "CE";
	public static final String DF = "DF";
	public static final String ES = "ES";
	public static final String GO = "GO";
	public static final String MA = "MA";
	public static final String MT = "MT";
	public static final String MS = "MS";
	public static final String MG = "MG";
	public static final String PA = "PA";
	public static final String PB = "PB";
	public static final String PR = "PR";
	public static final String PE = "PE";
	public static final String PI = "PI";
	public static final String RR = "RR";
	public static final String RO = "RO";
	public static final String RJ = "RJ";
	public static final String RN = "RN";
	public static final String RS = "RS";
	public static final String SC = "SC";
	public static final String SP = "SP";
	public static final String SE = "SE";
	public static final String TO = "TO";

	private static final String AC_DESCRICAO = "Acre";
	private static final String AL_DESCRICAO = "Alagoas";
	private static final String AP_DESCRICAO = "Amapá";
	private static final String AM_DESCRICAO = "Amazonas";
	private static final String BA_DESCRICAO = "Bahia";
	private static final String CE_DESCRICAO = "Ceará";
	private static final String DF_DESCRICAO = "Distrito Federal";
	private static final String ES_DESCRICAO = "Espírito Santo";
	private static final String GO_DESCRICAO = "Goiás";
	private static final String MA_DESCRICAO = "Maranhão";
	private static final String MT_DESCRICAO = "Mato Grosso";
	private static final String MS_DESCRICAO = "Mato Grosso do Sul";
	private static final String MG_DESCRICAO = "Minas Gerais";
	private static final String PA_DESCRICAO = "Pará";
	private static final String PB_DESCRICAO = "Paraíba";
	private static final String PR_DESCRICAO = "Paraná";
	private static final String PE_DESCRICAO = "Pernambuco";
	private static final String PI_DESCRICAO = "Piauí";
	private static final String RR_DESCRICAO = "Roraima";
	private static final String RO_DESCRICAO = "Rondônia";
	private static final String RJ_DESCRICAO = "Rio de Janeiro";
	private static final String RN_DESCRICAO = "Rio Grande do Norte";
	private static final String RS_DESCRICAO = "Rio Grande do Sul";
	private static final String SC_DESCRICAO = "Santa Catarina";
	private static final String SP_DESCRICAO = "São Paulo";
	private static final String SE_DESCRICAO = "Sergipe";
	private static final String TO_DESCRICAO = "Tocantins";

	
	// Definir uma estrutura de dados para armazenar os dados acima

	
	// Estruturas de arrays para armazenar os dados acima.. 
	
		private static String [] arraySiglas = {"AC","AL","AP","AM","BA","CE","DF","ES","GO","MA","MT","MS","MG","PA","PB","PR",
				"PE","PI","RR","RO","RJ","RN","RS","SC","SP","SE","TO"};
		
		private static String [] arrayEstados = {"Acre","Alagoas","Amapá","Amazonas","Bahia","Ceará","Distrito Federal",
				"Espírito Santo","Goiás","Maranhão","Mato Grosso","Mato Grosso do Sul","Minas Gerais",
				"Pará","Paraíba","Paraná","Pernambuco","Piauí","Roraima","Rondônia","Rio de Janeiro",
				"Rio Grande do Norte","Rio Grande do Sul","Santa Catarina","São Paulo","Sergipe",
				"Tocantins"};
	

	/**
	 * Retorna o nome de um estado a partir da sua sigla.
	 * @param sigla Sigla do estado para busca do seu nome.
	 * @return Nome do estado.
	 */
	public static String getDescricaoEstado(String sigla) {
		
		int i = 0;
		
		while (i< arraySiglas.length && !sigla.equals(arraySiglas[i]) ){
						
			i++;
		}		
		return arrayEstados[i];
	}
		
	
}
