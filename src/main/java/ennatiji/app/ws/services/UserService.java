package ennatiji.app.ws.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;

import ennatiji.app.ws.entities.UserEntity;
import ennatiji.app.ws.requests.UserRequest;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import ennatiji.app.ws.Reposetories.UserReposetory;

@Service
public class UserService {
	
	@Autowired
	UserReposetory userReposetory;

	
public void createUser(UserRequest userRequest) {
		
		UserEntity userEntity = new UserEntity();
		
		userEntity.setFullName(userRequest.getFullName());
		userEntity.setEmail(userRequest.getEmail());
		
		userReposetory.save(userEntity);
				
	}

public List<UserEntity> getUsers(int page, int limit) {
	
	if(page > 0) page = page - 1;
		
	Pageable pageableRequest = PageRequest.of(page, limit);
	
	Page<UserEntity> userPage;
	
	userPage = userReposetory.findAllUsers(pageableRequest);
	
	List<UserEntity> users = userPage.getContent();
	
	
	
	return users;
}

}
