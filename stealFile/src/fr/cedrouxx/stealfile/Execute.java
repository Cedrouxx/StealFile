package fr.cedrouxx.stealfile;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.StandardCopyOption;
import java.util.ArrayList;
import java.util.List;

public class Execute implements Runnable{

	Main main;
	List<String> errorsList = new ArrayList<String>();
	
	public Execute(Main main) {
		this.main = main;
	}
	
	@Override
	public void run() {
		start();
	}
	
	private void start() {
		File file = new File(main.getEditText().getText());
		if(file.exists()) {
			if(main.getPngFile().isSelected() || main.getJpgFile().isSelected() || main.getTxtFile().isSelected()) {
				int i = 0;
				Boolean pngB = false;
				Boolean jpgB = false;
				Boolean txtB = false;
				
				if(main.getPngFile().isSelected()) {
					pngB = true;
					i++;
				}if(main.getJpgFile().isSelected()) {
					jpgB = true;
					i++;
				}if(main.getTxtFile().isSelected()) {
					txtB = true;
					i++;
				}
				
				String[] fileType = new String[i];
				i = 0;
				if(pngB) {
					fileType[i] = "png";
					i++;
				}if(jpgB) {
					fileType[i] = "jpg";
					i++;
				}if(txtB) {
					fileType[i] = "txt";
					i++;
				}
				
				main.getInfo().setText("scan");
				Scan scan = new Scan(main, fileType, file.toString());
				scan.addExeptionFolder(true, "Windows");
				scan.addExeptionFolder(false, "AppData\\Local");
				copyFile(scan.start());
				
			}else {
				main.getInfo().setText("Erreur : type de fichier non selectionner !");
				main.buttonOn();
			}
		}else {
			main.getInfo().setText("Erreur : chemin de départ incorrect !");
			main.buttonOn();
		}
	}
	
	private void copyFile(File[] files) {
		System.out.println("[Copy] Start");
		main.getInfo().setText("copie");
		File folder = new File("").getAbsoluteFile();
		folder = new File(folder + File.separator + Main.TITLE);
		
		if(!folder.exists()) {
			folder.mkdir();
		}
		System.out.println(folder);
		
		for(File file : files) {
			String name = file.getName().split("\\.")[0];
			String fileType = "." + file.getName().split("\\.")[1];
			
			if(new File(folder.toString() + File.separator + name + fileType).exists()) {
				Boolean ok = false;
				int i = 1;
				while(!ok) {
					if(!new File(folder.toString() + File.separator + name + i + fileType).exists()) {
						name = name + i;
						ok = true;
					}
					i++;
				}
			}
			
			try {
				main.addConsole("[Copy] " + file);
				Files.copy(file.toPath(), new File(folder + File.separator + name + fileType).toPath(), StandardCopyOption.REPLACE_EXISTING);
			} catch (IOException e) {
				errorsList.add(file.toString());
				main.addConsole("[Error Copy] " + file);
				e.printStackTrace();
			}
		}
		
		main.getInfo().setText("fin.");
		main.addConsoleError(errorsList);
		main.buttonOn();
		
	}


}
