package com.testmg.socialnetwork.command;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.apache.commons.lang3.StringUtils;

import com.testmg.socialnetwork.util.AppPrinter;

public class ExitCommand implements Command {
	private static final Pattern REGEX = Pattern.compile("^exit$");
	private AppPrinter appPrinter;

	public ExitCommand(AppPrinter appPrinter) {
		this.appPrinter = appPrinter;
	}

	@Override
	public void execute(String commandLine) {
		appPrinter.displayMessage("Bye!");
		System.exit(0);
	}

	@Override
	public boolean matches(String input) {
		if (StringUtils.isNotBlank(input)) {
			Matcher matcher = REGEX.matcher(input);
			return matcher.find();
		}
		return false;
	}
}
