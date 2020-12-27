package sec03.brd06;

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

/*
@WebServlet("/board/*")*/
public class BoardController extends HttpServlet {
	private static String ARTICLE_IMAGE_REPO = "C:\\board\\article_image";
	//�ۿ� ÷���� �̹��� ������ġ�� ����� ��ȯ
	BoardService boardService;
	ArticleVO articleVO;


	public void init(ServletConfig config) throws ServletException {
		boardService = new BoardService();
		 //���� �ʱ�ȭ�� BoardService ��ü�� ����
		articleVO = new ArticleVO();
	}

	protected void doGet(HttpServletRequest request, HttpServletResponse response)	throws ServletException, IOException {
		doHandle(request, response);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response)	throws ServletException, IOException {
		doHandle(request, response);
	}

	private void doHandle(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String nextPage = "";
		request.setCharacterEncoding("utf-8");
		response.setContentType("text/html; charset=utf-8");
		String action = request.getPathInfo();
		//��û���� ������
		System.out.println("action:" + action);
		try {
			List<ArticleVO> articlesList = new ArrayList<ArticleVO>();
			//arrayList ����
			if (action == null) {
				//ó�� ������
				articlesList = boardService.listArticles();
				request.setAttribute("articlesList", articlesList);
				//boardService �� �ִ� articlesList �ҷ��´�.
				nextPage = "/board05/listArticles.jsp";
			} else if (action.equals("/listArticles.do")) {
				//listAricles.do �϶�
				articlesList = boardService.listArticles();
				
				request.setAttribute("articlesList", articlesList);
				//BoardService�� articlesList�� �ҷ��� ����
				nextPage = "/board05/listArticles.jsp";
				//����
			} else if (action.equals("/articleForm.do")) {
				//action��  articleForm.do �϶�
				nextPage = "/board05/articleForm.jsp";
				//���� articleForm
			} else if (action.equals("/addArticle.do")) {
				int articleNO = 0;
				Map<String, String> articleMap = upload(request, response);
				//articleMap�� ����Ǿ� �ִ� �� ������
				String title = articleMap.get("title");
				String content = articleMap.get("content");
				String imageFileName = articleMap.get("imageFileName");

				articleVO.setParentNO(0);
				articleVO.setId("hong");
				articleVO.setTitle(title);
				articleVO.setContent(content);
				articleVO.setImageFileName(imageFileName);
				articleNO = boardService.addArticle(articleVO);
				//boardService�� �ִ� addArticle(article��) ArticleVO �Ѿ�ͼ�  insertNeArticle(article) 
				//BoardDAO �� �ִ� insertNewArticle  �ҷ���
				if (imageFileName != null && imageFileName.length() != 0) {
					//imageFileName�� NULL �� �ƴϰų� ������
					File srcFile = new File(ARTICLE_IMAGE_REPO + "\\" + "temp" + "\\" + imageFileName);
					//tmp������ �ӽ÷� ���ε�� ���� ��ü ����
					File destDir = new File(ARTICLE_IMAGE_REPO + "\\" + articleNO);
					//�ش� ��ο� �� ��ȣ�� ������ �����Ѵ�.
					destDir.mkdirs();
					FileUtils.moveFileToDirectory(srcFile, destDir, true);
				}//tmp ������ ������ �� ��ȣ�� �̸����� �ϴ� ������ �̵�
				PrintWriter pw = response.getWriter();
				pw.print("<script>" + "  alert('������ �߰��߽��ϴ�.');" + " location.href='" + request.getContextPath()
						+ "/board/listArticles.do';" + "</script>");
//�˶�
				return;
			} else if (action.equals("/viewArticle.do")) {
				String articleNO = request.getParameter("articleNO");
				//articleNO �� �ҷ���
				articleVO = boardService.viewArticle(Integer.parseInt(articleNO));
				//boardService�� �ִ� viewArticle articleNO�� integer�� �ؼ� �ҷ���
				//	article = boardDAO.selectArticle(articleNO);
				//boardDAO�� �ִ� selectArticle
				request.setAttribute("article", articleVO);
				nextPage = "/board05/viewArticle.jsp";
			} else if (action.equals("/modArticle.do")) {
				//modArticle.do�϶�
				Map<String, String> articleMap = upload(request, response);
			//�� �ҷ���
				int articleNO = Integer.parseInt(articleMap.get("articleNO"));
				articleVO.setArticleNO(articleNO);
				String title = articleMap.get("title");
				String content = articleMap.get("content");
				String imageFileName = articleMap.get("imageFileName");
				articleVO.setParentNO(0);
				articleVO.setId("hong");
				articleVO.setTitle(title);
				articleVO.setContent(content);
				articleVO.setImageFileName(imageFileName);
				boardService.modArticle(articleVO);
				if (imageFileName != null && imageFileName.length() != 0) {
					//imageFileName�� NULL �� �ƴϰų�, ������
					String originalFileName = articleMap.get("originalFileName");
					//originalFileName:������ �����̸�
					File srcFile = new File(ARTICLE_IMAGE_REPO + "\\" + "temp" + "\\" + imageFileName);
					File destDir = new File(ARTICLE_IMAGE_REPO + "\\" + articleNO);
					destDir.mkdirs();
					FileUtils.moveFileToDirectory(srcFile, destDir, true);
					;
					//try {
					    //File file = FileUtils.getFile("/home/mjs/test/text.txt");
					    //File fileToMove = FileUtils.getFile("/home/mjs/test/new_folder/renamed_text.txt");
					    //FileUtils.moveFile(file, fileToMove);
					//moveFile()�� ���ڷ� ���޵Ǵ� Path�� ������ �̵���Ŵ 
					//�̵��ϴ� ��ο� �������� ���� directory�� �ִٸ� �� ������ �ڵ����� ������ �ݴϴ�. 
					//���� ���������� /home/mjs/test/new_folder ������ �������� ���� �� �� ������ ������ְ� ������ �ű�ϴ�.
					//���� �������� �ʴ� ������ �����ϰ� ���� �ʴٸ�  moveFileToDirectory(src, des, createDestDir)�� ���
					File oldFile = new File(ARTICLE_IMAGE_REPO + "\\" + articleNO + "\\" + originalFileName);
					oldFile.delete();
					
				}
				PrintWriter pw = response.getWriter();
				pw.print("<script>" + "  alert('���� �����߽��ϴ�.');" + " location.href='" + request.getContextPath()
						+ "/board/viewArticle.do?articleNO=" + articleNO + "';" + "</script>");
				//������ �˸���
				return;
			} else if (action.equals("/removeArticle.do")) {
				//action�� removeArticle.do�϶�
				int articleNO = Integer.parseInt(request.getParameter("articleNO"));
				List<Integer> articleNOList = boardService.removeArticle(articleNO);
				for (int _articleNO : articleNOList) {
					File imgDir = new File(ARTICLE_IMAGE_REPO + "\\" + _articleNO);
					if (imgDir.exists()) {
						FileUtils.deleteDirectory(imgDir);
						//imgDir ����
					}
				}

				PrintWriter pw = response.getWriter();
				pw.print("<script>" + "  alert('���� �����߽��ϴ�.');" + " location.href='" + request.getContextPath()
						+ "/board/listArticles.do';" + "</script>");
				return;
			}

			RequestDispatcher dispatch = request.getRequestDispatcher(nextPage);
			dispatch.forward(request, response);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	private Map<String, String> upload(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		Map<String, String> articleMap = new HashMap<String, String>();
		//hashmap ����
		String encoding = "utf-8";
		File currentDirPath = new File(ARTICLE_IMAGE_REPO);
		//ARTICLE_IMAGE_REPO ���� File currentFirPath ����
		//�� �̹��� ���������� ���� ���� ��ü�� ����
		DiskFileItemFactory factory = new DiskFileItemFactory();
		//�޸𸮳� ���Ϸ� ���ε� ���� �����ϴ� fatory ����
		factory.setRepository(currentDirPath);
		factory.setSizeThreshold(1024 * 1024);
		ServletFileUpload upload = new ServletFileUpload(factory);
		//���ε� ��ûó���� �ϴ� ServletFileUpload ����
		try {
			List items = upload.parseRequest(request);
			//���ε� ��û �Ľ��ؼ� FileItem ��� ����
			for (int i = 0; i < items.size(); i++) {
				FileItem fileItem = (FileItem) items.get(i);
				if (fileItem.isFormField()) {
					System.out.println(fileItem.getFieldName() + "=" + fileItem.getString(encoding));
					articleMap.put(fileItem.getFieldName(), fileItem.getString(encoding));
					//articleMap�� ����
				} else {
					System.out.println("�Ķ���͸�:" + fileItem.getFieldName());
					//System.out.println("���ϸ�:" + fileItem.getName());
					System.out.println("����ũ��:" + fileItem.getSize() + "bytes");
					//articleMap.put(fileItem.getFieldName(), fileItem.getName());
					//   //���ε�� ������ �����̸��� map�� (imagefilename,���ε� �����̸�) ���� ����
					if (fileItem.getSize() > 0) {
						int idx = fileItem.getName().lastIndexOf("\\");
						//������ ���
						if (idx == -1) {
							//���н�,������ ���
							idx = fileItem.getName().lastIndexOf("/");
						}

						String fileName = fileItem.getName().substring(idx + 1);
						//���� ���ϸ� ����
						System.out.println("���ϸ�:" + fileName);
						articleMap.put(fileItem.getFieldName(), fileName);
						//�ͽ��÷η����� ���ε� ������ ��� ���� �� map�� ���ϸ� ����
						File uploadFile = new File(currentDirPath + "\\temp\\" + fileName);
						//uploadFile�� ��θ� ���ϸ� ����
						fileItem.write(uploadFile);
						 //���ε��� ������ �����ϴ� ��� ���ε��� ������ �����̸����� ����ҿ� ���ε� 
					} // end if
				} // end if
			} // end for
		} catch (Exception e) {
			e.printStackTrace();
		}
		return articleMap;
	}

}