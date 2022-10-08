//package root.service.security;
//
//import java.io.IOException;
//
//import javax.servlet.Filter;
//import javax.servlet.FilterChain;
//import javax.servlet.FilterConfig;
//import javax.servlet.ServletException;
//import javax.servlet.ServletRequest;
//import javax.servlet.ServletResponse;
//import javax.servlet.http.HttpServletResponse;
//
//import org.slf4j.Logger;
//import org.slf4j.LoggerFactory;
//import org.springframework.stereotype.Component;
//
//@Component
//public class SimpleCORSFilter implements Filter {
//
//	private final Logger log = LoggerFactory.getLogger(SimpleCORSFilter.class);
//
//	public SimpleCORSFilter() {
//		log.info("SimpleCORSFilter init");
//	}
//
//	@Override
//	public void doFilter(ServletRequest req, ServletResponse res, FilterChain chain)
//			throws IOException, ServletException {
//
////		HttpServletRequest request = (HttpServletRequest) req;
//		HttpServletResponse response = (HttpServletResponse) res;
//
////		response.setHeader("Access-Control-Allow-Origin", request.getHeader("Origin"));
//		response.setHeader("Allow-Origin-With-Credentials", "true");
//		response.setHeader("Access-Control-Allow-Origin", "http://localhost:4200");
////		response.setHeader("Access-Control-Allow-Origin","*"); 
//		response.setHeader("Access-Control-Allow-Credentials", "true");
//		response.setHeader("Access-Control-Allow-Methods", "POST, GET, OPTIONS, DELETE, PUT");
//		response.setHeader("Access-Control-Max-Age", "3600");
//		response.setHeader("Access-Control-Allow-Headers", "Content-Type, Accept, X-Requested-With, remember-me, Cookie, Set-Cookie");
//
//		chain.doFilter(req, res);
//	}
//
//	@Override
//	public void init(FilterConfig filterConfig) {
//	}
//
//	@Override
//	public void destroy() {
//	}
//
//}