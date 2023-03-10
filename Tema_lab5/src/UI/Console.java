package UI;

import java.awt.*;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.io.BufferedReader;
import java.io.FileReader;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableModel;

import Controller.EmisiuneController;
import Domain.Emisiune;
import Repository.ProgramTV;

import java.lang.*;

class Console  extends JFrame implements java.awt.event.ActionListener
{ 
  EmisiuneController ec;
  JTextField emisiune;
  DefaultTableModel model1;
  JTable table1;
  DefaultTableModel model2;
  JTable table2;
  DefaultTableModel model3;
  JTable table3;
  
  public Console (EmisiuneController ec_)
     {super("Program TV");
      this.ec = ec_;
      JPanel p=new JPanel();		//declararea panoului general p
      p.setLayout(new GridLayout(10,1));//setarea dimensiunilor lui p
      				//pe panoul p se vor "pune" 3 subpanouri
				//p1,p2 si p3, toate sunt de tipul JPanel

				//in p1 se pun: o ticheta si o caseta de text
      JPanel p1=new JPanel();	//declaratiile si instantieri pentru p1, etic1 si nr	
      JLabel etic1 =new JLabel("Cauta:");
      emisiune=new JTextField(25);
      p1.add(etic1);		//adaugarea etichetei etic1 la p1
      p1.add(emisiune);		//adaugarea casetei de text la p1
      p.add(p1);		//adaugarea panoului p1 la panoul general p
      			
				//in p2 se pune un buton;
      JPanel p2=new JPanel();	//declaratiile si instantieri pentru p2 si but
      JButton but1=new JButton("Afisare tabel");
      but1.addActionListener(this);//butonului but i se asociaza un eveniment 
				  //parametru este un obiect de tip oglinda, practic panoul p
      p2.add(but1);		//adaugarea butonului but la p2
      p.add(p2);		//adaugarea panoului p2 la panoul general p
      
      String[] columns1 = {"Ora", "Durata", "Ziua"};
      String[][] data1 = {};
      model1 = new DefaultTableModel(data1, columns1); //continutul
      table1 = new JTable();
      table1.setModel(model1);
      table1.setBounds(30, 40, 200, 300);
      JScrollPane sp1 = new JScrollPane(table1);
      p.add(sp1);
      
      String[] columns2 = {"Ora", "Categoria", "Durata"};
      String[][] data2 = {};
      model2 = new DefaultTableModel(data2, columns2); //continutul
      table2 = new JTable();
      table2.setModel(model2);
      table2.setBounds(30, 40, 200, 300);
      JScrollPane sp2 = new JScrollPane(table2);
      p.add(sp2);
      
      JPanel p3=new JPanel();	//declaratiile si instantieri pentru p2 si but
      JButton but2=new JButton("Afisare ProgramTV");
      but2.addActionListener(this);//butonului but i se asociaza un eveniment 
				  //parametru este un obiect de tip oglinda, practic panoul p
      p3.add(but2);		//adaugarea butonului but la p2
      p.add(p3);		//adaugarea panoului p2 la panoul general p
      
      String[] columns3 = {"Ora", "Categoria", "Durata", "Ziua"};
      String[][] data3 = {};
      model3 = new DefaultTableModel(data3, columns3); //continutul
      table3 = new JTable();
      table3.setModel(model3);
      table3.setBounds(30, 40, 200, 300);
      JScrollPane sp3 = new JScrollPane(table3);
      p.add(sp3);
   
      getContentPane().add(p);	//adaugarea panoului general la un container general 
     
     }
     
