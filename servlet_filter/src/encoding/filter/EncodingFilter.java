package encoding.filter;

import java.io.IOException;
import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

// @WebFilter("/test/*")
@WebFilter(
	urlPatterns = {
		"/test/a",
		"/test/b"
	}
)
public class EncodingFilter implements Filter {

    public EncodingFilter() {
    }

	public void destroy() {
	}

	public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain)
			throws IOException, ServletException {
		// 사용자 요청 메시지에 대한 필터 기능 동작
		System.out.println("EncodingFilter doFilter() 시작");
		request.setCharacterEncoding("UTF-8");
		
		chain.doFilter(request, response); // 다음 필터 실행
		
		// 사용자 응답 메시지에 대한 필터 기능 동작
		response.setCharacterEncoding("UTF-8");
		System.out.println("EncodingFilter doFilter() 종료");
	}

	public void init(FilterConfig fConfig) throws ServletException {
		System.out.println("EncodingFilter init() 동작");
	}

}
