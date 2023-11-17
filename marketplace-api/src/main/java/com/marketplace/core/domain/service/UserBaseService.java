package com.marketplace.core.domain.service;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import com.marketplace.adapters.outbound.entity.BusinessEntity;
import com.marketplace.adapters.outbound.entity.UserEntity;
import com.marketplace.adapters.outbound.repository.BusinessRepository;
import com.marketplace.adapters.outbound.repository.UserRepository;
import com.marketplace.core.domain.model.User;
import com.marketplace.core.ports.inbound.UserService;

@Service
public class UserBaseService implements UserService {

	@Autowired
	private UserRepository useRepository;
	
	@Autowired
	private BusinessRepository businessRepository; 
	
	@Override
	@CacheEvict(value = "userList", allEntries = true)
	public User create(User user) {

		if (validatePassword(user.getPassword())) {

			Optional<UserEntity> result = useRepository.findByEmail(user.getEmail());

			if (result.isEmpty()) {
				user.setPassword(user.getPassword());
				return useRepository.save(new UserEntity(user)).toModel();
			}
		}
		
		System.err.println("Senha não tá legal");
		return null;
	}

	@Override
	@CacheEvict(value = "userList", allEntries = true)
	public User update(User user) {

		if (user != null) {

			if (!validatePassword(user.getPassword())) {
				return null;
			}

			return useRepository.findById(user.getId()).map(result -> {

				result.setName(user.getName());
				result.setEmail(user.getEmail());				
				result.setPassword(user.getPassword());

				return useRepository.save(result).toModel();
			}).orElseGet(() -> {
				return null;
			});
		}

		return null;
	}

	@Override
	public User findById(Long id) {
		Optional<UserEntity> entity = useRepository.findById(id);

		if (entity.isPresent()) {
			return entity.get().toModel();
		}
		return null;
	}
	
	@Override
	@Cacheable(value = "userList")
	public Page<User> findByAttributes(String userEmail, String userName, Pageable pageable) {

		Page<UserEntity> entity = null;

		if (userName == null && userEmail == null) {
			entity = this.useRepository.findAll(pageable);
		} else {
			if (userName != null) {
				userName = userName.concat("%");
			}

			entity = this.useRepository.findUserByEmailAndName(userEmail, userName, pageable);
		}

		if (entity != null && !entity.isEmpty()) {
			return entity.map(item -> item.toModel());
		} else {
			return null;
		}
	}

	@Override
	public User findByEmail(String email) {
		Optional<UserEntity> entity = useRepository.findByEmail(email);

		if (entity.isPresent()) {
			return entity.get().toModel();
		}
		return null;
	}

	@Override
	public boolean deleteById(Long id) {
		Optional<UserEntity> result = useRepository.findById(id);
		
		if (result.isPresent()) {
			
			Optional<BusinessEntity> businessEntity = businessRepository.findBusinessByOwnerUser(id);
			
			if(businessEntity.isEmpty()) {
				
				useRepository.deleteById(id);
				return true;
			}
			/**
			 * TODO Emplementar regra de retorno para o front informando que o usuário tem
			 * um negocio aberto e não pode excluir a conta!
			 */
			System.err.println("Usuario possuí negocio atrelado");
		}
		
		return false;
	}
	
	private boolean validatePassword(String password) {
		return true;
		/*if (password != null && password.length() <= 30) {
			// Verificar se é isso que o enunciado pede
			for (int i = 1; i < password.length(); i++) {
				if (password.charAt(i) == password.charAt(i - 1)) {
					return false;
				}
			}		
			String regex = "^(?=.*[0-9])(?=.*[a-z])(?=.*[A-Z])(?=.*[@#$%¨&^(*)_+=!?+-/.<>])(?=\\S+$).{9,20}$";
			Pattern p = Pattern.compile(regex);
			Matcher m = p.matcher(password);
			return m.matches();
		}
		return false;*/
	}
	
}
