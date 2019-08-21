/**
 * 355
 * Performance: speed=%, memory=%
 */

import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Queue;
import java.util.Set;
import java.util.HashSet;
import java.util.PriorityQueue;

public class Twitter {
	private int uuid = 0;
	private Map<Integer, Integer> uuid2tid;
	private Map<Integer, User> users;


	/** Initialize your data structure here. */
	public Twitter() {
		uuid2tid = new HashMap<>();
		users = new HashMap<>();
	}
	
	/** Compose a new tweet. */
	public void postTweet(int user, int tid) {
		if (users.get(user) == null) users.put(user, new User(user));
		int old = users.get(user).post(++uuid);
		uuid2tid.put(uuid, tid);
		if (old != 0) uuid2tid.remove(old);
	}

	/** Retrieve the 10 most recent tweet ids in the user's news feed;
		Each item in the news feed must be posted by users who the user followed or by the user herself;
		Tweets must be ordered from most recent to least recent */
	public List<Integer> getNewsFeed(int user) {
		if (users.get(user) == null) users.put(user, new User(user));

		PriorityQueue<Item> q = new PriorityQueue<>();
		User u = users.get(user);

		for (Integer fid : u.followees) {
			User f = users.get(fid); // fool: get(user)
			if (f.latest != -1) {
				q.offer(new Item(f, f.tweets[f.latest], f.latest));
			}
		}

		List<Integer> list = new ArrayList<>(10);
		// System.out.printf("q.size()=%d\n", q.size());
		for (int i = 0; !q.isEmpty() && i < 10; ++i) {
			Item newest = q.poll();
			// System.out.printf("newest: user=%d, idx=%d, uuid=%d (#=%d)\n",
				// newest.user.id, newest.idx, newest.uuid, q.size());
			list.add(uuid2tid.get(newest.uuid));

			int latest = (newest.idx + newest.user.tweets.length - 1) % newest.user.tweets.length;
			// System.out.printf("latest: %d -> %d, val=%d\n", newest.idx, latest, newest.user.tweets[latest]);
			if (newest.user.tweets[latest] != 0) {
				newest.idx = latest;
				newest.uuid = newest.user.tweets[latest];
				q.offer(newest);
			}
		}

		return list;
	}
	
	/** Follower follows a followee. If the operation is invalid, it should be a no-op. */
	public void follow(int follower, int followee) {
		if (users.get(follower) == null) users.put(follower, new User(follower));
		if (users.get(followee) == null) users.put(followee, new User(followee));
		users.get(follower).follow(followee);
	}
	
	/** Follower unfollows a followee. If the operation is invalid, it should be a no-op. */
	public void unfollow(int follower, int followee) {
		if (users.get(follower) == null) users.put(follower, new User(follower));
		if (users.get(followee) == null) users.put(followee, new User(followee));
		if (follower != followee) users.get(follower).unfollow(followee);
	}

	public static void main(String args[]) {
	}
}

class User {
	int id;
	int[] tweets;
	int latest;
	Set<Integer> followees;

	User(int id) {
		this.id = id;
		tweets = new int[10];
		latest = -1;
		followees = new HashSet<>();
		followees.add(id);
	}

	void follow(int another) {
		followees.add(another);
	}

	void unfollow(int another) {
		followees.remove(another);
	}

	int post(int uuid) {
		latest = (latest+1) % tweets.length;
		int old = tweets[latest];
		tweets[latest] = uuid;
		return old;
	}
}

class Item implements Comparable<Item> {
	User user;
	int uuid, idx;
	Item(User user, int uuid, int idx) {
		this.user = user;
		this.uuid = uuid;
		this.idx = idx;
	}

	@Override
	public int compareTo(Item x) {
		return x.uuid - uuid;
	}
}
