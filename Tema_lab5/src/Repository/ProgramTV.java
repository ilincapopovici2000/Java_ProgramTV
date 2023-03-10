package Repository;

import Domain.Emisiune;

public class ProgramTV {	//Repository
	
	private Emisiune[] emisiuni;
	private int size, capacity;
	
	public ProgramTV(){		//constructor implicit
		this.capacity=100;
		this.emisiuni = new Emisiune[capacity];
		this.size=0;
	}
	
	public ProgramTV(int cap){	//constructor cu parametrul capacity
		this.capacity=cap;
		this.emisiuni = new Emisiune[capacity];
		this.size=0;
	}
	
	ProgramTV(int cap, Emisiune[] emi, int size){//constructor cu parametrii
		this.capacity=cap;
		this.size=size;
		for(int i=0;i<size;i++)
			this.emisiuni[i]=emi[i];
	}
	
	//metode CRUD
	
	public void create_emisiune(Emisiune e) {	//create
		if(capacity>size) {
			this.emisiuni[size] = e;
			size++;
		}
		else System.out.println("Nu s-a putut crea emisiunea...");
	}
	
	public Emisiune[] get_all() {				//read
		Emisiune[] e = new Emisiune[size];
		for(int i=0;i<size;i++)
			e[i]=this.emisiuni[i];
		return e;
	}
	
	public void update_emisiune(Emisiune e1, Emisiune e2) {	//update
		int ok=0;
		for(int i=0;i<size;i++)
			if(this.emisiuni[i].equals(e1)) {
				this.emisiuni[i].atribuire(e2);
				ok=1;
				System.out.println("Emisiunea a fost actualizata!");
			}
		if(ok==0)
			System.out.println("Nu exitsta emisiunea data...");
	}
	
	public void delete_emisiune(Emisiune e) {	//delete
		int ok=0;
		for(int i=0;i<size;i++)
			if(this.emisiuni[i].equals(e)) {
				this.emisiuni[i]=emisiuni[size-1];
				size--;
				ok=1;
				System.out.println("Emisiunea a fost stearsa!");
			}
		if(ok==0)
			System.out.println("Nu exitsta emisiunea data...");
	}
	
	public void populate() {					//popularea de date 
		Emisiune e1=new Emisiune(18, "film", 120, "joi");
		Emisiune e2=new Emisiune(12, "show", 40, "vineri");
		Emisiune e3=new Emisiune(14, "sport", 120, "luni");
		Emisiune e4=new Emisiune(20, "jurnal", 60, "joi");
		Emisiune e5=new Emisiune(19, "sport", 130, "miercuri");
		Emisiune e6=new Emisiune(18, "jurnal", 80, "luni");
		Emisiune e7=new Emisiune(22, "film", 100, "sambata");
		Emisiune e8=new Emisiune(15, "show", 60, "marti");
		Emisiune[] e = {e1, e2, e3, e4, e5, e6, e7, e8};
		for(int i=0;i<e.length;i++)
			create_emisiune(e[i]);
	}
	
	public int get_capacity() {
		return this.capacity;
	}
	
	public int get_size() {
		return this.size;
	}
	
	public Emisiune get_elem_pos(int i) {
		return this.emisiuni[i];
	}
	
	public void set_elem_pos(int i, Emisiune e) {
		this.emisiuni[i] = e;
	}
}
