package com.testmg.socialnetwork;


import com.testmg.socialnetwork.command.FollowCommand;
import com.testmg.socialnetwork.command.InvalidCommand;
import com.testmg.socialnetwork.command.PostCommand;
import com.testmg.socialnetwork.command.ReadCommand;
import com.testmg.socialnetwork.command.WallCommand;
import com.testmg.socialnetwork.domain.Post;
import com.testmg.socialnetwork.domain.User;
import com.testmg.socialnetwork.repository.Repository;
import com.testmg.socialnetwork.util.AppPrinter;
import com.testmg.socialnetwork.util.DefaultAppPrinter;
import com.testmg.socialnetwork.util.TimeFormatter;

import cucumber.api.java.en.Given;
import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;

import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.spy;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;
import java.util.Arrays;
import java.util.Date;

import org.junit.Assert;

public class AppSteps {
	private PostCommand commandPost;
	private ReadCommand commandRead;
	private InvalidCommand commandInvalid;
	private FollowCommand commandFollow;
	private Repository repository;
	private User user;
	private User user1;
	private TimeFormatter timeFormatter = new TimeFormatter();
	private PrintStream printStream;
	private ByteArrayOutputStream baos;
	private AppPrinter printer;
	private WallCommand commandWall;
	
	@Given("^that the user/users (.*) is/are using console-based social networking application$")
	public void userUsesSocialNetworking(String listNames) throws Throwable {
		 repository = mock(Repository.class);
		 commandPost = new PostCommand(repository);
		 commandFollow = new FollowCommand(repository);
		 baos = new ByteArrayOutputStream();
		 printStream = new PrintStream(baos);
		 printer = new DefaultAppPrinter(printStream, timeFormatter);
		 commandInvalid = new InvalidCommand(printer);
		 commandRead = new ReadCommand(repository, printer);
		 commandFollow = new FollowCommand(repository);
		 commandWall = new WallCommand(repository, printer);
	}

	@When("^the user (.*) write a post command (.*)$")
	public void userWriteAPostCommand(String name, String command) throws Throwable {

			user = mock(User.class);
			Assert.assertFalse(commandPost.matches(null));
			Assert.assertFalse(commandPost.matches(""));
			Assert.assertTrue(commandPost.matches(command));

			when(repository.getOrCreateUser(name)).thenReturn(user);
			commandPost.execute(command);

		
	}

	@Then("^the message (.*) of the user (.*) is pubblished$")
	public void thePostCommandOfTheUserIsPubblished(String message, String name) throws Throwable {
		 verify(repository).getOrCreateUser(name);
		 verify(user).addPost(message);
	}



	@When("^the user (.*) write a read command (.*)$")
	public void userWriteAReadCommand(String userName, String readCommand) throws Throwable {

		Assert.assertFalse(commandRead.matches(null));
		Assert.assertFalse(commandRead.matches(""));
		Assert.assertTrue(commandRead.matches(readCommand));
		
		user = spy(new User(readCommand));
		Post post1 = new Post(readCommand, "I love the weather today", new Date(
				System.currentTimeMillis()));
		Post post2 = new Post(readCommand, "I love NY", new Date(System.currentTimeMillis()));
		
		user.setPosts(Arrays.asList(post1,
				post2));
		
		user.setPosts(Arrays.asList(post1));

		when(repository.getOrCreateUser(readCommand)).thenReturn(user);

		commandRead.execute(readCommand);
			
	}

	@Then("^the user (.*) can view the timeline of (.*)$")
	public void userCanViewtheTimelineOfAnotherUser(String userName, String user1Name) throws Throwable {
			verify(repository).getOrCreateUser(user1Name);
			verify(user).getPosts();

	}

	@When("^the user (.*) write a command (.*) to follow the user (.*)$")
	public void userWriteAFollowCommand(String userName, String followCommand, String followedUser) throws Throwable {
		
		Assert.assertFalse(commandFollow.matches(null));
		Assert.assertFalse(commandFollow.matches(""));
		Assert.assertTrue(commandFollow.matches(followCommand));

		user = spy(new User(userName));
		user1 = spy(new User(followedUser));;

		when(repository.getOrCreateUser(userName)).thenReturn(user);
		when(repository.getOrCreateUser(followedUser)).thenReturn(user1);
		

		commandFollow.execute(followCommand);
			
	}

	@Then("^the user (.*) subscribe to the timelines of (.*)$")
	public void userSubscribetheTimelineOfAnotherUser(String userName, String user1Name) throws Throwable {
		verify(repository).getOrCreateUser(userName);
		verify(repository).getOrCreateUser(user1Name);
		verify(user).addFollowing(user1);
		Assert.assertTrue(user.getFollowingUsers().contains(user1));
		
	}
	
	@When("^the user (.*) write a command (.*) to see all subscriptions of (.*)$")
	public void userWriteAWallCommand(String userName, String wallCommand, String followedUser) throws Throwable {
		
		Assert.assertFalse(commandWall.matches(null));
		Assert.assertFalse(commandWall.matches(""));
		Assert.assertTrue(commandWall.matches(wallCommand));

		user = spy(new User(userName));
		user1 = spy(new User(followedUser));;
		user.setPosts(Arrays.asList(new Post(userName, "I love the weather today", new Date(
				System.currentTimeMillis())),
				new Post(userName, "I love NY", new Date(System.currentTimeMillis()))));

		user1.setPosts(Arrays.asList(new Post(followedUser, "I love Paris", new Date(System.currentTimeMillis()))));

		user.addFollowing(user1);

		when(repository.getOrCreateUser(userName)).thenReturn(user);
		when(repository.getOrCreateUser(followedUser)).thenReturn(user1);
		

		commandWall.execute(wallCommand);
			
	}

	@Then("^the user (.*) can view an aggregated list of all subscriptions of (.*)$")
	public void userCanViewAggregateListOfAllSubscriptions(String userName, String user1Name) throws Throwable {
		verify(repository).getOrCreateUser(userName);
		verify(user).getPosts();
		verify(user).getFollowingUsers();

		verify(user1).getPosts();
		
	}

}
