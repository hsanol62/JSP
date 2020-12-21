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
        //���۵� fileName �ҷ���
        System.out.println("fileName=" + fileName);

        OutputStream out = response.getOutputStream();
        //// response���� OutputStream ��ü�� ������ OutputStream out������ ����
        
        String downFile = file_repo + "\\" + fileName;
        File f = new File(downFile);
        //���� �ٿ�ε� �ϵ��� downFile ������ File f ����
        //https://windorsky.tistory.com/entry/JSP%EC%97%90%EC%84%9C-%ED%8C%8C%EC%9D%BC-%EB%8B%A4%EC%9A%B4%EB%A1%9C%EB%93%9C?category=818972

        response.setHeader("Cache-Control", "no-cache");
        //���ϴٿ�ε�� ĳ�̾ȵǵ��� ����
        response.addHeader("Content-disposition", "attachment; fileName=" + fileName);
        // file downloadó�� �� attachmemt �� ����
		// attachment: ���� �ٿ�ε� �����ڰ� ��.

        //Content-disposition :HTTP Response Body�� ���� ������ ����/������ �˷��ִ� �Ӽ�(���� �ٿ�ε带 ó���ϴ� HTTP ��� �� �ϳ�)
        //�� ���� ���信 �� ����� �����ϸ� �� ����(���� ���� ���� ���� ������)�� �� �������� �ٷ� ���ų� �����޵��� ����
        //default=inline
        // inline�̸� ������ �� ������ ������ �� �� �ְ�, attachment�̸� ������ �������� �� �ִ�.
        
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
