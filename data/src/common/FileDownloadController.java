package common;

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
public class FileDownloadController extends HttpServlet{
	private static String ARTICLE_IMAGE_REPO ="C:\\board\\article_image";
	
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException{
		doHandle(request, response);
	}
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException{
		doHandle(request, response);
	}
	protected void doHandle(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException{
		request.setCharacterEncoding("utf-8");
		response.setContentType("text/html;charset=utf-8");
		//�̹��� ���� �̸��� �� ��ȣ�� �����ɴϴ�.
		String imageFileName = (String) request.getParameter("imageFileName");
		String articleNO = request.getParameter("articleNO");
		//�̹��� ���� �̸��� �� ��ȣ�� ������
		System.out.println("imageFileName="+ imageFileName);
		OutputStream out = response.getOutputStream();
		// �� ��ȣ�� ���� ���� ��θ� �����մϴ�.
		String path = ARTICLE_IMAGE_REPO + "\\" + articleNO+"\\"+imageFileName;
		File imageFile = new File(path);
		System.out.println(imageFile);
		
		response.setHeader("Cache-Control",  "no-cache");
		// �̹��� ������ ���� �޴� �� �ʿ��� response�� ��� ������ �����մϴ�.
		response.addHeader("Content-disposition",  "attachment;fileName=" + imageFileName);
		FileInputStream in = new FileInputStream(imageFile);
		// ���۸� �̿��� �� ���� 8kb�� �����մϴ�.
		byte[] buffer = new byte[1024 * 8];
		while(true) {
			int count = in.read(buffer);
			if(count == -1)
				break;
			out.write(buffer, 0 , count);
		}
		in.close();
		out.close();
	}
}