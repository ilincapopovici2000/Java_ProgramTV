package Controller;

import Domain.Emisiune;
import Repository.ProgramTV;

public class EmisiuneController {
	private ProgramTV programTV;
	
	public EmisiuneController(){			//constructor implicit	
		this.programTV= new ProgramTV();
	}
	
	public EmisiuneController(ProgramTV p) {//constructor cu parametrii
		this.programTV=p;
	}
	
	public void add_emisiune(Emisiune e) {	//metoda de adaugare care apeleaza 
		this.programTV.create_emisiune(e);  //create din Repo
	}
	
	public void update_emisiune(Emisiune e1, Emisiune e2){ //metoda de actualizare 
		this.programTV.update_emisiune(e1, e2); //care apeleaza update din repo
	}
	
	public void delete_emisiune(Emisiune e){ //metoda de stergere care apeleaza
		this.programTV.delete_emisiune(e);   //delete din Repo 
	}
	
	public Emisiune[] get_all() {			//returneaza toate emisiunile din Repo
		return this.programTV.get_all();
	}
	
	public void get_all_tabel() {			//returneaza toate emisiunile din Repo
		capTabel();
		for(int j=0;j<this.programTV.get_size();j++)
			System.out.format("| %-12s|%5d:00 |%7d min | %-8s |\n", this.programTV.get_elem_pos(j).get_categoria(), 
					this.programTV.get_elem_pos(j).get_ora(), 
					this.programTV.get_elem_pos(j).get_durata(), 
					this.programTV.get_elem_pos(j).get_zi());
		linie();
	}
	
	public void populate() {				//pune date in Repo
		this.programTV.populate();
	}
	
	public Emisiune[] get_all_filme() {		//returneaza toate filmele din Repo
		capTabel_emisiuni();
		Emisiune[] e=this.programTV.get_all();
		ProgramTV p=new ProgramTV();
		for(int i=0;i<e.length;i++)
			if(e[i].get_categoria().equals("film")) {
				p.create_emisiune(e[i]);
			}
		
		for(int j=0;j<p.get_size();j++)
			System.out.format("|%5d:00 |%7d min | %-8s |\n",p.get_elem_pos(j).get_ora(), 
					p.get_elem_pos(j).get_durata(), p.get_elem_pos(j).get_zi());
		linie_e();
		
		return p.get_all();
	}
	
	public Emisiune[] get_all_jurnale() {	//returneaza toate jurnalele din Repo
		capTabel_emisiuni();
		Emisiune[] e=this.programTV.get_all();
		ProgramTV p=new ProgramTV();
		for(int i=0;i<e.length;i++)
			if(e[i].get_categoria().equals("jurnal"))
				p.create_emisiune(e[i]);
		
		for(int j=0;j<p.get_size();j++)
			System.out.format("|%5d:00 |%7d min | %-8s |\n",p.get_elem_pos(j).get_ora(), 
					p.get_elem_pos(j).get_durata(), p.get_elem_pos(j).get_zi());
		linie_e();
		
		return p.get_all();
	}
	
	public Emisiune[] get_all_show() {		//returneaza toate show-urile din Repo
		capTabel_emisiuni();
		Emisiune[] e=this.programTV.get_all();
		ProgramTV p=new ProgramTV();
		for(int i=0;i<e.length;i++)
			if(e[i].get_categoria().equals("show"))
				p.create_emisiune(e[i]);
		
		for(int j=0;j<p.get_size();j++)
			System.out.format("|%5d:00 |%7d min | %-8s |\n",p.get_elem_pos(j).get_ora(), 
					p.get_elem_pos(j).get_durata(), p.get_elem_pos(j).get_zi());
		linie_e();
		return p.get_all();
	}
	
	public Emisiune[] get_all_sports() {	//returneaza toate emisiunile de sport
		capTabel_emisiuni();
		Emisiune[] e=this.programTV.get_all(); // din Repo
		ProgramTV p=new ProgramTV();
		for(int i=0;i<e.length;i++)
			if(e[i].get_categoria().equals("sport"))
				p.create_emisiune(e[i]);
		
		for(int j=0;j<p.get_size();j++)
			System.out.format("|%5d:00 |%7d min | %-8s |\n",p.get_elem_pos(j).get_ora(), 
					p.get_elem_pos(j).get_durata(), p.get_elem_pos(j).get_zi());
		linie_e();
		
		return p.get_all();
	}
	
	public void ordonare_sport_desc() {		
		Emisiune[] e=this.programTV.get_all();
		for(int i=0;i<e.length;i++)
			if(e[i].get_categoria().equals("sport"))
				for (int j = i+1; j < e.length; j++) {
					if (e[i].get_durata() < e[j].get_durata() && 
							e[i].get_categoria().equals(e[j].get_categoria())){
						 Emisiune aux=e[i];
						 e[i]=e[j];
						 e[j]=aux;
					}
				}
		for(int j=0;j<e.length;j++)
			this.programTV.set_elem_pos(j, e[j]);
		
	}
	
	
	public void ordonare_jurnale_desc() {	//sorteaza in ordine descrescatoatre
		ProgramTV p = new ProgramTV();		//jurnalele dupa durata
		Emisiune aux= new Emisiune();
		Emisiune[] e=this.programTV.get_all();
		for(int i=0;i<e.length;i++)
			if(e[i].get_categoria().equals("jurnal"))
				for (int j = i+1; j < e.length; j++) {
					if (e[i].get_durata() < e[j].get_durata() && 
							e[i].get_categoria().equals(e[j].get_categoria())){
						aux=e[i];
						e[i]=e[j];
						e[j]=aux;
					}
				}
		for(int j=0;j<e.length;j++)
			this.programTV.set_elem_pos(j, e[j]);
		
	}
	
	public void capTabel(){
		String sir=  "|  Categoria  |   Ora   |   Durata   |   Ziua   |";
		linie();
		System.out.println(sir);
		linie();
	}
	public void linie() {
		String linii="+-------------+---------+------------+----------+";
		System.out.println(linii);
	}
	
	public void capTabel_emisiuni(){
		String sir=  "|   Ora   |   Durata   |   Ziua   |";
		linie_e();
		System.out.println(sir);
		linie_e();
	}
	public void linie_e() {
		String linii="+---------+------------+----------+";
		System.out.println(linii);
	}
	
	public void capTabel1(){
		String sir=  "|  Categoria  |   Ora   |   Durata   |";
		linie1();
		System.out.println(sir);
		linie1();
	}
	public void linie1() {
		String linii="+-------------+---------+------------+";
		System.out.println(linii);
	}
	
	 public void afisareProgram(String zi){		//afiseaza intr-un tabel programul
		 capTabel1();							//pe o zi a saptamanii
			ProgramTV p = new ProgramTV();
			Emisiune[] e= this.programTV.get_all();
			for(int i=0;i<e.length;i++)
				if(e[i].get_zi().equals(zi)) {
					p.create_emisiune(e[i]);	
				}
			for(int j=0;j<p.get_size();j++)
				System.out.format("|%-12s |%5d:00 |%7d min |\n",p.get_elem_pos(j).get_categoria(),
						p.get_elem_pos(j).get_ora(), p.get_elem_pos(j).get_durata());
			linie1();
	}
}
