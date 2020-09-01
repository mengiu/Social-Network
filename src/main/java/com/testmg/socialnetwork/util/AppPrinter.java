package com.testmg.socialnetwork.util;

import com.testmg.socialnetwork.domain.Post;

public interface AppPrinter {

	/**
	 * Display the given post as it will appear on the wall.
	 * 
	 * @param post
	 *            The post to be displayed.
	 */
	public void displayPostForWall(Post post);

	/**
	 * Display the given post as it will appear on the user timeline.
	 * 
	 * @param post
	 *            The post to be displayed.
	 */
	public void displayPostForUser(Post post);

	/**
	 * Display the specified message.
	 * 
	 * @param message
	 *            The message to be displayed.
	 */
	public void displayMessage(String message);

}
