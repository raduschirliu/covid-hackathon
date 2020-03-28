package com.mmpp.motivationapp.backend;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Random;

public class Motivator {

	public ArrayList<Message> MessageBox;

	public Motivator() {
		MessageBox = new ArrayList<Message>();
		
	}
	
	public void addMessage(String s) {
		Message newMessage = new Message(s);
		MessageBox.add(newMessage);
	}
	
	public void displayRandomMessage() {
		if(MessageBox.size() == 0) {
			System.out.println("No messages to display");
			return;
		}
		
		//Get a random index within the total messages
		Random r = new Random();
		int max = MessageBox.size();
		int index = r.nextInt(max);
		
		System.out.println(MessageBox.get(index));
	}
	
	public void importMessagesFromFile(String filename) {
		File file = new File(filename);
		try {
			BufferedReader in = new BufferedReader(new FileReader(file));
			while(in.ready()) {
				String s = in.readLine();
				addMessage(s);
			}
		} catch (FileNotFoundException e) {
			System.err.println("Could not find the file with the specified filename");
			e.printStackTrace();
		} catch (IOException e) {
			System.err.println("I/O error");
			e.printStackTrace();
		}
		
	}
	
	
	public ArrayList<Message> getMessageBox() {
		return MessageBox;
	}

	public void setMessageBox(ArrayList<Message> messageBox) {
		MessageBox = messageBox;
	}
	
	
	public static void main(String [] args) {
		Motivator m = new Motivator();
		m.importMessagesFromFile("MessageBank.txt");
		m.displayRandomMessage();
	}
	
}
