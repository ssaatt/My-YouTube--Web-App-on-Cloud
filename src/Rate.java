

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.hw2.db.RDS;


/**
 * Servlet implementation class Rate
 */
public class Rate extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private static RDS rds;
    /**
     * @see HttpServlet#HttpServlet()
     */
    public Rate() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		rds = new RDS();
		String videoName = (String) request.getParameter("rateVideoName")
				.replaceAll("_", " ");
		int rating = 0;
		if (videoName != null) {
			if (request.getParameter("rating") != null) {
				rating = Integer.parseInt(request.getParameter("rating"));
			}
			rds.rateVideo(videoName, rating);

		}
		getServletContext().getRequestDispatcher("/index.jsp").forward(
				request, response);
	}

}
