package com.example.meus_gastos;

import com.example.meus_gastos.DTOs.User.CreateUserDTO;
import com.example.meus_gastos.DTOs.User.ResponseUserDTO;
import com.example.meus_gastos.domain.User.UserEntity;
import com.example.meus_gastos.mapper.User.UserMap;
import com.example.meus_gastos.repositories.User.UserRepository;
import com.example.meus_gastos.services.UserService;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.math.BigDecimal;
import java.util.List;

import static org.hibernate.validator.internal.util.Contracts.assertNotNull;
import static org.junit.Assert.assertEquals;

@DisplayName("Users")
class MeusGastosApplicationTests {

	@Autowired
	private UserService userService;

	@Autowired
	private UserRepository userRepository;

	@Autowired
	private UserMap userMap;

	@Test
	@DisplayName("Find all")
	public void testFindAll() throws Exception {
		CreateUserDTO userDTO =  CreateUserDTO.builder()
				.firstName("John")
				.lastName("Doe")
				.email("john.doe@example.com")
				.document("12345666")
				.password("securepassword")
				.phone("555-1234")
				.totalBalance(BigDecimal.valueOf(1000.0))
				.build();

		//Salva o usuário
		userService.createUser(userDTO);

		//Busca todos usuarios

		List<UserEntity> users = userRepository.findAll();

		assertNotNull(users);
		assertEquals(1, users.size());

		UserEntity savedUser = users.get(0);
		assertEquals("John", savedUser.getFirstName());
		assertEquals("Doe", savedUser.getLastName());
		assertEquals("john.doe@example.com", savedUser.getEmail());
		assertEquals("12345678901", savedUser.getDocument());
		assertEquals("securepassword", savedUser.getPassword());
		assertEquals("555-1234", savedUser.getPhone());
		assertEquals(BigDecimal.valueOf(1000.00), savedUser.getTotalBalance());

	}
}
