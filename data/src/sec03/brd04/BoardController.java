package sec03.brd04;


import java.io.File;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.RequestDispatcher;
import javax.servlet.Servlet;
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

/*@WebServlet("/board/*")*/
//Servlet�� ���ε� URL ������ ����
//Annotation�� ���� web.xml ���Ͽ� ������ ������ ���� �ʴ��� �ش� Servlet�� ����
public class BoardController extends HttpServlet {
	//��û�� �� ��� ����� ����
	private static String ARTICLE_IMAGE_REPO = "C:\\board\\article_image";
	//�ۿ� ÷���� �̹��� ���� ��ġ�� ����� ��ȯ
	BoardService boardService;
	ArticleVO articleVO;

	public void init(ServletConfig config) throws ServletException {
		//���� �����̳ʴ� ���� ��ü�� ������ ��, �� �ѹ� init() �޼ҵ带 ȣ��
		//�ʱ�ȭ �Ķ���Ͱ� �ִٸ� init() �޼ҵ忡 ������ �ʱ�ȭ �۾��� �����ϴ� �ڵ尡 �־�� �Ѵ�.
		boardService = new BoardService();
		//���� �ʱ�ȭ�� BoardService ��ü�� ����
		articleVO = new ArticleVO();
	}

	//doGet doPost�� doHandle ȣ���Ѵ�.
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doHandle(request, response);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response)	throws ServletException, IOException {
		doHandle(request, response);
	}

	private void doHandle(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String nextPage = "";
		//���ڿ� nextPage ����
		request.setCharacterEncoding("utf-8");
		response.setContentType("text/html; charset=utf-8");
		String action = request.getPathInfo();
		//action�� ��θ� ������
		System.out.println("action:" + action);
		try {
			List<ArticleVO> articlesList = new ArrayList<ArticleVO>();
			//ArrayList ����
			//ArticleVO�ڷḸ �ҷ���
			if (action == null) {
				//action�� null�ϋ�
				//������ ��� �� null = ó�� ���� ��������
				articlesList = boardService.listArticles();
				//��ü �� ��ȸ
				request.setAttribute("articlesList", articlesList);
				// ��ȸ�� ���� ���ε��� �� jsp�� ������
				nextPage = "/board03/listArticles.jsp";
				//listArticles���� (�۸��)
			} else if (action.equals("/listArticles.do")) {
				//action�� listArticles.do�϶�
				articlesList = boardService.listArticles();
				request.setAttribute("articlesList", articlesList);
				nextPage = "/board03/listArticles.jsp";
				//board03/listArticles.jsp ���� (�۸��)
			} else if (action.equals("/articleForm.do")) {
				//action�� articleForm.do �ϋ�
				nextPage = "/board03/articleForm.jsp";
				//board03/articleForm ����
			} else if (action.equals("/addArticle.do")) {
				//action�� addArticle.do�ϋ�
				int articleNO=0;
				Map<String, String> articleMap = upload(request, response);
				String title = articleMap.get("title");
				String content = articleMap.get("content");
				String imageFileName = articleMap.get("imageFileName");
				 //articleMap�� ����� �� ������ �ٽ� ������
				articleVO.setParentNO(0);
				articleVO.setId("hong");
				//
				articleVO.setTitle(title);
				articleVO.setContent(content);
				articleVO.setImageFileName(imageFileName);
				articleNO= boardService.addArticle(articleVO);
				if(imageFileName!=null && imageFileName.length()!=0) {
				    File srcFile = new 	File(ARTICLE_IMAGE_REPO +"\\"+"temp"+"\\"+imageFileName);
					File destDir = new File(ARTICLE_IMAGE_REPO +"\\"+articleNO);
					destDir.mkdirs();
					FileUtils.moveFileToDirectory(srcFile, destDir, true);
					 //������ �̹��� ������ ������ �̵�
					srcFile.delete();
				}
				PrintWriter pw = response.getWriter();
				pw.print("<script>" 
				         +"  alert('������ �߰��߽��ϴ�.');" 
						 +" location.href='"+request.getContextPath()+"/board/listArticles.do';"
				         +"</script>");
//���� ��� �޼��� ��Ÿ�� �� location��ü�� href �� ��� ��û
				return;
			}else if(action.equals("/viewArticle.do")){
				//action�� viewArticle.do �ϋ�
				String articleNO = request.getParameter("articleNO");
				//�̹��� ���� �̸��� �� ��ȣ�� ������
				articleVO=boardService.viewArticle(Integer.parseInt(articleNO));
				request.setAttribute("article",articleVO);
				//articleVO
				nextPage = "/board03/viewArticle.jsp";
				//����
			}


			RequestDispatcher dispatch = request.getRequestDispatcher(nextPage);
			dispatch.forward(request, response);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	private Map<String, String> upload(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		Map<String, String> articleMap = new HashMap<String, String>();
		//hashMap ����
		String encoding = "utf-8";
		File currentDirPath = new File(ARTICLE_IMAGE_REPO);
		DiskFileItemFactory factory = new DiskFileItemFactory();
		factory.setRepository(currentDirPath);
		factory.setSizeThreshold(1024 * 1024);
		ServletFileUpload upload = new ServletFileUpload(factory);
		try {
			List items = upload.parseRequest(request);
			for (int i = 0; i < items.size(); i++) {
				FileItem fileItem = (FileItem) items.get(i);
				if (fileItem.isFormField()) {
					System.out.println(fileItem.getFieldName() + "=" + fileItem.getString(encoding));
					articleMap.put(fileItem.getFieldName(), fileItem.getString(encoding));
				} else {
					System.out.println("�Ķ���͸�:" + fileItem.getFieldName());
					//System.out.println("���ϸ�:" + fileItem.getName());
					System.out.println("����ũ��:" + fileItem.getSize() + "bytes");
					//articleMap.put(fileItem.getFieldName(), fileItem.getName());
					if (fileItem.getSize() > 0) {
						int idx = fileItem.getName().lastIndexOf("\\");
						if (idx == -1) {
							idx = fileItem.getName().lastIndexOf("/");
						}

						String fileName = fileItem.getName().substring(idx + 1);
						System.out.println("���ϸ�:" + fileName);
						articleMap.put(fileItem.getFieldName(), fileName); 
						//�ͽ��÷η����� ���ε� ������ ��� ���� �� map�� ���ϸ� ����
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