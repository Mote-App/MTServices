package rain;

import javax.servlet.*;
import javax.servlet.http.*;

import java.io.*;
import java.util.*;

//
//
//
//


/**
 *  This servlet handles a customer's shopping cart.
 *
 *  <p>The servlet accesses a user's shopping cart by retrieving the session value named <b>the_cart</b>
 *
 *
 *  <p>The servlet supports the following parameters:
 *	<ul>
 *  	<li><b>command</b> - Command to "add" an item or "view" the cart's contents.  See usage examples below
 *  	<li><b>id</b> - The product id...used when adding products
 *	</ul>
 *
 *
 *  <p> Usage examples.  If you want to link to the servlet from a web page:
 *
 *	<pre>
 *
 *		//
 *		// link to "add" an item to the cart
 *		//
 *		"<-a href=/servlet/rain.ShoppingCartServlet?command=add&id=" + tempProduct.getId() + ">"
 *
 *
 *		//
 *		// link to "view" the contents of the cart
 *		//
 *		<-a HREF="/servlet/rain.ShoppingCartServlet?command=view">
 *
 *	</pre>
 *
 *  @author 517 Development Team
 */
public class ShoppingCartServlet extends HttpServlet {

	/**
	 *  Reference to the video data accessor
	 */
	private RainForestService rainforestService;


	/**
	 *  Retrieves an instance of the service layer object
	 *
	 *  @throws ServletException error occurred retrieving service layer object
	 */
	public void init() throws ServletException {

		// Retrieve the RainForestService from the servlet context
		// The RainForestService was initialized by the listener class: RainAppListener
		ServletContext application = getServletContext();
		rainforestService  = (RainForestService) application.getAttribute(Constants.RAINFOREST_SERVICE_KEY);
	}



	/**
	 *  Handles requests for the shopping cart.
	 *
	 *  <p> Basic steps
	 *
	 *  <ol>
	 *		<li>  Get the user's session
	 *
	 *		<li>  Find out the command by accessing the parameter <b>command</b>
	 *
     *      <li>  If command equals "add" then add the product to cart, show the cart's contents
	 *
     *      <li>  If command equals "view" then show the cart's contents
	 *  </ol>
	 *
	 */
    public void doGet (HttpServletRequest request, HttpServletResponse response)
	  throws ServletException, IOException {


		//	Get the user's session
		HttpSession session = request.getSession();

		//	Retrieve the shopping cart from the session.
		ShoppingCart theCart = (ShoppingCart) session.getAttribute(Constants.LTREE_CART_KEY);

		// normal HTML header work
		response.setContentType("text/html");
		PrintWriter out = response.getWriter();
		out.println("<html>");
		out.println("<head><title>Shopping Cart Contents</title></head>");
		out.println("<body>");

			//	Get the request parameter named "command"
		String command = request.getParameter("command");

		if (command.equals("add")) {
			String productType = request.getParameter("product_type");
			String productId = request.getParameter("id");

			Product theProduct = null;
			if (productType.equalsIgnoreCase("video")) {
				theProduct = rainforestService.getVideoRecording(productId);
			}
			theCart.add(theProduct);
			showCart(theCart, out, response);
		}
		else if (command.equals("view")) {
			showCart(theCart, out, response);
		}
		else if (command.equals("remove")) {
			String productId = request.getParameter("id");
			theCart.remove(productId);
			showCart(theCart, out, response);
		}
		out.println("</body></html>");
    }


	/**
	 *  Shows the contents of the shopping cart in a table.
	 *
	 *  <pre>
	 *
	 *    +---------------------------------------+
	 *    |   Product   |   Price   |   Quantity  |
	 *    =========================================
	 *    |     ...     |    ...    |     ...     |
	 *    |----------------------------------------
	 *    |     ...     |    ...    |     ...     |
	 *    |----------------------------------------
	 *    |   Total     |           |             |
	 *    +---------------------------------------+
	 *
	 *  </pre>
     *
	 *  Basic steps
	 *
	 *  <ol>
	 *		<li>  Get a list of items from the shopping cart
	 *
	 *		<li>  Iterate thru the list and display the product name, price and quantity
	 *
	 *		<li>  Display the shoppping cart's total price and the number of items
	 *  </ol>
	 *
	 */
	public void showCart(ShoppingCart userCart, PrintWriter out, HttpServletResponse response) {

		if ((userCart == null) || userCart.isEmpty()) {
			out.println("<h4><i>Your cart is empty</i></h4>");
			return;
		}

		out.println("<h4>Shopping Cart Contents</h4>");

		//	table w/ the columns "Product", "Price", "Quantity"
		out.println("<table border=1 cellpadding=5>");

		out.println("<tr>");

			out.println("<th>Product</th>");
			out.println("<th>Price</th>");
			out.println("<th>Quantity</th>");
			out.println("<th>Modify</th>");

		out.println("</tr>");


		//	Get the shopping cart items from the shopping cart
		Collection<ShoppingCartItem> items = userCart.getItems();

		//	Loop thru the list
		//		-  Get a product from the ShoppingCartItem
		//
		//		-  Display the following in a table row
		//			-  product name
		//			-  product price
		//			-  (ShoppingCartItem) quantity
		//
		String removeBaseLink = "ShoppingCartServlet?command=remove&id=";
		String addBaseLink = "ShoppingCartServlet?command=add&id=";

		String videoDetailsLink = "VideoDetailsServlet?id=";

		String productIcon = null;
		String productLink = null;
		String type = null;

		Product tempProduct = null;

		for (ShoppingCartItem tempItem : items) {
			tempProduct = tempItem.getProduct();

			// figure out which icon we need to use, Video or Music
			// also add "details link" for the products
			//
			if (tempProduct instanceof VideoRecording) {
				productIcon = " <img src=images/video-icon.gif alt=Video> ";
				productLink = productIcon + "<a href=" + videoDetailsLink + tempProduct.getId() + ">" + tempProduct.getName() + "</a>";
			}

			out.println("<tr>");
				out.println("<td>" + productLink + "</td>");
				out.println("<td>$" + tempProduct.getPrice() + " USD</td>");
				out.println("<td align=center>" + tempItem.getQuantity() + "</td>");

				out.println("<td>");

					//
					//  set up "modify" links for "Add" and "Remove"
					//
					if (tempProduct instanceof VideoRecording) {
						type = "video";

						out.println("<a href=");
						out.println(response.encodeURL(addBaseLink + tempProduct.getId()) + "&product_type=" + type);
						out.println(">Add</a>");
						out.println(" -- ");
					}

					out.println("<a href=");
					out.println(response.encodeURL(removeBaseLink + tempProduct.getId()));
					out.println(">Remove</a>");
				out.println("</td>");

			out.println("</tr>");
		}

		//  shows a table row with totals for: price, items
		//  display a table row with totals for: price, items
		displayTotals(userCart, out);

		out.println("</table>");
	}

	protected void displayTotals(ShoppingCart userCart, PrintWriter out) {

		out.println("<tr>");

			double price = userCart.getTotalPrice();
			java.text.NumberFormat currencyFormatter = java.text.NumberFormat.getCurrencyInstance(java.util.Locale.US);
			out.println("<td><b>TOTAL</b></td>");
			out.println("<td>" +  currencyFormatter.format(price) + " USD</td>");
			out.println("<td align=center>" +  userCart.getNumberOfProducts() + "</td>");

		out.println("</tr>");
	}

	/**
	 *  Forwards the request to doGet...
	 */
	public void doPost(HttpServletRequest request, HttpServletResponse response)
		throws ServletException, IOException {

		doGet(request, response);
	}


}
