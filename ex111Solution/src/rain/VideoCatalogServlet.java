package rain;

import java.io.*;

import javax.servlet.*;
import javax.servlet.http.*;

import java.util.*;

import java.text.*;
/**
 *  This servlet allows the user to browse the video catalog.  The servlet reads
 *  parameters and displays a table of the products.
 *
 *  <p> The servlet expects the following parameter:
 *
 *  <ul>
 *    <li>  video_category:  Describes the category to search
 *  </ul>
 *
 *  @author 517 Development Team
 *
 */
public class VideoCatalogServlet extends HttpServlet {

	/**
	 *  Reference to the video data accessor
	 */
	private RainForestService rainforestService;
	private DecimalFormat decimalFormat;
	
	public void init() throws ServletException {

		// Retrieve the service object from the servlet context
		// The RainForestService was initialized by the listener class: RainAppListener
		ServletContext application = getServletContext();
		rainforestService  = (RainForestService) application.getAttribute(Constants.RAINFOREST_SERVICE_KEY);

		decimalFormat = new DecimalFormat("0.00");
	}

	/**
	 *  This method reads the form parameter and
	 *  displays a list of recordings matching the search.
	 */
    public void doGet (HttpServletRequest request, HttpServletResponse response)
	  throws ServletException, IOException {

		response.setContentType("text/html");

		//  Retrieve the selected video category from the HTML form

		String category = request.getParameter("video_category");

		// 	Get a list of recordings from the VideoDAO
		List<VideoRecording> theList = null;

		String sortByString = request.getParameter("sort_by");

		
		
		if (sortByString == null) {
			theList = rainforestService.getVideoRecordings(category);
		}
		else {
			try{
			  int sortByFlag = Integer.parseInt(sortByString);
			  theList = rainforestService.getVideoRecordings(category, sortByFlag);
		    } catch (NumberFormatException exc) {
					// non-numeric sort flag...
					log(exc.getMessage());
					exc.printStackTrace();

					// just give them the basic list
					theList = rainforestService.getVideoRecordings(category);
			}
		}
		PrintWriter out = response.getWriter();

		out.println("<h3>Category: " + category + "</h3>");

			//	Build an HTML table for the list of products.
		buildHtmlTable(theList, out, category, response);

    }

	protected void buildHtmlTable(List<VideoRecording> theList, PrintWriter out, String category, HttpServletResponse response) {

		// URL-encode the base link to take care of categories w/ spaces and special characters
		String baseLink = "VideoCatalogServlet?video_category=" + encode(category);

		String titleLink = "<a href=" + response.encodeURL(baseLink + "&sort_by=" + VideoDao.SORT_BY_TITLE) + ">Title</a>";
		String priceLink = "<a href=" + response.encodeURL(baseLink + "&sort_by=" + VideoDao.SORT_BY_PRICE) + ">Price</a>";
		String stockCountLink = "<a href=" + response.encodeURL(baseLink + "&sort_by=" + VideoDao.SORT_BY_STOCK_COUNT) + ">Stock Count</a>";

		out.println("<table border=1 cellpadding=5>");

		out.println("<tr>");
			out.println("<th>" + titleLink + "</th>");
			out.println("<th>" + priceLink + "</th>");
			out.println("<th>" + stockCountLink + "</th>");
			out.println("<th>Cart</th>");
		out.println("</tr>");

		String detailsLink;

		for (VideoRecording tempRecording : theList) {

			detailsLink = "<a href=" + response.encodeURL("VideoDetailsServlet?id=" + tempRecording.getId()) + ">" + tempRecording.getTitle() + "</a>";
			out.println("<tr>");
			out.println("<td>" + detailsLink + "</td>");
			out.println("<td>" + decimalFormat.format(tempRecording.getPrice()) + "</td>");
			out.println("<td>" + tempRecording.getStockCount() + "</td>");

			out.print("<td>");
			if (tempRecording.isInStock()) {
				out.print("<a href=" + response.encodeURL("ShoppingCartServlet?command=add&product_type=video&id=" + tempRecording.getId()) + ">");
					out.print("<img border=0 src=\"images/add_to_cart_button.gif\" alt=\"" + tempRecording.getTitle() +"\">");
				out.println("</a>");
			}
			else {
				out.print("Out Of Stock");
			}
			out.println("</td>");

			out.println("</tr>");
		}

		out.println("</table>");

	}


	/**
	 * Simple helper method to encode the String using UTF-8 encoding. <b>
	 * JDK 1.4 deprecated the method java.net.URLEncoder.encode(String). Now requires
	 * you to explicitly provide the encoding type.
	 */
	private String encode(String theCategory) {

		String result = null;
		try {
			result = java.net.URLEncoder.encode(theCategory, "UTF-8");
		}
		catch (java.io.UnsupportedEncodingException exc) {
			exc.printStackTrace();
		}
		return result;
	}


	/**
	 *  Forwards requests to the doGet(...) method
	 */
	public void doPost(HttpServletRequest request, HttpServletResponse response)
		throws ServletException, IOException {

		doGet(request, response);
	}


}
