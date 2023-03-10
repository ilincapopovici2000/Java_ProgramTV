package Domain;

public class Emisiune extends Object{
	private int ora;		//0-23
	private String categoria;//film, jurnal, show, sport
	private int durata;		//in minute
	private String zi;		//luni-duminica
	
	public Emisiune(){		//constructor implicit
		this.ora=0;
		this.categoria=null;
		this.durata=0;
		this.zi=null;
	}
	
	public Emisiune(int ora, String categoria, int durata, String zi){ //constructor cu parametrii
		this.ora=ora;
		this.categoria=categoria;
		this.durata=durata;
		this.zi=zi;
	}
	
	public int get_ora() {
		return this.ora;
	}
	
	public String get_categoria() {
		return this.categoria;
	}
	
	public int get_durata() {
		return this.durata;
	}
	
	public String get_zi() {
		return this.zi;
	}
	
	public void set_ora(int ora) {
		this.ora=ora;
	}
	
	public void set_categoria(String categoria) {
		this.categoria=categoria;
	}
	
	public void set_durata(int durata) {
		this.durata=durata;
	}
	
	public void set_zi(String zi) {
		this.zi=zi;
	}
	
	public String toString() {	//returneaza modul in care se va afisa o emisiune
		return ora + ", " + categoria + ", " + durata + ", " + zi;
	}
	
	public boolean equals(Object obj){ //verifica daca doua emisiuni sunt egale
		if (!(obj instanceof Emisiune))
			return false;
		Emisiune e = (Emisiune)obj;
		return (ora==e.ora) && (categoria.equals(e.categoria)) &&
				(durata==e.durata) && (zi.equals(e.zi));
	}
	
	public void atribuire(Emisiune e) {
		this.ora=e.ora;
		this.categoria=e.categoria;
		this.durata=e.durata;
		this.zi=e.zi;
	}
}
