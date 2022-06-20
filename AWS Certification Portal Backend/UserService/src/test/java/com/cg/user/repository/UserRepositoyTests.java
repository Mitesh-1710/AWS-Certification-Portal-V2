package com.cg.user.repository;

import static org.assertj.core.api.Assertions.assertThat;

import java.util.List;
import java.util.Optional;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.mongo.embedded.EmbeddedMongoAutoConfiguration;
import org.springframework.boot.test.autoconfigure.data.mongo.DataMongoTest;

import com.cg.user.model.UserModel;


@DataMongoTest(excludeAutoConfiguration = EmbeddedMongoAutoConfiguration.class)
class UserRepositoyTests {

	@Autowired
	private UserRepository userRepo;

	//JUnit test for save user 
	@DisplayName("JUnit test for save user")
	@Test
	public void givenUserObject_whenSave_thenReturnSavedUser() {
		
		//given
		UserModel user = new UserModel(1,"Mitesh Mayavanshi","mitesh@gmail.com",9586107216L,"A4","Mitesh123","2022-03-17","JFS React");
		
		//when
		UserModel savedUser = userRepo.save(user);
	
		//then
		assertThat(savedUser).isNotNull();
		assertThat(savedUser.getEmpID()).isGreaterThan(0);
	}
	
	// JUnit test for get all users 
    @DisplayName("JUnit test for get all users")
    @Test
    public void givenUsersList_whenFindAll_thenUsersList(){
    	
    	//given
    	UserModel user = new UserModel(1,"Mitesh Mayavanshi","mitesh@gmail.com",9586107216L,"A4","Mitesh123","2022-03-17","JFS React");
    	UserModel user1 = new UserModel(2,"Vaibhav Kamthan","vaibhav@gmail.com",7016108807L,"A4","Vaibhav123","2022-03-17","JFS React");		
    	userRepo.save(user);
    	userRepo.save(user1);

        //when
        List<UserModel> userList = userRepo.findAll();

        //then
        assertThat(userList).isNotNull();

    }
    
    // JUnit test for get user by id 
    @DisplayName("JUnit test for get user by id")
    @Test
    public void givenUserObject_whenFindById_thenReturnUserObject(){
		
		//given
		UserModel user = new UserModel(1,"Mitesh Mayavanshi","mitesh@gmail.com",9586107216L,"A4","Mitesh123","2022-03-17","JFS React");
		userRepo.save(user);

        //when 
		UserModel userDB = userRepo.findById(user.getEmpID()).get();

        //then
        assertThat(userDB).isNotNull();
    }

    // JUnit test for get user by email operation
    @DisplayName("JUnit test for get user by email")
    @Test
    public void givenUserEmail_whenFindByEmail_thenReturnUserObject(){

		//given
		UserModel user = new UserModel(1,"Mitesh Mayavanshi","mitesh@gmail.com",9586107216L,"A4","Mitesh123","2022-03-17","JFS React");
		userRepo.save(user);

        //when 
		UserModel userDB = userRepo.findByEmail(user.getEmail()).get();

        //then
        assertThat(userDB).isNotNull();
    }
    
    // JUnit test for if user email exist in the database or not
    @DisplayName("JUnit test for if user email exist in the database or not")
    @Test
    public void givenUserEmail_whenExistByEmail_thenReturnTrue(){

		//given
		UserModel user = new UserModel(1,"Mitesh Mayavanshi","mitesh@gmail.com",9586107216L,"A4","Mitesh123","2022-03-17","JFS React");
		userRepo.save(user);

        //when 
		boolean userDB = userRepo.existsByEmail(user.getEmail());

        //then
        assertThat(userDB).isTrue();
    }

    // JUnit test for update user
    @DisplayName("JUnit test for update user")
    @Test
    public void givenUserObject_whenUpdateUser_thenReturnUpdatedUser(){

		//given
		UserModel user = new UserModel(1,"Mitesh Mayavanshi","mitesh@gmail.com",9586107216L,"A4","Mitesh123","2022-03-17","JFS React");
		userRepo.save(user);

        //when 
		UserModel savedUserDB = userRepo.findById(user.getEmpID()).get();
		savedUserDB.setEmail("mitesh.maya@gmail.com");
		UserModel updatedUser = userRepo.save(savedUserDB);

        //then
        assertThat(updatedUser .getEmail()).isEqualTo("mitesh.maya@gmail.com");

    }

    // JUnit test for delete user
    @DisplayName("JUnit test for delete user")
    @Test
    public void givenUserObject_whenDelete_thenRemoveUser(){
		
    	//given
		UserModel user = new UserModel(1,"Mitesh Mayavanshi","mitesh@gmail.com",9586107216L,"A4","Mitesh123","2022-03-17","JFS React");
		userRepo.save(user);

        //when
		userRepo.deleteById(user.getEmpID());
        Optional<UserModel> userOptional = userRepo.findById(user.getEmpID());

        //then
        assertThat(userOptional).isEmpty();
    }
    
}
