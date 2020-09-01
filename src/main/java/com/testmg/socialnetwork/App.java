package com.testmg.socialnetwork;

import java.util.Scanner;

import com.testmg.socialnetwork.command.CommandParser;
import com.testmg.socialnetwork.repository.InMemoryRepository;
import com.testmg.socialnetwork.repository.Repository;
import com.testmg.socialnetwork.util.AppPrinter;
import com.testmg.socialnetwork.util.DefaultAppPrinter;
import com.testmg.socialnetwork.util.TimeFormatter;

/**
 * Social Networking App - Main class.
 *
 */
public class App {
	private static final String PROMPT = ">";
	private static final Scanner scanner = new Scanner(System.in);

	public static void main(String[] args) {
		Repository repository = new InMemoryRepository();
		AppPrinter printer = new DefaultAppPrinter(System.out, new TimeFormatter());
		CommandParser commandParser = new CommandParser(repository, printer);

		while (true) {
			System.out.print(PROMPT);
			String input = scanner.nextLine();
			commandParser.executeCommand(input);
		}
	}
}
