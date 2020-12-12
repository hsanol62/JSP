package Dec11;
import java.io.IOException;
import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;

@WebFilter("/*")
//루트로부터 모든 페이지에 접근 //모든것 실행할때 필터적용
public class EncoderFilter2 implements Filter {
    ServletContext context;
    public EncoderFilter2() {
    }

    public void destroy() {
        System.out.println("destroy 호출");
    }
    //필터 나갈 떄 실행

    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {
        System.out.println("doFilter 호출");
        //필터 작업
        request.setCharacterEncoding("utf-8");
        String context = ((HttpServletRequest) request).getContextPath();
        String pathinfo = ((HttpServletRequest) request).getRequestURI();
        String realPath = request.getRealPath(pathinfo);
        String mesg = " Context 정보:" + context 
        		+ "\n URI 정보: "+  pathinfo 
        		+ "\n 물리적 경로 : "+realPath;
        System.out.println(mesg);

        long begin = System.currentTimeMillis();
        //요청 필터에서 요청 처리 전의 시각을 구합니다. 
        chain.doFilter(request, response);
        long end = System.currentTimeMillis();
        //요청 필터에서 요청 처리 후의 시각을 구합니다. 
        System.out.println("작업 시간:"+(end -begin )+ "ms");
//직업 요청 전과 후의 시각 차를 구해 작업 수행 시간을 구합니다.
    }

    public void init(FilterConfig fConfig) throws ServletException {
        System.out.println("utf-8 인코딩 ,........");
        context = fConfig.getServletContext();
//필터에 들어올때 처음 실행
    }

}