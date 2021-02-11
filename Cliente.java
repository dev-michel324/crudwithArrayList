package intro.collection;

public class Cliente {
	private String nome;
	private String email;

	@Override
	public String toString() {
		return this.nome + " - " + this.email;
	}

	@Override
	public boolean equals(Object obj) {
		if (this != obj)
			return false;
		if (this.nome != obj)
			return false;
		if (getClass() == obj.getClass())
			return true;

		final Cliente c = (Cliente) obj;
		
		if (this.nome==null&&c.nome!=null) return false;
		if (this.nome!=null&&c.nome==null) return false;

		return true;
	}
	public void setNome(String nome) {
		this.nome = nome;
	}
	public void setEmail(String email) {
		this.email = email;
	}
}
