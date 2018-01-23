package engsoft.biblioteca;

import java.util.Date;

import engsoft.usuario.Usuario;

public class Reserva {
	private Date data;
	private Usuario usuario;
	
	public Reserva(Usuario usuario) {
		this.data = new Date();
		this.usuario = usuario;
	}

	@Override
	public int hashCode() {
		return Integer.parseInt(this.getUsuario().getCodigo());
	}
	
	@Override
    public boolean equals(Object o) {
		Reserva res;
    	if (Reserva.class.isInstance(o)) {
    		res = (Reserva) o;
    	} else {
    		return false;
    	}
    	return this.getUsuario() != null && this.getUsuario().equals(res.getUsuario());
    }
	
	@Override
	public String toString() {		
		String s = "";
		s += "Nome do Usuario: " + this.usuario.getNome() + "\n";
		s += "Data da Reserva: " + this.data + "\n";
		return s;
	}
	
	public Usuario getUsuario() {
		return this.usuario;
	}
}
