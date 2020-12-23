package sec03.brd03;
import java.io.File;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.fileupload.FileItem;
import org.apache.commons.fileupload.disk.DiskFileItemFactory;
import org.apache.commons.fileupload.servlet.ServletFileUpload;
import org.apache.commons.io.FileUtils;

/*@WebServlet("/board/listArticle.do")
*/public class BoardController extends HttpServlet {
	private static String ARTICLE_IMAGE_REPO = "\\C:\\board\\article_image";
	BoardService boardService;
	ArticleVO articleVO;
	
	public void init(ServletConfig config) throws ServletException{
		boardService = new BoardService();
		articleVO = new ArticleVO();
	}
	
	public void doGet(HttpServletRequest request, HttpServletResponse response) 
			throws ServletException, IOException{
		doHandle(request, response);
	}
	public void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException{
		doHandle(request, response);
	}
	
	public void doHandle(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException{
		String nextPage ="";
		request.setCharacterEncoding("utf-8");
		response.setContentType("text/html;charset=utf-8");
		String action = request.getPathInfo();
		System.out.println("action:" + action);
		
		try {
			List<ArticleVO> articlesList = new ArrayList<ArticleVO>();
			if(action == null) {
				//action�� null�̸�
				articlesList = boardService.listArticles();
				//acriclesList ����
				request.setAttribute("articlesList", articlesList);
				//acriclesList �� acriclesList  �� ����
				nextPage = "/board02/listArticles.jsp";
				//board02/listArticles.jsp����
			} else if (action.equals("/listArticles.do")) {
				//listArticles.do �϶�
				articlesList = boardService.listArticles();
				request.setAttribute("articlesList",  articlesList);
				nextPage = "/board02/listArticles.jsp";
				//listArticles.jsp ���� ����������
			} else if(action.equals("/articleForm.do")) {
				//action articleForm.do�ϋ�
				nextPage ="/board02/articleForm.jsp";
				//board02/articleForm.jsp ����
			} else if(action.equals("/addArticle.do")) {
				int articleNO=0;
				Map<String, String> articleMap = upload(request, response);
				String title = articleMap.get("title");
				String content = articleMap.get("content");
				String imageFileName = articleMap.get("imageFileName");
				articleVO.setParentNO(0);
				articleVO.setId("hong");
				articleVO.setTitle(title);
				articleVO.setContent(content);
				articleVO.setImageFileName(imageFileName);
				// ���̺� �� ���� �߰��� �� �� �ۿ� ���� �� ��ȣ�� �����ɴϴ�.
				articleNO= boardService.addArticle(articleVO);
				
				// ������ ÷���� ��쿡�� �����մϴ�.
				if(imageFileName !=null && imageFileName.length() !=0) {
					//temp ������ �ӽ÷� ���ε� �� ���� ��ü�� �����մϴ�.
					File srcFile = new File(ARTICLE_IMAGE_REPO +"\\"+"temp"+"\\"+imageFileName);
					//��� ������ �� ��ȣ�� ������ �����մϴ�.
					File destDir = new File(ARTICLE_IMAGE_REPO +"\\"+articleNO);
					destDir.mkdirs();
					//temp ������ ������ �� ��ȣ�� �̸����� �ϴ� ������ �̵���ŵ�ϴ�.
					FileUtils.moveFileToDirectory(srcFile, destDir, true);
				}

				PrintWriter pw = response.getWriter();
				pw.print("<script>" 
				         +"  alert('������ �߰��߽��ϴ�.');" 
						 +" location.href='"+request.getContextPath()+"/board/listArticles.do';"
				         +"</script>");
				//�� �� ��� �޽����� ��Ÿ�� �� �ڹٽ�ũ��Ʈ location ��ü�� href �Ӽ��� �̿��� �� ����� ��û�մϴ�.
				return;
			}

			RequestDispatcher dispatch = request.getRequestDispatcher(nextPage);
			dispatch.forward(request, response);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	private Map<String, String> upload(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		Map<String, String> articleMap = new HashMap<String, String>();
		//Map ����
		String encoding = "utf-8";
		File currentDirPath = new File(ARTICLE_IMAGE_REPO);
		//���ϼҽ�, ���� ������, ���� �ִ� ������ �ʿ�
		DiskFileItemFactory factory = new DiskFileItemFactory();
		//DiskFileItemFactory�� ���� factory �ν��Ͻ� ����
		factory.setRepository(currentDirPath);
		//��ε� ���� ���� ���
		factory.setSizeThreshold(1024 * 1024);
		//factory ������ ����
		ServletFileUpload upload = new ServletFileUpload(factory);
		//upload ��ü ����
		try {
			List items = upload.parseRequest(request);
			//list items ����
			for (int i = 0; i < items.size(); i++) {
				FileItem fileItem = (FileItem) items.get(i);
				//FIleItem fileItem�� �� ����
				if (fileItem.isFormField()) {
					//. FileItem�� �� �Է� �׸����� ���ο� ���� �˸��� ó��
					System.out.println(fileItem.getFieldName() + "=" 
				+ fileItem.getString(encoding));
					articleMap.put(fileItem.getFieldName(), fileItem.getString(encoding));
				} else {
					System.out.println("�Ķ���͸�:" + fileItem.getFieldName());
			//���� �� ���
					System.out.println("����ũ��:" + fileItem.getSize() + "bytes");
		//����ũ�� ���
					if (fileItem.getSize() > 0) {
						int idx = fileItem.getName().lastIndexOf("\\");//��������
						if (idx == -1) {//���н�, ������ ���
							idx = fileItem.getName().lastIndexOf("/");
						}

						// ÷���� ������ ���� temp ������ ���ε� �մϴ�.
						String fileName = fileItem.getName().substring(idx + 1);
						//���ϸ� ����
						System.out.println("���ϸ�:" + fileName);
						articleMap.put(fileItem.getFieldName(), fileName); 
						// ���ε� ������ ��� ���� �� map�� ���ϸ� ����
						File uploadFile = new File(currentDirPath + "\\temp\\" + fileName);
						fileItem.write(uploadFile);

					} // end if
				} // end if
			} // end for
		} catch (Exception e) {
			e.printStackTrace();
		}
		return articleMap;
	}
	
}