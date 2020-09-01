package com.testmg.socialnetwork.command;

import com.testmg.socialnetwork.util.AppPrinter;

public class InvalidCommand implements Command {

	private AppPrinter postDisplayer;

	public InvalidCommand(AppPrinter postDisplayer) {
		this.postDisplayer = postDisplayer;
	}

	@Override
	public void execute(String commandLine) {
		postDisplayer.displayMessage(String.format("Command: %s is invalid!", commandLine));
	}

	@Override
	public boolean matches(String input) {
		return false;
	}

}
