package fr.cedrouxx.stealfile;

import fr.cedrouxx.scanfolder.ScanFolder;

public class Scan extends ScanFolder {

	Main main;
	
	public Scan(Main main, String fichierType, String startFolder) {
		super(fichierType, startFolder);
		this.main = main;
	}

	public Scan(Main main, String[] fichierType, String startFolder) {
		super(fichierType, startFolder);
		this.main = main;
	}

	@Override
	public void console(ConsoleType type, String string) {
		if(type == ConsoleType.find) {
			main.addConsole("[Find] " + string);
			System.out.println("[Find] " + string);
		}else if(type == ConsoleType.search) {
			main.addConsole("[Search] " + string);
			System.out.println("[Search] " + string);
		}else if(type == ConsoleType.exeptionFolder) {
			main.addConsole("[ExeptionFolder]" + string);
			System.out.println("[ExeptionFolder] " + string);
		}
		
	}

}
