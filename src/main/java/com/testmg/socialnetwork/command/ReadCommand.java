package com.testmg.socialnetwork.command;

import java.util.Comparator;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import java.util.stream.Collectors;

import org.apache.commons.lang3.StringUtils;

import com.testmg.socialnetwork.domain.Post;
import com.testmg.socialnetwork.domain.User;
import com.testmg.socialnetwork.repository.Repository;
import com.testmg.socialnetwork.util.AppPrinter;

public class ReadCommand implements Command {
	private static final Pattern REGEX = Pattern.compile("^\\S+$");

	private Repository repository;
	private final AppPrinter postDisplayer;

	public ReadCommand(Repository repository, AppPrinter postDisplayer) {
		this.repository = repository;
		this.postDisplayer = postDisplayer;
	}

	@Override
	public void execute(String commandLine) {
		Comparator <Post> myComparator = (arg1, arg2) 
	            -> {
	            	return arg2.getPublishDate().compareTo(arg1.getPublishDate());
	               };
		User user = repository.getOrCreateUser(commandLine.trim());
		List<Post> posts = user.getPosts();
		// Sort posts.
		List<Post> postsSorted = posts.stream().sorted(myComparator).collect(Collectors.toList());
		postsSorted.forEach( p -> postDisplayer.displayPostForUser(p));
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
