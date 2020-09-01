package com.testmg.socialnetwork.repository;

import java.util.HashMap;
import java.util.Map;

import com.testmg.socialnetwork.domain.User;

public class InMemoryRepository implements Repository {
	private Map<String, User> userMap = new HashMap<String, User>();
	
	public User getOrCreateUser(String userName) {
		User user = userMap.get(userName);
		if (user == null) {
			user = new User(userName);
			userMap.put(userName, user);
		}
		return user;
	}

}
