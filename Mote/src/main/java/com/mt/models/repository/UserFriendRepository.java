package com.mt.models.repository;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

import com.mt.models.UserFriends;

public interface UserFriendRepository extends CrudRepository<UserFriends, Long> {
	@Query("select u.id from UserFriends u where u.profileId = :profileId and u.friend.profileId = :friendId")
	Long findUserFriend(@Param("profileId") long profileId, @Param("friendId") long friendId);
}