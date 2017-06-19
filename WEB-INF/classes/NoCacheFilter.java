
import java.io.IOException;
import javax.servlet.Filter;
import javax.servlet.http.*;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.annotation.WebFilter;

public class NoCacheFilter implements Filter {


	public void doFilter(ServletRequest req, ServletResponse res, FilterChain chain) throws IOException, ServletException {
		HttpServletResponse response = (HttpServletResponse) res;
		response.setHeader("Cache-Control", "no-cache, no-store, must-revalidate"); 
        response.setHeader("Pragma", "no-cache"); 
        response.setDateHeader("Expires", 0); 
		chain.doFilter(req, res);
	}
	public void init(FilterConfig fConfig) throws ServletException {
		
	}

	public void destroy(){
		
	}
}
