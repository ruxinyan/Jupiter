package rpc;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;
import java.util.Set;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import external.TicketMasterAPI;
import entity.Item;
import db.DBConnection;
import db.DBConnectionFactory;
/**
 * Servlet implementation class SearchItem
 */
@WebServlet("/search")//localhost: 8080/Jupiter/search
public class SearchItem extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public SearchItem() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		//Create a PrintWriter from response such that we can add data to response.
		String userId = request.getParameter("user_id");

		double lat = Double.parseDouble(request.getParameter("lat"));
		double lon = Double.parseDouble(request.getParameter("lon"));
		// term can be empty or null.
		String term = request.getParameter("term");
		
		DBConnection conn = DBConnectionFactory.getDBConnection();
		Set<String> favorite = conn.getFavoriteItemIds(userId);

		List<Item> items = conn.searchItems(lat, lon, term);

		
		JSONArray array = new JSONArray();
		try {
			for (Item item : items) {
				// Add a thin version of item object
				JSONObject obj = item.toJSONObject();
				// Check if this is a favorite one.
				// This field is required by frontend to correctly display favorite items.
				obj.put("favorite", favorite.contains(item.getItemId()));

				array.put(obj);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		RpcHelper.writeJsonArray(response, array);

	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}
	

}
