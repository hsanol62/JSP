package sec01.ex01;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.OutputStream;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet("/download.do")
public class FileDownload extends HttpServlet {
    private static final long serialVersionUID = 1L;

    public FileDownload() {
        super();
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        doHandle(request, response);
    }

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        doHandle(request, response);
    }
    private void doHandle(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.setCharacterEncoding("utf-8");
        response.setContentType("text/html; charset=utf-8");

        String file_repo = "C:\\file_repo";
        String fileName = (String)request.getParameter("fileName");
        //전송된 fileName 불러옴
        System.out.println("fileName=" + fileName);

        OutputStream out = response.getOutputStream();
        //// response에서 OutputStream 객체를 가져와 OutputStream out변수에 저장
        
        String downFile = file_repo + "\\" + fileName;
        File f = new File(downFile);
        //파일 다운로드 하도록 downFile 저장할 File f 생성
        //https://windorsky.tistory.com/entry/JSP%EC%97%90%EC%84%9C-%ED%8C%8C%EC%9D%BC-%EB%8B%A4%EC%9A%B4%EB%A1%9C%EB%93%9C?category=818972

        response.setHeader("Cache-Control", "no-cache");
        //파일다운로드는 캐싱안되도록 설정
        response.addHeader("Content-disposition", "attachment; fileName=" + fileName);
        // file download처리 시 attachmemt 로 설정
		// attachment: 파일 다운로드 대상상자가 뜸.

        //Content-disposition :HTTP Response Body에 오는 컨텐츠 기질/성향을 알려주는 속성(파일 다운로드를 처리하는 HTTP 헤더 중 하나)
        //웹 서버 응답에 이 헤더를 포함하면 그 내용(보통 사진 등의 파일 데이터)을 웹 브라우저로 바로 보거나 내려받도록 설정
        //default=inline
        // inline이면 파일을 웹 브라우저 내에서 볼 수 있고, attachment이면 파일을 내려받을 수 있다.
        
        FileInputStream in = new FileInputStream(f);

        byte[] buffer = new byte[1024 * 8];

        while(true) {
            int count = in.read(buffer);
            if(count == -1) {
                break;
            }
            out.write(buffer, 0, count);
        }
        in.close();
        out.close();
    }
}
