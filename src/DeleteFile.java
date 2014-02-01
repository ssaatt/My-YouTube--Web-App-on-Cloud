

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.amazonaws.auth.AWSCredentials;
import com.amazonaws.auth.PropertiesCredentials;
import com.amazonaws.services.s3.AmazonS3Client;
import com.hw2.db.RDS;

/**
 * Servlet implementation class DeleteFile
 */
public class DeleteFile extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private static RDS rds;   
    /**
     * @see HttpServlet#HttpServlet()
     */
    public DeleteFile() {
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
	protected void doPost(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		
		AWSCredentials credentials = new PropertiesCredentials(
				DeleteFile.class.getResourceAsStream("AwsCredentials.properties"));
		
		AmazonS3Client s3 = new AmazonS3Client(credentials);
		
		String videoName = (String) request.getParameter("videoName").replaceAll("_", " ");

		
		if (videoName != null) {
			s3.deleteObject("js4153", videoName);
			rds = new RDS();
			rds.deleteVideo(videoName);
		}
		getServletContext().getRequestDispatcher("/index.jsp").forward(
				request, response);
	}

}
