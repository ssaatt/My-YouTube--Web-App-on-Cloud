

import java.io.File;
import java.io.IOException;
import java.util.Iterator;
import java.util.List;
import java.util.Random;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.fileupload.FileItem;
import org.apache.commons.fileupload.disk.DiskFileItemFactory;
import org.apache.commons.fileupload.servlet.ServletFileUpload;

import com.amazonaws.auth.AWSCredentials;
import com.amazonaws.auth.PropertiesCredentials;
import com.amazonaws.services.s3.AmazonS3Client;
import com.amazonaws.services.s3.model.CannedAccessControlList;
import com.amazonaws.services.s3.model.PutObjectRequest;
import com.hw2.db.RDS;

/**
 * Servlet implementation class FileUpLoad
 */
public class FileUpLoad extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private static final String UPD = "uploadDir";
	RDS rds;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public FileUpLoad() {
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
		boolean isMulti = ServletFileUpload.isMultipartContent(request);
		rds = new RDS();

		if (isMulti) {

			DiskFileItemFactory dfi = new DiskFileItemFactory();
			AWSCredentials credentials = new PropertiesCredentials(FileUpLoad.class.getResourceAsStream("AwsCredentials.properties"));

			AmazonS3Client s3 = new AmazonS3Client(credentials);

			dfi.setRepository(new File(System.getProperty("java.io.tmpdir")));

			ServletFileUpload sfu = new ServletFileUpload(dfi);

			String uploadPath = getServletContext().getRealPath("") + File.separator + UPD;
			System.out.println("uploadpath is: "+ uploadPath);
			
			File upd = new File(uploadPath);
			if (!upd.exists()) {	
				upd.mkdir();
			}

			try {
				List fil = sfu.parseRequest(request);
				Iterator i = fil.iterator();

				while (i.hasNext()) {
					FileItem fileItem = (FileItem) i.next();
					if (!fileItem.isFormField()) {
						String fileName = new File(fileItem.getName()).getName();
						if ((!fileName.contains(".mp4")) && (!fileName.contains(".flv")) ) {
							request.setAttribute("message", "Only mp4 or flv files can be uploaded!!");
							throw new Exception("Only .mp4 or .flv files could be uploaded!");

						} else {
							String filePath = uploadPath + File.separator
									+ fileName;
							File storeFile = new File(filePath);
							
							fileItem.write(storeFile);
                  
							PutObjectRequest putObjectRequest = new PutObjectRequest(
									"js4153", fileName, storeFile);
                            
							CannedAccessControlList accessControlList = CannedAccessControlList.PublicReadWrite;		
							putObjectRequest.setCannedAcl(accessControlList);
							s3.putObject(putObjectRequest);

							rds.setVideo(fileName, 0);
						}
					}
				}
				request.setAttribute("message", "Cong!! Your File Has Been Uploaded!");
			} catch (Exception ex) {
				request.setAttribute("message", "There is an Error! "
						+ ex.getMessage());
			}

			getServletContext().getRequestDispatcher("/index.jsp").forward(
					request, response);
		}
	}

}
