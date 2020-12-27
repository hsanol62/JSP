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
	//글에 첨부한 이미지 저장위치를 상수로 변환
	BoardService boardService;
	ArticleVO articleVO;


	public void init(ServletConfig config) throws ServletException {
		boardService = new BoardService();
		 //서블릿 초기화시 BoardService 객체를 생성
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
		//요청명을 가져옴
		System.out.println("action:" + action);
		try {
			List<ArticleVO> articlesList = new ArrayList<ArticleVO>();
			//arrayList 생성
			if (action == null) {
				//처음 켰을때
				articlesList = boardService.listArticles();
				request.setAttribute("articlesList", articlesList);
				//boardService 에 있는 articlesList 불러온다.
				nextPage = "/board05/listArticles.jsp";
			} else if (action.equals("/listArticles.do")) {
				//listAricles.do 일때
				articlesList = boardService.listArticles();
				
				request.setAttribute("articlesList", articlesList);
				//BoardService의 articlesList를 불러와 저장
				nextPage = "/board05/listArticles.jsp";
				//연결
			} else if (action.equals("/articleForm.do")) {
				//action이  articleForm.do 일때
				nextPage = "/board05/articleForm.jsp";
				//연결 articleForm
			} else if (action.equals("/addArticle.do")) {
				int articleNO = 0;
				Map<String, String> articleMap = upload(request, response);
				//articleMap에 저장되어 있는 글 가져옴
				String title = articleMap.get("title");
				String content = articleMap.get("content");
				String imageFileName = articleMap.get("imageFileName");

				articleVO.setParentNO(0);
				articleVO.setId("hong");
				articleVO.setTitle(title);
				articleVO.setContent(content);
				articleVO.setImageFileName(imageFileName);
				articleNO = boardService.addArticle(articleVO);
				//boardService에 있는 addArticle(article값) ArticleVO 넘어와서  insertNeArticle(article) 
				//BoardDAO 에 있는 insertNewArticle  불러옴
				if (imageFileName != null && imageFileName.length() != 0) {
					//imageFileName이 NULL 이 아니거나 없을떄
					File srcFile = new File(ARTICLE_IMAGE_REPO + "\\" + "temp" + "\\" + imageFileName);
					//tmp폴더에 임시로 업로드된 파일 객체 생성
					File destDir = new File(ARTICLE_IMAGE_REPO + "\\" + articleNO);
					//해당 경로에 글 번호로 폴더를 생성한다.
					destDir.mkdirs();
					FileUtils.moveFileToDirectory(srcFile, destDir, true);
				}//tmp 폴더의 파일을 글 번호를 이름으로 하는 폴더로 이동
				PrintWriter pw = response.getWriter();
				pw.print("<script>" + "  alert('새글을 추가했습니다.');" + " location.href='" + request.getContextPath()
						+ "/board/listArticles.do';" + "</script>");
//알람
				return;
			} else if (action.equals("/viewArticle.do")) {
				String articleNO = request.getParameter("articleNO");
				//articleNO 값 불러옴
				articleVO = boardService.viewArticle(Integer.parseInt(articleNO));
				//boardService에 있는 viewArticle articleNO를 integer로 해서 불러옴
				//	article = boardDAO.selectArticle(articleNO);
				//boardDAO에 있는 selectArticle
				request.setAttribute("article", articleVO);
				nextPage = "/board05/viewArticle.jsp";
			} else if (action.equals("/modArticle.do")) {
				//modArticle.do일때
				Map<String, String> articleMap = upload(request, response);
			//글 불러옴
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
					//imageFileName이 NULL 이 아니거나, 없을떄
					String originalFileName = articleMap.get("originalFileName");
					//originalFileName:파일의 실제이름
					File srcFile = new File(ARTICLE_IMAGE_REPO + "\\" + "temp" + "\\" + imageFileName);
					File destDir = new File(ARTICLE_IMAGE_REPO + "\\" + articleNO);
					destDir.mkdirs();
					FileUtils.moveFileToDirectory(srcFile, destDir, true);
					;
					//try {
					    //File file = FileUtils.getFile("/home/mjs/test/text.txt");
					    //File fileToMove = FileUtils.getFile("/home/mjs/test/new_folder/renamed_text.txt");
					    //FileUtils.moveFile(file, fileToMove);
					//moveFile()는 인자로 전달되는 Path로 파일을 이동시킴 
					//이동하는 경로에 생성되지 않은 directory가 있다면 그 폴더를 자동으로 생성해 줍니다. 
					//위의 예제에서는 /home/mjs/test/new_folder 폴더가 존재하지 않을 때 이 폴더를 만들어주고 파일을 옮깁니다.
					//만약 존재하지 않는 폴더는 생성하고 싶지 않다면  moveFileToDirectory(src, des, createDestDir)을 사용
					File oldFile = new File(ARTICLE_IMAGE_REPO + "\\" + articleNO + "\\" + originalFileName);
					oldFile.delete();
					
				}
				PrintWriter pw = response.getWriter();
				pw.print("<script>" + "  alert('글을 수정했습니다.');" + " location.href='" + request.getContextPath()
						+ "/board/viewArticle.do?articleNO=" + articleNO + "';" + "</script>");
				//수정시 알림글
				return;
			} else if (action.equals("/removeArticle.do")) {
				//action이 removeArticle.do일때
				int articleNO = Integer.parseInt(request.getParameter("articleNO"));
				List<Integer> articleNOList = boardService.removeArticle(articleNO);
				for (int _articleNO : articleNOList) {
					File imgDir = new File(ARTICLE_IMAGE_REPO + "\\" + _articleNO);
					if (imgDir.exists()) {
						FileUtils.deleteDirectory(imgDir);
						//imgDir 삭제
					}
				}

				PrintWriter pw = response.getWriter();
				pw.print("<script>" + "  alert('글을 삭제했습니다.');" + " location.href='" + request.getContextPath()
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
		//hashmap 생성
		String encoding = "utf-8";
		File currentDirPath = new File(ARTICLE_IMAGE_REPO);
		//ARTICLE_IMAGE_REPO 넣을 File currentFirPath 생성
		//글 이미지 저장폴더에 대해 파일 객체를 생성
		DiskFileItemFactory factory = new DiskFileItemFactory();
		//메모리나 파일로 업로드 파일 보관하는 fatory 생성
		factory.setRepository(currentDirPath);
		factory.setSizeThreshold(1024 * 1024);
		ServletFileUpload upload = new ServletFileUpload(factory);
		//업로드 요청처리를 하는 ServletFileUpload 생성
		try {
			List items = upload.parseRequest(request);
			//업로드 요청 파싱해서 FileItem 목록 구함
			for (int i = 0; i < items.size(); i++) {
				FileItem fileItem = (FileItem) items.get(i);
				if (fileItem.isFormField()) {
					System.out.println(fileItem.getFieldName() + "=" + fileItem.getString(encoding));
					articleMap.put(fileItem.getFieldName(), fileItem.getString(encoding));
					//articleMap에 넣을
				} else {
					System.out.println("파라미터명:" + fileItem.getFieldName());
					//System.out.println("파일명:" + fileItem.getName());
					System.out.println("파일크기:" + fileItem.getSize() + "bytes");
					//articleMap.put(fileItem.getFieldName(), fileItem.getName());
					//   //업로드된 파일의 파일이름을 map에 (imagefilename,업로드 파일이름) 으로 저장
					if (fileItem.getSize() > 0) {
						int idx = fileItem.getName().lastIndexOf("\\");
						//윈도우 기반
						if (idx == -1) {
							//유닉스,리눅스 기반
							idx = fileItem.getName().lastIndexOf("/");
						}

						String fileName = fileItem.getName().substring(idx + 1);
						//순수 파일명 추출
						System.out.println("파일명:" + fileName);
						articleMap.put(fileItem.getFieldName(), fileName);
						//익스플로러에서 업로드 파일의 경로 제거 후 map에 파일명 저장
						File uploadFile = new File(currentDirPath + "\\temp\\" + fileName);
						//uploadFile에 경로명 파일명 저장
						fileItem.write(uploadFile);
						 //업로드한 파일이 존재하는 경우 업로드한 파일의 파일이름으로 저장소에 업로드 
					} // end if
				} // end if
			} // end for
		} catch (Exception e) {
			e.printStackTrace();
		}
		return articleMap;
	}

}