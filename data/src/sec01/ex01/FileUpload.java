package sec01.ex01;

import java.io.File;
import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.fileupload.FileItem;
import org.apache.commons.fileupload.FileUploadException;
import org.apache.commons.fileupload.disk.DiskFileItemFactory;
import org.apache.commons.fileupload.servlet.ServletFileUpload;

/**
 * Servlet implementation class FileUpload
 */
@WebServlet("/upload.do")
public class FileUpload extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		doHandle(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		doHandle(request, response);
	}

	private void doHandle(HttpServletRequest request, HttpServletResponse response)	throws ServletException, IOException {
		request.setCharacterEncoding("utf-8");
			String encoding = "utf-8";
			File currentDirPath = new File("C:\\file_repo");
			//���ε��� ���� ��θ� ����
			DiskFileItemFactory factory = new DiskFileItemFactory();
			factory.setRepository(currentDirPath);
			//1�ް��� ������ currentDirPath ��� ������ �̵�, ���� ��θ� ����
			factory.setSizeThreshold(1024 * 1024);
//	�ִ� ���ε� ������ ���� ũ�⸦ ���� byte ����

			ServletFileUpload upload = new ServletFileUpload(factory);
			// ���ε� ��û�� ó���ϴ� ServletFileUpload ����
			try { 
				//list�� input �Ȱ� ����
				List items = upload.parseRequest(request);
				//request ��ü���� �Ű������� List�� ������
				//���ε� ��û �Ľ��ؼ� Item ��� ����
				for (int i = 0; i < items.size(); i++) {
					FileItem fileItem = (FileItem) items.get(i);
	//���� ���ε� â���� ���ε�� �׸���� �ϳ��� ������
					if (fileItem.isFormField()) {
						//fileItesm�� formField�� ���۵� �Ű����� �� ���
						System.out.println(fileItem.getFieldName() + "=" + fileItem.getString(encoding));
					} else {
						//������ ��� ���ε�
						System.out.println("�Ķ���͸�:" + fileItem.getFieldName());
						//�Ķ���͸�  ���
						System.out.println("���ϸ�:" + fileItem.getName());
						//���ϸ� ���
						System.out.println("����ũ��:" + fileItem.getSize() + "bytes");
						//����ũ�� ���
						if (fileItem.getSize() > 0) {
							//���ε��� ������ �����ϴ� ���
							int idx = fileItem.getName().lastIndexOf("\\");
							//������ ���

							if (idx == -1) {
								//���н�, ������ ���
								idx = fileItem.getName().lastIndexOf("/");
							}
							String fileName = fileItem.getName().substring(idx + 1);
//		�����̸����� ����ҿ� ���� ���ε�					
							File uploadFile = new File(currentDirPath + "\\" + fileName);
							fileItem.write(uploadFile);
						} // end if
					} // end if
				} // end for
			} catch (Exception e) {
				e.printStackTrace();
			}
	}

}
