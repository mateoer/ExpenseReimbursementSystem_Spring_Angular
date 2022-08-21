package root.service.security;

//@Configuration
public class UserDetailServiceConfiguration {
	
	/*
	 * @Autowired private DataSource dataSource;
	 * 
	 * @Bean public UserDetailsManager authenticateUsers() {
	 * 
	 * UserDetails user = User.withUsername("username")
	 * .password(PasswordEncoderFactories.createDelegatingPasswordEncoder().encode(
	 * "password")).build(); JdbcUserDetailsManager users = new
	 * JdbcUserDetailsManager(dataSource); users.
	 * setAuthoritiesByUsernameQuery("select user_name,user_pwd,user_enabled from user where user_name=?"
	 * ); users.
	 * setUsersByUsernameQuery("select user_name,user_role from user where user_name=?"
	 * ); users. createUser(user); return users; }
	 */

	/*
	 * @Bean public UserDetailsService userDetailsService(BCryptPasswordEncoder
	 * bCryptPasswordEncoder) { InMemoryUserDetailsManager manager = new
	 * InMemoryUserDetailsManager();
	 * manager.createUser(User.withUsername("suechan").password(
	 * bCryptPasswordEncoder.encode("abc123")).roles("EMPLOYEE").build());
	 * manager.createUser(User.withUsername("mateoer").password(
	 * bCryptPasswordEncoder.encode("abc123")).roles("MANAGER").build()); return
	 * manager; }
	 * 
	 * 
	 * @Bean public BCryptPasswordEncoder bCryptPasswordEncoder() { return new
	 * BCryptPasswordEncoder(); }
	 */
}