  public static Emisiune[] citireDinFisier(){
		int n;							//numarul de linii din fisier
		Emisiune ve[]=null;					//declarare tablou de tip Emisiune
		
		try { BufferedReader fisIn = 
			  new BufferedReader(new FileReader("D:\\eclipse-workspace\\Tema_lab5\\src\\Emisiune.txt"));
		      String s;
		      s=fisIn.readLine();
		      n=Integer.parseInt(s);
		      ve = new Emisiune[n];				//aloca n referinte pentru fiecare Emisiune
		      System.out.println("n="+ve.length);
		      int i=0;							//contor al tabloului ve
		      while((s = fisIn.readLine())!= null){
			    String felii[]=s.split(",");
			    int ora=Integer.parseInt(felii[0]);
			    String categoria =felii[1];
			    int durata = Integer.parseInt(felii[2]);
			    String zi = felii[3];
			    ve[i]=new Emisiune(ora, categoria, durata, zi);//alocarea efectiva pentru comp. ve
			    i++;
		     }
		      System.out.println("lungimea tabelului="+ve.length);
		  fisIn.close();
		  
		} // try
	   catch(Exception e) {
	     System.out.println(e.getMessage());
	     e.printStackTrace();
	   } // catch //citiri valorile vectorului
	   return ve;
  }
  
   public void actionPerformed(java.awt.event.ActionEvent e)  //actiunea asociata butonului but
	{if(e.getActionCommand()=="Afisare tabel") {
		model1.setRowCount(0);
		model2.setRowCount(0);
		String text_label= emisiune.getText();    //se ia numarul din caseta de text nr
		Emisiune[] emisiuni = this.ec.get_all();
		for(int i=0;i<emisiuni.length;i++) {
			if(emisiuni[i].get_categoria().equals(text_label)) {
				if(emisiuni[i].get_categoria().equals("sport"))
					for (int j = i+1; j < emisiuni.length; j++) {
						if (emisiuni[i].get_durata() < emisiuni[j].get_durata() && 
								emisiuni[i].get_categoria().equals(emisiuni[j].get_categoria())){
							 Emisiune aux=emisiuni[i];
							 emisiuni[i]=emisiuni[j];
							 emisiuni[j]=aux;
						}
					}
				if(emisiuni[i].get_categoria().equals("jurnal"))
					for (int j = i+1; j < emisiuni.length; j++) {
						if (emisiuni[i].get_durata() < emisiuni[j].get_durata() && 
								emisiuni[i].get_categoria().equals(emisiuni[j].get_categoria())){
							 Emisiune aux=emisiuni[i];
							 emisiuni[i]=emisiuni[j];
							 emisiuni[j]=aux;
						}
					}
				
				String[] data = new String[3];
				data[0]=String.valueOf(emisiuni[i].get_ora());
				data[1]=String.valueOf(emisiuni[i].get_durata());
				data[2]=emisiuni[i].get_zi();
				model1.addRow(data);
			}
		}
		model2.setRowCount(0);
		for(int i=0;i<emisiuni.length;i++) {
			if(emisiuni[i].get_zi().equals(text_label)) {
				String[] data = new String[3];
				data[0]=String.valueOf(emisiuni[i].get_ora());
				data[1]=emisiuni[i].get_categoria();
				data[2]=String.valueOf(emisiuni[i].get_durata());
				model2.addRow(data);
			}
		}
	}
	if(e.getActionCommand()=="Afisare ProgramTV") {
		model3.setRowCount(0);
		Emisiune[] emisiuni = this.ec.get_all();
		for(int i=0;i<emisiuni.length;i++) {
			String[] data = new String[4];
			data[0]=String.valueOf(emisiuni[i].get_ora());
			data[1]=emisiuni[i].get_categoria();
			data[2]=String.valueOf(emisiuni[i].get_durata());
			data[3]=emisiuni[i].get_zi();
			model3.addRow(data);    
		}
	}
	}
		
   
   public static void main(String args[])
   {//citim din fisier
		Emisiune[] e= citireDinFisier();
		ProgramTV p=new ProgramTV();
		for(int i=0;i<e.length;i++)
			p.create_emisiune(e[i]);
		
	EmisiuneController ec = new EmisiuneController(p);
	Console c=new Console(ec);
    c.addWindowListener(
	new WindowAdapter()
        {public void windowClosing(WindowEvent a)
             {System.exit(0);}
         }
      );
     c.pack();
     c.setVisible(true);
   }
 
} 