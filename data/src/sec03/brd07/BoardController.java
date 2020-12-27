package sec03.brd07;

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
import javax.servlet.http.HttpSession;

import org.apache.commons.fileupload.FileItem;
import org.apache.commons.fileupload.disk.DiskFileItemFactory;
import org.apache.commons.fileupload.servlet.ServletFileUpload;
import org.apache.commons.io.FileUtils;

/**
 * Servlet implementation class BoardController
 */
@WebServlet("/board/*")
public class BoardController extends HttpServlet {
	private static String ARTICLE_IMAGE_REPO = "C:\\board\\article_image";
	//글에 첨부한 이미지 저장위치를 상수로 변환
	BoardService boardService;
	ArticleVO articleVO;

	/**
	 * @see Servlet#init(ServletConfig)
	 */
	public void init(ServletConfig config) throws ServletException {
		boardService = new BoardService();
		 //서블릿 초기화시 BoardService 객체를 생성
		articleVO = new ArticleVO();
	}

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doHandle(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doHandle(request, response);
	}

	private void doHandle(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String nextPage = "";
		request.setCharacterEncoding("utf-8");
		response.setContentType("text/html; charset=utf-8");
		HttpSession session;
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
				nextPage = "/board06/listArticles.jsp";
			} else if (action.equals("/listArticles.do")) {
			    //listAricles.do 일때
				articlesList = boardService.listArticles();
				request.setAttribute("articlesList", articlesList);
				//BoardService의 articlesList를 불러와 저장
				nextPage = "/board06/listArticles.jsp";
			} else if (action.equals("/articleForm.do")) {
				 //action이  articleForm.do 일때

				nextPage = "/board06/articleForm.jsp";
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
				}
				//tmp 폴더의 파일을 글 번호를 이름으로 하는 폴더로 이동
				PrintWriter pw = response.getWriter();
				pw.print("<script>" + "  alert('새글을 추가했습니다.');" + " location.href='" + request.getContextPath()
						+ "/board/listArticles.do';" + "</script>");
				//알림창 
				return;
			} else if (action.equals("/viewArticle.do")) {
				String articleNO = request.getParameter("articleNO");
				//articleNO 값 불러옴
				articleVO = boardService.viewArticle(Integer.parseInt(articleNO));
				//boardService에 있는 viewArticle articleNO를 integer로 해서 불러옴
				//	article = boardDAO.selectArticle(articleNO);
				//boardDAO에 있는 selectArticle
				request.setAttribute("article", articleVO);
				nextPage = "/board06/viewArticle.jsp";
			} else if (action.equals("/modArticle.do")) {
				//action이 modArticle.do일때
				Map<String, String> articleMap = upload(request, response);
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
					String originalFileName = articleMap.get("originalFileName");
					File srcFile = new File(ARTICLE_IMAGE_REPO + "\\" + "temp" + "\\" + imageFileName);
					File destDir = new File(ARTICLE_IMAGE_REPO + "\\" + articleNO);
					destDir.mkdirs();
					FileUtils.moveFileToDirectory(srcFile, destDir, true);
					;
					File oldFile = new File(ARTICLE_IMAGE_REPO + "\\" + articleNO + "\\" + originalFileName);
					oldFile.delete();
				}
				PrintWriter pw = response.getWriter();
				pw.print("<script>" + "  alert('글을 수정했습니다.');" + " location.href='" + request.getContextPath()
						+ "/board/viewArticle.do?articleNO=" + articleNO + "';" + "</script>");
				return;
			} else if (action.equals("/removeArticle.do")) {
				int articleNO = Integer.parseInt(request.getParameter("articleNO"));
				List<Integer> articleNOList = boardService.removeArticle(articleNO);
				for (int _articleNO : articleNOList) {
					File imgDir = new File(ARTICLE_IMAGE_REPO + "\\" + _articleNO);
					if (imgDir.exists()) {
						FileUtils.deleteDirectory(imgDir);
					}
				}

				PrintWriter pw = response.getWriter();
				pw.print("<script>" + "  alert('글을 삭제했습니다.');" + " location.href='" + request.getContextPath()
						+ "/board/listArticles.do';" + "</script>");
				return;

			} else if (action.equals("/replyForm.do")) {
				//replyForm.do 일때
				int parentNO = Integer.parseInt(request.getParameter("parentNO"));
				//parent 
				//request.getParameter
				//클라이언트 측에서 서버로 데이터를 요청 할때 전달되는 값들을 담은 변수를 요청 파라미터
				//요청 파라미터는 url 주소 뒤에 붙은 후에 [파라미터변수명=값]의 형식을 통해 서버로 데이터 전송
				session = request.getSession();
				session.setAttribute("parentNO", parentNO);
				// session.setAttribute(이름, 값) 세션 값 저장
				//setAttribute는 name, value 쌍으로 객체 
			//	Object를 저장하는 메서드다. 세션이 유지되는 동안 저장
				nextPage = "/board06/replyForm.jsp";
				//replyForm 연결
			} else if (action.equals("/addReply.do")) {
				session = request.getSession();
				//현재세션을 반환한다 세션이 없으면 새로운 세션 생성된다.
				int parentNO = (Integer) session.getAttribute("parentNO");
			//	getAttribute 메서드로 세션에 저장된 값을 조회할 수 있다.
				//리턴 타입은 Object이므로 형변환이 필요하다.
				//메서드 setAttribute에 이용한 name(parentNO)을 알고 있으면 다음과 같이 조회할 수 있다.
				session.removeAttribute("parentNO");
				//removeAttribute 메서드로 name 값에 해당하는 세션 정보를 삭제
				Map<String, String> articleMap = upload(request, response);
				//글 불러옴
				
				String title = articleMap.get("title");
				String content = articleMap.get("content");
				String imageFileName = articleMap.get("imageFileName");
				articleVO.setParentNO(parentNO);
				articleVO.setId("hong");
				articleVO.setTitle(title);
				articleVO.setContent(content);
				articleVO.setImageFileName(imageFileName);
				int articleNO = boardService.addReply(articleVO);
				if (imageFileName != null && imageFileName.length() != 0) {
					//imageFileName이 NULL 이 아니거나, 없을떄
					File srcFile = new File(ARTICLE_IMAGE_REPO + "\\" + "temp" + "\\" + imageFileName);
					File destDir = new File(ARTICLE_IMAGE_REPO + "\\" + articleNO);
					destDir.mkdirs();
					FileUtils.moveFileToDirectory(srcFile, destDir, true);
				}
				//moveFile()는 인자로 전달되는 Path로 파일을 이동시킴 
				PrintWriter pw = response.getWriter();
				pw.print("<script>" + "  alert('답글을 추가했습니다.');" + " location.href='" + request.getContextPath()
						+ "/board/viewArticle.do?articleNO="+articleNO+"';" + "</script>");
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
		//hashmap 생성
		String encoding = "utf-8";
		File currentDirPath = new File(ARTICLE_IMAGE_REPO);
		//ARTICLE_IMAGE_REPO 넣을 File currentDirPath 생성
				//글 이미지 저장폴더에 대해 파일 객체를 생성
		DiskFileItemFactory factory = new DiskFileItemFactory();
		//메모리나 파일로 업로드 파일 보관하는 fatory 생성
		factory.setRepository(currentDirPath);
		//경로
		factory.setSizeThreshold(1024 * 1024);
		//파일크기
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
				} else {
					System.out.println("파라미터명:" + fileItem.getFieldName());
					//System.out.println("파일명:" + fileItem.getName());
					System.out.println("파일크기:" + fileItem.getSize() + "bytes");
					//articleMap.put(fileItem.getFieldName(), fileItem.getName());
					if (fileItem.getSize() > 0) {
						int idx = fileItem.getName().lastIndexOf("\\");
						if (idx == -1) {
							idx = fileItem.getName().lastIndexOf("/");
						}

						String fileName = fileItem.getName().substring(idx + 1);
						System.out.println("파일명:" + fileName);
						articleMap.put(fileItem.getFieldName(), fileName);  //익스플로러에서 업로드 파일의 경로 제거 후 map에 파일명 저장
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