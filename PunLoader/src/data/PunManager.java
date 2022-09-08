package data;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.ArrayList;

public class PunManager{
	
	int current_id = 0;
	private ArrayList<Pun> puns = new ArrayList<>();
	
	public PunManager() {}
	
	public int size() {
		return puns.size();
	}
	
	public Pun getPun(int index) {
		return puns.get(index);
	}
	
	public Pun createPun(String text) {
		
		Pun pun = new Pun(current_id, text);
		puns.add(pun);
		current_id++;
		
		return pun;
	}
	
	public void removePun(Pun p) {
		puns.remove(p);
	}
	
	public void load_puns() {
		try {
			File file = new File("puns.dat");
			FileInputStream fis = new FileInputStream(file);
			ObjectInputStream ois = new ObjectInputStream(fis);
			try {
				
				ArrayList<Pun> loaded_puns = (ArrayList<Pun>) ois.readObject();
				
				puns = loaded_puns;
				
				if(puns.size() > 0)
					current_id = puns.get(puns.size() - 1).getId() + 1;
				
			} 
			catch (ClassNotFoundException e) {
				// TODO Auto-generated catch block
				
			}
			ois.close();
		} 
		catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			save_puns();
			load_puns();
		}
		
		
		
	}
	
	public void save_puns() {
		try {
			File file = new File("puns.dat");
			FileOutputStream fos = new FileOutputStream(file);
			ObjectOutputStream oos = new ObjectOutputStream(fos);
			oos.writeObject(puns);
			oos.close();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

}
