import java.util.Optional;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import org.junit.Before;
import org.junit.Test;
import com.test.java.op.User.Address;
import com.test.java.op.User.Country;
import lombok.extern.slf4j.Slf4j;
@Slf4j
public class OptionalCase {

	private User user;
	private User user1;
	
	@Before
	public void init() {
		log.info(" init ---------- ");
		user = User.builder().id(1).name("weapon")
			.address(Address.builder().street("houjie").build())
			.country(Country.builder().city("xa").build())
			.build();
		user1 = User.builder().id(2).name("king").build();
	}
	
	@Test
	public void test() {
		Optional<User> op = Optional.of(user1);
		Optional<User.Address> opa =  Optional.ofNullable(op.get().getAddress());
		assertFalse(opa.isPresent());
	}
	
	@Test
	public void test1() {
		User u = Optional.ofNullable(user).orElse(builderUser());
		assertEquals(u.getId(), 1);
		log.info("----------------------");
		u = Optional.of(user).orElse(builderUser());
		log.info("----------------------");
		u = Optional.ofNullable(user).orElseGet(() -> builderUser());
		/**
		 * orElse 有无都会创建默认值
		 */
	}
	
	private User builderUser() {
		log.info("create user");
		return User.builder().build();
	}
}

import java.io.Serializable;
import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class User implements Serializable {

	private static final long serialVersionUID = 1L;
	
	private int id;
	private String name;
	private Address address;
	private Country country;
	
	@Data
	@Builder
	public static class Country {
		private String city;
	}
	
	@Data
	@Builder
	public static class Address {
		private String street;
	}
	
	
}
