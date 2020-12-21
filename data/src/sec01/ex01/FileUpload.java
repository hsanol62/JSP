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
			//업로드할 파일 경로를 지정
			DiskFileItemFactory factory = new DiskFileItemFactory();
			factory.setRepository(currentDirPath);
			//1메가가 넘으면 currentDirPath 경로 폴더로 이동, 파일 경로를 설정
			factory.setSizeThreshold(1024 * 1024);
//	최대 업로드 가능한 파일 크기를 설정 byte 단위

			ServletFileUpload upload = new ServletFileUpload(factory);
			// 업로드 요청을 처리하는 ServletFileUpload 생성
			try { 
				//list로 input 된것 받음
				List items = upload.parseRequest(request);
				//request 객체에서 매개변수를 List로 가져옴
				//업로드 요청 파싱해서 Item 목록 구함
				for (int i = 0; i < items.size(); i++) {
					FileItem fileItem = (FileItem) items.get(i);
	//파일 업로드 창에서 업로드된 항목들을 하나씩 가져옴
					if (fileItem.isFormField()) {
						//fileItesm이 formField면 전송된 매개변수 값 출력
						System.out.println(fileItem.getFieldName() + "=" + fileItem.getString(encoding));
					} else {
						//파일일 경우 업로드
						System.out.println("파라미터명:" + fileItem.getFieldName());
						//파라미터명  출력
						System.out.println("파일명:" + fileItem.getName());
						//파일명 출력
						System.out.println("파일크기:" + fileItem.getSize() + "bytes");
						//파일크기 출력
						if (fileItem.getSize() > 0) {
							//업로드한 파일이 존재하는 경우
							int idx = fileItem.getName().lastIndexOf("\\");
							//윈도우 기반

							if (idx == -1) {
								//유닉스, 리눅스 기반
								idx = fileItem.getName().lastIndexOf("/");
							}
							String fileName = fileItem.getName().substring(idx + 1);
//		파일이름으로 저장소에 파일 업로드					
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
