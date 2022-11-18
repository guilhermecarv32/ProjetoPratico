package filaRecreio;

public class Pedido {
  public final static int TOTAL_LANCHES = 6;
  public final static int MISTO = 0;
  public final static int CACHORRO_QUENTE = 1;
  public final static int PASTEL = 2;
  public final static int COXINHA = 3;
  public final static int SUCO = 4;
  public final static int REFRIGERANTE = 5;
  
  private int pedidos[] = new int[6];

  public void receberPedido(int indice) {
	  if(indice > 0)
		  pedidos[(indice - 1)]++;
  }

  public int quantidadePedido(int indice) {
    return pedidos[indice];
  }
  
	public static String traduzirPedido(int pedido) {
	    String nomePedido = "";
	    
	    pedido -= 1;

	    switch(pedido) {
	      case 0:
	        nomePedido = "Misto";
	        break;
	      case 1:
	    	nomePedido = "Cachorro Quente";  
	        break;
	      case 2:
	    	nomePedido = "Pastel";
	        break;
	      case 3:
	    	nomePedido = "Coxinha";
	        break;
	      case 4:
	    	nomePedido = "Suco";
	        break;
	      case 5:
	    	nomePedido = "Refrigerante";  
	        break;
	    }

	    return nomePedido;
	  }
}