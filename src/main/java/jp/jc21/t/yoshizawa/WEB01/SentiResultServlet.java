package jp.jc21.t.yoshizawa.WEB01;

import java.io.IOException;
import java.net.URISyntaxException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Servlet implementation class SentiResultServlet
 */
@WebServlet("/R04JK3A05-Sentiment")
public class SentiResultServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public SentiResultServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		String string = "生姜焼定食";
		try {
			Senti result = Sentiment.getSentiment(string);
			String positive = result.documents[0].confidenceScores.positive;
			String neutral = result.documents[0].confidenceScores.neutral;
			String negative = result.documents[0].confidenceScores.negative;			
			request.setAttribute("message", positive);
			request.setAttribute("message", neutral);
			request.setAttribute("message", negative);
			request.getRequestDispatcher("/WEB-INF/jsp/sentiResult.jsp").forward(request, response);
		} catch (URISyntaxException e) {
			e.printStackTrace();
		} catch (InterruptedException e) {
			e.printStackTrace();
		}

	}
	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		request.setCharacterEncoding("UTF-8");
		String string = request.getParameter("string");
		request.setAttribute("string", string);

		try {
			Senti result = Sentiment.getSentiment(string);
			String positive = result.documents[0].confidenceScores.positive;
			String neutral = result.documents[0].confidenceScores.neutral;
			String negative = result.documents[0].confidenceScores.negative;			
			request.setAttribute("positive", positive);
			request.setAttribute("neutral", neutral);
			request.setAttribute("negative", negative);
			request.getRequestDispatcher("/WEB-INF/jsp/sentiResult.jsp")
				.forward(request, response);
		} catch (URISyntaxException e) {
			e.printStackTrace();
		} catch (InterruptedException e) {
			e.printStackTrace();
		}

	}

}