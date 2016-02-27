package com.mt.models.repository;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

import com.mt.models.UserFriends;

public interface UserFriendRepository extends CrudRepository<UserFriends, Long> {
	@Query("select u.id from UserFriends u where u.profileId = ? and u.friend.profileId = ?")
	Long findUserFriend(long profileId, long friendId);
}