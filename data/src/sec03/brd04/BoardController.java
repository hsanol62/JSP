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
//Servlet과 매핑될 URL 패턴을 지정
//Annotation을 통해 web.xml 파일에 별로의 설정을 하지 않더라도 해당 Servlet을 실행
public class BoardController extends HttpServlet {
	//요청시 글 목록 출력의 역할
	private static String ARTICLE_IMAGE_REPO = "C:\\board\\article_image";
	//글에 첨부한 이미지 저장 위치를 상수로 변환
	BoardService boardService;
	ArticleVO articleVO;

	public void init(ServletConfig config) throws ServletException {
		//서블릿 컨테이너는 서블릿 객체가 생성된 후, 단 한번 init() 메소드를 호출
		//초기화 파라미터가 있다면 init() 메소드에 서블릿의 초기화 작업을 수행하는 코드가 있어야 한다.
		boardService = new BoardService();
		//서블릿 초기화시 BoardService 객체를 생성
		articleVO = new ArticleVO();
	}

	//doGet doPost는 doHandle 호출한다.
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doHandle(request, response);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response)	throws ServletException, IOException {
		doHandle(request, response);
	}

	private void doHandle(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String nextPage = "";
		//문자열 nextPage 선언
		request.setCharacterEncoding("utf-8");
		response.setContentType("text/html; charset=utf-8");
		String action = request.getPathInfo();
		//action의 경로를 가져옴
		System.out.println("action:" + action);
		try {
			List<ArticleVO> articlesList = new ArrayList<ArticleVO>();
			//ArrayList 생성
			//ArticleVO자료만 불러옴
			if (action == null) {
				//action이 null일떄
				//마지막 경로 가 null = 처음 실행 시켰을떄
				articlesList = boardService.listArticles();
				//전체 글 조회
				request.setAttribute("articlesList", articlesList);
				// 조회한 글을 바인딩한 후 jsp로 포워딩
				nextPage = "/board03/listArticles.jsp";
				//listArticles연결 (글목록)
			} else if (action.equals("/listArticles.do")) {
				//action이 listArticles.do일때
				articlesList = boardService.listArticles();
				request.setAttribute("articlesList", articlesList);
				nextPage = "/board03/listArticles.jsp";
				//board03/listArticles.jsp 연결 (글목록)
			} else if (action.equals("/articleForm.do")) {
				//action이 articleForm.do 일떄
				nextPage = "/board03/articleForm.jsp";
				//board03/articleForm 연결
			} else if (action.equals("/addArticle.do")) {
				//action이 addArticle.do일떄
				int articleNO=0;
				Map<String, String> articleMap = upload(request, response);
				String title = articleMap.get("title");
				String content = articleMap.get("content");
				String imageFileName = articleMap.get("imageFileName");
				 //articleMap에 저장된 글 정보를 다시 가져옴
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
					 //수정된 이미지 파일을 폴더로 이동
					srcFile.delete();
				}
				PrintWriter pw = response.getWriter();
				pw.print("<script>" 
				         +"  alert('새글을 추가했습니다.');" 
						 +" location.href='"+request.getContextPath()+"/board/listArticles.do';"
				         +"</script>");
//새글 등록 메세지 나타낸 후 location객체의 href 글 목록 요청
				return;
			}else if(action.equals("/viewArticle.do")){
				//action이 viewArticle.do 일떄
				String articleNO = request.getParameter("articleNO");
				//이미지 파일 이름과 글 번호를 가져옴
				articleVO=boardService.viewArticle(Integer.parseInt(articleNO));
				request.setAttribute("article",articleVO);
				//articleVO
				nextPage = "/board03/viewArticle.jsp";
				//연결
			}


			RequestDispatcher dispatch = request.getRequestDispatcher(nextPage);
			dispatch.forward(request, response);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	private Map<String, String> upload(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		Map<String, String> articleMap = new HashMap<String, String>();
		//hashMap 생성
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
						articleMap.put(fileItem.getFieldName(), fileName); 
						//익스플로러에서 업로드 파일의 경로 제거 후 map에 파일명 저장
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